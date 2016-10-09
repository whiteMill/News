package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.news.R;
import com.example.entity.NewsEntity;
import com.example.utils.ContentNews;
import com.example.view.HeadListView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/8.
 */
public class NewsFragment extends Fragment {
    private  int newsID ;
    private  String newsName;
    private View  view;
    private TextView  item_textview;
    private HeadListView headListView;
    private ImageView detail_loading;
    private RelativeLayout notify_view;
    private TextView notify_view_text;
    private LinearLayout  notify_view_cancel_layout;
    private TextView notify_view_cancel;
    private TextView empty_notify_view;
    private ArrayList<NewsEntity> newsEntities=new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =  getArguments();
        if(bundle!=null){
            newsID = bundle.getInt("id",0);
            newsName = bundle.getString("text","");
        }else{
            newsID=0;
            newsName="";
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment,null);
        initView();
        return view;
    }

    private void initView(){
        item_textview = (TextView) view.findViewById(R.id.item_textview);
        headListView = (HeadListView) view.findViewById(R.id.mListView);
        detail_loading = (ImageView) view.findViewById(R.id.detail_loading);
        notify_view = (RelativeLayout) view.findViewById(R.id.notify_view);
        notify_view_text = (TextView) view.findViewById(R.id.notify_view_text);
        notify_view_cancel_layout = (LinearLayout) view.findViewById(R.id.notify_view_cancel_layout);
        notify_view_cancel = (TextView) view.findViewById(R.id.notify_view_cancel);
        empty_notify_view = (TextView) view.findViewById(R.id.empty_notify_view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /*
    * Fragment是否可见时候操作
    * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

        }else{

        }
    }

   private void initData(){
       newsEntities = ContentNews.getNewsEntity();
   }


    private class NewsAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsEntities.size();
        }

        @Override
        public Object getItem(int position) {
            return newsEntities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return null;
        }
    }
}
