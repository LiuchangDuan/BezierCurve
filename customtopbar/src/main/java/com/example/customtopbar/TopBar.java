package com.example.customtopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/4.
 */
public class TopBar extends RelativeLayout {

    private int mLeftTextColor;

    private Drawable mLeftBackground;

    private String mLeftText;

    private int mRightTextColor;

    private Drawable mRightBackground;

    private String mRightText;

    private float mTitleTextSize;

    private int mTitleTextColor;

    private String mTitle;

    private Button mLeftButton, mRightButton;

    private TextView mTitleView;

    private LayoutParams mLeftParams, mRightParams, mTitleParams;

    private topbarClickListener mListener;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
        initView(context);
    }

    private void initParams(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        if (ta != null) {
            mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
            mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
            mLeftText = ta.getString(R.styleable.TopBar_leftText);
            mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
            mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
            mRightText = ta.getString(R.styleable.TopBar_rightText);
            mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
            mTitleTextColor = ta.getColor(R.styleable.TopBar_myTitleTextColor, 0);
            mTitle = ta.getString(R.styleable.TopBar_myTitle);
            ta.recycle();
        }
    }

    private void initView(Context context) {
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);

        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        addView(mTitleView, mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.leftClick();
                }
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.rightClick();
                }
            }
        });

    }

    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置按钮的显示与否
     * 通过id区分按钮
     * flag区分是否显示
     * @param id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    public interface topbarClickListener {

        void leftClick();

        void rightClick();

    }

}
