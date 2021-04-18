package Ezik.dzialki.pl;

import org.bukkit.configuration.file.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class inv
{
    static FileConfiguration y;
    public static ItemStack i1;
    public static ItemStack i2;
    public static ItemStack i3;
    public static ItemStack i4;
    public static ItemStack i5;
    public static ItemStack i6;
    public static ItemStack i7;
    public static ItemStack i8;
    public static ItemStack i9;
    
    static {
        inv.y = Main.getInst().getConfig();
        inv.i1 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s1")));
        inv.i2 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s2")));
        inv.i3 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s3")));
        inv.i4 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s4")));
        inv.i5 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s5")));
        inv.i6 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s6")));
        inv.i7 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s7")));
        inv.i8 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s8")));
        inv.i9 = new ItemStack(Material.getMaterial(inv.y.getString("crafting.s9")));
    }
    
    public static Inventory inv1() {
        final Inventory in = Bukkit.createInventory((InventoryHolder)null, 27, "§cCrafting Dzialki");
        in.setItem(2, inv.i1);
        in.setItem(3, inv.i2);
        in.setItem(4, inv.i3);
        in.setItem(11, inv.i4);
        in.setItem(12, inv.i5);
        in.setItem(13, inv.i6);
        in.setItem(20, inv.i7);
        in.setItem(21, inv.i8);
        in.setItem(22, inv.i9);
        in.setItem(15, Main.dzialka());
        return in;
    }
    
    public static Inventory inv() {
        final Inventory in = Bukkit.createInventory((InventoryHolder)null, 27, "§cCrafting Dzialki");
        in.setItem(2, inv.i1);
        in.setItem(3, inv.i2);
        in.setItem(4, inv.i3);
        in.setItem(11, inv.i4);
        in.setItem(12, inv.i5);
        in.setItem(13, inv.i6);
        in.setItem(20, inv.i7);
        in.setItem(21, inv.i8);
        in.setItem(22, inv.i9);
        in.setItem(15, Main.dzialka());
        return in;
    }
}
