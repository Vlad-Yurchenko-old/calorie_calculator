package com.example.homeinc.caloriecalculator.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperMenu extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "menuDB";
    public static final String TABLE_RECIPE = "menuProducts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_KKAL = "kkal";
    public static final String KEY_PROTEINS = "proteins";
    public static final String KEY_FATS = "fats";
    public static final String KEY_CARBOHYDRATES = "carbohydrates";
    public static final String KEY_DATE = "date";
    public static final String KEY_COUNT = "count"; // кол-во в граммах

    public DBHelperMenu(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_RECIPE + "(" +
                KEY_ID + " integer primary key," +
                KEY_NAME + " text," +
                KEY_KKAL + " real," +
                KEY_PROTEINS + " real," +
                KEY_FATS + " real," +
                KEY_DATE + " text," +
                KEY_COUNT + " real," +
                KEY_CARBOHYDRATES + " real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_RECIPE);

        onCreate(db);

    }

}
