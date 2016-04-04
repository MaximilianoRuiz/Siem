package com.example.maxi.siem.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxi.siem.R;
import com.example.maxi.siem.dialog.InputDialog;
import com.example.maxi.siem.dialog.OutputDialog;
import com.example.maxi.siem.vo.Usuario;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class PrincipalFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4;
    private LinearLayout llFirstButtons, llSedondButtons, llLastButtons;
    private ProgressBar pbLoad;
    private TextView tvLoad;

    private Intent intent;
    private Firebase firebase;
    private Usuario usuario;
    private SharedPreferences prefs;

    private boolean estacionado;

    public PrincipalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        initValues();

        initView(view);

        return view;
    }

    private void initValues() {
        firebase = new Firebase(getResources().getString(R.string.firebase_url));

        firebase.child("usuario1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
                pbLoad.setVisibility(View.GONE);
                tvLoad.setVisibility(View.GONE);
                llFirstButtons.setVisibility(View.VISIBLE);
                llSedondButtons.setVisibility(View.VISIBLE);
                llLastButtons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        prefs = getActivity().getSharedPreferences("StatusApp", Context.MODE_PRIVATE);

        estacionado = prefs.getBoolean("estacionado", false);

    }

    private void initView(View view) {
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);

        llFirstButtons = (LinearLayout) view.findViewById(R.id.llFirstButtons);
        llSedondButtons = (LinearLayout) view.findViewById(R.id.llSecondButtons);
        llLastButtons = (LinearLayout) view.findViewById(R.id.llLastButtons);
        pbLoad = (ProgressBar) view.findViewById(R.id.pbLoad);
        tvLoad = (TextView) view.findViewById(R.id.tvLoad);

        btn1.setEnabled(!estacionado);
        btn2.setEnabled(estacionado);

        addActionListeners();
    }

    private void addActionListeners() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog inputDialog = new InputDialog(usuario);
                inputDialog.show(getFragmentManager(), "Dialog");
                saveStatus(true);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputDialog outputDialog = new OutputDialog(usuario);
                outputDialog.show(getFragmentManager(), "Dialog");
                saveStatus(false);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inactiveButtonMessage(getView());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inactiveButtonMessage(getView());
            }
        });
    }

    public void inactiveButtonMessage(View view) {
        Toast.makeText(getContext(), "Programadores Trabajando ...", Toast.LENGTH_LONG).show();
    }

    private void saveStatus(boolean status) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("estacionado", status);
        editor.commit();

        btn1.setEnabled(!status);
        btn2.setEnabled(status);
    }
}
