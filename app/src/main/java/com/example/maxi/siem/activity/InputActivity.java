package com.example.maxi.siem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.maxi.siem.R;
import com.example.maxi.siem.vo.Usuario;

public class InputActivity extends AppCompatActivity {

    private Usuario usuario;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_entrada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initValue();
    }

    private void initValue() {
        bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");
    }
}
