package com.zriton.topprevents.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zriton.topprevents.R;
import com.zriton.topprevents.model.Website;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private Context mContext;
    private List<Website> mWebsiteList = new ArrayList<>();

    public EventsListAdapter(Context pContext, List<Website> pWebsiteList) {
        mContext = pContext;
        mWebsiteList = pWebsiteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.list_events_item, parent, false);
        return new EventsListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Website lWebsite = mWebsiteList.get(position);
        holder.tvName.setText(lWebsite.getName());
        holder.tvCategory.setText(lWebsite.getCategory());
        Glide.with(mContext).
                load(lWebsite.getImage()).
                into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mWebsiteList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCategory)
        TextView tvCategory;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}