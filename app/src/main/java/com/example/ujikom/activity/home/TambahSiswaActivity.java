package com.example.ujikom.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.activity.auth.LoginActivity;
import com.example.ujikom.model.tambahsantri.ResponseTambahSantri;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahSiswaActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaSiswa)
    EditText edtNamaSiswa;
    @BindView(R.id.btnTambahSiswa)
    Button btnTambahSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_siswa);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTambahSiswa)
    public void onViewClicked() {

        String namaSiswa = edtNamaSiswa.getText().toString();
        String kelas = LoginActivity.kelas;

        if (TextUtils.isEmpty(namaSiswa)) {
            Toast.makeText(this, "The fill cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            tambahSiswa(namaSiswa, kelas);
        }

    }

    private void tambahSiswa(String nama, String kelas) {
        ApiClient.service.responseTambahSantri(nama, kelas).enqueue(new Callback<ResponseTambahSantri>() {
            @Override
            public void onResponse(Call<ResponseTambahSantri> call, Response<ResponseTambahSantri> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(TambahSiswaActivity.this, pesan, Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(TambahSiswaActivity.this, HomeActivity.class));
                        finish();
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(TambahSiswaActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahSantri> call, Throwable t) {

            }
        });
    }
}
