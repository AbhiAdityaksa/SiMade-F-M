package com.example.simadeui.admin.writer.villageinfo;

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
import Model.VillageInfoResponse;

public class VillageInfoAdapter extends RecyclerView.Adapter<VillageInfoAdapter.ViewHolder> {

    private Context context;
    private OnClickListener onClickListener;
    private List<VillageInfoResponse> villageInfoResponseList;

    public VillageInfoAdapter(Context context, List<VillageInfoResponse> villageInfoResponseList) {
        this.context = context;
        this.villageInfoResponseList = villageInfoResponseList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.info_desa_admin_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        VillageInfoResponse villageInfoResponse = villageInfoResponseList.get(i);
        viewHolder.bind(villageInfoResponse);
    }

    @Override
    public int getItemCount() {
        return villageInfoResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvNama, tvEtc;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_info_desa_date_admin);
            tvNama = itemView.findViewById(R.id.tv_info_desa_name_admin);
            tvEtc = itemView.findViewById(R.id.tv_info_desa_etc_admin);
            if (onClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(VillageInfoResponse villageInfoResponse) {
            tvTanggal.setText(DateFormated.setTglHistory(villageInfoResponse.getCreatedAt()));
            tvNama.setText(villageInfoResponse.getName());
            tvEtc.setText(villageInfoResponse.getEtc());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
