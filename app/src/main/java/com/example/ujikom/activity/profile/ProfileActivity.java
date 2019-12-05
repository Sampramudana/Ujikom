package com.example.ujikom.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ujikom.R;
import com.example.ujikom.activity.auth.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView txtNamaUser = (TextView) view.findViewById(R.id.txtNamaUser);
        TextView txtNamaKelas = (TextView) view.findViewById(R.id.txtNamaKelas);
        Button btnEditUser = (Button) view.findViewById(R.id.btnEditUser);
        Button btnChangeUser = (Button) view.findViewById(R.id.btnChangeUser);
        Button btnLogout = (Button) view.findViewById(R.id.btnLogout);

        String nama = LoginActivity.nama;
        String kelas = LoginActivity.kelas;

        txtNamaUser.setText("Ust. " + nama);
        txtNamaKelas.setText(kelas);

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        btnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditPassActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}
