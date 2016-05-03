package pl.boleck.delask;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;
import pl.boleck.delask.comunication.Endpoints;
import pl.boleck.delask.helper.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Boleck on 2016-05-02.
 */

public class DelaskApp extends Application {
    private static Context mContext;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;




    }
    public static Context getContext(){
        return mContext;
    }

    public Retrofit getRetrofit() {
        if(retrofit ==null) {
            buildRetrofit();
        }
        return retrofit;
    }

    private void buildRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.HTTP.BASE_URL)
                .client(okHttpClient)
                .build();
    }
}
