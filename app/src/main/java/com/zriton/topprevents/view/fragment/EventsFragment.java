package com.zriton.topprevents.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by aditya on 24/09/16.
 */

public class EventsFragment extends Fragment {

    public static EventsFragment newInstance() {
        Bundle arguments = new Bundle();
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
}
