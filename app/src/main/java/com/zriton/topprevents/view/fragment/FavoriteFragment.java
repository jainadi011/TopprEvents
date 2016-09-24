package com.zriton.topprevents.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by aditya on 24/09/16.
 */

public class FavoriteFragment extends Fragment {

    public static FavoriteFragment newInstance() {
        Bundle arguments = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
}
