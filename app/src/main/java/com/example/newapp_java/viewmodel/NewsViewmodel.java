package com.example.newapp_java.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newapp_java.api.ApiService;
import com.example.newapp_java.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewmodel  extends ViewModel {


    MutableLiveData<News> result = new MutableLiveData<>();

    MutableLiveData<Boolean> loadError = new MutableLiveData<>();

    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<News> getResult() {
        return result;
    }

    public MutableLiveData<Boolean> getLoadError() {
        return loadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public void loadResult() {

        loading.setValue(true);

        String apiKey = "87c2c97411e14a1fa22f202e7a49cc86";

        Call<News> newsCall = ApiService.getNews("us", "Technology", apiKey);

        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loading.setValue(false);

                    result.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                loading.setValue(false);
                loadError.setValue(true);

            }
        });
    }
}
