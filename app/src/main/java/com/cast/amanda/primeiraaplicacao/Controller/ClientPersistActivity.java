package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });

        editTextAge = (EditText) findViewById(R.id.persistTextAge);
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
        if (extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if (client == null) {
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    private void bindButtonFindCep() {
        btnCEP = (Button) findViewById(R.id.btnCEP);
        btnCEP.setOnClickListener(new View.OnClickListener() {

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
        if (item.getItemId() == R.id.menuSave) {
            if (FormHelper.requireValidate(ClientPersistActivity.this, editTextAge, editTextName,
                    editTextPhone, persistTextCep, editTextTipoLogradouro, editTextLogradouro,
                    editTextBairro, editTextTipoCidade, editTextTipoEstado)) {
                Client client = bindClient();
                client.save();
                Toast.makeText(ClientPersistActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                ClientPersistActivity.this.finish();
            }
        } else if (item.getItemId() == R.id.menuClear) {
            editTextName.setText("");
            editTextAge.setText("");
            editTextPhone.setText("");
            persistTextCep.setText("");
        }
        return true;
    }

    private Client bindClient() {
        if (client == null) {
            client = new Client();
            ClientAdress clientAdress = new ClientAdress();
            client.setClientAdress(clientAdress);
        }
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setPhone(Integer.valueOf(editTextPhone.getText().toString()));

        client.getClientAdress().setCep(persistTextCep.getText().toString());
        client.getClientAdress().setBairro(editTextBairro.getText().toString());
        client.getClientAdress().setCidade(editTextTipoCidade.getText().toString());
        client.getClientAdress().setEstado(editTextTipoEstado.getText().toString());
        client.getClientAdress().setTipoDeLogradouro(editTextTipoLogradouro.getText().toString());
        client.getClientAdress().setLogradouro(editTextLogradouro.getText().toString());

        return client;
    }

    private void bindForm(Client client) {
        editTextName.setText(client.getName());
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);

        editTextAge.setText(client.getAge().toString());
        editTextPhone.setText(client.getPhone().toString());
        persistTextCep.setText(client.getClientAdress().getCep());
        editTextTipoLogradouro.setText(client.getClientAdress().getTipoDeLogradouro());
        editTextBairro.setText(client.getClientAdress().getBairro());
        editTextTipoCidade.setText(client.getClientAdress().getCidade());
        editTextLogradouro.setText(client.getClientAdress().getLogradouro());
        editTextTipoEstado.setText(client.getClientAdress().getEstado());
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

