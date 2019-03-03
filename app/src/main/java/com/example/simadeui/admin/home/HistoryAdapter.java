package com.example.simadeui.admin.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simadeui.R;

import java.util.List;

import Helper.DateFormated;
import Model.HistoryResponse;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<HistoryResponse> historyResponseList;

    public HistoryAdapter(Context context, List<HistoryResponse> historyResponseList) {
        this.context = context;
        this.historyResponseList = historyResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HistoryResponse historyResponse = historyResponseList.get(i);
        viewHolder.bind(historyResponse);
    }

    @Override
    public int getItemCount() {
        return historyResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAdmin, tvUser, tvTgl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdmin = itemView.findViewById(R.id.nama_admin);
            tvUser = itemView.findViewById(R.id.nama_warga);
            tvTgl = itemView.findViewById(R.id.tgl_history);
        }

        public void bind(HistoryResponse historyResponse) {
            tvAdmin.setText(historyResponse.getNamaAdmin());
            tvUser.setText(historyResponse.getNamaUser());
            tvTgl.setText(DateFormated.setTglHistory(historyResponse.getCreatedAt()));
        }
    }
}
