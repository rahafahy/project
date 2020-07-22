package com.example.lastone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void but_log(View v) {
     Intent intent = new Intent(MainActivity.this, Login.class);
     startActivity(intent);
   }
    public void but_regg(View v) {
        Intent intent = new Intent(MainActivity.this, Register2.class);
        startActivity(intent);
    }


}