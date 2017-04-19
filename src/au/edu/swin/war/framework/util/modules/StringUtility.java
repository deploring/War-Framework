package au.edu.swin.war.framework.util.modules;

import au.edu.swin.war.framework.util.WarManager;
import au.edu.swin.war.framework.util.WarModule;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

/**
 * This class handles mildly helpful string functions
 * needed for user-friendliness.
 * <p>
 * Created by Josh on 18/04/2017.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see ItemStack
 * @since 1.0
 */
public class StringUtility extends WarModule {

    /**
     * String utility constructor.
     * We need to link back to the manager and plugin.
     *
     * @param main The supercontroller.
     */
    public StringUtility(WarManager main) {
        super(main);
    }

    /**
     * Turns an array of Strings into a sentence.
     * <p>
     * i.e. ['1', '2', '3', '4']
     * -> "1, 2, 3 and 4"
     *
     * @param array An array of words.
     * @return A sentence.
     */
    public String sentenceFormat(List<?> array, ChatColor c) {
        if (array.size() == 0) return "None";
        String format = "";
        if (array.size() == 1) return array.get(0).toString();
        int i = 1;
        while (i <= array.size()) {
            if (i == array.size()) {
                format = format + c + " and " + array.get(i - 1);
            } else if (i == 1) {
                format = array.get(0).toString();
            } else {
                format = format + c + ", " + array.get(i - 1);
            }
            i++;
        }
        return format;
    }

    /**
     * Generates a 5-digit number.
     * While not intrinsically a String, it is treated
     * as one when manipulating the map world.
     *
     * @return A 5-digit number.
     */
    public long generateID() {
        long generated = new Random().nextInt(90000) + 10000;
        // Number between 11111-99999;

        if (main().match().getRoundID_() == generated) return generateID();
        // Though a very small chance, regenerate if it is the same as the previous round.
        return generated;
    }

    /**
     * Converts an integer into a digital clock formatted string.
     *
     * @param i The integer to convert, in number of seconds.
     * @return The converted string in a MM:SS format.
     */
    public String getDigitalTime(int i) {
        if (i < 0) i = -i;
        int remainder = i % 3600, minutes = remainder / 60, seconds = remainder % 60;
        String time = "";
        if (minutes > 0) {
            if (minutes < 10) time += "0";
            time += minutes + ":";
        } else {
            time += "00:";
        }
        if (seconds > 0) {
            if (seconds < 10) time += "0";
            time += seconds;
        } else {
            time += "00";
        }
        return time;
    }

    /**
     * Is it second, or seconds?
     * Is it amount, or amounts?
     * <p>
     * i.e. 1 seconds -> 1 second,
     * 2 seconds -> 2 seconds.
     *
     * @param amount The amount.
     * @return The plural.
     */
    public String plural(int amount) {
        return amount == 1 ? " " : "s ";
    }

    /**
     * Turns ChatColor into dye Color.
     * This may not be 100% accurate but it was as close as I could get.
     *
     * @param color The ChatColor to convert.
     * @return The matching Color.
     */
    public Color convertChatToDye(ChatColor color) {
        if (color == ChatColor.AQUA)
            return Color.AQUA;
        else if (color == ChatColor.BLACK)
            return Color.BLACK;
        else if (color == ChatColor.BLUE)
            return Color.BLUE;
        else if (color == ChatColor.DARK_AQUA)
            return Color.TEAL;
        else if (color == ChatColor.DARK_BLUE)
            return Color.NAVY;
        else if (color == ChatColor.DARK_GRAY)
            return Color.GRAY;
        else if (color == ChatColor.DARK_GREEN)
            return Color.GREEN;
        else if (color == ChatColor.DARK_PURPLE)
            return Color.PURPLE;
        else if (color == ChatColor.DARK_RED)
            return Color.MAROON;
        else if (color == ChatColor.GOLD)
            return Color.ORANGE;
        else if (color == ChatColor.GRAY)
            return Color.GRAY;
        else if (color == ChatColor.GREEN)
            return Color.LIME;
        else if (color == ChatColor.LIGHT_PURPLE)
            return Color.FUCHSIA;
        else if (color == ChatColor.RED)
            return Color.RED;
        else if (color == ChatColor.YELLOW)
            return Color.YELLOW;
        else if (color == ChatColor.WHITE)
            return Color.WHITE;
        else return Color.WHITE;
    }
}
