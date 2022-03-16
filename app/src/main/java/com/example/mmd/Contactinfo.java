package com.example.mmd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contactinfo extends AppCompatActivity {
    TextView profile_name;
    TextView profile_number;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinfo);
        profile_name = findViewById(R.id.nameProfile);
        profile_number = findViewById(R.id.phoneProfile);
        image = findViewById(R.id.profile_image);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        profile_name.setText(name);
        String number = intent.getExtras().getString("number");
        profile_number.setText(number);
        int imageph = intent.getExtras().getInt("image");
        image.setBackgroundResource(imageph);
    }
}