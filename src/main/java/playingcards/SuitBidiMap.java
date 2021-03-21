package playingcards;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SuitBidiMap {

    private BidiMap<Suit, String> suits;

    public SuitBidiMap() {
        suits = new TreeBidiMap<Suit, String>();
        suits.put(Suit.SPADES, "S");
        suits.put(Suit.HEARTS, "H");
        suits.put(Suit.CLUBS, "C");
        suits.put(Suit.DIAMONDS, "D");
    }

    public String getSuitChar(Suit key) {
        return suits.get(key);
    }

    public Suit getSuit(String value) {
        return suits.getKey(value);
    }

    public String getRandomSuit() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(suits.values());
        String randomKey = keys.get(random.nextInt(keys.size()));

		return randomKey;
	}
}