package com.zzz.javatemplate.ui.constellation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zzz.javatemplate.R;
import com.zzz.javatemplate.manager.RetrofitHelper;
import com.zzz.javatemplate.model.ConstellationResultModel;
import com.zzz.javatemplate.ui.news.NewsActivity;
import com.zzz.javatemplate.util.ThreadUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstellationActivity extends AppCompatActivity {

    private static final String[] constellationArray = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座",};

    TextView btnReChoose;

    AlertDialog chooseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);
        initView();
        initData();
    }

    private void initView() {
        btnReChoose = findViewById(R.id.btn_re_choose);
        final View alertDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_contellation_name, null);
        final Spinner spinner = alertDialogView.findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.cell_spinner, constellationArray));
        chooseDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("选择你的星座")
                .setView(alertDialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getConstellationData("today", constellationArray[spinner.getSelectedItemPosition()]);
                    }
                }).create();

        chooseDialog.show();

        btnReChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDialog.show();
            }
        });

    }

    private void initData() {

    }

    private void getConstellationData(final String type, final String name) {
        ThreadUtil.runOnIOThread(new Runnable() {
            @Override
            public void run() {
                RetrofitHelper.getInstance(RetrofitHelper.UrlType.CONSTELLATION)
                        .getConstellation(type, name).enqueue(new Callback<ConstellationResultModel>() {
                    @Override
                    public void onResponse(@NotNull Call<ConstellationResultModel> call, Response<ConstellationResultModel> response) {
                        onConstellationDataLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<ConstellationResultModel> call, Throwable t) {
                        Toast.makeText(ConstellationActivity.this, "请求失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void onConstellationDataLoaded(ConstellationResultModel constellationResultModel) {
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvTime = findViewById(R.id.tv_time);
        TextView tvPercent = findViewById(R.id.tv_percent);
        TextView tvColor = findViewById(R.id.tv_color);
        TextView tvHealthy = findViewById(R.id.tv_healthy);
        TextView tvLove = findViewById(R.id.tv_love);
        TextView tvMoney = findViewById(R.id.tv_money);
        TextView tvNum = findViewById(R.id.tv_num);
        TextView tvColl = findViewById(R.id.tv_coll);
        TextView tvDes = findViewById(R.id.tv_des);
        TextView tvWork = findViewById(R.id.tv_work);

        tvName.setText(constellationResultModel.getName());
        tvTime.setText(constellationResultModel.getDatetime());
        tvPercent.setText(String.valueOf(constellationResultModel.getAll()));
        tvColor.setText(constellationResultModel.getColor());
        tvHealthy.setText(String.valueOf(constellationResultModel.getHealth()));
        tvLove.setText(String.valueOf(constellationResultModel.getLove()));
        tvMoney.setText(String.valueOf(constellationResultModel.getMoney()));
        tvNum.setText(String.valueOf(constellationResultModel.getNumber()));
        tvColl.setText(constellationResultModel.getQFriend());
        tvDes.setText(constellationResultModel.getSummary());
        tvWork.setText(String.valueOf(constellationResultModel.getWork()));
    }

}
