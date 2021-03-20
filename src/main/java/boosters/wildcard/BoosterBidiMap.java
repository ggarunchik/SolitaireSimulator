package boosters.wildcard;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class BoosterBidiMap {

    private BidiMap<BoosterType, String> boosterTypeBMap;

    public BoosterBidiMap() {
        boosterTypeBMap = new TreeBidiMap<BoosterType, String>();
        boosterTypeBMap.put(BoosterType.WILD, "W");
        boosterTypeBMap.put(BoosterType.PLUSFIVE, "PF");
    }

    public String getSuitChar(BoosterType key) {
        return boosterTypeBMap.get(key);
    }

    public BoosterType getSuit(String value) {
        return boosterTypeBMap.getKey(value);
    }
}
