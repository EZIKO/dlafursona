package eventy.Ezik.dzialki.pl;

import org.bukkit.event.block.*;
import Ezik.dzialki.pl.*;
import org.bukkit.configuration.file.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlaceEvent implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlaceEgg(final BlockPlaceEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals((Object)Main.dzialka().getType())) {
            if (!e.getPlayer().getItemInHand().getItemMeta().equals(Main.dzialka().getItemMeta())) {
                return;
            }
            final FileConfiguration yml = Main.getInst().getConfig();
            final Location loc = e.getBlockPlaced().getLocation();
            if (!loc.getWorld().getName().equalsIgnoreCase(yml.getString("swiatDzialek"))) {
                e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cTworzenie dzialek w tym swiecie jest zablokowane!"));
                e.setCancelled(true);
                return;
            }
            final int sd = yml.getInt("dzialkaSize");
            if (yml.getBoolean("dzialki." + e.getPlayer().getName().toLowerCase() + ".tak")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cLimit posiadanych dzialek zostal przekroczony!"));
                return;
            }
            if (yml.get("dzialki") != null) {
                for (final String p : yml.getConfigurationSection("dzialki").getKeys(false)) {
                    if (loc.getX() >= yml.getDouble("dzialki." + p + ".MiX") && loc.getX() <= yml.getDouble("dzialki." + p + ".MaX") && loc.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && loc.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cTen teren jest zajety!"));
                        return;
                    }
                    if (loc.getX() + sd >= yml.getDouble("dzialki." + p + ".MiX") && loc.getX() + sd <= yml.getDouble("dzialki." + p + ".MaX") && loc.getZ() + sd >= yml.getDouble("dzialki." + p + ".MiZ") && loc.getZ() + sd <= yml.getDouble("dzialki." + p + ".MaZ")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                        return;
                    }
                    if (loc.getX() - sd >= yml.getDouble("dzialki." + p + ".MiX") && loc.getX() - sd <= yml.getDouble("dzialki." + p + ".MaX") && loc.getZ() - sd >= yml.getDouble("dzialki." + p + ".MiZ") && loc.getZ() - sd <= yml.getDouble("dzialki." + p + ".MaZ")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                        return;
                    }
                    if (loc.getX() + sd >= yml.getDouble("dzialki." + p + ".MiX") && loc.getX() + sd <= yml.getDouble("dzialki." + p + ".MaX") && loc.getZ() - sd >= yml.getDouble("dzialki." + p + ".MiZ") && loc.getZ() - sd <= yml.getDouble("dzialki." + p + ".MaZ")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                        return;
                    }
                    if (loc.getX() - sd >= yml.getDouble("dzialki." + p + ".MiX") && loc.getX() - sd <= yml.getDouble("dzialki." + p + ".MaX") && loc.getZ() + sd >= yml.getDouble("dzialki." + p + ".MiZ") && loc.getZ() + sd <= yml.getDouble("dzialki." + p + ".MaZ")) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                        return;
                    }
                }
            }
            final Player p2 = e.getPlayer();
            final double Xmax = loc.getX() + sd;
            final double Xmin = loc.getX() - sd;
            final double Zmax = loc.getZ() + sd;
            final double Zmin = loc.getZ() - sd;
            final double X = loc.getX();
            final double Z = loc.getZ();
            final double Y = loc.getY();
            yml.set("dzialki." + p2.getName().toLowerCase() + ".MaX", (Object)Xmax);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".MiX", (Object)Xmin);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".MaZ", (Object)Zmax);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".MiZ", (Object)Zmin);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".tak", (Object)true);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".jajX", (Object)X);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".jajZ", (Object)Z);
            yml.set("dzialki." + p2.getName().toLowerCase() + ".jajY", (Object)Y);
            Main.getInst().saveConfig();
            e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&6Zabezpieczyles teren &7" + sd * 2 + "&6x&7" + sd * 2 + "&6!"));
        }
    }
}
