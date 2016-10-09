package com.example.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/**
 * Created by Wangl on 2016/9/26.
 */
public class MyHorizontalScrollView extends HorizontalScrollView {

    /*传入整体布局*/
    private View ll_content;
    /*传入更多栏目选择布局*/
    private View ll_more;
    /*传入拖动栏布局*/
    private View rl_column;
    /*传入左阴影图片*/
    private ImageView leftImageView;
    /*传入右阴影图片*/
    private ImageView rightImageView;
    /*传入屏幕宽度*/
    private int mScreennWidth = 0;
    /*父类活动的Activity*/
    private Activity activity;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    * 传入父类布局中的资源文件
    * */
    public void setParam(View ll_content, View ll_more, View rl_column, ImageView leftImageView, ImageView rightImageView, int mScreennWidth, Activity activity) {
        this.activity = activity;
        this.leftImageView = leftImageView;
        this.rightImageView = rightImageView;
        this.mScreennWidth = mScreennWidth;
        this.ll_content = ll_content;
        this.ll_more = ll_more;
        this.rl_column = rl_column;
    }

    @Override
    protected void onScrollChanged(int ll_location, int tt_location, int oldl_location, int oldt_location) {
        /*
        * ll_content 滑动后的可视界面的左上角在整个ScrollViewX轴的位置,oldl_location 滑动前的位置
        *
        * */
        super.onScrollChanged(ll_location, tt_location, oldl_location, oldt_location);
        shade_ShowOrHide();
        if (ll_content != null && !activity.isFinishing() && ll_more != null && leftImageView != null && rightImageView != null && rl_column != null) {
            if (ll_content.getWidth() <= mScreennWidth) {
                leftImageView.setVisibility(View.GONE);
                 rightImageView.setVisibility(View.GONE);
            }
        } else {
            return;
        }

        if (ll_location == 0) {
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
            return;
        }
        if (ll_content.getWidth() - ll_location + rl_column.getWidth() + ll_more.getWidth() == mScreennWidth) {
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
            return;
        }
        leftImageView.setVisibility(View.VISIBLE);
        rightImageView.setVisibility(View.VISIBLE);
    }

    public void shade_ShowOrHide() {
        if (ll_content != null && !activity.isFinishing()) {
            measure(0, 0);
            if (getMeasuredWidth() <= mScreennWidth) {
                leftImageView.setVisibility(View.GONE);
                rightImageView.setVisibility(View.GONE);
            }
        } else {
            return;
        }
        if (getLeft() == 0) {
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
        }
        if (getRight() == getMeasuredWidth() - mScreennWidth) {
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.GONE);
        }
        leftImageView.setVisibility(View.VISIBLE);
        rightImageView.setVisibility(View.VISIBLE);

    }

}
