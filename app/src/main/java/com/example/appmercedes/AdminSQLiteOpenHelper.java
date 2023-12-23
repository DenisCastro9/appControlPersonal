package com.example.appmercedes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table docentes (\n" +
                "\t dni integer primary key, \n" +
                "\t nombre text, \n" +
                "\t cuil integer, \n" +
                "\t mail text, \n" +
                "\t cargo text, \n" +
                "\t antiguedad text, \n" +
                "\t horas text, \n" +
                "\t campo text, \n" +
                "\t situacion text \n" +
                ")\n");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
