package com.example.ujikom.adapter.adminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.model.getKelas.ResponseGetKelas;

import java.util.ArrayList;
import java.util.List;

public class AdminKelasAdapter extends RecyclerView.Adapter<AdminKelasAdapter.ViewHolder> {
    Context context;
    List<ResponseGetKelas> dataKelas;

    public AdminKelasAdapter(Context context, ArrayList<ResponseGetKelas> dataKelas){
        this.context = context;
        this.dataKelas = dataKelas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String namaKelas = dataKelas.get(position).getKelas();

        holder.txtkelas.setText(namaKelas);

    }

    @Override
    public int getItemCount() {
        return dataKelas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtkelas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtkelas = itemView.findViewById(R.id.txtKelas);
        }
    }
}
