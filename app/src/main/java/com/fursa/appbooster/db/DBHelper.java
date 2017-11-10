package com.fursa.appbooster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.fursa.appbooster.model.TaskModel;

import org.w3c.dom.Text;

/**
 * Created by Fursa Ilya on 09.11.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Column.DB_TABLE + "("
                + Column.ID_COL + " INTEGER PRIMARY KEY,"
                + Column.TITLE_COL + " TEXT,"
                + Column.DESCRIPTION_COL + " TEXT,"
                + Column.DOWNLOAD_LINK_COL + " TEXT,"
                + Column.ICON_URL_COL + " TEXT, "
                + Column.REWARD_COL + " DOUBLE, "
                + Column.APPLE_DOWNLOAD_LINK_COL + " TEXT, "
                + Column.BUNDLE_ID_COL + " TEXT " + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Column.DB_TABLE);
        onCreate(db);
    }

    public DBHelper(Context context) {
        super(context, Column.DB_NAME, null, Column.DB_VERSION_NUMBER);
    }

}
