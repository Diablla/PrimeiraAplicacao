package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.Model.Services.ClientAdress;
import com.cast.amanda.primeiraaplicacao.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ClientContract {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String CEP = "cep";
    public static final String TIPO_LOGRADOURO = "tipo_logradouro";
    public static final String LOGRADOURO = "logradouro";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    public static final String BAIRRO = "bairro";

    public static final String[] COLUMNS = {ID, NAME, AGE, PHONE, CEP, TIPO_LOGRADOURO, LOGRADOURO, CIDADE, ESTADO, BAIRRO};

    public static String getSqlCreateTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT,  ");
        sql.append(AGE + " INTEGER, ");
        sql.append(PHONE + " INTEGER, ");
        sql.append(CEP + " TEXT,  ");
        sql.append(TIPO_LOGRADOURO + " TEXT,  ");
        sql.append(LOGRADOURO + " TEXT,  ");
        sql.append(CIDADE + " TEXT,  ");
        sql.append(ESTADO + " TEXT,  ");
        sql.append(BAIRRO + " TEXT  ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static Client bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getInt(cursor.getColumnIndex(ClientContract.PHONE)));
            ClientAdress clientAdress = new ClientAdress();

            clientAdress.setCep(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            clientAdress.setTipoDeLogradouro(cursor.getString(cursor.getColumnIndex(ClientContract.TIPO_LOGRADOURO)));
            clientAdress.setLogradouro(cursor.getString(cursor.getColumnIndex(ClientContract.LOGRADOURO)));
            clientAdress.setCidade(cursor.getString(cursor.getColumnIndex(ClientContract.CIDADE)));
            clientAdress.setEstado(cursor.getString(cursor.getColumnIndex(ClientContract.ESTADO)));
            clientAdress.setBairro(cursor.getString(cursor.getColumnIndex(ClientContract.BAIRRO)));

            client.setClientAdress(clientAdress);
            return client;
        }
        return null;
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getPhone());

        values.put(ClientContract.TIPO_LOGRADOURO, client.getClientAdress().getTipoDeLogradouro());
        values.put(ClientContract.LOGRADOURO, client.getClientAdress().getLogradouro());
        values.put(ClientContract.BAIRRO, client.getClientAdress().getBairro());
        values.put(ClientContract.CIDADE, client.getClientAdress().getCidade());
        values.put(ClientContract.CEP, client.getClientAdress().getCep());
        values.put(ClientContract.ESTADO, client.getClientAdress().getEstado());

        return values;
    }

    public static List<Client> bindList(Cursor cursor) {

        List<Client> clients = new ArrayList<>();

        while (cursor.moveToNext()) {
            clients.add(bind(cursor));
        }
        return clients;
    }

}
