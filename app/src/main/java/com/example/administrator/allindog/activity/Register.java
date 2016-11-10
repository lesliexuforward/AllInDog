package com.example.administrator.allindog.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.allindog.R;
import com.example.administrator.allindog.db.DatabaseHelper;
import com.example.administrator.allindog.db.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Register extends AppCompatActivity{
    //获取当前时间的参数
    Date currentDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //定义数据库
    private DatabaseHelper dbHelper;
    private EditText edName;
    private EditText edPassword;
    private EditText edPhone;
    private EditText edBir;
    private EditText edNote;

    private Button postData;

    private RadioGroup group;
    private RadioButton boyChoosed;
    private RadioButton girlChoosed;

    //定义来表示选中的性别
    private String choosedsex;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //还需进行界面设计，将注册的信息传入到数据库中
        dbHelper=new DatabaseHelper(this,Login.DatabaseName,null,Login.Database_version);
        //初始化控件
        edName=(EditText)findViewById(R.id.edname);
        edPassword=(EditText)findViewById(R.id.edpassword);
        edPhone=(EditText)findViewById(R.id.edphone);
        edBir=(EditText)findViewById(R.id.limitedDate);
        edNote=(EditText)findViewById(R.id.ednote);
        //选择性别
        group=(RadioGroup)findViewById(R.id.radioGroup);
        boyChoosed=(RadioButton)findViewById(R.id.boy);
        girlChoosed=(RadioButton)findViewById(R.id.girl);

        postData=(Button)findViewById(R.id.postdata);
        //响应性别选择
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton)findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                choosedsex=rb.getText().toString();
            }
        });

        //响应提交按钮
        postData.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String inputusername=edName.getText().toString();
                String inputpassword=edPassword.getText().toString();
                String inputphone=edPhone.getText().toString();
                String inputbir=edBir.getText().toString();
                String inputnote=edNote.getText().toString();
                int isboy=choosedsex.equals("男")?1:0;
                currentDate=new Date(System.currentTimeMillis());
                String inputDate=sdf.format(currentDate);

                User inputuser=new User(inputusername,inputpassword,inputphone,inputbir,inputnote ,isboy,inputDate);
                dbHelper.insertUser(inputuser);

                //插入数据
                /*
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", inputusername);
                values.put("userpassword", inputpassword);
                values.put("usersex", isboy);
                values.put("phonenumber", inputphone);
                values.put("birthday",inputbir);
                values.put("personaldesc",inputnote);
                //插入注册日期
                values.put("regitime",inputDate);
               db.insert("users", null, values);
               */
                /*
                用于测试的代码
              //  int test=(int)db.insert("users", null, values);
                Log.d("Register",inputusername);
                Log.d("Register",inputpassword);
                Log.d("Register",Integer.toString(isboy));
                Log.d("Register",inputphone);
                Log.d("Register",inputbir);
                Log.d("Register",inputnote);
                Log.d("Register",sdf.format(d));
                */
                Toast.makeText(Register.this,"register succeed!!",Toast.LENGTH_SHORT).show();


                startActivity(new Intent(Register.this,Login.class));
                finish();

            }
        });


    }
}
