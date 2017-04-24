package au.edu.swin.war.framework.game;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

/**
 * This (non-extendable) class handles all
 * Team-based interactions by the Gamemode.
 *
 * @author s101601828 @ Swin.
 * @version 1.0
 * @see org.bukkit.scoreboard.Scoreboard
 * @see org.bukkit.scoreboard.Team
 * <p>
 * Created by Josh on 20/03/2017.
 * @since 1.0
 */
public final class WarTeam {

    /* BEGIN RECORD */
    private final String teamName;
    private final ChatColor teamColor;
    private final Integer maxTeamSize;
    private final String scoreboardName;
    private Team bukkitTeam;
    /*  END RECORD  */

    /**
     * Creates an instance of this record,
     * With a team limit & its own unique identifier.
     * All separate methods will call this.
     *
     * @param teamName       The team's name.
     * @param teamColor      The team's color.
     * @param maxTeamSize    The maximum amount of players allowed on this team.
     * @param scoreboardName The team's scoreboard name.
     */
    private WarTeam(String teamName, ChatColor teamColor, Integer maxTeamSize, String scoreboardName) {
        this.teamName = teamName;
        this.teamColor = teamColor;
        this.maxTeamSize = maxTeamSize;
        this.scoreboardName = scoreboardName;
        this.bukkitTeam = null;
    }

    /**
     * Creates an instance of this record,
     * With no team limit & its own unique identifier.
     *
     * @param teamName       The team's name.
     * @param teamColor      The team's color.
     * @param scoreboardName The team's scoreboard name.
     */
    public WarTeam(String teamName, ChatColor teamColor, String scoreboardName) {
        this(teamName, teamColor, -1, scoreboardName);
    }

    /**
     * Creates an instance of this record,
     * With a team limit & no unique identifier.
     *
     * @param teamName    The team's name.
     * @param teamColor   The team's color.
     * @param maxTeamSize The maximum amount of players allowed on this team.
     */
    public WarTeam(String teamName, ChatColor teamColor, Integer maxTeamSize) {
        this(teamName, teamColor, maxTeamSize, teamName);
    }

    /**
     * Creates an instance of this record,
     * Without a team limit or a unique identifier.
     *
     * @param teamName  The team's name.
     * @param teamColor The team's color.
     */
    public WarTeam(String teamName, ChatColor teamColor) {
        this(teamName, teamColor, -1, teamName);
    }

    /**
     * Returns the name for the team that was
     * designated in the map configuration file.
     *
     * @return The Team's name.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the color for the team that was
     * designated in the map configuration file.
     *
     * @return The Team's designated color.
     */
    public ChatColor getTeamColor() {
        return teamColor;
    }

    /**
     * Returns the maximum number of players
     * allowed on this team at any given time.
     *
     * @return Maximum team size.
     */
    private Integer getMaxTeamSize() {
        return maxTeamSize;
    }

    /**
     * Returns the unique scoreboard identifier for this Team.
     *
     * @return The unique scoreboard identifier for this Team.
     */
    private String getScoreboardName() {
        return scoreboardName;
    }

    /**
     * Returns the Spigot Team object
     * used at runtime during a match.
     *
     * @return The Spgiot Team.
     */
    public Team getBukkitTeam() {
        return bukkitTeam;
    }

    /**
     * This method must be called on a cloned instantiation
     * of the class to implement Spigot's useful Team functions.
     *
     * @param bukkitTeam The Bukkit Team the gamemode will assign & control.
     */
    public void setBukkitTeam(Team bukkitTeam) {
        this.bukkitTeam = bukkitTeam;
    }

    /**
     * Creates an instantiated copy of this class and
     * any relevant fields needed for manipulation.
     *
     * @return A clean, usable copy of this class for runtime.
     */
    @Override
    public WarTeam clone() {
        return new WarTeam(getTeamName(), getTeamColor(), getMaxTeamSize(), getScoreboardName());
    }

    /**
     * Makes toString() return the team's designated name.
     *
     * @return The team's name.
     */
    @Override
    public String toString() {
        return this.getTeamName();
    }

    /**
     * Returns a colored team name string.
     *
     * @return The colored team name.
     */
    public String getDisplayName() {
        return getTeamColor() + getTeamName() + ChatColor.WHITE;
    }
}
