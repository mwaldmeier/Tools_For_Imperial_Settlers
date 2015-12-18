package com.mwaldmeier.toolsforimperialsettlers;

/**
 * Created by michael on 12/13/2015.
 */
public enum GoodType {
    WOOD("woodImg"),
    STONE("stoneImg"),
    FOOD("foodImg"),
    WORKERS("workerImg"),
    RAZE("razeImg"),
    DEFENSE("defenceImg"),
    GOLD("goldImg");

    private final String imgValue;
    GoodType(String imgValue) {this.imgValue = imgValue;}

    public String getImgValue() {
        return imgValue;
    }
}
