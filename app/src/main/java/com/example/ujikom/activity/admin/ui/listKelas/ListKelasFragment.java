package com.example.ujikom.activity.admin.ui.listKelas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ujikom.R;
import com.example.ujikom.adapter.adminAdapter.AdminKelasAdapter;
import com.example.ujikom.model.getKelas.ResponseGetKelas;
import com.example.ujikom.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKelasFragment extends Fragment {

    ArrayList<ResponseGetKelas> data;

    RecyclerView recyclerKelas;
    FloatingActionButton btnTambahKelas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_kelas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerKelas = view.findViewById(R.id.recyclerKelas);
        btnTambahKelas = view.findViewById(R.id.btnTambahKelas);

        btnTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TambahKelasActivity.class));
            }
        });

        getReadKelas();
    }

    private void getReadKelas() {
        ApiClient.service.responseGetKelas().enqueue(new Callback<ArrayList<ResponseGetKelas>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetKelas>> call, Response<ArrayList<ResponseGetKelas>> response) {
                if (response.code() == 200){
                    data = response.body();
                    Log.d("dataKelasAdmin", ""+data);

                    if (data == null){
                        Toast.makeText(getActivity(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }else{
                        recyclerKelas.setHasFixedSize(true);
                        recyclerKelas.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerKelas.setAdapter(new AdminKelasAdapter(getActivity(), data));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetKelas>> call, Throwable t) {

            }
        });
    }
}
