package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qrcode.data.local.PrefUtils;
import com.example.qrcode.ui.AuthActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView qrCode;
    private Button startQr;
    private EditText qrValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (PrefUtils.getUserName().isEmpty()){
            startActivity(new Intent(this, AuthActivity.class));



        qrCode = findViewById(R.id.image_view);
        startQr = findViewById(R.id.btn);
        qrValue = findViewById(R.id.edit_text);

        startQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}