package com.example.ujikom.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.model.getKelas.ResponseGetKelas;
import com.example.ujikom.model.register.ResponseRegister;
import com.example.ujikom.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edtRegisterName)
    EditText edtRegisterName;
    @BindView(R.id.edtRegisterUsername)
    EditText edtRegisterUsername;
    @BindView(R.id.edtRegisterPassword)
    EditText edtRegisterPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    @BindView(R.id.spinClass)
    Spinner spinClass;

    ArrayList<ResponseGetKelas> dataKelas = null;

    public String kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initSpinnerKelas();

        spinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kelas = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick({R.id.btnRegister, R.id.txtLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:

                String nama = edtRegisterName.getText().toString();
                kelas = spinClass.getSelectedItem().toString();
                String username = edtRegisterUsername.getText().toString();
                String password = edtRegisterPassword.getText().toString();

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "The fill cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    userRegister(nama, kelas, username, password);
                }

                break;
            case R.id.txtLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void initSpinnerKelas() {
        ApiClient.service.responseGetKelas().enqueue(new Callback<ArrayList<ResponseGetKelas>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetKelas>> call, Response<ArrayList<ResponseGetKelas>> response) {
                if (response.code() == 200){
                    dataKelas = response.body();
                    Log.d("dataKelas", ""+dataKelas);

                    if (dataKelas == null){
                        Toast.makeText(RegisterActivity.this, "kelas null", Toast.LENGTH_SHORT).show();
                    }else{
                        List<String> listSpinner = new ArrayList<>();
                        for (int i = 0; i < dataKelas.size(); i++){
                            listSpinner.add(dataKelas.get(i).getKelas());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinClass.setAdapter(adapter);
                        Log.d("dataSpinner", ""+listSpinner);

                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetKelas>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "gagal onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void userRegister(String paramNama, String paramKelas, String paramUsername, String paramPassword) {
        ApiClient.service.responseRegister(paramNama, paramKelas, paramUsername, paramPassword).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else if (status.equalsIgnoreCase("0")) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Failed OnFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
