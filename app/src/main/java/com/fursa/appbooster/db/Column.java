package com.fursa.appbooster.db;

/**
 * This class contains app for DB
 * Created by Fursa Ilya on 10.11.17.
 */

public class Column {
    public static final String ID_COL = "id";
    public static final String TITLE_COL = "title";
    public static final String DESCRIPTION_COL = "description";
    public static final String DOWNLOAD_LINK_COL = "download_link";
    public static final String ICON_URL_COL = "icon_url";
    public static final String REWARD_COL = "reward";
    public static final String BUNDLE_ID_COL = "app_bundle_id";
    public static final String APPLE_DOWNLOAD_LINK_COL = "apple_download_link";
    //db info
    public static final String DB_NAME = "appbooster.sqlite";
    public static final String DB_TABLE = "OffersTable";
    public static int DB_VERSION_NUMBER = 1;
}
