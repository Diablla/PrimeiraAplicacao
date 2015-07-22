package com.cast.amanda.primeiraaplicacao.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.R;

/**
 * Created by Amanda on 21/07/2015.
 */
public class ClientPersistActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private EditText editTextStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        editTextName = (EditText) findViewById(R.id.persistTextName);
        editTextAge = (EditText)  findViewById(R.id.persistTextAge);
        editTextPhone = (EditText) findViewById(R.id.persistTextPhone);
        editTextStreet = (EditText)  findViewById(R.id.persistTextStreet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuSave){
            Client client = bindClient();
            client.save();

            Toast.makeText(ClientPersistActivity.this, Client.getAll().toString(), Toast.LENGTH_LONG).show();

        }
        else if(item.getItemId() == R.id.menuClear){
            editTextName.setText("");
            editTextAge.setText("");
            editTextPhone.setText("");
            editTextStreet.setText("");
        }
        return true;
    }

    private Client bindClient(){
        Client client = new Client();
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setPhone(Integer.valueOf(editTextPhone.getText().toString()));
        client.setStreet(editTextStreet.getText().toString());

        return client;
    }

    //bindClient().save();
}

