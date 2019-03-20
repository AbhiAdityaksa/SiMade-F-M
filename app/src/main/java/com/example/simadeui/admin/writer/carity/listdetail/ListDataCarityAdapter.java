package com.example.simadeui.admin.writer.carity.listdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simadeui.R;

import java.util.List;

import Helper.CurrencyFormated;
import Helper.DateFormated;
import Model.DetailSumCarityResponse;

public class ListDataCarityAdapter extends RecyclerView.Adapter<ListDataCarityAdapter.ViewHolder> {

    private Context context;
    private List<DetailSumCarityResponse> detailSumCarityResponseList;
    private OnClickListener onClickListener;

    public ListDataCarityAdapter(Context context, List<DetailSumCarityResponse> detailSumCarityResponseList) {
        this.context = context;
        this.detailSumCarityResponseList = detailSumCarityResponseList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_data_penyumbang_admin_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DetailSumCarityResponse detailSumCarityResponse = detailSumCarityResponseList.get(i);
        viewHolder.bind(detailSumCarityResponse);
    }

    @Override
    public int getItemCount() {
        return detailSumCarityResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvName, tvNominal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_list_date_penyumbang);
            tvName = itemView.findViewById(R.id.tv_name_penyumbang);
            tvNominal = itemView.findViewById(R.id.tv_nominal_sumbangan);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(DetailSumCarityResponse detailSumCarityResponse) {
            tvTanggal.setText(DateFormated.setTglHistory(detailSumCarityResponse.getUpdatedAt()));
            tvName.setText(detailSumCarityResponse.getNamaWarga());
            int nominal = Integer.parseInt(detailSumCarityResponse.getNominal());
            String total = CurrencyFormated.toRupiah(nominal);
            tvNominal.setText(total);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
