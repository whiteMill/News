package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by admin on 2016/9/27.
 */
public class DBUtils {
    private static DBUtils dbUtils;
    private Context context;
    private SqlHelp sqlHelp;
    private SQLiteDatabase sqLiteDatabase;

    private DBUtils(Context context) {
        this.context = context;
        sqlHelp = new SqlHelp(context);
        sqLiteDatabase = sqlHelp.getWritableDatabase();
    }

    public static DBUtils getDbUtils(Context context) {
        if (dbUtils == null) {
            dbUtils = new DBUtils(context);
        }
        return dbUtils;
    }

    public void close() {
        sqlHelp.close();
        sqlHelp = null;
        sqLiteDatabase.close();
        sqLiteDatabase = null;
        dbUtils = null;
    }

    /*添加数据*/
    public void insertData(ContentValues values){
        sqLiteDatabase.insert(sqlHelp.TABLE_CHANNEL,null,values);
    }

    /*删除数据*/
    public void deleteData(String whereClause, String[] whereArgs){
        sqLiteDatabase.delete(sqlHelp.TABLE_CHANNEL,whereClause,whereArgs);
    }

    /*更新数据*/
    public void updateData(ContentValues values,String whereClause, String[] whereArgs){
        sqLiteDatabase.update(sqlHelp.TABLE_CHANNEL,values,whereClause,whereArgs);
    }

    /*查询数据*/
    public Cursor selectData(String[] columns, String selection,
                             String[] selectionArgs, String groupBy, String having,
                             String orderBy) {
        Cursor cursor = sqLiteDatabase.query(sqlHelp.TABLE_CHANNEL,columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

}
