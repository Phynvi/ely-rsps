package server.model.players.skills;


import server.model.players.*;
import server.Config;
import server.util.Misc;
import server.Server;
import java.lang.Math;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.objects.Object;
import server.model.players.PlayerHandler;
import server.model.players.ActionHandler;


public class Woodcutting {
       
        Client c;
       
        private final int VALID_AXE[] = {1351,1349,1353,1361,1355,1357,1359,6739};
        private final int[] AXE_REQS = {1,1,6,6,21,31,41,61};
        private int logType;
        private int exp;
        private int levelReq;
        private int axeType;
        private int treeX;
        private int treeY;
        private int density;
        private int treeType;
        public int logsCutFromTree;
        public int tempTimer = 0;
       
        public static int TREE_TIMER = 2000;
       
        public Woodcutting(Client c) {
                this.c = c;
        }

        public void startWoodcutting(int logType, int levelReq, int exp, int treeX, int treeY, int density, int treeType) {
                if (goodAxe() > 0) {
                        c.turnPlayerTo(treeX, treeY);
                        if (c.playerLevel[c.playerWoodcutting] >= levelReq) {
                                this.logType = logType;
                                this.exp = exp;
                                this.levelReq = levelReq;
                                this.treeX = treeX;
                                this.treeY = treeY;
                                this.density = density;
                                this.treeType = treeType;
                                this.axeType = goodAxe();
                                c.wcTimer = getWcTimer(density);
                                c.startAnimation(getWcEmote());
                        } else {
                                c.getPA().resetVariables();
                                c.startAnimation(65535);
                                c.sendMessage("You need a woodcutting level of " + levelReq + " to cut this tree.");
                        }              
                } else {
                        c.startAnimation(65535);                               
                        c.sendMessage("You don't have an axe for which you have the level to use.");
                        c.getPA().resetVariables();
                }
        }

        public void resetWoodcut() {
                                this.logType = -1;
                                this.exp = -1;
                                this.levelReq = -1;
                                this.axeType = -1;

                this.density = -1;
                this.treeX = -1;
                this.treeY = -1;
                this.treeType = -1;
                c.wcTimer = -1;
                logsCutFromTree = 0;
        }
public void randomNestdrop() {
c.sendMessage("A nest has fallen out of the tree!");
Server.itemHandler.createGroundItem(c, 5073, c.getX(), c.getY(), 1, c.getId());
        }
       
        public int getWcEmote() {
                if (axeType == 1351) // bronze
                        return 879;
                if (axeType == 1349) // iron
                        return 877;
                if (axeType == 1353) // steel
                        return 875;
                if (axeType == 1355) // mith
                        return 871;
                if (axeType == 1357) // addy
                        return 869;
                if (axeType == 1359) // rune
                        return 867;
                if (axeType == 6739) // d axe
                        return 2846;
                if (axeType == 13661) // d axe
                        return 2846;
                if (axeType == 1361) // black
                        return 873;
                else
                        return 0;
        }
       
        public void cutWood() {
                int maxLogsPerTree = cutForChop(treeType);
                if (logsCutFromTree < maxLogsPerTree) {

                        if (c.getItems().addItem(logType,1)) {
                                c.startAnimation(getWcEmote());
                                logsCutFromTree += 1;
                                c.sendMessage("You get some logs.");
                                c.getPA().addSkillXP(exp * Config.WOODCUTTING_EXPERIENCE, c.playerWoodcutting);
                                c.getPA().refreshSkill(c.playerWoodcutting);
                                c.wcTimer = getWcTimer(density);
                        } else {
                                c.getPA().resetVariables();
                                 c.frame1();                   
                                return;
                }
                               
                        }else if(c.getItems().freeSlots() >= 1){
                                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                                if (Server.playerHandler.players[j] != null) {
                                c.getItems().addItem(logType,1);
                                c.sendMessage("You get some logs.");
                                c.getPA().addSkillXP(exp * Config.WOODCUTTING_EXPERIENCE, c.playerWoodcutting);

                                        Client c2 = (Client)Server.playerHandler.players[j];
                                        new Object(1343, treeX, treeY, 0, 1, 10, treeType, getTicksForTree());
                                        c.getPA().resetVariables();
                                         c.frame1();
                               
                                        resetWoodcut();
                                        if (Misc.random(25) == 1)
                                        randomNestdrop();
                                        return;
                                }
                        }
                        return;
                }
}
        public int getTicksForTree() {
                return (2000-PlayerHandler.getPlayerCount())/100 + additionalTicksPerTree();
        }
       
        public int additionalTicksPerTree() {
                if (treeType == 1276) // reg
                        return 1;
                if (treeType == 1278) // reg
                        return 1;
                if (treeType == 1281) // oak
                        return 1;
                if (treeType == 1308) // willow
                        return 3;
                if (treeType == 1307) // maple
                        return 4;
                if (treeType == 1309) // yew
                        return 6;
                if (treeType == 1306) // magic
                        return 7;
                else
                        return 0;
        }
       
        public int cutForChop(int tree) {
                if (tree == 1276 || tree == 1278)
                        return 0;
                if (tree == 1281)
                        return 0 + Misc.random(5);
                if (tree == 1308)
                        return 0 + Misc.random(6);
                if (tree == 1307)
                        return 0 + Misc.random(8);
                if (tree == 1309)
                        return 0 + Misc.random(7);
                if (tree == 1306)
                        return 0 + Misc.random(10);
                else
                        return 0;
        }
       
        public int axePower(int axe) {
                if (axe == 1351) // bronze
                        return 1;
                if (axe == 1349) // iron
                        return 1;
                if (axe == 1353) // steel
                        return 2;
                if (axe == 1361) // black
                        return 2;
                if (axe == 1355) // mith
                        return 3;
                if (axe == 1357) // addy
                        return 4;
                if (axe == 1359) // rune
                        return 5;
                if (axe == 6739) //daxe
                        return 7;
                if (axe == 13661) //daxe
                        return 8;
                else
                        return 0;
        }
       
        public int goodAxe() {
                for (int j = VALID_AXE.length - 1; j >= 0; j--) {
                        if (c.playerEquipment[c.playerWeapon] == VALID_AXE[j]) {
                                if (c.playerLevel[c.playerWoodcutting] >= AXE_REQS[j])
                                        return VALID_AXE[j];
                        }              
                }
                for (int i = 0; i < c.playerItems.length; i++) {
                        for (int j = VALID_AXE.length - 1; j >= 0; j--) {
                                if (c.playerItems[i] == VALID_AXE[j] + 1) {
                                        if (c.playerLevel[c.playerWoodcutting] >= AXE_REQS[j])
                                                return VALID_AXE[j];
                                }
                        }              
                }
                return - 1;
        }
       
        public int getWcTimer(int density) {
                int time = Misc.random(2);
                return density + time - axePower(goodAxe());
        }

}