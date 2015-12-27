package com.mwaldmeier.helperforimperialsettlers;

import android.app.Application;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 12/13/2015.
 */
public class ImpSettlers extends Application{
    private Integer NumPlayers;
    private List<Player> Players = new ArrayList<>();

    public void setUpNewGame(Integer numPlayers) {
        this.NumPlayers = numPlayers;
        Players.clear();
        Integer i = 1;
        while (i <= this.NumPlayers) {
            Players.add(new Player(i));
            i += 1;
        }
    }



    public void addOneToScoreFor(Integer playerNum) {
        Players.get((playerNum - 1)).addOneToScore();
    }

    public boolean removeOneFromScoreFor(Integer playerNum) {
        return Players.get((playerNum -1)).removeOneFromScore();
    }

    public Integer getPlayerScoreFor(Integer playerNum) {
        return Players.get((playerNum - 1)).getScore();
    }

    public Integer getNumPlayers() {
        return NumPlayers;
    }

    public void addOneGoodToPlayer(Integer playerNum, GoodType goodType) {
        Players.get((playerNum - 1)).addOneToGood(goodType);
    }

    public boolean removeOneGoodFromPlayer(Integer playerNum, GoodType goodType) {
        return Players.get((playerNum - 1)).removeOneFromGood(goodType);
    }

    public Integer getGoodCountForPlayer(Integer playerNum, GoodType goodType) {
        return Players.get((playerNum-1)).getGoodCount(goodType);
    }

    private class Player {
        private final Integer ID;
        private List<Good> goods = new ArrayList<>();
        private Integer Score;
        private View goodsView;

        public Player(Integer id) {
            this.ID = id;
            for (GoodType goodType:
                 GoodType.values()) {
                goods.add(new Good(goodType));
            }
            this.Score = 0;
            this.goodsView = null;
        }

        public Integer getID() {
            return ID;
        }

        public Integer getScore() {
            return Score;
        }

        public void addOneToScore() {
                this.Score += 1;
        }

        public boolean removeOneFromScore() {
            if (this.Score > 0) {
                this.Score -= 1;
                return true;
            } else {
                return false;
            }
        }

        public void addOneToGood(GoodType goodType) {
            for (Good good :
                    goods) {
                if (good.getType() == goodType) {
                    good.addOne();
                }
            }
        }

        public boolean removeOneFromGood(GoodType goodType) {
            boolean didRemove = false;
            for (Good good :
                    goods) {
                if (good.getType() == goodType) {
                    didRemove = good.removeOne();
                }
            }
            return didRemove;
        }

        public Integer getGoodCount(GoodType goodType) {
            Integer count = null;
            for (Good good :
                    goods) {
                if (good.getType() == goodType) {
                    count = good.getCount();
                    break;
                }
            }
            return count;
        }

        private class Good {
            private final GoodType Type;
            private Integer Count;
            private View goodImgView;

            public Good(GoodType type) {
                this.Type = type;
                this.Count = 0;
                this.goodImgView = null;
            }

            public GoodType getType() {
                return Type;
            }

            public Integer getCount() {
                return Count;
            }
            public void addOne() {
                    this.Count += 1;
            }
            public boolean removeOne() {
                if (this.Count > 0) {
                    this.Count -= 1;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
