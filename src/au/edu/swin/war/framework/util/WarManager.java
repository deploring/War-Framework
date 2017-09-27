package au.edu.swin.war.framework.util;

import au.edu.swin.war.framework.WarPlayer;
import au.edu.swin.war.framework.WarPlugin;
import au.edu.swin.war.framework.util.modules.ItemUtility;
import au.edu.swin.war.framework.util.modules.StringUtility;
import au.edu.swin.war.framework.util.modules.WorldUtility;
import net.md_5.bungee.api.chat.TextComponent;
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

    private final WarPlugin plugin; // The WarPlugin instance (for JavaPlugin access)
    private final HashMap<UUID, WarPlayer> warPlayers; // The Key/Value set of WarPlayers.

    private final ItemUtility itemutil; // An instance of the item utility.
    private final StringUtility strutil; // An instance of the string utility.
    private final WorldUtility wrldutil; // An instance of the world utility.

    // Ensure to include a field containing the match manager!
    // Ensure to include a field containing the cache!
    // Extend the abstracted methods provided to do this!

    /**
     * Creates an instance of this class.
     * Must be called in onEnable();
     *
     * @param plugin Instance of main plugin. Must extend WarPlugin.
     */
    public WarManager(WarPlugin plugin) {
        this.plugin = plugin;
        this.warPlayers = new HashMap<>();
        this.itemutil = new ItemUtility(this);
        this.strutil = new StringUtility(this);
        this.wrldutil = new WorldUtility(this);
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
     * Returns an instance of StringUtility so that
     * maps, gamemodes, etc. can quickly access functions
     * that allow Strings to be manipulated.
     *
     * @return A running instance of the strings utility.
     */
    public StringUtility strings() {
        return strutil;
    }

    /**
     * Returns an instance of WorldUtility so that
     * maps, gamemodes, etc. can quickly access functions
     * that allow world files to be manipulated.
     *
     * @return A running instance of the world utility.
     */
    public WorldUtility world() {
        return wrldutil;
    }

    /**
     * Returns a running instance of the match manager.
     * This cannot be held in the framework, so you will
     * need to create your own field and make this function
     * return the manager.
     *
     * @return A running instance of the match manager.
     * @see WarMatch
     */
    public abstract WarMatch match();

    /**
     * Returns a running instance of the cache manager.
     * This cannot be held in the framework, so you will
     * need to create your own field and make this function
     * return the cache.
     *
     * @return A running instance of the cache.
     * @see WarCache
     */
    public abstract WarCache cache();

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
    public abstract WarPlayer craftWarPlayer(Player target);

    /**
     * When called, this should clear a player's inventory
     * and if applicable, give the player a spectator kit.
     *
     * @param wp The target player.
     */
    public abstract void giveSpectatorKit(WarPlayer wp);

    /**
     * Checks if a player can be warned, and then warns them.
     *
     * @param whoWasWarned Who was warned. (lol)
     */
    public abstract void warn(Player whoWasWarned, String warning);

    /**
     * Removes the instance of a WarPlayer.
     * This should always be called on the
     * targeted player's disconnect from the server.
     *
     * @param target The WarPlayer object to destroy in the HashMap.
     */
    public void destroyWarPlayer(UUID target) {
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
        if (target == null) return null;
        return getWarPlayer(target.getUniqueId());
    }

    /**
     * Returns an instance of a registered WarPlayer, if any.
     * Please remember to cast whatever you return to your own custom WarPlayer extension!
     *
     * @param target The target to query.
     * @return The target's WarPlayer instance.
     */
    public WarPlayer getWarPlayer(UUID target) {
        return warPlayers.get(target);
    }


    /**
     * Sends a TextComponent message to everyone online.
     *
     * @param comp Message to send.
     */
    public void broadcastSpigotMessage(TextComponent comp) {
        for (WarPlayer online : getWarPlayers().values())
            online.getPlayer().spigot().sendMessage(comp);
    }
}
