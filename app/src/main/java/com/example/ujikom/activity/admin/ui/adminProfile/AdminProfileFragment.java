package com.example.ujikom.activity.admin.ui.adminProfile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ujikom.R;
import com.example.ujikom.activity.auth.LoginActivity;

public class AdminProfileFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogoutAdmin = view.findViewById(R.id.btnLogoutAdmin);

        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder .setTitle("Peringatan")
                        .setMessage("Anda Yakin ingin Logout")
                        .setPositiveButton("Iya", (dialog, which) -> {
                           startActivity(new Intent(getActivity(), LoginActivity.class));
                           getActivity().finish();
                        })
                        .setNegativeButton("Tidak", (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.create();
                builder.show();
            }
        });
    }
}
