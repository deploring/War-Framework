package au.edu.swin.war.framework.event;

import au.edu.swin.war.framework.WarPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event to handle team joining.
 * <p>
 * Just like all events, custom ones can be created.
 * This one is called when a player joins the match.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see Event
 * <p>
 * Created by Josh on 21/09/2017.
 * @since 1.1
 */
public class MatchPlayerJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final WarPlayer player;

    public MatchPlayerJoinEvent(WarPlayer player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns the player who joined.
     *
     * @return The player who joined.
     */
    public WarPlayer getPlayer() {
        return player;
    }
}
