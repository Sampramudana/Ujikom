package com.example.ujikom.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.model.register.ResponseRegister;
import com.example.ujikom.network.ApiClient;

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

    String[] classItem = {"12 RPL A", "12 RPL B", "12 TKJ A", "12 TKJ B", "12 TKJ C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classItem);
        spinClass.setAdapter(adapter);
    }

    @OnClick({R.id.btnRegister, R.id.txtLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:

                String nama = edtRegisterName.getText().toString();
                String kelas = spinClass.getSelectedItem().toString();
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
