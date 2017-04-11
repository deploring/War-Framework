package main.java.au.edu.swin.war.framework.util;

import main.java.au.edu.swin.war.framework.WarPlayer;
import main.java.au.edu.swin.war.framework.WarPlugin;
import main.java.au.edu.swin.war.framework.modules.ItemUtility;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class acts as the backend for all module
 * interaction & global value access. It must be
 * extended by the actual program in order to fulfil
 * certain things that can't be included in a framework.
 * <p>
 * Created by Josh on 20/03/2017.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see WarPlugin
 * @since 1.0
 */
public abstract class WarManager {

    private WarPlugin plugin; // The WarPlugin instance (for JavaPlugin access)
    private HashMap<UUID, WarPlayer> warPlayers; // The Key/Value set of WarPlayers.
    private ItemUtility itemutil; // An instance of the item utility.

    /**
     * Creates an instance of this class.
     * Must be called in onEnable();
     *
     * @param plugin Instance of main plugin. Must extend WarPlugin.
     */
    public WarManager(WarPlugin plugin) {
        this.plugin = plugin;
        this.warPlayers = new HashMap<>();
        this.itemutil = new ItemUtility();
    }

    /**
     * Returns an instance of WarPlugin so that
     * crucial JavaPlugin methods can be accessed.
     *
     * @return A running instance of the plugin main.
     */
    public WarPlugin plugin() {
        return plugin;
    }

    /**
     * Returns an instance of ItemUtility so that
     * maps, gamemodes, etc. can quickly access functions
     * that allow ItemStacks to be manipulated.
     *
     * @return A running instance of the item utility.
     */
    public ItemUtility items() {
        return itemutil;
    }

    /**
     * Returns the Key/Value set for all online WarPlayers.
     * Use this method to conveniently loop through all players.
     * i.e. for sending a message to everyone online,
     * i.e. or performing a common action to everyone.
     *
     * @return The HashMap defining all players.
     */
    public HashMap<UUID, WarPlayer> getWarPlayers() {
        return warPlayers;
    }

    /**
     * Creates an instance of a WarPlayer for a player.
     * Since the class relies heavily on Spigot methods,
     * the parameter must be the player entity itself.
     * <p>
     * !IMPORTANT! Please make sure this crafts your
     * extension of WarPlayer, not the abstract class itself!
     *
     * @param target The target to base the WarPlayer object on.
     * @see WarPlayer;
     */
    public abstract void craftWarPlayer(Player target);

    /**
     * When called, this should clear a player's inventory
     * and if applicable, give the player a spectator kit.
     *
     * @param wp The target player.
     */
    public abstract void giveSpectatorKit(WarPlayer wp);

    /**
     * Removes the instance of a WarPlayer.
     * This should always be called on the
     * targeted player's disconnect from the server.
     *
     * @param target The WarPlayer object to destroy in the HashMap.
     */
    public void destroyWarPlayer(String target) {
        warPlayers.remove(target);
    }

    /**
     * Please read the below documentation, since this
     * method simply calls the function below this one.
     * method simply calls the function below this one.
     *
     * @param target The target to query.
     * @return The target's WarPlayer instance.
     */
    public WarPlayer getWarPlayer(Player target) {
        return getWarPlayer(target.getName());
    }

    /**
     * Returns an instance of a registered WarPlayer, if any.
     * Please remember to cast whatever you return to your own custom WarPlayer extension!
     *
     * @param target The target to query.
     * @return The target's WarPlayer instance.
     */
    public WarPlayer getWarPlayer(String target) {
        return warPlayers.get(target);
    }
}
