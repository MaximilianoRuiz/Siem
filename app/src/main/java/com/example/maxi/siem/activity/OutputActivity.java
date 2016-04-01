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

public class OutputActivity extends AppCompatActivity {

    private TextView tvEntrada, tvSalida, tvConsumo, tvSaldoAPagar, tvSaldo;

    private Usuario usuario;
    private Bundle bundle;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_salida);
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
        tvEntrada = (TextView) findViewById(R.id.tvEntrada);
        tvSalida = (TextView) findViewById(R.id.tvSalida);
        tvConsumo = (TextView) findViewById(R.id.tvConsumo);
        tvSaldoAPagar = (TextView) findViewById(R.id.tvSaldoAPagar);
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);

        setValues();
    }

    private void setValues() {
        String localTime = FormatUtils.getLocalTime();
        String costoHora = getResources().getString(R.string.costo_hora);
        String consumoHoras = CalculateUtils.getConsumoHoras(usuario.getEntrada(), localTime);
        String consumo = CalculateUtils.getConsumo(consumoHoras, costoHora);
        String saldo = CalculateUtils.getSaldo(usuario.getSaldo(), consumo);

        tvEntrada.setText("Entrada: " + usuario.getEntrada() + "hs");
        tvSalida.setText("Salida: " + localTime + "hs");
        tvConsumo.setText(consumoHoras + " hs");
        tvSaldoAPagar.setText("$" + FormatUtils.getDoubleFormat(consumo));
        tvSaldo.setText(FormatUtils.getDoubleFormat(saldo));

        firebase.child("usuario1/salida").setValue(localTime);
        firebase.child("usuario1/saldo").setValue(saldo);
    }
}
