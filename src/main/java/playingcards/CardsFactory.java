package playingcards;

public interface CardsFactory {
    Card createCard(String cardCode) throws InvalidCardException;
    Card createRandomCard() throws InvalidCardException;

}
