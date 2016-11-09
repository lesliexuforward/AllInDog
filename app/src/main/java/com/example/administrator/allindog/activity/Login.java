package com.example.administrator.allindog.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.allindog.R;
import com.example.administrator.allindog.db.DatabaseHelper;


public class Login extends AppCompatActivity {
    private TextView registerButton;
    //数据库名称
    public static final String DatabaseName="dandc.db";
    public static  final int Database_version=1;

    private DatabaseHelper dbHelper;

    //测试查询数据库
    private Button queryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //创建数据库
        dbHelper=new DatabaseHelper(this,DatabaseName,null,Database_version);
       // dbHelper.getWritableDatabase();
        registerButton=(TextView) findViewById(R.id.register);
        queryData=(Button)findViewById(R.id.query);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("users", null, null, null, null, null,
                        null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor
                                .getColumnIndex("username"));
                        String password = cursor.getString(cursor
                                .getColumnIndex("userpassword"));
                        int sex = cursor.getInt(cursor
                                .getColumnIndex("usersex"));
                        double phone = cursor.getDouble(cursor
                                .getColumnIndex("phonenumber"));
                        Log.d("MainActivity", " name is " + name);
                        Log.d("MainActivity", "password is " + password);
                        Log.d("MainActivity", "sex is " + sex);
                        Log.d("MainActivity", "phone is " + phone);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //把数据库的引用传过去

                intent.setClass(Login.this,Register.class);
                startActivity(intent);
            }
        });

    }
}
