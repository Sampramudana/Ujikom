package com.example.ujikom.activity.profile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.activity.auth.LoginActivity;
import com.example.ujikom.model.updateuser.ResponseUpdateUser;
import com.example.ujikom.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.editNamaUser)
    EditText editNamaUser;
    @BindView(R.id.spinEditClass)
    Spinner spinEditClass;
    @BindView(R.id.btnSave)
    Button btnSave;

    String[] classItem = {"12 RPL A", "12 RPL B", "12 TKJ A", "12 TKJ B", "12 TKJ C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classItem);
        spinEditClass.setAdapter(adapter);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        String nama = editNamaUser.getText().toString();
        String kelas = spinEditClass.getSelectedItem().toString();
        String username = LoginActivity.username;
        String password = LoginActivity.password;

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
                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else if (status.equalsIgnoreCase("0")){
                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateUser> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Gagal onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
