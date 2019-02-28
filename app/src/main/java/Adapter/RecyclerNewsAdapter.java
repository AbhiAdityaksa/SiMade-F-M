package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simadeui.R;

import java.lang.reflect.Array;
import java.util.List;

import Model.News;

public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.MyViewHolder> {

    private Context nContext;
//    private List<News> nData;
    private List<String> nData;
    private onClickListener onClickListener;
    private String date = "Sabtu, 2 Februari 2019";
    private String title = "Lorem ipsum";
    private String berita = "Aliquam quis faucibus tellus, ac ultrices eros. Nulla facilisi. Praesent aliquet dui eros, id ullamcorper odio ultricies nec. Phasellus in nibh molestie, luctus turpis eu, cursus felis. Phasellus viverra nisi felis, vitae elementum ex ornare sit amet. Integer ut arcu elit. Suspendisse urna purus, hendrerit vel mi ac, dignissim rutrum orci. Nullam sodales convallis sem in ullamcorper. In eleifend lacus non tincidunt porta.";

    public RecyclerNewsAdapter(Context nContext, List<String> nData){
        this.nContext = nContext;
        this.nData = nData;
    }

    public interface onClickListener{
        void onClick(int posisition);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
//        final News news = nData.get(i);
        String data = nData.get(i);
//        myViewHolder.tv_date.setText(date);
//        myViewHolder.tv_title.setText(title);
        myViewHolder.tv_news.setText(berita);

    }

    @Override
    public int getItemCount() {
        return nData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_title, tv_news;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date_news);
            tv_title = itemView.findViewById(R.id.tv_title_news);
            tv_news = itemView.findViewById(R.id.tv_news);
        }
    }

    public void setOnClickListener(RecyclerNewsAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
