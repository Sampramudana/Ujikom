package com.example.ujikom.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.adapter.UangAdapter;
import com.example.ujikom.model.getuang.ResponseGetUang;
import com.example.ujikom.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSiswaActivity extends AppCompatActivity {

    @BindView(R.id.textNamaSantri)
    TextView textNamaSantri;
    @BindView(R.id.txtJumlahUang)
    TextView txtJumlahUang;
    @BindView(R.id.recyclerHistory)
    RecyclerView recyclerHistory;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingButton;

    public static String uang;
    private ArrayList<ResponseGetUang> data = null;
    String id_santri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("namasantri");
        id_santri = intent.getStringExtra("id");

        textNamaSantri.setText(nama);

        getReadUang(id_santri);
    }

    private void getReadUang(String id_santri) {
        ApiClient.service.responseGetUang(id_santri).enqueue(new Callback<ArrayList<ResponseGetUang>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetUang>> call, Response<ArrayList<ResponseGetUang>> response) {

                if (response.code() == 200) {
                    data = response.body();

                    if (data == null) {
                        Toast.makeText(DetailSiswaActivity.this, "Data NULL", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!data.isEmpty()) {
                            uang = data.get(0).getUang();
                            txtJumlahUang.setText(uang);
                        } else {
                            uang = "0";
                            txtJumlahUang.setText(uang);
                        }

                        recyclerHistory.setHasFixedSize(true);
                        recyclerHistory.setLayoutManager(new LinearLayoutManager(DetailSiswaActivity.this));
                        recyclerHistory.setAdapter(new UangAdapter(DetailSiswaActivity.this, data));

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetUang>> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.floatingButton)
    public void onViewClicked() {

        Intent intent = new Intent(this, TransaksiActivity.class);
        intent.putExtra("id", id_santri);
        this.startActivity(intent);
    }
}
