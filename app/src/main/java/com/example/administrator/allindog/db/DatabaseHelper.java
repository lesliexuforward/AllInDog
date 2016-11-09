package com.example.administrator.allindog.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by jiang on 2016/11/9.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mcontext;

    //创建用户表
    private static final String CREATE_USER="create table users("+"userid integer  primary key autoincrement,"+"userpassword text not null,"
            +"usersex integer not null,"+"phonenumber text not null,"+"personaldesc text,"+"birthday  text not null,"+
            "regitime text not null,"+"username text not null)";

    //创建宠物表
    private static final String CREATE_PETS="create table pets("+"petid integer primary key autoincrement,"+
            "petname text not null,"+"petsex integer not null,"+"petbirthday text,"+"masterid integer,"+
            "FOREIGN KEY(masterid) REFERENCES users(userid))";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_PETS);
        Toast.makeText(mcontext, "Database create succeed!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
