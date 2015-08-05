package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHerbsRepository implements HerbsRepository {

    private static SQLiteHerbsRepository singletonInstance;

    private SQLiteHerbsRepository(){
        super();
    }

    public static HerbsRepository getInstance(){
    if(SQLiteHerbsRepository.singletonInstance == null){
        SQLiteHerbsRepository.singletonInstance = new SQLiteHerbsRepository();
    }
        return SQLiteHerbsRepository.singletonInstance;
    }

    @Override
    public void save(Herbs herbs) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = HerbsContract.getContentValues(herbs);

        if(herbs.getId() == null){
            db.insert(HerbsContract.TABLE, null, values);
        } else {
            values.put(HerbsContract.ID, herbs.getId());
            String where = HerbsContract.ID + " = ?";
            String[] args = {herbs.getId().toString()};
            db.update(HerbsContract.TABLE, values, where, args);
        }

        db.close();
        helper.close();
    }

    @Override
    public List<Herbs> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(HerbsContract.TABLE, HerbsContract.COLUMNS, null, null, null, null, HerbsContract.NAME);
        List<Herbs> herbses = new ArrayList<>();

        herbses = HerbsContract.bindList(cursor);
        db.close();
        helper.close();
        return herbses;
    }

}
