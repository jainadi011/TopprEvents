package com.zriton.topprevents.view.activity;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.zriton.topprevents.R;
import com.zriton.topprevents.model.Website;
import com.zriton.topprevents.util.PaletteBitmap;
import com.zriton.topprevents.util.PaletteBitmapTranscoder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aditya on 24/09/16.
 */

public class EventDetail extends AppCompatActivity {

    private Website mWebsite = new Website();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(EventDetail.this);
        mWebsite = getIntent().getParcelableExtra("website");
        setUpToolbar();
        setData();
    }


    /**
     * Add toolbar to layout
     */
    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        String name = mWebsite.getName();
        if(name.length()>=18) {
            name = name.substring(0, 18);
            name+="...";
        }
        mToolbar.setTitle(name);
        collapsingToolbarLayout.setTitle(name);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    private void setData() {
        Glide.with(ivImage.getContext()).
                fromUri().asBitmap()
                .transcode(new PaletteBitmapTranscoder(this), PaletteBitmap.class)
                .centerCrop().load(Uri.parse(mWebsite.getImage())).into(new ImageViewTarget<PaletteBitmap>(ivImage) {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            protected void setResource(PaletteBitmap resource) {
                ivImage.setImageBitmap(resource.getBitmap());
                int colorPrimary = resource.palette.getDominantColor(ContextCompat.getColor(EventDetail.this, R.color.colorPrimary));
                int colorDark = colorPrimary + 15;
                collapsingToolbarLayout.setContentScrimColor(colorPrimary);
                collapsingToolbarLayout.setStatusBarScrimColor(colorDark);

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
