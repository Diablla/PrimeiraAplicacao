package com.cast.amanda.primeiraaplicacao.Model.Services;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Amanda on 27/07/2015.
 */
public class ClientAdress implements Parcelable{

    private String cep;
    private String tipoDeLogradouro;
    private String Logradouro;
    private String Bairro;
    private String Cidade;
    private String estado;

    public ClientAdress(Parcel in){
        super();
        readToParcel(in);
    }

    public ClientAdress(){
        super();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoDeLogradouro() {
        return tipoDeLogradouro;
    }

    public void setTipoDeLogradouro(String tipoDeLogradouro) {
        this.tipoDeLogradouro = tipoDeLogradouro;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cep == null ? "" : cep);
        dest.writeString(tipoDeLogradouro == null ? "" : tipoDeLogradouro);
        dest.writeString(Logradouro == null ? "" : Logradouro);
        dest.writeString(Bairro == null ? "" : Bairro);
        dest.writeString(Cidade == null ? "" : Cidade);
        dest.writeString(estado == null ? "" : estado);
    }

    private void readToParcel(Parcel in) {
        cep = in.readString();
        tipoDeLogradouro = in.readString();
        Logradouro = in.readString();
        Bairro = in.readString();
        Cidade = in.readString();
        estado = in.readString();
    }
}
