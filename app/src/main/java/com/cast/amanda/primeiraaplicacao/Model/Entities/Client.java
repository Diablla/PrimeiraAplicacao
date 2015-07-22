package com.cast.amanda.primeiraaplicacao.Model.Entities;

import com.cast.amanda.primeiraaplicacao.Model.Persistence.MemoryClientRepository;

import java.util.List;

/**
 * Created by Amanda on 20/07/2015.
 */
public class Client {

    private  String name;
    private Integer age;
    private Integer phone;
    private String street;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        return !(street != null ? !street.equals(client.street) : client.street != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    public void save(){
        //vai pegar uma instancia unica / save exige um cliente / this indica que quer salvar o proprio cliente
        MemoryClientRepository.getInstance().save(this);
    }

    public static List<Client> getAll(){
        return MemoryClientRepository.getInstance().getAll();
    }

    @Override
    public String toString() {
        return "Nome "+this.getName() + ", Idade "+this.getAge() +", Telefone " + this.getPhone() +  ", Rua " + this.getStreet();
    }
}
