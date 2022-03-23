package com.example.mmd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

    public class DBhelper extends SQLiteOpenHelper {
        public DBhelper(Context context) {
            super(context,DBconstants.DB_name,null,DBconstants.DB_version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_TABLE= "CREATE TABLE "+DBconstants.TABLE_NAME+"("+
                    DBconstants.c_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DBconstants.c_name+" NVARCHAR(30),"+
                    DBconstants.c_country+" NVARCHAR(20),"+
                    DBconstants.c_time+" CHAR(10),"+
                    DBconstants.c_image+" INTEGER,"+
                    DBconstants.c_number+" CHAR(11)"+")";
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String DROP_TABLE="DROP TABLE IF EXISTS "+DBconstants.TABLE_NAME;
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
        public void insert(Contacts contacts){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(DBconstants.c_name,contacts.getName());
            contentValues.put(DBconstants.c_image,contacts.getProfile());
            contentValues.put(DBconstants.c_number,contacts.getNumber());
            contentValues.put(DBconstants.c_time,contacts.getTime());
            db.insert(DBconstants.TABLE_NAME,null,contentValues);
            db.close();
        }
        public ArrayList<Contacts> getContact(){
            SQLiteDatabase db=this.getReadableDatabase();
            ArrayList<Contacts> contacts=new ArrayList<>();
            String GET_CONTACT="SELECT * FROM "+DBconstants.TABLE_NAME;
            Cursor cursor= db.rawQuery(GET_CONTACT,null);
            if (cursor.getCount()!=0){
                cursor.moveToFirst();
                for(int i=0;i<cursor.getCount();i++){
                    contacts.add(new Contacts(cursor.getString(1),cursor.getString(5),cursor.getString(3),cursor.getInt(4)));
                    cursor.moveToNext();
                }
            }
            return contacts;
        }
        public void update_contact(int ID,Contacts contacts){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(DBconstants.c_name,contacts.getName());
            contentValues.put(DBconstants.c_image,contacts.getProfile());
            contentValues.put(DBconstants.c_number,contacts.getNumber());
            contentValues.put(DBconstants.c_time,contacts.getTime());
            db.update(DBconstants.TABLE_NAME,contentValues,DBconstants.c_id+ "= ?",new String[]{String.valueOf(ID)});
        }
        public void delete_contact(int ID){
            SQLiteDatabase db=this.getWritableDatabase();
            db.delete(DBconstants.TABLE_NAME,DBconstants.c_id+"= ? ",new String[]{String.valueOf(ID)});
            db.close();
        }
    }
