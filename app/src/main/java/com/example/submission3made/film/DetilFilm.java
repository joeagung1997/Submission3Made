package com.example.submission3made.film;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.submission3made.R;
import com.example.submission3made.film.model.DataFilmBean;
import com.squareup.picasso.Picasso;

public class DetilFilm extends AppCompatActivity {

    TextView tvJudul, tvDesc, tvTanggal,tvRating;
    ImageView imageFilm;

    DataFilmBean dataFilmBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_film);



        tvJudul = (TextView) findViewById(R.id.judulFilm);
        tvDesc = (TextView) findViewById(R.id.descFilm);
        imageFilm = (ImageView)findViewById(R.id.imageFilm);

        tvTanggal = (TextView)findViewById(R.id.tv_respon_tanggal);
        tvRating = (TextView)findViewById(R.id.txt_respon_rating);


//        Intent intent = getIntent();
//        DataFilmBean dataFilmBean = intent.getParcelableExtra("film");

        dataFilmBean = (DataFilmBean) getIntent().getSerializableExtra("film");

        tvJudul.setText(dataFilmBean.getTitle());
        tvDesc.setText(dataFilmBean.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+dataFilmBean.getPosterPath()).into(imageFilm);
//        imageFilm.setImageResource(Integer.parseInt(dataFilmBean.getPosterPath()));
        tvTanggal.setText(dataFilmBean.getReleaseDate());
        tvRating.setText(String.valueOf(dataFilmBean.getVoteAverage()));

//
//        String judul = dataFilmBean.getTitle();
//
//        String desc = dataFilmBean.getOverview();
//        String image = String.valueOf(dataFilmBean.getPosterPath());
//        String tanggal = dataFilmBean.getReleaseDate();
//        String rating = String.valueOf(dataFilmBean.getVoteAverage());
//
//
//        tvJudul.setText(judul);
//        tvDesc.setText(desc);
//        imageFilm.setImageResource(Integer.parseInt(image));
//        tvTanggal.setText(tanggal);
//        tvRating.setText(rating);

//        String judul = intent.getStringExtra("nama");
//        String desc = intent.getStringExtra("desc");
//

//        tvJudul.setText(judul);
//        tvDesc.setText(desc);
//


    }
}
