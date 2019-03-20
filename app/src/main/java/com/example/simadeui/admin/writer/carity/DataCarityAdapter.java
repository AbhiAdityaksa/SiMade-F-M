package com.example.simadeui.admin.writer.carity;

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
import Model.SumCarityResponse;

public class DataCarityAdapter extends RecyclerView.Adapter<DataCarityAdapter.ViewHolder> {

    private Context context;
    private List<SumCarityResponse> sumCarityResponseList;
    private OnClickListener onClickListener;

    public DataCarityAdapter(Context context, List<SumCarityResponse> sumCarityResponseList) {
        this.context = context;
        this.sumCarityResponseList = sumCarityResponseList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.data_sumbangan_admin_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SumCarityResponse sumCarityResponse = sumCarityResponseList.get(i);
        viewHolder.bind(sumCarityResponse);
    }

    @Override
    public int getItemCount() {
        return sumCarityResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvName, tvTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_date_data_sumbangan_valid_admin);
            tvName = itemView.findViewById(R.id.tv_name_data_sumbangan_admin);
            tvTotal = itemView.findViewById(R.id.tv_total_carity_data_sumbangan_admin);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(SumCarityResponse sumCarityResponse) {
            tvTanggal.setText(DateFormated.setTgl(sumCarityResponse.getValid()));
            tvName.setText(sumCarityResponse.getName());
            int total = Integer.parseInt(sumCarityResponse.getTotalCarity());
            String total_carity = CurrencyFormated.toRupiah(total);
            tvTotal.setText(total_carity);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
