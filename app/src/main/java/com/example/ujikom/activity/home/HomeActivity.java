package com.example.ujikom.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.adapter.SiswaAdapter;
import com.example.ujikom.model.getsantri.ResponseGetSantri;
import com.example.ujikom.network.ApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {

    @BindView(R.id.searchBar)
    SearchView searchBar;
    @BindView(R.id.txtKelasHome)
    TextView txtKelasHome;
    @BindView(R.id.recyclerSiswa)
    RecyclerView recyclerSiswa;

    ArrayList<ResponseGetSantri> data = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getReadSiswa();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    private void getReadSiswa() {
        ApiClient.service.responseGetSantri().enqueue(new Callback<ArrayList<ResponseGetSantri>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetSantri>> call, Response<ArrayList<ResponseGetSantri>> response) {
                if (response.code() == 200) {
                    data = response.body();
                    if (data == null) {
                        Toast.makeText(getContext(), "Data NULL", Toast.LENGTH_SHORT).show();
                    } else {
                        recyclerSiswa.setHasFixedSize(true);
                        recyclerSiswa.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerSiswa.setAdapter(new SiswaAdapter(getContext(), data));
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
