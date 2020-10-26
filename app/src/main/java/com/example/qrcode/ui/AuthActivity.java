package com.example.qrcode.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrcode.MainActivity;
import com.example.qrcode.R;
import com.example.qrcode.data.local.PrefUtils;
import com.example.qrcode.data.models.AuthModels;
import com.example.qrcode.data.network.AuthClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {


    private EditText userLogin,userPassword;
    private Button singIn,generate;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        init();
        signIn();
    }

    private void init(){
        userLogin = findViewById(R.id.auth_login_et_qr);
        userPassword = findViewById(R.id.auth_password_et_qr);
        singIn = findViewById(R.id.sing_in_qr);
        generate = findViewById(R.id.generate_gr);

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQR();
            }
        });
    }
    private void scanQR(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            id = result.getContents();
            AuthClient.getClient().getUser(id).enqueue(new Callback<AuthModels>() {
                @Override
                public void onResponse(Call<AuthModels> call, Response<AuthModels> response) {
                    if (response.isSuccessful() && response.body() != null){
                        PrefUtils.saveUserName(response.body().getLogin());
                        Log.d("Fail", "onResponse: "+response.body().getLogin());
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));}
                }

                @Override
                public void onFailure(Call<AuthModels> call, Throwable t) {
                    Log.d("Fail", "onFailure: "+ t.getMessage());
                    Toast.makeText(AuthActivity.this, "неверные данные",Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });
        }
        super.onActivityResult(requestCode,resultCode, data);
    }

    private void signIn(){
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = userLogin.getText().toString();
                String password = userPassword.getText().toString();
                String id = Credentials.basic(login,password);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,id);
                startActivity(Intent.createChooser(intent,"use qr code generator"));
            }


        });
    }

    @Override
    public void onBackPressed() {

    }

}