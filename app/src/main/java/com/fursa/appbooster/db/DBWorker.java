package com.fursa.appbooster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fursa.appbooster.model.TaskModel;

/**
 * Created by Fursa Ilya on 10.11.17.
 */

public class DBWorker {
    private static final String TAG = DBWorker.class.getSimpleName();
    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private long result;

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

    public int getRowsSize() {
        cursor = db.query(Column.DB_TABLE, null, null, null, null, null, null);
        return cursor.getCount();
    }
}
