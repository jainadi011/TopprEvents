package com.zriton.topprevents.presenter;

import com.zriton.topprevents.model.Website;

import java.util.List;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsContract {

    public interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void setRefreshing(boolean isRefreshing);

        void showEvents(List<Website> tasks);

        void showLoadingEventComplete();

        void showLoadingEventError(String error);

        void showNoEvents();

        void showNoNetwork(boolean active);

    }

    public interface Presenter extends BasePresenter {

        void loadEvents(boolean forceUpdate);

    }
}
