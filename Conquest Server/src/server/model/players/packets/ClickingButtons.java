package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.npcs.*;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.Client;
import server.model.players.packets.Commands;
import server.model.players.SkillMenu;
import server.model.players.PacketType;
import server.model.players.SkillGuides;
import server.util.Misc;
import java.util.Locale;
import server.event.EventContainer;
import server.event.Event;
import server.event.EventManager;
import server.model.objects.Doors;
import server.model.objects.DoubleDoors;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
	c.sendMessage("<col=red>Actionbutton: " + actionButtonId + " | Teleaction: " +c.teleAction+ " | Fight mode: " + c.fightMode +  " | Dialogue action: " + c.dialogueAction);
		for (int i = 0; i < c.qCAB.length; i++) {
		if (actionButtonId == c.qCAB[i][0] ){
				for (int j = 0; j < c.qCS.length; j++) {
				if ( j == i ) {
				c.forcedText = c.qC+ "My " +c.qCS[j]+ " Level is " +c.getLevelForXP(c.playerXP[c.qCAB[i][1]])+ ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				}
				}
		}
	}
		switch (actionButtonId){
			//crafting + fletching interface:
			case 89223: //Deposit Inventory
				if(c.getItems().freeSlots() == 28) {
c.sendMessage("You have no items in your backpack.");
} else {
for(int i = 0; i <= 27; i++) {
c.getItems().bankItem(c.playerItems[i] , i, c.getItems().itemAmount(c.playerItems[i]));
}
}
break;
			case 70146:
			if (c.playerLevel[24] > 98) {
				c.getItems().addItem(18509, 1);
			} else {
				c.sendMessage("You must be 99 Dungeoneering to Recieve This.");
			}
			break;
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
			
			
			case 66122:
switch(c.npcType) {
case 6807:
case 6874:
case 6868:
case 6795:
case 6816:
case 6873:

c.sendMessage("You are now storing items inside your npc");
	c.Summoning().store();
}
			break;
			case 66127:
			if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.storing = false;
c.sendMessage("Your Spawned NPC has been put away.");
} else {
c.sendMessage("You do not have a npc currently spawned");
}
break;
case 101055:
c.getPA().closeAllWindows();
c.sendMessage("Awe, that's too bad. Have fun with your Conquest-317 adventure!");
break;
case 101051:
c.getItems().deleteItem(600, 1);
c.isDonator = 1;
c.playerRights = 4;
c.getPA().closeAllWindows();
c.logout();
c.sendMessage("Congratulations, you are now an Conquest-317 Donator!");
break;
case 113237://#### => actionbutton that opens the interface.
		c.getPA().sendFrame126("TorvaScape - Item's Kept on Death", 17103);
		c.StartBestItemScan(c);
		c.EquipStatus = 0;
		for (int k = 0; k < 4; k++)
			c.getPA().sendFrame34a(10494, -1, k, 1);
		for (int k = 0; k < 39; k++)
			c.getPA().sendFrame34a(10600, -1, k, 1);
		if(c.WillKeepItem1 > 0)
			c.getPA().sendFrame34a(10494, c.WillKeepItem1, 0, c.WillKeepAmt1);
		if(c.WillKeepItem2 > 0)
			c.getPA().sendFrame34a(10494, c.WillKeepItem2, 1, c.WillKeepAmt2);
		if(c.WillKeepItem3 > 0)
			c.getPA().sendFrame34a(10494, c.WillKeepItem3, 2, c.WillKeepAmt3);
		if(c.WillKeepItem4 > 0 && c.prayerActive[10])
			c.getPA().sendFrame34a(10494, c.WillKeepItem4, 3, 1);
		for(int ITEM = 0; ITEM < 28; ITEM++){
			if(c.playerItems[ITEM]-1 > 0 && !(c.playerItems[ITEM]-1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot)
 			&& !(c.playerItems[ITEM]-1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot)
 			&& !(c.playerItems[ITEM]-1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot)
 			&& !(c.playerItems[ITEM]-1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot)){
				c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]);
				c.EquipStatus += 1;
			} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt1){
				c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt1);
				c.EquipStatus += 1;
			} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt2){
				c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt2);
				c.EquipStatus += 1;
			} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt3){
				c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt3);
				c.EquipStatus += 1;
			} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot) && c.playerItemsN[ITEM] > 1){
				c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-1);
				c.EquipStatus += 1;
			}
			}
			for(int EQUIP = 0; EQUIP < 14; EQUIP++){
				if(c.playerEquipment[EQUIP] > 0 && !(c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP+28 == c.WillKeepItem1Slot)
					&& !(c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP+28 == c.WillKeepItem2Slot)
					&& !(c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP+28 == c.WillKeepItem3Slot)
					&& !(c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP+28 == c.WillKeepItem4Slot)){
						c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]);
						c.EquipStatus += 1;
					} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP+28 == c.WillKeepItem1Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt1 > 0){
						c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt1);
						c.EquipStatus += 1;
					} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP+28 == c.WillKeepItem2Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt2 > 0){
						c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt2);
						c.EquipStatus += 1;
					} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP+28 == c.WillKeepItem3Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt3 > 0){
						c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt3);
						c.EquipStatus += 1;
					} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP+28 == c.WillKeepItem4Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-1 > 0){
						c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-1);
						c.EquipStatus += 1;
				}
			}
	          	c.ResetKeepItems();
			c.getPA().showInterface(17100);
			break;
/*SkillGuides Start*/
case 121135:// Attack button
			case 34142:
				SkillGuides.atkInterface(c);
				break;
			case 121140:// str button
			case 34119:
				SkillGuides.strInterface(c);
				break;
			case 121145: //Defence
			case 34120:
				SkillGuides.defInterface(c);
				break;
			case 121150:
			case 34133: //Range
				SkillGuides.rangeInterface(c);
				break;
			case 121180:
			case 34123: //Hitpoints
				//SkillGuides.hpInterface(c);
				break;
			case 121155:
			case 34139: //Prayer 
				SkillGuides.prayInterface(c);
				break;
			case 121160:
			case 34136: //Magic

				SkillGuides.mageInterface(c);
				break;
			case 121165:
			case 34155: //Runecrafting
				SkillGuides.rcInterface(c);
				break;
			case 121185:
			case 34158: //Agility
				SkillGuides.agilityInterface(c);
				break;
			case 121190:
			case 34161: //Herblore
				SkillGuides.herbloreInterface(c);
				break;
			case 121195:
			case 59199: //Theiving
				SkillGuides.thievingInterface(c);
				break;	
			case 121200:
			case 59202: //craft
				SkillGuides.craftingInterface(c);
				break;
			case 121205:
			case 33222: //Fletching
				SkillGuides.fletchingInterface(c);
				break;	
			case 121210: 
			case 59205: //Slayer
				SkillGuides.slayerInterface(c);
				break;
			case 121225:
			case 33208: //Mining
				SkillGuides.miningInterface(c);
				break;
			case 121230:
			case 33211: //Smithing
				SkillGuides.smithingInterface(c);
				break;
			case 121235:
			case 33214: //Fishing
				SkillGuides.fishingInterface(c);
				break;
			case 121240:
			case 33217: //Cooking
				SkillGuides.cookingInterface(c);
				break;
			case 121245:
			case 33220: //Firemaking
				SkillGuides.firemakingInterface(c);
				break;
			case 121250:
			case 33223: //Woodcutting
				SkillGuides.woodcuttingInterface(c);
				break;
			case 121255:
			case 54104: //Farming
				SkillGuides.farmingInterface(c);
				break;
/*SkillGuides End*/
	//CONSTRUCTION INTERFACES
            //PUBLIC - PRIVATE
            case 122099://public
                c.getPA().startTeleport2(2060, 3261, 0);
                c.sendMessage("You teleported to the public Construction zone.");
            break;
 
            case 122102://private
                c.getPA().startTeleport2(2060, 3261, c.playerId * 4);
                c.sendMessage("You teleported to the private Construction zone.");
            break;

	case 115121:
	c.getPA().closeAllWindows();
	break;
 
            //CHOOSE WHAT TO BUILD
            case 122019://fern
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 1) {
                c.sendMessage("You need a level 1 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
                c.getItems().deleteItem2(249, 1);
                c.getItems().deleteItem2(1511, 1);
                c.sendMessage("You build a Fern.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(31 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13432, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
            break;
 
            case 122022://tree
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 5) {
                c.sendMessage("You need a level 5 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
                c.getItems().deleteItem2(1511, 1);
                c.getItems().deleteItem2(1511, 1);
                c.getItems().deleteItem2(1511, 1);
                c.sendMessage("You build a Tree.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(31 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13411, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
            break;
 
            case 122025://chair
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 19) {
                c.sendMessage("You need a level 19 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 10);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Chair.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13584, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122028://bookcase
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 29) {
                c.sendMessage("You need a level 29 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 15);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Bookcase.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13598, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122031://greenman's ale
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 26) {
                c.sendMessage("You need a level 26 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 15);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Greenman's ale.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(184 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13571, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122034://small oven
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 24) {
                c.sendMessage("You need a level 24 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build a Small oven.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(80 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13533, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122037://carved oak bench
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 31) {
                c.sendMessage("You need a level 31 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 15);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Carved oak bench.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(240 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13302, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122040://painting stand
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 41) {
                c.sendMessage("You need a level 41 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 20);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Painting stand.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(240 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13717, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122043://bed
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 40) {
                c.sendMessage("You need a level 40 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
                c.getItems().deleteItem2(1539, 20);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.getItems().deleteItem2(8778, 1);
                c.sendMessage("You build a Bed.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(300 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13151, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122046://teak drawers
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 51) {
                c.sendMessage("You need a level 51 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1)) {
                c.getItems().deleteItem2(1539, 20);
                c.getItems().deleteItem2(8780, 1);
                c.getItems().deleteItem2(8780, 1);
                c.sendMessage("You build a Teak drawers.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(180 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13158, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122049://mithril armour
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 28) {
                c.sendMessage("You need a level 28 Construction to do that.");
                return;
            }
            if(c.playerLevel[13] < 68) {
                c.sendMessage("You need a level 68 Smithing to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1159, 1) && c.getItems().playerHasItem(1121, 1) && c.getItems().playerHasItem(1071, 1)) {
                c.getItems().deleteItem2(1159, 1);
                c.getItems().deleteItem2(1121, 1);
                c.getItems().deleteItem2(1071, 1);
                c.sendMessage("You build a Mithril armour.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(135 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
                c.getPA().checkObjectSpawn(13491, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122052://adamant armour
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 28) {
                c.sendMessage("You need a level 28 Construction to do that.");
                return;
            }
            if(c.playerLevel[13] < 88) {
                c.sendMessage("You need a level 88 Smithing to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1161, 1) && c.getItems().playerHasItem(1123, 1) && c.getItems().playerHasItem(1073, 1)) {
                c.getItems().deleteItem2(1161, 1);
                c.getItems().deleteItem2(1123, 1);
                c.getItems().deleteItem2(1073, 1);
                c.sendMessage("You build a Adamant armour.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(150 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
                c.getPA().checkObjectSpawn(13492, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122055://rune armour
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 28) {
                c.sendMessage("You need a level 28 Construction to do that.");
                return;
            }
            if(c.playerLevel[13] < 99) {
                c.sendMessage("You need a level 99 Smithing to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1163, 1) && c.getItems().playerHasItem(1127, 1) && c.getItems().playerHasItem(1079, 1)) {
                c.getItems().deleteItem2(1163, 1);
                c.getItems().deleteItem2(1127, 1);
                c.getItems().deleteItem2(1079, 1);
                c.sendMessage("You build a Rune armour.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(165 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
                c.getPA().checkObjectSpawn(13493, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
 
            case 122058://rune display case
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 28) {
                c.sendMessage("You need a level 28 Construction to do that.");
                return;
            }
            if(c.playerLevel[20] < 44) {
                c.sendMessage("You need a level 44 Runecrafting to do that.");
                return;
            }
            if (c.getItems().playerHasItem(563, 100) && c.getItems().playerHasItem(561, 100) && c.getItems().playerHasItem(8780, 1)) {
                c.getItems().deleteItem2(563, 100);
                c.getItems().deleteItem2(561, 1);
                c.getItems().deleteItem2(8780, 1);
                c.sendMessage("You build a Rune display case.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(212 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().addSkillXP(44 * Config.RUNECRAFTING_EXPERIENCE, 20);
                c.getPA().checkObjectSpawn(13508, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122061://archery target
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 81) {
                c.sendMessage("You need a level 81 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 25) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1)) {
                c.getItems().deleteItem2(1539, 25);
                c.getItems().deleteItem2(8780, 1);
                c.getItems().deleteItem2(8780, 1);
                c.getItems().deleteItem2(8780, 1);
                c.sendMessage("You build an Archery target.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(600 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13402, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122064://combat stone
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 59) {
                c.sendMessage("You need a level 59 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build a Combat stone.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(200 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
                Server.npcHandler.spawnNpc(c, 4162, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122067://elemental balance
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 77) {
                c.sendMessage("You need a level 77 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build an Elemental balance.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(356 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
                Server.npcHandler.spawnNpc(c, 4095, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122070://mahogany prize chest
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 54) {
                c.sendMessage("You need a level 54 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1)) {
                c.getItems().deleteItem2(1539, 20);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.sendMessage("You build a Mahogany prize chest.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(860 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13389, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122073://lectern
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 67) {
                c.sendMessage("You need a level 67 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1)) {
                c.getItems().deleteItem2(1539, 40);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.sendMessage("You build a Lectern.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(580 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13648, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122076://crystal of power
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 66) {
                c.sendMessage("You need a level 66 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(1539, 15);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build a Crystal of power.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(890 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13661, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122079://altar
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 64) {
                c.sendMessage("You need a level 64 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(1539, 15);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build an Altar.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(910 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13191, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122082://intense burners
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 61) {
                c.sendMessage("You need a level 61 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(263, 1)) {
                c.getItems().deleteItem2(1539, 10);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(263, 1);
                c.sendMessage("You build an Intense burners.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(280 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13210, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122085://hedge
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 80) {
                c.sendMessage("You need a level 80 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(263, 1) && c.getItems().playerHasItem(263, 1)) {
                c.getItems().deleteItem2(1511, 1);
                c.getItems().deleteItem2(1511, 1);
                c.getItems().deleteItem2(263, 1);
                c.getItems().deleteItem2(263, 1);
                c.sendMessage("You build a Hedge.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(316 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13476, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122088://rocnar
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 83) {
                c.sendMessage("You need a level 83 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(2361, 1) && c.getItems().playerHasItem(2361, 1) && c.getItems().playerHasItem(263, 1) && c.getItems().playerHasItem(263, 1)) {
                c.getItems().deleteItem2(2361, 1);
                c.getItems().deleteItem2(2361, 1);
                c.getItems().deleteItem2(263, 1);
                c.getItems().deleteItem2(263, 1);
                c.sendMessage("You build a Rocnar.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(387 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(13373, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
 
            case 122091://bank chest
            if(!c.getItems().playerHasItem(2347, 1)) {
                c.sendMessage("You need a hammer to do that.");
                return;
            }
            if(c.playerLevel[23] < 92) {
                c.sendMessage("You need a level 92 Construction to do that.");
                return;
            }
            if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
                c.getItems().deleteItem2(1539, 40);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(8782, 1);
                c.getItems().deleteItem2(2351, 1);
                c.sendMessage("You build a Bank chest.");
                c.getPA().closeAllWindows();
                c.getPA().addSkillXP(800 * Config.CONSTRUCTION_EXPERIENCE, 23);
                c.getPA().checkObjectSpawn(3193, c.absX, c.absY, c.heightLevel, 10);
                } else {
                c.sendMessage("You don't have the required materials.");
                }
                 
            break;
			case 113238:
			c.sendMessage("<col=1532693>Total Ingame Play Time</col>");
			break;
			case 189125: ///The Staff Tab
			if (c.inWild())
			return;
			 if (c.playerRights >= 1) { 
			c.sendMessage("You must Logout when you choose title to come into effect");
			c.getPA().showInterface(17000);
			}
			break;
			case 189121: ///The Tab ItSelf
			if (c.inWild())
			return;
			c.sendMessage("You must Logout when you choose title to come into effect");
			c.getPA().showInterface(21850);
			break;
			case 85102: ///Auras
			case 93054:
			///c.getPA().showInterface(85111);
			c.sendMessage("Comming Soon.");
			break;
			case 85111: ///Pet Shop
			c.sendMessage("Comming Soon.");
			break;
			case 85114: ///Items
			case 93066:
			///c.getPA().showInterface(85111);
			c.sendMessage("Comming Soon.");
			break;
			case 93069: ///Titles
			c.sendMessage("You must Logout when you choose title to come into effect");
			c.getPA().showInterface(21850);
			break;
			case 85121:///Features
			case 90373:
			///c.getPA().showInterface(85111);
			c.sendMessage("Comming Soon.");
			break;
			case 85137: ///Special Offers
			case 93089:
			///c.getPA().showInterface(85111);
			c.sendMessage("Comming Soon.");
			break;
			case 85151:///Closing Shop Down
			case 93103:
			case 67058:
			c.getPA().closeAllWindows();
			break;
			
			/**
			*Staff Player Titles
			**/
			
			case 67060:///Main Owner
			if (c.playerName.equalsIgnoreCase(""+Config.OWNER+"")) {
					c.playerTitle = 17;
					c.sendMessage("You have been givent the title [Main Owner], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67062:///Co-Owner
			if (c.playerName.equalsIgnoreCase(""+Config.CO_OWNER+"")) {
					c.playerTitle = 18;
					c.sendMessage("You have been givent the title [Co Owner], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67064:///Head Admin
			if (c.playerName.equalsIgnoreCase(""+Config.HEAD_ADMIN+"")) {
					c.playerTitle = 19;
					c.sendMessage("You have been givent the title [Head Admin], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67066:///Admin
			if (c.playerRights == 5) {
					c.playerTitle = 20;
					c.sendMessage("You have been givent the title [Admin], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67068:///Head Mod
			if (c.playerName.equalsIgnoreCase(""+Config.HEAD_MOD+"")) {
					c.playerTitle = 21;
					c.sendMessage("You have been givent the title [Head Mod], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67070:///Mod
			if (c.playerRights == 1) {
					c.playerTitle = 22;
					c.sendMessage("You have been givent the title [Mod], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67072:///Donator
			if (c.isDonator >= 1) {
					c.playerTitle = 25;
					c.sendMessage("You have been givent the title [Donator], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			case 67074:///Graphics
			if (c.playerRights == 10) {
					c.playerTitle = 26;
					c.sendMessage("You have been givent the title [Graphics], you must relog");
					c.getPA().removeAllWindows();
					}
			break;
			/**
			*
			**/
			
			/*NewTeleTabsStart*/
						case 59119:
   c.getPA().spellTeleport(2676, 3715, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 59122:
   c.getPA().spellTeleport(2884, 9798, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 59125:
   c.getPA().spellTeleport(3428, 3537, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 59128:
   c.getPA().spellTeleport(2710, 9466, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 63095:
   c.getPA().spellTeleport(2895, 2727, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 110072:
   c.getPA().spellTeleport(2661, 3306, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 110069://hunter
   c.getPA().spellTeleport(2602, 4775, 0);
   			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
   break;
   case 59138:
			c.setSidebarInterface(6, 16220);
			break;
			case 86226:
			c.setSidebarInterface(6, 33320);
			break;
			case 130081:
			c.setSidebarInterface(6, 22220);
			break;
			case 130077:
			c.setSidebarInterface(6, 44430);
			break;
			case 173173:
			c.setSidebarInterface(6, 33330);
			break;
			case 173169:
			c.setSidebarInterface(6, 44230);
			break;
			case 172225:
			c.setSidebarInterface(6, 44430);
			break;
			case 130073:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 172221:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 173165:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 63114:
			c.setSidebarInterface(6, 15220);
			break;
			case 63111:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 59135:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 50235:
			case 4140:
			
			c.getDH().sendOption5("Barrows", "Pest Control", "Boss Endurance", "Duel Arena", "Mage Arena");
			c.dialogueAction = 9999;
			break;
			
			case 75023://barrows
			c.getPA().spellTeleport(3565, 3314, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 75026:// pc
			c.getPA().spellTeleport(2662, 2650, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 75029:// tzhaar
			c.getPA().spellTeleport(2444, 5170, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 75032:// beehivemini
			c.getPA().spellTeleport(2758, 3450, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			/*home teleport*/
			case 12856:
			c.getPA().spellTeleport(2769, 2979, 0);
			break;
			
			case 75039:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 4143:
			case 50245:
			c.getPA().spellTeleport(2581, 9849, 0);
			break;
			
			case 86207://godwars
			c.getPA().spellTeleport(2882, 5310, 2);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 86210://kbd
			c.getPA().spellTeleport(3007, 3849, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 86213://dagannoth king
			c.getPA().spellTeleport(2479, 10147, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 86216://corporeal beast
			c.getPA().spellTeleport(3302, 9372, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130043://tormented demons
			c.getPA().spellTeleport(2717, 9805, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130046://icy bones
			c.getPA().spellTeleport(3054, 9576, 0);
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130049://balanced elemental
			c.getPA().spellTeleport(2560, 4950, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 130052://sea troll queen
			c.getPA().spellTeleport(2345, 3694, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130065:
			c.setSidebarInterface(6, 22220);
			break;
			case 130062:
			c.setSidebarInterface(6, 44420);
			break;
			
			case 173135://nomad
			c.getPA().spellTeleport(3253, 9517, 2);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 173138://bal'lak
			c.getPA().spellTeleport(1992, 4642, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 173141://giant mole
			c.getPA().spellTeleport(1763, 5196, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 173144://kalphite queen
			c.getPA().spellTeleport(3505, 9494, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;

			case 173157:
			c.setSidebarInterface(6, 33320);
			break;
			case 173154:
			c.setSidebarInterface(6, 33420);
			break;
			
			case 130143://pheonix lair
			c.getPA().spellTeleport(2384, 4712, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130146://rammernauts
			c.getPA().spellTeleport(3176, 9758, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130149://arzinian avatars
			c.getPA().spellTeleport(3077, 3915, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130162:
			c.setSidebarInterface(6, 44420);
			break;
			
			case 172207:
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 173151:
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			case 130059:
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
case 130159:
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 130152://nex
			c.sendMessage("Freshly added, good luck!");
			c.getPA().spellTeleport(3234, 9364, 12);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 86223:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 50253:
			case 4146:
			
			c.getPA().spellTeleport(2806, 2708, 0);
			//c.getDH().sendOption5("Godwars", "King Black Dragon", "Dagannoth Kings", "Corporeal Beast", "More");
			//c.dialogueAction = 9998;
			//c.setSidebarInterface(6, 22220);
			//c.getDH().sendOption5("Godwars", "King Black Dragon @red@(Wildy)", "Dagannoth Kings", "Chaos Elemental@red@(Wildy)", "Coming Soon");
			//c.teleAction = 3;
			break;
			
			case 94159://edge
			c.getPA().spellTeleport(3087, 3497, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 94162://graveyard
			c.getPA().spellTeleport(3164, 3685, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 94165://FunPK
			c.getPA().spellTeleport(2604, 3154, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 94168://greater demons
			c.getPA().spellTeleport(3288, 3886, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 106103:
			case 98151:
			case 94175:
			case 114055:
			case 110079:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 94178:
			c.setSidebarInterface(6, 25220);
			break;
			
			case 98154:
			c.setSidebarInterface(6, 24220);
			break;
			
			case 98135:
			c.getPA().spellTeleport(2561, 3311, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;

			case 51005:
			case 4150:
			c.dialogueAction = 8656;
			c.getDH().sendOption5("Edgeville", "Mage Bank", "Graveyard", "Fun PK", "None");
			//c.teleAction = 4;
			break;			
			
			case 106087:
			c.getPA().spellTeleport(3044, 9779, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 106090:
			c.getPA().spellTeleport(3079, 9502, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 106093:
			c.getPA().spellTeleport(2595, 3417, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 106096:
			c.getPA().spellTeleport(2724, 3484, 0);
			c.getDH().sendDialogues(106096, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 106136:
			c.setSidebarInterface(6, 28220);
			break;
			
			case 110112:
			c.setSidebarInterface(6, 27220);
			break;
			
			case 110082:
			c.setSidebarInterface(6, 27220);
			break;
			
			case 110063:
			c.getPA().spellTeleport(2812, 3463, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 110066:
			c.getPA().spellTeleport(2480, 3437, 0);
						if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 51013:
			case 6004:
			c.getPA().spellTeleport(2515, 3860, 0);
			break; 
			
			/*114039
114042
114045
114048
114055*/

			case 123051:
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); //modern
				}else {
					if(c.playerMagicBook == 2) {
						c.setSidebarInterface(6, 29999); //lunar
				}else {
					c.setSidebarInterface(6, 12855); // ancient
				}
			}
			break;
			
			case 123035://lumbridge
			c.getPA().spellTeleport(3222, 3218, 0);
			break;
			case 123038://varrock
			c.getPA().spellTeleport(3210, 3424, 0);
			break;
			case 123041://falador
			c.getPA().spellTeleport(2964, 3378, 0);
			break;
			case 123044://canifis
			c.getPA().spellTeleport(3506, 3496, 0);
			break;
case 51023:
			case 6005:
			if (c.goldMember == true || c.playerName.equalsIgnoreCase("josh")) {
			c.getPA().spellTeleport(2604, 3877, 0);
			}
			break;
			/*NewTeleTabsEnd*/
			
			/**
			* This is where the player title clicking works.
			**/
			
			case 85163:///Remove Title
					c.playerTitle = 0;
					c.sendMessage("You're Player Title has been removed.");
					c.getPA().removeAllWindows();
					c.getPA().requestUpdates();
			break;
			case 85166:///Sir
			    if (c.playersPked >= 25) { 
					c.playerTitle = 1;
					c.sendMessage("You successfully change your Pk Title to Sir.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85169:///Lord
			    if (c.playersPked >= 50) { 
					c.playerTitle = 2;
					c.sendMessage("You successfully change your Pk Title to Lord.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			
			case 85172:///Monster
			    if (c.playersPked >= 75) { 
					c.playerTitle = 3;
					c.sendMessage("You successfully change your Pk Title to Monster.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85175:///Unreal
			    if (c.playersPked >= 100) { 
					c.playerTitle = 4;
					c.sendMessage("You successfully change your Pk Title to Unreal.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85178:///HellRaiser
			    if (c.playersPked >= 150) { 
					c.playerTitle = 5;
					c.sendMessage("You successfully change your Pk Title HellRaiser.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;case 85181:///Archon
			    if (c.playersPked >= 200) { 
					c.playerTitle = 6;
					c.sendMessage("You successfully change your Pk Title to Archon.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85184:///Stoner
			    if (c.playersPked >= 250) { 
					c.playerTitle = 7;
					c.sendMessage("You successfully change your Pk Title to Stoner.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85187:///Chronic
			    if (c.playersPked >= 300) { 
					c.playerTitle = 8;
					c.sendMessage("You successfully change your Pk Title to Chronic.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85190:///OverLord
			    if (c.playersPked >= 350) { 
					c.playerTitle = 9;
					c.sendMessage("You successfully change your Pk Title to OverLord.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85193:///The Divine
			    if (c.playersPked >= 400) { 
					c.playerTitle = 10;
					c.sendMessage("You successfully change your Pk Title to The Divine.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85196:///The Fallen
			    if (c.playersPked >= 450) { 
					c.playerTitle = 11;
					c.sendMessage("You successfully change your Pk Title to The Fallen.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85199:///Commander
			    if (c.playersPked >= 500) { 
					c.playerTitle = 12;
					c.sendMessage("You successfully change your Pk Title to Commander.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85202:///War-Chief
			    if (c.playersPked >= 600) { 
					c.playerTitle = 13;
					c.sendMessage("You successfully change your Pk Title to War-Chief.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85205:///Pimp
			    if (c.playersPked >= 700) { 
					c.playerTitle = 14;
					c.sendMessage("You successfully change your Pk Title to Pimp.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85208:///Pornstar
			    if (c.playersPked >= 800) { 
					c.playerTitle = 15;
					c.sendMessage("You successfully change your Pk Title to Pornstar.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			break;
			case 85211:///Pk Master
			    if (c.playersPked >= 1000) { 
					c.playerTitle = 16;
					c.sendMessage("You successfully change your Pk Title to Pk Master.");
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You have not earned enough kills for this title.");
					c.getPA().removeAllWindows();
				}
			case 113255:///Members Title
			    if (c.npcKills >= 1000) { 
					c.playerTitle = 27;
					c.sendMessage("You successfully change your Pk Title to [Member].");
				}
			break;
			
			/**
			*Where the Titles End
			**/
			/**
			*New XpLamp Begins
			**/
		case 136191:
		c.antiqueSelect = 0;
		c.sendMessage("You select Attack");//Done
		break;
		case 136233:
		c.antiqueSelect = 1;
			c.sendMessage("You select Defence");//Done
		break;
		case 136212:
		c.antiqueSelect = 2;
			c.sendMessage("You select Strength");//Done
		break;
		case 136254:
		c.antiqueSelect = 3;
			c.sendMessage("You select Hitpoints");//Done
		break;
		case 136215:
		c.antiqueSelect = 4;
			c.sendMessage("You select Ranged");//Done
		break;
		case 136236:
		c.antiqueSelect = 5;
			c.sendMessage("You select Prayer");//Done
		break;
		
		case 136194:
		c.antiqueSelect = 6;
			c.sendMessage("You select Magic");//Done
		break;
		
		case 137004:
		c.antiqueSelect = 7;
			c.sendMessage("You select Cooking");//Done
		break;
		case 136200:
		c.antiqueSelect = 8;
			c.sendMessage("You select Woodcutting");//Done
		break;
		case 136206:
		c.antiqueSelect = 9;
			c.sendMessage("You select Fletching");
		break;
		case 136239:
		c.antiqueSelect = 10;
			c.sendMessage("You select Fishing");//Done
		break;
		case 136221:
		c.antiqueSelect = 11;
			c.sendMessage("You select Firemaking");//Done
		break;
		case 136242:
		c.antiqueSelect = 12;
			c.sendMessage("You select Crafting");//Done
		break;
		case 136218:
		c.antiqueSelect = 13;
			c.sendMessage("You select Smithing");//Done
		break;
		case 136197:
		c.antiqueSelect = 14;
			c.sendMessage("You select Mining");//Done
		break;
		case 136224:
		c.antiqueSelect = 15;
			c.sendMessage("You select Herblore");//Done
		break;
		case 136203:
		c.antiqueSelect = 16;
			c.sendMessage("You select Agility");//Done
		break;
		case 136209:
		c.antiqueSelect = 17;
			c.sendMessage("You select Thieving");//Done
		break;
		case 136227:
		c.antiqueSelect = 18;
			c.sendMessage("You select Slayer");//Done
		break;
		case 136245:
		c.antiqueSelect = 19;
			c.sendMessage("You select Farming");//Done
		break;
		case 137007:
		c.antiqueSelect = 20;
			c.sendMessage("You select Runecrafting");//Done
		break;
		case 136248:
		c.antiqueSelect = 21;
			c.sendMessage("You select Hunter");//Done
		break;
		case 136251:
		c.antiqueSelect = 22;
			c.sendMessage("You select Summoning");//Done
		break;
		case 136230:
		c.antiqueSelect = 23;
			c.sendMessage("You select Pking");//Done
		break;
		case 137001:
		c.antiqueSelect = 24;
			c.sendMessage("You select Dungeoneering");//Done
		break;
		
		case 137013://Confirm
        c.getPA().addSkillXP(500000,c.antiqueSelect);
        c.getItems().deleteItem2(2528, 1);
        c.sendMessage("The lamp mysteriously vanishes");
        c.getPA().closeAllWindows();
		if(c.antiqueSelect == 0)
		c.sendMessage("You have Chosen 500K Xp in Attack");
		else if(c.antiqueSelect == 1)
		c.sendMessage("You have Chosen 500K Xp in Defence");
		else if(c.antiqueSelect == 2)
		c.sendMessage("You have Chosen 500K Xp in Strength");
		else if(c.antiqueSelect == 3)
		c.sendMessage("You have Chosen 500K Xp in Constitution");
		else if(c.antiqueSelect == 4)
		c.sendMessage("You have Chosen 500K Xp in Ranged");
		else if(c.antiqueSelect == 5)
		c.sendMessage("You have Chosen 500K Xp in Prayer");
		else if(c.antiqueSelect == 6)
		c.sendMessage("You have Chosen 500K Xp in Magic");
		else if(c.antiqueSelect == 7)
		c.sendMessage("You have Chosen 500K Xp in Cooking");
		else if(c.antiqueSelect == 8)
		c.sendMessage("You have Chosen 500K Xp in Woodcutting");
		else if(c.antiqueSelect == 9)
		c.sendMessage("You have Chosen 500K Xp in Fletching");
		else if(c.antiqueSelect == 10)
		c.sendMessage("You have Chosen 500K Xp in Fishing");
		else if(c.antiqueSelect == 11)
		c.sendMessage("You have Chosen 500K Xp in Firemaking");
		else if(c.antiqueSelect == 12)
		c.sendMessage("You have Chosen 500K Xp in Crafting");
		else if(c.antiqueSelect == 13)
		c.sendMessage("You have Chosen 500K Xp in Smithing");
		else if(c.antiqueSelect == 14)
		c.sendMessage("You have Chosen 500K Xp in Mining");
		else if(c.antiqueSelect == 15)
		c.sendMessage("You have Chosen 500K Xp in Herblore");
		else if(c.antiqueSelect == 16)
		c.sendMessage("You have Chosen 500K Xp in Agility");
		else if(c.antiqueSelect == 17)
		c.sendMessage("You have Chosen 500K Xp in Thieving");
		else if(c.antiqueSelect == 18)
		c.sendMessage("You have Chosen 500K Xp in Slayer");
		else if(c.antiqueSelect == 19)
		c.sendMessage("You have Chosen 500K Xp in Farming");
		else if(c.antiqueSelect == 20)
		c.sendMessage("You have Chosen 500K Xp in Runecrafting");
		else if(c.antiqueSelect == 21)
		c.sendMessage("You have Chosen 500K Xp in Hunter");
		else if(c.antiqueSelect == 22)
		c.sendMessage("You have Chosen 500K Xp in Summoning");
		else if(c.antiqueSelect == 23)
		c.sendMessage("You have Chosen 500K Xp in Pk'ing");
		else if(c.antiqueSelect == 24)
		c.sendMessage("You have Chosen 500K Xp in Dungeoneering");
    break;

		/**case 19361:
		c.getPA().addSkillXP(7000, 500000);
		c.getPA().removeAllWindows();
			break;*/
			
			
		case 189118:
			if (c.Wheel == 0) {
		c.getDH().sendDialogues(57, 945);
		} else if (c.Wheel > 0) {
		c.getItems().addItem(c.getPA().Wheel(), 1);
		c.Wheel = (c.Wheel - 1);
		c.getDH().sendDialogues(58, 945);
		}
		break;	
			case 113236:
			c.getPA().sendFrame126(""+ Config.FORUMS +"", 12000);
			break;
                        case 21010:
                        c.takeAsNote = true;
                        break;
                        case 21011:
                        c.takeAsNote = false;
                        break;
			case 68244:
c.getPA().startTeleport(2676, 3711, 0, "modern");
break;





		/*case 114112://melee set
		if (c.inWild() && c.isBanking) {
		c.sendMessage("You cannot do this right now");
		} else if(c.getItems().freeSlots() <= 10) {
		c.sendMessage("You need atleast 10 free slot's to use this feature.");
		} else if (c.getItems().playerHasItem(995, 350000)) {
		c.getItems().deleteItem2(995, 350000);
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(1127, 1);
		c.getItems().addItem(1079, 1);
		c.getItems().addItem(3842, 1);
		c.getItems().addItem(4587, 1);
		c.getItems().addItem(1231, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(2550, 1);
		} else {
		c.sendMessage("You need atleast 350,000 coins to use this feature.");
		}
		break;
case 23132: //unmorph
                c.isMorphed = false;
		c.setSidebarInterface(1, 3917);
		c.setSidebarInterface(2, 638);
		c.setSidebarInterface(3, 3213);
		c.setSidebarInterface(4, 1644);
		c.setSidebarInterface(5, 5608);
			if(c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151);
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855);
			} else if (c.playerMagicBook == 2) {
				c.setSidebarInterface(6, 29999);
			}
		c.setSidebarInterface(7, 18128);
		c.setSidebarInterface(8, 5065);
		c.setSidebarInterface(9, 5715); 
		c.setSidebarInterface(10, 2449);
		c.setSidebarInterface(11, 904);
		c.setSidebarInterface(12, 147);
		c.setSidebarInterface(13, 962);
		c.setSidebarInterface(0, 2423);
if (c.playerEquipment[c.playerRing] == 7927) {
c.getItems().deleteEquipment(c.playerEquipment[c.playerRing], c.playerRing);
c.getItems().addItem(7927,1);
}
c.isNpc = false;
c.updateRequired = true;
c.appearanceUpdateRequired = true;
break;
			case 46230:
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(10551, 1);
		c.getItems().addItem(4087, 1);
		c.getItems().addItem(11732, 1);
		c.getItems().addItem(13006, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(6737, 1);
		c.getItems().addItem(8850, 1);
		c.getItems().addItem(4151, 1);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 1);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 5);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			case 46234:
		c.getItems().addItem(10941, 1);
		c.getItems().addItem(10939, 1);
		c.getItems().addItem(10940, 1);
		c.getItems().addItem(10933, 1);
		c.getItems().addItem(18508, 1);
		c.getItems().addItem(2462, 1);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
			break;
			case 46227:
		c.getItems().addItem(12222, 1);
		c.getItems().addItem(6107, 1);
		c.getItems().addItem(2497, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(12988, 1);
		c.getItems().addItem(10498, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(4151, 1);
		c.getItems().addItem(892, 1000);
		c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			
					case 114113://mage set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 7) {
				c.sendMessage("You need atleast 7 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3755, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(3842, 1);
				c.getItems().addItem(4675, 1);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;
			
								case 114114://range set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 13) {
				c.sendMessage("You need atleast 13 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(3749, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2491, 1);
				c.getItems().addItem(6328, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(9185, 1);
				c.getItems().addItem(9243, 100);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(892, 100);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
			case 114115://hybrid set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 14) {
				c.sendMessage("You need atleast 14 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(555, 300);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(565, 100);
				c.getItems().addItem(4675, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2415, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3105, 1);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
						case 114118://runes set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 10) {
				c.sendMessage("You need atleast 10 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(560,1000);
				c.getItems().addItem(555,1000);
				c.getItems().addItem(565,1000);
				c.getItems().addItem(9075,1000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(556,1000);
				c.getItems().addItem(554,1000);
				c.getItems().addItem(562,1000);
				c.getItems().addItem(561,1000);
				c.getItems().addItem(563,1000);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;
			
									case 114119://barrage set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 2000000)) {
				c.getItems().deleteItem2(995, 2000000);
				c.getItems().addItem(555,6000);
				c.getItems().addItem(560,4000);
				c.getItems().addItem(565,2000);
			} else {
				c.sendMessage("You need atleast 2,000,000 coins to use this feature.");
			}
			break;
			
			case 114120://veng set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(560,200);
				c.getItems().addItem(9075,400);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
			case 114123://shark set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(385,1000);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
						case 114124://tuna pot set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 150000)) {
				c.getItems().deleteItem2(995, 150000);
				c.getItems().addItem(7060,1000);
			} else {
				c.sendMessage("You need atleast 150,000 coins to use this feature.");
			}
			break;
			
			case 114125://super set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 80000)) {
				c.getItems().deleteItem2(995, 80000);
				c.getItems().addItem(146,100);
				c.getItems().addItem(158,100);
				c.getItems().addItem(164,100);
			} else {
				c.sendMessage("You need atleast 80,000 coins to use this feature.");
			}
			break;
			
						case 114126://super restores biatch
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3025,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114127://mage pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3041,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114128://range pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 36000)) {
				c.getItems().deleteItem2(995, 36000);
				c.getItems().addItem(2445,100);
			} else {
				c.sendMessage("You need atleast 36,000 coins to use this feature.");
			}
			break;*/
			
			
			


		
			
		case 17111://stop viewing viewing orb
                c.setSidebarInterface(10, 2449);
                c.viewingOrb = false;
                c.teleportToX = 2399;
                c.teleportToY = 5171;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59139://viewing orb southwest
                c.viewingOrb = true;
                c.teleportToX = 2388;
                c.teleportToY = 5138;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            /*case 59138://viewing orb southeast
                c.viewingOrb = true;
                c.teleportToX = 2411;
                c.teleportToY = 5137;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;*/

            case 59137://viewing orb northeast
                c.viewingOrb = true;
                c.teleportToX = 2409;
                c.teleportToY = 5158;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59136://viewing orb northwest
                c.viewingOrb = true;
                c.teleportToX = 2384;
                c.teleportToY = 5157;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            /*case 59135://viewing orb middle
                c.viewingOrb = true;
                c.teleportToX = 2398;
                c.teleportToY = 5150;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;*/
			case 107229:
				if (c.isDonator == 1 && c.inGWD()) {
				c.Arma = 15;
				c.Band = 15;
				c.Sara = 15;
				c.Zammy = 15;
					c.sendMessage("Your Conquest-317 donator rank forces your KC to raise to 15!");
				} else {
					c.sendMessage("You must be a Conquest-317 donator and be in godwars dungeon to use this!");
				} 
			break;

				case 108003:
				if (c.isDonator == 1) {
				c.setSidebarInterface(4, 27620);
				} else {
				c.sendMessage("You must be an Conquest-317 donator to view this tab!");
				return;				
				}
				break;

				case 107231:
				if (c.isDonator == 1) {
				c.getPA().spellTeleport(3048, 4973, 1);
				c.sendMessage("You teleported to Conquest-317's donator-zone a place to chill/relax.");
				} else {
				c.sendMessage("You must be a Conquest-317 donator to teleport to the Conquest-317 donator-zone!");
				return;				
				}
				break;
			case 108006:
				if (c.xpLock == false) {
					c.xpLock = true;
					c.sendMessage("Your XP is now LOCKED!");
				} else {
					c.xpLock = false;
					c.sendMessage("Your XP is now UNLOCKED!");
				} 
			break;
			case 107230:
			if(c.isDonator == 0 || c.inWild()) {
			c.sendMessage("You must be outside wilderness and be a donator to use this!");
			return;
			}
			if (c.playerMagicBook == 0 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.setSidebarInterface(0, 328);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				return;
			}	
			if (c.playerMagicBook == 1 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 2;
				c.setSidebarInterface(0, 328);
				c.setSidebarInterface(6, 16640);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				return;
			}
			if (c.playerMagicBook == 2 && c.isDonator == 1 && !c.inWild()) {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.setSidebarInterface(0, 328);
				c.sendMessage("You feel a drain on your memory.(Gone back to normal)");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				return;
			}
			break;
					case 94142:
if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
{
Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
c.storeditems[i] = -1;
c.occupied[i] = false;
}
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.sendMessage("Your Spawned NPC's have been put away.");
} else {
c.sendMessage("You do not have a npc currently spawned");
}
			//1st tele option
			case 9190:
				
				 if (c.dialogueAction == 5678) {
					c.altarPrayed = 0;
c.setSidebarInterface(5, 5608);
c.startAnimation(645);
c.sendMessage("The priest enlightens you with his wisdom!");
 c.getCurse().resetCurse();
				}
				 if (c.dialogueAction == 8656) {
					
					c.getPA().spellTeleport(3087, 3496, 0);
					
					}
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.dialogueAction == 9999) {
					//rock crabs
					c.getPA().spellTeleport(3565, 3308, 0);
					c.getItems().addItem(952, 1);
				} else if (c.dialogueAction == 9998) {
					
					c.getPA().spellTeleport(2882, 5310, 2);
				} else if (c.dialogueAction == 9997) {
					
					c.getPA().spellTeleport(2717, 9805, 0);
					} else if (c.dialogueAction == 9996) {
					
					c.getPA().spellTeleport(3505, 9494, 0);
						
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				} else if (c.teleAction == 3) {
					//godwars
					c.sendMessage("You teleport to Godwars.");
					c.getPA().spellTeleport(2882, 5310, 2);
					c.teleAction = -1;
				} else if (c.teleAction == 4) {
					//varrock wildy
					c.getPA().spellTeleport(2539, 4716, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				} else if (c.teleAction == 20) {
					//lum
					c.getPA().spellTeleport(3222, 3218, 0);//3222 3218 
				}
				if (c.dialogueAction == 2222) {
				c.getShops().openShop(2);
				}
				if (c.dialogueAction == 2223) {
				c.getPA().spellTeleport(3577, 9927, 0);//3222 3218 
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2786, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 301) {
					c.getShops().openShop(6);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 121) {
					c.getPA().spellTeleport(2717, 9805, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 122) {
					c.getPA().spellTeleport(3253, 9517, 2);
					c.dialogueAction = -1;
				 } else if (c.dialogueAction == 123) {
					c.getPA().spellTeleport(2384, 4712, 0);
					c.sendMessage("A mysterious force transports you to the Phoenix's Lair.");
					c.dialogueAction = -1;
				
				}
				break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0
                case 154:
                        if(System.currentTimeMillis() - c.logoutDelay < 8000) {
                                c.sendMessage("You cannot do skillcape emotes in combat!");
                        return;
                        }
                        if(System.currentTimeMillis() - c.lastEmote >= 7000) {
                        if(c.getPA().wearingCape(c.playerEquipment[c.playerCape])) {
                                c.stopMovement();
                                c.gfx0(c.getPA().skillcapeGfx(c.playerEquipment[c.playerCape]));
                                c.startAnimation(c.getPA().skillcapeEmote(c.playerEquipment[c.playerCape]));
                } else if(c.playerEquipment[c.playerCape] == 19710) {
                        c.getPA().dungemote(c);
		} else if(c.playerEquipment[c.playerCape] == 14046) {
                        c.startAnimation(352);
                } else if(c.playerEquipment[c.playerCape] == 19709) {
                        c.getPA().dungemote(c);
			c.gfx0(1600);
                } else {
                                c.sendMessage("You must be wearing a Skillcape to do this emote.");
                        }
                                c.lastEmote = System.currentTimeMillis();
                }
                break;
			//2nd tele option
			case 9191:

 if (c.dialogueAction == 5678) {
c.altarPrayed = 1;
c.setSidebarInterface(5, 22500);
c.startAnimation(645);
c.sendMessage("The priest enlightens you with his wisdom!");
c.getCombat().resetPrayers();
				}
if (c.dialogueAction == 2223) {
				c.getPA().spellTeleport(3429, 3537, 0);//3222 3218 
				}
 if (c.dialogueAction == 8656) {
					
					c.getPA().spellTeleport(2539, 4716, 0);
					
					}
if (c.dialogueAction == 9999) {
					//rock crabs
					c.getPA().spellTeleport(2659, 2676, 0);
					}
 if (c.dialogueAction == 9998) {
					
					c.getPA().spellTeleport(3007, 3849, 0);
					}
 if (c.dialogueAction == 9997) {
					
					c.getPA().spellTeleport(2560, 4950, 0);
					}
 if (c.dialogueAction == 9996) {
					
					c.getPA().spellTeleport(3234, 9364, 12);
					}
if (c.dialogueAction == 2222 && c.combatLevel > 4) {
				c.getShops().openShop(3);
				}
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(2662, 2650, 0);
				} else if (c.teleAction == 3) {
					//kbd
					c.getPA().spellTeleport(3007, 3849, 0);
					c.teleAction = -1;
				} else if (c.teleAction == 4) {
					//graveyard
					c.getPA().spellTeleport(2981, 3595, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079,9502,0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3210,3424,0);//3210 3424
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2796, 4818, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2464, 4834, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 301) {
					c.getShops().openShop(5);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 121) {
					c.getPA().spellTeleport(3054, 9576, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 122) {
					c.getPA().spellTeleport(1993, 4646, 0);
					c.sendMessage("There are several safespots for Bal'lak, use your eyes!");
					c.dialogueAction = -1;
				}else if (c.dialogueAction == 123) {
					c.getPA().spellTeleport(3176, 9758, 0);
					c.dialogueAction = -1;
				}
				break;
			//3rd tele option	

			case 9192:

 if (c.dialogueAction == 5678) {
c.setSidebarInterface(0, 328);
c.setSidebarInterface(6, 1151); //modern
c.playerMagicBook = 0;
c.sendMessage("The priest teaches you his fine arts.");
c.autocastId = -1;
c.getPA().resetAutocast();
				}
 if (c.dialogueAction == 8656) {
					
					c.getPA().spellTeleport(3165, 3684, 0);
					
					}
if (c.dialogueAction == 9999) {
c.getPA().closeAllWindows();
				c.Waittt += 10;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
			c.sendMessage("Your first boss will arrive in 5 seconds, after death another will spawn.");
			
}
 if (c.dialogueAction == 9998) {
					
					c.getPA().spellTeleport(2479, 10147, 0);
					}
if (c.dialogueAction == 9997) {
					
					c.getPA().spellTeleport(3253, 9517, 2);
					}
 if (c.dialogueAction == 9996) {
					
					c.getPA().spellTeleport(1992, 4642, 0);
					}
if (c.dialogueAction == 2223) {
if (c.goldMember == true) {
				c.getPA().spellTeleport(3206, 9379, 0);//3222 3218 
} else {
c.sendMessage("This is a Gold members' area!");
}
				}
if (c.dialogueAction == 2222 && c.combatLevel > 50) {
				c.getShops().openShop(4);
				}
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2438, 5168, 0);
					c.sendMessage("To fight Jad, enter the cave.");
				} else if (c.teleAction == 3) {
					//dag kings
					c.getPA().spellTeleport(1910, 4367, 0);
					c.sendMessage("Climb down the ladder to get into the lair.");
					c.teleAction = -1;
				} else if (c.teleAction == 4) {
					//Hillz
						c.dialogueId = 51;
					c.getDH().sendDialogues(c.dialogueId, 0);
									
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2597,3408,0);
				}
				 else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2757,3477,0);
				}

				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 301) {
					c.getShops().openShop(2);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 121) {
					c.getPA().spellTeleport(2560, 4950, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 122) {
					c.getPA().spellTeleport(1763, 5196, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 123) {
					c.getPA().spellTeleport(3077, 3915, 0);
					c.dialogueAction = -1;
				}
				break;
			//4th tele option
			case 9193:
 if (c.dialogueAction == 5678) {
c.setSidebarInterface(0, 328);
c.playerMagicBook = 1;
c.setSidebarInterface(6, 12855);
c.sendMessage("The priest teaches you his fine arts.");
c.getPA().resetAutocast();
				}
if (c.dialogueAction == 9999) {
					//rock crabs
					c.getPA().spellTeleport(3367, 3267, 0);

					}
 if (c.dialogueAction == 8656) {
					
					c.getPA().spellTeleport(2604, 3154, 0);
					
					}
 if (c.dialogueAction == 9998) {
					
					c.getPA().spellTeleport(3302, 9372,0);
					}
if (c.dialogueAction == 9997) {
					
					c.getDH().sendOption5("Kalphite Queen", "Nex", "Bal'Lak", "Pheonix", "Back");
					c.dialogueAction = 9996;
}
 if (c.dialogueAction == 9996) {
					
					c.getPA().spellTeleport(2384, 4712, 0);
					}
if (c.dialogueAction == 2223) {
				c.getPA().spellTeleport(3243, 9364, 0);//3222 3218 
				}
if (c.dialogueAction == 2222 && c.combatLevel > 100) {
				c.getShops().openShop(5);
				}
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
					c.sendMessage("You teleport to the Brimhaven Dungeon.");
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//chaos elemental
					c.getPA().spellTeleport(3302, 9372, 0);
					c.sendMessage("Enter the gate to fight the mighty Corporeal Beast!");
					c.sendMessage("Note: Magic protect, Ruby bolts (e) and Diamond bolts (e) are recommended!");
					c.getPA().closeAllWindows();
					c.teleAction = -1;
				} else if (c.teleAction == 4) {
					//Fala
				c.getPA().spellTeleport(3086, 3516, 0);

				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 301) {
					c.getShops().openShop(3);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 121) {
					c.getPA().spellTeleport(2345, 3694, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 122) {
					c.getPA().spellTeleport(3505, 9494, 0);
					c.dialogueAction = -1;
				 /*else if (c.dialogueAction == 123) {
					c.getPA().spellTeleport(3365, 3960, 0);
					c.dialogueAction = -1;
				}*/
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2964,3378,0);
				}
				break;
			case 9194:
 if (c.dialogueAction == 5678) {
c.setSidebarInterface(0, 328);
c.playerMagicBook = 2;
c.setSidebarInterface(6, 16640);
c.sendMessage("The priest teaches you his fine arts.");
c.getPA().resetAutocast();	
				}
if (c.dialogueAction == 9999) {
					
c.getPA().spellTeleport(3090, 3934, 0);//3222 3218 
					}
 if (c.dialogueAction == 8656) {
					
					c.getPA().closeAllWindows();
					
					}
 if (c.dialogueAction == 9998) {
c.dialogueAction = 9997;
c.getDH().sendOption5("Tormented Demons", "Balance Elemental", "Nomad", "More", "Back");
c.appearanceUpdateRequired = true;
c.updateRequired = true;
}
if (c.dialogueAction == 9997) {
c.dialogueAction = 9998;
c.getDH().sendOption5("Godwars", "King Black Dragon", "Dagannoth Kings", "Corporeal Beast", "More");
c.appearanceUpdateRequired = true;
c.updateRequired = true;
}
if (c.dialogueAction == 9996) {
c.dialogueAction = 9997;
c.getDH().sendOption5("Tormented Demons", "Balance Elemental", "Nomad", "More", "Back");
c.appearanceUpdateRequired = true;
c.updateRequired = true;
}
if (c.dialogueAction == 2223) {
				c.getPA().spellTeleport(2480, 5175, 0);//3222 3218 
				}
if (c.dialogueAction == 2222 && c.combatLevel > 126) {
				c.getShops().openShop(6);
				}
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(3117, 9847, 0);
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.getPA().spellTeleport(2865,3546,0);
					//c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					c.dialogueId = 191;
					c.getDH().sendDialogues(c.dialogueId, 0);
					c.teleAction = -1;
				} else if (c.teleAction == 4) {
					c.dialogueId = 50;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				} else if (c.dialogueAction == 121) {
					c.dialogueId = 192;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 122) {
					c.dialogueId = 193;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} /*else if (c.dialogueAction == 122) {
					c.getPA().spellTeleport(2715, 9620, 0);
					c.sendMessage("Be careful, a team is REQUIRED here!");
					c.dialogueAction = -1;
				}*/
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 301) {
					c.getShops().openShop(69);
					c.dialogueAction = -1;
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3506,3496,0);
				}
				break;
			
			case 71074:
				if (c.clanId >= 0 && Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
					if (c.CSLS == 0) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						Server.clanChat.clans[c.clanId].CS = 1;
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
						c.CSLS = 1;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					if (c.CSLS == 1) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 2;
						Server.clanChat.clans[c.clanId].CS = 2;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
			c.lastEmote = System.currentTimeMillis();
						return;

				}	
				}	
					if (c.CSLS == 2) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						if(Server.clanChat.clans[c.clanId].playerz == 1) {
						c.sendMessage("There must be atleast 2 members in the clan chat to toggle Coinshare ON.");
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
						}
						c.CSLS = 3;
						Server.clanChat.clans[c.clanId].CS = 3;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
						return;

				}	
				}	
					if (c.CSLS == 3) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					} else {
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;
			
case 15163:
							if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(444, 1) && (c.playerLevel[13] >= 40)) {
						c.sendMessage("<shad=155653>You smelt a gold bar..");
						c.getItems().deleteItem(444, 1);
						c.startAnimation(899);
						c.getItems().addItem(2357, 1);
						c.getPA().addSkillXP(23 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need atleast 40 smithing and a gold ore to smelt a gold bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
				break;
				
						case 15162:
							if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(444, 5) && (c.playerLevel[13] >= 40)) {
						c.sendMessage("<shad=155653>You smelt 5 gold bars..");
						c.getItems().deleteItem(444, 5);
						c.startAnimation(899);
						c.getItems().addItem(2357, 5);
						c.getPA().addSkillXP(115 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need atleast 40 smithing and 5 gold ore to smelt 5 gold bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
				break;
				
						case 15161:
							if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(444, 10) && (c.playerLevel[13] >= 40)) {
						c.sendMessage("<shad=155653>You smelt 10 gold bars..");
						c.getItems().deleteItem(444, 10);
						c.startAnimation(899);
						c.getItems().addItem(2357, 10);
						c.getPA().addSkillXP(230 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need atleast 40 smithing and 10 gold ore to smelt 10 gold bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
				break;
				
						case 15160:
							if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(444, 28) && (c.playerLevel[13] >= 40)) {
						c.sendMessage("<shad=155653>You smelt 28 gold bars..");
						c.getItems().deleteItem(444, 28);
						c.startAnimation(899);
						c.getItems().addItem(2357, 28);
						c.getPA().addSkillXP(644 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need atleast 40 smithing and 28 gold ore to smelt 28 gold bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
				break;
				
						case 15147:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(436, 1) && c.getItems().playerHasItem(438, 1)) {
						c.sendMessage("<shad=155653>You smelt a bronze bar..");
						c.getItems().deleteItem(436, 1);
						c.getItems().deleteItem(438, 1);
						c.startAnimation(899);
						c.getItems().addItem(2349, 1);
						c.getPA().addSkillXP(6 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need a copper and tin ore to smelt a bronze bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
				break;
			
			case 15146:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(436, 5) && c.getItems().playerHasItem(438, 5)) {
						c.sendMessage("<shad=155653>You smelt 5 bronze bars..");
						c.getItems().deleteItem(436, 5);
						c.getItems().deleteItem(438, 5);
						c.startAnimation(899);
						c.getItems().addItem(2349, 5);
						c.getPA().addSkillXP(30 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need 5 copper and 5 tin ore to smelt 5 bronze bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15247:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(436, 10) && c.getItems().playerHasItem(438, 10)) {
						c.sendMessage("<shad=155653>You smelt 10 bronze bars..");
						c.getItems().deleteItem(436, 10);
						c.getItems().deleteItem(438, 10);
						c.startAnimation(899);
						c.getItems().addItem(2349, 10);
						c.getPA().addSkillXP(60 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need 10 copper and 10 tin ore to smelt 10 bronze bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 9110:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(436, 14) && c.getItems().playerHasItem(438, 14)) {
						c.sendMessage("<shad=155653>You smelt 14 bronze bars..");
						c.getItems().deleteItem(436, 14);
						c.getItems().deleteItem1(438, 14);
						c.startAnimation(899);
						c.getItems().addItem(2349, 14);
						c.getPA().addSkillXP(168 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need 14 copper and 14 tin ore to smelt 14 bronze bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(440, 1) && (c.playerLevel[13] >= 15)) {
						c.sendMessage("<shad=155653>You smelt an iron bar..");
						c.getItems().deleteItem1(440, 1);
						c.startAnimation(899);
						c.getItems().addItem(2351, 1);
						c.getPA().addSkillXP(13 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 15 smithing and an iron ore to smelt an iron bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15149:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(440, 10) && (c.playerLevel[13] >= 15)) {
						c.sendMessage("<shad=155653>You smelt 10 iron bars.");
						c.getItems().deleteItem1(440, 10);
						c.startAnimation(899);
						c.getItems().addItem(2351, 10);
						c.getPA().addSkillXP(130 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 15 smithing and 10 iron ore to smelt an iron bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15150:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(440, 5) && (c.playerLevel[13] >= 15)) {
						c.sendMessage("<shad=155653>You smelt 5 iron bars..");
						c.getItems().deleteItem1(440, 5);
						c.startAnimation(899);
						c.getItems().addItem(2351, 5);
						c.getPA().addSkillXP(65 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 15 smithing and 5 iron ore to smelt an iron bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15148:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(440, 28) && (c.playerLevel[13] >= 15)) {
						c.sendMessage("<shad=155653>You smelt 28 iron bars..");
						c.getItems().deleteItem1(440, 28);
						c.startAnimation(899);
						c.getItems().addItem(2351, 28);
						c.getPA().addSkillXP(364 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 15 smithing and 28 iron ore to smelt an iron bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15159:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(453, 2) && c.getItems().playerHasItem(440, 1) && (c.playerLevel[13] >= 30)) {
						c.sendMessage("<shad=155653>You smelt a steel bar..");
						c.getItems().deleteItem1(440, 1);
						c.getItems().deleteItem1(453, 2);
						c.startAnimation(899);
						c.getItems().addItem(2353, 1);
						c.getPA().addSkillXP(18 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 30 smithing an iron ore and 2 coal to smelt a steel bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15158:
					if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(453, 10) && c.getItems().playerHasItem(440, 5) && (c.playerLevel[13] >= 30)) {
						c.sendMessage("<shad=155653>You smelt 5 steel bars..");
						c.getItems().deleteItem1(440, 5);
						c.getItems().deleteItem1(453, 10);
						c.startAnimation(899);
						c.getItems().addItem(2353, 5);
						c.getPA().addSkillXP(90 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 30 smithing 5 iron ore and 10 coal to smelt 5 steel bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15157:
					if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(453, 20) && c.getItems().playerHasItem(440, 8) && (c.playerLevel[13] >= 30)) {
						c.sendMessage("<shad=155653>You smelt 10 steel bars..");
						c.getItems().deleteItem1(440, 8);
						c.getItems().deleteItem1(453, 20);
						c.startAnimation(899);
						c.getItems().addItem(2353, 10);
						c.getPA().addSkillXP(180 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 30 smithing 8 iron ore and 20 coal to smelt 10 steel bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 15156:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(453, 20) && c.getItems().playerHasItem(440, 8) && (c.playerLevel[13] >= 30)) {
						c.sendMessage("<shad=155653>You smelt 10 steel bars..");
						c.getItems().deleteItem1(440, 8);
						c.getItems().deleteItem1(453, 20);
						c.startAnimation(899);
						c.getItems().addItem(2353, 10);
						c.getPA().addSkillXP(180 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 30 smithing 8 iron ore and 20 coal to smelt 10 steel bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 29017:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(447, 1) && (c.playerLevel[13] >= 50)) {
						c.sendMessage("<shad=155653>You smelt a mithril bar..");
						c.getItems().deleteItem1(447, 1);
						c.startAnimation(899);
						c.getItems().addItem(2359, 1);
						c.getPA().addSkillXP(30 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 50 smithing and a mithril ore to smelt a mithril bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 29016:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(447, 5) && (c.playerLevel[13] >= 50)) {
						c.sendMessage("<shad=155653>You smelt 5 mithril bars..");
						c.getItems().deleteItem1(447, 5);
						c.startAnimation(899);
						c.getItems().addItem(2359, 5);
						c.getPA().addSkillXP(150 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 50 smithing and 5 mithril ore to smelt 5 mithril bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 24253:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(447, 10) && (c.playerLevel[13] >= 50)) {
						c.sendMessage("<shad=155653>You smelt 10 mithril bars..");
						c.getItems().deleteItem1(447, 10);
						c.startAnimation(899);
						c.getItems().addItem(2359, 10);
						c.getPA().addSkillXP(300 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 50 smithing and 10 mithril ore to smelt 10 mithril bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 16062:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(447, 28) && (c.playerLevel[13] >= 50)) {
						c.sendMessage("<shad=155653>You smelt 28 mithril bars..");
						c.getItems().deleteItem1(447, 28);
						c.startAnimation(899);
						c.getItems().addItem(2359, 28);
						c.getPA().addSkillXP(840 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 50 smithing and 28 mithril ore to smelt a mithril bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			case 29022:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(449, 1) && (c.playerLevel[13] >= 70)) {
						c.sendMessage("<shad=155653>You smelt an adamant bar..");
						c.getItems().deleteItem1(449, 1);
						c.startAnimation(899);
						c.getItems().addItem(2361, 1);
						c.getPA().addSkillXP(38 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 70 smithing and an adamant ore to smelt an adamant bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;

			case 29020:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(449, 5) && (c.playerLevel[13] >= 70)) {
						c.sendMessage("<shad=155653>You smelt 5 adamant bars..");
						c.getItems().deleteItem1(449, 5);
						c.startAnimation(899);
						c.getItems().addItem(2361, 5);
						c.getPA().addSkillXP(190 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 70 smithing and 5 adamant ore to smelt 5 adamant bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			case 29019:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(449, 10) && (c.playerLevel[13] >= 70)) {
						c.sendMessage("<shad=155653>You smelt 10 adamant bars..");
						c.getItems().deleteItem1(449, 10);
						c.startAnimation(899);
						c.getItems().addItem(2361, 10);
						c.getPA().addSkillXP(380 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 70 smithing and 10 adamant ore to smelt 10 adamant bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			case 29018:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(449, 28) && (c.playerLevel[13] >= 70)) {
						c.sendMessage("<shad=155653>You smelt 28 adamant bars..");
						c.getItems().deleteItem1(449, 28);
						c.startAnimation(899);
						c.getItems().addItem(2361, 28);
						c.getPA().addSkillXP(1064 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 70 smithing and 28 adamant ore to smelt 28 adamant bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			case 29026:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(451, 1) && (c.playerLevel[13] >= 85)) {
						c.sendMessage("<shad=155653>You smelt a rune bar..");
						c.getItems().deleteItem1(451, 1);
						c.startAnimation(899);
						c.getItems().addItem(2363, 1);
						c.getPA().addSkillXP(50 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 85 smithing and a rune ore to smelt a rune bar.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			
			case 29025:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(451, 5) && (c.playerLevel[13] >= 85)) {
						c.sendMessage("<shad=155653>You smelt 5 rune bars..");
						c.getItems().deleteItem1(451, 5);
						c.startAnimation(899);
						c.getItems().addItem(2363, 5);
						c.getPA().addSkillXP(250 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 85 smithing and 5 rune ore to smelt 5 rune bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
				
			case 29024:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(451, 10) && (c.playerLevel[13] >= 85)) {
						c.sendMessage("<shad=155653>You smelt 10 rune bars..");
						c.getItems().deleteItem1(451, 10);
						c.startAnimation(899);
						c.getItems().addItem(2363, 10);
						c.getPA().addSkillXP(500 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 85 smithing and 10 rune ore to smelt 10 rune bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
				
			case 29023:
				if (c.smeltInterface) {
					c.getPA().closeAllWindows();
					if (c.getItems().playerHasItem(451, 28) && (c.playerLevel[13] >= 85)) {
						c.sendMessage("<shad=155653>You smelt 28 rune bars..");
						c.getItems().deleteItem1(451, 28);
						c.startAnimation(899);
						c.getItems().addItem(2363, 28);
						c.getPA().addSkillXP(1400 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
						c.getPA().refreshSkill(c.playerSmithing);
					} else {
						c.sendMessage("You need to have atleast 85 smithing and 28 rune ore to smelt 28 rune bars.");
						c.getPA().removeAllWindows();
						c.smeltInterface = false;
					}
				} else {
					c.getPA().resetVariables();
				}
			break;
			case 108005:
			c.getPA().showInterface(19148);
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
				 if (c.dialogueAction == 2147) {
				c.team = c.team+1;
					c.sendMessage("You pledge to the Zamorakian Gods.");
				c.getPA().closeAllWindows();
				}
				if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
				}
				if(c.dialogueAction == 100) {
					c.getDH().sendDialogues(25, 946);
				}
				if (c.dialogueAction == 16) {
c.getItems().deleteItem(15084,1);
c.getItems().addItem(15098,1);
c.getPA().removeAllWindows();
}
					
			break;
			
			case 9179:
				 if (c.dialogueAction == 2147) {
				c.team = c.team+2;
					c.sendMessage("You pledge to the Saradomin Gods.");
				c.getPA().closeAllWindows();
				}
				if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(21, 946);
				}
				if(c.dialogueAction == 100) {
					c.getGamble().gambleBlackJack(c);
				}
				if (c.dialogueAction == 16) {
c.getItems().deleteItem(15084,1);
c.getItems().addItem(15092,1);
c.getPA().removeAllWindows();
}	
			break;
			
			case 9180:
				 if (c.dialogueAction == 2147) {
				c.team = c.team+3;
				c.sendMessage("You pledge to the Guthix Gods.");
				c.getPA().closeAllWindows();
				}
				if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(23, 946);
				}
				if(c.dialogueAction == 100) {
					if(!c.getItems().playerHasItem(995, 200000)) {
						c.sendMessage("You need at least 200k coins to play this game!");
						c.getPA().removeAllWindows();
						break;
					}
					c.getGamble().playGame(c);
				}
				if (c.dialogueAction == 16) {
c.getItems().deleteItem(15084,1);
c.getItems().addItem(15086,1);
c.getPA().removeAllWindows();
}
			break;
			
			case 9181:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				} else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				} else {
					c.getPA().removeAllWindows();
				}
				c.dialogueAction = 0;
				if (c.dialogueAction == 16) {
c.getItems().deleteItem(15084,1);
c.getItems().addItem(15088,1);
c.getPA().removeAllWindows();
}
				break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 18355)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient, chaotic staff or a SOL.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;

			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);		
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(3088,3933,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 50) {
					c.getPA().startTeleport(2661,3307,0,"modern");
					c.sendMessage("This is PVP");
				} else if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3007,3631,0,"modern");
				} else if (c.dialogueAction == 8) {
					c.getPA().resetBarrows();
					c.sendMessage("Your barrows killcount has been reset.");
				} else if (c.dialogueAction == 13) {
					c.getPA().spellTeleport(1762, 5180, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 186) {
                   			Server.lottery.enterLottery(c);
                   			c.getPA().removeAllWindows();
				} else if (c.dialogueAction == 202) {
					c.getPA().enterZombies();
					c.sendMessage("Good luck on your round of Nazi Zombies!~Justin");
 				} else if (c.dialogueAction == 27) {
					c.getPA().movePlayer(3210, 3424, 0);
					c.monkeyk0ed = 0;
					c.Jail = false;
		c.forcedText = "I swear to god that i will never break the rules anymore!";
			c.forcedChatUpdateRequired = true;
		c.updateRequired = true;

} else if (c.dialogueAction == 90) {
					c.getPA().startTeleport(2588,9879,0,"modern");
				c.sendMessage("Anna Jones safely treks into Bork's lair, but quickly ditches you.");
	} else if (c.dialogueAction == 91) {
					c.getShops().openShop(73);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 92) {
					c.getShops().openShop(74);
					c.dialogueAction = -1;
	} else if (c.dialogueAction == 93) {
					c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
				c.dialogueAction = -1;
} else if (c.dialogueAction == 300) {
				c.getDH().sendDialogues(42, 1972);
				} else {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				}	
				break;
			
			case 9158:
				if (c.dialogueAction == 50) {
					c.getPA().startTeleport(2559,3089,0,"modern");
					c.sendMessage("This is PVP!");
			} else if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3243,3790,0,"modern");

				} else if (c.dialogueAction == 13) {
					c.getPA().spellTeleport(3505, 9494, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 90) {
					c.forcedChat("*shiver*");
					c.startAnimation(2836);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 300) {
					c.getPA().removeAllWindows();
					c.dialogueAction = -1;
									} else if (c.dialogueAction == 34) {
					c.getPA().removeAllWindows();
					c.dialogueAction = -1;
					}

				if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
				} else {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				}
				break;
			case 9159:
				if (c.dialogueAction == 51) {
					c.getPA().startTeleport(3351,3659,0,"modern");
					}
				break;
			case 107243:
				c.setSidebarInterface(4, 1644);
				break;

			case 107215:
				c.setSidebarInterface(11, 904);
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			if(c.playerEquipment[c.playerWeapon] == 13902) {
			c.specBarId = 7486;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			} else {
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			}
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

			case 30108:
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			if(c.playerEquipment[c.playerWeapon] == 15486) {
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(1958);
				c.SolProtect = 120;
				c.startAnimation(10518);
				c.getItems().updateSpecialBar();
			c.usingSpecial = !c.usingSpecial;
			c.sendMessage("All damage will be split into half for 1 minute.");
			c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", 7562);
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}	
			}			
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			
case 153:
case 152:
c.isRunning2 = !c.isRunning2;
int frame = c.isRunning2 == true ? 1 : 0;
c.getPA().sendFrame36(173,frame);
break;
			
			case 9154:
			c.logout();
			break;
			
			case 117048:
			c.getPA().startTeleport(3087, 3498, 0, "modern");	
			break;		

			/*home teleports*/
			case 4171:
			
			case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(2767 + Misc.random(2), 2979, 0, type);	
			break;
			
			/*case 50235:
			case 4140:
			case 117112:
			//c.getPA().startTeleport(Config.LUMBY_X, Config.LUMBY_Y, 0, "modern");
			c.getDH().sendOption5("Rock Crabs", "Taverley Dungeon", "Slayer Tower", "Brimhaven Dungeon", "Hill Giants");

			c.teleAction = 9999;
			break;

			
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Pest Control", "TzHaar Cave", "Duel Arena", "Warrior Guild");
			c.teleAction = 2;
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.dialogueId = 190;
			c.getDH().sendDialogues(c.dialogueId, 0);
			c.teleAction = 3;
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Mage Bank", "Green Dragons", "Multi Pk Zones", "Edgeville", "PvP Zones");
			c.teleAction = 4;
			break;			
			
			case 51013:
			case 6004:	
			case 117162:	
			c.getPA().startTeleport(2852, 3432, 0, "modern");
			//c.getDH().sendOption5("Mining", "Smithing", "Fishing/Cooking", "Woodcutting", "Farming");
			//c.teleAction = 5;
			break; 
			
			
			case 51023:
			case 6005:
						c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Falador", "Canifis");
			c.teleAction = 20;
			break;*/ 
			
			
			case 51031:
			case 29031:
			c.getPA().startTeleport(3045, 5105, 1, "modern");	
			break; 		

			case 72038:
			case 51039:
			c.getPA().startTeleport(2882, 5310, 2, "modern");	
			break;
			
    /*Attack*/
case 9125: //Accurate
    case 22230://punch
    case 48010://flick (whip)
    case 14218://pound (mace)
    case 33020://jab (halberd)
    case 21200: //spike (pickaxe)
    case 6168: //chop (axe)
    case 8234: //stab (dagger)
    case 17102: //accurate (darts)
    case 6236: //accurate (long bow)
    case 1080: //bash (staff)
    case 6221: // range accurate
    case 30088: //claws (chop)
    case 1177: //hammer (pound)
    c.fightMode = 0;
    if (c.autocasting)
        c.getPA().resetAutocast();
    break;
			
    /*Defence*/
case 9126: //Defensive
    case 22228: //block (unarmed)
    case 48008: //deflect (whip)
    case 1175: //block (hammer)
    case 21201: //block (pickaxe)
    case 14219: //block (mace)
    case 1078: //focus - block (staff)
    case 33018: //fend (hally)
    case 6169: //block (axe)
    case 8235: //block (dagger)
    case 18078: //block (spear)
    case 30089: //block (claws)
    c.fightMode = 1;
    if (c.autocasting)
        c.getPA().resetAutocast();
    break;
    /*All*/
case 9127: // Controlled
    case 14220: //Spike (mace)
    case 6234: //longrange (long bow)
    case 6219: //longrange
    case 18077: //lunge (spear)
    case 18080: //swipe (spear)
    case 18079: //pound (spear)
    case 17100: //longrange (darts)
    c.fightMode = 3;
    if (c.autocasting)
        c.getPA().resetAutocast();
    break;
    /*Strength*/
case 9128: //Aggressive
    case 14221: //Pummel(mace)
    case 33019: //Swipe(Halberd)
    case 21203: //impale (pickaxe)
    case 21202: //smash (pickaxe)
    case 6171: //hack (axe)
    case 6170: //smash (axe)
    case 6220: // range rapid
    case 8236: //slash (dagger)
    case 8237: //lunge (dagger)
    case 30090: //claws (lunge)
    case 30091: //claws (Slash)
    case 1176: //stat hammer
    case 22229: //block (unarmed)
    case 1079: //pound (staff)
    case 6235: //rapid (long bow)
    case 17101: //repid (darts)
    c.fightMode = 2;
    if (c.autocasting)
        c.getPA().resetAutocast();
    break;

			/**Prayers**/
			case 87231: // thick skin
			if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}
			c.getCurse().activateCurse(0);
			break;	
			case 87233: // burst of str
			c.getCurse().activateCurse(1);
			break;	
			case 87235: // charity of thought
			c.getCurse().activateCurse(2);
			break;	
			case 87237: // range
			c.getCurse().activateCurse(3);
			break;
			case 87239: // mage
			c.getCurse().activateCurse(4);
			break;
case 87241: // berserker
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(5);
			break;
			case 87243: // super human
			c.getCurse().activateCurse(6);
			break;
			case 87245:	// improved reflexes
			c.getCurse().activateCurse(7);
			break;
			case 87247: //hawk eye
			c.getCurse().activateCurse(8);
			break;
			case 87249:
			c.getCurse().activateCurse(9);
			break;
			case 87251: // protect Item
			c.getCurse().activateCurse(10);
			break;			
			case 87253: // 26 range
			c.getCurse().activateCurse(11);
			break;
			case 87255: // 27 mage
			c.getCurse().activateCurse(12);
			break;	
			case 88001: // steel skin
			c.getCurse().activateCurse(13);
			break;
			case 88003: // ultimate str
			c.getCurse().activateCurse(14);
			break;
			case 88005: // incredible reflex
			c.getCurse().activateCurse(15);
			break;	
			case 88007: // protect from magic
			c.getCurse().activateCurse(16);
			break;					
			case 88009: // protect from range
			c.getCurse().activateCurse(17);
			break;
			case 88011: // protect from melee
			c.getCurse().activateCurse(18);
			break;
			case 88013: // 44 range
			c.getCurse().activateCurse(19);
			break;	
			/**End of curse prayers**/
			
			
			/**Prayers**/
			case 97168: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 97170: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 97172: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 97174: // range
			c.getCombat().activatePrayer(3);
			break;
			case 97176: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 97178: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 97180: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 97182:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 97184: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 97186:
			c.getCombat().activatePrayer(9);
			break;
			case 97188: // protect Item
			/*if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}*/
			c.getCombat().activatePrayer(10);
			break;			
			case 97190: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 97192: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 97194: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 97196: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 97198: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 97200: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 97202: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 97204: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 97206: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 97208: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 97210: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 97212: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 97214: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 97216: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 97218: // piety
			c.getCombat().activatePrayer(25);
			break;

					
			case 13092:
                        if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
                         if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						c.sendMessage("Trade accepted.");
						c.SaveGame();
						ot1.SaveGame();
						ot1.sendMessage("Trade accepted.");
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;			
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 67100://Accept
					c.getPA().showInterface(3559);
					c.newPlayer = false;
					c.sendMessage("Would you like to keep playing, or would you like to leave?");
				break;
			case 67103://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 100231:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 100237:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);		c.stopMovement();
            break;
            case 169:
                c.startAnimation(856);		c.stopMovement();
            break;
            case 162:
                c.startAnimation(857);		c.stopMovement();
            break;
            case 164:
                c.startAnimation(858);		c.stopMovement();
            break;
            case 165:
                c.startAnimation(859);		c.stopMovement();
            break;
            case 161:
                c.startAnimation(860);		c.stopMovement();
            break;
            case 170:
                c.startAnimation(861);		c.stopMovement();
            break;
            case 171:
                c.startAnimation(862);		c.stopMovement();
            break;
            case 163:
                c.startAnimation(863);		c.stopMovement();
            break;
            case 167:
                c.startAnimation(864);		c.stopMovement();
            break;
            case 172:
                c.startAnimation(865);		c.stopMovement();
            break;
            case 166:
                c.startAnimation(866);		c.stopMovement();
            break;
            case 52050:
                c.startAnimation(2105);		c.stopMovement();
            break;
            case 52051:
                c.startAnimation(2106);		c.stopMovement();
            break;
            case 52052:
                c.startAnimation(2107);		c.stopMovement();
            break;
            case 52053:
                c.startAnimation(2108);		c.stopMovement();
            break;
            case 52054:
                c.startAnimation(2109);		c.stopMovement();
            break;
            case 52055:
                c.startAnimation(2110);		c.stopMovement();
            break;
            case 52056:
                c.startAnimation(2111);		c.stopMovement();
            break;
            case 52057:
                c.startAnimation(2112);		c.stopMovement();
            break;
            case 52058:
                c.startAnimation(2113);		c.stopMovement();
            break;
            case 43092:
                c.startAnimation(0x558);		c.stopMovement();
				c.gfx0(574);
            break;
            case 2155:
                c.startAnimation(11044);		c.stopMovement();
				c.gfx0(1973);
            break;
            case 25103:
                c.startAnimation(10530);		c.stopMovement();
				c.gfx0(1864);
            break;
            case 25106:
				c.startAnimation(8770);
				c.gfx0(1553);		c.stopMovement();
            break;
            case 2154:
                c.startAnimation(7531);		c.stopMovement();
            break;
            case 52071:
                c.startAnimation(0x84F);		c.stopMovement();
            break;
            case 52072:
                c.startAnimation(0x850);		c.stopMovement();
            break;
            case 73003:
		c.startAnimation(6111);	c.stopMovement();
            break;
            case 73001:
                c.startAnimation(3544);		c.stopMovement();
            break;
            case 73000:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");
return;
}
                c.startAnimation(3543);		c.stopMovement();
            break;
			case 72032:
				c.startAnimation(9990);		c.stopMovement();
				c.gfx0(1734);
            break;
			case 72033:
				c.startAnimation(4278);		c.stopMovement();
            break;
			case 59062:
				c.startAnimation(4280);		c.stopMovement();
            break;
			case 72254:
				c.startAnimation(4275);		c.stopMovement();
            break;
			case 73004:
				c.startAnimation(7272);		c.stopMovement();
				c.gfx0(1244);
            break;
			case 72255:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");		c.stopMovement();
return;
}
				c.startAnimation(2414);
				c.gfx0(1537);
			break;
			/* END OF EMOTES */
			case 28166:
				
				break;
case 118098:
c.getPA().castVeng();
break;

		 /* Dungeoneering Start.
 		 *
		 */
		case 70132:
		if (c.dungPoints >= 1000) {
		c.dungPoints -= 1000;
		c.sendMessage("You buy a Ring of Vigour!");
		c.getItems().addItem(19669, 1); 
		} else {
		c.sendMessage("You don't have enough Dungeoneering points!");
		}
		break;
		case 70133:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18359, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70148:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18363, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70147:
		if(c.dungPoints >= 2300) {
		c.dungPoints -= 2300;
		c.getItems().addItem(18361, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70138:
		if(c.dungPoints >= 50) {
		c.dungPoints -= 50;
		c.getItems().addItem(4447, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70134:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13354, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70135:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13352, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70136:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13346, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70137:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13348, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70139:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13350, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70140:
		if(c.dungPoints >= 2500) {
		c.dungPoints -= 2500;
		c.getItems().addItem(13355, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70144:
		if(c.dungPoints >= 1200) {
		c.dungPoints -= 1200;
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70143:
		if(c.dungPoints >= 1000) {
		c.dungPoints -= 1000;
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70142:
		if(c.dungPoints >= 900) {
		c.dungPoints -= 900;
		c.getItems().addItem(4753, 1);
		c.getItems().addItem(4755, 1);
		c.getItems().addItem(4757, 1);
		c.getItems().addItem(4759, 1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70145:
		if(c.dungPoints >= 800) {
		c.dungPoints -= 800;
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 70141:
		if(c.dungPoints >= 900) {
		c.dungPoints -= 900;
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		} else {
		c.sendMessage("You do not have enough dungeoneering points");
		}
		break;
		case 66156:
		if(c.playerLevel[6] <= 9) {
		c.sendMessage("You must be 10+ Magic To Choose Magic Class");
		} else {
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(15786, 1);
		c.getItems().addItem(15797, 1);
		c.getItems().addItem(15837, 1);
		c.getItems().addItem(15892, 1);
		c.getItems().addItem(16185, 1);
		c.getItems().addItem(16153, 1);
		c.getItems().addItem(391, 3);
		c.getItems().addItem(995, 2000000);
		c.getItems().addItem(554, 50000);
		c.getItems().addItem(555, 50000);
		c.getItems().addItem(556, 50000);
		c.getItems().addItem(557, 50000);
		c.getItems().addItem(558, 50000);
		c.getItems().addItem(559, 50000);
		c.getItems().addItem(560, 50000);
		c.getItems().addItem(561, 50000);
		c.getItems().addItem(562, 50000);
		c.getItems().addItem(563, 50000);
		c.getItems().addItem(565, 50000);
		c.getItems().addItem(564, 50000);
		c.getItems().addItem(566, 50000);
		c.playerMagicBook = 1;
		c.setSidebarInterface(6, 12855);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Mage equipment and 2M.");
		}
	}
		break;
		case 66157:
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		                                c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(15808, 1);
		c.getItems().addItem(15914, 1);
		c.getItems().addItem(15925, 1);
		c.getItems().addItem(15936, 1);
		c.getItems().addItem(16013, 1);
		c.getItems().addItem(16035, 1);
		c.getItems().addItem(16127, 1);
		c.getItems().addItem(16262, 1);
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(391, 3);
		c.getItems().addItem(995, 2000000);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Melee equipment and 2M.");
		}
	
		break;
		case 66158:
		if(c.playerLevel[4] <= 9) {
		c.sendMessage("You must be 10+ Ranged To Choose Ranged Class");
		} else {
		if (c.dungRest > 1) {
				c.sendMessage("You must wait 3 Minutes before using this again!");
				return;
		} else {
		                                c.dungRest = 180; //180 = 3 Minutes
		c.getItems().addItem(16002, 1);
		c.getItems().addItem(16046, 1);
		c.getItems().addItem(16057, 1);
		c.getItems().addItem(16068, 1);
		c.getItems().addItem(16105, 1);
		c.getItems().addItem(19893, 1);
		c.getItems().addItem(19892, 1);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(892, 10000);
		c.getItems().addItem(397, 3);
		c.getItems().addItem(995, 2000000);
		c.getPA().closeAllWindows();
		c.sendMessage("You have received Ranged equipment and 2M.");
		}
	}
		break;
		//Dungeoneering finish
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
