package com.example.maxi.siem.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maxi.siem.R;

public class LateralMenuFragment extends Fragment {

    private ImageView ivUser;
    private ListView lvPrincipalMenu, lvSecondaryMenu;

    private String[] principalMenu;
    private String[] secondaryMenu;

    public LateralMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lateral_menu, container, false);

        initValues();

        initWidget(view);

        return view;
    }

    private void initValues() {
        Resources res = getResources();

        principalMenu = res.getStringArray(R.array.principal_menu);
        secondaryMenu = res.getStringArray(R.array.secondary_menu);
    }

    private void initWidget(View view) {
        ivUser = (ImageView) view.findViewById(R.id.ivUser);
        lvPrincipalMenu = (ListView) view.findViewById(R.id.lvPrincipalMenu);
        lvSecondaryMenu = (ListView) view.findViewById(R.id.lvSecondaryMenu);

        addAdapters();
    }

    private void addAdapters() {
        lvPrincipalMenu.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, principalMenu));
        lvSecondaryMenu.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, secondaryMenu));

        addActionListeners();
    }

    private void addActionListeners() {
        lvPrincipalMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                inactiveButtonMessage();
            }
        });

        lvSecondaryMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                inactiveButtonMessage();
            }
        });
    }

    private void inactiveButtonMessage() {
        Toast.makeText(getContext(), "Programadores Trabajando ...", Toast.LENGTH_LONG).show();
    }
}
