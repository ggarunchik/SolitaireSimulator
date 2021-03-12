package playingcards;


public class PlayingCard implements Card {
    public static final SuitBidiMap suits = new SuitBidiMap();
    public static final FaceBidiMap faces = new FaceBidiMap();

    private Face face;
    private Suit suit;

    public PlayingCard(String cardCode) throws InvalidCardException {
        String face = null;
        String suit = null;
        // Use first letters of card_code
        face = cardCode.substring(0, cardCode.length() - 1);
        // Use last letter in card_code
        suit = String.valueOf(cardCode.charAt(cardCode.length() - 1));

        // Set card values
        this.face = faces.getFace(face);
        this.suit = suits.getSuit(suit);

        // Invalid rank or suit
        if (this.face == null || this.suit == null) {
            throw new InvalidCardException(
                    String.format("Invalid card %s", cardCode));
        }
    }

    @Override
    public Face getFace() {
        return this.face;
    }

    @Override
    public int getFaceNum() {
        return faces.getFaceWeight(this.face);
    }

    @Override
    public Suit getSuit() {
        return this.suit;
    }

    public String getSuitChar(Suit key) {
        return suits.getSuitChar(key);
    }

    public String getFacePrint() {
        int symbol = this.getFaceNum();
        if (symbol == 1) {
            return "A";
        } else if (symbol == 10) {
            return "10";
        } else if (symbol == 11) {
            return "J";
        } else if (symbol == 12) {
            return "Q";
        } else if (symbol == 13) {
            return "K";
        }
        return Integer.toString(symbol);
    }

    @Override
    public String toString() {
        if (this.getFacePrint() == "10") {
            return this.getFacePrint() + suits.getSuitChar(this.suit) + " ";
        }
        return " " + this.getFacePrint() + suits.getSuitChar(this.suit) + " ";
    }
}
