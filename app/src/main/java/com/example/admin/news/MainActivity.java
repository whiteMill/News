package com.example.admin.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.app;
import com.example.entity.ChannelItem;
import com.example.utils.BaseTools;
import com.example.utils.ChannelManage;
import com.example.view.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private MyHorizontalScrollView mHorizontalScrollView;
    private ViewPager mViewPager;
    private RelativeLayout rl_column;
    private LinearLayout mRadioGroup_content;
    private ImageView shade_left;  //左阴影部分
    private ImageView shade_right; //右阴影部分
    private LinearLayout ll_more_columns;
    private ImageView button_more_columns; //点击加载更多
    private ArrayList<ChannelItem> channelItemArrayList=new ArrayList<>();
    private int columnIndex=0; //当前被选中的栏目
      /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScreenWidth= BaseTools.getWindowsWidth(this);
        mItemWidth=mScreenWidth/7;
        Log.d("TTT",mScreenWidth+"---"+mItemWidth);
        init();
        getData();
        initFragment();
        initDataList();
    }

    private void init(){
       mHorizontalScrollView= (MyHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        rl_column= (RelativeLayout) findViewById(R.id.rl_column);
        mRadioGroup_content= (LinearLayout) findViewById(R.id.mRadioGroup_content);
        shade_left= (ImageView) findViewById(R.id.shade_left);
        shade_right= (ImageView) findViewById(R.id.shade_right);
        ll_more_columns= (LinearLayout) findViewById(R.id.ll_more_columns);
        button_more_columns= (ImageView) findViewById(R.id.button_more_columns);
    }

    public void getData(){
        channelItemArrayList = (ArrayList<ChannelItem>) ChannelManage.getChannelManage(app.getmApplication().getSqlHelp()).getUserItem();
    }

    public void initDataList(){
        mRadioGroup_content.removeAllViews();
        int count = channelItemArrayList.size();
        mHorizontalScrollView.setParam(mRadioGroup_content,ll_more_columns,rl_column,shade_left,shade_right,mItemWidth,this);
        for(int i=0;i<count;i++){
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView=new TextView(this);
            columnTextView.setTextAppearance(this,R.style.top_category_scroll_view_item_text);
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(channelItemArrayList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(columnIndex == i){
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 for(int i=0;i<mRadioGroup_content.getChildCount();i++){
                     View localView = mRadioGroup_content.getChildAt(i);
                     if(localView!=v){
                          localView.setSelected(false);
                     }else{
                          localView.setSelected(true);
                          mViewPager.setCurrentItem(i);
                     }
                 }
                }
            });
            mRadioGroup_content.addView(columnTextView,i,params);
        }
    }

    private void initFragment(){
        fragmentList.clear();
        int count = channelItemArrayList.size();
        for(int i=0;i<count;i++){
            Bundle data=new Bundle();
            data.putInt("id",channelItemArrayList.get(i).getId());
            data.putString("text",channelItemArrayList.get(i).getName());
            Fragment fragment=new Fragment();
            fragment.setArguments(data);
            fragmentList.add(fragment);
        }
        mViewPager.setAdapter(new mFragementPagerAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) fragmentList));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                setTable(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTable(int position) {
        columnIndex=position;
        for(int i = 0; i<mRadioGroup_content.getChildCount();i++){
            View checkView = mRadioGroup_content.getChildAt(position);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mHorizontalScrollView.smoothScrollTo(i2, 0);
        }

        for(int j=0;j<mRadioGroup_content.getChildCount();j++){
            View buttonView = mRadioGroup_content.getChildAt(j);
            boolean isCheck=false;
            if(j==position){
                 isCheck=true;
            }else{
                isCheck=false;
            }
            buttonView.setSelected(isCheck);

        }

    }

    private class mFragementPagerAdapter extends  FragmentPagerAdapter{

        private ArrayList<Fragment> fragmentArrayList;


        public mFragementPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public mFragementPagerAdapter(FragmentManager fragmentManager,ArrayList<Fragment> fragmentArrayList){
            super(fragmentManager);
            this.fragmentArrayList=fragmentArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
