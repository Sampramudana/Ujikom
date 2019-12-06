package com.example.ujikom.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;
import com.example.ujikom.activity.MainActivity;
import com.example.ujikom.activity.auth.LoginActivity;
import com.example.ujikom.activity.auth.RegisterActivity;
import com.example.ujikom.model.getKelas.ResponseGetKelas;
import com.example.ujikom.model.updateUser.ResponseUpdateUser;
import com.example.ujikom.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

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

    ArrayList<ResponseGetKelas> dataKelas = null;

    public String kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        String nama = LoginActivity.nama;
        editNamaUser.setText(nama);

        initSpinnerKelas();

        spinEditClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kelas = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        String nama = editNamaUser.getText().toString();
        kelas = spinEditClass.getSelectedItem().toString();
        String username = LoginActivity.username;
        String password = LoginActivity.passwords;

        updateUser(nama, username, password, kelas);
    }

    private void initSpinnerKelas() {
        ApiClient.service.responseGetKelas().enqueue(new Callback<ArrayList<ResponseGetKelas>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGetKelas>> call, Response<ArrayList<ResponseGetKelas>> response) {
                if (response.code() == 200){
                    dataKelas = response.body();
                    Log.d("dataKelas", ""+dataKelas);

                    if (dataKelas == null){
                        Toast.makeText(EditProfileActivity.this, "kelas null", Toast.LENGTH_SHORT).show();
                    }else{
                        List<String> listSpinner = new ArrayList<>();
                        for (int i = 0; i < dataKelas.size(); i++){
                            listSpinner.add(dataKelas.get(i).getKelas());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(EditProfileActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinEditClass.setAdapter(adapter);
                        Log.d("dataSpinner", ""+listSpinner);

                    }
                }else{
                    Toast.makeText(EditProfileActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGetKelas>> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "gagal onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(String nama, String username, String password, String kelas) {

        String id = LoginActivity.id;

        ApiClient.service.responseUpdateUser(id, nama, username, password, kelas).enqueue(new Callback<ResponseUpdateUser>() {
            @Override
            public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {

                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1")){
                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                        finish();
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
