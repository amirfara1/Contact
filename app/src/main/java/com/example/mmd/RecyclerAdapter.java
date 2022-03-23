package com.example.mmd;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    private Context context;
    private ArrayList<Contacts> contacts;
    private setOnClickListener setOnClickListener;

    public RecyclerAdapter(Context context, ArrayList<Contacts> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
    Contacts contact = contacts.get(position);
    holder.name.setText(contact.getName());
    holder.number.setText(contact.getNumber());
    holder.time.setText(contact.getTime());
    holder.profile.setBackgroundResource(contact.getProfile());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;
        TextView time;
        ImageView profile;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.personName);
            number = itemView.findViewById(R.id.telephone);
            time = itemView.findViewById(R.id.msgtime);
            profile = itemView.findViewById(R.id.profile_pic);
            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add("delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Toast.makeText(context,contacts.toString(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });

                    contextMenu.add("edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            return true;
                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(setOnClickListener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnClickListener.OnClickItem(getAdapterPosition(),contacts.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
    interface setOnClickListener{
        void OnClickItem(int position,Contacts contacts);
    }
    public void OnItemClick (setOnClickListener setOnClickListener) {
        this.setOnClickListener = setOnClickListener;
    }
    public void uodate(ArrayList<Contacts> contacts){
        this.contacts.clear();
        this.contacts.addAll(contacts);
    }
}