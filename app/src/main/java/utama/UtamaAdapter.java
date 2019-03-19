package utama;

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

public class UtamaAdapter extends RecyclerView.Adapter<UtamaAdapter.ViewHolder> {

    private Context context;
    private List<VillageInfoResponse> villageInfoResponseList;
    private OnClickListener onClickListener;

    public UtamaAdapter(Context context, List<VillageInfoResponse> villageInfoResponseList) {
        this.context = context;
        this.villageInfoResponseList = villageInfoResponseList;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, viewGroup, false));
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
        TextView tvTanggal, tvName, tvCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_date_news);
            tvName = itemView.findViewById(R.id.tv_title_news);
            tvCat = itemView.findViewById(R.id.tv_news);
        }

        public void bind(VillageInfoResponse villageInfoResponse) {
            tvTanggal.setText(DateFormated.setTglHistory(villageInfoResponse.getCreatedAt()));
            tvName.setText(villageInfoResponse.getName());
            tvCat.setText(villageInfoResponse.getCategoryName());
            if (onClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
