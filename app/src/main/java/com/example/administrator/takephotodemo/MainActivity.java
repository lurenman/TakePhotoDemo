package com.example.administrator.takephotodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_click= (TextView) findViewById(R.id.tv_click);
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==tv_click)
        {
            Intent intent=new Intent(MainActivity.this,PhotoActivity.class);
            startActivity(intent);
        }
    }
}
