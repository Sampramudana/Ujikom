package com.example.ujikom.activity.admin.ui.listSantri;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ujikom.R;
import com.example.ujikom.adapter.adminAdapter.AdminSantriAdapter;
import com.example.ujikom.model.getAdminSantri.ResponseGetAllSantri;
import com.example.ujikom.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSantriFragment extends Fragment {

    private ArrayList<ResponseGetAllSantri> data = null;

    RecyclerView recyclerAllSantri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_santri_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerAllSantri = view.findViewById(R.id.recyclerAllSiswa);

        getReadAllSiswa();

    }

    private void getReadAllSiswa() {
        ApiClient.service.responseGetAllSantri().enqueue(new Callback<ArrayList<ResponseGetAllSantri>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetAllSantri>> call, Response<ArrayList<ResponseGetAllSantri>> response) {
                if (response.code() == 200){
                    data = response.body();

                    if (data == null){
                        Toast.makeText(getActivity(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }else {
                        recyclerAllSantri.setHasFixedSize(true);
                        recyclerAllSantri.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerAllSantri.setAdapter(new AdminSantriAdapter(getActivity(), data));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetAllSantri>> call, Throwable t) {

            }
        });
    }
}
