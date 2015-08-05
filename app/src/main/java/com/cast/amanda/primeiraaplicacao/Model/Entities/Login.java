package com.cast.amanda.primeiraaplicacao.Model.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.cast.amanda.primeiraaplicacao.Model.Persistence.SQLiteLoginRepository;

import java.io.Serializable;

/**
 * Created by Amanda on 30/07/2015.
 */
public class Login implements Serializable, Parcelable {

    private String username;
    private String password;

    public Login(Parcel in){
        super();
        readToParcel(in);
    }

    public Login(){
        super();
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>(){
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        public Login[] newArray(int size){
            return new Login[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (username != null ? !username.equals(login.username) : login.username != null)
            return false;
        return !(password != null ? !password.equals(login.password) : login.password != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    /*-----------------GETTERS AND SETTERS-----------------*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void verifyLogin(){
        //vai pegar uma instancia unica / verificar exige um login / this indica que quer verificar o proprio login(usuario)
        SQLiteLoginRepository.getInstance().verifyLogin(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username == null ? "" : username);
        dest.writeString(password == null ? "" : password);
    }

    private void readToParcel(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

}
