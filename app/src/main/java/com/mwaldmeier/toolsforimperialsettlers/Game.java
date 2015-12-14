package com.mwaldmeier.toolsforimperialsettlers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 12/13/2015.
 */
public class Game {
    private final Integer NumPlayers;
    private final List<Player> players = new ArrayList<>();

    public Game(Integer numPlayers) {
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

        public Player(Integer id) {
            this.ID = id;
            for (GoodType goodType:
                 GoodType.values()) {
                goods.add(new Good(goodType));
            }
        }

        public Integer getID() {
            return ID;
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
