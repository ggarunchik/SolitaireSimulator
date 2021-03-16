package boosters.wildcard;

import boosters.Booster;
import boosters.BoosterFactory;

public class BoosterWildCardFactory implements BoosterFactory {
    public BoosterWildCard createBooster() {
        return new BoosterWildCard();
    }
}
