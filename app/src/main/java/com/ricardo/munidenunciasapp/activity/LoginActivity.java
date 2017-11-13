package com.ricardo.munidenunciasapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ricardo.munidenunciasapp.R;
import com.ricardo.munidenunciasapp.models.Usuario;
import com.ricardo.munidenunciasapp.service.ApiService;
import com.ricardo.munidenunciasapp.service.ApiServiceGenerator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private Button btnLogin;
    private Button btnLinkToRegister;

    private EditText inputEmail;
    private EditText inputPassword;

    private ProgressDialog pDialog;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            inputEmail.setText(username);
            inputPassword.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String username = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!username.isEmpty() && !password.isEmpty()) {
                    // login user
                    pDialog.setMessage("Ingresando ...");
                    showDialog();

                    checkLogin(username, password);

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Campos incompletos!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void checkLogin(final String username, final String password) {

        final ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Usuario>> call = service.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Usuario> usuarios = response.body();
                        Log.d(TAG, "usuarios: " + usuarios);
                        hideDialog();

                        for (Usuario usuario : usuarios) {

                            Log.d(TAG, "usuarios: " + usuarios);
                            if (usuario.getUsername().equalsIgnoreCase(username) && usuario.getPassword().equalsIgnoreCase(convertMd5(password))) {

                                int id = usuario.getId();
                                String nombres = usuario.getNombres();

                                Toast.makeText(getApplication(), "Bienvenido "+ nombres, Toast.LENGTH_SHORT).show();

                                // Save to SharedPreferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                boolean success = editor
                                        .putString("id", String.valueOf(id))
                                        .putBoolean("islogged", true)
                                        .commit();

                                // Go to Dashboard
                                goDashboard();

                            }
                        }
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

                        // get id from SharedPreferences
                        String id = sharedPreferences.getString("id", null);
                        if( id.isEmpty() ) {
                        }
                        Toast.makeText(LoginActivity.this, "Datos incorrectos.", Toast.LENGTH_SHORT).show();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        hideDialog();
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                hideDialog();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public static String convertMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    private  void goDashboard(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
