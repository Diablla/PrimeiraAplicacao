package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Login;
import com.cast.amanda.primeiraaplicacao.util.AppUtil;

/**
 * Created by Amanda on 30/07/2015.
 */
public class SQLiteLoginRepository implements LoginRepository {

    private static SQLiteLoginRepository singletonInstance;

    private SQLiteLoginRepository(){
        super();
    }

    public static LoginRepository getInstance(){
        if(SQLiteLoginRepository.singletonInstance == null){
            SQLiteLoginRepository.singletonInstance = new SQLiteLoginRepository();
        }
        return SQLiteLoginRepository.singletonInstance;
    }

    @Override
    public Login verifyLogin(Login login) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = LoginContract.getContentValues(login);

        String where = LoginContract.USERNAME + " = ? AND " + LoginContract.PASSWORD + " = ? ";
        String[] select = {login.getUsername(), login.getPassword()};

        Cursor cursor = db.query(LoginContract.TABLE_LOGIN, LoginContract.COLUMNS_LOGIN, where, select, null, null, null);

        Login usuarioValido = LoginContract.bind(cursor);
        db.close();
        helper.close();

        return usuarioValido;
    }
}
