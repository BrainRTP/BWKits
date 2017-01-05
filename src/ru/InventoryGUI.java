package ru;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

public class InventoryGUI extends JavaPlugin implements Listener {
    private static Plugin plugin;
    private FileConfiguration data;
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        File dataFile = new File(getDataFolder() + File.separator + "config.yml");
        if (!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getServer().getConsoleSender().sendMessage("§c==============§a BWKits v1.0 §c==============");
        getServer().getConsoleSender().sendMessage("§aThe plugin has been successfully enabled!");
        getServer().getConsoleSender().sendMessage("§c=========================================");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§c==============§a BWKits v1.0 §c==============");
        getServer().getConsoleSender().sendMessage("§4The plugin has been successfully disabled!");
        getServer().getConsoleSender().sendMessage("§c=========================================");
    }


    public static Economy economy = null;
    private boolean setupEconomy(){
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    String pex_nope = this.getConfig().getString("pex_nope");
    String vipdon = this.getConfig().getString("vipdon");
    String vipplusdon = this.getConfig().getString("vipplusdon");
    String platinumdon = this.getConfig().getString("platinumdon");

    int warrior_cost = this.getConfig().getInt("warrior.cost");
    int archer_cost = this.getConfig().getInt("archer.cost");
    int miner_cost = this.getConfig().getInt("miner.cost");
    int builder_cost = this.getConfig().getInt("builder.cost");
    int tank_cost = this.getConfig().getInt("tank.cost");
    int defender_cost = this.getConfig().getInt("defender.cost");
    int alchemist_cost = this.getConfig().getInt("alchemist.cost");
    int teleporter_cost = this.getConfig().getInt("teleporter.cost");
    int resources_cost = this.getConfig().getInt("resources.cost");

    String warrior_name = this.getConfig().getString("warrior.name");
    String archer_name = this.getConfig().getString("archer.name");
    String miner_name = this.getConfig().getString("miner.name");
    String builder_name = this.getConfig().getString("builder.name");
    String tank_name = this.getConfig().getString("tank.name");
    String defender_name = this.getConfig().getString("defender.name");
    String alchemist_name = this.getConfig().getString("alchemist.name");
    String teleporter_name = this.getConfig().getString("teleporter.name");
    String resources_name = this.getConfig().getString("resources.name");

    String kit_already_selected_message = this.getConfig().getString("kit_already_selected_message");

    String kitselect_name = this.getConfig().getString("kitselect_name");
    String kitbuy_name = this.getConfig().getString("kitbuy_name");
    String kit_select_message = this.getConfig().getString("kit_select_message");
    String not_kit = this.getConfig().getString("not_kit");
    String cost = this.getConfig().getString("cost");
    String bwkit = this.getConfig().getString("bwkit");
    String command_console = this.getConfig().getString("command_console");
    String pex_reset = this.getConfig().getString("pex_reset");
    String bronze_name = this.getConfig().getString("bronze_name");
    String iron_name = this.getConfig().getString("iron_name");
    String teleporter_countdown1 = this.getConfig().getString("teleporter_countdown1");
    String teleporter_countdown2 = this.getConfig().getString("teleporter_countdown2");
    String teleporter_countdown3 = this.getConfig().getString("teleporter_countdown3");

    String bedwars = this.getConfig().getString("bedwars");
    String kit_give = this.getConfig().getString("kit_give");
    String not_enought_money = this.getConfig().getString("not_enought_money");
    String accept = this.getConfig().getString("accept");
    String already_buy = this.getConfig().getString("already_buy");
    String buy_now = this.getConfig().getString("buy_now");
    String accept_no = this.getConfig().getString("accept_no");
    String allow = this.getConfig().getString("allow");
    String buy = this.getConfig().getString("buy");
    String buy_allow = this.getConfig().getString("buy_allow");
    String pex_deny = this.getConfig().getString("pex_deny");
    String cfgreload = this.getConfig().getString("cfgreload");


    public void openGUI(Player player) { //Гуи выбора класса '/bwk'

        String warrior_name = this.getConfig().getString("warrior.name");
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
        Inventory inv = Bukkit.createInventory(null, 9, kitselect_name); //Создается инвентарь в 9 клеток (1 строка)

        List<String> lores = new ArrayList<String>(); //нужен для lore'а
// Воин
        //----- Набор куплен и доступен для выбора -----
        ItemStack warrior = new ItemStack(Material.IRON_SWORD); //Какой предмет будет отображаться
        ItemMeta warriorMeta = warrior.getItemMeta(); //хз зачем, но без него никак.

        warriorMeta.setDisplayName(warrior_name); //название айтема
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(accept);
        warriorMeta.setLore(lores); //lore применяется к предмету
        warrior.setItemMeta(warriorMeta); //Хз зачем, но нужно.

        //----- Набор не куплен и не доступен для выбора -----
        ItemStack warrior_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta warrior_noMeta = warrior_no.getItemMeta();

        lores = new ArrayList<String>();
        warrior_noMeta.setDisplayName(warrior_name);
        lores.add(accept_no);
        warrior_noMeta.setLore(lores);
        warrior_no.setItemMeta(warrior_noMeta);

        //----- Набор выбран -----
        ItemStack warrior_yes = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_yesMeta = warrior_yes.getItemMeta();

        lores = new ArrayList<String>();
        warrior_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        warrior_yesMeta.setDisplayName(warrior_name);
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(allow);
        warrior_yesMeta.setLore(lores);
        warrior_yes.setItemMeta(warrior_yesMeta);

// Воин

// Лучник
        ItemStack archer = new ItemStack(Material.BOW);
        ItemMeta archerMeta = archer.getItemMeta();


        archerMeta.setDisplayName(archer_name);
        lores = new ArrayList<String>();
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(accept);
        archerMeta.setLore(lores);
        archer.setItemMeta(archerMeta);

        ItemStack archer_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta archer_noMeta = archer_no.getItemMeta();

        lores = new ArrayList<String>();
        archer_noMeta.setDisplayName(archer_name);
        lores.add(pex_nope + vipdon);
        archer_noMeta.setLore(lores);
        archer_no.setItemMeta(archer_noMeta);

        ItemStack archer_yes = new ItemStack(Material.BOW); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta archer_yesMeta = archer_yes.getItemMeta();

        lores = new ArrayList<String>();
        archer_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        archer_yesMeta.setDisplayName(archer_name);
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(allow);
        archer_yesMeta.setLore(lores);
        archer_yes.setItemMeta(archer_yesMeta);

        //----- Нет прав -----
        ItemStack archer_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta archer_pexMeta = archer_pex.getItemMeta();

        lores = new ArrayList<String>();
        archer_pexMeta.setDisplayName(archer_name);
        lores.add(pex_nope + platinumdon);
        archer_pexMeta.setLore(lores);
        archer_pex.setItemMeta(archer_pexMeta);
// Лучник

// Шахтер

        ItemStack miner = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta minerMeta = miner.getItemMeta();

        minerMeta.setDisplayName(miner_name);
        lores = new ArrayList<String>();
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(accept);
        minerMeta.setLore(lores);
        miner.setItemMeta(minerMeta);

        ItemStack miner_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta miner_noMeta = miner_no.getItemMeta();

        lores = new ArrayList<String>();
        miner_noMeta.setDisplayName(miner_name);
        lores.add(accept_no);
        miner_noMeta.setLore(lores);
        miner_no.setItemMeta(miner_noMeta);

        ItemStack miner_yes = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_yesMeta = miner_yes.getItemMeta();

        lores = new ArrayList<String>();
        miner_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        miner_yesMeta.setDisplayName(miner_name);
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(allow);
        miner_yesMeta.setLore(lores);
        miner_yes.setItemMeta(miner_yesMeta);
// Шахтер

// Строитель
        ItemStack builder = new ItemStack(Material.SANDSTONE);
        ItemMeta builderMeta = builder.getItemMeta();

        builderMeta.setDisplayName(builder_name);
        lores = new ArrayList<String>();
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(accept);
        builderMeta.setLore(lores);
        builder.setItemMeta(builderMeta);

        ItemStack builder_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta builder_noMeta = builder_no.getItemMeta();

        lores = new ArrayList<String>();
        builder_noMeta.setDisplayName(builder_name);
        lores.add(accept_no);
        builder_noMeta.setLore(lores);
        builder_no.setItemMeta(builder_noMeta);

        ItemStack builder_yes = new ItemStack(Material.SANDSTONE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta builder_yesMeta = builder_yes.getItemMeta();

        lores = new ArrayList<String>();
        builder_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        builder_yesMeta.setDisplayName(builder_name);
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(allow);
        builder_yesMeta.setLore(lores);
        builder_yes.setItemMeta(builder_yesMeta);
// Строитель

// Танк
        ItemStack tank = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tankMeta = tank.getItemMeta();

        tankMeta.setDisplayName(tank_name);
        lores = new ArrayList<String>();
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(accept);
        tankMeta.setLore(lores);
        tank.setItemMeta(tankMeta);

        ItemStack tank_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta tank_noMeta = tank_no.getItemMeta();

        lores = new ArrayList<String>();
        tank_noMeta.setDisplayName(tank_name);
        lores.add(accept_no);
        tank_noMeta.setLore(lores);
        tank_no.setItemMeta(tank_noMeta);

        ItemStack tank_yes = new ItemStack(Material.CHAINMAIL_CHESTPLATE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta tank_yesMeta = tank_yes.getItemMeta();

        lores = new ArrayList<String>();
        tank_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        tank_yesMeta.setDisplayName(tank_name);
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(allow);
        tank_yesMeta.setLore(lores);
        tank_yes.setItemMeta(tank_yesMeta);
// Танк

// Защитник
        ItemStack defender = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defenderMeta = defender.getItemMeta();

        defenderMeta.setDisplayName(defender_name);
        lores = new ArrayList<String>();
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(accept);
        defenderMeta.setLore(lores);
        defender.setItemMeta(defenderMeta);

        ItemStack defender_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta defender_noMeta = defender_no.getItemMeta();

        lores = new ArrayList<String>();
        defender_noMeta.setDisplayName(defender_name);
        lores.add(accept_no);
        defender_noMeta.setLore(lores);
        defender_no.setItemMeta(defender_noMeta);

        ItemStack defender_yes = new ItemStack(Material.LEATHER_CHESTPLATE); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta defender_yesMeta = defender_yes.getItemMeta();

        lores = new ArrayList<String>();
        defender_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        defender_yesMeta.setDisplayName(defender_name);
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(allow);
        defender_yesMeta.setLore(lores);
        defender_yes.setItemMeta(defender_yesMeta);
// Защитник

// Алхимик
        ItemStack alchemist = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemistMeta = alchemist.getItemMeta();

        alchemistMeta.setDisplayName(alchemist_name);
        lores = new ArrayList<String>();
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(accept);
        alchemistMeta.setLore(lores);
        alchemist.setItemMeta(alchemistMeta);

        ItemStack alchemist_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta alchemist_noMeta = alchemist_no.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_noMeta.setDisplayName(alchemist_name);
        lores.add(accept_no);
        alchemist_noMeta.setLore(lores);
        alchemist_no.setItemMeta(alchemist_noMeta);

        ItemStack alchemist_yes = new ItemStack(Material.BREWING_STAND_ITEM); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta alchemist_yesMeta = alchemist_yes.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        alchemist_yesMeta.setDisplayName(alchemist_name);
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(allow);
        alchemist_yesMeta.setLore(lores);
        alchemist_yes.setItemMeta(alchemist_yesMeta);

        //----- Нет прав -----
        ItemStack alchemist_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta alchemist_pexMeta = alchemist_pex.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_pexMeta.setDisplayName(alchemist_name);
        lores.add(pex_nope + vipdon);
        alchemist_pexMeta.setLore(lores);
        alchemist_pex.setItemMeta(alchemist_pexMeta);
// Алхимик

// Телепортер
        ItemStack teleporter = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporterMeta = teleporter.getItemMeta();

        teleporterMeta.setDisplayName(teleporter_name);
        lores = new ArrayList<String>();
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(accept);
        teleporterMeta.setLore(lores);
        teleporter.setItemMeta(teleporterMeta);

        ItemStack teleporter_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta teleporter_noMeta = teleporter_no.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_noMeta.setDisplayName(teleporter_name);
        lores.add(accept_no);
        teleporter_noMeta.setLore(lores);
        teleporter_no.setItemMeta(teleporter_noMeta);

        ItemStack teleporter_yes = new ItemStack(Material.ENDER_PEARL); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta teleporter_yesMeta = teleporter_yes.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        teleporter_yesMeta.setDisplayName(teleporter_name);
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(allow);
        teleporter_yesMeta.setLore(lores);
        teleporter_yes.setItemMeta(teleporter_yesMeta);

        //----- Нет прав -----
        ItemStack teleporter_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta teleporter_pexMeta = teleporter_pex.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_pexMeta.setDisplayName(teleporter_name);
        lores.add(pex_nope + platinumdon);
        teleporter_pexMeta.setLore(lores);
        teleporter_pex.setItemMeta(teleporter_pexMeta);
// Телепортер

// Легкий старт
        ItemStack resources = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resourcesMeta = resources.getItemMeta();

        resourcesMeta.setDisplayName(resources_name);
        lores = new ArrayList<String>();
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(accept);
        resourcesMeta.setLore(lores);
        resources.setItemMeta(resourcesMeta);

        ItemStack resources_no = new ItemStack(Material.INK_SACK, 1, data);
        ItemMeta resources_noMeta = resources_no.getItemMeta();

        lores = new ArrayList<String>();
        resources_noMeta.setDisplayName(resources_name);
        lores.add(accept_no);
        resources_noMeta.setLore(lores);
        resources_no.setItemMeta(resources_noMeta);

        ItemStack resources_yes = new ItemStack(Material.DOUBLE_PLANT); //Можно добавить прочность, тобиш так: Material.BOW, 1, data
        ItemMeta resources_yesMeta = resources_yes.getItemMeta();

        lores = new ArrayList<String>();
        resources_yesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        resources_yesMeta.setDisplayName(resources_name);
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(allow);
        resources_yesMeta.setLore(lores);
        resources_yes.setItemMeta(resources_yesMeta);

        //----- Нет прав -----
        ItemStack resources_pex = new ItemStack(Material.INK_SACK, 1, data_pex);
        ItemMeta resources_pexMeta = resources_pex.getItemMeta();

        lores = new ArrayList<String>();
        resources_pexMeta.setDisplayName(resources_name);
        lores.add(pex_nope + platinumdon);
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
                    player.sendMessage(String.format(kit_already_selected_message, warrior_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + warrior_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, archer_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + archer_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, miner_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + miner_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, builder_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + builder_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, tank_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + tank_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, defender_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + defender_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, alchemist_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + alchemist_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, teleporter_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + teleporter_name);
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
                    player.sendMessage(String.format(kit_already_selected_message, resources_name));
                }
                else {
                    player.sendMessage(bedwars + kit_select_message + resources_name);
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
                player.sendMessage(bedwars + not_kit);
                break;
            default:
                player.closeInventory();
                break;
        }
    }

    public void openGUI_shop(Player player) {
        DyeColor color = DyeColor.GRAY;
        byte data = 8;

        String warrior_name = this.getConfig().getString("warrior.name");
        List<String> warrior_lore = this.getConfig().getStringList("warrior.lore");
        List<String> archer_lore = this.getConfig().getStringList("archer.lore");
        List<String> miner_lore = this.getConfig().getStringList("miner.lore");
        List<String> builder_lore = this.getConfig().getStringList("builder.lore");
        List<String> tank_lore = this.getConfig().getStringList("tank.lore");
        List<String> defender_lore = this.getConfig().getStringList("defender.lore");
        List<String> alchemist_lore = this.getConfig().getStringList("alchemist.lore");
        List<String> teleporter_lore = this.getConfig().getStringList("teleporter.lore");
        List<String> resources_lore = this.getConfig().getStringList("resources.lore");
        Inventory inv = Bukkit.createInventory(null, 9, kitbuy_name);

        List<String> lores = new ArrayList<String>();
// Воин
        ItemStack warrior_buy = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_buyMeta = warrior_buy.getItemMeta();

        warrior_buyMeta.setDisplayName(warrior_name);
        warrior_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        warrior_buyMeta.setLore(lores);
        warrior_buy.setItemMeta(warrior_buyMeta);

        ItemStack warrior_view = new ItemStack(Material.IRON_SWORD);
        ItemMeta warrior_viewMeta = warrior_view.getItemMeta();

        lores = new ArrayList<String>();
        warrior_viewMeta.setDisplayName(warrior_name);
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(cost  + warrior_cost);
        lores.add(buy);
        warrior_viewMeta.setLore(lores);
        warrior_view.setItemMeta(warrior_viewMeta);
// Воин

// Лучник
        ItemStack archer_buy = new ItemStack(Material.BOW);
        ItemMeta archer_buyMeta = archer_buy.getItemMeta();

        archer_buyMeta.setDisplayName(archer_name);
        archer_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        archer_buyMeta.setLore(lores);
        archer_buy.setItemMeta(archer_buyMeta);

        ItemStack archer_view = new ItemStack(Material.BOW);
        ItemMeta archer_viewMeta = archer_view.getItemMeta();

        lores = new ArrayList<String>();
        archer_viewMeta.setDisplayName(archer_name);
        for (String s : warrior_lore){
            lores.add(s);
        }
        lores.add(cost  + archer_cost);
        lores.add(buy);
        archer_viewMeta.setLore(lores);
        archer_view.setItemMeta(archer_viewMeta);

        ItemStack archer_pex = new ItemStack(Material.BOW);
        ItemMeta archer_pexMeta = archer_pex.getItemMeta();

        lores = new ArrayList<String>();
        archer_pexMeta.setDisplayName(archer_name);
        for (String s : archer_lore){
            lores.add(s);
        }
        lores.add(cost  + archer_cost);
        lores.add(pex_nope + platinumdon);
        archer_pexMeta.setLore(lores);
        archer_pex.setItemMeta(archer_pexMeta);
// Лучник

// Шахтер
        ItemStack miner_buy = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_buyMeta = miner_buy.getItemMeta();

        miner_buyMeta.setDisplayName(miner_name);
        miner_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        miner_buyMeta.setLore(lores);
        miner_buy.setItemMeta(miner_buyMeta);

        ItemStack miner_view = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_viewMeta = miner_view.getItemMeta();

        lores = new ArrayList<String>();
        miner_viewMeta.setDisplayName(miner_name);
        for (String s : miner_lore){
            lores.add(s);
        }
        lores.add(cost  + miner_cost);
        lores.add(buy);
        miner_viewMeta.setLore(lores);
        miner_view.setItemMeta(miner_viewMeta);
// Шахтер

// Строитель
        ItemStack builder_buy = new ItemStack(Material.SANDSTONE);
        ItemMeta builder_buyMeta = builder_buy.getItemMeta();

        builder_buyMeta.setDisplayName(builder_name);
        builder_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        builder_buyMeta.setLore(lores);
        builder_buy.setItemMeta(builder_buyMeta);

        ItemStack builder_view = new ItemStack(Material.SANDSTONE);
        ItemMeta builder_viewMeta = builder_view.getItemMeta();

        lores = new ArrayList<String>();
        builder_viewMeta.setDisplayName(builder_name);
        for (String s : builder_lore){
            lores.add(s);
        }
        lores.add(cost  + builder_cost);
        lores.add(buy);
        builder_viewMeta.setLore(lores);
        builder_view.setItemMeta(builder_viewMeta);
// Строитель

// Танк
        ItemStack tank_buy = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_buyMeta = tank_buy.getItemMeta();

        tank_buyMeta.setDisplayName(tank_name);
        tank_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        tank_buyMeta.setLore(lores);
        tank_buy.setItemMeta(tank_buyMeta);

        ItemStack tank_view = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_viewMeta = tank_view.getItemMeta();

        lores = new ArrayList<String>();
        tank_viewMeta.setDisplayName(tank_name);
        for (String s : tank_lore){
            lores.add(s);
        }
        lores.add(cost  + tank_cost);
        lores.add(buy);
        tank_viewMeta.setLore(lores);
        tank_view.setItemMeta(tank_viewMeta);
// Танк

// Защитник
        ItemStack defender_buy = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_buyMeta = defender_buy.getItemMeta();

        defender_buyMeta.setDisplayName(defender_name);
        defender_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        defender_buyMeta.setLore(lores);
        defender_buy.setItemMeta(defender_buyMeta);

        ItemStack defender_view = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_viewMeta = defender_view.getItemMeta();

        lores = new ArrayList<String>();
        defender_viewMeta.setDisplayName(defender_name);
        for (String s : defender_lore){
            lores.add(s);
        }
        lores.add(cost  + defender_cost);
        lores.add(buy);
        defender_viewMeta.setLore(lores);
        defender_view.setItemMeta(defender_viewMeta);
// Защитник

// Алхимик
        ItemStack alchemist_buy = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_buyMeta = alchemist_buy.getItemMeta();

        alchemist_buyMeta.setDisplayName(alchemist_name);
        alchemist_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        alchemist_buyMeta.setLore(lores);
        alchemist_buy.setItemMeta(alchemist_buyMeta);

        ItemStack alchemist_view = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_viewMeta = alchemist_view.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_viewMeta.setDisplayName(alchemist_name);
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(cost  + alchemist_cost);
        lores.add(buy);
        alchemist_viewMeta.setLore(lores);
        alchemist_view.setItemMeta(alchemist_viewMeta);

        ItemStack alchemist_pex = new ItemStack(Material.BREWING_STAND_ITEM);
        ItemMeta alchemist_pexMeta = alchemist_pex.getItemMeta();

        lores = new ArrayList<String>();
        alchemist_pexMeta.setDisplayName(alchemist_name);
        for (String s : alchemist_lore){
            lores.add(s);
        }
        lores.add(cost  + alchemist_cost);
        lores.add(pex_nope + vipdon);
        alchemist_pexMeta.setLore(lores);
        alchemist_pex.setItemMeta(alchemist_pexMeta);
// Алхимик

// Телепортер
        ItemStack teleporter_buy = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_buyMeta = teleporter_buy.getItemMeta();

        teleporter_buyMeta.setDisplayName(teleporter_name);
        teleporter_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        teleporter_buyMeta.setLore(lores);
        teleporter_buy.setItemMeta(teleporter_buyMeta);

        ItemStack teleporter_view = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_viewMeta = teleporter_view.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_viewMeta.setDisplayName(teleporter_name);
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(cost  + teleporter_cost);
        lores.add(buy);
        teleporter_viewMeta.setLore(lores);
        teleporter_view.setItemMeta(teleporter_viewMeta);

        ItemStack teleporter_pex = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_pexMeta = teleporter_pex.getItemMeta();

        lores = new ArrayList<String>();
        teleporter_pexMeta.setDisplayName(teleporter_name);
        for (String s : teleporter_lore){
            lores.add(s);
        }
        lores.add(cost  + teleporter_cost);
        lores.add(pex_nope + platinumdon);
        teleporter_pexMeta.setLore(lores);
        teleporter_pex.setItemMeta(teleporter_pexMeta);
// Телепортер

// Легкий старт
        ItemStack resources_buy = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_buyMeta = resources_buy.getItemMeta();

        resources_buyMeta.setDisplayName(resources_name);
        resources_buyMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        lores = new ArrayList<String>();
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(buy_allow);
        resources_buyMeta.setLore(lores);
        resources_buy.setItemMeta(resources_buyMeta);

        ItemStack resources_view = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_viewMeta = resources_view.getItemMeta();

        lores = new ArrayList<String>();
        resources_viewMeta.setDisplayName(resources_name);
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(cost  + resources_cost);
        lores.add(buy);
        resources_viewMeta.setLore(lores);
        resources_view.setItemMeta(resources_viewMeta);

        ItemStack resources_pex = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta resources_pexMeta = resources_pex.getItemMeta();

        lores = new ArrayList<String>();
        resources_pexMeta.setDisplayName(resources_name);
        for (String s : resources_lore){
            lores.add(s);
        }
        lores.add(cost  + resources_cost);
        lores.add(pex_nope + platinumdon);
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
                    if (balance >= warrior_cost) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, warrior_cost); //depositPlayer - выдать | withdrawPlayer - снять
                        player.sendMessage(String.format(buy_now, warrior_name));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.warrior");
                    } else {
                        player.closeInventory();
                        player.sendMessage(not_enought_money);
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, warrior_name));
                }
                break;
            case 1:
                if (!player.isPermissionSet("bw.buykit.archer")){
                    if (!player.isPermissionSet("bw.buykit.d_archer")) {
                        player.closeInventory();
                        player.sendMessage(bedwars + pex_nope + platinumdon);
                    }
                    else {
                        if (balance >= archer_cost) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, archer_cost);
                            player.sendMessage(String.format(buy_now, archer_name));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.archer");
                        } else {
                            player.closeInventory();
                            player.sendMessage(not_enought_money);
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, archer_name));
                }
                break;
            case 2:
                if (!player.isPermissionSet("bw.buykit.miner")){
                    if (balance >= miner_cost) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, miner_cost);
                        player.sendMessage(String.format(buy_now, miner_name));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.miner");
                    } else {
                        player.closeInventory();
                        player.sendMessage(not_enought_money);
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, miner_name));
                }
                break;
            case 3:
                if (!player.isPermissionSet("bw.buykit.builder")){
                    if (balance >= builder_cost) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, builder_cost);
                        player.sendMessage(String.format(buy_now, builder_name));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.builder");
                    } else {
                        player.closeInventory();
                        player.sendMessage(not_enought_money);
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, builder_name));
                }
                break;
            case 4:
                if (!player.isPermissionSet("bw.buykit.tank")){
                    if (balance >= tank_cost) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, tank_cost);
                        player.sendMessage(String.format(buy_now, tank_name));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.tank");
                    } else {
                        player.closeInventory();
                        player.sendMessage(not_enought_money);
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, tank_name));
                }
                break;
            case 5:
                if (!player.isPermissionSet("bw.buykit.defender")){
                    if (balance >= defender_cost) {
                        player.closeInventory();
                        economy.withdrawPlayer(player, defender_cost);
                        player.sendMessage(String.format(buy_now, defender_name));
                        PermissionsEx.getUser(player).addPermission("bw.buykit.defender");
                    } else {
                        player.closeInventory();
                        player.sendMessage(not_enought_money);
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, defender_name));
                }
                break;
            case 6:
                if (!player.isPermissionSet("bw.buykit.alchemist")){
                    if (!player.isPermissionSet("bw.buykit.d_alchemist")) {
                        player.closeInventory();
                        player.sendMessage(bedwars + pex_nope + vipdon);
                    }
                    else {
                        if (balance >= alchemist_cost) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, alchemist_cost);
                            player.sendMessage(String.format(buy_now, alchemist_name));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.alchemist");
                        } else {
                            player.closeInventory();
                            player.sendMessage(not_enought_money);
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, alchemist_name));
                }
                break;
            case 7:
                if (!player.isPermissionSet("bw.buykit.teleporter")){
                    if (!player.isPermissionSet("bw.buykit.d_teleporter")) {
                        player.closeInventory();
                        player.sendMessage(bedwars + pex_nope + platinumdon);
                    }
                    else {
                        if (balance >= teleporter_cost) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, teleporter_cost);
                            player.sendMessage(String.format(buy_now, teleporter_name));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.teleporter");
                        } else {
                            player.closeInventory();
                            player.sendMessage(not_enought_money);
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, teleporter_name));
                }
                break;
            case 8:
                if (!player.isPermissionSet("bw.buykit.resources")){
                    if (!player.isPermissionSet("bw.buykit.d_resources")) {
                        player.closeInventory();
                        player.sendMessage(bedwars + pex_nope + platinumdon);
                    }
                    else {
                        if (balance >= resources_cost) {
                            player.closeInventory();
                            economy.withdrawPlayer(player, resources_cost);
                            player.sendMessage(String.format(buy_now, resources_name));
                            PermissionsEx.getUser(player).addPermission("bw.buykit.resources");
                        } else {
                            player.closeInventory();
                            player.sendMessage(not_enought_money);
                        }
                    }
                } else {
                    player.closeInventory();
                    player.sendMessage(String.format(already_buy, resources_name));
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
                getServer().getConsoleSender().sendMessage(bwkit + cfgreload);
            }
            else
                getServer().getConsoleSender().sendMessage(command_console);
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
                    player.sendMessage(bwkit + pex_reset);
                } else
                    player.sendMessage(pex_deny);
            }
            if(cmd.getName().equalsIgnoreCase("bwshopgui") && args.length == 0)
                openGUI_shop(player);
            if(cmd.getName().equalsIgnoreCase("bwselectkit") && args.length == 0)
                openGUI(player);
            if(cmd.getName().equalsIgnoreCase("bwreload") && args.length == 0) {
                reloadConfig();
                player.sendMessage(bwkit + cfgreload);
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
        warrior_item1Meta.setDisplayName(warrior_name);
        warrior_item1Meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        warrior_item1.setItemMeta(warrior_item1Meta);
        // Воин

        // Лучник
        ItemStack archer_item1 = new ItemStack(Material.BOW);
        ItemMeta archer_item1Meta =  archer_item1.getItemMeta();
        ItemStack archer_item2 = new ItemStack(Material.ARROW, 32);
        ItemMeta archer_item2Meta =  archer_item2.getItemMeta();
        archer_item1Meta.setDisplayName(archer_name);
        archer_item1.setItemMeta(archer_item1Meta);
        archer_item2Meta.setDisplayName(archer_name);
        archer_item2.setItemMeta(archer_item2Meta);
        // Лучник

        // Шахтер
        ItemStack miner_item1 = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta miner_item1Meta =  miner_item1.getItemMeta();
        miner_item1Meta.setDisplayName(miner_name);
        miner_item1Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        miner_item1.setItemMeta(miner_item1Meta);
        // Шахтер

        // Строитель
        ItemStack builder_item1 = new ItemStack(Material.SANDSTONE, 64);
        ItemMeta builder_item1Meta =  builder_item1.getItemMeta();
        builder_item1Meta.setDisplayName(builder_name);
        builder_item1.setItemMeta(builder_item1Meta);
        // Строитель

        // Танк
        ItemStack tank_item1 = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta tank_item1Meta =  tank_item1.getItemMeta();
        tank_item1Meta.setDisplayName(tank_name);
        tank_item1.setItemMeta(tank_item1Meta);

        ItemStack tank_item2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta tank_item2Meta =  tank_item2.getItemMeta();
        tank_item2Meta.setDisplayName(tank_name);
        tank_item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        tank_item2.setItemMeta(tank_item2Meta);

        ItemStack tank_item3 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta tank_item3Meta =  tank_item3.getItemMeta();
        tank_item3Meta.setDisplayName(tank_name);
        tank_item3.setItemMeta(tank_item3Meta);

        ItemStack tank_item4 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta tank_item4Meta =  tank_item4.getItemMeta();
        tank_item4Meta.setDisplayName(tank_name);
        tank_item4.setItemMeta(tank_item4Meta);
        // Танк

        // Защитник
        ItemStack defender_item1 = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta defender_item1Meta =  defender_item1.getItemMeta();
        defender_item1Meta.setDisplayName(defender_name);
        defender_item1.setItemMeta(defender_item1Meta);

        ItemStack defender_item2 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta defender_item2Meta =  defender_item2.getItemMeta();
        defender_item2Meta.setDisplayName(defender_name);
        defender_item2.setItemMeta(defender_item2Meta);

        ItemStack defender_item3 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta defender_item3Meta =  defender_item3.getItemMeta();
        defender_item3Meta.setDisplayName(defender_name);
        defender_item3.setItemMeta(defender_item3Meta);

        ItemStack defender_item4 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta defender_item4Meta =  defender_item4.getItemMeta();
        defender_item4Meta.setDisplayName(defender_name);
        defender_item4.setItemMeta(defender_item4Meta);

        ItemStack defender_item5 = new ItemStack(Material.WOOD_SWORD);
        ItemMeta defender_item5Meta =  defender_item5.getItemMeta();
        defender_item5Meta.setDisplayName(defender_name);
        defender_item5.setItemMeta(defender_item4Meta);
        // Защитник

        // Алхимик
        Potion potion1 = new Potion(PotionType.SPEED, 1);
        potion1.setSplash(true);
        ItemStack potion = potion1.toItemStack(1);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(alchemist_name);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 0), true);
        potion.setItemMeta(meta);
        // Алхимик

        // Телепортер
        ItemStack teleporter_item1 = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleporter_item1Meta =  teleporter_item1.getItemMeta();
        teleporter_item1Meta.setDisplayName(teleporter_name);
        teleporter_item1.setItemMeta(teleporter_item1Meta);
        // Телепортер

        // Легкий старт
        ItemStack resources_item1 = new ItemStack(Material.BRICK, 64);
        ItemMeta resources_item1Meta =  resources_item1.getItemMeta();
        resources_item1Meta.setDisplayName(bronze_name);
        resources_item1.setItemMeta(resources_item1Meta);

        ItemStack resources_item2 = new ItemStack(Material.IRON_INGOT, 4);
        ItemMeta resources_item2Meta =  resources_item2.getItemMeta();
        resources_item2Meta.setDisplayName(iron_name);
        resources_item2.setItemMeta(resources_item2Meta);
        // Легкий старт

        /*
        List<String> lores = new ArrayList<String>();
        ItemStack wa = new ItemStack(Material.IRON_SWORD);
        ItemMeta poc1Meta = poc1.getItemMeta();

        poc1Meta.setDisplayName(warrior_name);
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
                        player.sendMessage(bedwars + kit_give + warrior_name);
                        inv.setItemInHand(warrior_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.archer")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + archer_name);
                        inv.setItem(0, archer_item1);
                        inv.setItem(1, archer_item2);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.miner")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + miner_name);
                        inv.setItemInHand(miner_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.builder")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + builder_name);
                        inv.setItemInHand(builder_item1);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.tank")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + tank_name);
                        inv.setItem(39, tank_item1);
                        inv.setItem(38, tank_item2);
                        inv.setItem(37, tank_item3);
                        inv.setItem(36, tank_item4);
                    }, 10L);
                }

                if (player.hasPermission("bw.kit.defender")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + defender_name);
                        inv.setItem(39, defender_item1);
                        inv.setItem(38, defender_item2);
                        inv.setItem(37, defender_item3);
                        inv.setItem(36, defender_item4);
                    }, 10L);
                } else if (player.hasPermission("bw.kit.alchemist")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + alchemist_name);
                        inv.setItem(0, potion);
                    }, 10L);
                } else if (player.hasPermission("bw.kit.teleporter")) {
                    player.sendMessage(bedwars + teleporter_countdown1);
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {

                        player.sendMessage(bedwars + teleporter_countdown2);
                        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {

                            player.sendMessage(bedwars + teleporter_countdown3);
                            getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                                player.sendMessage(bedwars + kit_give + teleporter_name);
                                inv.setItemInHand(teleporter_item1);
                            }, 300L);
                        }, 400L);
                    }, 600L);
                } else if (player.hasPermission("bw.kit.resources")) {
                    getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        player.sendMessage(bedwars + kit_give + resources_name);
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
