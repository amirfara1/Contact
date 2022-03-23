package com.example.mmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<Contacts> contacts = new ArrayList<>();
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    public static final int REQ_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recid);
        toolbar = findViewById(R.id.tool);
        contacts = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        DBhelper dBhelper=new DBhelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this,dBhelper.getContact());
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        adapter.OnItemClick(new RecyclerAdapter.setOnClickListener() {
            @Override
            public void OnClickItem(int position, Contacts contacts) {
                Intent intent = new Intent(MainActivity.this, Contactinfo.class);
                intent.putExtra("name", contacts.getName());
                intent.putExtra("number", contacts.getNumber());
                intent.putExtra("image", contacts.getProfile());
                startActivity(intent);
            }
        });
    }
}