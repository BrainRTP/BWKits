package ru;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public class Msg {
    private static String pex_nope;
    private static String vipdon;
    private static String vipplusdon;
    private static String platinumdon;


    private static String warrior_name;
    private static String archer_name;
    private static String miner_name;
    private static String builder_name;
    private static String tank_name;
    private static String defender_name;
    private static String alchemist_name;
    private static String teleporter_name;
    private static String resources_name;

    private static int warrior_cost;
    private static int archer_cost;
    private static int miner_cost;
    private static int builder_cost;
    private static int tank_cost;
    private static int defender_cost;
    private static int alchemist_cost;
    private static int teleporter_cost;
    private static int resources_cost;

    private static String kit_already_selected_message;

    private static Logger log = BWKits.getPlugin().getLogger();

    public static void load() {
        BWKits.getPlugin().saveDefaultConfig();
        log.info("Загрузка конфига...");
        FileConfiguration cfile = BWKits.getPlugin().getConfig();
        warrior_name = cfile.getString("warrior.name");

        pex_nope = cfile.getString("pex_nope");
        vipdon = cfile.getString("vipdon");
        vipplusdon = cfile.getString("vipplusdon");
        platinumdon = cfile.getString("platinumdon");

        warrior_cost = cfile.getInt("warrior.cost");
        archer_cost = cfile.getInt("archer.cost");
        miner_cost = cfile.getInt("miner.cost");
        builder_cost = cfile.getInt("builder.cost");
        tank_cost = cfile.getInt("tank.cost");
        defender_cost = cfile.getInt("defender.cost");
        alchemist_cost = cfile.getInt("alchemist.cost");
        teleporter_cost = cfile.getInt("teleporter.cost");
        resources_cost = cfile.getInt("resources.cost");

        warrior_name = cfile.getString("warrior.name");
        archer_name = cfile.getString("archer.name");
        miner_name = cfile.getString("miner.name");
        builder_name = cfile.getString("builder.name");
        tank_name = cfile.getString("tank.name");
        defender_name = cfile.getString("defender.name");
        alchemist_name = cfile.getString("alchemist.name");
        teleporter_name = cfile.getString("teleporter.name");
        resources_name = cfile.getString("resources.name");

        kit_already_selected_message = cfile.getString("kit_already_selected_message");

        String kitselect_name = cfile.getString("kitselect_name");
        String kitbuy_name = cfile.getString("kitbuy_name");
        String kit_select_message = cfile.getString("kit_select_message");
        String not_kit = cfile.getString("not_kit");
        String cost = cfile.getString("cost");
        String bwkit = cfile.getString("bwkit");
        String command_console = cfile.getString("command_console");
        String pex_reset = cfile.getString("pex_reset");
        String bronze_name = cfile.getString("bronze_name");
        String iron_name = cfile.getString("iron_name");
        String teleporter_countdown1 = cfile.getString("teleporter_countdown1");
        String teleporter_countdown2 = cfile.getString("teleporter_countdown2");
        String teleporter_countdown3 = cfile.getString("teleporter_countdown3");

        String bedwars = cfile.getString("bedwars");
        String kit_give = cfile.getString("kit_give");
        String not_enought_money = cfile.getString("not_enought_money");
        String accept = cfile.getString("accept");
        String already_buy = cfile.getString("already_buy");
        String buy_now = cfile.getString("buy_now");
        String accept_no = cfile.getString("accept_no");
        String allow = cfile.getString("allow");
        String buy = cfile.getString("buy");
        String buy_allow = cfile.getString("buy_allow");
        String pex_deny = cfile.getString("pex_deny");
        String cfgreload = cfile.getString("cfgreload");
        }
    public static String getPex_nope() { return pex_nope; }
    public static String getVipdon() { return vipdon; }
    public static String getVipplusdon() { return vipplusdon; }
    public static String getPlatinumdon() { return platinumdon; }


    public static String getWarriorName() { return warrior_name; }
    public static String getArcherName() { return archer_name; }
    public static String getMinerName() { return miner_name; }
    public static String getBuilderName() { return builder_name; }
    public static String getTankName() { return tank_name; }
    public static String getDefenderName() { return defender_name; }
    public static String getAlchemistName() { return alchemist_name; }
    public static String getTeleporterName() { return teleporter_name; }
    public static String getResourcesName() { return resources_name; }

    public static int getWarriorCost() { return warrior_cost; }
    public static int getArcherCost() { return archer_cost; }
    public static int getMinerCost() { return miner_cost; }
    public static int getBuilderCost() { return builder_cost; }
    public static int getTankCost() { return tank_cost; }
    public static int getDefenderCost() { return defender_cost; }
    public static int getAlchemistCost() { return alchemist_cost; }
    public static int getTeleporterCost() { return teleporter_cost; }
    public static int getResourcesCost() { return resources_cost; }

    public static String getKit_already_selected_message() { return kit_already_selected_message; }
}
