package com.cast.amanda.primeiraaplicacao.Model.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.cast.amanda.primeiraaplicacao.Model.Persistence.MemoryClientRepository;
import com.cast.amanda.primeiraaplicacao.Model.Persistence.SQLiteClientRepository;
import com.cast.amanda.primeiraaplicacao.Model.Services.ClientAdress;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Amanda on 20/07/2015.
 */
public class Client implements Serializable, Parcelable{

    private Integer id;
    private String name;
    private Integer age;
    private Integer phone;
    private ClientAdress clientAdress;

    public Client(Parcel in){
        super();
        readToParcel(in);
    }

    public Client(){
        super();
    }

   public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>(){
                @Override
                public Client createFromParcel(Parcel source) {
                    return new Client(source);
                }

                public Client[] newArray(int size){
                    return new Client[size];
                }
   };

    public ClientAdress getClientAdress() {
        if (clientAdress == null) {
            clientAdress = new ClientAdress();
        }
        return clientAdress;
    }

    public void setClientAdress(ClientAdress clientAdress) {
        this.clientAdress = clientAdress;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public static Creator<Client> getCREATOR() {
        return CREATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        return !(clientAdress != null ? !clientAdress.equals(client.clientAdress) : client.clientAdress != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (clientAdress != null ? clientAdress.hashCode() : 0);
        return result;
    }

    public void save(){
        //vai pegar uma instancia unica / save exige um cliente / this indica que quer salvar o proprio cliente
        SQLiteClientRepository.getInstance().save(this);
    }

    public static List<Client> getAll(){
        return SQLiteClientRepository.getInstance().getAll();
    }

    @Override
    public String toString() {
        return "Nome "+this.getName() + ", Idade "+this.getAge() +", Telefone " + this.getPhone();
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
        dest.writeInt(age == null ? -1 : age);
        dest.writeInt(phone == null ? -1 : phone);
        dest.writeParcelable(clientAdress, flags);
    }

    private void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        name = in.readString();
        int partialAge = in.readInt();
        age = partialAge == -1 ? null : partialAge;
        int partialPhone = in.readInt();
        phone = partialPhone == -1 ? null : partialPhone;
        clientAdress = in.readParcelable(ClientAdress.class.getClassLoader());
    }

}
