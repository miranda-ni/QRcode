package com.example.qrcode.data.network;

import android.widget.EditText;

import com.example.qrcode.data.models.AuthModels;

public interface AuthCallback {
     void onSuccess(AuthModels authModels);
    void onFailure(Exception e);

}
