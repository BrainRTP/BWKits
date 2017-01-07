package ru;
//Fucking test!!
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

    private static String kitselect_name;
    private static String kitbuy_name;
    private static String kit_select_message;
    private static String not_kit;
    private static String cost;
    private static String bwkit;
    private static String command_console;
    private static String pex_reset;
    private static String bronze_name;
    private static String iron_name;
    private static String teleporter_countdown1;
    private static String teleporter_countdown2;
    private static String teleporter_countdown3;

    private static String bedwars;
    private static String kit_give;
    private static String not_enought_money;
    private static String accept;
    private static String already_buy;
    private static String buy_now;
    private static String accept_no;
    private static String allow;
    private static String buy;
    private static String buy_allow;
    private static String pex_deny;
    private static String cfgreload;

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

        kitselect_name = cfile.getString("kitselect_name");
        kitbuy_name = cfile.getString("kitbuy_name");
        kit_select_message = cfile.getString("kit_select_message");
        not_kit = cfile.getString("not_kit");
        cost = cfile.getString("cost");
        bwkit = cfile.getString("bwkit");
        command_console = cfile.getString("command_console");
        pex_reset = cfile.getString("pex_reset");
        bronze_name = cfile.getString("bronze_name");
        iron_name = cfile.getString("iron_name");
        teleporter_countdown1 = cfile.getString("teleporter_countdown1");
        teleporter_countdown2 = cfile.getString("teleporter_countdown2");
        teleporter_countdown3 = cfile.getString("teleporter_countdown3");

        bedwars = cfile.getString("bedwars");
        kit_give = cfile.getString("kit_give");
        not_enought_money = cfile.getString("not_enought_money");
        accept = cfile.getString("accept");
        already_buy = cfile.getString("already_buy");
        buy_now = cfile.getString("buy_now");
        accept_no = cfile.getString("accept_no");
        allow = cfile.getString("allow");
        buy = cfile.getString("buy");
        buy_allow = cfile.getString("buy_allow");
        pex_deny = cfile.getString("pex_deny");
        cfgreload = cfile.getString("cfgreload");
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

    public static String getKitselect_name() { return kitselect_name; }
    public static String getKitbuy_name() { return kitbuy_name; }
    public static String getKit_select_message() { return kit_select_message; }
    public static String getNot_kit() { return not_kit; }
    public static String getCost() { return cost; }
    public static String getBwkit() { return bwkit; }
    public static String getCommand_console() { return command_console; }
    public static String getPex_reset() { return pex_reset; }
    public static String getBronze_name() { return bronze_name; }
    public static String getIron_name() { return iron_name; }
    public static String getTeleporter_countdown1() { return teleporter_countdown1; }
    public static String getTeleporter_countdown2() { return teleporter_countdown2; }
    public static String getTeleporter_countdown3() { return teleporter_countdown3; }
    public static String getKit_already_selected_message() { return kit_already_selected_message; }

    public static String getBedwars() { return bedwars; }
    public static String getKit_give() { return kit_give; }
    public static String getNot_enought_money() { return not_enought_money; }
    public static String getAccept() { return accept; }
    public static String getAlready_buy() { return already_buy; }
    public static String getBuy_now() { return buy_now; }
    public static String getAccept_no() { return accept_no; }
    public static String getAllow() { return allow; }
    public static String getBuy() { return buy; }
    public static String getBuy_allow() { return buy_allow; }
    public static String getPex_deny() { return pex_deny; }
    public static String getCfgreload() { return cfgreload; }

}
