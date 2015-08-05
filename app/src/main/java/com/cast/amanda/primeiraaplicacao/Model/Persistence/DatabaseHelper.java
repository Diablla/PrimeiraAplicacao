package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{


    private static final String BANCO_DADOS = "MY_DATABASE";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context){

        super(context, DatabaseHelper.BANCO_DADOS, null, DatabaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HerbsContract.getSqlCreateTable());
        db.execSQL(LoginContract.getSqlCreateTableLogin());

        db.execSQL("insert into login (username, password) values ('admin', 'admin')");
        db.execSQL("insert into herbs (id, name, about, medicalUse, favorite) values (1, 'boldo', 'O nome cientifico do boldo eh Peumus boldus Molina e pode ser comprada em lojas de produtos naturais e farmacias de manipulacao.', 'O boldo serve para tratar ma digestao, problemas do figado, litiase biliar, gota, obstipacao, cistite, flatulencia, dor de cabeca e suores frio.', 0);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
