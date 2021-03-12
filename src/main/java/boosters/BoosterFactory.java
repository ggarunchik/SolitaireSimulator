package boosters;

import playingcards.InvalidCardException;
import playingcards.PlayingCard;

import java.util.List;

public interface BoosterFactory {
    List<PlayingCard> createBooster() throws InvalidCardException;
}
