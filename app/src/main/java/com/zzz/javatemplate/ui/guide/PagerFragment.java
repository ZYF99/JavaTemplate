package com.zzz.javatemplate.ui.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.zzz.javatemplate.R;
import com.zzz.javatemplate.ui.constellation.ConstellationActivity;
import com.zzz.javatemplate.ui.news.NewsActivity;
import com.zzz.javatemplate.util.GlideUtil;

public class PagerFragment extends Fragment {


    private Context context;
    private int pageNum;
    private Boolean isLast;

    public PagerFragment(Context context, int pageNum, Boolean isLast) {
        this.pageNum = pageNum;
        this.context = context;
        this.isLast = isLast;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_pager, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayout llBtn = view.findViewById(R.id.ll_btn);
        ImageView bgImage = view.findViewById(R.id.img_bg);
        TextView btnNews = view.findViewById(R.id.btn_news);
        TextView btnConstellation = view.findViewById(R.id.btn_constellation);

        GlideUtil.loadImageFromUrl(context,getImageUrl(pageNum),bgImage);

        if (isLast) {
            llBtn.setVisibility(View.VISIBLE);
        } else {
            llBtn.setVisibility(View.GONE);
        }
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToNewsActivity();
            }
        });
        btnConstellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToConstellationActivity();
            }
        });

    }

    private void jumpToNewsActivity() {
        Intent intent = new Intent(context, NewsActivity.class);
        startActivity(intent);
    }

    private void jumpToConstellationActivity() {
        Intent intent = new Intent(context, ConstellationActivity.class);
        startActivity(intent);
    }

    private String getImageUrl(int index) {
        switch (index) {
            case 0:
                return "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1659491271,2111150768&fm=26&gp=0.jpg";
            case 1:
                return "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=186268141,1523325197&fm=26&gp=0.jpg";
            case 2:
                return "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1856440416,1598862095&fm=26&gp=0.jpg";
            default:
                throw new RuntimeException("No this index of :" + index);
        }


    }

}
