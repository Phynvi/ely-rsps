import java.io.*;
import sign.signlink;

public final class ItemDef {

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
if (itemDef.editedModelColor != null) {
			for (int i2 = 0; i2 < itemDef.editedModelColor.length; i2++) {
				if (itemDef.newModelColor[i2] == 0) {
					itemDef.newModelColor[i2] = 1;
				}
			}
		}
		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lentItemID != -1)
			itemDef.toLend();
		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.";
			itemDef.groundActions = null;
			itemDef.itemActions = null;
			itemDef.team = 0;
		}
		switch (itemDef.id) {
		case 6828:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			
	
	
						itemDef.name = "@gre@Low Reward Box";
						itemDef.description = "You have a low chance of getting a good reward.";
				
		break;
		case 6829:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			
	
	
						itemDef.name = "@yel@Medium Reward Box";
						itemDef.description = "You have a medium chance of getting a good reward.";
				
		break;
		case 6831:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			
	
	
						itemDef.name = "@blu@High Reward Box";
						itemDef.description = "You have a High chance of getting a good reward.";
				
		break;
		case 15084:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Roll";
			itemDef.modelid = 7509;
			itemDef.modelZoom = 760;
			itemDef.modelRotationY = 0;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.name = "Dice bag";
			itemDef.description = "It's a dice bag";
		break;
	case 19123:
		itemDef.modelid = 67133;
		itemDef.name = "Raptor chestplate";
		itemDef.description = "Its a raptor chestplate.";
		itemDef.modelZoom = 1486;
		itemDef.modelRotationY = 553;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 66619;
		itemDef.anInt200 = 67877;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
	case 19124:
		itemDef.modelid = 67132;
		itemDef.name = "Raptor tassets";
		itemDef.description = "Its raptor tassets.";
		itemDef.modelZoom = 1616;
		itemDef.modelRotationY = 303;
		itemDef.modelRotationX = 144;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 15;
		itemDef.anInt165 = 66508;
		itemDef.anInt200 = 67874;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
	case 19125:
		itemDef.modelid = 67131;
		itemDef.name = "Raptor boots";
		itemDef.description = "A pair of raptor boots.";
		itemDef.modelZoom = 743;
		itemDef.modelRotationY = 158;
		itemDef.modelRotationX = 159;
		itemDef.modelOffset1 = 5;
		itemDef.modelOffset2 = -7;
		itemDef.anInt165 = 66320;
		itemDef.anInt200 = 67866;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
			case 19126:
				itemDef.name = "Trickster helm";
				itemDef.description = "Its a Trickster helm";
				itemDef.anInt200 = 44764;
				itemDef.anInt165 = 44764;
				itemDef.modelid = 45328;
				itemDef.modelRotationY = 5;
				itemDef.modelRotationX = 1889;
				itemDef.modelZoom = 738;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
			break;
			case 19127:
				itemDef.name = "Trickster robe";
				itemDef.description = "Its a Trickster robe";
				itemDef.anInt200 = 44786;
				itemDef.anInt165 = 44786;
				itemDef.modelid = 45329;
				itemDef.modelRotationY = 593;
				itemDef.modelRotationX = 2041;
				itemDef.modelZoom = 1420;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
			break;
			case 19128:
				itemDef.name = "Trickster robe legs";
				itemDef.description = "Its a Trickster robe";
				itemDef.anInt200 = 44770;
				itemDef.anInt165 = 44770;
				itemDef.modelid = 45335;
				itemDef.modelRotationY = 567;
				itemDef.modelRotationX = 1023;
				itemDef.modelZoom = 2105;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
			break;
			case 19129:
				itemDef.modelid = 45316;
				itemDef.name = "Trickster boots";
				itemDef.description = "Its a Trickster boot";
				itemDef.modelZoom = 848;
				itemDef.modelRotationX = 141;
				itemDef.modelRotationY = 141;
				itemDef.modelOffset1 = -9;
				itemDef.modelOffset2 = 0;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44757;
				itemDef.anInt165 = 44757;
			break;
			case 19130:
				itemDef.modelid = 45317;
				itemDef.name = "Trickster gloves";
				itemDef.description = "Its a Trickster glove";
				itemDef.modelZoom = 830;
				itemDef.modelRotationX = 150;
				itemDef.modelRotationY = 536;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffset2 = 3;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44761;
				itemDef.anInt165 = 44761;
			break;
			case 19131:
				itemDef.modelid = 44633;
				itemDef.name = "Vanguard helm";
				itemDef.description = "Its a Vanguard helm";
				itemDef.modelZoom = 855;
				itemDef.modelRotationY = 1966;
				itemDef.modelRotationX = 5;
				itemDef.modelOffset2 = 4;
				itemDef.modelOffset1 = -1;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44769;
				itemDef.anInt165 = 44769;
			break;
			case 19132:
				itemDef.modelid = 44627;
				itemDef.name = "Vanguard body";
				itemDef.description = "Its a Vanguard body";
				itemDef.modelZoom = 1513;
				itemDef.modelRotationX = 2041;
				itemDef.modelRotationY = 593;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = -11;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44812;
				itemDef.anInt165 = 44812;
			break;
			case 19133:
				itemDef.modelid = 44658;
				itemDef.name = "Vanguard legs";
				itemDef.description = "Its a Vanguard legs";
				itemDef.modelZoom = 1711;
				itemDef.modelRotationX = 0;
				itemDef.modelRotationY = 360;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = -11;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44771;
				itemDef.anInt165 = 44771;
			break;
			case 19134:
				itemDef.modelid = 44699;
				itemDef.name = "Vanguard gloves";
				itemDef.description = "Its a Vanguard glove";
				itemDef.modelZoom = 830;
				itemDef.modelRotationX = 0;
				itemDef.modelRotationY = 536;
				itemDef.modelOffset1 = 9;
				itemDef.modelOffset2 = 3;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44758;
				itemDef.anInt165 = 44758;
			break;
			case 19135:
				itemDef.modelid = 44700;
				itemDef.name = "Vanguard boots";
				itemDef.description = "Its a Vanguard boot";
				itemDef.modelZoom = 848;
				itemDef.modelRotationX = 141;
				itemDef.modelRotationY = 141;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffset2 = 0;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44752;
				itemDef.anInt165 = 44752;
			break;
			case 19136:
				itemDef.modelid = 44704;
				itemDef.name = "Battle-mage helm";
				itemDef.description = "Its a Battle-mage helm";
				itemDef.modelZoom = 658;
				itemDef.modelRotationX = 1898;
				itemDef.modelRotationY = 2;
				itemDef.modelOffset1 = 12;
				itemDef.modelOffset2 = 3;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44767;
				itemDef.anInt165 = 44767;
			break;
			case 19137:
				itemDef.modelid = 44631;
				itemDef.name = "Battle-mage robe";
				itemDef.description = "Its a Battle-mage robe";
				itemDef.modelZoom = 1382;
				itemDef.modelRotationX = 3;
				itemDef.modelRotationY = 488;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffset2 = 0;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44818;
				itemDef.anInt165 = 44818;
			break;
			case 19138:
				itemDef.modelid = 44672;
				itemDef.name = "Battle-mage robe legs";
				itemDef.description = "Its a Battle-mage legs";
				itemDef.modelZoom = 1842;
				itemDef.modelRotationX = 1024;
				itemDef.modelRotationY = 498;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffset2 = -1;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44775;
				itemDef.anInt165 = 44775;
			break;
			case 19139:
				itemDef.modelid = 44662;
				itemDef.name = "Battle-mage boots";
				itemDef.description = "Its a Battle-mage boot";
				itemDef.modelZoom = 987;
				itemDef.modelRotationX = 1988;
				itemDef.modelRotationY = 188;
				itemDef.modelOffset1 = -8;
				itemDef.modelOffset2 = 5;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44755;
				itemDef.anInt165 = 44755;
			break;
			case 19140:
				itemDef.modelid = 44573;
				itemDef.name = "Battle-mage gloves";
				itemDef.description = "Its a Battle-mage glove";
				itemDef.modelZoom = 1053;
				itemDef.modelRotationX = 0;
				itemDef.modelRotationY = 536;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffset2 = 0;
				itemDef.stackable = false;
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.anInt200 = 44762;
				itemDef.anInt165 = 44762;
			break;
	case 20051:
	itemDef.name = "Polypore staff";
	itemDef.description = "It's an Polypore staff";
	itemDef.itemActions = new String[] { null, "Wield", "Check", "Clean", "drop"};
	itemDef.groundActions = new String[] { null, null, "take", null, null};
	itemDef.modelid = 13426;
	itemDef.anInt165 = 13417;
	itemDef.anInt200 = 13417;
	itemDef.modelZoom = 3750;
	itemDef.modelRotationY = 1454;
	itemDef.modelRotationX = 997;
	itemDef.modelOffset1 = 0;
	itemDef.modelOffset2 = 8;
break;
	case 19141:
		itemDef.modelid = 10919;
		itemDef.name = "Aviator Poncho";
		itemDef.description = "It's a Aviator Poncho";
		itemDef.modelZoom = 1513;
		itemDef.modelRotationY = 384;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 10490;
		itemDef.anInt200 = 10490;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
	case 19142:
		itemDef.modelid = 10937;
		itemDef.name = "Aviator Leggings";
		itemDef.description = "It's a Aviator Leggings";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 474;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 10486;
		itemDef.anInt200 = 10486;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
	case 19153:
		itemDef.modelid = 10935;
		itemDef.name = "Aviator Visor";
		itemDef.description = "It's a Aviator Visor";
		itemDef.modelZoom = 789;
		itemDef.modelRotationY = 67;
		itemDef.modelRotationX = 175;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 10523;
		itemDef.anInt200 = 10523;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
		case 19154:
		itemDef.modelid = 62714;
		itemDef.name = "Torva full helm";
		itemDef.description = "Torva full helm.";
		itemDef.modelZoom = 672;
		itemDef.modelRotationY = 85;
		itemDef.modelRotationX = 1867;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -3;
		itemDef.anInt165 = 62738;
		itemDef.anInt200 = 62754;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		break;
				case 600:
						itemDef.name = "@cya@Book of Donations";
						itemDef.description = "An Conquest 317 Book of Donations.";
				break;


case 691:
						itemDef.name = "@red@25K Certificate";
						itemDef.description = "I should cash this in at home!";
				break;
case 692:
						itemDef.name = "@red@50K Certificate";
						itemDef.description = "I should cash this in at home!";
				break;
case 693:
						itemDef.name = "@red@75K Certificate";
						itemDef.description = "Yet another benefit of being a gold member! ^.^";
				break;


case 405:
						itemDef.name = "@red@Low Chance Casket";
						itemDef.description = "Chance your luck and open this casket!";
				break;
case 19039:
						itemDef.name = "@red@Best Chance Casket";
						itemDef.description = "Gold Member only benefit ^.^";
				break;
case 19040:
						itemDef.name = "@red@Medium Chance Casket";
						itemDef.description = "Chance your luck and open this casket!";
				break;
case 7956:
						itemDef.name = "@red@High Chance Casket";
						itemDef.description = "Chance your luck and open this casket!";
				break;

case 19155:
itemDef.modelid = 14129;
itemDef.name = "Owner cape";
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 572;
itemDef.modelRotationX = 0;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 14130;
itemDef.anInt200 = 14130;
itemDef.stackable = false;
itemDef.anInt175 = -1;
itemDef.anInt197 = -1;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.anInt204 = 0;
break;

                case 19156:
                itemDef.modelid = 6;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[4] = "Drop";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.anInt204 = 14;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffset2 = 1;
                itemDef.anInt165 = 7;
                itemDef.anInt200 = 7;
                itemDef.name = "@red@ Donator Cape @red@";
                itemDef.description = "Donator Cape";
                break;

                case 19158:
                itemDef.modelid = 4;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[4] = "Drop";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.anInt204 = 14;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffset2 = 1;
                itemDef.anInt165 = 5;
                itemDef.anInt200 = 5;
                itemDef.name = "@gre@ Donator Cape @gre@";
                itemDef.description = "Donator Cape";
                break;
case 19159:
		itemDef.modelid = 62701;
		itemDef.name = "Torva platelegs";
		itemDef.description = "Torva platelegs.";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 474;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62743;
		itemDef.anInt200 = 62760;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;



case 19160:
      itemDef.modelid = 3288;
      itemDef.name = "Death Cape";
      itemDef.modelZoom = 1385;
      itemDef.modelRotationY = 500;
      itemDef.modelRotationX = 2000;
      itemDef.modelOffset1 = 1;
      itemDef.modelOffset2 = -3;
      itemDef.stackable = false;
      itemDef.value = 1;
      itemDef.membersObject = true;
      itemDef.anInt165 = 3287;
      itemDef.anInt200 = 3287;
      itemDef.itemActions = new String[5];
      itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[2] = "Skull";
      itemDef.itemActions[4] = "Drop";
      itemDef.anInt175 = 14;
      itemDef.anInt197 = 7;
   break;

case 19161:
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.editedModelColor = new int[1];
		itemDef.newModelColor = new int[1];
		itemDef.editedModelColor[0] = 926;
		itemDef.newModelColor[0] = 1;
		itemDef.modelid = 2635;
		itemDef.modelZoom = 440;
		itemDef.modelRotationY = 76;
		itemDef.modelRotationX = 1850;
		itemDef.anInt204 = 0;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 187;
		itemDef.anInt200 = 363;
		itemDef.anInt188 = -1;
		itemDef.anInt164 = -1;
		itemDef.anInt175 = -1;
		itemDef.anInt197 = -1;
		itemDef.name = "Black partyhat";
		itemDef.description = "A nice hat from a cracker.";
		break;

case 19163:
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.editedModelColor = new int[1];
		itemDef.newModelColor = new int[1];
		itemDef.editedModelColor[0] = 926;
		itemDef.newModelColor[0] = 10;
		itemDef.modelid = 2635;
		itemDef.modelZoom = 440;
		itemDef.modelRotationY = 76;
		itemDef.modelRotationX = 1850;
		itemDef.anInt204 = 0;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 187;
		itemDef.anInt200 = 363;
		itemDef.anInt188 = -1;
		itemDef.anInt164 = -1;
		itemDef.anInt175 = -1;
		itemDef.anInt197 = -1;
		itemDef.name = "Bandos PartyHat";
		itemDef.description = "A partyhat from bandos.";
		break;

	case 19164:
		itemDef.modelid = 62699;
		itemDef.name = "Torva platebody";
		itemDef.description = "Torva Platebody.";
		itemDef.modelZoom = 1506;
		itemDef.modelRotationY = 473;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62746;
		itemDef.anInt200 = 62762;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

		case 19165:
		itemDef.modelid = 62693;
		itemDef.name = "Pernix cowl";
		itemDef.description = "Pernix cowl";
		itemDef.modelZoom = 800;
		itemDef.modelRotationY = 532;
		itemDef.modelRotationX = 14;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 1;
		itemDef.anInt165 = 62739;
		itemDef.anInt200 = 62756;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62731;
		itemDef.anInt197 = 62727;
	break;

	case 19166:
		itemDef.modelid = 62709;
		itemDef.name = "Pernix body";
		itemDef.description = "Pernix body";
		itemDef.modelZoom = 1378;
		itemDef.modelRotationY = 485;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 7;
		itemDef.anInt165 = 62744;
		itemDef.anInt200 = 62765;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;


	case 19468:
		itemDef.modelid = 62695;
		itemDef.name = "Pernix chaps";
		itemDef.description = "Pernix chaps";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 504;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = 4;
		itemDef.modelOffset2 = 3;
		itemDef.anInt165 = 62741;
		itemDef.anInt200 = 62757;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 19469:
		itemDef.modelid = 62710;
		itemDef.name = "Virtus mask";
		itemDef.description = "Virtus mask";
		itemDef.modelZoom = 928;
		itemDef.modelRotationY = 406;
		itemDef.modelRotationX = 2041;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62736;
		itemDef.anInt200 = 62755;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62728;
		itemDef.anInt197 = 62728;
	break;

	case 19470:
		itemDef.modelid = 62704;
		itemDef.name = "Virtus robe top";
		itemDef.description = "Virtus robe top";
		itemDef.modelZoom = 1122;
		itemDef.modelRotationY = 488;
		itemDef.modelRotationX = 3;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62748;
		itemDef.anInt200 = 62764;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 19471:
		itemDef.modelid = 62700;
		itemDef.name = "Virtus robe legs";
		itemDef.description = "Virtus robe legs";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 498;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 4;
		itemDef.anInt165 = 62742;
		itemDef.anInt200 = 62758;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 19472:
		itemDef.modelid = 13455;
		itemDef.name = "Cigarette";
		itemDef.description = "Don't Smoke!";
		itemDef.modelZoom = 300;
		itemDef.modelRotationY = 473;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 13456;
		itemDef.anInt200 = 13456;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Smoke";
		itemDef.itemActions[4] = "Drop";
	break;
case 19473:
itemDef.name = "Ragefire boots";
itemDef.modelid = 53897;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53330;
itemDef.anInt200 = 53330;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Ragefire boots.";
break;

case 19474:
itemDef.name = "Steadfast Boots";
itemDef.modelid = 53835;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53327;
itemDef.anInt200 = 53327;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Steadfast boots.";
break;

case 19810:
itemDef.modelid = 65262;
itemDef.name = "Max Cape";
itemDef.description = "For the masters of Conquest 317 only.";
itemDef.modelZoom = 1385;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 24;
itemDef.modelRotationY = 279;
itemDef.modelRotationX = 948;
itemDef.anInt165 = 65300;
itemDef.anInt200 = 65322;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
break;

case 19811:
itemDef.modelid = 65261;
itemDef.name = "Veteran Cape";
itemDef.description = "For the veterans of Conquest 317";
itemDef.modelZoom = 1385;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 24;
itemDef.modelRotationY = 279;
itemDef.modelRotationX = 948;
itemDef.anInt165 = 65305;
itemDef.anInt200 = 65318;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
break;

case 19812:
itemDef.modelid = 65270;
itemDef.name = "Completionist Cape";
itemDef.description = "For the skillers of Conquest 317";
itemDef.modelZoom = 1385;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 24;
itemDef.modelRotationY = 279;
itemDef.modelRotationX = 948;
itemDef.anInt165 = 65297;
itemDef.anInt200 = 65297;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
break;

case 19813:
itemDef.modelid = 65257;
itemDef.name = "Classic Cape";
itemDef.description = "Very old fashioned...";
itemDef.modelZoom = 1385;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 24;
itemDef.modelRotationY = 279;
itemDef.modelRotationX = 948;
itemDef.anInt165 = 65302;
itemDef.anInt200 = 65327;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
break; 

case 19815:
itemDef.name = "Glaiven boots";
itemDef.modelid = 53828;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53309;
itemDef.anInt200 = 53309;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Glaiven boots.";
break;
	case 19816:
		itemDef.modelid = 62694;
		itemDef.name = "Ancient ceremonial hood";
		itemDef.modelZoom = 980;
		itemDef.modelRotationY = 208;
		itemDef.modelRotationX = 220;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -18;
		itemDef.anInt165 = 62737;
		itemDef.anInt200 = 62753;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62730;
		itemDef.anInt197 = 62730;
	break;

	case 19817:
		itemDef.modelid = 62705;
		itemDef.name = "Ancient ceremonial top";
		itemDef.modelZoom = 1316;
		itemDef.modelRotationY = 477;
		itemDef.modelRotationX = 9;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 13;
		itemDef.anInt165 = 62745;
		itemDef.anInt200 = 62763;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 54;
	break;

case 19818:
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.editedModelColor = new int[1];
		itemDef.newModelColor = new int[1];
		itemDef.editedModelColor[0] = 926;
		itemDef.newModelColor[0] = 23970;
		itemDef.modelid = 2635;
		itemDef.modelZoom = 440;
		itemDef.modelRotationY = 76;
		itemDef.modelRotationX = 1850;
		itemDef.anInt204 = 0;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 187;
		itemDef.anInt200 = 363;
		itemDef.anInt188 = -1;
		itemDef.anInt164 = -1;
		itemDef.anInt175 = -1;
		itemDef.anInt197 = -1;
		itemDef.name = "Eli's PartyHat";
		itemDef.description = "A nice hat made for Eli.";
		break;

case 19820:
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.editedModelColor = new int[1];
		itemDef.newModelColor = new int[1];
		itemDef.editedModelColor[0] = 926;
		itemDef.newModelColor[0] = 57300;
		itemDef.modelid = 2635;
		itemDef.modelZoom = 440;
		itemDef.modelRotationY = 76;
		itemDef.modelRotationX = 1850;
		itemDef.anInt204 = 0;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 187;
		itemDef.anInt200 = 363;
		itemDef.anInt188 = -1;
		itemDef.anInt164 = -1;
		itemDef.anInt175 = -1;
		itemDef.anInt197 = -1;
		itemDef.name = "Justin's PartyHat";
		itemDef.description = "A nice hat made by Justin.";
		break;

	case 19821:
		itemDef.modelid = 62707;
		itemDef.name = "Ancient ceremonial legs";
		itemDef.modelZoom = 1828;
		itemDef.modelRotationY = 539;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62740;
		itemDef.anInt200 = 62759;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 40;
		itemDef.anInt196 = 30;
		itemDef.anInt184 = 100;
	break;

	case 19822:
		itemDef.modelid = 62697;
		itemDef.name = "Ancient ceremonial gloves";
		itemDef.modelZoom = 548;
		itemDef.modelRotationY = 618;
		itemDef.modelRotationX = 1143;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62735;
		itemDef.anInt200 = 62752;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;

case 19823:
		itemDef.name = "Gnome scarf";
		itemDef.modelid = 17125;
		itemDef.editedModelColor = new int [3];
		itemDef.newModelColor = new int [3];
		itemDef.editedModelColor[0] = 119;
		itemDef.newModelColor[0] = 7737;
		itemDef.editedModelColor[1] = 103;
		itemDef.newModelColor[1] = 7737;
		itemDef.editedModelColor[2] = 127;
		itemDef.newModelColor[2] = 7737;
		itemDef.modelZoom = 919;
		itemDef.modelRotationY = 595;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -7;
		itemDef.modelOffset2 = 8;
		itemDef.anInt165 = 17155;
		itemDef.anInt200 = 17157;
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
	break;

	case 19825:
		itemDef.name = "Gnome scarf";
		itemDef.modelid = 17125;
		itemDef.editedModelColor = new int [3];
		itemDef.newModelColor = new int [3];
		itemDef.editedModelColor[0] = 119;
		itemDef.newModelColor[0] = 725;
		itemDef.editedModelColor[1] = 103;
		itemDef.newModelColor[1] = 725;
		itemDef.editedModelColor[2] = 127;
		itemDef.newModelColor[2] = 725;
		itemDef.modelZoom = 919;
		itemDef.modelRotationY = 595;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -7;
		itemDef.modelOffset2 = 8;
		itemDef.anInt165 = 17155;
		itemDef.anInt200 = 17157;
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
	break;

	case 19826:
		itemDef.name = "Gnome scarf";
		itemDef.modelid = 17125;
		itemDef.editedModelColor = new int [3];
		itemDef.newModelColor = new int [3];
		itemDef.editedModelColor[0] = 119;
		itemDef.newModelColor[0] = -22250;
		itemDef.editedModelColor[1] = 103;
		itemDef.newModelColor[1] = -22250;
		itemDef.editedModelColor[2] = 127;
		itemDef.newModelColor[2] = -22250;
		itemDef.modelZoom = 919;
		itemDef.modelRotationY = 595;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -7;
		itemDef.modelOffset2 = 8;
		itemDef.anInt165 = 17155;
		itemDef.anInt200 = 17157;
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
	break;

	case 19827:
		itemDef.name = "Gnome scarf";
		itemDef.modelid = 17125;
		itemDef.editedModelColor = new int [3];
		itemDef.newModelColor = new int [3];
		itemDef.editedModelColor[0] = 119;
		itemDef.newModelColor[0] = 23970;
		itemDef.editedModelColor[1] = 103;
		itemDef.newModelColor[1] = 23970;
		itemDef.editedModelColor[2] = 127;
		itemDef.newModelColor[2] = 23970;
		itemDef.modelZoom = 919;
		itemDef.modelRotationY = 595;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -7;
		itemDef.modelOffset2 = 8;
		itemDef.anInt165 = 17155;
		itemDef.anInt200 = 17157;
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
	break;

	case 19828:
		itemDef.modelid = 62696;
		itemDef.name = "Ancient ceremonial boots";
		itemDef.modelZoom = 676;
		itemDef.modelRotationY = 63;
		itemDef.modelRotationX = 106;
		itemDef.modelOffset1 = 5;
		itemDef.modelOffset2 = -1;
		itemDef.anInt165 = 62734;
		itemDef.anInt200 = 62751;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;
}
		return itemDef;
	}
	
	public static void unpackConfig(NamedArchive streamLoader) {
		stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.dat"));
		Stream stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.idx"));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 50000];
		int i = 2;
		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ItemDef[10];
		for (int k = 0; k < 10; k++)
			cache[k] = new ItemDef();
	}

	public void setDefaults() {
		modelid = 0;
		name = null;
		description = null;
		editedModelColor = null;
		newModelColor = null;
		modelZoom = 2000;
		modelRotationY = 0;
		modelRotationX = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		itemActions = null;
		anInt165 = -1;
		anInt188 = -1;
		aByte205 = 0;
		anInt200 = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
		lendID = -1;
		lentItemID = -1;
	}
	
	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				modelid = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readString();
			else if (i == 4)
				modelZoom = stream.readUnsignedWord();
			else if (i == 5)
				modelRotationY = stream.readUnsignedWord();
			else if (i == 6)
				modelRotationX = stream.readUnsignedWord();
			else if (i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (i == 8) {
				modelOffset2 = stream.readUnsignedWord();
				if (modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if (i == 10)
				stream.readUnsignedWord();
			else if (i == 11)
				stackable = true;
			else if (i == 12)
				value = stream.readUnsignedWord();
			else if (i == 16)
				membersObject = true;
			else if (i == 23) {
				anInt165 = stream.readUnsignedWord();
				aByte205 = stream.readSignedByte();
			} else if (i == 24)
				anInt188 = stream.readUnsignedWord();
			else if (i == 25) {
				anInt200 = stream.readUnsignedWord();
				aByte154 = stream.readSignedByte();
			} else if (i == 26)
				anInt164 = stream.readUnsignedWord();
			else if (i >= 30 && i < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if (groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if (i >= 35 && i < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[i - 35] = stream.readString();
				if (itemActions[i - 35].equalsIgnoreCase("null"))
					itemActions[i - 35] = null;
			} else if (i == 40) {
				int j = stream.readUnsignedByte();
				editedModelColor = new int[j];
				newModelColor = new int[j];
				for (int k = 0; k < j; k++) {
					editedModelColor[k] = stream.readUnsignedWord();
					newModelColor[k] = stream.readUnsignedWord();
				}
			} else if (i == 78)
				anInt185 = stream.readUnsignedWord();
			else if (i == 79)
				anInt162 = stream.readUnsignedWord();
			else if (i == 90)
				anInt175 = stream.readUnsignedWord();
			else if (i == 91)
				anInt197 = stream.readUnsignedWord();
			else if (i == 92)
				anInt166 = stream.readUnsignedWord();
			else if (i == 93)
				anInt173 = stream.readUnsignedWord();
			else if (i == 95)
				anInt204 = stream.readUnsignedWord();
			else if (i == 97)
				certID = stream.readUnsignedWord();
			else if (i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if (i >= 100 && i < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if (i == 110)
				anInt167 = stream.readUnsignedWord();
			else if (i == 111)
				anInt192 = stream.readUnsignedWord();
			else if (i == 112)
				anInt191 = stream.readUnsignedWord();
			else if (i == 113)
				anInt196 = stream.readSignedByte();
			else if (i == 114)
				anInt184 = stream.readSignedByte() * 5;
			else if (i == 115)
				team = stream.readUnsignedByte();
			else if (i == 116)
				lendID = stream.readUnsignedWord();
			else if (i == 117)
				lentItemID = stream.readUnsignedWord();
		} while (true);
	}
	
	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}

	public Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return null;
		Model model = Model.method462(k);
		if (l != -1) {
			Model model_1 = Model.method462(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}

	public boolean method195(int j) {
		int k = anInt165;
		int l = anInt188;
		int i1 = anInt185;
		if (j == 1) {
			k = anInt200;
			l = anInt164;
			i1 = anInt162;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		if (i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = anInt165;
		int k = anInt188;
		int l = anInt185;
		if (i == 1) {
			j = anInt200;
			k = anInt164;
			l = anInt162;
		}
		if (j == -1)
			return null;
		Model model = Model.method462(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.method462(k);
				Model model_3 = Model.method462(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.method462(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		if (i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if (i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}
	
	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelid = itemDef.modelid;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		modelRotationX = itemDef.modelRotationX;
		anInt204 = itemDef.anInt204;
		modelOffset1 = itemDef.modelOffset1;
		modelOffset2 = itemDef.modelOffset2;
		editedModelColor = itemDef.editedModelColor;
		newModelColor = itemDef.newModelColor;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
		stackable = true;
	}
	
	public void toLend() {
		ItemDef itemDef = forID(lentItemID);
		itemActions = new String[5];
		modelid = itemDef.modelid;
		modelOffset1 = itemDef.modelOffset1;
		modelRotationX = itemDef.modelRotationX;
		modelOffset2 = itemDef.modelOffset2;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		anInt204 = itemDef.anInt204;
		value = 0;
		ItemDef itemDef_1 = forID(lendID);
		anInt166 = itemDef_1.anInt166;
		editedModelColor = itemDef_1.editedModelColor;
		anInt185 = itemDef_1.anInt185;
		anInt188 = itemDef_1.anInt188;
		anInt173 = itemDef_1.anInt173;
		anInt175 = itemDef_1.anInt175;
		groundActions = itemDef_1.groundActions;
		anInt165 = itemDef_1.anInt165;
		name = itemDef_1.name;
		anInt200 = itemDef_1.anInt200;
		membersObject = itemDef_1.membersObject;
		anInt197 = itemDef_1.anInt197;
		anInt164 = itemDef_1.anInt164;
		anInt162 = itemDef_1.anInt162;
		newModelColor = itemDef_1.newModelColor;
		team = itemDef_1.team;
		if (itemDef_1.itemActions != null) {
			for (int i_33_ = 0; i_33_ < 4; i_33_++)
				itemActions[i_33_] = itemDef_1.itemActions[i_33_];
		}
		itemActions[4] = "Discard";
	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];
			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lentItemID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) ((double) k3 * 1.5D);
		if (k > 0)
			k3 = (int) ((double) k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204, itemDef.modelRotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (sprite2.myPixels[i5 + j4 * 32] == 0)
					if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
		}
		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (sprite2.myPixels[j5 + k4 * 32] == 0)
						if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lentItemID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if (itemDef.stackable)
			sprite2.maxWidth = 33;
		else
			sprite2.maxWidth = 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public Model method201(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method201(1);
		}
		Model model = (Model) mruNodes2.insertFromCache(id);
		if (model != null)
			return model;
		model = Model.method462(modelid);
		if (model == null)
			return null;
		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method202(1);
		}
		Model model = Model.method462(modelid);
		if (model == null)
			return null;
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		return model;
	}

	public ItemDef() {
		id = -1;
	}

	public byte aByte154;
	public int value;
	public int[] editedModelColor;
	public int id;
	static MRUNodes mruNodes1 = new MRUNodes(100);
	public static MRUNodes mruNodes2 = new MRUNodes(50);
	public int[] newModelColor;
	public boolean membersObject;
	public int anInt162;
	public int certTemplateID;
	public int anInt164;
	public int anInt165;
	public int anInt166;
	public int anInt167;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public static ItemDef[] cache;
	public int anInt173;
	public int modelid;
	public int anInt175;
	public boolean stackable;
	public String description;
	public int certID;
	public static int cacheIndex;
	public int modelZoom;
	public static boolean isMembers = true;
	public static Stream stream;
	public int anInt184;
	public int anInt185;
	public int anInt188;
	public String itemActions[];
	public int modelRotationY;
	public int anInt191;
	public int anInt192;
	public int[] stackIDs;
	public int modelOffset2;
	public static int[] streamIndices;
	public int anInt196;
	public int anInt197;
	public int modelRotationX;
	public int anInt200;
	public int[] stackAmounts;
	public int team;
	public static int totalItems;
	public int anInt204;
	public byte aByte205;
	public int lendID;
	public int lentItemID;
}
