package com.example.ujikom.adapter.adminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.model.getAdminSantri.ResponseGetAllSantri;

import java.util.ArrayList;
import java.util.List;

public class AdminSantriAdapter extends RecyclerView.Adapter<AdminSantriAdapter.ViewHolder> {
    Context context;
    List<ResponseGetAllSantri> dataGetAllSantri;

    public AdminSantriAdapter(Context context, ArrayList<ResponseGetAllSantri> dataGetAllSantri){
        this.context = context;
        this.dataGetAllSantri = dataGetAllSantri;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_siswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String namaSantri = dataGetAllSantri.get(position).getNamaSantri();
        String kelasSantri = dataGetAllSantri.get(position).getKelas();

        holder.txtNama.setText(namaSantri);
        holder.txtKelas.setText(kelasSantri);

    }

    @Override
    public int getItemCount() {
        return dataGetAllSantri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtKelas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNamaSiswa);
            txtKelas = itemView.findViewById(R.id.txtItemKelas);
        }
    }
}
