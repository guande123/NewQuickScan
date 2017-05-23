package com.gdz.demo.gdnews.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdz.demo.gdnews.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final int CONTINUE_COUNT_NUM = 1002;
    private static final int DONE_COUNT_NUM = 1003 ;
    private static final String TAG = "LoginActivity";
    private EditText mEdtPhone;
    private EditText mEdtIdentify;
    private Button mBtnIdentify;
    private Button mBtnLogin;
    private Handler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        bindViews();
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
              switch (msg.what){
                  case CONTINUE_COUNT_NUM:
                        mBtnIdentify.setText(String.valueOf(msg.arg1));
                      return true;
                  case DONE_COUNT_NUM:
                      mBtnIdentify.setClickable(true);
                      mBtnIdentify.setText("重新获取验证码");
                      return  true;
              }
                return false;
            }
        });
    }

    private void bindViews() {
        mEdtPhone = (EditText) findViewById(R.id.edt_phone_number);
        mEdtIdentify = (EditText) findViewById(R.id._edt_identify_number);
        mBtnIdentify = (Button) findViewById(R.id.btn_identify);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnIdentify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_identify:
                gainIdentificNumber();
                break;
            case R.id.btn_login:
                 //验证  获取的验证码 ，后 登陆
                vertifyIdentifyNumber();
                break;
        }
    }

    private void vertifyIdentifyNumber() {
        final String identifyNumber = mEdtIdentify.getText().toString().trim();
        final String phoneNumber = mEdtPhone.getText().toString().trim();
        BmobSMS.verifySmsCode(phoneNumber, identifyNumber, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                 //   Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "verifySmsCode:验证成功 ");
                    //一键登录bmob
                    BmobUser.loginBySMSCode(phoneNumber, identifyNumber, new LogInListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            //登录完成后，或返回上个活动，或者前往首页
                            if(e ==null){
                                Log.i(TAG, "loginBySMSCode: 短信一键登录成功");
                                finish();
                            }else{
                                Log.i(TAG, "loginBySMSCode: 短信一键登录失败");
                            }
                        }
                    });
                }else{
                    Log.i("LoginActivity", "doverifySmsCode:验证失败 ");
                }
            }
        });
    }

    private void gainIdentificNumber() {
        String phoneNumber = mEdtPhone.getText().toString().trim();
        //手机号码正则表达式
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        boolean isPhoneNum = phoneNumber.matches(regex);
        if(isPhoneNum){
            BmobSMS.requestSMSCode(phoneNumber, "bmobtest", new QueryListener<Integer>() {
                @Override
                public void done(Integer integer, BmobException e) {
                    if(e==null){
                        //需要注意网络请求耗时操作 使用的线程错误，Toast应该在主线程调用
                     //   Toast.makeText(LoginActivity.this,"坐等收取信息",Toast.LENGTH_SHORT).show();;
                        Log.i(TAG, "requestSMSCode: 坐等收取信息");
                    }else{
                        Log.i(TAG, "requestSMSCode: 发送验证码失败");
                    }
                }
            });
            mBtnIdentify.setClickable(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 60;
                    Message msg = new Message();
                    while(true){
                        msg.what = CONTINUE_COUNT_NUM;
                        msg.arg1 = i;
                        mHandler.sendMessage(msg);
                        i--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(i<0){
                            break;
                        }
                    }
                    msg.what = DONE_COUNT_NUM;
                    mHandler.sendMessage(msg);
                }
            }).start();
        }else{
            Toast.makeText(LoginActivity.this,"手机号码格式不正确！",Toast.LENGTH_LONG).show();;
        }
    }
}
