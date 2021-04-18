package Ezik.dzialki.pl;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;

public class komendy implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("dzialka")) {
            final FileConfiguration yml = Main.getInst().getConfig();
            if (args.length < 1 || args.length > 2) {
                sender.sendMessage(Main.c("&9Ezdzialki &7- Pomoc"));
                sender.sendMessage(Main.c("&7- &3/dzialka crafting &7- Crafting dzialki."));
                sender.sendMessage(Main.c("&7- &3/dzialka dodaj [gracz] &7- dodaje gracza do dzialki."));
                sender.sendMessage(Main.c("&7- &3/dzialka wyrzuc [gracz] &7- wyrzuca gracza z dzialki."));
                return true;
            }
            if (args[0].equalsIgnoreCase("crafting")) {
                final Player p = (Player)sender;
                p.openInventory(inv.inv());
                return true;
            }
            if (args[0].equalsIgnoreCase("dodaj")) {
                if (args.length != 2) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cPoprawne uzycie: &7/dzialka dodaj <gracz>"));
                    return true;
                }
                if (!yml.getBoolean("dzialki." + sender.getName().toLowerCase() + ".tak")) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cNie posiadasz dzialki!"));
                    return true;
                }
                final Player poff = Bukkit.getPlayer(args[1]);
                if (poff == null) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cNiema takiego gracza online!"));
                    return true;
                }
                yml.set("dzialki." + sender.getName().toLowerCase() + ".addmem." + args[1].toLowerCase(), (Object)true);
                Main.getInst().saveConfig();
                sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&aPomyslnie dodano do dzialki gracza: &7" + poff.getName()));
                poff.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&aGracz &7" + sender.getName() + " &adodal Cie do dzialki!"));
                return true;
            }
            else {
                if (!args[0].equalsIgnoreCase("wyrzuc")) {
                    sender.sendMessage(Main.c("&9Ezdzialki &7- Pomoc"));
                    sender.sendMessage(Main.c("&7- &3/dzialka crafting &7- Crafting dzialki."));
                    sender.sendMessage(Main.c("&7- &3/dzialka dodaj [gracz] &7- dodaje gracza do dzialki."));
                    sender.sendMessage(Main.c("&7- &3/dzialka wyrzuc [gracz] &7- wyrzuca gracza z dzialki."));
                    return true;
                }
                if (args.length != 2) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cPoprawne uzycie: &7/dzialka wyrzuc <gracz>"));
                    return true;
                }
                if (!yml.getBoolean("dzialki." + sender.getName().toLowerCase() + ".tak")) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cNie posiadasz dzialki!"));
                    return true;
                }
                if (!yml.getBoolean("dzialki." + sender.getName().toLowerCase() + ".addmem." + args[1].toLowerCase())) {
                    sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&cTen gracz nie nalezy do twojej dzialki!"));
                    return true;
                }
                yml.set("dzialki." + sender.getName().toLowerCase() + ".addmem." + args[1].toLowerCase(), (Object)null);
                Main.getInst().saveConfig();
                sender.sendMessage(String.valueOf(String.valueOf(Main.s1)) + Main.c("&3Pomyslnie usunieto z dzialki gracza: &7" + args[1]));
            }
        }
        return true;
    }
}
