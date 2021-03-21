package playingcards;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FaceBidiMap {
	private BidiMap<Face, Integer> faces;
	
	public FaceBidiMap() {
		faces = new TreeBidiMap<Face, Integer>();
		faces.put(Face.ACE, 1);
		faces.put(Face.TWO, 2);
		faces.put(Face.THREE, 3);
		faces.put(Face.FOUR, 4);
		faces.put(Face.FIVE, 5);
		faces.put(Face.SIX, 6);
		faces.put(Face.SEVEN, 7);
		faces.put(Face.EIGHT, 8);
		faces.put(Face.NINE, 9);
		faces.put(Face.TEN, 10);
		faces.put(Face.JACK, 11);
		faces.put(Face.QUEEN, 12);
		faces.put(Face.KING, 13);
	}
	
	public int getFaceWeight(Face key) {
		return faces.get(key);
	}
	
	private int parseValue(String value) {
		switch (value) {
		case "A": return 1;
		case "10": return 10;
		case "J": return 11;
		case "Q": return 12;
		case "K": return 13;
		default: return Integer.parseInt(value);
		}
	}

	public String pareIntValueToCardCode(int value) {
		switch (value) {
			case 1: return "A";
			case 10: return "10";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default: return String.valueOf(value);
		}
	}
	
	public Face getFace(String value) {
		return faces.getKey(parseValue(value));
	}

	public String getRandomFace() {
		Random random = new Random();
		List<Integer> keys = new ArrayList<Integer>(faces.values());
		String randomKey = String.valueOf(keys.get(random.nextInt(keys.size())));

		return randomKey;
	}
}