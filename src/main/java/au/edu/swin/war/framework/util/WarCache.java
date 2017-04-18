package main.java.au.edu.swin.war.framework.util;

import main.java.au.edu.swin.war.framework.game.WarMap;
import main.java.au.edu.swin.war.framework.game.WarMode;
import main.java.au.edu.swin.war.framework.game.WarTeam;

import java.util.HashMap;

/**
 * This class acts as a cache for gamemode and
 * map running instances. If an instance of either
 * must be retrieved, it should be done through this
 * cache.
 * Created by Josh on 18/04/2017.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @since 1.0
 */
public abstract class WarCache extends WarModule {

    private HashMap<String, WarMap> maps; // The key/value set for all maps.
    private HashMap<String, WarMode> gamemodes; // The key/value set for all gamemodes.

    /**
     * Constructor of the cache. It should:
     * Load all gamemodes using reflections.
     * Load all maps using instantiations or reflections.
     *
     * @param main The supercontroller.
     */
    protected WarCache(WarManager main) {
        super(main);
        maps = new HashMap<>();
        gamemodes = new HashMap<>();
        loadMaps();
        loadGamemodes();
    }

    /**
     * This procedure should load any gamemode classes created.
     * In the external program, gamemodes should be detected via
     * reflections, instantiated, and put in the gamemodes array.
     */
    abstract void loadGamemodes();

    /**
     * This procedure should load any maps classes created.
     * In the external program, maps should be detected via
     * reflections or instantiated, and put in the maps array.
     */
    abstract void loadMaps();

    /**
     * Returns the current map playing.
     * This returns a running instance, not a name.
     * If you want to get the name of the map, preferably
     * use the getCurrentMap() function in WarMatch.
     *
     * @return The current map playing.
     */
    public WarMap getCurrentMap() {
        return getMap(main().match().getCurrentMap());
    }

    /**
     * Returns a gamemode based on its name.
     *
     * @param gamemode The gamemode to search for.
     * @return The gamemode found, if any.
     */
    public WarMode getGamemode(String gamemode) {
        return gamemodes.get(gamemode);
    }

    /**
     * Returns a map based on its name.
     * This returns a running instance, as stated above.
     *
     * @param map The map to match in the key/value set.
     * @return The map found, if any.
     */
    public WarMap getMap(String map) {
        return maps.get(map);
    }

    /**
     * Finds a team based on an incomplete or complete word.
     *
     * For example: ['Red Team', 'Blue Team']
     *
     * Input of 'bl' or 'Bl' -> 'Blue Team'
     * Input of 'r' -> 'Red Team'
     *
     * @param preference The team to try and find.
     * @return The team found, if any.
     */
    public WarTeam matchTeam(String preference) {
        WarTeam found = null;
        if (preference == null) return null;
        for (WarTeam team : main().match().getCurrentMode().getTeams()) {
            if (team.getTeamName().toLowerCase().startsWith(preference.toLowerCase())) {
                found = team;
                break;
            }
        }
        return found;
    }
}
