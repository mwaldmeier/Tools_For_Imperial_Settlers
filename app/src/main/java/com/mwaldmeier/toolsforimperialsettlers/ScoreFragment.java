package com.mwaldmeier.toolsforimperialsettlers;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 12/13/2015.
 */
public class ScoreFragment extends android.app.Fragment {

    List<TextView> playerScoreLbls = new ArrayList<>();
    ImpSettlers ThisGame;
    SoundPool sp;
    int soundID;

    public ScoreFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        ThisGame = ((ImpSettlers) getActivity().getApplication());

        //set up drop sound
        sp = ((MainActivity) getActivity()).getSoundPool();
        soundID = sp.load(getActivity().getApplicationContext(), R.raw.blop, 1);

        ((ImageButton) rootView.findViewById(R.id.backBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.goToPage(2);
            }
        });

        //Hide player 3 & 4
        ((RelativeLayout) rootView.findViewById(R.id.playerScoreSheetFragmentLayout3)).setVisibility(View.GONE);
        ((RelativeLayout) rootView.findViewById(R.id.playerScoreSheetFragmentLayout4)).setVisibility(View.GONE);

        setUpPlayerScoreLbls(rootView);
        setUpScoreBtns(rootView);

        return rootView;
    }

    private void setUpPlayerScoreLbls(View rootView) {
        playerScoreLbls.add((TextView) rootView.findViewById(R.id.playerScore1));
        playerScoreLbls.add((TextView) rootView.findViewById(R.id.playerScore2));
        if (ThisGame.getNumPlayers() >= 3) {
            playerScoreLbls.add((TextView) rootView.findViewById(R.id.playerScore3));
            ((RelativeLayout) rootView.findViewById(R.id.playerScoreSheetFragmentLayout3)).setVisibility(View.VISIBLE);
            if (ThisGame.getNumPlayers() == 4) {
                playerScoreLbls.add((TextView) rootView.findViewById(R.id.playerScore4));
                ((RelativeLayout) rootView.findViewById(R.id.playerScoreSheetFragmentLayout4)).setVisibility(View.VISIBLE);
            }
        }

        refreshScores();

    }

    private void refreshScores() {
        Integer i = 1;

        for (TextView playerScore :
                playerScoreLbls) {
            playerScore.setText(ThisGame.getPlayerScoreFor(i).toString());
            i += 1;
        }
    }

    private void setUpScoreBtns(View rootView) {
        List<Button> plusBtns = new ArrayList<>();
        List<Button> minusBtns = new ArrayList<>();

        plusBtns.add((Button) rootView.findViewById(R.id.addOneScoreBtn1));
        plusBtns.add((Button) rootView.findViewById(R.id.addOneScoreBtn2));
        plusBtns.add((Button) rootView.findViewById(R.id.addOneScoreBtn3));
        plusBtns.add((Button) rootView.findViewById(R.id.addOneScoreBtn4));

        minusBtns.add((Button) rootView.findViewById(R.id.minusOneScoreBtn1));
        minusBtns.add((Button) rootView.findViewById(R.id.minusOneScoreBtn2));
        minusBtns.add((Button) rootView.findViewById(R.id.minusOneScoreBtn3));
        minusBtns.add((Button) rootView.findViewById(R.id.minusOneScoreBtn4));

        Integer i = 1;
        for (Button plusBtn :
                plusBtns) {
            final Integer finalI = i;
            plusBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ThisGame.addOneToScoreFor(finalI);
                    playDropSound();
                    refreshScores();

                }
            });
            i += 1;
        }

        i = 1;
        for (Button minusBtn :
                minusBtns) {
            final Integer finalI = i;
            minusBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (ThisGame.removeOneFromScoreFor(finalI)) {
                        playDropSound();
                        refreshScores();

                    }
                }
            });
            i += 1;
        }

    }

    private void playDropSound() {
        if (((MainActivity) getActivity()).getSoundOn().equals("1")) {
            sp.play(soundID, 1, 1, 0, 0, 1);
        }
    }
}