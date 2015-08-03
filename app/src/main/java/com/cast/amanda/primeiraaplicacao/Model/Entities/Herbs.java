package com.cast.amanda.primeiraaplicacao.Model.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.cast.amanda.primeiraaplicacao.Model.Persistence.SQLiteClientRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Amanda on 20/07/2015.
 */
public class Herbs implements Serializable, Parcelable{

    private Integer id;
    private String name;
    private String about;
    private String medicalUse;

    public Herbs(Parcel in){
        super();
        readToParcel(in);
    }

    public Herbs(){
        super();
    }

    public static final Parcelable.Creator<Herbs> CREATOR = new Parcelable.Creator<Herbs>(){
        @Override
        public Herbs createFromParcel(Parcel source) {
            return new Herbs(source);
        }

        public Herbs[] newArray(int size){
            return new Herbs[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMedicalUse() {
        return medicalUse;
    }

    public void setMedicalUse(String medicalUse) {
        this.medicalUse = medicalUse;
    }

    public static Creator<Herbs> getCREATOR() {
        return CREATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Herbs herbs = (Herbs) o;

        if (id != null ? !id.equals(herbs.id) : herbs.id != null) return false;
        if (name != null ? !name.equals(herbs.name) : herbs.name != null) return false;
        if (about != null ? !about.equals(herbs.about) : herbs.about != null) return false;
        if (medicalUse != null ? !medicalUse.equals(herbs.medicalUse) : herbs.medicalUse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (medicalUse != null ? medicalUse.hashCode() : 0);
        return result;
    }

    public void save(){
        //vai pegar uma instancia unica / save exige um cliente / this indica que quer salvar o proprio cliente
        SQLiteClientRepository.getInstance().save(this);
    }

    public static List<Herbs> getAll(){
        return SQLiteClientRepository.getInstance().getAll();
    }

    @Override
    public String toString() {
        return "Nome "+this.getName() + ", Sobre "+this.getAbout() +", Uso Medicinal " + this.getMedicalUse();
    }

    public void delete() {
        SQLiteClientRepository.getInstance().delete(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(about == null ? "" : about);
        dest.writeString(medicalUse == null ? "" : medicalUse);
    }

    private void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        name = in.readString();
        about = in.readString();
        medicalUse = in.readString();
    }

}
