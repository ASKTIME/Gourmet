package com.example.gourmet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gourmet.R;
import com.example.gourmet.db.GourmetDBHelper;
import com.example.gourmet.db.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    /**
     * 声明自己写的 DBOpenHelper 对象
     * DBOpenHelper(extends SQLiteOpenHelper) 主要用来
     * 创建数据表
     * 然后再进行数据表的增、删、改、查操作
     */

    private GourmetDBHelper mGourmetDBHelper;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private Button mRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        mGourmetDBHelper = new GourmetDBHelper(this);
        initData();
    }

    private void initData() {
        //登录监听
        /**
         * 登录验证：
         *
         * 从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
         *  String name = mUsername.getText().toString().trim();
         *  String password = mPassword.getText().toString().trim();
         *  进行匹配验证,先判断一下用户名密码是否为空，
         *  if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
         *  再进而for循环判断是否与数据库中的数据相匹配
         *  if (name.equals(user.getName()) && password.equals(user.getPassword()))
         *  一旦匹配，立即将match = true；break；
         *  否则 一直匹配到结束 match = false；
         *
         *  登录成功之后，进行页面跳转：
         *
         *  Intent intent = new Intent(this, MainActivity.class);
         *  startActivity(intent);
         *  finish();//销毁此Activity
         */
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mGourmetDBHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或者密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册监听
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        //初始化控件
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.register);
    }
}
