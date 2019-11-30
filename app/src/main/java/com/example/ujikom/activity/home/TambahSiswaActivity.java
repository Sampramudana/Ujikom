package com.example.ujikom.activity.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        if (TextUtils.isEmpty(namaSiswa)) {
            Toast.makeText(this, "The fill cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            tambahSiswa(namaSiswa);
        }

    }

    private void tambahSiswa(String paramNamaSiswa) {
//        ApiClient
    }
}
