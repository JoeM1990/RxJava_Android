package com.creasmit.task.data.repository.remote;

import com.creasmit.task.UrlConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteService {
    private static Retrofit retrofit;

    public static Retrofit retrofit() {
        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
