package playingcards;

public interface CardsFactory {
    PlayingCard createCard(String cardCode) throws InvalidCardException;
    PlayingCard createRandomCard() throws InvalidCardException;

}
