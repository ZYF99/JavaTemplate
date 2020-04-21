package com.zzz.javatemplate.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.zzz.javatemplate.R;
import com.zzz.javatemplate.model.NewsResultModel;
import com.zzz.javatemplate.util.GlideUtil;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NewsResultModel.News>newsList;
    private OnCellClickListener onCellClickListener;
    NewsAdapter(Context context,List<NewsResultModel.News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_news, parent, false);
        return new NewsViewHHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final NewsResultModel.News news = newsList.get(position);
        NewsViewHHolder newsHolder = (NewsViewHHolder)holder;
        newsHolder.tvTitle.setText(news.getTitle());
        newsHolder.tvAuthor.setText(news.getAuthor_name());
        newsHolder.tvTime.setText(news.getDate());
        GlideUtil.loadImageFromUrl(context,news.getThumbnail_pic_s(),newsHolder.imgForeground);
        newsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCellClickListener.onCellClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvTime;
        TextView tvAuthor;
        ImageView imgForeground;

        NewsViewHHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            tvTime = view.findViewById(R.id.tv_time);
            tvAuthor = view.findViewById(R.id.tv_author);
            imgForeground = view.findViewById(R.id.news_img);
        }
    }

    void replaceData(List<NewsResultModel.News> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    void setOnCellClickListener(OnCellClickListener onCellClickListener){
        this.onCellClickListener = onCellClickListener;
    }

    interface OnCellClickListener{
        void onCellClick(NewsResultModel.News news);
    }

}
