package com.example.administrator.allindog.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

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
    //登录
    private Button loginButton;
    //账号和密码
    private EditText editUsername;
    private EditText editPassword;

    private  SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //创建数据库
        dbHelper=new DatabaseHelper(this,DatabaseName,null,Database_version);
        db= dbHelper.getWritableDatabase();

        editUsername=(EditText)findViewById(R.id.etloginuser);
        editPassword=(EditText)findViewById(R.id.edloginpass);

        //测试查询数据库文件
        queryData=(Button)findViewById(R.id.query);
        queryData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

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
                        String phone = cursor.getString(cursor
                                .getColumnIndex("phonenumber"));
                        String birthday = cursor.getString(cursor
                                .getColumnIndex("birthday"));
                        String des = cursor.getString(cursor
                                .getColumnIndex("personaldesc"));
                        String restime = cursor.getString(cursor
                                .getColumnIndex("regitime"));
                        Log.d("MainActivity", " name is " + name);
                        Log.d("MainActivity", "password is " + password);
                        Log.d("MainActivity", "sex is " + sex);
                        Log.d("MainActivity", "phone is " + phone);
                        Log.d("MainActivity", "bir is " + birthday);
                        Log.d("MainActivity", "des is " + des);
                        Log.d("MainActivity", "regtime is " + restime);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        //注册用户
        registerButton=(TextView) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //把数据库的引用传过去

                intent.setClass(Login.this,Register.class);
                startActivity(intent);
            }
        });

        //用户登录
        loginButton=(Button)findViewById(R.id.login);
        loginButton.setOnClickListener(new LoginListener());

    }


    //创建一个监听登录的接口
    class LoginListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String name = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            if (name.equals("") || password.equals("")) {
                // 弹出消息框
                new AlertDialog.Builder(Login.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            } else {
                isUserinfo(name, password);
            }
        }

        // 判断输入的用户是否正确
        public Boolean isUserinfo(String name, String pwd) {
            Cursor cursor=null;
            try{
                String str="select * from users where username=? and userpassword=?";
                cursor = db.rawQuery(str, new String []{name,pwd});
                if(cursor.getCount()<=0){
                    new AlertDialog.Builder(Login.this).setTitle("错误")
                            .setMessage("帐号或密码错误！").setPositiveButton("确定", null)
                            .show();
                    return false;
                }else{
                    new AlertDialog.Builder(Login.this).setTitle("正确")
                            .setMessage("成功登录").setPositiveButton("确定", new DialogInterface.OnClickListener(){


                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent thirdIntent=new Intent();
                            thirdIntent.setClass(Login.this,ThirdActivity.class);

                            startActivity(thirdIntent);
                        }
                    })
                            .show();

                    return true;
                }
               // cursor.close();

            }catch(SQLiteException e){
                e.printStackTrace();
            }finally{
                if(cursor!=null)
                    cursor.close();
            }


            return false;
        }

    }

    //关闭数据库
    protected void onDestroy(){
        super.onDestroy();
        if(db!=null)
        {
            db.close();
        }

    }

}
