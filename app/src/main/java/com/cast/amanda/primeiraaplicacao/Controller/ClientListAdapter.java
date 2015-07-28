package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.R;

import java.util.List;

/**
 * Created by Amanda on 20/07/2015.
 */
public class ClientListAdapter extends BaseAdapter {

    private List<Client> clientList;
    private Activity context;

    public ClientListAdapter(Activity context, List<Client> listClient){
        this.context = context;
        this.clientList = listClient;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }

    @Override
    public Client getItem(int position) {
        return clientList.get(position);
    }

    //position é um identificador unico, nunca retornaremos um mesmo elemento em mais de uma posição.
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.client_list_item, parent, false);
        Client client = getItem(position);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(client.getName());

        TextView textViewAge = (TextView) view.findViewById(R.id.textViewAge);
        textViewAge.setText(client.getAge().toString());

        return view;
    }

    public void setClients(List<Client> clients){
        this.clientList = clients;
    }
}
