package com.example.mmd;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacts> contacts = new ArrayList<>();
    RecyclerAdapter adapter;
    //    ArrayAdapter arrayAdapter;
    RecyclerView recyclerView;
    Button button;
    int position;
    int which_item = position;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recid);
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        contacts = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        //
//        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                return true;
//
//                final int which_item = position;
//
//                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_delete)
//                        .setTitle("Are you sure?").setMessage("Do you want to delete this Item")
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                contacts.remove(which_item);
//                                arrayAdapter.notifyDataSetChanged();
//
//                            }
//                        }).setNegativeButton("No" , null).show();
//            }
//        });
//        recyclerView.setOnLongClickListener(new View.OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View v){
//                return false;
//            }
//        });





        adapter = new RecyclerAdapter(this , contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        contacts.add(new Contacts("arash","987456","145", R.drawable.profile5));
        contacts.add(new Contacts("arash","987456","785", R.drawable.profile3));
        contacts.add(new Contacts("arash","987456","412", R.drawable.profile4));
        adapter.OnitemClick(new RecyclerAdapter.setOnClickListener() {
            @Override
            public void OnClickItem(int position, Contacts contacts) {
                Intent intent = new Intent(MainActivity.this,Contactinfo.class);
                intent.putExtra("name",contacts.getName().toString());
                intent.putExtra("number",contacts.getNumber());
                intent.putExtra("image",contacts.getProfile());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        menu.setHeaderTitle("Select Action");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.delAction){
            new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_delete)
                    .setTitle("Are you sure?").setMessage("Do you want to delete this Item")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            contacts.remove(which_item);
//                            arrayAdapter.notifyDataSetChanged();

                        }
                    }).setNegativeButton("No" , null).show();
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
            return true;

        }else if (item.getItemId() == R.id.edAction){
            Toast.makeText(this, "Edit Selected", Toast.LENGTH_SHORT).show();
        }else {
            return false;
        }

        return true;
    }
}