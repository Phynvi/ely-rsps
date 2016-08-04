package server.model.players.skills;

import server.Config;
import server.util.Misc;
import server.model.players.Client;
import server.model.players.Player;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
/**
 * Thieving.java
 *
 * @author Ace
 *
 **/
 
public class Thieving {
	
	private Client c;
		
	public Thieving(Client c) {
		this.c = c;
	}
	
	public void stealFromNPC(int id) {
		if (System.currentTimeMillis() - c.lastThieve < 2000)
			return;
		for (int j = 0; j < npcThieving.length; j++) {
			if (npcThieving[j][0] == id) {
				if (c.playerLevel[Player.playerThieving] >= npcThieving[j][1]) {
					if (Misc.random(c.playerLevel[Player.playerThieving] + 2 - npcThieving[j][1]) != 1) {
						c.getPA().addSkillXP(npcThieving[j][2] * Config.THIEVING_EXPERIENCE, Player.playerThieving);
						c.getItems().addItem(995, npcThieving[j][3]);
						c.startAnimation(881);
						c.lastThieve = System.currentTimeMillis();
						c.sendMessage("You thieve some money...");
						break;
					} else {
						c.setHitDiff(npcThieving[j][4]);
						c.setHitUpdateRequired(true);
						//c.playerLevel[c.playerHitpoints] -= npcThieving.getDamage();
						c.lastThieve = System.currentTimeMillis() + 2000;
						c.sendMessage("You fail to thieve the NPC, but take no damage.");
						c.sendMessage("Justin has saved you from being hurt.");
						break;
					}
				} else {
					c.sendMessage("You need a thieving level of " + npcThieving[j][1] + " to thieve from this NPC.");
				}
			}		
		}
	}
	public void stealFromStall(int id, int xp, int level, final int i, final int x, final int y) {
		if (System.currentTimeMillis() - c.lastThieve < 2500)
			return;
		if (c.playerLevel[c.playerThieving] >= level) {
			if (c.getItems().addItem(id,1)) {
				c.startAnimation(832);
				c.getPA().addSkillXP(xp * Config.THIEVING_EXPERIENCE, c.playerThieving);
				c.lastThieve = System.currentTimeMillis();
				c.sendMessage("You steal a " + server.model.items.Item.getItemName(id) + ".");
				c.getPA().checkObjectSpawn(5430 + Misc.random(1), x, y, 0, 10);
				EventManager.getSingleton().addEvent(new Event() {
					public void execute(EventContainer p) {
						c.getPA().checkObjectSpawn(i, x, y, 0, 10);
						p.stop();
					}
				}, 3500);
			}
		} else {
			c.sendMessage("You must have a thieving level of " + level + " to thieve from this stall.");
		}
	}
	//npc, level, exp, coin amount
	public int[][] npcThieving = {{1,1,8,200,1},{18,25,26,500,1},{9,40,47,1000,2},{26,55,85,1500,3},{20,70,152,2000,4},{21,80,273,3000,5}};

}