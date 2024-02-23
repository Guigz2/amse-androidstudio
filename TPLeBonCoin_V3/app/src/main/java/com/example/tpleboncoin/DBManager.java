package com.example.tpleboncoin;
// Package + Import

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private DBManager(Context c) {
        context = c;
        //init(); // Useful for adding ads for the first time.
    }

    public static DBManager getDBManager(Context context) {
        if (DBManager == null){
            return new DBManager(context);
        }
        return DBManager;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init(){
        open();
        insert(new AdModel("Wood", "Douai", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=","123456789"));
        insert(new AdModel("Steel", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg","234567891"));
        insert(new AdModel("Clay", "Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg","345678912"));
        insert(new AdModel("Metal", "Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg","456789123"));
        insert(new AdModel("Glass", "Valenciennes", "https://cdn.globalso.com/jinjing-glass/clear-glass1.jpg","567891234"));
        insert(new AdModel("Wood", "Orchies", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg","678912345"));
    }

    public void insert(AdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitle());
        contentValue.put(DBHelper.ADDRESS, ad.getAddress());
        contentValue.put(DBHelper.IMAGE, ad.getImage());
        contentValue.put(DBHelper.TEL, ad.getTelephone());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._id, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE, DBHelper.TEL};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, AdModel ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getTitle());
        contentValues.put(DBHelper.ADDRESS, ad.getAddress());
        contentValues.put(DBHelper.IMAGE, ad.getImage());
        contentValues.put(DBHelper.TEL, ad.getTelephone());
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._id + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._id + "=" + _id, null);
    }

    public AdModel getById(int id){
        return dbHelper.getById(id);
    }

}
