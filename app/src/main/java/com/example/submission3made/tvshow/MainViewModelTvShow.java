package com.example.submission3made.tvshow;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission3made.service.ApiClient;
import com.example.submission3made.service.ApiService;
import com.example.submission3made.tvshow.model.DataTvShowBean;
import com.example.submission3made.tvshow.model.RespTvShow;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelTvShow extends ViewModel {

    private MutableLiveData<ArrayList<DataTvShowBean>> listTvShow = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<String> message = new MutableLiveData<>();


    LiveData<ArrayList<DataTvShowBean>> getTvDataShowModel() {
        return listTvShow;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<String> getMessage() {
        return message;
    }


    void getTvShow() {
        if (listTvShow.getValue() == null) {
            loading.postValue(true);
            final ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
            Call<RespTvShow> call = service.getDataTvShow();
            call.enqueue(new Callback<RespTvShow>() {
                @Override
                public void onResponse(Call<RespTvShow> call, Response<RespTvShow> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            loading.postValue(false);
                            listTvShow.postValue(response.body().getResults());
                        }
                    }
                }

                @Override
                public void onFailure(Call<RespTvShow> call, Throwable t) {
                    loading.postValue(false);
                    message.postValue(t.getMessage());
                }
            });
        }
    }
}
