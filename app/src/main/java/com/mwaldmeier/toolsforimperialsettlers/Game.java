package com.mwaldmeier.toolsforimperialsettlers;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 12/13/2015.
 */
public class Game extends Application{
    private Integer NumPlayers;
    private final List<Player> players = new ArrayList<>();

    public void setUpNewGame(Integer numPlayers) {
        this.NumPlayers = numPlayers;
        Integer i = 1;
        while (i <= this.NumPlayers) {
            players.add(new Player(i));
            i += 1;
        }
    }

    private class Player {
        private final Integer ID;
        private List<Good> goods = new ArrayList<>();
        private Integer Score;

        public Player(Integer id) {
            this.ID = id;
            for (GoodType goodType:
                 GoodType.values()) {
                goods.add(new Good(goodType));
            }
            this.Score = 0;
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
            if (this.Score < 1) {
                this.Score -= 1;
                return true;
            } else {
                return false;
            }
        }

        private class Good {
            private final GoodType Type;
            private Integer Count;
            public Good(GoodType type) {
                this.Type = type;
                this.Count = 0;
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
