package com.example.ujikom.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.model.transaksi.ResponseTransaksi;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    @BindView(R.id.spinStatus)
    Spinner spinStatus;
    @BindView(R.id.edtNominal)
    EditText edtNominal;
    @BindView(R.id.edtKeterangan)
    EditText edtKeterangan;
    @BindView(R.id.buttonSave)
    Button buttonSave;

    String[] statusItem = {"Masuk", "Keluar"};

    public static String uang;
    int hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statusItem);
        spinStatus.setAdapter(adapter);

        uang = DetailSiswaActivity.uang;
    }

    @OnClick(R.id.buttonSave)
    public void onViewClicked() {

        Intent intent = getIntent();
        String id_santri = intent.getStringExtra("id");
        String status = spinStatus.getSelectedItem().toString();
        String keterangan = edtKeterangan.getText().toString();
        String nominal = edtNominal.getText().toString();

        int uangku = Integer.parseInt(uang);
        int nominalku = Integer.parseInt(nominal);
        Log.d("nominalkoe", ""+nominalku);

        if (status.equals("Masuk")){
            hasil = uangku + nominalku;
        }else{
            hasil = uangku - nominalku;
        }

        String hasilku = Integer.toString(hasil);

        if (TextUtils.isEmpty(nominal) || TextUtils.isEmpty(keterangan)) {
            Toast.makeText(this, "The fill cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            tambahTransaksi(hasilku, id_santri, status, nominal, keterangan);
        }
    }

    private void tambahTransaksi(String uang, String id_santri, String status, String nominal, String keterangan) {
        ApiClient.service.responseTransaksi(uang, id_santri, status, nominal, keterangan).enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(TransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(TransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                Toast.makeText(TransaksiActivity.this, "Failed on Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
