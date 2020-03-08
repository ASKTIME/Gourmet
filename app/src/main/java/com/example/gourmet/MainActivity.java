package com.example.gourmet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gourmet.Activity.LoginActivity;

public class MainActivity extends AppCompatActivity{

    private Button mBtMainLogout;

    /**
     * 此类 implements View.OnClickListener 之后，
     * 就可以把onClick事件写到onCreate()方法之外
     * 这样，onCreate()方法中的代码就不会显得很冗余
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initEvent();
    }

    private void initEvent() {
      mBtMainLogout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (v.getId() == R.id.bt_main_logout) {
                  //从默认界面到登录界面
                  startActivity(new Intent(MainActivity.this,LoginActivity.class));
                  finish();
              }
          }
      });
    }

    private void initView() {
        //初始化控件对象
        mBtMainLogout = findViewById(R.id.bt_main_logout);

    }
}
