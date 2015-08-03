package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;

import java.util.ArrayList;
import java.util.List;

public class ClientContract {


    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ABOUT = "about";
    public static final String MEDICALUSE = "medicalUse";

    public static final String[] COLUMNS = {ID, NAME, ABOUT, MEDICALUSE};

    public static String getSqlCreateTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT,  ");
        sql.append(ABOUT + " TEXT, ");
        sql.append(MEDICALUSE + " TEXT ");
        sql.append(" ); ");

        return sql.toString();
    }

    public static Herbs bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Herbs herbs = new Herbs();
            herbs.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            herbs.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            herbs.setAbout(cursor.getString(cursor.getColumnIndex(ClientContract.ABOUT)));
            herbs.setMedicalUse(cursor.getString(cursor.getColumnIndex(ClientContract.MEDICALUSE)));

            return herbs;
        }
        return null;
    }

    public static ContentValues getContentValues(Herbs herbs) {
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, herbs.getId());
        values.put(ClientContract.NAME, herbs.getName());
        values.put(ClientContract.ABOUT, herbs.getAbout());
        values.put(ClientContract.MEDICALUSE, herbs.getMedicalUse());

        return values;
    }

    public static List<Herbs> bindList(Cursor cursor) {

        List<Herbs> herbses = new ArrayList<>();

        while (cursor.moveToNext()) {
            herbses.add(bind(cursor));
        }
        return herbses;
    }

}
