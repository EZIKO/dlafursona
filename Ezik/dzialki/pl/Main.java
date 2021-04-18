package Ezik.dzialki.pl;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import eventy.Ezik.dzialki.pl.*;
import org.bukkit.command.*;
import org.bukkit.inventory.*;

public class Main extends JavaPlugin
{
    private static Main instance;
    public static String s1;
    
    static {
        Main.s1 = c("&9Ezdzialki &8| &7");
    }
    
    public static String c(String s) {
        return s = s.replaceAll("&", "§");
    }
    
    public static ItemStack dzialka() {
        final String block = getInst().getConfig().getString("dzialkaBlock");
        final ItemStack it = new ItemStack(Material.getMaterial(block.toUpperCase()), 1);
        final ItemMeta im = it.getItemMeta();
        im.setDisplayName(String.valueOf(String.valueOf(Main.s1)) + c("&6Dzialka"));
        im.setLore((List)Arrays.asList(c("&cPostaw aby zabezpieczyc teren!")));
        it.setItemMeta(im);
        return it;
    }
    
    public void onEnable() {
        (Main.instance = this).saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents((Listener)new PlaceEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Build(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Move(), (Plugin)this);
        this.getCommand("dzialka").setExecutor((CommandExecutor)new komendy());
        final ShapedRecipe dz = new ShapedRecipe(dzialka()).shape(new String[] { "123", "456", "789" }).setIngredient('1', inv.i1.getType()).setIngredient('2', inv.i2.getType()).setIngredient('3', inv.i3.getType()).setIngredient('4', inv.i4.getType()).setIngredient('5', inv.i5.getType()).setIngredient('6', inv.i6.getType()).setIngredient('7', inv.i7.getType()).setIngredient('8', inv.i8.getType()).setIngredient('9', inv.i9.getType());
        this.getServer().addRecipe((Recipe)dz);
    }
    
    public static Main getInst() {
        return Main.instance;
    }
}
