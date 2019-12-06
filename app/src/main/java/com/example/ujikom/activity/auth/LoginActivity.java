package com.example.ujikom.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.Util.SaveSharedPreference;
import com.example.ujikom.activity.MainActivity;
import com.example.ujikom.activity.admin.AdminActivity;
import com.example.ujikom.activity.home.HomeActivity;
import com.example.ujikom.activity.profile.ProfileActivity;
import com.example.ujikom.model.login.ResponseLogin;
import com.example.ujikom.network.ApiClient;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static com.example.ujikom.Util.PreferencesUtility.ID_PREF;
import static com.example.ujikom.Util.PreferencesUtility.KELAS_PREF;
import static com.example.ujikom.Util.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.ujikom.Util.PreferencesUtility.NAMA_PREF;
import static com.example.ujikom.Util.PreferencesUtility.USERNAME_PREF;

public class LoginActivity extends AppCompatActivity {

    public static String id;
    public static String nama;
    public static String kelas;

    public static String username;
    public static String passwords;

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtRegister)
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext(), LOGGED_IN_PREF)) {
            id = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), ID_PREF);
            nama = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), NAMA_PREF);
            kelas = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), KELAS_PREF);
            username = SaveSharedPreference.getLoggedStatusString(getApplicationContext(), USERNAME_PREF);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick({R.id.btnLogin, R.id.txtRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                passwords = password;

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    userLogin(username, password);
                }

                break;
            case R.id.txtRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void userLogin(String paramUsername, String paramPassword) {
        ApiClient.service.responseLogin(paramUsername, paramPassword).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin user = response.body();

                if (user.getResult().equalsIgnoreCase("1")) {
                    String message = response.body().getMsg();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    id = user.getUser().getIdUser();
                    nama = user.getUser().getNama();
                    kelas = user.getUser().getKelas();
                    username = user.getUser().getVsusername();

                    Log.d("isi login", nama+" dan "+kelas);

                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true, kelas, username, nama, id);

                    if (kelas.equals("-")){
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

