package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.R;

import java.util.List;

public class HerbsListAdapter extends BaseAdapter {

    private List<Herbs> herbsList;
    private Activity context;

    public HerbsListAdapter(Activity context, List<Herbs> listHerbs){
        this.context = context;
        this.herbsList = listHerbs;
    }

    @Override
    public int getCount() {
        return herbsList.size();
    }

    @Override
    public Herbs getItem(int position) {
        return herbsList.get(position);
    }

    //position eh um identificador unico, nunca retornaremos um mesmo elemento em mais de uma posicao.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //o que eh mostrado na tela
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.client_list_item, parent, false);
        Herbs herbs = getItem(position);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(herbs.getName());

        return view;
    }

    public void setClients(List<Herbs> herbses){
        this.herbsList = herbses;
    }
}
