package com.example.ujikom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujikom.R;
import com.example.ujikom.model.getuang.ResponseGetUang;

import java.util.ArrayList;
import java.util.List;

public class UangAdapter extends RecyclerView.Adapter<UangAdapter.ViewHolder> {

    Context context;
    List<ResponseGetUang> dataGetUang;

    public UangAdapter(Context context, ArrayList<ResponseGetUang> dataGetUang) {
        this.context = context;
        this.dataGetUang = dataGetUang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String tanggal = dataGetUang.get(position).getCreated();
        String status = dataGetUang.get(position).getStatusUang();
        String selisihUang = dataGetUang.get(position).getSelisihUang();
        String keterangan = dataGetUang.get(position).getKeterangan();

        holder.txtTanggal.setText(tanggal);
        holder.txtStatus.setText(status);
        holder.txtSelisihUang.setText("Selisih uang = ".concat(selisihUang) );
        holder.txtKeterangan.setText(keterangan);

        if (status.equals("Masuk")) {
            holder.txtStatus.setTextColor(Color.GREEN);
        } else if (status.equals("Keluar")) {
            holder.txtStatus.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return dataGetUang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtStatus, txtSelisihUang, txtKeterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtSelisihUang = itemView.findViewById(R.id.txtSelisihUang);
            txtKeterangan = itemView.findViewById(R.id.txtKeterangan);
        }
    }
}
