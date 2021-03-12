package boosters.plusfive;

import boosters.BoosterFactory;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;

import java.util.List;

public class BoosterPlusFiveFactory implements BoosterFactory {
    @Override
    public List<PlayingCard> createBooster() throws InvalidCardException {
        return new BoosterPlusFive().initDeck();
    }
}
