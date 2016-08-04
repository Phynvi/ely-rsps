package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.world.ClanChatHandler;
import server.util.Misc;
import server.Server;

/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {
	//@Override
int[] lowChance = {1077, 1125, 1165, 1195,
		1297, 1367, 853, 7390,
		7392, 7394, 7396, 7386, 
		7388, 1099, 1135, 1065,
		851};	
int[] medChance = {1073, 1123, 1161, 1199,
		1301, 1371, 857, 2577,
		2579, 2487, 2493, 2499, 
		2631, 855};	
int[] highChance = {1079, 1093, 1113, 1127,
		1147, 1163, 1185, 1201,
		1275, 1303, 1319, 1333,
		1359, 1373, 2491, 2497,
		2503, 861, 859, 2581,
		2651, 10330, 10332, 10334, 10336, 10338, 10340, 10342, 10344, 10346, 10348, 10350, 10352,1079, 1093, 1113, 1127,
		1147, 1163, 1185, 1201};
int[] bestChance = {1079, 1093, 1113, 1127,
		1147, 1163, 1185, 1201,1079, 1093, 1113, 1127,
		1147, 1163, 1185, 1201,10330, 10332, 10334, 10336, 10338, 10340, 10342, 10344, 10346, 10348, 10350, 10352, 11858,2581,2577,11278,15126,6585,3481,3483,3486,3488};
public int lowChance() {
			return lowChance[(int) (Math.random() * lowChance.length)];
	}
	public int medChance() {
			return medChance[(int) (Math.random() * medChance.length)];
	}
	public int highChance() {
			return highChance[(int) (Math.random() * highChance.length)];
	}
public int bestChance() {
			return bestChance[(int) (Math.random() * bestChance.length)];
	}
	public void processPacket(Client c, int packetType, int packetSize) {

		int Diceamount1 = Misc.random(6);
int Diceamount2 = Misc.random(100);
int Diceamount3 = Misc.random(10);
int DiceamountB1 = Misc.random(6);
int DiceamountB2 = Misc.random(6);
int DiceamountB3 = Misc.random(6);
int DiceamountB4 = Misc.random(6);
int DiceamountB = (DiceamountB1) + (DiceamountB2) + (DiceamountB3) + (DiceamountB4);
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
		}
                if(itemId == 8007) {
                   c.getItems().deleteItem(8007,c.getItems().getItemSlot(8007),1);
                   c.getPA().teleTabTeleport(3213, 3423, 0, "teleTab");
                }
                if(itemId == 8008) {
                   c.getItems().deleteItem(8008,c.getItems().getItemSlot(8008),1);
                   c.getPA().teleTabTeleport(3224, 3218, 0, "teleTab");
                }
              if(itemId == 8009) {
                   c.getItems().deleteItem(8009,c.getItems().getItemSlot(8009),1);
                   c.getPA().teleTabTeleport(2965, 3383, 0, "teleTab");
                }
            if(itemId == 8010) {
                   c.getItems().deleteItem(8010,c.getItems().getItemSlot(8010),1);
                   c.getPA().teleTabTeleport(2757, 3477, 0, "teleTab");
                }
          if(itemId == 8011) {
                   c.getItems().deleteItem(8011,c.getItems().getItemSlot(8011),1);
                   c.getPA().teleTabTeleport(2653, 3283, 0, "teleTab");
                }
          if(itemId == 8012) {
                   c.getItems().deleteItem(8012,c.getItems().getItemSlot(8012),1);
                   c.getPA().teleTabTeleport(2546, 3112, 0, "teleTab");
                }

          if(itemId == 405) {
				c.getItems().addItem(lowChance(),Misc.random(1));
c.getItems().deleteItem(405, c.getItems().getItemSlot(405), 1);  
				}
  if(itemId == 6828) {
				c.getItems().addItem(c.SlayMiniLow(),1);
c.getItems().deleteItem(6828, c.getItems().getItemSlot(6828), 1);  
				}
  if(itemId == 6829) {
				c.getItems().addItem(c.SlayMiniMed(),1);
c.getItems().deleteItem(6829, c.getItems().getItemSlot(6829), 1);  
				}
  if(itemId == 6831) {
				c.getItems().addItem(c.SlayMiniHigh(),1);
c.getItems().deleteItem(6831, c.getItems().getItemSlot(6831), 1);  
				}
				
          if(itemId == 19040) {
				c.getItems().addItem(medChance(),Misc.random(1));
c.getItems().deleteItem(19040, c.getItems().getItemSlot(19040), 1);
			}
				
          if(itemId == 7956) {
c.getItems().deleteItem(7956, c.getItems().getItemSlot(7956), 1);
				c.getItems().addItem(highChance(),Misc.random(1));
			}
          if(itemId == 19039) {
c.getItems().deleteItem(19039, c.getItems().getItemSlot(19039), 1);
				c.getItems().addItem(bestChance(),Misc.random(1));
			}
/*Dragon Token*/
if(itemId == 7478) {
c.getItems().deleteItem(7478, 1);
c.getItems().addItem(995, 1000000);
c.sendMessage("You use the DragonToken and recieve <col=1532693>1 Million</col>coins.");
}
          if(itemId == 8013) {
                   c.getItems().deleteItem(8013,c.getItems().getItemSlot(8013),1);
                   c.getPA().teleTabTeleport(3086, 3499, 0, "teleTab");
                }
if (itemId == 5073) {
c.getItems().addItem(5075, 1);
                        c.getItems().handleNests (itemId);
}
if (itemId == 600) {
c.getPA().showInterface(25900);
}
		if(itemId == 15084) {
c.getDH().sendDialogues(94, 4289);
}
	if (itemId == 15262){
			c.getItems().deleteItem(15262,1);
			c.getItems().addItem(18016,100000);
			c.sendMessage("You open the pack and receive 100,000 spirit shards.");
			}
	if (itemId == 15098 && System.currentTimeMillis() - c.diceDelay >= 5000) { //Dice 100
c.startAnimation(11900);
c.gfx0(2075);
c.sendMessage("You rolled a "+ Diceamount2 +" on the percentile dice.");
c.diceDelay = System.currentTimeMillis();
}
if (itemId == 15086 && System.currentTimeMillis() - c.diceDelay >= 5000) { //Dice 6
c.startAnimation(11900);
c.gfx0(2075);
c.sendMessage("You have rolled a "+ Diceamount1 +" on a six-sided die.");
c.diceDelay = System.currentTimeMillis();
}
if (itemId == 15088 && System.currentTimeMillis() - c.diceDelay >= 5000) { //Dice blackjack
c.startAnimation(11900);
c.gfx0(2075);
c.sendMessage("You have rolled a "+ DiceamountB +" on two six-sided dice.");
c.diceDelay = System.currentTimeMillis();
}
if (itemId == 15092 && System.currentTimeMillis() - c.diceDelay >= 5000) { //Dice 10
c.startAnimation(11900);
c.gfx0(2075);
c.sendMessage("You have rolled a "+ Diceamount3 +" on a ten-sided die.");
c.diceDelay = System.currentTimeMillis();
}
	if (itemId == 4251) {
c.getPA().startTeleport(2911, 4576, 0, "modern");
c.getItems().deleteItem(4251, 1);
c.sendMessage("You teleport to a mysterious shrine.");
c.sendMessage("Touch the shrine once you are fully geared.");
}
		if(itemId == 4447) {
						for (int i = 0; i < 5; i++) {
					c.playerLevel[0] = 99;
					c.playerLevel[1] = 99;
					c.playerLevel[2] = 99;
					c.playerLevel[3] = 99;
					c.playerLevel[4] = 99;
					c.playerLevel[6] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.playerXP[6] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);
					c.getPA().refreshSkill(6);	
					c.getItems().deleteItem(4447, 1);
					c.logout();
				}
				c.getPA().requestUpdates();
			}
			
		if(itemId == 6796) {
			c.playerLevel[0] = 99;
			c.playerLevel[2] = 99;
			c.playerLevel[3] = 99;
			c.playerLevel[4] = 99;
			c.playerLevel[6] = 99;
			c.playerXP[0] = c.getPA().getXPForLevel(100);
			c.playerXP[2] = c.getPA().getXPForLevel(100);
			c.playerXP[3] = c.getPA().getXPForLevel(100);
			c.playerXP[4] = c.getPA().getXPForLevel(100);
			c.playerXP[6] = c.getPA().getXPForLevel(100);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			c.getItems().deleteItem(6796, 1);
			c.logout();
			}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 2528) {
		c.getItems().deleteItem(2528,1);
		c.getPA().showInterface(2808);
		}
		//Begin artifacts by Hirukos
		if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		//End of artifacts By Hirukos

		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
	if (c.getHerblore().checkGrimy(itemId, 0))
    c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			}
		}
	}

}
