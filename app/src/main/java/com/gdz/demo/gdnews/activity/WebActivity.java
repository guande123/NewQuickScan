package com.gdz.demo.gdnews.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.data.MyBmobUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class WebActivity extends AppCompatActivity {
    private WebView mWebView;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        mWebView = (WebView) findViewById(R.id.webview);
        mEditText = (EditText) findViewById(R.id.edt_commet);
        mButton = (Button) findViewById(R.id.btn_comment);
        Intent intent = getIntent();
        if(intent!=null){
          String url=   intent.getStringExtra("url");
            String title = intent.getStringExtra("title");
             String uniquekey = intent.getStringExtra("uniquekey");
            mWebView.loadUrl(url);  //方式1. 加载一个网页：
            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);//这里的url为参数的url
                    return true;
                }
                //设置加载前的函数
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    System.out.println("开始加载了");
                }
                //设置结束加载函数
                @Override
                public void onPageFinished(WebView view, String url) {
                    System.out.println("结束加载了");
                }
            });
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String comment =    mEditText.getText().toString().trim();
                //MyBmobUser是bmobUser的扩展类 ,,如果bmobuser 之前有登陆过，将在本地生成持久态的user对象
              MyBmobUser bmobUser = BmobUser.getCurrentUser(MyBmobUser.class);
                if(bmobUser == null){
                    // 前往登陆页面进行登陆
                    Intent intent = new Intent(WebActivity.this,LoginActivity.class);
                    intent.putExtra("comment",true);
                    startActivity(intent);
                }else{
                    //对象保存 评论内容  update
                    bmobUser.setCommentContent(comment);
                  //  update
                }
            }
        });
    }
}
