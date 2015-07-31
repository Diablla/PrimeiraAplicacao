package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Login;

public class LoginContract {

    public static final String TABLE_LOGIN = "login";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS_LOGIN = {USERNAME, PASSWORD};

    public static String getSqlCreateTableLogin() {

        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_LOGIN);
        sql.append(" ( ");
        sql.append(USERNAME + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" ); ");

        return sql.toString();
    }

   //ao clique do botão, verificar se o size da tabela é zero. Se for zero cria esse usuario a
   // primeira vez, se for maior que zero verifica se o usuario e a senha é admin admin;
    public static ContentValues getContentValues(Login login) {
        ContentValues values = new ContentValues();
        values.put(LoginContract.USERNAME, login.getUsername());
        values.put(LoginContract.PASSWORD, login.getPassword());

        return values;
    }

    public static Login bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Login login = new Login();
            login.setUsername(cursor.getString(cursor.getColumnIndex(LoginContract.USERNAME)));
            login.setPassword(cursor.getString(cursor.getColumnIndex(LoginContract.PASSWORD)));

            return login;
        }
        return null;
    }

}
