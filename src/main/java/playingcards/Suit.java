package playingcards;

public enum Suit {
    SPADES("S"), HEARTS("H"), DIAMONDS("D"), CLUBS("C");

    private final String suitStringValue;

    Suit(String suitStringValue) {
        this.suitStringValue = suitStringValue;
    }

    public String getSuitStringValue() {
        return suitStringValue;
    }
}
