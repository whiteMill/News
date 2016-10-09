package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.SqlHelp;
import com.example.entity.ChannelItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/27.
 */
public class ChannelImple implements ChannelDaoInter {
    private SqlHelp sqlHelp;
    private ChannelImple channelImple;

    public  ChannelImple(Context context){
        sqlHelp=new SqlHelp(context);
    }


    @Override
    public boolean addCache(ChannelItem item) {
        long id=-1;
        Boolean flag=false;
        SQLiteDatabase sqLiteDatabase=sqlHelp.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        try{
            contentValues.put("ID",item.getId());
            contentValues.put("NAME",item.getName());
            contentValues.put("ORDERID",item.getOrderId());
            contentValues.put("SELECTED",item.getSelected());
            id=sqLiteDatabase.insert(SqlHelp.TABLE_CHANNEL,null,contentValues);
            if(id==-1){
                flag=false;
            }else{
                flag=true;
            }
        }catch (Exception e){

        }finally {
            if(sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
        }
     return flag;
    }

    @Override
    public boolean deleteCache(String whereClause, String[] whereArgs) {
         long id=-1;
         SQLiteDatabase sqLiteDatabase=sqlHelp.getWritableDatabase();

         try {
             id = sqLiteDatabase.delete(SqlHelp.TABLE_CHANNEL,whereClause,whereArgs);
         }catch (Exception e){

         }finally {
              if(sqLiteDatabase!=null){
                  sqLiteDatabase.close();
              }
         }
        if(id==-1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateCache(ContentValues values, String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;
        try {
            database = sqlHelp.getWritableDatabase();
            count = database.update(SqlHelp.TABLE_CHANNEL, values, whereClause, whereArgs);
            flag = (count > 0 ? true : false);
        } catch (Exception e) {

        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;


    }

    @Override
    public Map<String, String> viewCache(String selection, String[] selectionArgs) {
         Map<String,String> datamap=new HashMap<>();
         SQLiteDatabase database = null;
        try{
            database = sqlHelp.getReadableDatabase();
            Cursor cursor = database.query(true,SqlHelp.TABLE_CHANNEL,null,selection,selectionArgs,null,null,null,null);
            int columnCount=cursor.getColumnCount();
            while(cursor.moveToNext()){
                for(int i=0;i<columnCount;i++){
                    String name=cursor.getColumnName(i);
                    String value=cursor.getString(cursor.getColumnIndex(name));
                    if(value==null){
                        value="";
                    }
                    datamap.put(name,value);
                }
            }
        }catch (Exception e){

        }finally {
            database.close();
        }

        return datamap;
    }

    @Override
    public List<Map<String, String>> listCache(String selection, String[] selectionArgs) {
        List<Map<String,String>> dataList=new ArrayList<>();
        SQLiteDatabase database = null;
        try{
            database = sqlHelp.getReadableDatabase();
            Cursor cursor = database.query(false,SqlHelp.TABLE_CHANNEL,null,selection,selectionArgs,null,null,null,null);
            int columnCount=cursor.getColumnCount();
            while(cursor.moveToNext()){
                Map<String,String> datamap=new HashMap<>();
                for(int i=0;i<columnCount;i++){
                    String name=cursor.getColumnName(i);
                    String value=cursor.getString(cursor.getColumnIndex(name));
                    if(value==null){
                        value="";
                    }
                    datamap.put(name,value);
                }
                dataList.add(datamap);
            }
        }catch (Exception e){

        }finally {
            if(database!=null){
                database.close();
            }

        }

        return dataList;
    }

    @Override
    public void clearFeedTable() {
        String sql = "DELETE FROM " + SqlHelp.TABLE_CHANNEL + ";";
        SQLiteDatabase db = sqlHelp.getWritableDatabase();
        db.execSQL(sql);
        revertSeq();
    }

    private void revertSeq() {
        String sql = "update sqlite_sequence set seq=0 where name='"
                + SqlHelp.TABLE_CHANNEL + "'";
        SQLiteDatabase db = sqlHelp.getWritableDatabase();
        db.execSQL(sql);
    }
}
