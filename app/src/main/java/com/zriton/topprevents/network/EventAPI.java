package com.zriton.topprevents.network;

import com.zriton.topprevents.model.EventResponse;
import com.zriton.topprevents.util.Config;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by aditya on 24/09/16.
 */

public class EventAPI {

    private HashMap<String,String> params;

    public EventAPI() {
        params = new HashMap<>();
        params.put(Config.GET_EVENTS_TYPE_KEY,Config.GET_EVENTS_TYPE_VALUE);
        params.put(Config.GET_EVENTS_QUERY_KEY, Config.GET_EVENTS_QUERY_VALUE);
    }

    private interface EventService {
        @FormUrlEncoded
        @POST(Config.GET_EVENTS)
        Observable<EventResponse> getEventResponse(@FieldMap(encoded = true) Map<String, String> fields);
    }

    private EventService getEventService()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient lClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Config.MAIN_URL)
                .client(lClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventService.class);
    }

    private Observable<EventResponse> eventObservable = getEventService().getEventResponse(params).cache();



    public Observable<EventResponse> getEventObservable() {
        return eventObservable;
    }
}
