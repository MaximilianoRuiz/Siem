package com.example.maxi.siem.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxi.siem.R;
import com.example.maxi.siem.util.CalculateUtils;
import com.example.maxi.siem.util.FormatUtils;
import com.example.maxi.siem.vo.Usuario;
import com.firebase.client.Firebase;

public class OutputDialog extends DialogFragment {

    private TextView tvEntrada, tvSalida, tvConsumo, tvSaldoAPagar, tvSaldo;

    private Usuario usuario;
    private Firebase firebase;

    public OutputDialog(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.pantalla_salida, null);
        Builder builder = new Builder(getActivity());

        initValue();

        initWidget(view);

        builder.setView(view);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Aceptar", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

    private void initValue() {
        firebase = new Firebase(getResources().getString(R.string.firebase_url));
    }

    private void initWidget(View view) {
        tvEntrada = (TextView)view.findViewById(R.id.tvEntrada);
        tvSalida = (TextView) view.findViewById(R.id.tvSalida);
        tvConsumo = (TextView) view.findViewById(R.id.tvConsumo);
        tvSaldoAPagar = (TextView) view.findViewById(R.id.tvSaldoAPagar);
        tvSaldo = (TextView) view.findViewById(R.id.tvSaldo);

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
        tvSaldo.setText("$ " + FormatUtils.getDoubleFormat(saldo));

        firebase.child("usuario1/salida").setValue(localTime);
        firebase.child("usuario1/saldo").setValue(saldo);
    }
}