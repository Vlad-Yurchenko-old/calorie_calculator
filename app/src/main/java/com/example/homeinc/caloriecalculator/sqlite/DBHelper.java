package com.example.homeinc.caloriecalculator.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.homeinc.caloriecalculator.domain.Product;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "productDB";
    public static final String TABLE_RECIPE = "products";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_KKAL = "kkal";
    public static final String KEY_PROTEINS = "proteins";
    public static final String KEY_FATS = "fats";
    public static final String KEY_CARBOHYDRATES = "carbohydrates";

    public DBHelper(Context context) {
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
                KEY_CARBOHYDRATES + " real)");

        create(db, new Product("Шоколад", 546, 5, 31, 61));
        create(db, new Product("Картофель", 77, 2, 0, 17));
        create(db, new Product("Гречка", 343, 13, 3, 72));
        create(db, new Product("Куриная грудка", 165, 31, 4, 0));
        create(db, new Product("Овсяная каша", 68, 2, 1, 12));
        create(db, new Product("Макароны", 371, 13, 2, 75));
        create(db, new Product("Яблоко", 52, 0, 0, 714));
        create(db, new Product("Банан", 89, 1, 0, 23));
        create(db, new Product("Мёд", 304, 0, 0, 82));
        create(db, new Product("Огурец", 16, 1, 0, 4));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_RECIPE);

        onCreate(db);

    }

    private void create(SQLiteDatabase sqLiteDatabase, Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME, product.getName());
        contentValues.put(DBHelper.KEY_KKAL, product.getKkal());
        contentValues.put(DBHelper.KEY_PROTEINS, product.getProteins());
        contentValues.put(DBHelper.KEY_FATS, product.getFats());
        contentValues.put(DBHelper.KEY_CARBOHYDRATES, product.getCarbohydrates());
        sqLiteDatabase.insert(DBHelper.TABLE_RECIPE, null, contentValues);
    }

}
