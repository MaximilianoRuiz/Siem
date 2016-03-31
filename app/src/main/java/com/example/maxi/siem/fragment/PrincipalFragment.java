package com.example.maxi.siem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.maxi.siem.R;
import com.example.maxi.siem.activity.InputActivity;
import com.example.maxi.siem.activity.OutputActivity;
import com.example.maxi.siem.vo.Usuario;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class PrincipalFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4;

    private Intent intent;
    private Firebase firebase;
    private Usuario usuario;

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
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }

    private void initView(View view) {
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);

        addActionListeners();
    }

    private void addActionListeners() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), InputActivity.class);
                intent.putExtra("usuario", usuario);
                if (usuario != null) {
                    startActivity(intent);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), OutputActivity.class);
                intent.putExtra("usuario", usuario);
                if (usuario != null) {
                    startActivity(intent);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inactiveButtonMessage();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inactiveButtonMessage();
            }
        });
    }

    private void inactiveButtonMessage() {
        Toast.makeText(getContext(), "Programadores Trabajando ...", Toast.LENGTH_LONG).show();
    }
}
