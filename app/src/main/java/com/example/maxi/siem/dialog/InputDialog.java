package com.example.maxi.siem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import com.example.maxi.siem.R;
import com.example.maxi.siem.util.CalculateUtils;
import com.example.maxi.siem.util.FormatUtils;
import com.example.maxi.siem.vo.Usuario;
import com.firebase.client.Firebase;

public class InputDialog extends DialogFragment {

    private TextView tvHoraEntrada, tvSaldo, tvMaxHora, tvCostoHora;

    private Usuario usuario;
    private Firebase firebase;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.pantalla_entrada, null);
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
        usuario = (Usuario) getArguments().getSerializable("usuario");
        firebase = new Firebase(getResources().getString(R.string.firebase_url));
    }

    private void initWidget(View view) {
        tvHoraEntrada = (TextView) view.findViewById(R.id.tvHoraEntrada);
        tvSaldo = (TextView) view.findViewById(R.id.tvSaldo);
        tvMaxHora = (TextView) view.findViewById(R.id.tvMaxHora);
        tvCostoHora = (TextView) view.findViewById(R.id.tvCostoHora);

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