package playingcards;

public interface CardsFactory {
    Card createCard(String face, String suit);
    Card createRandomCard();

}
