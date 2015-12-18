package com.mwaldmeier.toolsforimperialsettlers;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends android.app.Fragment {
    ImpSettlers ThisGame;
    List<View> PlayerViews = new ArrayList<>();
    List<List> PlayerImgViewLists = new ArrayList<>();

    public GoodsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods, container, false);

        ThisGame = ((ImpSettlers) getActivity().getApplication());

        //set up middle pool
        rootView.findViewById(R.id.foodImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.stoneImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.woodImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.workerImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.goldImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.razeImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.defenceImg).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.goodsPoolBox).setOnDragListener(new MyDragListenerForPlayerGoods());

        //set up player goods
        PlayerViews.add(rootView.findViewById(R.id.playerGoodsBox1));
        PlayerViews.add(rootView.findViewById(R.id.playerGoodsBox2));
        PlayerViews.add(rootView.findViewById(R.id.playerGoodsBox3));
        PlayerViews.add(rootView.findViewById(R.id.playerGoodsBox4));

        PlayerViews.get(0).setOnDragListener(new MyDragListenerForGoodsPool());
        PlayerViews.get(1).setOnDragListener(new MyDragListenerForGoodsPool());
        PlayerViews.get(2).setOnDragListener(new MyDragListenerForGoodsPool());
        PlayerViews.get(3).setOnDragListener(new MyDragListenerForGoodsPool());

        setListForImgs(rootView);
        setGoodCountsForAllPlayers();

        return rootView;
    }

    private void setListForImgs(View rootView) {
        List<View> player1Imgs = new ArrayList<>();
        player1Imgs.add(rootView.findViewById(R.id.foodImg1));
        player1Imgs.add(rootView.findViewById(R.id.stoneImg1));
        player1Imgs.add(rootView.findViewById(R.id.woodImg1));
        player1Imgs.add(rootView.findViewById(R.id.workerImg1));
        player1Imgs.add(rootView.findViewById(R.id.goldImg1));
        player1Imgs.add(rootView.findViewById(R.id.razeImg1));
        player1Imgs.add(rootView.findViewById(R.id.defenceImg1));

        List<View> player2Imgs = new ArrayList<>();
        player2Imgs.add(rootView.findViewById(R.id.foodImg2));
        player2Imgs.add(rootView.findViewById(R.id.stoneImg2));
        player2Imgs.add(rootView.findViewById(R.id.woodImg2));
        player2Imgs.add(rootView.findViewById(R.id.workerImg2));
        player2Imgs.add(rootView.findViewById(R.id.goldImg2));
        player2Imgs.add(rootView.findViewById(R.id.razeImg2));
        player2Imgs.add(rootView.findViewById(R.id.defenceImg2));

        List<View> player3Imgs = new ArrayList<>();
        player3Imgs.add(rootView.findViewById(R.id.foodImg3));
        player3Imgs.add(rootView.findViewById(R.id.stoneImg3));
        player3Imgs.add(rootView.findViewById(R.id.woodImg3));
        player3Imgs.add(rootView.findViewById(R.id.workerImg3));
        player3Imgs.add(rootView.findViewById(R.id.goldImg3));
        player3Imgs.add(rootView.findViewById(R.id.razeImg3));
        player3Imgs.add(rootView.findViewById(R.id.defenceImg3));

        List<View> player4Imgs = new ArrayList<>();
        player4Imgs.add(rootView.findViewById(R.id.foodImg4));
        player4Imgs.add(rootView.findViewById(R.id.stoneImg4));
        player4Imgs.add(rootView.findViewById(R.id.woodImg4));
        player4Imgs.add(rootView.findViewById(R.id.workerImg4));
        player4Imgs.add(rootView.findViewById(R.id.goldImg4));
        player4Imgs.add(rootView.findViewById(R.id.razeImg4));
        player4Imgs.add(rootView.findViewById(R.id.defenceImg4));

        PlayerImgViewLists.add(player1Imgs);
        PlayerImgViewLists.add(player2Imgs);
        PlayerImgViewLists.add(player3Imgs);
        PlayerImgViewLists.add(player4Imgs);

        setListenersForImgs();
    }

    private void setListenersForImgs() {
        for (List imgList :
                PlayerImgViewLists) {

            for (Object imgView :
                    imgList) {
                ((View) imgView).setOnTouchListener(new MyTouchListener());
            }

        }
    }

    private void setGoodCountsForAllPlayers() {
        for (int i = 1; i <= PlayerViews.size(); i++) {
            setGoodCountLblForPlayerView(i);
        }
    }

    private void setGoodCountLblForPlayerView(Integer playerNum) {
        for (Object imgViewObj :
                PlayerImgViewLists.get((playerNum - 1))) {
            ImageView imgView = ((ImageView) imgViewObj);
            GoodType thisGood = null;
            String countStr = null;

            //determine what good this is
            for (GoodType goodType :
                    GoodType.values()) {
                if (goodType.getImgValue().equals(imgView.getTag().toString())) {
                    thisGood = goodType;
                    break;
                }
            }

            if (thisGood != null) {
                countStr = (ThisGame.getGoodCountForPlayer(playerNum, thisGood)).toString();

                //get the text viw for this good
                ViewGroup container = (ViewGroup) imgView.getParent();
                TextView countLblView = (TextView) container.getChildAt((container.indexOfChild(imgView) + 1));

                countLblView.setText(countStr);
            }
        }
    }

    private GoodType getGoodTypeFromImg(View view) {
        GoodType goodType = null;
        String imgStr = (String) view.getTag();
        switch (imgStr) {
            case ("foodImg"):
                goodType = GoodType.FOOD;
                break;
            case ("woodImg"):
                goodType = GoodType.WOOD;
                break;
            case ("stoneImg"):
                goodType = GoodType.STONE;
                break;
            case ("workerImg"):
                goodType = GoodType.WORKERS;
                break;
            case ("razeImg"):
                goodType = GoodType.RAZE;
                break;
            case ("defenceImg"):
                goodType = GoodType.DEFENSE;
                break;
            case ("goldImg"):
                goodType = GoodType.GOLD;
                break;
        }
        return goodType;
    }

    private Integer getPlayerNumFromView(View view) {
        Integer playerNum = null;
        switch (view.getId()) {
            case (R.id.playerGoodsBox1):
                playerNum = 1;
                break;
            case (R.id.playerGoodsBox2):
                playerNum = 2;
                break;
            case (R.id.playerGoodsBox3):
                playerNum = 3;
                break;
            case (R.id.playerGoodsBox4):
                playerNum = 4;
                break;
        }

        return playerNum;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListenerForGoodsPool implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.player_goods_holder_shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.player_goods_holder_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    Integer playerNum = null;
                    Integer oldPlayerNum = null;
                    GoodType goodType = null;
                    View imgView = (View) event.getLocalState();
                    RelativeLayout containerView = (RelativeLayout) v;
                    View originalDragView = (View) imgView.getParent().getParent().getParent();

                    oldPlayerNum = getPlayerNumFromView(originalDragView);
                    playerNum = getPlayerNumFromView(containerView);

                    if (! playerNum.equals(oldPlayerNum)) {

                        goodType = getGoodTypeFromImg(imgView);

                        ThisGame.addOneGoodToPlayer(playerNum, goodType);
                        setGoodCountLblForPlayerView(playerNum);

                        //test if this came from another player
                        if (oldPlayerNum != null) {
                            ThisGame.removeOneGoodFromPlayer(oldPlayerNum, goodType);
                            setGoodCountLblForPlayerView(oldPlayerNum);
                        }
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    class MyDragListenerForPlayerGoods implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.player_goods_holder_shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.player_goods_holder_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    Integer playerNum = null;
                    GoodType goodType = null;
                    View imgView = (View) event.getLocalState();
                    //View goodsHolderVeiw = (View) (imgView.getParent());
                    View playerContainerView = (View) imgView.getParent().getParent().getParent();

                    playerNum = getPlayerNumFromView(playerContainerView);

                    if (playerNum != null) {

                        goodType = getGoodTypeFromImg(imgView);

                        ThisGame.removeOneGoodFromPlayer(playerNum, goodType);
                        setGoodCountLblForPlayerView(playerNum);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
}
