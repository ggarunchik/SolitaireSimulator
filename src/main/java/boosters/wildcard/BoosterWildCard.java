package boosters.wildcard;

import boosters.Booster;
import playingcards.Card;

public class BoosterWildCard implements Booster, Card {
    public static  final  BoosterBidiMap boosters = new BoosterBidiMap();

    private BoosterType boosterType;

    public BoosterWildCard() {
        this.boosterType = boosters.getSuit("W");
    }

    @Override
    public BoosterType getSuit() {
        return this.boosterType;
    }

    @Override
    public int getFaceNum() {
        return 0;
    }

    @Override
    public String toString() {
        return "boosterType = " + boosterType;
    }
}
