package eventy.Ezik.dzialki.pl;

import org.bukkit.event.player.*;
import Ezik.dzialki.pl.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;
import java.util.*;
import org.bukkit.event.*;

public class Move implements Listener
{
    @EventHandler
    public void OnBuild4(final PlayerMoveEvent e) {
        final Location l = e.getTo();
        final Location l2 = e.getFrom();
        final FileConfiguration yml = Main.getInst().getConfig();
        if (yml.get("dzialki") == null) {
            return;
        }
        for (final String p : yml.getConfigurationSection("dzialki").getKeys(false)) {
            if ((l2.getX() < yml.getDouble("dzialki." + p + ".MiX") || l2.getX() > yml.getDouble("dzialki." + p + ".MaX") || l2.getZ() < yml.getDouble("dzialki." + p + ".MiZ") || l2.getZ() > yml.getDouble("dzialki." + p + ".MaZ")) && l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) {
                e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&7Wkroczyles na teren dzialki gracza: &6" + p));
                return;
            }
            if ((l.getX() >= yml.getDouble("dzialki." + p + ".MiX") && l.getX() <= yml.getDouble("dzialki." + p + ".MaX") && l.getZ() >= yml.getDouble("dzialki." + p + ".MiZ") && l.getZ() <= yml.getDouble("dzialki." + p + ".MaZ")) || l2.getX() < yml.getDouble("dzialki." + p + ".MiX") || l2.getX() > yml.getDouble("dzialki." + p + ".MaX") || l2.getZ() < yml.getDouble("dzialki." + p + ".MiZ") || l2.getZ() > yml.getDouble("dzialki." + p + ".MaZ")) {
                continue;
            }
            e.getPlayer().sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&7Opusciles dzialke gracza: &6" + p));
        }
    }
}
