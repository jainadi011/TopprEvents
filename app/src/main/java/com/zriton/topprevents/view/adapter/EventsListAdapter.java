package com.zriton.topprevents.view.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zriton.topprevents.R;
import com.zriton.topprevents.model.Website;
import com.zriton.topprevents.view.activity.EventDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private List<Website> mWebsiteList;

    public EventsListAdapter() {
        mWebsiteList = new ArrayList<>();
    }

    public void addWebsite(List<Website> newWebsite) {
        mWebsiteList.clear();
        mWebsiteList.addAll(newWebsite);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_events_item, parent, false);
        return new EventsListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Website lWebsite = mWebsiteList.get(position);
        holder.tvName.setText(lWebsite.getName());
        holder.tvCategory.setText(lWebsite.getCategory());
        Glide.with(holder.ivImage.getContext()).
                load(lWebsite.getImage()).
                into(holder.ivImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                Intent lIntent = new Intent(holder.cardView.getContext(), EventDetail.class);
                lIntent.putExtra("website", lWebsite);
                holder.cardView.getContext().startActivity(lIntent);
            }
        });

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
        @BindView(R.id.cardView)
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
