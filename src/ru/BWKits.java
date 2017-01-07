package ru;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.*;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BWKits extends JavaPlugin implements Listener {
    private static Plugin plugin;
    private FileConfiguration data;
    private File file;
    public void onEnable() {
        plugin = this;
        Msg.load();
        file = new File(getDataFolder(), "config.yml");
        getServer().getPluginManager().registerEvents(this, this);
//        this.saveDefaultConfig();
//        File dataFile = new File(getDataFolder() + File.separator + "config.yml");
//        if (!dataFile.exists()){
//            try {
//                dataFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        getServer().getConsoleSender().sendMessage("§c==============§a BWKits v1.0 §c==============");
        getServer().getConsoleSender().sendMessage("§aThe plugin has been successfully enabled!");
        getServer().getConsoleSender().sendMessage("§c=========================================");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§c==============§a BWKits v1.0 §c==============");
        getServer().getConsoleSender().sendMessage("§4The plugin has been successfully disabled!");
        getServer().getConsoleSender().sendMessage("§c=========================================");
    }
    public static Plugin getPlugin() {
        return plugin;
    }

    public static Economy economy = null;
    private boolean setupEconomy(){
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
//    String Msg.getPex_nope() = this.getConfig().getString("Msg.getPex_nope()");
//    String Msg.getVipdon() = this.getConfig().getString("Msg.getVipdon()");
//    String Msg.getVipplusdon() = this.getConfig().getString("Msg.getVipplusdon()");
//    String Msg.getPlatinumdon() = this.getConfig().getString("Msg.getPlatinumdon()");
//
//    int Msg.getWarriorCost() = this.getConfig().getInt("warrior.Msg.getCost()");
//    int Msg.getArcherCost() = this.getConfig().getInt("archer.Msg.getCost()");
//    int Msg.getMinerCost() = this.getConfig().getInt("miner.Msg.getCost()");
//    int Msg.getBuilderCost() = this.getConfig().getInt("builder.Msg.getCost()");
//    int Msg.getTankCost() = this.getConfig().getInt("tank.Msg.getCost()");
//    int Msg.getDefenderCost() = this.getConfig().getInt("defender.Msg.getCost()");
//    int Msg.getAlchemistCost() = this.getConfig().getInt("alchemist.Msg.getCost()");
//    int Msg.getTeleporterCost() = this.getConfig().getInt("teleporter.Msg.getCost()");
//    int Msg.getResourcesCost() = this.getConfig().getInt("resources.Msg.getCost()");
//
//    String Msg.getWarriorName() = this.getConfig().getString("warrior.name");
//    String Msg.getArcherName() = this.getConfig().getString("archer.name");
//    String Msg.getMinerName() = this.getConfig().getString("miner.name");
//    String Msg.getBuilderName() = this.getConfig().getString("builder.name");
//    String Msg.getTankName() = this.getConfig().getString("tank.name");
//    String Msg.getDefenderName() = this.getConfig().getString("defender.name");
//    String Msg.getAlchemistName() = this.getConfig().getString("alchemist.name");
//    String Msg.getTeleporterName() = this.getConfig().getString("teleporter.name");
//    String Msg.getResourcesName() = this.getConfig().getString("resources.name");

//    String Msg.getKit_already_selected_message() = this.getConfig().getString("Msg.getKit_already_selected_message()");
//
//    String Msg.getKitselect_name() = this.getConfig().getString("Msg.getKitselect_name()");
//    String Msg.getKitbuy_name() = this.getConfig().getString("Msg.getKitbuy_name()");
//    String Msg.getKit_select_message() = this.getConfig().getString("Msg.getKit_select_message()");
//    String Msg.getNot_kit() = this.getConfig().getString("Msg.getNot_kit()");
//    String Msg.getCost() = this.getConfig().getString("Msg.getCost()");
//    String Msg.getBwkit() = this.getConfig().getString("Msg.getBwkit()");
//    String Msg.getCommand_console() = this.getConfig().getString("Msg.getCommand_console()");
//    String Msg.getPex_reset() = this.getConfig().getString("Msg.getPex_reset()");
//    String Msg.getBronze_name() = this.getConfig().getString("Msg.getBronze_name()");
//    String Msg.getIron_name() = this.getConfig().getString("Msg.getIron_name()");
//    String Msg.getTeleporter_countdown1() = this.getConfig().getString("Msg.getTeleporter_countdown1()");
//    String Msg.getTeleporter_countdown2() = this.getConfig().getString("Msg.getTeleporter_countdown2()");
//    String Msg.getTeleporter_countdown3() = this.getConfig().getString("Msg.getTeleporter_countdown3()");
//
//    String Msg.getBedwars() = this.getConfig().getString("Msg.getBedwars()");
//    String Msg.getKit_give() = this.getConfig().getString("Msg.getKit_give()");
//    String Msg.getNot_enought_money() = this.getConfig().getString("Msg.getNot_enought_money()");
//    String Msg.getAccept() = this.getConfig().getString("Msg.getAccept()");
//    String Msg.getAlready_buy() = this.getConfig().getString("Msg.getAlready_buy()");
//    String Msg.getBuy_now() = this.getConfig().getString("Msg.getBuy_now()");
//    String Msg.getAccept_no() = this.getConfig().getString("Msg.getAccept_no()");
//    String Msg.getAllow() = this.getConfig().getString("Msg.getAllow()");
//    String Msg.getBuy() = this.getConfig().getString("Msg.getBuy()");
//    String Msg.getBuy_allow() = this.getConfig().getString("Msg.getBuy_allow()");
//    String Msg.getPex_deny() = this.getConfig().getString("Msg.getPex_deny()");
//    String Msg.getCfgreload() = this.getConfig().getString("Msg.getCfgreload()");


    public void openGUI(Player player) { //Гуи выбора класса '/bwk'

//        String Msg.getWarriorName() = this.getConfig().getString("warrior.name");
        List<String> warrior_lore = this.getConfig().getStringList("warrior.lore");
        List<String> archer_lore = this.getConfig().getStringList("archer.lore");
        List<String> miner_lore = this.getConfig().getStringList("miner.lore");
        List<String> builder_lore = this.getConfig().getStringList("builder.lore");
        List<String> tank_lore = this.getConfig().getStringList("tank.lore");
        List<String> defender_lore = this.getConfig().getStringList("defender.lore");
        List<String> alchemist_lore = this.getConfig().getStringList("alchemist.lore");
        List<String> teleporter_lore = this.getConfig().getStringList("teleporter.lore");
        List<String> resources_lore = this.getConfig().getStringList("resources.lore");

        DyeColor color = DyeColor.GRAY; //Хз зачем
        byte data = 8; //Для слудующей строки
        byte data_pex = 9;
        Inventory inv = Bukkit.createInventory(null, 9, Msg.getKitselect_name()); //Создается инвентарь в 9 клеток (1 строка)

        List<String> lores = new ArrayList<String>(); //нужен для lore'а
// Воин
        //----- Набор куплен и доступен для выбора -----
        ItemStack warrior = new ItemStack(Material.IRON_SWORD); //Какой предмет будет отображаться
        ItemMeta warriorMeta = warrior.getItemMeta(); //хз зачем, но без него никак.

        warriorMeta.setDisplayName(Msg.getWarriorName()); //название айтема
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        warriorMeta.setLore(lores); //lore применяется к предмету
        warrior.setItemMeta(warriorMeta); //Хз зачем, но нужно.

        //----- Набор не куплен и не доступен для выбора -----
        ItemStack warrior_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta warrior_noMeta = warrior_no.getItemMeta();

        lores = new ArrayList<String>();
        warrior_noMeta.setDisplayName(Msg.getWarriorName());
        lores.add(Msg.getAccept_no());
        warrior_noMeta.setLore(lores);
        warrior_no.setItemMeta(warrior_noMeta);

        //----- Набор выбран -----
        ItemStack warrior_yes = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_yesMeta = warrior_yes.getItemMeta();

        lores = new ArrayList<String>();
        warrior_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        warrior_yesMeta.setDisplayName(Msg.getWarriorName());
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        warrior_yesMeta.setLore(lores);
        warrior_yes.setItemMeta(warrior_yesMeta);

// Воин

// Лучник
        ItemStack archer = new ItemStack(Material.BOW);
        ItemMeta archerMeta = archer.getItemMeta();


        archerMeta.setDisplayName(Msg.getArcherName());
        lores = new ArrayList<String>();
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        archerMeta.setLore(lores);
        archer.setItemMeta(archerMeta);

        ItemStack archer_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta archer_noMeta = archer_no.getItemMeta();

        lores = new ArrayList<String>();
        archer_noMeta.setDisplayName(Msg.getArcherName());
        lores.add(Msg.getPex_nope() + Msg.getVipdon());
        archer_noMeta.setLore(lores);
        archer_no.setItemMeta(archer_noMeta);

        ItemStack archer_yes = new ItemStack(Material.BOW); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta archer_yesMeta = archer_yes.getItemMeta();

        lores = new ArrayList<String>();
        archer_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        archer_yesMeta.setDisplayName(Msg.getArcherName());
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        archer_yesMeta.setLore(lores);
        archer_yes.setItemMeta(archer_yesMeta);

        //----- Нет прав -----
        ItemStack archer_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta archer_pexMeta = archer_pex.getItemMeta();

        lores = new ArrayList<String>();
        archer_pexMeta.setDisplayName(Msg.getArcherName());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        archer_pexMeta.setLore(lores);
        archer_pex.setItemMeta(archer_pexMeta);
// Лучник

// Шахтер

        ItemStack miner = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta minerMeta = miner.getItemMeta();

        minerMeta.setDisplayName(Msg.getMinerName());
        lores = new ArrayList<String>();
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        minerMeta.setLore(lores);
        miner.setItemMeta(minerMeta);

        ItemStack miner_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta miner_noMeta = miner_no.getItemMeta();

        lores = new ArrayList<String>();
        miner_noMeta.setDisplayName(Msg.getMinerName());
        lores.add(Msg.getAccept_no());
        miner_noMeta.setLore(lores);
        miner_no.setItemMeta(miner_noMeta);

        ItemStack miner_yes = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_yesMeta = miner_yes.getItemMeta();

        lores = new ArrayList<String>();
        miner_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        miner_yesMeta.setDisplayName(Msg.getMinerName());
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        miner_yesMeta.setLore(lores);
        miner_yes.setItemMeta(miner_yesMeta);
// Шахтер

// Строитель
        ItemStack builder = new ItemStack(Material.SANDSTONE);
        ItemMeta builderMeta = builder.getItemMeta();

        builderMeta.setDisplayName(Msg.getBuilderName());
        lores = new ArrayList<String>();
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        builderMeta.setLore(lores);
        builder.setItemMeta(builderMeta);

        ItemStack builder_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta builder_noMeta = builder_no.getItemMeta();

        lores = new ArrayList<String>();
        builder_noMeta.setDisplayName(Msg.getBuilderName());
        lores.add(Msg.getAccept_no());
        builder_noMeta.setLore(lores);
        builder_no.setItemMeta(builder_noMeta);

        ItemStack builder_yes = new ItemStack(Material.SANDSTONE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta builder_yesMeta = builder_yes.getItemMeta();

        lores = new ArrayList<String>();
        builder_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        builder_yesMeta.setDisplayName(Msg.getBuilderName());
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        builder_yesMeta.setLore(lores);
        builder_yes.setItemMeta(builder_yesMeta);
// Строитель

// Танк
        ItemStack tank = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tankMeta = tank.getItemMeta();

        tankMeta.setDisplayName(Msg.getTankName());
        lores = new ArrayList<String>();
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        tankMeta.setLore(lores);
        tank.setItemMeta(tankMeta);

        ItemStack tank_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta tank_noMeta = tank_no.getItemMeta();

        lores = new ArrayList<String>();
        tank_noMeta.setDisplayName(Msg.getTankName());
        lores.add(Msg.getAccept_no());
        tank_noMeta.setLore(lores);
        tank_no.setItemMeta(tank_noMeta);

        ItemStack tank_yes = new ItemStack(Material.CHAINMAIL_CHESTPLATE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta tank_yesMeta = tank_yes.getItemMeta();

        lores = new ArrayList<String>();
        tank_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        tank_yesMeta.setDisplayName(Msg.getTankName());
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        tank_yesMeta.setLore(lores);
        tank_yes.setItemMeta(tank_yesMeta);
// Танк

// Защитник
        ItemStack defender = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defenderMeta = defender.getItemMeta();

        defenderMeta.setDisplayName(Msg.getDefenderName());
        lores = new ArrayList<String>();
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        defenderMeta.setLore(lores);
        defender.setItemMeta(defenderMeta);

        ItemStack defender_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta defender_noMeta = defender_no.getItemMeta();

        lores = new ArrayList<String>();
        defender_noMeta.setDisplayName(Msg.getDefenderName());
        lores.add(Msg.getAccept_no());
        defender_noMeta.setLore(lores);
        defender_no.setItemMeta(defender_noMeta);

        ItemStack defender_yes = new ItemStack(Material.LEATHER_CHESTPLATE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta defender_yesMeta = defender_yes.getItemMeta();

        lores = new ArrayList<String>();
        defender_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        defender_yesMeta.setDisplayName(Msg.getDefenderName());
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        defender_yesMeta.setLore(lores);
        defender_yes.setItemMeta(defender_yesMeta);
// Защитник

// Алхимик
        ItemStack alchemist = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemistMeta = alchemist.getItemMeta();

        alchemistMeta.setDisplayName(Msg.getAlchemistName());
        lores = new ArrayList<String>();
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        alchemistMeta.setLore(lores);
        alchemist.setItemMeta(alchemistMeta);

        ItemStack alchemist_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta alchemist_noMeta = alchemist_no.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_noMeta.setDisplayName(Msg.getAlchemistName());
        lores.add(Msg.getAccept_no());
        alchemist_noMeta.setLore(lores);
        alchemist_no.setItemMeta(alchemist_noMeta);

        ItemStack alchemist_yes = new ItemStack(Material.BREWING_STAND_ITEM); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta alchemist_yesMeta = alchemist_yes.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        alchemist_yesMeta.setDisplayName(Msg.getAlchemistName());
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        alchemist_yesMeta.setLore(lores);
        alchemist_yes.setItemMeta(alchemist_yesMeta);

        //----- Нет прав -----
        ItemStack alchemist_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta alchemist_pexMeta = alchemist_pex.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_pexMeta.setDisplayName(Msg.getAlchemistName());
        lores.add(Msg.getPex_nope() + Msg.getVipdon());
        alchemist_pexMeta.setLore(lores);
        alchemist_pex.setItemMeta(alchemist_pexMeta);
// Алхимик

// Телепортер
        ItemStack teleporter = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporterMeta = teleporter.getItemMeta();

        teleporterMeta.setDisplayName(Msg.getTeleporterName());
        lores = new ArrayList<String>();
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        teleporterMeta.setLore(lores);
        teleporter.setItemMeta(teleporterMeta);

        ItemStack teleporter_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta teleporter_noMeta = teleporter_no.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_noMeta.setDisplayName(Msg.getTeleporterName());
        lores.add(Msg.getAccept_no());
        teleporter_noMeta.setLore(lores);
        teleporter_no.setItemMeta(teleporter_noMeta);

        ItemStack teleporter_yes = new ItemStack(Material.ENDER_PEARL); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta teleporter_yesMeta = teleporter_yes.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        teleporter_yesMeta.setDisplayName(Msg.getTeleporterName());
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        teleporter_yesMeta.setLore(lores);
        teleporter_yes.setItemMeta(teleporter_yesMeta);

        //----- Нет прав -----
        ItemStack teleporter_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta teleporter_pexMeta = teleporter_pex.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_pexMeta.setDisplayName(Msg.getTeleporterName());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        teleporter_pexMeta.setLore(lores);
        teleporter_pex.setItemMeta(teleporter_pexMeta);
// Телепортер

// Легкий старт
        ItemStack resources = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resourcesMeta = resources.getItemMeta();

        resourcesMeta.setDisplayName(Msg.getResourcesName());
        lores = new ArrayList<String>();
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(Msg.getAccept());
        resourcesMeta.setLore(lores);
        resources.setItemMeta(resourcesMeta);

        ItemStack resources_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta resources_noMeta = resources_no.getItemMeta();

        lores = new ArrayList<String>();
        resources_noMeta.setDisplayName(Msg.getResourcesName());
        lores.add(Msg.getAccept_no());
        resources_noMeta.setLore(lores);
        resources_no.setItemMeta(resources_noMeta);

        ItemStack resources_yes = new ItemStack(Material.DOUBLE_PLANT); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta resources_yesMeta = resources_yes.getItemMeta();

        lores = new ArrayList<String>();
        resources_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        resources_yesMeta.setDisplayName(Msg.getResourcesName());
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(Msg.getAllow());
        resources_yesMeta.setLore(lores);
        resources_yes.setItemMeta(resources_yesMeta);

        //----- Нет прав -----
        ItemStack resources_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta resources_pexMeta = resources_pex.getItemMeta();

        lores = new ArrayList<String>();
        resources_pexMeta.setDisplayName(Msg.getResourcesName());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        resources_pexMeta.setLore(lores);
        resources_pex.setItemMeta(resources_pexMeta);
// Легкий старт
        if (player.hasPermission("bw.buykit.warrior")) {
            if (player.hasPermission("warrior1")) {
                inv.setItem(0, warrior_yes);
            }
            else
                inv.setItem(0, warrior);
        } else if (!player.isPermissionSet("bw.buykit.warrior"))
            inv.setItem(0, warrior_no);

        if (player.hasPermission("bw.buykit.archer")) {
            if (player.hasPermission("archer1"))
                inv.setItem(1, archer_yes);
            else
                inv.setItem(1, archer);
        } else if (!player.isPermissionSet("bw.buykit.archer")) {
            if (!player.hasPermission("bw.kit.d_archer"))
                inv.setItem(1, archer_pex);
            else
                inv.setItem(1, archer_no);
        }

        if (player.hasPermission("bw.buykit.miner")) {
            if (player.hasPermission("miner1"))
                inv.setItem(2, miner_yes);
            else
                inv.setItem(2, miner);
        } else if (!player.isPermissionSet("bw.buykit.miner"))
            inv.setItem(2, miner_no);

        if (player.hasPermission("bw.buykit.builder")) {
            if (player.hasPermission("builder1"))
                inv.setItem(3, builder_yes);
            else
                inv.setItem(3, builder);
        } else if (!player.isPermissionSet("bw.buykit.builder"))
            inv.setItem(3, builder_no);

        if (player.hasPermission("bw.buykit.tank")) {
            if (player.hasPermission("tank1"))
                inv.setItem(4, tank_yes);
            else
                inv.setItem(4, tank);
        } else if (!player.isPermissionSet("bw.buykit.tank"))
            inv.setItem(4, tank_no);

        if (player.hasPermission("bw.buykit.defender")) {
            if (player.hasPermission("defender1"))
                inv.setItem(5, defender_yes);
            else
                inv.setItem(5, defender);
        } else if (!player.isPermissionSet("bw.buykit.defender"))
            inv.setItem(5, defender_no);

        if (player.hasPermission("bw.buykit.alchemist")) {
            if (player.hasPermission("alchemist1"))
                inv.setItem(6, alchemist_yes);
            else
                inv.setItem(6, alchemist);
        } else if (!player.isPermissionSet("bw.buykit.alchemist")) {
            if (!player.hasPermission("bw.kit.d_alchemist"))
                inv.setItem(6, alchemist_pex);
            else
                inv.setItem(6, alchemist_no);
        }

        if (player.hasPermission("bw.buykit.teleporter")) {
            if (player.hasPermission("teleporter1"))
                inv.setItem(7, teleporter_yes);
            else
                inv.setItem(7, teleporter);
        } else if (!player.isPermissionSet("bw.buykit.teleporter")) {
            if (!player.hasPermission("bw.kit.d_teleporter"))
                inv.setItem(7, teleporter_pex);
            else
                inv.setItem(7, teleporter_no);
        }

        if (player.hasPermission("bw.buykit.resources")) {
            if (player.hasPermission("resources1"))
                inv.setItem(8, resources_yes);
            else
                inv.setItem(8, resources);
        } else if (!player.isPermissionSet("bw.buykit.resources")) {
            if (!player.hasPermission("bw.kit.d_resources"))
                inv.setItem(8, resources_pex);
            else
                inv.setItem(8, resources_no);
        }

        //inv.setItem(0, warrior);
        //inv.setItem(1, archer); //Воин, Лучник, Шахтер, Строитель, Танк, Защитник, Алхимик, Телепортер, Легкий старт
        //inv.setItem(2, miner);
        //inv.setItem(3, builder);
        //inv.setItem(4, tank);
        //inv.setItem(5, defender);
        //inv.setItem(6, alchemist);
        //inv.setItem(7, teleporter);
        //inv.setItem(8, resources);
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Kit Selector"))
            return;
        ;
        Player player = (Player) event.getWhoClicked();
        Permissible perm = (Permissible) event.getWhoClicked();
        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            player.closeInventory();
            return;
        }
        switch (event.getCurrentItem().getType()) {
            case IRON_SWORD:
                if (player.hasPermission("warrior1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getWarriorName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getWarriorName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.d_teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.d_alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.d_archer");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.d_resources");
                    PermissionsEx.getUser(player).addPermission("warrior1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case BOW:
                if (player.hasPermission("archer1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getArcherName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getArcherName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("archer1"); //True
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case IRON_PICKAXE:
                if (player.hasPermission("miner1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getMinerName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getMinerName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("miner1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case SANDSTONE:
                if (player.hasPermission("builder1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getBuilderName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getBuilderName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("builder1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case CHAINMAIL_CHESTPLATE:
                if (player.hasPermission("tank1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getTankName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getTankName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("tank1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case LEATHER_CHESTPLATE:
                if (player.hasPermission("defender1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getDefenderName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getDefenderName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("defender1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case BREWING_STAND_ITEM:
                if (player.hasPermission("alchemist1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getAlchemistName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getAlchemistName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("alchemist1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case ENDER_PEARL:
                if (player.hasPermission("teleporter1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getTeleporterName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getTeleporterName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    PermissionsEx.getUser(player).addPermission("teleporter1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    openGUI(player);
                    return;
                }
                break;
            case DOUBLE_PLANT:
                if (player.hasPermission("resources1")) {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getKit_already_selected_message(), Msg.getResourcesName()));
                }
                else {
                    player.sendMessage(Msg.getBedwars() + Msg.getKit_select_message() + Msg.getResourcesName());
                    PermissionsEx.getUser(player).addPermission("bw.kit.resources");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).addPermission("resources1"); //True
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    openGUI(player);
                    return;
                }
                break;
            case INK_SACK:
                player.closeInventory();
                player.sendMessage(Msg.getBedwars() + Msg.getNot_kit());
                break;
            default:
                player.closeInventory();
                break;
        }
    }

    public void openGUI_shop(Player player) {
        DyeColor color = DyeColor.GRAY;
        byte data = 8;

//        String Msg.getWarriorName() = this.getConfig().getString("warrior.name");
        List<String> warrior_lore = this.getConfig().getStringList("warrior.lore");
        List<String> archer_lore = this.getConfig().getStringList("archer.lore");
        List<String> miner_lore = this.getConfig().getStringList("miner.lore");
        List<String> builder_lore = this.getConfig().getStringList("builder.lore");
        List<String> tank_lore = this.getConfig().getStringList("tank.lore");
        List<String> defender_lore = this.getConfig().getStringList("defender.lore");
        List<String> alchemist_lore = this.getConfig().getStringList("alchemist.lore");
        List<String> teleporter_lore = this.getConfig().getStringList("teleporter.lore");
        List<String> resources_lore = this.getConfig().getStringList("resources.lore");
        Inventory inv = Bukkit.createInventory(null, 9, Msg.getKitbuy_name());

        List<String> lores = new ArrayList<String>();
// Воин
        ItemStack warrior_buy = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_buyMeta = warrior_buy.getItemMeta();

        warrior_buyMeta.setDisplayName(Msg.getWarriorName());
        player.sendMessage(Msg.getWarriorName());
        warrior_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        warrior_buyMeta.setLore(lores);
        warrior_buy.setItemMeta(warrior_buyMeta);

        ItemStack warrior_view = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_viewMeta = warrior_view.getItemMeta();

        lores = new ArrayList<String>();
        warrior_viewMeta.setDisplayName(Msg.getWarriorName());
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getWarriorCost());
        lores.add(Msg.getBuy());
        warrior_viewMeta.setLore(lores);
        warrior_view.setItemMeta(warrior_viewMeta);
// Воин

// Лучник
        ItemStack archer_buy = new ItemStack(Material.BOW);
        ItemMeta archer_buyMeta = archer_buy.getItemMeta();

        archer_buyMeta.setDisplayName(Msg.getArcherName());
        archer_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        archer_buyMeta.setLore(lores);
        archer_buy.setItemMeta(archer_buyMeta);

        ItemStack archer_view = new ItemStack(Material.BOW);
        ItemMeta archer_viewMeta = archer_view.getItemMeta();

        lores = new ArrayList<String>();
        archer_viewMeta.setDisplayName(Msg.getArcherName());
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getArcherCost());
        lores.add(Msg.getBuy());
        archer_viewMeta.setLore(lores);
        archer_view.setItemMeta(archer_viewMeta);

        ItemStack archer_pex = new ItemStack(Material.BOW);
        ItemMeta archer_pexMeta = archer_pex.getItemMeta();

        lores = new ArrayList<String>();
        archer_pexMeta.setDisplayName(Msg.getArcherName());
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getArcherCost());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        archer_pexMeta.setLore(lores);
        archer_pex.setItemMeta(archer_pexMeta);
// Лучник

// Шахтер
        ItemStack miner_buy = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_buyMeta = miner_buy.getItemMeta();

        miner_buyMeta.setDisplayName(Msg.getMinerName());
        miner_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        miner_buyMeta.setLore(lores);
        miner_buy.setItemMeta(miner_buyMeta);

        ItemStack miner_view = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_viewMeta = miner_view.getItemMeta();

        lores = new ArrayList<String>();
        miner_viewMeta.setDisplayName(Msg.getMinerName());
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getMinerCost());
        lores.add(Msg.getBuy());
        miner_viewMeta.setLore(lores);
        miner_view.setItemMeta(miner_viewMeta);
// Шахтер

// Строитель
        ItemStack builder_buy = new ItemStack(Material.SANDSTONE);
        ItemMeta builder_buyMeta = builder_buy.getItemMeta();

        builder_buyMeta.setDisplayName(Msg.getBuilderName());
        builder_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        builder_buyMeta.setLore(lores);
        builder_buy.setItemMeta(builder_buyMeta);

        ItemStack builder_view = new ItemStack(Material.SANDSTONE);
        ItemMeta builder_viewMeta = builder_view.getItemMeta();

        lores = new ArrayList<String>();
        builder_viewMeta.setDisplayName(Msg.getBuilderName());
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getBuilderCost());
        lores.add(Msg.getBuy());
        builder_viewMeta.setLore(lores);
        builder_view.setItemMeta(builder_viewMeta);
// Строитель

// Танк
        ItemStack tank_buy = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_buyMeta = tank_buy.getItemMeta();

        tank_buyMeta.setDisplayName(Msg.getTankName());
        tank_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        tank_buyMeta.setLore(lores);
        tank_buy.setItemMeta(tank_buyMeta);

        ItemStack tank_view = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_viewMeta = tank_view.getItemMeta();

        lores = new ArrayList<String>();
        tank_viewMeta.setDisplayName(Msg.getTankName());
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getTankCost());
        lores.add(Msg.getBuy());
        tank_viewMeta.setLore(lores);
        tank_view.setItemMeta(tank_viewMeta);
// Танк

// Защитник
        ItemStack defender_buy = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_buyMeta = defender_buy.getItemMeta();

        defender_buyMeta.setDisplayName(Msg.getDefenderName());
        defender_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        defender_buyMeta.setLore(lores);
        defender_buy.setItemMeta(defender_buyMeta);

        ItemStack defender_view = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_viewMeta = defender_view.getItemMeta();

        lores = new ArrayList<String>();
        defender_viewMeta.setDisplayName(Msg.getDefenderName());
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getDefenderCost());
        lores.add(Msg.getBuy());
        defender_viewMeta.setLore(lores);
        defender_view.setItemMeta(defender_viewMeta);
// Защитник

// Алхимик
        ItemStack alchemist_buy = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_buyMeta = alchemist_buy.getItemMeta();

        alchemist_buyMeta.setDisplayName(Msg.getAlchemistName());
        alchemist_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        alchemist_buyMeta.setLore(lores);
        alchemist_buy.setItemMeta(alchemist_buyMeta);

        ItemStack alchemist_view = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_viewMeta = alchemist_view.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_viewMeta.setDisplayName(Msg.getAlchemistName());
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getAlchemistCost());
        lores.add(Msg.getBuy());
        alchemist_viewMeta.setLore(lores);
        alchemist_view.setItemMeta(alchemist_viewMeta);

        ItemStack alchemist_pex = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_pexMeta = alchemist_pex.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_pexMeta.setDisplayName(Msg.getAlchemistName());
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getAlchemistCost());
        lores.add(Msg.getPex_nope() + Msg.getVipdon());
        alchemist_pexMeta.setLore(lores);
        alchemist_pex.setItemMeta(alchemist_pexMeta);
// Алхимик

// Телепортер
        ItemStack teleporter_buy = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_buyMeta = teleporter_buy.getItemMeta();

        teleporter_buyMeta.setDisplayName(Msg.getTeleporterName());
        teleporter_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        teleporter_buyMeta.setLore(lores);
        teleporter_buy.setItemMeta(teleporter_buyMeta);

        ItemStack teleporter_view = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_viewMeta = teleporter_view.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_viewMeta.setDisplayName(Msg.getTeleporterName());
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getTeleporterCost());
        lores.add(Msg.getBuy());
        teleporter_viewMeta.setLore(lores);
        teleporter_view.setItemMeta(teleporter_viewMeta);

        ItemStack teleporter_pex = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_pexMeta = teleporter_pex.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_pexMeta.setDisplayName(Msg.getTeleporterName());
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getTeleporterCost());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        teleporter_pexMeta.setLore(lores);
        teleporter_pex.setItemMeta(teleporter_pexMeta);
// Телепортер

// Легкий старт
        ItemStack resources_buy = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_buyMeta = resources_buy.getItemMeta();

        resources_buyMeta.setDisplayName(Msg.getResourcesName());
        resources_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(Msg.getBuy_allow());
        resources_buyMeta.setLore(lores);
        resources_buy.setItemMeta(resources_buyMeta);

        ItemStack resources_view = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_viewMeta = resources_view.getItemMeta();

        lores = new ArrayList<String>();
        resources_viewMeta.setDisplayName(Msg.getResourcesName());
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getResourcesCost());
        lores.add(Msg.getBuy());
        resources_viewMeta.setLore(lores);
        resources_view.setItemMeta(resources_viewMeta);

        ItemStack resources_pex = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_pexMeta = resources_pex.getItemMeta();

        lores = new ArrayList<String>();
        resources_pexMeta.setDisplayName(Msg.getResourcesName());
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(Msg.getCost()  + Msg.getResourcesCost());
        lores.add(Msg.getPex_nope() + Msg.getPlatinumdon());
        resources_pexMeta.setLore(lores);
        resources_pex.setItemMeta(resources_pexMeta);
// Легкий старт

        if (player.hasPermission("*")){
            if (!player.hasPermission("-bw.bypass")){
                PermissionsEx.getUser(player).addPermission("-bw.bypass");
            }
        }

        if (player.hasPermission("bw.buykit.warrior"))
            inv.setItem(0, warrior_buy);
        else if (!player.isPermissionSet("bw.buykit.warrior"))
            inv.setItem(0, warrior_view);

        if (player.hasPermission("bw.buykit.archer"))
            inv.setItem(1, archer_buy);
        else if (!player.isPermissionSet("bw.buykit.archer")) {
            if (!player.isPermissionSet("bw.buykit.d_archer"))
                inv.setItem(1, archer_pex);
            else
                inv.setItem(1, archer_view);
        }
        if (player.hasPermission("bw.buykit.miner"))
            inv.setItem(2, miner_buy);
        else if (!player.isPermissionSet("bw.buykit.miner"))
            inv.setItem(2, miner_view);

        if (player.hasPermission("bw.buykit.builder"))
            inv.setItem(3, builder_buy);
        else if (!player.isPermissionSet("bw.buykit.builder"))
            inv.setItem(3, builder_view);

        if (player.hasPermission("bw.buykit.tank"))
            inv.setItem(4, tank_buy);
        else if (!player.isPermissionSet("bw.buykit.tank"))
            inv.setItem(4, tank_view);

        if (player.hasPermission("bw.buykit.defender"))
            inv.setItem(5, defender_buy);
        else if (!player.isPermissionSet("bw.buykit.defender"))
            inv.setItem(5, defender_view);

        if (player.hasPermission("bw.buykit.alchemist"))
            inv.setItem(6, alchemist_buy);
        else if (!player.isPermissionSet("bw.buykit.alchemist")) {
            if (!player.isPermissionSet("bw.buykit.d_alchemist"))
                inv.setItem(6, alchemist_pex);
            else
                inv.setItem(6, alchemist_view);
        }

        if (player.hasPermission("bw.buykit.teleporter"))
            inv.setItem(7, teleporter_buy);
        else if (!player.isPermissionSet("bw.buykit.teleporter")) {
            if (!player.isPermissionSet("bw.buykit.d_teleporter"))
                inv.setItem(7, teleporter_pex);
            else
                inv.setItem(7, teleporter_view);
        }

        if (player.hasPermission("bw.buykit.resources"))
            inv.setItem(8, resources_buy);
        else if (!player.isPermissionSet("bw.buykit.resources")) {
            if (!player.isPermissionSet("bw.buykit.d_resources"))
                inv.setItem(8, resources_pex);
            else
                inv.setItem(8, resources_view);
        }
        //inv.setItem(0, warrior);
        //inv.setItem(1, archer); //Воин, Лучник, Шахтер, Строитель, Танк, Защитник, Алхимик, Телепортер, Легкий старт
        //inv.setItem(2, miner);
        //inv.setItem(3, builder);
        //inv.setItem(4, tank);
        //inv.setItem(5, defender);
        //inv.setItem(6, alchemist);
        //inv.setItem(7, teleporter);
        //inv.setItem(8, resources);
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClickShop(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Kit Shop"))
            return;
        ;
        setupEconomy();
        Player player = (Player) event.getWhoClicked();
        double balance = economy.getBalance(player);

        Permissible perm = (Permissible) event.getWhoClicked();
        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            player.closeInventory();
            return;
        }
        switch (event.getSlot()) {
            case 0:
                if (!player.isPermissionSet("bw.buykit.warrior")){
                    if (balance >= Msg.getWarriorCost()) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, Msg.getWarriorCost()); //depositPlayer - выдать | withdrawPlayer - снять
                        player.sendMessage(String.format(Msg.getBuy_now(), Msg.getWarriorName()));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.warrior");
                    } else {
                        player.closeInventory();
                        player.sendMessage(Msg.getNot_enought_money());
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getWarriorName()));
                }
                break;
            case 1:
                if (!player.isPermissionSet("bw.buykit.archer")){
                    if (!player.isPermissionSet("bw.buykit.d_archer")) {
                        player.closeInventory();
                        player.sendMessage(Msg.getBedwars() + Msg.getPex_nope() + Msg.getPlatinumdon());
                    }
                    else {
                        if (balance >= Msg.getArcherCost()) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, Msg.getArcherCost());
                            player.sendMessage(String.format(Msg.getBuy_now(), Msg.getArcherName()));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.archer");
                        } else {
                            player.closeInventory();
                            player.sendMessage(Msg.getNot_enought_money());
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getArcherName()));
                }
                break;
            case 2:
                if (!player.isPermissionSet("bw.buykit.miner")){
                    if (balance >= Msg.getMinerCost()) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, Msg.getMinerCost());
                        player.sendMessage(String.format(Msg.getBuy_now(), Msg.getMinerName()));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.miner");
                    } else {
                        player.closeInventory();
                        player.sendMessage(Msg.getNot_enought_money());
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getMinerName()));
                }
                break;
            case 3:
                if (!player.isPermissionSet("bw.buykit.builder")){
                    if (balance >= Msg.getBuilderCost()) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, Msg.getBuilderCost());
                        player.sendMessage(String.format(Msg.getBuy_now(), Msg.getBuilderName()));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.builder");
                    } else {
                        player.closeInventory();
                        player.sendMessage(Msg.getNot_enought_money());
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getBuilderName()));
                }
                break;
            case 4:
                if (!player.isPermissionSet("bw.buykit.tank")){
                    if (balance >= Msg.getTankCost()) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, Msg.getTankCost());
                        player.sendMessage(String.format(Msg.getBuy_now(), Msg.getTankName()));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.tank");
                    } else {
                        player.closeInventory();
                        player.sendMessage(Msg.getNot_enought_money());
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getTankName()));
                }
                break;
            case 5:
                if (!player.isPermissionSet("bw.buykit.defender")){
                    if (balance >= Msg.getDefenderCost()) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, Msg.getDefenderCost());
                        player.sendMessage(String.format(Msg.getBuy_now(), Msg.getDefenderName()));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.defender");
                    } else {
                        player.closeInventory();
                        player.sendMessage(Msg.getNot_enought_money());
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getDefenderName()));
                }
                break;
            case 6:
                if (!player.isPermissionSet("bw.buykit.alchemist")){
                    if (!player.isPermissionSet("bw.buykit.d_alchemist")) {
                        player.closeInventory();
                        player.sendMessage(Msg.getBedwars() + Msg.getPex_nope() + Msg.getVipdon());
                    }
                    else {
                        if (balance >= Msg.getAlchemistCost()) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, Msg.getAlchemistCost());
                            player.sendMessage(String.format(Msg.getBuy_now(), Msg.getAlchemistName()));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.alchemist");
                        } else {
                            player.closeInventory();
                            player.sendMessage(Msg.getNot_enought_money());
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getAlchemistName()));
                }
                break;
            case 7:
                if (!player.isPermissionSet("bw.buykit.teleporter")){
                    if (!player.isPermissionSet("bw.buykit.d_teleporter")) {
                        player.closeInventory();
                        player.sendMessage(Msg.getBedwars() + Msg.getPex_nope() + Msg.getPlatinumdon());
                    }
                    else {
                        if (balance >= Msg.getTeleporterCost()) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, Msg.getTeleporterCost());
                            player.sendMessage(String.format(Msg.getBuy_now(), Msg.getTeleporterName()));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.teleporter");
                        } else {
                            player.closeInventory();
                            player.sendMessage(Msg.getNot_enought_money());
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getTeleporterName()));
                }
                break;
            case 8:
                if (!player.isPermissionSet("bw.buykit.resources")){
                    if (!player.isPermissionSet("bw.buykit.d_resources")) {
                        player.closeInventory();
                        player.sendMessage(Msg.getBedwars() + Msg.getPex_nope() + Msg.getPlatinumdon());
                    }
                    else {
                        if (balance >= Msg.getResourcesCost()) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, Msg.getResourcesCost());
                            player.sendMessage(String.format(Msg.getBuy_now(), Msg.getResourcesName()));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.resources");
                        } else {
                            player.closeInventory();
                            player.sendMessage(Msg.getNot_enought_money());
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(Msg.getAlready_buy(), Msg.getResourcesName()));
                }
                break;
            default:
                player.closeInventory();
                break;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(cmd.getName().equalsIgnoreCase("bwreload") && args.length == 0) {
                reloadConfig();
                getServer().getConsoleSender().sendMessage(Msg.getBwkit() + Msg.getCfgreload());
            }
            else
                getServer().getConsoleSender().sendMessage(Msg.getCommand_console());
        }
        else {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("bwkitclear") && args.length == 0) {
                if (player.hasPermission("bwc")) {
                    PermissionsEx.getUser(player).removePermission("bw.buykit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.buykit.resources");
                    PermissionsEx.getUser(player).removePermission("bw.kit.warrior");
                    PermissionsEx.getUser(player).removePermission("bw.kit.archer");
                    PermissionsEx.getUser(player).removePermission("bw.kit.miner");
                    PermissionsEx.getUser(player).removePermission("bw.kit.builder");
                    PermissionsEx.getUser(player).removePermission("bw.kit.tank");
                    PermissionsEx.getUser(player).removePermission("bw.kit.defender");
                    PermissionsEx.getUser(player).removePermission("bw.kit.alchemist");
                    PermissionsEx.getUser(player).removePermission("bw.kit.teleporter");
                    PermissionsEx.getUser(player).removePermission("bw.kit.resources");
                    //
                    PermissionsEx.getUser(player).removePermission("warrior1");
                    PermissionsEx.getUser(player).removePermission("archer1");
                    PermissionsEx.getUser(player).removePermission("builder1");
                    PermissionsEx.getUser(player).removePermission("tank1");
                    PermissionsEx.getUser(player).removePermission("defender1");
                    PermissionsEx.getUser(player).removePermission("alchemist1");
                    PermissionsEx.getUser(player).removePermission("miner1");
                    PermissionsEx.getUser(player).removePermission("teleporter1");
                    PermissionsEx.getUser(player).removePermission("resources1");
                    player.sendMessage(Msg.getBwkit() + Msg.getPex_reset());
                } else
                    player.sendMessage(Msg.getPex_deny());
            }
            if(cmd.getName().equalsIgnoreCase("bwshopgui") && args.length == 0)
                openGUI_shop(player);
            if(cmd.getName().equalsIgnoreCase("bwselectkit") && args.length == 0)
                openGUI(player);
            if(cmd.getName().equalsIgnoreCase("bwreload") && args.length == 0) {
                reloadConfig();
                player.sendMessage(Msg.getBwkit() + Msg.getCfgreload());
            }
            return true;
        }
        return false;
    }


    @EventHandler
    public void onPlayerInteractShop(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;

        if (is.getType() == Material.EMERALD)
            openGUI_shop(event.getPlayer());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();
        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;

        if (is.getType() == Material.NAME_TAG)
            openGUI(event.getPlayer());
    }

    @EventHandler
    public void onWoldChange(PlayerChangedWorldEvent event) throws InterruptedException {

        //List<String> lores = new ArrayList<String>();
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        //player.sendMessage("Мир: " + event.getPlayer().getWorld().getName());

        // Воин
        ItemStack warrior_item1 = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_item1Meta =  warrior_item1.getItemMeta();
        warrior_item1Meta.setDisplayName(Msg.getWarriorName());
        warrior_item1Meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        warrior_item1.setItemMeta(warrior_item1Meta);
        // Воин

        // Лучник
        ItemStack archer_item1 = new ItemStack(Material.BOW);
        ItemMeta archer_item1Meta =  archer_item1.getItemMeta();
        ItemStack archer_item2 = new ItemStack(Material.ARROW, 32);
        ItemMeta archer_item2Meta =  archer_item2.getItemMeta();
        archer_item1Meta.setDisplayName(Msg.getArcherName());
        archer_item1.setItemMeta(archer_item1Meta);
        archer_item2Meta.setDisplayName(Msg.getArcherName());
        archer_item2.setItemMeta(archer_item2Meta);
        // Лучник

        // Шахтер
        ItemStack miner_item1 = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_item1Meta =  miner_item1.getItemMeta();
        miner_item1Meta.setDisplayName(Msg.getMinerName());
        miner_item1Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        miner_item1.setItemMeta(miner_item1Meta);
        // Шахтер

        // Строитель
        ItemStack builder_item1 = new ItemStack(Material.SANDSTONE, 64);
        ItemMeta builder_item1Meta =  builder_item1.getItemMeta();
        builder_item1Meta.setDisplayName(Msg.getBuilderName());
        builder_item1.setItemMeta(builder_item1Meta);
        // Строитель

        // Танк
        ItemStack tank_item1 = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta tank_item1Meta =  tank_item1.getItemMeta();
        tank_item1Meta.setDisplayName(Msg.getTankName());
        tank_item1.setItemMeta(tank_item1Meta);

        ItemStack tank_item2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_item2Meta =  tank_item2.getItemMeta();
        tank_item2Meta.setDisplayName(Msg.getTankName());
        tank_item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        tank_item2.setItemMeta(tank_item2Meta);

        ItemStack tank_item3 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta tank_item3Meta =  tank_item3.getItemMeta();
        tank_item3Meta.setDisplayName(Msg.getTankName());
        tank_item3.setItemMeta(tank_item3Meta);

        ItemStack tank_item4 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta tank_item4Meta =  tank_item4.getItemMeta();
        tank_item4Meta.setDisplayName(Msg.getTankName());
        tank_item4.setItemMeta(tank_item4Meta);
        // Танк

        // Защитник
        ItemStack defender_item1 = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta defender_item1Meta =  defender_item1.getItemMeta();
        defender_item1Meta.setDisplayName(Msg.getDefenderName());
        defender_item1.setItemMeta(defender_item1Meta);

        ItemStack defender_item2 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_item2Meta =  defender_item2.getItemMeta();
        defender_item2Meta.setDisplayName(Msg.getDefenderName());
        defender_item2.setItemMeta(defender_item2Meta);

        ItemStack defender_item3 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta defender_item3Meta =  defender_item3.getItemMeta();
        defender_item3Meta.setDisplayName(Msg.getDefenderName());
        defender_item3.setItemMeta(defender_item3Meta);

        ItemStack defender_item4 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta defender_item4Meta =  defender_item4.getItemMeta();
        defender_item4Meta.setDisplayName(Msg.getDefenderName());
        defender_item4.setItemMeta(defender_item4Meta);

        ItemStack defender_item5 = new ItemStack(Material.WOOD_SWORD);
        ItemMeta defender_item5Meta =  defender_item5.getItemMeta();
        defender_item5Meta.setDisplayName(Msg.getDefenderName());
        defender_item5.setItemMeta(defender_item4Meta);
        // Защитник

        // Алхимик
        Potion potion1 = new Potion(PotionType.SPEED, 1);
        potion1.setSplash(true);
        ItemStack potion = potion1.toItemStack(1);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(Msg.getAlchemistName());
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 0), true);
        potion.setItemMeta(meta);
        // Алхимик

        // Телепортер
        ItemStack teleporter_item1 = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_item1Meta =  teleporter_item1.getItemMeta();
        teleporter_item1Meta.setDisplayName(Msg.getTeleporterName());
        teleporter_item1.setItemMeta(teleporter_item1Meta);
        // Телепортер

        // Легкий старт
        ItemStack resources_item1 = new ItemStack(Material.BRICK, 64);
        ItemMeta resources_item1Meta =  resources_item1.getItemMeta();
        resources_item1Meta.setDisplayName(Msg.getBronze_name());
        resources_item1.setItemMeta(resources_item1Meta);

        ItemStack resources_item2 = new ItemStack(Material.IRON_INGOT, 4);
        ItemMeta resources_item2Meta =  resources_item2.getItemMeta();
        resources_item2Meta.setDisplayName(Msg.getIron_name());
        resources_item2.setItemMeta(resources_item2Meta);
        // Легкий старт

        /*
        List<String> lores = new ArrayList<String>();
        ItemStack wa = new ItemStack(Material.IRON_SWORD);
        ItemMeta poc1Meta = poc1.getItemMeta();

        poc1Meta.setDisplayName(Msg.getWarriorName());
        poc1Meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        lores.add(arrow + warrior_item1
        poc1Meta.setLore(lores);

        poc1.setItemMeta(poc1Meta); //Church Pirate Tipis Pilz China
        */
        List<String> maps = this.getConfig().getStringList("maps");
        for (String s : maps) {
            if (player.getWorld().getName().equalsIgnoreCase(s)) {
                if (player.hasPermission("bw.kit.warrior")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getWarriorName());
                        inv.setItemInHand(warrior_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.archer")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getArcherName());
                        inv.setItem(0, archer_item1);
                        inv.setItem(1, archer_item2);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.miner")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getMinerName());
                        inv.setItemInHand(miner_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.builder")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getBuilderName());
                        inv.setItemInHand(builder_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.tank")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getTankName());
                        inv.setItem(39, tank_item1);
                        inv.setItem(38, tank_item2);
                        inv.setItem(37, tank_item3);
                        inv.setItem(36, tank_item4);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.defender")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getDefenderName());
                        inv.setItem(39, defender_item1);
                        inv.setItem(38, defender_item2);
                        inv.setItem(37, defender_item3);
                        inv.setItem(36, defender_item4);
                        inv.setItemInHand(defender_item5);
                    }, 10L);
                } else if (player.hasPermission("bw.kit.alchemist")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getAlchemistName());
                        inv.setItem(0, potion);
                    }, 10L);
                } else if (player.hasPermission("bw.kit.teleporter")) {
                    player.sendMessage(Msg.getBedwars() + Msg.getTeleporter_countdown1());
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {

                        player.sendMessage(Msg.getBedwars() + Msg.getTeleporter_countdown2());
                        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {

                            player.sendMessage(Msg.getBedwars() + Msg.getTeleporter_countdown3());
                            getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                                player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getTeleporterName());
                                inv.setItemInHand(teleporter_item1);
                            }, 300L);
                        }, 400L);
                    }, 600L);
                } else if (player.hasPermission("bw.kit.resources")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(Msg.getBedwars() + Msg.getKit_give() + Msg.getResourcesName());
                        inv.setItem(39, tank_item1);
                        inv.setItem(1, archer_item2);
                    }, 10L);
                }
            }
        }
        //World world = player.getWorld();
        //player.sendMessage(world.getName());
    }
}
