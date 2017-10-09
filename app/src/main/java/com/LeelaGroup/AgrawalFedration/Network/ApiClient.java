package com.LeelaGroup.AgrawalFedration.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 02-Jun-17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://agrawalfederation.com/AgrawalfederationApp/";
    private static Retrofit retrofit = null;

        public static Retrofit getRetrofit() {

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create(gson));
            builder.client(okHttpClient);
            return builder.build();
    }
}
