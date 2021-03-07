package playingcards;

public interface CardsFactory {
    PlayingCard createCard(String face, String suit);
    PlayingCard createRandomCard();

}
