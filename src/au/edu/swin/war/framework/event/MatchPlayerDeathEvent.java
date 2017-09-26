package au.edu.swin.war.framework.event;

import au.edu.swin.war.framework.WarPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event to handle match death.
 * <p>
 * Just like all events, custom ones can be created.
 * This one is called when a player dies during a match.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see Event
 * <p>
 * Created by Josh on 21/09/2017.
 * @since 1.1
 */
public class MatchPlayerDeathEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final WarPlayer dead, killer;

    public MatchPlayerDeathEvent(WarPlayer dead, WarPlayer killer) {
        this.dead = dead;
        this.killer = killer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns the player who died.
     *
     * @return The player who died.
     */
    public WarPlayer getPlayer() {
        return dead;
    }

    /**
     * Returns the player who killed this player.
     * Warning: Returns null if there was no killer.
     *
     * @return The player who died.
     */
    public WarPlayer getKiller() {
        return killer;
    }
}
