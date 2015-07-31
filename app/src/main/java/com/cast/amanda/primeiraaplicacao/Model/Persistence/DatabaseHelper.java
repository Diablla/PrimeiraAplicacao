package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{


    private static final String BANCO_DADOS = "MY_DATABASE";
    private static final int VERSION = 2;

    public DatabaseHelper(Context context){

        super(context, DatabaseHelper.BANCO_DADOS, null, DatabaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getSqlCreateTable());
        db.execSQL(LoginContract.getSqlCreateTableLogin());

        db.execSQL("insert into login (username, password) values ('admin', 'admin')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
