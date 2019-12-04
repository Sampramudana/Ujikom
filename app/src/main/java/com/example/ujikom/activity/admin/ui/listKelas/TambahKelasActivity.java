package com.example.ujikom.activity.admin.ui.listKelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.activity.admin.AdminActivity;
import com.example.ujikom.model.tambahKelas.ResponseTambahKelas;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKelasActivity extends AppCompatActivity {

    @BindView(R.id.spinAddKelas1)
    Spinner spinAddKelas1;
    @BindView(R.id.spinAddKelas2)
    Spinner spinAddKelas2;
    @BindView(R.id.spinAddKelas3)
    Spinner spinAddKelas3;
    @BindView(R.id.kelas)
    TextView kelas;
    @BindView(R.id.btnTambahKelasAdmin)
    Button btnTambahKelasAdmin;

    String spinKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
        ButterKnife.bind(this);

        String[] class1 = {"7", "8", "9", "10", "11", "12"};
        String[] class2 = {" SMP", " RPL", " TKJ"};
        String[] class3 = {" A", " B", " C", " D", " E", " F", " G"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, class1);
        spinAddKelas1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, class2);
        spinAddKelas2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, class3);
        spinAddKelas3.setAdapter(adapter3);


        spinAddKelas1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinKelas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAddKelas2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinKelas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAddKelas3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinKelas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void spinKelas() {
        String spin1 = spinAddKelas1.getSelectedItem().toString();
        String spin2 = spinAddKelas2.getSelectedItem().toString();
        String spin3 = spinAddKelas3.getSelectedItem().toString();
        spinKelas = spin1+spin2+spin3;
        kelas.setText(spinKelas);
    }

    @OnClick(R.id.btnTambahKelasAdmin)
    public void onViewClicked() {
        ApiClient.service.responseTambahKelas(spinKelas).enqueue(new Callback<ResponseTambahKelas>() {
            @Override
            public void onResponse(Call<ResponseTambahKelas> call, Response<ResponseTambahKelas> response) {
                if (response.code() == 200){
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")){
                        Toast.makeText(TambahKelasActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahKelasActivity.this, AdminActivity.class));
                        finish();
                    }else{
                        Toast.makeText(TambahKelasActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahKelas> call, Throwable t) {

            }
        });
    }
}
