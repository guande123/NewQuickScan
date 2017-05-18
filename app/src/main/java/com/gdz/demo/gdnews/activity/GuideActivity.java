package com.gdz.demo.gdnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.utils.SPUtils;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class GuideActivity extends AppCompatActivity {
    private TextView mTextView;
    private ViewFlipper mViewFlipper ;
    private GestureDetector mGestureDetector;
    private OnGestureDetectorListener mGestureListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Log.i("GuideActivity", "onCreate: " );

        initViews();

    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.tx_start_exp);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.setFirstStatus(GuideActivity.this,false);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mGestureListener = new OnGestureDetectorListener();
        mGestureDetector = new GestureDetector(GuideActivity.this,mGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class OnGestureDetectorListener implements  GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 120) {//向右滑动
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(GuideActivity.this, R.anim.push_left_in));
                mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(GuideActivity.this, R.anim.push_left_out));
                mViewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > 120) {//向左滑动
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(GuideActivity.this, R.anim.push_right_in));
                mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(GuideActivity.this, R.anim.push_right_out));
                mViewFlipper.showPrevious();
            }
            return false;
        }
    }
}
