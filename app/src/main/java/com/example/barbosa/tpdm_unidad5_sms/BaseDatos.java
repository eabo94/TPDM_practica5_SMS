package com.example.barbosa.tpdm_unidad5_sms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE SIGNOS (IDSUERTE INTEGER PRIMARY KEY AUTOINCREMENT," +
                "SIGNO VARCHAR(200), " +
                "DESCRIPCION VARCHAR(200))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
