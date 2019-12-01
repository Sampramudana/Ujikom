package com.example.ujikom.activity.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ujikom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }
}
