package playingcards;

import java.util.Random;

public class PlayingCardsFactory implements CardsFactory {

    @Override
    public PlayingCard createCard(String face, String suit) {
        return new PlayingCard(face, suit);
    }

    @Override
    public PlayingCard createRandomCard() {
        Random random = new Random();
        int randomFace = random.nextInt(PlayingCard.getFACES().length);
        int randomSuit = random.nextInt(PlayingCard.getSUITS().length);

        return new PlayingCard(PlayingCard.getFACES()[randomFace], PlayingCard.getSUITS()[randomSuit]);
    }
}
