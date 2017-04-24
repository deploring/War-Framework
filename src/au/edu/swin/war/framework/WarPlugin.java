package au.edu.swin.war.framework;

import au.edu.swin.war.framework.util.WarManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
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

    // These fields are explained in the registerCommands() procedure.
    private CommandsManager<CommandSender> commands;
    private ArrayList<Class> cmdClasses;

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

    /**
     * Assigns a default commands manager to our commands class(es).
     * Once this is done, commands are registered and listened to.
     *
     * @see org.bukkit.permissions.Permission
     */
    protected void registerCommands() {
        // If there's no command classes registered...
        if (cmdClasses == null || cmdClasses.size() < 1) {
            log("Could not register commands! Perhaps you registered no classes?");
            // Don't bother doing anything.
            return;
        }

        /*
         Assign the command executor a default commands manager.
         This is an sk89q thing so commands can be handled.
         Commands are specific statements executed by players.
         An example command is /join, which sets the player as 'joined.
         */
        commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender player, String perm) {
                // Simple function to check whether or not the player has a permission.
                // Permissions are external, and for all intents and purposes, are not used.
                return player.hasPermission(perm);
            }
        };

        // sk89q command framework requires an injection into the commands class constructor.
        commands.setInjector(new SimpleInjector(main()));
        // Once an injector has been assigned, we can open up the registration.
        CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, commands);

        for (Class cmdClass : cmdClasses)
            // For each class, process it with the registration field. This is an sk89q thing, don't assess this.
            cmdRegister.register(cmdClass);
    }

    /**
     * Registers a class containing sk89q Commands.
     *
     * @param cmdClass The command class.
     * @see com.sk89q.minecraft.util.commands.Command
     */
    protected void registerCommandClass(Class cmdClass) {
        if (cmdClasses == null)
            // Initialise the array if this is the first time adding.
            cmdClasses = new ArrayList<>();

        // Add the class so it will be initialised later.
        cmdClasses.add(cmdClass);
    }

    /**
     * The onCommand method found in Bukkit's CommandExecutor class.
     * Re-written with sk89q framework to handle commands much more smoothly.
     *
     * @param sender The sender.
     * @param cmd    Bukkit's "Command" instance.
     * @param label  The command label.
     * @param args   Any arguments included in the command.
     * @return Always true because we don't want it to return false.
     * @see com.sk89q.minecraft.util.commands.Command
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            // Execute it through sk89q's command processor.
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            // No permission?
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            // Missing a nested command?
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            // Incorrect usage?
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            // Entering a string where a number is required?
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "You need to enter a number!");
            } else {
                sender.sendMessage(ChatColor.RED + "Error occurred, contact developer.");
                sender.sendMessage(ChatColor.RED + "Message: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (CommandException e) {
            // Any other exceptions?
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }
}
