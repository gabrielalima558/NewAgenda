package com.example.gabriela.agenda.model.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gabriela.agenda.model.Classmate;

import java.util.ArrayList;
import java.util.List;

public class ClassmateDAO extends SQLiteOpenHelper {

    public ClassmateDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE classmate (id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "address TEXT, " +
                "phone TEXT, " +
                "site TEXT, " +
                "stars REAL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS classmate";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertClassmate(Classmate classmate) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("name", classmate.getName());
        data.put("address", classmate.getAddress());
        data.put("phone", classmate.getPhone());
        data.put("site", classmate.getSite());
        data.put("stars", classmate.getStars());

        db.insert("classmate", null, data);
    }

    public List<Classmate> getClassmate() {
        String sql = "SELECT * FROM classmate;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Classmate> classmateList = new ArrayList<Classmate>();
        while (cursor.moveToNext()){
            Classmate classmate = new Classmate();
            classmate.setId(cursor.getLong(cursor.getColumnIndex("id")));
            classmate.setName(cursor.getString(cursor.getColumnIndex("name")));
            classmate.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            classmate.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            classmate.setSite(cursor.getString(cursor.getColumnIndex("site")));
            classmate.setStars(cursor.getInt(cursor.getColumnIndex("stars")));

            classmateList.add(classmate);
        }
        cursor.close();

        return classmateList;
    }

    public void delete(Classmate classmate) {
        SQLiteDatabase db = getWritableDatabase();
        String params = (classmate.getId().toString());
        db.delete("classmate", "id = ?", new String[]{params});
    }

    public boolean update(Classmate classmate){
        ContentValues data = new ContentValues();
        data.put("name", classmate.getName());
        data.put("address", classmate.getAddress());
        data.put("phone", classmate.getPhone());
        data.put("site", classmate.getSite());
        data.put("stars", classmate.getStars());

        String where = "id = ?";

        String[] whereArgs = {Long.toString(classmate.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("classmate", data, where, whereArgs) > 0;

        db.close();

        Log.e("Editar", "classmate"+ data + where + whereArgs);
        Log.e("UPDATE", String.valueOf(isUpdate));

        return isUpdate;
    }
}
