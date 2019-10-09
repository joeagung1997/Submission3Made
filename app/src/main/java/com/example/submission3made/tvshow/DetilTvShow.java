package com.example.submission3made.tvshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.submission3made.R;
import com.example.submission3made.tvshow.model.DataTvShowBean;
import com.squareup.picasso.Picasso;

public class DetilTvShow extends AppCompatActivity {


    TextView tvJudulTv, tvDescTv, tvJamTayang;
    ImageView imageTv;
    DataTvShowBean tvShowBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_tv_show);



        tvJudulTv = (TextView) findViewById(R.id.judulTayanganTv);
        tvDescTv = (TextView) findViewById(R.id.tvDescTv);
        tvJamTayang = (TextView) findViewById(R.id.tv_respon_tanggal);

        imageTv = (ImageView)findViewById(R.id.imageTayanganTv);


//        Intent intent = getIntent();
//
//        DataTvShowBean tvShowBean = intent.getParcelableExtra("tvshow");
        tvShowBean = (DataTvShowBean) getIntent().getSerializableExtra("tvshow");


        String judul = tvShowBean.getName();

        String desc = tvShowBean.getOverview();
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tvShowBean.getPosterPath()).into(imageTv);

        String jadwal = tvShowBean.getFirstAirDate();



        tvJudulTv.setText(judul);
        tvDescTv.setText(desc);
        tvJamTayang.setText(jadwal);

    }
}
