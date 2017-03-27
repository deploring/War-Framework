package main.java.au.edu.swin.war.framework;

import main.java.au.edu.swin.war.framework.game.WarTeam;
import org.bukkit.entity.Player;

/**
 * This class adds an additional layer of functionality
 * to the conventional Player instance seen in Spigot.
 * <p>
 * This must be initialized and stored when a player
 * connects and must be destroyed once they disconnect.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see org.bukkit.entity.Player
 * <p>
 * Created by Josh on 20/03/2017.
 * @since 1.0
 */
public abstract class WarPlayer {

    private Player player; // The Spigot's player implementation
    private WarTeam currentTeam; // The team the player is currently on
    private boolean joined; // Whether or not the player is marked as joined

    /**
     * Constructor for WarPlayer class.
     *
     * @param player The Spigot instance of the Player's entity.
     */
    public WarPlayer(Player player) {
        this.player = player;
        this.currentTeam = null;
        this.joined = false;
    }

    /**
     * Returns whether or not the player has
     * 'joined' by typing the /join command.
     *
     * @return Player's joined status.
     */
    public boolean isJoined() {
        return joined;
    }

    /**
     * Associates the player with the team on a global level.
     *
     * @param newTeam The team to associate the player with.
     */
    public void setCurrentTeam(WarTeam newTeam) {
        this.currentTeam = newTeam;
    }

    /**
     * Returns the team that the player is currently associated with.
     * This is the team that the player currently on during a match.
     *
     * @return Player's associated team.
     */
    public WarTeam getCurrentTeam() {
        return currentTeam;
    }

    /**
     * Sets the 'joined' state of the player.
     *
     * @param joined The joined state.
     */
    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    /**
     * Returns the Spigot Player entity.
     *
     * @return Player entity.
     * @see org.bukkit.entity.Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns whether or not the player is actually
     * in a currently active round or not.
     *
     * @return If the player is playing or not.
     */
    public boolean isPlaying() {
        return currentTeam != null;
    }
}
