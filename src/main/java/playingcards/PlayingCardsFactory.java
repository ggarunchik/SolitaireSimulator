package playingcards;

import java.util.Random;

public class PlayingCardsFactory implements CardsFactory {
    public static final SuitBidiMap suits = new SuitBidiMap();
    public static final FaceBidiMap faces = new FaceBidiMap();

    @Override
    public PlayingCard createCard(String cardCode) throws InvalidCardException {
        return new PlayingCard(cardCode);
    }

    @Override
    public PlayingCard createRandomCard() throws InvalidCardException {
        String randomFace = faces.getRandomFace();
        String randomSuit = suits.getRandomSuit();

        return new PlayingCard(randomFace + randomSuit);
    }
}
