package com.example.submission3made.service;

import com.example.submission3made.film.model.RespFilm;
import com.example.submission3made.tvshow.model.RespTvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/discover/movie?api_key=6d584ed75cc8772ef6582ea391a929b3")
    Call<RespFilm> getDataFilm();

    @GET("3/discover/movie?api_key=6d584ed75cc8772ef6582ea391a929b3")
    Call<RespFilm> getDataLanguage(@Query("language") String language);

    @GET("3/discover/tv?api_key=6d584ed75cc8772ef6582ea391a929b3")
    Call<RespTvShow> getDataTvShow();


}
