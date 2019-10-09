package com.example.submission3made.film;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.ConfigurationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission3made.R;
import com.example.submission3made.film.model.DataFilmBean;
import com.example.submission3made.film.model.RespFilm;
import com.example.submission3made.service.ApiClient;
import com.example.submission3made.service.ApiService;


import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {

    private RecyclerView recyclerView;

    FilmAdapter adapter;

    ProgressDialog progressDialog;
    Context context;
    String language = "id";


    private MainViewModelFilm mainViewModelFilm;
    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");

        recyclerView = view.findViewById(R.id.rv_listfilm);

        final ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<RespFilm> call = service.getDataLanguage(language);

        call.enqueue(new Callback<RespFilm>() {
            @Override
            public void onResponse(Call<RespFilm> call, Response<RespFilm> response) {
                
                generateDataList(response.body().getResults());

//                if (language.equals("id")){
//                    generateDataList(response.body().getResults());
//                }else if (language.equals("EN")) {
//                    generateDataList(response.body().getResults());
//
//                }
            }

            @Override
            public void onFailure(Call<RespFilm> call, Throwable t) {

            }
        });



        MainViewModelFilm model = ViewModelProviders.of(this).get(MainViewModelFilm.class);

        model.getMovies();

        model.getFilmViewModel().observe(this, new Observer<ArrayList<DataFilmBean>>() {
            @Override
            public void onChanged(ArrayList<DataFilmBean> dataFilmBeans) {
                generateDataList(dataFilmBeans);
            }
        });

        model.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressDialog.show();
                }else {
                    progressDialog.dismiss();
                }
            }
        });

        model.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }
        });


    }



    private void generateDataList(ArrayList<DataFilmBean> dataFilms) {
        adapter = new FilmAdapter(getActivity(),dataFilms);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
