package com.zriton.topprevents.presenter;

import android.support.annotation.NonNull;

import com.zriton.topprevents.model.EventResponse;
import com.zriton.topprevents.network.EventAPI;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsPresenter implements EventsContract.Presenter {

    @NonNull
    private final EventsContract.View mEventsView;

    @NonNull
    private Subscription mSubscription;

    public EventsPresenter(@NonNull EventsContract.View pTasksView) {
        mEventsView = pTasksView;
        mEventsView.setPresenter(this);
    }

    /**
     * @param forceUpdate Pass in true for refreshing data or false for fetching data first time
     */
    @Override
    public void loadEvents(final boolean forceUpdate) {
        final EventAPI lEventAPI = new EventAPI();
        final Observable<EventResponse> lFollowerListObservable = lEventAPI.getEventObservable();

        mSubscription = lFollowerListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers
                        .mainThread())
                .subscribe(new Subscriber<EventResponse>() {
                    @Override
                    public void onCompleted() {
                        mEventsView.showLoadingEventComplete();
                        if (forceUpdate) {
                            mEventsView.setRefreshing(false);
                        } else {
                            mEventsView.setLoadingIndicator(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (forceUpdate) {
                            mEventsView.setRefreshing(false);
                        } else {
                            mEventsView.setLoadingIndicator(false);
                        }
                        mEventsView.showLoadingEventError(String.valueOf(e.toString()));
                    }

                    @Override
                    public void onNext(EventResponse pEventResponse) {
                        if (pEventResponse.getWebsites().size() > 0)
                            mEventsView.showEvents(pEventResponse);
                        else
                            mEventsView.showNoEvents();
                    }
                });
    }


    @Override
    public void subscribe() {
        loadEvents(false);
    }

    @Override
    public void unsubscribe() {
        mSubscription.unsubscribe();
    }
}
