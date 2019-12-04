package com.example.ujikom.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.activity.auth.LoginActivity;
import com.example.ujikom.adapter.SantriAdapter;
import com.example.ujikom.model.getSantri.ResponseGetSantri;
import com.example.ujikom.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {

    private ArrayList<ResponseGetSantri> data = null;

    private RecyclerView recyclerSiswa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchView searchBar = view.findViewById(R.id.searchBar);
        TextView txtKelasHome = view.findViewById(R.id.txtKelasHome);
        recyclerSiswa = view.findViewById(R.id.recyclerSiswa);

        String kelas = LoginActivity.kelas;

        Log.d("kelasHome", kelas);
        txtKelasHome.setText(kelas);
        getReadSiswa(kelas);

    }

    private void getReadSiswa(String kelas) {
        ApiClient.service.responseGetSantri(kelas).enqueue(new Callback<ArrayList<ResponseGetSantri>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetSantri>> call, Response<ArrayList<ResponseGetSantri>> response) {
                if (response.code() == 200) {
                    data = response.body();

                    Log.d("dataIndexSantri", ""+data);
                    if (data == null) {
                        Toast.makeText(getActivity(), "Data NULL", Toast.LENGTH_SHORT).show();
                    } else {
                        recyclerSiswa.setHasFixedSize(true);
                        recyclerSiswa.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerSiswa.setAdapter(new SantriAdapter(getActivity(), data));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetSantri>> call, Throwable t) {

            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(getActivity(), TambahSiswaActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
