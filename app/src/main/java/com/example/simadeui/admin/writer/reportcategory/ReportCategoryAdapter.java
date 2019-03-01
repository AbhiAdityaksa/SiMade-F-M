package com.example.simadeui.admin.writer.reportcategory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simadeui.R;

import java.util.List;

import Model.ReportCategory;

public class ReportCategoryAdapter extends RecyclerView.Adapter<ReportCategoryAdapter.ViewHolder> {

    private Context context;
    private OnClickListener onClickListener;
    private List<ReportCategory> reportCategoryList;

    public ReportCategoryAdapter(Context context, List<ReportCategory> reportCategoryList) {
        this.context = context;
        this.reportCategoryList = reportCategoryList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.report_category_admin_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ReportCategory reportCategory = reportCategoryList.get(i);
        viewHolder.bind(reportCategory);
    }

    @Override
    public int getItemCount() {
        return reportCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_report_category_date_admin);
            tvName = itemView.findViewById(R.id.tv_report_category_name_admin);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(ReportCategory reportCategory) {
            tvDate.setText(reportCategory.getUpdatedAt());
            tvName.setText(reportCategory.getName());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
