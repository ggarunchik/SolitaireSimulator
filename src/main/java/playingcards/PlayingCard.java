package playingcards;

import java.util.Arrays;

public class PlayingCard implements Card {
    private String face;
    private String suit;

    /**
     * All possible suits a card may have
     */
    public static final String[] SUITS = {"S", "H", "D", "C"};

    /**
     * All possible face values a card may have
     */
    public static final String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public PlayingCard(String faceName, String suitName) {
        setFace(faceName);
        setSuit(suitName);
    }

    @Override
    public void setFace(String faceName) {
        if (isValidFace(faceName))
            this.face = faceName;
    }

    @Override
    public void setSuit(String suitName) {
        if (isValidSuit(suitName))
            this.suit = suitName;
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public static String[] getSUITS() {
        return SUITS;
    }

    public static String[] getFACES() {
        return FACES;
    }

    /**
     * Find if passed face name is valid to use
     *
     * @param faceName - passed value
     * @return - result if face name is valid
     */
    private boolean isValidFace(String faceName) {
        boolean containsFace = Arrays.stream(FACES).anyMatch(faceName::equals);
        if (!containsFace) {
            throw new IllegalArgumentException("Invalid Face!" + " actual: " + faceName + " expected: " + Arrays.deepToString(FACES));
        }
        return containsFace;
    }

    /**
     * Find if passed suit is valid to use
     *
     * @param suiteName - passed value
     * @return - result if suit is valid
     */
    private boolean isValidSuit(String suiteName) {
        boolean containsSuit = Arrays.stream(SUITS).anyMatch(suiteName::equals);
        if (!containsSuit) {
            throw new IllegalArgumentException("Invalid Face!" + " actual: " + suiteName + " expected: " + Arrays.deepToString(SUITS));
        }
        return containsSuit;
    }

    @Override
    public String toString() {
        return face + suit;
    }
}
