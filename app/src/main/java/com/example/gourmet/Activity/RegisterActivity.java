package com.example.gourmet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gourmet.MainActivity;
import com.example.gourmet.R;
import com.example.gourmet.db.Code;
import com.example.gourmet.db.GourmetDBHelper;

public class RegisterActivity extends AppCompatActivity {


    private String realCode;
    private GourmetDBHelper mGourmetDBHelper;
    private Button mBt_register_register;
    private EditText mEdit_register_username;
    private EditText mEdit_register_password_two;
    private EditText mEdit_register_phoneCodes;
    private ImageView mIv_register_showCode;
    private ImageView mIv_register_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        initView();
        mGourmetDBHelper = new GourmetDBHelper(this);
        //将验证码用图片的形式显示出来
        mIv_register_showCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        initData();
    }

    private void initData() {
        mBt_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的用户名、密码、验证码
                String username = mEdit_register_username.getText().toString().trim();
                String password = mEdit_register_password_two.getText().toString().trim();
                String phoneCode = mEdit_register_phoneCodes.getText().toString().toLowerCase();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode) ) {
                    if (phoneCode.equals(realCode)) {
                        //将用户名和密码加入到数据库中
                        mGourmetDBHelper.add(username, password);
                       startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        finish();
                        Toast.makeText(RegisterActivity.this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册成功，点击返回到登录界面
        mIv_register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBt_register_register = findViewById(R.id.bt_register_register);
        mEdit_register_username = findViewById(R.id.edit_register_username);
        mEdit_register_password_two = findViewById(R.id.edit_register_password_two);
        mEdit_register_phoneCodes = findViewById(R.id.edit_register_phoneCodes);
        mIv_register_showCode = findViewById(R.id.iv_register_showCode);
        mIv_register_back = findViewById(R.id.iv_register_back);

    }
}
