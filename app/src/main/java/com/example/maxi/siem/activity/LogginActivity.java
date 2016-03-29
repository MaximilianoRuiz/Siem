package com.example.maxi.siem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.maxi.siem.R;

public class LogginActivity extends AppCompatActivity {

    private FloatingActionButton btnLoggin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initWidget();

        addListeners();
    }

    private void initWidget() {
        btnLoggin = (FloatingActionButton) findViewById(R.id.btnLoggin);
    }

    private void addListeners() {
        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
}
