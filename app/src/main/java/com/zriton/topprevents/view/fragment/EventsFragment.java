package com.zriton.topprevents.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zriton.topprevents.R;
import com.zriton.topprevents.model.Website;
import com.zriton.topprevents.presenter.EventsContract;
import com.zriton.topprevents.presenter.EventsPresenter;
import com.zriton.topprevents.util.CheckInternetConnection;
import com.zriton.topprevents.view.adapter.EventsListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsFragment extends Fragment implements EventsContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.noInternet)
    RelativeLayout noInternet;


    private EventsListAdapter mEventsListAdapter;
    private EventsContract.Presenter mPresenter;

    public static EventsFragment newInstance() {
        Bundle arguments = new Bundle();
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lView = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, lView);
        setHasOptionsMenu(true);
        initRecyclerView();
        mPresenter = new EventsPresenter(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (CheckInternetConnection.isConnectedToInternet(getContext()))
                    mPresenter.loadEvents(true);
                else {
                    setRefreshing(false);
                    Toast.makeText(getContext(), "Please check internet settings and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return lView;
    }

    @OnClick(R.id.btRetry) void onRetryClicked(){
        if (CheckInternetConnection.isConnectedToInternet(getContext())) {
            showNoNetwork(false);
            setLoadingIndicator(true);
            mPresenter.subscribe();
        }
        else
            showNoNetwork(true);
    }

    public void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEventsListAdapter = new EventsListAdapter();
        mRecyclerView.setAdapter(mEventsListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CheckInternetConnection.isConnectedToInternet(getContext())) {
            showNoNetwork(false);
            setLoadingIndicator(true);
            mPresenter.subscribe();
        }
        else
            showNoNetwork(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_events, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressBar.setVisibility(View.VISIBLE);
        else
            mProgressBar.setVisibility(View.GONE);
        mProgressBar.setIndeterminate(active);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void showEvents(List<Website> pWebsiteList) {
        mContent.setVisibility(View.VISIBLE);
        mEventsListAdapter.addWebsite(pWebsiteList);
    }


    @Override
    public void showLoadingEventComplete() {
        Toast.makeText(getActivity(), "Events Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingEventError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoEvents() {
        Toast.makeText(getActivity(), "No events!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoNetwork(boolean active) {
        if(active) {
            Toast.makeText(getContext(), "Please check internet settings and try again", Toast.LENGTH_SHORT).show();
            noInternet.setVisibility(View.VISIBLE);
        }
        else
            noInternet.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
