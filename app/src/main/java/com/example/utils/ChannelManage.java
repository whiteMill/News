package com.example.utils;

import android.util.Log;

import com.example.dao.ChannelImple;
import com.example.db.SqlHelp;
import com.example.entity.ChannelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/27.
 */
public class ChannelManage {
    public static ChannelManage channelManage;

    //默认的用户选择频道列表
    public static List<ChannelItem> defaultUserChannels;

    //默认的其他频道列表
    public static List<ChannelItem> defaultOtherChannels;

    //判断数据库中是否存在用户数据
    private boolean userIsExist = false;

    private ChannelImple channelImple;


    private ChannelManage(SqlHelp sqlHelp) {
        if (channelImple == null) {
            channelImple = new ChannelImple(sqlHelp.getContext());
        }
    }

    public static ChannelManage getChannelManage(SqlHelp sqlHelp) {
        if (channelManage == null) {
            channelManage = new ChannelManage(sqlHelp);
        }
        return channelManage;
    }

    static {
        defaultUserChannels = new ArrayList<ChannelItem>();
        defaultOtherChannels = new ArrayList<ChannelItem>();
        defaultUserChannels.add(new ChannelItem(1, "推荐", 1, 1));
        defaultUserChannels.add(new ChannelItem(2, "热点", 2, 1));
        defaultUserChannels.add(new ChannelItem(3, "杭州", 3, 1));
        defaultUserChannels.add(new ChannelItem(4, "时尚", 4, 1));
        defaultUserChannels.add(new ChannelItem(5, "科技", 5, 1));
        defaultUserChannels.add(new ChannelItem(6, "体育", 6, 1));
        defaultUserChannels.add(new ChannelItem(7, "军事", 7, 1));
        defaultOtherChannels.add(new ChannelItem(8, "财经", 1, 0));
        defaultOtherChannels.add(new ChannelItem(9, "汽车", 2, 0));
        defaultOtherChannels.add(new ChannelItem(10, "房产", 3, 0));
        defaultOtherChannels.add(new ChannelItem(11, "社会", 4, 0));
        defaultOtherChannels.add(new ChannelItem(12, "情感", 5, 0));
        defaultOtherChannels.add(new ChannelItem(13, "女人", 6, 0));
        defaultOtherChannels.add(new ChannelItem(14, "旅游", 7, 0));
        defaultOtherChannels.add(new ChannelItem(15, "健康", 8, 0));
        defaultOtherChannels.add(new ChannelItem(16, "美女", 9, 0));
        defaultOtherChannels.add(new ChannelItem(17, "游戏", 10, 0));
        defaultOtherChannels.add(new ChannelItem(18, "数码", 11, 0));
        defaultUserChannels.add(new ChannelItem(19, "娱乐", 12, 0));
    }

    public  void deleteAllChannel(){
        channelImple.clearFeedTable();
    }

    //获取用户的频道
    public List<ChannelItem> getUserItem(){
        List<ChannelItem> channelItems=new ArrayList<>();
        List<Map<String, String>> mapList = channelImple.listCache(SqlHelp.SELECTED,new String[]{"1"});
        if(mapList!=null&&!mapList.isEmpty()){
            userIsExist=true;
            for(int i=0;i<mapList.size();i++){
                ChannelItem channelItem=new ChannelItem();
                channelItem.setId(Integer.valueOf(mapList.get(i).get(SqlHelp.ID)));
                channelItem.setOrderId(Integer.valueOf(mapList.get(i).get(SqlHelp.ORDERID)));
                channelItem.setName(mapList.get(i).get(SqlHelp.NAME));
                channelItem.setSelected(Integer.valueOf(mapList.get(i).get(SqlHelp.SELECTED)));
                channelItems.add(channelItem);
            }
            return channelItems;
        }
       initDefaultChannel();
       return defaultUserChannels;
    }


    //获取其他的频道
    public List<ChannelItem> getOtherItem(){
        List<ChannelItem> otherlItems=new ArrayList<>();
        List<Map<String, String>> mapList = channelImple.listCache(SqlHelp.SELECTED,new String[]{"0"});
        if(mapList!=null&&!mapList.isEmpty()){
            userIsExist=true;
            for(int i=0;i<mapList.size();i++){
                ChannelItem channelItem=new ChannelItem();
                channelItem.setId(Integer.valueOf(mapList.get(i).get(SqlHelp.ID)));
                channelItem.setOrderId(Integer.valueOf(mapList.get(i).get(SqlHelp.ORDERID)));
                channelItem.setName(mapList.get(i).get(SqlHelp.NAME));
                channelItem.setSelected(Integer.valueOf(mapList.get(i).get(SqlHelp.SELECTED)));
                otherlItems.add(channelItem);
            }
        }
        if(userIsExist){
            return otherlItems;
        }
        otherlItems=defaultOtherChannels;
        return otherlItems;
    }


    //保存用户频道
    public void saveUserChannel(List<ChannelItem> channelItemList){
       for(int i=0;i<channelItemList.size();i++){
           ChannelItem channelItem=channelItemList.get(i);
           channelItem.setOrderId(i);
           channelItem.setSelected(Integer.valueOf(1));
           channelImple.addCache(channelItem);
       }

    }

    //保存其他频道
    public void saveOtherChannel(List<ChannelItem> itemList){
     for(int i=0;i<itemList.size();i++){
         ChannelItem channelItem = itemList.get(i);
         channelItem.setSelected(Integer.valueOf(0));
         channelItem.setOrderId(i);
         channelImple.addCache(channelItem);
     }
 }

    /**
     * 初始化数据库内的频道数据
     */
    private void initDefaultChannel(){
        Log.d("deleteAll", "deleteAll");
        deleteAllChannel();
        saveUserChannel(defaultUserChannels);
        saveOtherChannel(defaultOtherChannels);
    }

}
