package com.fursa.appbooster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fursa.appbooster.model.TaskModel;

/**
 * This class helps to perform operations
 * with SQLite Database
 * Created by Fursa Ilya on 10.11.17.
 */

public class DBWorker {
    private static final String TAG = DBWorker.class.getSimpleName();
    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private long result;
    private TaskModel model;

    public DBWorker(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(TaskModel taskModel) {
        ContentValues cv = new ContentValues();
        cv.put(Column.ID_COL, taskModel.getId());
        cv.put(Column.TITLE_COL, taskModel.getTitle());
        cv.put(Column.DESCRIPTION_COL, taskModel.getDescription());
        cv.put(Column.DOWNLOAD_LINK_COL, taskModel.getDownloadLink());
        cv.put(Column.ICON_URL_COL, taskModel.getIconUrl());
        cv.put(Column.REWARD_COL, taskModel.getReward());
        cv.put(Column.APPLE_DOWNLOAD_LINK_COL, taskModel.getAppleDownloadLink());
        cv.put(Column.BUNDLE_ID_COL, taskModel.getAppleBundleId());
        result = db.insert(Column.DB_TABLE, null, cv);
        Log.d(TAG, String.valueOf(result));
    }

    public int getTableSize() {
        cursor = db.query(Column.DB_TABLE, null, null, null, null, null, null);
        return cursor.getCount();
    }

    public TaskModel getTaskByTitle(String title) {

        cursor = db.rawQuery("SELECT * FROM " + Column.DB_TABLE + " WHERE " + Column.TITLE_COL + "='" + title + "';", null);
        Log.d(TAG, String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()) {
            do {
                model = new TaskModel(
                        cursor.getInt(cursor.getColumnIndex(Column.ID_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.TITLE_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.DESCRIPTION_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.DOWNLOAD_LINK_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.ICON_URL_COL)),
                        cursor.getDouble(cursor.getColumnIndex(Column.REWARD_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.APPLE_DOWNLOAD_LINK_COL)),
                        cursor.getString(cursor.getColumnIndex(Column.BUNDLE_ID_COL)));

            } while (cursor.moveToNext());
        }

        return model;
    }
}
