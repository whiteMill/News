package com.example.dao;

import android.content.ContentValues;

import com.example.entity.ChannelItem;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/27.
 */
public interface ChannelDaoInter {

    public boolean addCache(ChannelItem item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause, String[] whereArgs);

    public Map<String, String> viewCache(String selection, String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection, String[] selectionArgs);

    public void clearFeedTable();

}
