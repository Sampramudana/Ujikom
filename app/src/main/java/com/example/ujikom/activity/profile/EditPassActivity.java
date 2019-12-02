package com.example.ujikom.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.activity.MainActivity;
import com.example.ujikom.activity.auth.LoginActivity;
import com.example.ujikom.model.updateuser.ResponseUpdateUser;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPassActivity extends AppCompatActivity {

    @BindView(R.id.editUsername)
    EditText editUsername;
    @BindView(R.id.editPassUser)
    EditText editPassUser;
    @BindView(R.id.btnSaveEdit)
    Button btnSaveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSaveEdit)
    public void onViewClicked() {

        String username = editUsername.getText().toString();
        String password = editPassUser.getText().toString();
        String nama = LoginActivity.nama;
        String kelas = LoginActivity.kelas;

        updateUser(nama, username, password, kelas);
    }

    private void updateUser(String nama, String username, String password, String kelas) {

        String id = LoginActivity.id;

        ApiClient.service.responseUpdateUser(id, nama, username, password, kelas).enqueue(new Callback<ResponseUpdateUser>() {
            @Override
            public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (message.equalsIgnoreCase("update sukses")){
                        Toast.makeText(EditPassActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditPassActivity.this, MainActivity.class));
                        finish();
                    } else if (status.equalsIgnoreCase("0")){
                        Toast.makeText(EditPassActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateUser> call, Throwable t) {
                Toast.makeText(EditPassActivity.this, "Gagal onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
