package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.Model.Services.CepService;
import com.cast.amanda.primeiraaplicacao.Model.Services.ClientAdress;
import com.cast.amanda.primeiraaplicacao.R;
import com.cast.amanda.primeiraaplicacao.util.FormHelper;

/**
 * Created by Amanda on 21/07/2015.
 */
public class ClientPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private Client client;
    private EditText editTextTipoLogradouro;
    private EditText editTextLogradouro;
    private EditText editTextBairro;
    private EditText editTextTipoCidade;
    private EditText editTextTipoEstado;
    private EditText persistTextCep;
    private Button btnCEP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        editTextName = (EditText) findViewById(R.id.persistTextName);
        editTextAge = (EditText)  findViewById(R.id.persistTextAge);
        editTextPhone = (EditText) findViewById(R.id.persistTextPhone);
        persistTextCep = (EditText) findViewById(R.id.persistTextCep);

        editTextTipoLogradouro = (EditText) findViewById(R.id.editTextTipoLogradouro);
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextTipoCidade = (EditText) findViewById(R.id.editTextTipoCidade);
        editTextTipoEstado = (EditText) findViewById(R.id.editTextTipoEstado);

        bindButtonFindCep();

        client = new Client();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if(client == null){
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    private void bindButtonFindCep() {
        btnCEP = (Button) findViewById(R.id.btnCEP);
        btnCEP.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new getAddressByCep().execute(persistTextCep.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuSave){
            if(FormHelper.requireValidate(ClientPersistActivity.this, editTextAge, editTextName, editTextPhone, persistTextCep)){
                Client client = bindClient();
                client.save();
                Toast.makeText(ClientPersistActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                ClientPersistActivity.this.finish();
            }
        }
        else if(item.getItemId() == R.id.menuClear){
            editTextName.setText("");
            editTextAge.setText("");
            editTextPhone.setText("");
            persistTextCep.setText("");
        }
        return true;
    }

    private Client bindClient(){
        if (client == null) {
            client = new Client();
        }
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setPhone(Integer.valueOf(editTextPhone.getText().toString()));
        client.getClientAdress().setCep(persistTextCep.getText().toString());

        return client;
    }

    private void bindForm(Client client){
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextPhone.setText(client.getPhone().toString());
    }

    private ProgressDialog progressDialog;
    private class getAddressByCep extends AsyncTask<String, Void, ClientAdress> {
        @Override
        protected ClientAdress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPreExecute() {
           progressDialog = new ProgressDialog(ClientPersistActivity.this);
           progressDialog.setMessage(getString(R.string.Loading));
           progressDialog.show();
        }

        @Override
        protected void onPostExecute(ClientAdress clientAdress) {
            super.onPostExecute(clientAdress);
            client.setClientAdress(clientAdress);
            editTextTipoLogradouro.setText(clientAdress.getTipoDeLogradouro());
            editTextLogradouro.setText(clientAdress.getLogradouro());
            editTextTipoCidade.setText(clientAdress.getCidade());
            editTextTipoEstado.setText(clientAdress.getEstado());
            editTextBairro.setText(clientAdress.getBairro());
            progressDialog.dismiss();
        }
    }
}

