package server.model.players;

import server.Config;
import server.Server;
import server.model.objects.Object;
import server.util.Misc;
import server.util.ScriptManager;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import server.model.objects.Doors;
import server.model.objects.DoubleDoors;

public class ActionHandler {
	private Client c;
	
			int[] donatorRitem = {15422, 15423, 15425, 15441, 15442, 15443, 15444, 18365, 18367, 18369, 18830, 19308, 19311, 19314, 19317, 19320, 19370, 19323, 19372, 19368, 19334, 15018, 15019, 15020, 19354, 15220, 15241, 15259, 13346, 13348, 13350, 13352, 13354, 13355, 13360, 13358, 13362, 15332, 13336, 13370, 13340, 13342, 13344, 20070, 20072, 592, 4718, 4720, 4712, 4714, 4724, 4734, 4736, 4738, 4749, 4753, 4757, 4759, 14484, 13899, 13902, 13742, 13740, 13738, 11728, 11724, 11722, 11720, 11720, 11718, 11708, 11706, 11704, 11702, 11700};
			int[] strangeRitem = {11283, 18830, 17017, 15486, 19708, 13672, 13673, 13674, 13675, 15399, 7447, 7445, 7433, 7449, 5608};
			int[] minigameRitem = {15441, 4151, 19161, 19160, 19163, 19473, 19474, 19815, 15443, 13734, 13736, 13738, 13740, 13742, 13744, 4251, 3481, 3483, 3485, 3488, 3486, 9471, 19780, 11730};
	
	public int donatorRitem() {
			return donatorRitem[(int) (Math.random() * donatorRitem.length)];
	}
	public int strangeRitem() {
			return strangeRitem[(int) (Math.random() * strangeRitem.length)];
	}
	public int minigameRitem() {
			return minigameRitem[(int) (Math.random() * minigameRitem.length)];
	}
	public ActionHandler(Client Client) {
		this.c = Client;
	}
	public static int getBakeryLength() {
		return BakeryStall.length;
	}
	
	public static int getGemLength() {
		return GemStall.length;
	}

	public static int getFurLength() {
		return FurStall.length;
	}

	public static int getSpiceLength() {
		return SpiceStall.length;
	}

	public static int getSilverLength() {
		return SilverStall.length;
	}
	public static int getFishLength() {
		return FishStall.length;
	}
	public static int getFish2Length() {
		return FishStall2.length;
	}
	public static int getTeaLength() {
		return TeaStall.length;
	}
	public static int getVegLength() {
		return VegStall.length;
	}
	public static int getVeg2Length() {
		return VegStall2.length;
	}
	public static int getSilkLength() {
		return SilkStall.length;
	}
	public static final int[] BakeryStall = {2309, 1901, 1891};
	public static final int[] FishStall2 = {378,384,372,7945};
	public static final int[] FishStall = {378,318,336,372,7945};
	public static final int[] TeaStall = {712};
	public static final int[] VegStall2 = {1943,1983,985,987};
	public static final int[] VegStall = {1943,1983,1966,1551};
	public static final int[] SilkStall = {950};
	public static final int[] GemStall = {1623, 1621, 1617, 1619};
	public static final int[] FurStall = {948, 958};
	public static final int[] SpiceStall = {2007, 7480, 7481, 7482, 7483, 7484, 7485, 7486, 7487, 7488, 7489, 7490, 7491, 7492, 7493, 7494, 7495,7496};
	public static final int[] SilverStall = {1798, 1800,1806};
		int[] PvpItems = {14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886, 14888, 14889, 14890, 14891, 14892 };
	int[] PvpPrices = { 10000000, 1000000, 500000, 35000, 800000,150000, 280000, 840000, 150000, 125000, 80000, 5000000, 240000, 108700, 200000, 284000 };
	public int[] warriors = {10247,10248,10249,10250};
	public void firstClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		switch(objectType) {
		
		case 4780:
			c.getPA().movePlayer(2479, 10147, 0);
		break;
		case 2591:
			c.getPA().movePlayer(2044, 4649, 0);
		break;
		case 4965:
			c.getPA().movePlayer(2999, 5021, 1);
		break;
		case 2470:
		if (c.playerLevel[c.playerSlayer] >= 80) {
			c.getPA().movePlayer(3277, 3029, 0);
		} else {
		c.sendMessage("You need a Slayer level of 80+ to enter the Slayer Farm!");
		}
		break;
		case 5250:
			c.getPA().movePlayer(1990, 4663, 0);
		break;
		case 4646:
			c.getPA().movePlayer(3549, 9865, 0);
		break;
		case 1765:
			c.getPA().movePlayer(2271, 4680, 0);
		break;
case 3192:
        c.highscores();
        break;
		
/*Thieving*/
			case 2560:
			c.getThieving().stealFromStall(SilkStall[Misc.random(getSilkLength() -1)], 40, 35, objectType, obX, obY);
			break;
			case 2561:
			c.getThieving().stealFromStall(BakeryStall[Misc.random(getBakeryLength() - 1)], 10, 1, objectType, obX, obY);
			break;
			case 2562:
				c.getThieving().stealFromStall(GemStall[Misc.random(getGemLength() - 1)], 30, 25, objectType, obX, obY);
			break;
			case 2563:
				c.getThieving().stealFromStall(FurStall[Misc.random(getFurLength() - 1)], 60, 50, objectType, obX, obY);
			break;
			case 2564:
				c.getThieving().stealFromStall(SpiceStall[Misc.random(getSpiceLength() - 1)], 100, 75, objectType, obX, obY);
			break;
			case 2565:
				c.getThieving().stealFromStall(SilverStall[Misc.random(getSilverLength() - 1)], 170, 90, objectType, obX, obY);
			break;
/*Thieving end*/


		case 2213: //banks
		case 14367:
		case 11758:
		case 3193:
		case 2995:
			c.getPA().openUpBank();
		break;
		
		/*case 9391://tzhaar viewing orb
                c.setSidebarInterface(10, 3209);
                c.outStream.createFrame(106); // Writes the frame 106 out.
                c.outStream.writeByteC(10); // Tells client to switch to the magic interface
                break;*/

		case 1738:
		c.getPA().movePlayer(2840, 3539, 2);
		break;

			case 26288:
			case 26287:
			case 26286:
			case 26289:
			
				if(c.gwdelay > 1) {
				c.sendMessage("You can only do this once every 5 minute!");
				return;
				}	
		if(c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
				c.startAnimation(645);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.sendMessage("You recharge your prayer points.");
				c.getPA().refreshSkill(5);
				c.gwdelay = 600;
			} else {
				c.sendMessage("You already have full prayer points.");
			}
 
			break;
		
		case 1:
		c.sendMessage("You found a butterfly Net!");
		c.getItems().addItem(10010, 1);
		break;
		/*case 9398://deposit
	c.getPA().sendFrame126("The Bank of Exilium - Deposit Box", 7421);
	c.getPA().sendFrame248(4465, 197);//197 just because you can't see it =\
	c.getItems().resetItems(7423);
break;*/
		case 26384:
	if (c.getItems().playerHasItem(2347, 1)) {
	c.startAnimation(7002);
	c.getPA().movePlayer(2849, 5333, 2);
	} else if (c.absX == 2850 && c.absY == 5333) {
	c.getPA().movePlayer(2851, 5333, 2);
	} else {
	c.sendMessage("You need a hammer to enter the chamber.");
				}
			break;

case 13405:
if (c.objectX == 2843){
    c.getPA().showInterface(31330);
} else {
    c.getPA().startTeleport2(2844, 10209, 0);
    c.sendMessage("You teleported back to home area.");
    }   
     
break;
case 11214:
c.getPA().showInterface(31250);
break;
		
		case 2286:
		c.getPA().movePlayer(2595, 4778, 0);
		c.getItems().addItem(995, 2000);
		c.getPA().addSkillXP(125*c.playerLevel[16], c.playerAgility);
		c.getPA().refreshSkill(c.playerAgility);
		break;
		
		case 8972:
		if((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
		c.sendMessage("You need 90 Agility And 90 Hunter to enter this Area");
		} else {
		if((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
		c.sendMessage("You need 90 Agility to enter this Area");
		} else {
		if((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
		c.sendMessage("You need 90 Hunter to enter this Area");
		} else {
		if((c.playerLevel[21] > 89) && (c.playerLevel[16] >89)) {
		c.getPA().movePlayer(2515, 4632, 0);
		c.sendMessage("A sense of nervousness fills your body..");
		c.sendMessage("you find yourself in a mystery cave!");
		}
		}
		}
		}
		

		break;
case 927:
			if (c.playerLevel[c.playerFishing] < 25) {
				c.sendMessage("You need a fishing level of 25 to fish here.");
				return;
			}
			if (!c.getItems().playerHasItem(307, 1)) {
				c.sendMessage("You must use a fishing rod here.");
				return;
			}
			if (!c.getItems().playerHasItem(314, 1)) {
				c.sendMessage("You need feathers to use as bait.");
				return;
			}
			if (c.getItems().playerHasItem(10136, 1)) {
				c.sendMessage("You already have a rainbow fish.");
				return;
			}
			if (c.getItems().freeSlots() < 1) {
				c.sendMessage("Not enough space in inventory.");
				return;
			}
			c.startAnimation(622);
			final Client c1 = this.c;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer e) {
					c1.getItems().deleteItem2(314, 1);
					c1.getItems().addItem(10136, 1);
					c1.sendMessage("You catch a Rainbow fish.");
					c1.getPA().addSkillXP(220, c.playerFishing);
					c1.getPA().refreshSkill(c.playerFishing);
					e.stop();
				}
			}, 8000);
			break;
		
case 4150:
		c.getPA().movePlayer(2606, 3154, 0);
		c.sendMessage("Welcome to Funpk!");
		break;
case 2471:
		c.getPA().movePlayer(3253, 9517, 2);
		c.sendMessage("Welcome to PK box");
		break;
				case 4151:
		c.getPA().movePlayer(3089, 3489, 0);
		c.sendMessage("You return home unharmed.");
		break;
		
		case 8987:
		c.getPA().movePlayer(3086, 3493, 0);
		break;
		
		case 6456:
		c.getPA().movePlayer(2837, 3806, 0);
		break;
// start of the right side ice minigame. Made by "Toxic Melee"
        case 7272:
        if (c.objectX == 2855 && c.objectY == 3810){ //portal
        c.getPA().movePlayer(2851, 3809, 2);        
        }
        break;
        case 6455:
        if (c.objectX == 2850 && c.objectY == 3810){ //1
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2848 && c.objectY == 3810){ //2
        c.getPA().movePlayer(2847, 3810, 2);
        }
        if (c.objectX == 2846 && c.objectY == 3810){ //3 
        c.getPA().movePlayer(2845, 3810, 2);
        }
        if (c.objectX == 2844 && c.objectY == 3810){ //4
        c.getPA().movePlayer(2843, 3810, 2);
        }
        if (c.objectX == 2842 && c.objectY == 3810){ //5
        c.getPA().movePlayer(2837, 3803, 1);
        }
        if (c.objectX == 2840 && c.objectY == 3810){ //6
        c.getPA().movePlayer(2839, 3810, 2);
        }
        if (c.objectX == 2838 && c.objectY == 3810){ //7
        c.getPA().movePlayer(2837, 3810, 2);
        }
        if (c.objectX == 2836 && c.objectY == 3810){ //8
        c.getPA().movePlayer(2837, 3803, 1);
        }
        if (c.objectX == 2834 && c.objectY == 3810){ //9
        c.getPA().movePlayer(2837, 3803, 1);
        }
        if (c.objectX == 2832 && c.objectY == 3810){ //10
        c.getPA().movePlayer(2831, 3810, 2);
        }
        if (c.objectX == 2830 && c.objectY == 3810){ //11
        c.getPA().movePlayer(2829, 3810, 2);
        }
        if (c.objectX == 2828 && c.objectY == 3810){ //12
        c.getPA().movePlayer(2837, 3803, 1);
        }
        // start of ice minigame objects left side. Made by "Toxic Melee"
        if (c.objectX == 2850 && c.objectY == 3809){ //1
        c.getPA().movePlayer(2849, 3809, 2);        
        }
        if (c.objectX == 2848 && c.objectY == 3809){ //2
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2846 && c.objectY == 3809){ //3
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2844 && c.objectY == 3809){ //4
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2842 && c.objectY == 3809){ //5
        c.getPA().movePlayer(2841, 3809, 2);        
        }
        if (c.objectX == 2840 && c.objectY == 3809){ //6
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2838 && c.objectY == 3809){ //7
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2836 && c.objectY == 3809){ //8
        c.getPA().movePlayer(2835, 3809, 2);        
        }
        if (c.objectX == 2834 && c.objectY == 3809){ //9
        c.getPA().movePlayer(2833, 3809, 2);        
        }
        if (c.objectX == 2832 && c.objectY == 3809){ //10
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2830 && c.objectY == 3809){ //11
        c.getPA().movePlayer(2837, 3803, 1);        
        }
        if (c.objectX == 2828 && c.objectY == 3809){ //12
        c.getPA().movePlayer(2827, 3809, 2);        
        }
        break;
        // start of ice minigames chests... Made by "Toxic Melee"
        case 13292:
        if (c.objectX == 2824 && c.objectY == 3808){ //far left        
        c.getPA().movePlayer(3094, 3469, 0);
        }
        if (c.objectX == 2824 && c.objectY == 3809){ //middle left    
        c.getPA().movePlayer(3094, 3469, 0);
        }
        if (c.objectX == 2824 && c.objectY == 3810){ //middle right    
        c.getPA().movePlayer(3094, 3469, 0);
        if(System.currentTimeMillis() - c.Delay > 2000) 
            {
                c.getItems().addItem(strangeRitem(),Misc.random(1));
                c.Delay = System.currentTimeMillis();
            } else {
                    c.sendMessage("You only get one treasure!");
                }
            break;
        }
        // End of Ice minigame! Made by "Toxic Melee"

	case 2469:
		c.getPA().movePlayer(1762, 5180, 0);
		break;
		
		case 6461:
		c.getPA().movePlayer(2851, 3809, 2);
		break;
		
		case 13623:
		c.getPA().movePlayer(2837, 3806, 0);
		c.sendMessage("Multi Zone Is Working!");
		break;
		
		case 15638:
		c.getPA().movePlayer(2841, 3538, 0);
		break;
				case 411:
			if(c.altarPrayed == 0) {
				c.altarPrayed = 1;
				c.setSidebarInterface(5, 22500);
				c.startAnimation(645);
				c.sendMessage("You sense a surge of power flow through your body!");
				c.getCombat().resetPrayers();
			} else {
				c.altarPrayed = 0;
				c.setSidebarInterface(5, 5608);
				c.startAnimation(645);
				c.sendMessage("You sense a surge of purity flow through your body!");
 				c.getCurse().resetCurse();
			}
		break;

		case 13619:
		c.getPA().movePlayer(2717, 9801, 4);
		c.sendMessage("You teleported to tormented demons donator only NPC's!");
		c.sendMessage("You'll only be able to see Donators here, Sorta like world 2...");
		break;
		case 6452:
			if (c.absX == 3304 && c.absY == 9376) {
		c.getPA().movePlayer(3305, 9376, 4);
		c.sendMessage("Get ready to fight the Corporeal Beast!");
		c.sendMessage("Note: It has 3 waves on it's hp bar!");
				} else {
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		        c.getPA().movePlayer(3304, 9376, 0);
				}
		break;
		case 6451:
			if (c.absX == 3304 && c.absY == 9375) {
		c.getPA().movePlayer(3305, 9375, 4);
		c.sendMessage("Prepare for the strongest monster in the game!");
		c.sendMessage("Note: It has 3 waves on it's hp bar!");
				} else {
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		        c.getPA().movePlayer(3304, 9375, 0);
				}
		break;
		case 13625:
		c.getPA().movePlayer(2975, 9515, 1);
		c.sendMessage("You teleported to Barrelchest Non-donators");
		c.sendMessage("The Donators portal to barrelchest is 3 barrelchest bosses spawns!");
		break;
		case 13289:
        	c.getPA().movePlayer(2769, 2979, 0);
        	if(System.currentTimeMillis() - c.Delay > 2000) 
           	{
                c.getItems().addItem(minigameRitem(),Misc.random(1));
                c.Delay = System.currentTimeMillis();
            } else {
                    c.sendMessage("You only get one treasure!");
                }
            break;
		case 13626:
		c.getPA().movePlayer(1993, 4646, 0);
		c.sendMessage("You enter the portal and arrive at the Dungeoneering dungeon.");
		c.sendMessage("Bal'lak is very dangerous, so it is recommended to safespot him.");
		break;
		case 13627:
		c.getPA().movePlayer(3176, 9758, 0);
		c.sendMessage("You enter the portal and arrive at the Rammernaut bosses.");
		c.sendMessage("Rammernaut is very dangerous, bring a team!");
		break;
		case 2466:
                if (c.getItems().playerHasItem(18202, 1)) {
		c.getItems().deleteItem(18202, 1);
                c.getPA().startTeleport(2962, 9630, 0, "modern");
		c.sendMessage("You enter the portal and arrive at: Level 2.");
		c.sendMessage("Kill monsters for a key, and use it on the portal.");
		} else {
		c.sendMessage("You need the right key to enter the portal!");
		}
		break;
		case 2467:
                if (c.getItems().playerHasItem(18218, 1)) {
		c.getItems().deleteItem(18218, 1);
                c.getPA().startTeleport(2464, 4782, 0, "modern");
		c.sendMessage("You enter the portal and arrive at: Level 3.");
		c.sendMessage("Kill the boss for a key, and use it on the portal.");
		} else {
		c.sendMessage("You need the right key to enter the portal!");
		}
		break;
	case 2468:
                 if (c.getItems().playerHasItem(18314, 1)) {
		c.getItems().deleteItem(18314, 1);
                c.getPA().startTeleport(2098, 4427, 0, "modern");
		c.sendMessage("Congratulations, you have finished the minigame!");
		c.sendMessage("Click on the chest to receive your reward.");
		} else {
		c.sendMessage("You need the right key to enter the portal!");
		}
		break;
		case 13628:
		c.getPA().movePlayer(2345, 3694, 0);
		c.sendMessage("You enter the portal and arrive the Sea Troll Queen.");
		c.sendMessage("Be careful, she freezes!");
		break;
		case 13629:
		if (c.isDonator == 1) {
		c.getPA().movePlayer(2738, 5091, 0);
		c.sendMessage("You enter the portal and arrive at the lair of Nomad.");
		c.sendMessage("Bring a team, he uses many different attacks!");
		} else {
		c.sendMessage("You must be a donator to enter Nomad's lair.");
		}
		break;
		case 2465:
		c.getPA().movePlayer(2872, 5269, 2);
		c.sendMessage("You enter the portal and arrive the Armadyl chamber.");
		c.sendMessage("You can only use ranged here!");
		break;
		case 3634:
		c.getPA().startTeleport(2987, 9631, 0, "modern");
		c.sendMessage("You touch the shrine and arrive at: Level 1.");
		c.sendMessage("Kill monsters for a key, and use it on the portal.");
		break;
		case 13617:
		c.getPA().movePlayer(2975, 9515, 5);
		c.sendMessage("You teleported to Barrelchest Donators");
		c.sendMessage("You will only see Donators here and 3 bosses!!");
		break;
		case 13620:
		c.getPA().movePlayer(2721, 9450, 4);
		c.sendMessage("You teleported to steel/iron donator only NPC's!");
		c.sendMessage("You'll only be able to see Donators here, this makes it alot easier to train.");
		break;
		case 13615:
		c.getPA().movePlayer(3115, 9838, 4);
		c.sendMessage("You teleported to Hill Giants donator only NPC's!");
		c.sendMessage("You'll only be able to see Donators here, this makes it alot easier to train.");
		break;
		case 13621:
		c.getPA().movePlayer(2855,9636, 0);
		c.sendMessage("You arrive at the chamber of To'Kash the Bloodchiller.");
		c.sendMessage("Note: a team is recommended.");
		break;
		case 15644:
			if (c.objectX == 2855) {
			if (c.absX == 2855 && c.absY == 3546) {
		        c.getPA().movePlayer(2855, 3545, 0);
				} else {
		        c.getPA().movePlayer(2855, 3546, 0);
				}
				}
			break;
		case 15641:
			if (c.objectX == 2854) {
			if (c.absX == 2854 && c.absY == 3546) {
		        c.getPA().movePlayer(2854, 3545, 0);
				} else {
		        c.getPA().movePlayer(2854, 3546, 0);
				}
			if (c.absX == 2847 && c.absY == 3540) {
		        c.getPA().movePlayer(2854, 3546, 0);
				}
				}
			if (c.objectY == 3540) {
			if (c.absX == 2846 && c.absY == 3540) {
				//c.getWarriorsGuild().handleKamfreena(c, true);
				//c.UsedTimer = true;
				}
			if (c.absX == 2847 && c.absY == 3540) {
				//c.getPA().movePlayer(2846, 3540, 2);
				//c.inCyclops = false;
				//c.kamfreenaDone = false;
				//c.UsedTimer = false;
				}
				}
			break;
		case 2882:
		case 2883:
			if (c.objectX == 3268) {
				if (c.absX < c.objectX) {
					c.getPA().walkTo(1,0);
				} else {
					c.getPA().walkTo(-1,0);
				}
			}
		break;
		case 272:
			c.getPA().movePlayer(c.absX, c.absY, 1);
		break;
		
		case 273:
			c.getPA().movePlayer(c.absX, c.absY, 0);
		break;

		case 60:
		        c.getPA().movePlayer(3086, 3493, 0);
		break;
		case 26428:
		      if (c.Zammy < 15 && c.absX == 2925 && c.absY == 5332) {
		       c.sendMessage("You need atleast 15 Zamorak KC to enter here!");
		       return;
		       }	
		       if(c.absX == 2925 && c.absY == 5332) {
		        c.getPA().movePlayer(2925, 5331, 6);
		       c.Zammy -= 15;
		       c.sendMessage("A magical force reseted your Zamorak kill count!");
		      }
		        if(c.absX == 2925 && c.absY == 5331) {
		        c.getPA().movePlayer(2925, 5332, 2);
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		      }
		break;
		case 26425:
		      if (c.Band < 15 && c.absX == 2863 && c.absY == 5354) {
		       c.sendMessage("You need atleast 15 Bandos KC to enter here!");
		       return;
		       }	
		      if(c.absX == 2863 && c.absY == 5354) {
	     	    	 c.getPA().movePlayer(2864, 5354, 6);
		       c.Band -= 15;
		       c.sendMessage("A magical force reseted your Bandos kill count!");
		      }
		       if(c.absX == 2864 && c.absY == 5354) {
	     	      c.getPA().movePlayer(2863, 5354, 2);
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		      }
		break;
		case 26303:
		      c.getPA().movePlayer(2872, 5269, 2);
		break;
		case 26426:
		      if (c.Arma < 15 && c.absX == 2839 && c.absY == 5295) {
		       c.sendMessage("You need atleast 15 Armadyl KC to enter here!");
		       return;
		       }		       
		       if(c.absX == 2839 && c.absY == 5295) {
		        c.getPA().movePlayer(2839, 5296, 6);
		       c.Arma -= 15;
		       c.sendMessage("A magical force reseted your Armadyl kill count!");
		      }
		       if(c.absX == 2839 && c.absY == 5296) {
		        c.getPA().movePlayer(2839, 5295, 2);
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		      }
		break;
		case 26427:
		      if (c.Sara < 15 && c.absX == 2908 && c.absY == 5265) {
		       c.sendMessage("You need atleast 15 Saradomin KC to enter here!");
		       return;
		       }	
		       if(c.absX == 2908 && c.absY == 5265) {
		       c.Sara -= 15;
		       c.sendMessage("A magical force reseted your Saradomin kill count!");
		        c.getPA().movePlayer(2907, 5265, 4);
		      }
		       if(c.absX == 2907 && c.absY == 5265) {
		        c.getPA().movePlayer(2908, 5265, 0);
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
		      }
		break;
			case 2403:
			if (c.Culin == true) {
				c.getShops().openShop(65);
			return;
			}
			if (c.Agrith == true && c.Flambeed == false) {
				c.getShops().openShop(61);
		return;
			} 
		if(c.Flambeed == true && c.Karamel == false) {
				c.getShops().openShop(62);
		return;
			} 
		if(c.Karamel == true && c.Dessourt == false) {
				c.getShops().openShop(63);
		return;
			} 
		if(c.Dessourt == true && c.Culin == false) {
				c.getShops().openShop(64);
			return;
			} 
			if (c.Agrith == false) {
				c.getShops().openShop(60);
	}
			break;
		case 245:
			c.getPA().movePlayer(c.absX, c.absY + 2, 2);
		break;
		case 26293:
			c.getPA().startTeleport(3086, 3493, 0, "modern");
		break;
		case 246:
			c.getPA().movePlayer(c.absX, c.absY - 2, 1);
		break;
		case 1766:
			c.getPA().movePlayer(3016, 3849, 0);
		break;
			case 410:
			if (c.playerMagicBook == 0) {
				if(c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
				c.setSidebarInterface(0, 328);
				}
				c.playerMagicBook = 2;
				c.setSidebarInterface(6, 16640);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
			} else {
				if(c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
				c.setSidebarInterface(0, 328);
				}
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}
		break;

			case 6552:
			if (c.playerMagicBook == 0) {
				if(c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
				c.setSidebarInterface(0, 328);
				}
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
			} else {
				if(c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
				c.setSidebarInterface(0, 328);
				}
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}	
		break;

		
		case 1816:
			c.getPA().startTeleport2(2271, 4680, 0);			
		break;
		case 1817:
			c.getPA().startTeleport(3086, 3493, 0, "modern");
		break;
		case 1814:
			//ardy lever
			c.getPA().startTeleport(3153, 3923, 0, "modern");
		break;
		
		case 9356:
			c.getPA().enterCaves();
			c.sendMessage("Good Luck!");
		break;
		case 12356:
			c.Waittt += 10;
			c.getPA().movePlayer(1899,5363, c.playerId * 4+2);
			c.sendMessage("Your first boss will arrive in 5 seconds, after death another will spawn.");

		break;
		case 1733:
			c.getPA().movePlayer(c.absX, c.absY + 6393, 0);
		break;
		
		case 1734:
			c.getPA().movePlayer(c.absX, c.absY - 6396, 0);
		break;
		
		case 9357:
			c.getPA().resetTzhaar();
		break;
		
		case 8959:
			if (c.getX() == 2490 && (c.getY() == 10146 || c.getY() == 10148)) {
				if (c.getPA().checkForPlayer(2490, c.getY() == 10146 ? 10148 : 10146)) {
					new Object(6951, c.objectX, c.objectY, c.heightLevel, 1, 10, 8959, 15);	
				}			
			}
		break;
		
		case 2996:
if (c.getItems().playerHasItem(989,1) && c.getItems().freeSlots() >= 1) {
c.getItems().deleteItem(989, 1);
c.getItems().addItem(c.getPA().randomCrystal(), 1);
c.getDH().sendDialogues(38, 945);
} else {
c.getDH().sendDialogues(37, 945); }
break;
		
		case 10177:
			c.getPA().movePlayer(1890, 4407, 0);
		break;
		case 10230:
			c.getPA().movePlayer(2900, 4449, 0);
		break;
		case 10229:
			c.getPA().movePlayer(1912, 4367, 0);
		break;
		case 2623:
			if (c.absX >= c.objectX)
				c.getPA().walkTo(-1,0);
			else
				c.getPA().walkTo(1,0);
		break;
		//pc boat
		case 14315:
			c.getPA().movePlayer(2661,2639,0);
		break;
		case 14314:
			c.getPA().movePlayer(2657,2639,0);
		break;
		
		case 1596:
		case 1597:
		if (c.getY() >= c.objectY)
			c.getPA().walkTo(0,-1);
		else
			c.getPA().walkTo(0,1);
		break;
		
		case 14235:
		case 14233:
			if (c.objectX == 2670)
				if (c.absX <= 2670)
					c.absX = 2671;
				else
					c.absX = 2670;
			if (c.objectX == 2643)
				if (c.absX >= 2643)
					c.absX = 2642;
				else
					c.absX = 2643;
			if (c.absX <= 2585)
				c.absY += 1;
			else c.absY -= 1;
			c.getPA().movePlayer(c.absX, c.absY, 0);
		break;
		case 14829: case 14830: case 14827: case 14828: case 14826: case 14831:
			//Server.objectHandler.startObelisk(objectType);
			Server.objectManager.startObelisk(objectType);
		break;
		case 4387:
			Server.castleWars.joinWait(c,1);
		break;
		
		case 4388:
			Server.castleWars.joinWait(c,2);
		break;
		
		case 4408:
			Server.castleWars.joinWait(c,3);
		break;
		
		case 9369:
                        c.sendMessage("Fight Caves disabled due to dupe");
                        break;
                
                case 9368:
                        c.sendMessage("Fight Caves disabled due to dupe");        
                break;
		case 4411:
		case 4415:
		case 4417:
		case 4418:
		case 4419:
		case 4420:
		case 4469:
		case 4470:
		case 4911:
		case 4912:
		case 1747:
		case 1757:
 			Server.castleWars.handleObjects(c, objectType, obX, obY);
		break;
		
		
		

		
		//barrows
		//Chest
		case 10284:
			if(c.barrowsKillCount < 5) {
				c.sendMessage("You haven't killed all the brothers.");
			}
			if(c.barrowsKillCount == 5 && c.barrowsNpcs[c.randomCoffin][1] == 1) {
				c.sendMessage("I have already summoned this npc.");
			}
			if(c.barrowsNpcs[c.randomCoffin][1] == 0 && c.barrowsKillCount >= 5) {
				Server.npcHandler.spawnNpc(c, c.barrowsNpcs[c.randomCoffin][0], 3551, 9694-1, 0, 0, 120, 30, 200, 200, true, true);
				c.barrowsNpcs[c.randomCoffin][1] = 1;
			}
			if((c.barrowsKillCount > 5 || c.barrowsNpcs[c.randomCoffin][1] == 2) && c.getItems().freeSlots() >= 2) {
				c.getPA().resetBarrows();
				c.getItems().addItem(c.getPA().randomRunes(), Misc.random(150) + 100);
				if (Misc.random(2) == 1)
					c.getItems().addItem(c.getPA().randomBarrows(), 1);
				c.getPA().startTeleport(3564, 3288, 0, "modern");
			} else if(c.barrowsKillCount > 5 && c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need at least 2 inventory slot opened.");
			}
			break;
		//doors
		case 6749:
			if(obX == 3562 && obY == 9678) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if(obX == 3558 && obY == 9677) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6730:
			if(obX == 3558 && obY == 9677) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if(obX == 3558 && obY == 9678) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6727:
			if(obX == 3551 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6746:
			if(obX == 3552 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6748:
			if(obX == 3545 && obY == 9678) {
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if(obX == 3541 && obY == 9677) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6729:
			if(obX == 3545 && obY == 9677){
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if(obX == 3541 && obY == 9678) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6726:
			if(obX == 3534 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if(obX == 3535 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6745:
			if(obX == 3535 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if(obX == 3534 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6743:
			if(obX == 3545 && obY == 9695) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if(obX == 3541 && obY == 9694) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break;
		case 6724:
			if(obX == 3545 && obY == 9694) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if(obX == 3541 && obY == 9695) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break; 
		//end doors
		//coffins
		case 6707: // verac
			c.getPA().movePlayer(3556, 3298, 0);
			break;
			
		case 6823:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[0][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2030, c.getX(), c.getY()-1, -1, 0, 120, 25, 200, 200, true, true);
				c.barrowsNpcs[0][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6706: // torag 
			c.getPA().movePlayer(3553, 3283, 0);
			break;
			
		case 6772:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[1][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2029, c.getX()+1, c.getY(), -1, 0, 120, 20, 200, 200, true, true);
				c.barrowsNpcs[1][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
			
		case 6705: // karil stairs
			c.getPA().movePlayer(3565, 3276, 0);
			break;
		case 6822:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[2][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2028, c.getX(), c.getY()-1, -1, 0, 90, 17, 200, 200, true, true);
				c.barrowsNpcs[2][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6704: // guthan stairs
			c.getPA().movePlayer(3578, 3284, 0);
			break;
		case 6773:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[3][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2027, c.getX(), c.getY()-1, -1, 0, 120, 23, 200, 200, true, true);
				c.barrowsNpcs[3][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6703: // dharok stairs
			c.getPA().movePlayer(3574, 3298, 0);
			break;
		case 6771:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[4][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2026, c.getX(), c.getY()-1, -1, 0, 120, 45, 250, 250, true, true);
				c.barrowsNpcs[4][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6702: // ahrim stairs
			c.getPA().movePlayer(3565, 3290, 0);
			break;
		case 6821:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[5][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2025, c.getX(), c.getY()-1, -1, 0, 90, 19, 200, 200, true, true);
				c.barrowsNpcs[5][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
/*BetterWC*/		
case 1276:
                case 1278://trees
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1511;
                        c.woodcut[1] = 1;
                        c.woodcut[2] = 25;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 8;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
                }
break;
                case 1281: //oak
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1521;
                        c.woodcut[1] = 15;
                        c.woodcut[2] = 37;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 9;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
}break;
               
                case 1308: //willow
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1519;
                        c.woodcut[1] = 30;
                        c.woodcut[2] = 68;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 10;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
}break;
               
                case 1307: //maple
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1517;
                        c.woodcut[1] = 45;
                        c.woodcut[2] = 100;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 11;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
}break;
               
                case 1309: //yew
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1515;
                        c.woodcut[1] = 60;
                        c.woodcut[2] = 175;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 12;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
}break;
               
                case 1306: // magic
if(c.getItems().freeSlots() <= 0){
                        c.sendMessage("Your inventory is full");
}else{
                        c.woodcut[0] = 1513;
                        c.woodcut[1] = 75;
                        c.woodcut[2] = 250;
                        c.woodcut[3] = obX;
                        c.woodcut[4] = obY;
                        c.woodcut[5] = 13;
                        c.woodcut[6] = objectType;
                        c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2], c.woodcut[3], c.woodcut[4], c.woodcut[5], c.woodcut[6]);
	}
break;
/*BetterWCEnd*/
/*BetterMining*/		
case 2091:
    c.getMining().startMining(0, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2095:
    c.getMining().startMining(1, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2093:
    c.getMining().startMining(2, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2097:
    c.getMining().startMining(3, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2103:
    c.getMining().startMining(4, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2105:
    c.getMining().startMining(5, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2107:
    c.getMining().startMining(6, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2090:
    c.getMining().startMining(7, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2094:
    c.getMining().startMining(8, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2092:
    c.getMining().startMining(9, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2096:
    c.getMining().startMining(10, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2102:
    c.getMining().startMining(11, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2104:
    c.getMining().startMining(12, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2106:
    c.getMining().startMining(13, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2100:
    c.getMining().startMining(14, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2101:
    c.getMining().startMining(15, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2098:
    c.getMining().startMining(16, c.objectX, c.objectY, c.clickObjectType);
    break;
case 2099:
    c.getMining().startMining(17, c.objectX, c.objectY, c.clickObjectType);
    break;
		
		case 8143:
			if (c.farm[0] > 0 && c.farm[1] > 0) {
				c.getFarming().pickHerb();
			}
		break;
	
			// DOORS
		case 1516:
		case 1519:
		
			if (c.objectY == 9698) {
				if (c.absY >= c.objectY)
					c.getPA().walkTo(0,-1);
				else
					c.getPA().walkTo(0,1);
				break;
			}
		case 1557:
		case 1558:
		c.getPA().walkTo(0,-1);
		break;	
		case 1530:
		case 1531:
		case 1533:
		case 1534:
		case 11712:
		case 11711:
		case 11707:
		case 11708:
		case 6725:
		case 3198:

		case 3197:
			Server.objectHandler.doorHandling(objectType, c.objectX, c.objectY, 0);	
			break;

		
		case 9319:
			if (c.heightLevel == 0)
				c.getPA().movePlayer(c.absX, c.absY, 1);
			else if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 2);
		break;
		
		case 9320:
			if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 0);
			else if (c.heightLevel == 2)
				c.getPA().movePlayer(c.absX, c.absY, 1);
		break;
		
		case 4496:
		case 4494:
			if (c.heightLevel == 2) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 0);
			}
		break;
		
		case 4493:
			if (c.heightLevel == 0) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
		break;
		
		case 4495:
			if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
		break;
		
		case 5126:
			if (c.absY == 3554)
				c.getPA().walkTo(0,1);
			else
				c.getPA().walkTo(0,-1);
		break;
		
		case 1755:
		        c.getPA().movePlayer(3086, 3493, 0);
		break;
		case 1759:
			if (c.objectX == 2884 && c.objectY == 3397)
				c.getPA().movePlayer(c.absX, c.absY + 6400, 0);				
		break;
		/*case 3203: //dueling forfeit
			if (c.duelCount > 0) {
				c.sendMessage("You may not forfeit yet.");
				break;
			}
			Client o = (Client) Server.playerHandler.players[c.duelingWith];				
			if(o == null) {
				c.getTradeAndDuel().resetDuel();
				c.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				break;
			}
			if(c.duelRule[0]) {
				c.sendMessage("Forfeiting the duel has been disabled!");
				break;
			}
			if(o != null) {
				o.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				c.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				o.duelStatus = 6;
				o.getTradeAndDuel().duelVictory();
				c.getTradeAndDuel().resetDuel();
				c.getTradeAndDuel().resetDuelItems();
				o.sendMessage("The other player has forfeited the duel!");
				c.sendMessage("You forfeit the duel!");
				break;
			}
			
			break;*/
			
		case 409:
			if(c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
				c.startAnimation(645);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.sendMessage("You recharge your prayer points.");
				c.getPA().refreshSkill(5);
			} else {
				c.sendMessage("You already have full prayer points.");
			}
			break;
		case 2873:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Saradomin blesses you with a cape.");
				c.getItems().addItem(2412, 1);
			}	
		break;
		case 2875:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Guthix blesses you with a cape.");
				c.getItems().addItem(2413, 1);
			}
		break;
		case 2874:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Zamorak blesses you with a cape.");
				c.getItems().addItem(2414, 1);
			}
		break;
		case 2879:
			c.getPA().movePlayer(2538, 4716, 0);
		break;
		case 2878:
			c.getPA().movePlayer(2509, 4689, 0);
		break;
		case 5960:
			c.getPA().startTeleport2(3090, 3956, 0);
		break;
		
		case 1815:
			c.getPA().startTeleport2(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0);
		break;
		
		case 9706:
			c.getPA().startTeleport2(3105, 3951, 0);
		break;
		case 9707:
			c.getPA().startTeleport2(3105, 3956, 0);
		break;
		
		case 5959:
			c.getPA().startTeleport2(2539, 4712, 0);
		break;
		
		case 2558:
			c.sendMessage("This door is locked.");	
		break;
		
		case 9294:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(c.objectX + 1, c.absY, 0);
			} else if (c.absX > c.objectX) {
				c.getPA().movePlayer(c.objectX - 1, c.absY, 0);
			}
		break;
				case 104:
			if (c.isDonator == 1 && c.donatorChest == 0) {
					c.sendMessage("There appears to be nothing inside.");	

			} else if (c.isDonator == 1 && c.donatorChest >= 1) {
					c.donatorChest -= 1;
					c.getItems().addItem(donatorRitem(),Misc.random(1));
					//c.getItems().addItem(donatorRitem2(),Misc.random(1));
					c.getItems().addItem(995,Misc.random(100000));				
					
			} else {
				c.sendMessage("This is a donator only chest.");
			}
		break;
		case 9293:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(2892, 9799, 0);
			} else {
				c.getPA().movePlayer(2886, 9799, 0);
			}
		break;
		case 10529:
		case 10527:
			if (c.absY <= c.objectY)
				c.getPA().walkTo(0,1);
			else
				c.getPA().walkTo(0,-1);
		break;
		case 3044:
			c.getSmithing().sendSmelting();
		break;
		case 733:
			c.startAnimation(451);
			/*if (Misc.random(1) == 1) {
				c.getPA().removeObject(c.objectX, c.objectY);
				c.sendMessage("You slash the web.");
			} else {
				c.sendMessage("You fail to slash the webs.");
			}*/
			if (c.objectX == 3158 && c.objectY == 3951) {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 1, 10, 733, 50);
			} else {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 0, 10, 733, 50);
			}
		break;
		
		default:
			ScriptManager.callFunc("objectClick1_"+objectType, c, objectType, obX, obY);
			break;

		}
	}
	
	public void secondClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		//c.sendMessage("Object type: " + objectType);
		switch(objectType) {
			case 11666:
			case 3044:
				c.getSmithing().sendSmelting();
			break;
			case 26288:
			case 26287:
			case 26286:
			case 26289:
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
			c.getPA().movePlayer(2882, 5310, 2);
			c.sendMessage("You teleported out of the god's chamber.");
			break;
/*Thieving*/
			
			case 2470:
		if (c.playerLevel[c.playerSlayer] >= 80) {
			c.getPA().movePlayer(3277, 3029, 0);
		} else {
		c.sendMessage("You need a Slayer level of 80+ to enter the Slayer Farm!");
		}
		break;
			case 635:
			c.getThieving().stealFromStall(TeaStall[Misc.random(getTeaLength() - 1)], 150, 1, objectType, obX, obY);
			break;
			case 4705:
			c.getThieving().stealFromStall(FishStall[Misc.random(getFishLength() - 1)], 375, 40, objectType, obX, obY);
			break;
			case 4707:
			c.getThieving().stealFromStall(FishStall2[Misc.random(getFish2Length() - 1)], 700, 40, objectType, obX, obY);
			break;
			case 4708:
			c.getThieving().stealFromStall(VegStall2[Misc.random(getVeg2Length() - 1)], 500, 20, objectType, obX, obY);
			break;
			case 4706:
			c.getThieving().stealFromStall(VegStall[Misc.random(getVegLength() - 1)], 250, 20, objectType, obX, obY);
			break;
			case 2560:
			c.getThieving().stealFromStall(SilkStall[Misc.random(getSilkLength() -1)], 40, 35, objectType, obX, obY);
			break;
			case 2561:
			c.getThieving().stealFromStall(BakeryStall[Misc.random(getBakeryLength() - 1)], 10, 1, objectType, obX, obY);
			break;
			case 2562:
				c.getThieving().stealFromStall(GemStall[Misc.random(getGemLength() - 1)], 30, 25, objectType, obX, obY);
			break;
			case 2563:
				c.getThieving().stealFromStall(FurStall[Misc.random(getFurLength() - 1)], 60, 50, objectType, obX, obY);
			break;
			case 2564:
				c.getThieving().stealFromStall(SpiceStall[Misc.random(getSpiceLength() - 1)], 100, 75, objectType, obX, obY);
			break;
			case 2565:
				c.getThieving().stealFromStall(SilverStall[Misc.random(getSilverLength() - 1)], 170, 90, objectType, obX, obY);
			break;
/*Thieving end*/

/*Bonfires*/
                                    case 2732:
if (c.getItems().playerHasItem(1511,1)){
    c.getItems().deleteItem(1511, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(40 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
else if (c.getItems().playerHasItem(1521,1)){
    c.getItems().deleteItem(1521, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(60 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
else if (c.getItems().playerHasItem(1519,1)){
    c.getItems().deleteItem(1519, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(90 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
else if (c.getItems().playerHasItem(1517,1)){
    c.getItems().deleteItem(1517, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(135 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
else if (c.getItems().playerHasItem(1515,1)){
    c.getItems().deleteItem(1515, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(202 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
    else if (c.getItems().playerHasItem(1513,1)){
    c.getItems().deleteItem(1513, 1);
    c.startAnimation(883);
    c.stopMovement();
c.getPA().addSkillXP(304 * Config.FIREMAKING_EXPERIENCE, 11);
    c.sendMessage("You add a log to the fire.");
    }
    break;

		case 2213: //banks
		case 14367:
		case 11758:
		case 3193:
			c.getPA().openUpBank();
		break;
	
			case 2558:
				if (System.currentTimeMillis() - c.lastLockPick < 3000 || c.freezeTimer > 0)
					break;
				if (c.getItems().playerHasItem(1523,1)) {
						c.lastLockPick = System.currentTimeMillis();
						if (Misc.random(10) <= 3){
							c.sendMessage("You fail to pick the lock.");
							break;
						}
					if (c.objectX == 3044 && c.objectY == 3956) {
						if (c.absX == 3045) {
							c.getPA().walkTo2(-1,0);
						} else if (c.absX == 3044) {
							c.getPA().walkTo2(1,0);
						}
					
					} else if (c.objectX == 3038 && c.objectY == 3956) {
						if (c.absX == 3037) {
							c.getPA().walkTo2(1,0);
						} else if (c.absX == 3038) {
							c.getPA().walkTo2(-1,0);
						}				
					} else if (c.objectX == 3041 && c.objectY == 3959) {
						if (c.absY == 3960) {
							c.getPA().walkTo2(0,-1);
						} else if (c.absY == 3959) {
							c.getPA().walkTo2(0,1);
						}					
					}
				} else {
					c.sendMessage("I need a lockpick to pick this lock.");
				}
			break;
		default:
			ScriptManager.callFunc("objectClick2_"+objectType, c, objectType, obX, obY);
			break;
		}
	}
	
	
	public void thirdClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		c.sendMessage("Object type: " + objectType);
		switch(objectType) {
		default:
			ScriptManager.callFunc("objectClick3_"+objectType, c, objectType, obX, obY);
			break;
		}
	}
	
	public void firstClickNpc(int npcType) {
	c.fishitem = -1;
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if (c.fishitem != -1) {
                    if (!c.getItems().playerHasItem(c.fishitem)) {
                        c.sendMessage("You need a " + c.getItems().getItemName(c.fishitem) + " to fish for " + c.getItems().getItemName(c.fishies));
                        c.fishing = false;
                        return;
                    }
                    if (c.getItems().freeSlots() == 0) {
                        c.sendMessage("Your inventory is full.");
                        c.fishing = false;
                        return;
                    }
                    if (c.playerFishing < c.fishreqt) {
                        c.sendMessage("You need a fishing level of " + c.fishreqt + " to fish here.");
                        c.fishing = false;
                        return;
                    }
                    c.fishtimer = c.getFishing().fishtime(c.fishies, c.fishreqt);
                }
		switch(npcType) {
case 6788:
c.getDH().sendOption5("Old Prayers", "Curses", "Old Magic", "Ancient Magick", "Lunar Magic");
			c.dialogueAction = 5678;
break;
case 3328:
c.getShops().openShop(9);
c.sendMessage("You current have "+c.SlayPts+" Slayer Point(s)");
break;
case 308:
c.getShops().openShop(7);
break;
case 6365:

c.getTotalPoints();
c.getShops().openShop(10);

break;
case 6258:

c.getTotalPoints();
c.getShops().openShop(11);

break;
case 8008:

c.getTotalPoints();
c.getShops().openShop(12);

break;
case 7400:
if (c.getItems().playerHasItem(691, 1)) {
c.getItems().addItem(995, 25000);
 c.getItems().deleteItem(691, 1);
}
if (c.getItems().playerHasItem(692, 1)) {
c.getItems().addItem(995, 50000);
 c.getItems().deleteItem(692, 1);
}
if (c.getItems().playerHasItem(693, 1)) {
c.getItems().addItem(995, 75000);
 c.getItems().deleteItem(693, 1);
}
	
			break;
			case 2830:
				c.getDH().sendDialogues(200, npcType);
				break;
			case 1512:
				c.getDH().sendDialogues(70, 4289);
			break;
case 8206:
    if (c.playerRights == 0) {
        c.getDH().sendDialogues(185, 0);
    } else if (c.playerRights == 1) {
        c.getDH().sendDialogues(185, 0);
    } else if (c.playerRights == 3) {
        c.getDH().sendDialogues(185, 0);
    } else if (c.playerRights == 4) {
        c.getDH().sendDialogues(185, 0);
    } else if (c.playerRights == 2) {
        c.sendMessage("Your rank is too high to participate in the lottery!");
    }
break;
case 3236://Construction shop
    c.getShops().openShop(91);
break;
			case 6794:
			case 6873:
			c.getDH().sendDialogues(75, 4289);
			break;
case 946: 
c.getDH().sendDialogues(20, npcType); 
break;
                case 316:
                    c.fishing = true;
					c.fishXP = 250;
                    c.fishies = 317;
                    c.fishreqt = 0;
                    c.fishitem = 303;
                    c.fishemote = 621;
                    c.fishies2 = 0;
                    c.fishreq2 = 0;
                break;
                case 334:
                    c.fishing = true;
					c.fishXP = 250;
                    c.fishies = 317;
                    c.fishreqt = 0;
                    c.fishitem = 303;
                    c.fishemote = 621;
                    c.fishies2 = 0;
                    c.fishreq2 = 0;
                break;
                case 324://cage-harpoon spot choice cage
                    c.fishing = true;
                    c.fishXP = 500;
                    c.fishies = 377;
                    c.fishreqt = 40;
                    c.fishitem = 301;
                    c.fishemote = 619;
                    c.fishies2 = 389;
                    c.fishreq2 = 81;
                break;
		case 325:
		c.fishing = true;
                    c.fishXP = 600;
                    c.fishies = 15272;
                    c.fishreqt = 40;
                    c.fishitem = 301;
                    c.fishemote = 619;
                    c.fishies2 = 15272;
                    c.fishreq2 = 99;
                break;
		case 320:
		c.fishing = true;
                    c.fishXP = 600;
                    c.fishies = 15272;
                    c.fishreqt = 40;
                    c.fishitem = 301;
                    c.fishemote = 619;
                    c.fishies2 = 15272;
                    c.fishreq2 = 95;
                break;
		case 326:
                    c.fishing = true;
                    c.fishXP = 400;
                    c.fishies = 341;
                    c.fishreqt = 23;
                    c.fishitem = 303;
                    c.fishemote = 621;
                    c.fishies2 = 363;
                    c.fishreq2 = 46;
                break;
		case 313:
                    c.fishing = true;
                    c.fishXP = 400;
                    c.fishies = 341;
                    c.fishreqt = 23;
                    c.fishitem = 303;
                    c.fishemote = 621;
                    c.fishies2 = 363;
                    c.fishreq2 = 46;
                break;
		case 3100: 
		c.getPA().movePlayer(2717, 9801, 0);
		c.sendMessage("You teleport to the Tormented demons.");
		break;
			case 4289:
			c.kamfreenaDone = true;
			c.getDH().sendDialogues(47, 4289);
				break;
			case 1061:
			c.inCyclops = true;
			c.getWarriorsGuild().handleKamfreena(c, true);
			break;
			case 1062:
			c.kamfreenaDone = false;
			c.inCyclops = false;
			c.getWarriorsGuild().handleKamfreena(c, true);
			break;
			case 2258:
				c.getDH().sendDialogues(17, npcType);
			break;
			case 2261:
				c.getPA().walkableInterface(-1);
				c.getPA().movePlayer(2885, 5330, 2);
			break;

			case 2259:
				c.getPA().movePlayer(2885, 5345, 2);
				c.getPA().walkableInterface(12418);
				c.sendMessage("You have entered the Zamorak chamber, To leave talk to me on the other side.");
			break;
			case 398:
				c.getPA().movePlayer(2918, 5273, 0);
				c.sendMessage("You have entered the Saradomin chamber, To leave talk to me on the other side.");
			break;
			case 399:
				c.getPA().movePlayer(2911, 5299, 2);
			break;
			case 1064:
				c.getPA().movePlayer(2852, 5333, 2);
			break;

			case 1063:
				c.getPA().movePlayer(2849, 5333, 2);
				c.sendMessage("You have entered the Bandos chamber, To leave talk to me on the other side.");
			break;
	
			case 8548:
				c.getPA().movePlayer(2384, 4712, 0);
				c.sendMessage("The Phoenix's follower escorts you to its lair.");
			break;

			case 9084:
			if (c.playerLevel[16] == 99) {
			c.sendMessage("Kuradal teleports you to the Forinthry dungeon.");
			c.getPA().startTeleport(2372, 4940, 0, "modern");
			} else {
			c.sendMessage("You need a Slayer level of 99 to talk to her.");
			return;
			}
			break;
				
			case 8517:
				if ((c.playerLevel[0] == 99) && (c.playerLevel[1] == 99) && (c.playerLevel[2] == 99) && (c.playerLevel[3] == 99) && (c.playerLevel[4] == 99) && (c.playerLevel[5] == 99) && (c.playerLevel[6] == 99) && (c.playerLevel[7] == 99) && (c.playerLevel[8] == 99) && (c.playerLevel[9] == 99) && (c.playerLevel[10] == 99) && (c.playerLevel[11] == 99) && (c.playerLevel[12] == 99) && (c.playerLevel[13] == 99) && (c.playerLevel[14] == 99) && (c.playerLevel[15] == 99) && (c.playerLevel[16] == 99) && (c.playerLevel[17] == 99) && (c.playerLevel[18] == 99) && (c.playerLevel[19] == 99) && (c.playerLevel[20] == 99) && (c.playerLevel[21] == 99) && (c.playerLevel[22] == 99) && (c.playerLevel[24] == 99)) {
					c.sendMessage("Jack Frost recognizes your talents and hands you the Completionist cape.");
					c.getItems().addItem(14016, 1);
				} else {
					c.sendMessage("Jack Frost denies your request. (You need level 99 in all skills to receive this cape.)");
					return;
				}
				break;

			case 8526:
				c.getPA().startTeleport(2716, 9620, 0, "modern");
				c.sendMessage("Zimberfizz uses his powers to teleport you to the Decaying avatar.");
			break;

			case 9173:
				c.getPA().movePlayer(2462, 5282, 0);
				c.sendMessage("The old man takes you to the Skeletal horror. He also gives you a bonesack for free!");
				c.getItems().addItem(7918, 1);
			break;

			case 5837:
				c.getDH().sendDialogues(700, npcType);
			break;

			case 1972:
				c.teleAction = c.dialogueAction = 0;
				c.getDH().sendDialogues(40, npcType);
			break;

			case 863:
				c.getPA().movePlayer(2560, 4950, 0);
				c.sendMessage("The scared skavid shakily shows you the way to the Balance Elemental.");
			break;
				
			case 70:
				c.getPA().movePlayer(2872, 5269, 2);
				c.sendMessage("You have entered the Armadyl chamber, To leave click the Pillar.");
				c.sendMessage("Note: You can only use ranged here!");
			break;
			case 8275:
				if (c.slayerTask <= 0) {
					c.getDH().sendDialogues(11,npcType);
				} else {
					c.getDH().sendDialogues(13,npcType);
				}
			break;
			case 500:
			if (c.monkeyk0ed >= 20) {
					c.getDH().sendDialogues(30,npcType);
				} else {
					c.getDH().sendDialogues(32,npcType);
				}			
			break;
			case 919:
				c.getShops().openShop(10);
			break;
			
			case 3381:
				c.getShops().openShop(76);
			break;
			case 6750:
				c.getShops().openShop(77);
			break;
			case 6746:///Level Point Shop
				c.getShops().openShop(11);
				c.sendMessage("You currently have <col=255>" + c.lvlPoints + "</col> Level Points.");
			break;
			case 659:
				c.getDH().sendDialogues(194,npcType);
				//c.getShops().openShop(73);
				//c.sendMessage("You currently have <col=255>" + c.pkPoints + "</col> EXLp.");
			break;
			case 661:
				c.getDH().sendDialogues(196,npcType);
				//c.getShops().openShop(74);
				//c.sendMessage("You currently have <col=255>" + c.pkPoints + "</col> EXLp.");
			break;
			case 1294:
				c.getShops().openShop(72);
			break;
			case 5839:
				c.getShops().openShop(75);
			break;

			case 1778:
				c.getShops().openShop(71);
			break;
			case 1779:
				c.getShops().openShop(67);
			break;
			case 554:
				c.getShops().openShop(68);
			break;
			case 5896:
				c.getShops().openShop(8);
				c.sendMessage("You currently have "+c.BossPoints+" points to spend!");
			break;
			case 520:
				c.getShops().openShop(69);
			break;
			case 542:
				c.getShops().openShop(9);
			break;
			case 541:
				c.getShops().openShop(5);
			break;
			case 4290:
				c.getShops().openShop(66);
			break;
			
			case 461:
				c.getShops().openShop(2);
			break;

			case 57:
				c.getShops().openShop(78);
			break;
			
			case 683:
				c.getShops().openShop(3);
			break;
			
			case 549:
				c.getShops().openShop(4);
			break;
			
			//hunter
			
			//implin's
			case 6055:
			c.CatchimpNpc("Baby Impling", 10010, 6055, 11238, 1500, 1, c.playerId);
			break;
			case 6056:
			c.CatchimpNpc("Young Impling", 10010, 6056, 11240, 3500, 17, c.playerId);
			break;
			case 6057:
			c.CatchimpNpc("Gourmet Impling", 10010, 6057, 11242, 4000, 20, c.playerId);
			break;
			case 6058:
			c.CatchimpNpc("Earth Impling", 10010, 6058, 11244, 5000, 34, c.playerId);
			break;
			case 6059:
			c.CatchimpNpc("Essence impling", 10010, 6059, 11246, 6000, 40, c.playerId);
			break;
			case 6060:
			c.CatchimpNpc("Electic impling", 10010, 6060, 11248, 8000, 50, c.playerId);
			break;
			case 6061:
			c.CatchimpNpc("Nature impling", 10010, 6061, 11250, 10000, 58, c.playerId);
			break;
			case 6062:
			c.CatchimpNpc("Magpie impling", 10010, 6062, 11252, 12500, 65, c.playerId);
			break;
			case 6063:
			c.CatchimpNpc("Ninja impling", 10010, 6063, 11254, 14000, 74, c.playerId);
			break;
			case 6064:
			c.CatchimpNpc("Dragon Impling", 10010, 6064, 11256, 25000, 90, c.playerId);
			break;
			
			//end of implin's!
			
			//butterfly's
							case 5082:
				c.CatchHunterNpc("Black Warlock", 10010, 5082, 10014, 18000, 85, c.playerId);
				break;
				case 5083:
				c.CatchHunterNpc("Snowy Knight", 10010, 5083, 10016, 15000, 75, c.playerId);
				break;
				case 5084:
				c.CatchHunterNpc("Sapphire Glacialis", 10010, 5084, 10018, 7500, 45, c.playerId);
				break;
				case 5085:
				c.CatchHunterNpc("Ruby Harvest", 10010, 5085, 10020, 5000, 30, c.playerId);
				break;
			//end of butterfly's	
				
		// end of hunter :)
			case 2538:
				c.getShops().openShop(6);
			break;
			
			case 519:
				c.getShops().openShop(8);
			break;
			case 1282:
				c.getShops().openShop(7);
			break;
			case 1152:
				c.getDH().sendDialogues(16,npcType);
			break;
			case 5580:
				c.getDH().sendDialogues(70,npcType);
			break;
case 521:
	c.getDH().sendOption5("Tier 1 (Level 3)", "Tier 2 (Levels 4-50)", "Tier 3 (Levels 51-99)", "Tier 4 (Levels 100-126)", "Tier 5 (Levels 126+)");
			c.dialogueAction = 2222;
			c.nextChat = 0;
				
			break;
			case 494:
				c.getPA().openUpBank();
			break;
			case 2566:
				c.getShops().openSkillCape();
			break;
			case 3789:
				c.sendMessage("You currently have " + c.pkPoints + " EXLp.");
			break;
			case 3788:
				c.getShops().openVoid();
			break;
			case 905:
				c.getDH().sendDialogues(5, npcType);
			break;
			case 460:
				c.getDH().sendDialogues(3, npcType);
			break;
			case 462:
				c.getDH().sendDialogues(7, npcType);
			break;
				case 6970:
				c.getShops().openShop(13);
			break;
	case 6971:
				c.getShops().openShop(12);
				break;
			case 3098:
			c.getDH().sendOption5("Training Dungeon", "Slayer Tower", "*Gold Members Slayer Dungeon", "Dragon Cavern", "Tzhaar");
			c.dialogueAction = 2223;
			c.nextChat = 0;
			break;
			case 522:
			case 523:
				c.getShops().openShop(1);
			break;
			case 599:
				c.getDH().sendDialogues(198, npcType);
			break;
			case 904:
				c.sendMessage("You have " + c.magePoints + " points.");
			break;
		default:
			ScriptManager.callFunc("npcClick1_"+npcType, c, npcType);
			if(c.playerRights == 3) 
				Misc.println("First Click Npc : "+npcType);
			break;
					}
	}


public void store(int i, int npcType)
{


switch(npcType) {
case 6807:
if(Server.npcHandler.npcs[i].npcId == c.summoningnpcid) {
c.sendMessage("You are now storing items inside your npc");
	c.Summoning().store();
}
break;
}
}
	public void secondClickNpc(int npcType) {
	c.fishitem = -1;
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if (c.fishitem != -1) {
                    if (!c.getItems().playerHasItem(c.fishitem)) {
                        c.sendMessage("You need a " + c.getItems().getItemName(c.fishitem) + " to fish for " + c.getItems().getItemName(c.fishies));
                        c.fishing = false;
                        return;
                    }
                    if (c.getItems().freeSlots() == 0) {
                       c. sendMessage("Your inventory is full.");
                        c.fishing = false;
                        return;
                    }
                    if (c.playerFishing < c.fishreqt) {
                        c.sendMessage("You need a fishing level of " + c.fishreqt + " to fish here.");
                        c.fishing = false;
                        return;
                    }
                    c.fishtimer = c.getFishing().fishtime(c.fishies, c.fishreqt);
                }
		switch(npcType) {
					case 526:
				c.getShops().openShop(14);
			break;
			case 527:
				c.getShops().openShop(15);
			break;
			//START OF MY SHOPS!
			

			case 528:
				c.getShops().openShop(16);
			break;
			case 1282:
				c.getShops().openShop(7);
			break;
			case 659:	
				c.getShops().openShop(73);
				c.sendMessage("You currently have <col=255>" + c.pkPoints + "</col> EXLp.");
			break;
			case 661:
				c.getShops().openShop(74);
				c.sendMessage("You currently have <col=255>" + c.pkPoints + "</col> EXLp.");
			break;
			case 1972:
				c.teleAction = c.dialogueAction = 0;
				c.getDH().sendDialogues(43, npcType);
			break;
			case 333:
                    c.fishing = true;
                    c.fishXP = 550;
                    c.fishies = 359;
                    c.fishreqt = 35;
                    c.fishitem = 311;
                    c.fishemote = 618;
                    c.fishies2 = 371;
                    c.fishreq2 = 50;
					break;
                case 312:
                    c.fishing = true;
                    c.fishXP = 550;
                    c.fishies = 359;
                    c.fishreqt = 35;
                    c.fishitem = 311;
                    c.fishemote = 618;
                    c.fishies2 = 371;
                    c.fishreq2 = 50;
					break;
                case 324:
                    c.fishing = true;
                    c.fishXP = 550;
                    c.fishies = 359;
                    c.fishreqt = 35;
                    c.fishitem = 311;
                    c.fishemote = 618;
                    c.fishies2 = 371;
                    c.fishreq2 = 50;
		break;
                case 334:
                    c.fishing = true;
                    c.fishXP = 550;
                    c.fishies = 359;
                    c.fishreqt = 35;
                    c.fishitem = 311;
                    c.fishemote = 618;
                    c.fishies2 = 371;
                    c.fishreq2 = 50;
		break;
                case 316:
                    c.fishing = true;
                    c.fishXP = 430;
					c.fishies = 327;
					c.fishreqt = 5;
                    c.fishitem = 307;
                    c.fishemote = 622;
                    c.fishies2 = 345;
                    c.fishreq2 = 10;
					break;
                case 326:
                    c.fishing = true;
                    c.fishXP = 430;
					c.fishies = 327;
					c.fishreqt = 5;
                    c.fishitem = 307;
                    c.fishemote = 622;
                    c.fishies2 = 345;
                    c.fishreq2 = 10;
					break;
               case 331:
                    c.fishing = true;
                    c.fishXP = 670;
                    c.fishies = 349;
                    c.fishreqt = 25;
                    c.fishitem = 307;
                    c.fishemote = 622;
                    c.fishies2 = 0;
                    c.fishreq2 = 0;			
					
									


                case 313:
                    c.fishing = true;
                    c.fishXP = 800;
                    c.fishies = 383;
                    c.fishreqt = 79;
                    c.fishitem = 311;
                    c.fishemote = 618;
                    c.fishies2 = 0;
                    c.fishreq2 = 0;
                break;
			case 3788:
				c.getShops().openVoid();
			break;
			case 494:
				c.getPA().openUpBank();
			break;
			case 904:
				c.getShops().openShop(17);
			break;
			case 522:
			case 523:
				c.getShops().openShop(1);
			break;
			case 541:
				c.getShops().openShop(5);
			break;
			
			case 461:
				c.getShops().openShop(2);
			break;
			
			case 683:
				c.getShops().openShop(3);
			break;
			
			case 549:
				c.getShops().openShop(4);
			break;
			case 599:
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			break;
			
			case 2538:
				c.getShops().openShop(6);
			break;
			
			case 519:
				c.getShops().openShop(8);
			break;
			case 3789:
				c.getShops().openShop(18);
				c.sendMessage("You have " + c.pkPoints + " EXLp.");
			break;
			case 1:
			case 9:
			case 18:
			case 20:
			case 26:
			case 21:
				c.getThieving().stealFromNPC(npcType);
			break;
			default:
				ScriptManager.callFunc("npcClick2_"+npcType, c, npcType);
				if(c.playerRights == 3) 
					Misc.println("Second Click Npc : "+npcType);
				break;
			
		}
	}
	
	public void thirdClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		switch(npcType) {
					
					
			case 8275:
				c.getShops().openShop(48);
			c.sendMessage("You currently have <col=255>" + c.SPoints + "</col> slayer Points.");
			break;
			default:
				ScriptManager.callFunc("npcClick3_"+npcType, c, npcType);
				if(c.playerRights == 3) 
					Misc.println("Third Click NPC : "+npcType);
				break;

		}
	}
	

}