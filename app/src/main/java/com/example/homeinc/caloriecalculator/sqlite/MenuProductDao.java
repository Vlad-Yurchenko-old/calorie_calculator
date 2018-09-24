package com.example.homeinc.caloriecalculator.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.homeinc.caloriecalculator.domain.CurrentMenuItem;
import com.example.homeinc.caloriecalculator.domain.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MenuProductDao {

    private static final String LOG_TAG = "VY";

    private DBHelperMenu dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public MenuProductDao(Context context) {
        this.context = context;
        dbHelper = new DBHelperMenu(context);
        dbHelper = new DBHelperMenu(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<CurrentMenuItem> readAll() {
        ArrayList<CurrentMenuItem> products = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBHelperMenu.TABLE_RECIPE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperMenu.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelperMenu.KEY_NAME);
            int kkalIndex = cursor.getColumnIndex(DBHelperMenu.KEY_KKAL);
            int proteinIndex = cursor.getColumnIndex(DBHelperMenu.KEY_PROTEINS);
            int fatsIndex = cursor.getColumnIndex(DBHelperMenu.KEY_FATS);
            int carbonIndex = cursor.getColumnIndex(DBHelperMenu.KEY_CARBOHYDRATES);
            int dateIndex = cursor.getColumnIndex(DBHelperMenu.KEY_DATE);
            int countIndex = cursor.getColumnIndex(DBHelperMenu.KEY_COUNT);
            do {
                products.add(new CurrentMenuItem(new Product(cursor.getInt(idIndex), cursor.getString(nameIndex),
                        cursor.getInt(kkalIndex), cursor.getInt(proteinIndex),
                        cursor.getInt(fatsIndex), cursor.getInt(carbonIndex)), cursor.getString(dateIndex), cursor.getInt(countIndex)));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "Продуктов нет", Toast.LENGTH_SHORT).show();
        }
        return products;
    }

    public void create(CurrentMenuItem currentMenuItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperMenu.KEY_NAME, currentMenuItem.product.getName());
        contentValues.put(DBHelperMenu.KEY_KKAL, currentMenuItem.product.getKkal());
        contentValues.put(DBHelperMenu.KEY_PROTEINS, currentMenuItem.product.getProteins());
        contentValues.put(DBHelperMenu.KEY_FATS, currentMenuItem.product.getFats());
        contentValues.put(DBHelperMenu.KEY_CARBOHYDRATES, currentMenuItem.product.getCarbohydrates());
        contentValues.put(DBHelperMenu.KEY_DATE, currentMenuItem.date);
        contentValues.put(DBHelperMenu.KEY_COUNT, currentMenuItem.number);
        sqLiteDatabase.insert(DBHelperMenu.TABLE_RECIPE, null, contentValues);
    }

    public void delete(int id){
        Log.d(LOG_TAG, "--- Delete from " + DBHelperMenu.TABLE_RECIPE + " : where id = "  + id + " ---");
        int delCount = sqLiteDatabase.delete(DBHelperMenu.TABLE_RECIPE, DBHelperMenu.KEY_ID + " = " + id, null);
        Log.d(LOG_TAG, "deleted rows count = " + delCount);
    }

    public void update(CurrentMenuItem currentMenuItem){
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Update mytable: where id = " + currentMenuItem.product.getId() + " ---");
        // подготовим значения для обновления
        cv.put(DBHelperMenu.KEY_NAME, currentMenuItem.product.getName());
        cv.put(DBHelperMenu.KEY_KKAL, currentMenuItem.product.getKkal());
        cv.put(DBHelperMenu.KEY_PROTEINS, currentMenuItem.product.getProteins());
        cv.put(DBHelperMenu.KEY_FATS, currentMenuItem.product.getFats());
        cv.put(DBHelperMenu.KEY_CARBOHYDRATES, currentMenuItem.product.getCarbohydrates());
        cv.put(DBHelperMenu.KEY_DATE, currentMenuItem.date);
        cv.put(DBHelperMenu.KEY_COUNT, currentMenuItem.number);
        // обновляем по id
        int updCount = sqLiteDatabase.update(DBHelperMenu.TABLE_RECIPE, cv, DBHelperMenu.KEY_ID + " = ?",
                new String[] { String.valueOf(currentMenuItem.product.getId()) });
        Log.d(LOG_TAG, "updated rows count = " + updCount);
    }

}
