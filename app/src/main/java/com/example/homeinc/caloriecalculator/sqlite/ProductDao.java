package com.example.homeinc.caloriecalculator.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.homeinc.caloriecalculator.domain.Product;

import java.util.ArrayList;

public class ProductDao {

    private static final String LOG_TAG = "VY";

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public ProductDao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<Product> readAll() {
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_RECIPE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int kkalIndex = cursor.getColumnIndex(DBHelper.KEY_KKAL);
            int proteinIndex = cursor.getColumnIndex(DBHelper.KEY_PROTEINS);
            int fatsIndex = cursor.getColumnIndex(DBHelper.KEY_FATS);
            int carbonIndex = cursor.getColumnIndex(DBHelper.KEY_CARBOHYDRATES);
            do {
                products.add(new Product(cursor.getInt(idIndex), cursor.getString(nameIndex),
                        cursor.getInt(kkalIndex), cursor.getInt(proteinIndex), cursor.getInt(fatsIndex), cursor.getInt(carbonIndex)));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "Продуктов нет", Toast.LENGTH_SHORT).show();
        }
        return products;
    }

    public void create(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME, product.getName());
        contentValues.put(DBHelper.KEY_KKAL, product.getKkal());
        contentValues.put(DBHelper.KEY_PROTEINS, product.getProteins());
        contentValues.put(DBHelper.KEY_FATS, product.getFats());
        contentValues.put(DBHelper.KEY_CARBOHYDRATES, product.getCarbohydrates());
        sqLiteDatabase.insert(DBHelper.TABLE_RECIPE, null, contentValues);
    }

    public void delete(int id){
        Log.d(LOG_TAG, "--- Delete from " + DBHelper.TABLE_RECIPE + " : where id = "  + id + " ---");
        int delCount = sqLiteDatabase.delete(DBHelper.TABLE_RECIPE, DBHelper.KEY_ID + " = " + id, null);
        Log.d(LOG_TAG, "deleted rows count = " + delCount);
    }

    public void update(Product product){
        ContentValues cv = new ContentValues();
        Log.d(LOG_TAG, "--- Update mytable: where id = " + product.getId() + " ---");
        // подготовим значения для обновления
        cv.put(DBHelper.KEY_NAME, product.getName());
        cv.put(DBHelper.KEY_KKAL, product.getKkal());
        cv.put(DBHelper.KEY_PROTEINS, product.getProteins());
        cv.put(DBHelper.KEY_FATS, product.getFats());
        cv.put(DBHelper.KEY_CARBOHYDRATES, product.getCarbohydrates());
        // обновляем по id
        int updCount = sqLiteDatabase.update(DBHelper.TABLE_RECIPE, cv, DBHelper.KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        Log.d(LOG_TAG, "updated rows count = " + updCount);
    }

}
