package com.aitc.storage;

/**
 * Created by shamar on 5/28/2015.
 */
public class Const {
    public static int DB_VERSION = 1;
    public static String DB_NAME = "mydb.db";
    public static String TABLE_NAME = "persons";

    public static String DB_COLUMN_ID = "_id";
    public static String DB_COLUMN_NAME = "name";
    public static String DB_COLUMN_AGE = "age";
    public static String DB_COLUMN_PHONE = "phone";
    public static String DB_COLUMN_EMAIL = "email";
    public static String DB_COLUMN_POSITION = "position";
    public static String DB_COLUMN_STREET = "street";
    public static String DB_COLUMN_PLACE = "place";

    public static String[] LIST_COULMNS = {DB_COLUMN_NAME, DB_COLUMN_POSITION};
    public static int[] LIST_LAYOUT_IDS = {R.id.list_item_name, R.id.list_item_position};
}
