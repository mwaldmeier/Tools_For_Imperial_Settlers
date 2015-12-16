package com.mwaldmeier.toolsforimperialsettlers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by michael on 12/13/2015.
 */
public class ScoreFragment extends android.app.Fragment {

    public ScoreFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        PlayerScoreSheetFragment sheet1 = new PlayerScoreSheetFragment();

        return rootView;
    }
}