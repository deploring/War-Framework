package au.edu.swin.war.framework;

import au.edu.swin.war.framework.game.WarTeam;
import au.edu.swin.war.framework.util.WarManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * This class adds an additional layer of functionality
 * to the conventional Player instance seen in Spigot.
 * <p>
 * This must be initialized and stored when a player
 * connects and must be destroyed once they disconnect.
 *
 * @author s101601828 @ Swin.
 * @version 1.1
 * @see org.bukkit.entity.Player
 * <p>
 * Created by Josh on 20/03/2017.
 * @since 1.0
 */
public abstract class WarPlayer {

    private final Player player; // The Spigot's player implementation
    private WarTeam currentTeam; // The team the player is currently on
    private boolean joined; // Whether or not the player is marked as joined
    private WarManager manager; // Instance of the supercontroller.

    /**
     * Constructor for WarPlayer class.
     *
     * @param player  The Spigot instance of the Player's entity.
     * @param manager Instance of the supercontroller.
     */
    public WarPlayer(Player player, WarManager manager) {
        this.player = player;
        this.currentTeam = null;
        this.joined = false;
        this.manager = manager;
        changeVisibility();
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
     * Sets the 'joined' state of the player.
     *
     * @param joined The joined state.
     */
    public void setJoined(boolean joined) {
        this.joined = joined;
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
     * Associates the player with the team on a global level.
     *
     * @param newTeam The team to associate the player with.
     */
    public void setCurrentTeam(WarTeam newTeam) {
        this.currentTeam = newTeam;
        changeVisibility();
    }

    /**
     * Changes the player's visibility to others based
     * on whether or not they are in the round.
     */
    private void changeVisibility() {
        if (isPlaying()) {
            player.setCollidable(true);
            // If they are playing, everyone can see this player.
            // They however, cannot see spectators.
            for (WarPlayer dp : manager.getWarPlayers().values())
                if (dp.equals(this)) continue;
                else if (dp.isPlaying()) {
                    // They are both playing, so they can both see each other.
                    dp.getPlayer().showPlayer(player);
                    player.showPlayer(dp.getPlayer());
                } else {
                    // The other player is spectating, so this player cannot see them.
                    dp.getPlayer().showPlayer(player);
                    player.hidePlayer(dp.getPlayer());
                }
        } else {
            player.setCollidable(false);
            // If they are spectating, only spectators can see this player.
            // They can see others playing as well.
            for (WarPlayer dp : manager.getWarPlayers().values())
                if (dp.equals(this)) continue;
                else if (dp.isPlaying()) {
                    // The other player is playing, so they cannot see this player.
                    dp.getPlayer().hidePlayer(player);
                    player.showPlayer(dp.getPlayer());
                } else {
                    // The other player is spectating, so they can see each other.
                    dp.getPlayer().showPlayer(player);
                    player.showPlayer(dp.getPlayer());
                }
        }
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
     * @param message The message to send to the player.
     * @see Player#sendMessage(String)
     */
    public void sendMessage(String message) {
        getPlayer().sendMessage(message);
    }

    /**
     * @return The player's in-game name.
     * @see Player#getName()
     */
    public String getName() {
        return getPlayer().getName();
    }

    /**
     * Returns thei player's IGN colored with their team color.
     *
     * @return The colored player name.
     */
    public String getTeamName() {
        return getCurrentTeam().getTeamColor() + getName() + ChatColor.WHITE;
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
