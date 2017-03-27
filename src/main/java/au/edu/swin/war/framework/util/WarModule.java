package main.java.au.edu.swin.war.framework.util;

/**
 * Any classes that need to access other modules or
 * interact with Spigot on a plugin level will need
 * to extend this class.
 * <p>
 * This WarModule() MUST be initialized or the program
 * will NOT work. A field of the module needs to be kept.
 * Created by Josh on 20/03/2017.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see org.bukkit.Bukkit
 * @since 1.0
 */
public abstract class WarModule {
    private final WarManager main; // The WarManager instance

    protected WarModule(WarManager main) {
        this.main = main;
        // super(main); and then write your own code.
    }

    /**
     * Returns an instance of the WarManager class.
     * Used to link back to the manager in a non-static approach.
     *
     * @return The WarManager instance.
     */
    public WarManager main() {
        return main;
    }
}
