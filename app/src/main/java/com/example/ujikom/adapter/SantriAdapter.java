package com.example.ujikom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.activity.home.DetailSiswaActivity;
import com.example.ujikom.model.getSantri.ResponseGetSantri;

import java.util.ArrayList;
import java.util.List;

public class SantriAdapter extends RecyclerView.Adapter<SantriAdapter.ViewHolder> {

    Context context;
    List<ResponseGetSantri> dataGetSantri;

    public SantriAdapter(Context context, ArrayList<ResponseGetSantri> dataGetSantri) {
        this.context = context;
        this.dataGetSantri = dataGetSantri;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_siswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String namaSantri = dataGetSantri.get(position).getNamaSantri();
        String kelasSantri = dataGetSantri.get(position).getKelas();
        String idSantri = dataGetSantri.get(position).getId();

        holder.txtNamaSiswa.setText(namaSantri);
        holder.txtItemKelas.setText(kelasSantri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailSiswaActivity.class);
                intent.putExtra("id", idSantri);
                intent.putExtra("namasantri", namaSantri);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataGetSantri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaSiswa, txtItemKelas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaSiswa = itemView.findViewById(R.id.txtNamaSiswa);
            txtItemKelas = itemView.findViewById(R.id.txtItemKelas);
        }
    }
}
