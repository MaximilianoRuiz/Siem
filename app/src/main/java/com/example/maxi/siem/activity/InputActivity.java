package com.example.maxi.siem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.maxi.siem.R;
import com.example.maxi.siem.util.CalculateUtils;
import com.example.maxi.siem.util.FormatUtils;
import com.example.maxi.siem.vo.Usuario;
import com.firebase.client.Firebase;

public class InputActivity extends AppCompatActivity {

    private TextView tvHoraEntrada, tvSaldo, tvMaxHora, tvCostoHora;

    private Usuario usuario;
    private Bundle bundle;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_entrada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initValue();

        initWidget();
    }

    private void initValue() {
        bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");

        firebase = new Firebase(getResources().getString(R.string.firebase_url));
    }

    private void initWidget() {
        tvHoraEntrada = (TextView) findViewById(R.id.tvHoraEntrada);
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);
        tvMaxHora = (TextView) findViewById(R.id.tvMaxHora);
        tvCostoHora = (TextView) findViewById(R.id.tvCostoHora);

        setValues();
    }

    private void setValues() {
        String localTime = FormatUtils.getLocalTime();
        String costoHora = getResources().getString(R.string.costo_hora);
        String maxHora = CalculateUtils.getMaxTiempo(usuario.getSaldo(), costoHora);

        tvHoraEntrada.setText(localTime + " hs");
        tvSaldo.setText("$ " + FormatUtils.getDoubleFormat(usuario.getSaldo()));
        tvMaxHora.setText(FormatUtils.getDoubleFormat(maxHora) + " hs");
        tvCostoHora.setText("Costo por hora: $ " + costoHora);

        firebase.child("usuario1/entrada").setValue(localTime);
    }
}
