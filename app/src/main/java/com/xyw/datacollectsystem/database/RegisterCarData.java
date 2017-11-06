package com.xyw.datacollectsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 31429 on 2017/11/2.
 */

public class RegisterCarData extends SQLiteOpenHelper {
    /**
     * 数据库版本号
     **/
    private final static int DATABASE_VERSION = 1;
    /**
     * 数据库名
     **/
    private final static String DB_NAME = "image_db";

    /**
     * 表名
     **/
    private String TABLE_IMAGE = "image_data";
    public String T_ID = "_id";// 字段名
    public String T_BLOB = "T_BLOB";// 字段名
    /**
     * 创建表 SQL 字符串
     **/
    private String TABLE_IMAGE_CREATE = "create table " + TABLE_IMAGE + " (" + T_ID
            + " INTEGER DEFAULT'1'NOT NULL PRIMARY KEY AUTOINCREMENT ,"
            + T_BLOB + " BLOB);";
    /**
     * 表列数据集合
     **/
    private String[] col = {T_ID, T_BLOB};

    public RegisterCarData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建数据
     **/
    public Long createData(Bitmap bmp) {
        ContentValues initValues = new ContentValues();
        Long id = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
        initValues.put(T_BLOB, os.toByteArray());// 以字节形式保存
        SQLiteDatabase db = getDatabaseWrit();
        id = db.insert(TABLE_IMAGE, null, initValues);// 保存数据
        db.close();
        Log.i("Image", "create done.");
        return id;
    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = null;

        SQLiteDatabase db = getDatabaseRead();
        Cursor cursor = db.query(TABLE_IMAGE, col, null, null, null, null, null);// 数据的查询
        HashMap<String, Object> bindData = null;
        list = new ArrayList<Map<String, Object>>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            bindData = new HashMap<String, Object>();
            bindData.put(T_ID, cursor.getLong(0));
            byte[] in = cursor.getBlob(1);
            Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
            bindData.put(T_BLOB, bmpout);
            list.add(bindData);
        }
        cursor.close();
        db.close();
        Log.i("Image", "get a Bitmap.");
        return list;
    }

    public void delete() {
        SQLiteDatabase db = getDatabaseWrit();
        db.delete(TABLE_IMAGE, null, null);// 数据的删除
        db.close();
        Log.i("Image", "delete all data.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_IMAGE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table" + TABLE_IMAGE;
        db.execSQL(sql);
        onCreate(db);
    }

    private SQLiteDatabase getDatabaseRead() {
        return this.getReadableDatabase();
    }

    private SQLiteDatabase getDatabaseWrit() {
        return this.getWritableDatabase();
    }
}

