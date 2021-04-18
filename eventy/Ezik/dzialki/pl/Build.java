package eventy.Ezik.dzialki.pl;

import org.bukkit.event.player.*;
import Ezik.dzialki.pl.*;
import org.bukkit.configuration.file.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

public class Build implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBuild(final PlayerInteractEvent e) {
        final Location l = e.getClickedBlock().getLocation();
        final FileConfiguration yml = Main.getInst().getConfig();
        if (yml.get("dzialki") == null) {
            return;
        }
        if (!l.getWorld().getName().equalsIgnoreCase(yml.getString("swiatDzialek"))) {
            return;
        }
        for (final String p : yml.getConfigurationSection("dzialki").getKeys(false)) {
            if (l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) {
                if (p.equalsIgnoreCase(e.getPlayer().getName())) {
                    if (e.getClickedBlock().getType() == Main.dzialka().getType() && l.getY() == yml.getDouble("dzialki." + p + ".jajY") && l.getX() == yml.getDouble("dzialki." + p + ".jajX") && l.getZ() == yml.getDouble("dzialki." + p + ".jajZ")) {
                        e.setCancelled(true);
                        e.getClickedBlock().setType(Material.AIR);
                        yml.set("dzialki." + p, (Object)null);
                        Main.getInst().saveConfig();
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&4Usunieto dzialke!"));
                    }
                    return;
                }
                if (yml.getBoolean("dzialki." + p.toLowerCase() + ".addmem." + e.getPlayer().getName().toLowerCase())) {
                    return;
                }
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cNie mozesz edytowac terenu czyjejs dzialki!"));
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBuild3(final PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) {
            return;
        }
        final Location l = e.getClickedBlock().getLocation();
        final FileConfiguration yml = Main.getInst().getConfig();
        if (yml.get("dzialki") == null) {
            return;
        }
        if (!l.getWorld().getName().equalsIgnoreCase(yml.getString("swiatDzialek"))) {
            return;
        }
        for (final String p : yml.getConfigurationSection("dzialki").getKeys(false)) {
            if (l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ") && p.equalsIgnoreCase(e.getPlayer().getName()) && e.getClickedBlock().getType() == Main.dzialka().getType() && l.getX() == yml.getDouble("dzialki." + p + ".jajX") && l.getZ() == yml.getDouble("dzialki." + p + ".jajZ") && l.getY() == yml.getDouble("dzialki." + p + ".jajY")) {
                e.setCancelled(true);
                e.getClickedBlock().setType(Material.AIR);
                yml.set("dzialki." + p, (Object)null);
                Main.getInst().saveConfig();
                e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&4Usunieto dzialke!"));
            }
        }
    }
    
    @EventHandler
    public void OnBuild4(final ExplosionPrimeEvent e) {
        final Location l = e.getEntity().getLocation();
        final FileConfiguration yml = Main.getInst().getConfig();
        if (yml.get("dzialki") == null) {
            return;
        }
        if (!l.getWorld().getName().equalsIgnoreCase(yml.getString("swiatDzialek"))) {
            return;
        }
        if (yml.getBoolean("explosion")) {
            return;
        }
        for (final String p : yml.getConfigurationSection("dzialki").getKeys(false)) {
            if (l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBuild5(final PlayerInteractEvent e) {
        final String p = e.getPlayer().getName().toLowerCase();
        final Location l = e.getClickedBlock().getLocation();
        final FileConfiguration yml = Main.getInst().getConfig();
        if (yml.get("dzialki") == null) {
            return;
        }
        if (!l.getWorld().getName().equalsIgnoreCase(yml.getString("swiatDzialek"))) {
            return;
        }
        if (l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) {
            return;
        }
        if (e.getClickedBlock().getType() == Main.dzialka().getType() && l.getX() == yml.getDouble("dzialki." + p + ".jajX") && l.getZ() == yml.getDouble("dzialki." + p + ".jajZ") && l.getY() == yml.getDouble("dzialki." + p + ".jajY")) {
            e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cNie tykaj tego!"));
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        if (ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Crafting Dzialki")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }
    }
}
