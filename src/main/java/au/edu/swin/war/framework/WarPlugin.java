package main.java.au.edu.swin.war.framework;

import com.sk89q.minecraft.util.commands.CommandsManager;
import main.java.au.edu.swin.war.framework.util.WarManager;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * An extension to JavaPlugin to hold War-related
 * data without cluttering the actual main code.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see org.bukkit.plugin.java.JavaPlugin
 * <p>
 * Created by Josh on 20/03/2017.
 * @since 1.0
 */
public abstract class WarPlugin extends JavaPlugin implements Listener {

    /**
     * This is for usage with sk89q's command framework.
     * It holds the CommandsManager instances as well
     * as any classes containing commands that need registration.
     **/
    protected CommandsManager<CommandSender> commands;
    protected ArrayList<Class> cmdClasses;

    /**
     * Logs a message to the console.
     * Message is logged to the INFO layer.
     *
     * @param message The message to log.
     * @see java.util.logging.Logger
     */
    public void log(String message) {
        getLogger().log(Level.INFO, "[War] " + message);
    }

    /**
     * Spigot's onEnable method.
     * This is the program's 'Main()'.
     *
     * @see org.bukkit.Server
     */
    public abstract void onEnable();

    /**
     * Spigot's onDisable method.
     * This is called when the program is shut down.
     *
     * @see org.bukkit.Server
     */
    public abstract void onDisable();

    /**
     * Allows modules to return to the manager in one
     * statement after executing anything in this class.
     *
     * @return The WarManager instance.
     */
    public abstract WarManager main();
}
