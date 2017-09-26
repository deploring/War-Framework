package au.edu.swin.war.framework.event;

import au.edu.swin.war.framework.WarPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event called when a match ends.
 * <p>
 * Just like all events, custom ones can be created.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see Event
 * <p>
 * Created by Josh on 21/09/2017.
 * @since 1.1
 */
public class MatchEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public MatchEndEvent() {
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
