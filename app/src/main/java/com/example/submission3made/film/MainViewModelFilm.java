package com.example.submission3made.film;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission3made.film.model.DataFilmBean;
import com.example.submission3made.film.model.RespFilm;
import com.example.submission3made.service.ApiClient;
import com.example.submission3made.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModelFilm extends ViewModel {

    private MutableLiveData<ArrayList<DataFilmBean>> listFilm = new MutableLiveData<>();

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<String> message = new MutableLiveData<>();

    LiveData<ArrayList<DataFilmBean>> getFilmViewModel() {
        return listFilm;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<String> getMessage() {
        return message;
    }

    void getMovies() {
        if (listFilm.getValue() == null) {
            loading.postValue(true);
            final ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
            Call<RespFilm> call = service.getDataFilm();
            call.enqueue(new Callback<RespFilm>() {
                @Override
                public void onResponse(Call<RespFilm> call, Response<RespFilm> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            loading.postValue(false);
                            listFilm.postValue(response.body().getResults());
                        }
                    }

                }

                @Override
                public void onFailure(Call<RespFilm> call, Throwable t) {
                    loading.postValue(false);
                    message.postValue(t.getMessage());
                }
            });
        }

    }

}
