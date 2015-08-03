package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClientRepository implements ClientRepository {

    private static SQLiteClientRepository singletonInstance;

    private SQLiteClientRepository(){
        super();
    }

    public static ClientRepository getInstance(){
    if(SQLiteClientRepository.singletonInstance == null){
        SQLiteClientRepository.singletonInstance = new SQLiteClientRepository();
    }
        return SQLiteClientRepository.singletonInstance;
    }

    @Override
    public void save(Herbs herbs) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = ClientContract.getContentValues(herbs);

        if(herbs.getId() == null){
            db.insert(ClientContract.TABLE, null, values);
        } else {
            values.put(ClientContract.ID, herbs.getId());
            String where = ClientContract.ID + " = ?";
            String[] args = {herbs.getId().toString()};
            db.update(ClientContract.TABLE, values, where, args);
        }

        db.close();
        helper.close();
    }

    @Override
    public List<Herbs> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ClientContract.TABLE, ClientContract.COLUMNS, null, null, null, null, ClientContract.NAME);
        List<Herbs> herbses = new ArrayList<>();

        herbses = ClientContract.bindList(cursor);
        db.close();
        helper.close();
        return herbses;
    }

    @Override
    public void delete(Herbs herbs) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = ClientContract.ID + " = ? ";
        String[] args = {herbs.getId().toString()};

        db.delete(ClientContract.TABLE, where, args);
        db.close();
        helper.close();

    }
}
