package com.outbell.ezebazaar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

public class SplashScreenPage extends Activity{

    private  final int splash_length =3000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenPage.this,MainActivity.class);
                startActivity(intent);

                SplashScreenPage.this.finish();

            }
        },splash_length);
    }
}
