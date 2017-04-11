package main.java.au.edu.swin.war.framework.modules;

import main.java.au.edu.swin.war.framework.WarPlayer;
import main.java.au.edu.swin.war.framework.game.WarTeam;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * This class handles cruicial inventory and
 * item-related prodecures as documented below.
 * <p>
 * Created by Josh on 09/04/2017.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see ItemStack
 * @since 1.0
 */
public class ItemUtility {

    /**
     * Checks if an inventory is empty.
     *
     * @param inv Inventory to check.
     * @return Whether or not it is empty.
     * @see Inventory
     */
    public boolean isInventoryEmpty(Inventory inv) {
        for (ItemStack item : inv.getContents()) { // Checks every item slot in an inventory.
            if (item != null)
                return true; // A slot contains an item, this inventory is not empty.
        }
        return false; // No slots weren't null, this inventory is empty.
    }

    /**
     * Completely resets a player's inventory and state.
     *
     * @param wp The target to clear.
     * @see Player
     */
    public void clear(WarPlayer wp) {
        Player target = wp.getPlayer();
        target.closeInventory(); // Closes their inventory so it can be properly modified.
        for (PotionEffect pe : target.getActivePotionEffects())
            target.removePotionEffect(pe.getType()); // Remove all active potion effects.
        target.getInventory().clear(); // Clear the target's inventory.
        target.getInventory().setArmorContents(new ItemStack[4]); // Removes the target's armor.
        target.setExp(0); // Resets XP gained.
        target.setLevel(0); // Resets XP level.
        target.setHealth(20); // Sets health back to 10 hearts. (1 = 1/2 a heart)
        target.setSaturation(20F); // Sets food saturation back to high. (how quickly a player gains hunger)
        target.setFoodLevel(20); // Sets food level back to maximum.
        target.setMaxHealth(20); // Resets a player's total maximum health.
    }

    /**
     * Modifies an ItemStack's ItemMeta.
     * Adds an additional enchantment onto it, if needed.
     * Make sure the first Object is an Enchantment, and the second is an Integer.
     *
     * @param stack The ItemStack to change.
     * @param name  The ItemStack's name.
     * @param lore  The ItemStack's lore.
     */
    public ItemStack changeItem(ItemStack stack, String name, String[] lore, Object[]... enchant) {
        changeItem(stack, name, lore);
        if (enchant != null)
            for (Object[] ench : enchant) // Grab both the Enchantment and the level of the enchantment.
                stack.addUnsafeEnchantment((Enchantment) ench[0], (int) ench[1]); // Apply it unsafely.
        return stack;
        // For statement documentation, check the 2nd below function.
    }

    /**
     * Modifies an ItemStack's ItemMeta.
     *
     * @param stack The ItemStack to change.
     * @param name  The ItemStack's name.
     * @param lore  The ItemStack's lore.
     */
    public ItemStack changeItem(ItemStack stack, String name, Object[] lore) {
        ArrayList<String> loreList = new ArrayList<>();
        for (Object st : lore)
            loreList.add(ChatColor.GOLD + "" + st);
        return changeItem(stack, name, loreList);
        // For statement documentation, check the below function.
    }

    /**
     * ORIGINAL METHOD.
     * Modifies an ItemStack's ItemMeta.
     *
     * @param stack The ItemStack to change.
     * @param name  The ItemStack's name.
     * @param lore  The ItemStack's lore.
     */
    public ItemStack changeItem(ItemStack stack, String name, ArrayList<String> lore) {
        ItemMeta meta = stack.getItemMeta(); // Gets an ItemStack's meta (which holds display names, lore, etc.)
        if (name != null)
            meta.setDisplayName(ChatColor.RED + name); // Set the display name if it isn't null.
        if (lore != null)
            meta.setLore(lore); // Set the lore if it isn't null.
        stack.setItemMeta(meta); // Apply our changes!
        return stack;
    }

    /**
     * ORIGINAL METHOD.
     * Modifies an ItemStack's ItemMeta.
     * Use this method if you do not want to apply lore.
     *
     * @param stack The ItemStack to change.
     * @param name  The ItemStack's name.
     */
    public ItemStack changeItem(ItemStack stack, String name) {
        ItemMeta meta = stack.getItemMeta();
        if (name != null)
            meta.setDisplayName(ChatColor.RED + name);
        stack.setItemMeta(meta);
        return stack;
        // For statement documentation, check the above function.
    }

    /**
     * Colors an item depending on the user's current team.
     * !IMPORTANT! Only use leather armor please.
     *
     * @param armor       The armor piece to color.
     * @param currentTeam The user's current team.
     * @return The colored armor.
     */
    public ItemStack colorArmor(ItemStack armor, WarTeam currentTeam) {
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta(); // Gets the leather armor's specific meta.
        meta.setColor(convertChatToDye(currentTeam.getTeamColor())); // Sets the color of the leather armor.
        armor.setItemMeta(meta); // Apply our changes!
        return armor;
    }

    /**
     * Returns the skull of a player.
     *
     * @param name The name of the player.
     * @return The skull.
     * @see SkullMeta
     */
    public ItemStack giveSkull(String name) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); // Creates an ItemStack of player skull.
        SkullMeta meta = (SkullMeta) skull.getItemMeta(); // Gets the skull item's specific meta.
        meta.setOwner(name); // Sets the skull to a player's IGN for their skin.
        skull.setItemMeta(meta); // Apply our changes!
        return skull;
    }

    /**
     * Creates a potion.
     *
     * @param type      Potion effect type.
     * @param duration  Duration.
     * @param amplifier Strength.
     * @return The potion.
     */
    public ItemStack createPotion(PotionEffectType type, int duration, int amplifier) {
        ItemStack POTION = new ItemStack(Material.POTION); // Creates a potion with no ingredients.
        PotionMeta meta = (PotionMeta) POTION.getItemMeta(); // Gets the potion's specific meta.
        meta.addCustomEffect(new PotionEffect(type, duration, amplifier), true); // Adds the custom effect.
        POTION.setItemMeta(meta); // Apply our changes!
        return POTION;
    }

    /**
     * Converts a Minecraft "Chat Color" into the most
     * appropriate "Dye Color" possible for team armor.
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
