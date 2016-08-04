package server.model.players;

import server.util.Misc;
import server.Server;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		
		case 1337:
			sendOption2("PK'er Scoreboard", "Skiller Scoreboard");
			c.dialogueAction = 1337;
		break;

		case 200:
			sendNpcChat4("Damn i'm gettin' old...", "*spit*",
					"'Been waitin' fo' some 'ne wit' guts to help me...", "",
					c.talkingNpc, "Captain Donnie", 9764);
			c.nextChat = 201;
			break;
		case 201:
			sendNpcChat4("'Bunch o' half livin' nazi's 'fter me...",
					"Can y'all handle 'em fo' me?",
					"I c'n giv' y'all some stuff..", "",
					c.talkingNpc, "Captain Donnie", 9764);
			c.nextChat = 202;
			break;
case 185:
    sendNpcChat4("Hello, im Lottie im in charge of Exilium's Lottery.", "Entering costs 5 million gp but you could win up to", "200 million gp! you can enter up to 5 times per draw.", "Would you like to enter the lottery?", c.talkingNpc, "Lottie", 8206);
    c.nextChat = 186;
break;
case 186:
    sendOption2("Yes i would like to enter!", "No, Id rather not.");
    c.dialogueAction = 186;
break;
		case 202:
			sendOption2("Sure, i'll get them for you.",
					"Sorry sir, i'm sort of busy at the moment.");
			c.dialogueAction = 202;
			break;

		case 700:
			sendNpcChat2("Are you sure you want me to take you?", "Bork is very dangerous and you WILL lose items!", c.talkingNpc, "Anna Jones", 9828);
			c.nextChat = 701;	
		break;
		case 701:
			sendOption2("Hell yeah! Bork's a pussy!", "No...I'm the pussy...");
			c.dialogueAction = 90;
			c.nextChat = 0;	
		break;
		case 194:
			sendNpcChat3("Hello, I'm here to exchange your EXLp", "for various kinds of weapons, armour, and supplies.", "Would you like to exchange some points?", c.talkingNpc, "Party Pete", 9847);
			c.nextChat = 195;	
		break;
		case 196:
			sendNpcChat3("Hello, I'm here to exchange your EXLp", "for various kinds of weapons, armour, and supplies.", "Would you like to exchange some points?", c.talkingNpc, "Megan", 9847);
			c.nextChat = 197;
		break;
		case 195:
			sendOption2("Sure, let me see what you have in stock.", "No thanks, I'd like to save them.");
			c.dialogueAction = 91;
			c.nextChat = 0;
		break;
		case 197:
			sendOption2("Sure, let me see what you have in stock.", "No thanks, I'd like to save them.");
			c.dialogueAction = 92;
			c.nextChat = 0;
		break;
		case 198:
			sendNpcChat3("Hello, I am able to change your character's look", "whether it's your gender, skin color, or clothes!", "Would you like me to do that for you?", c.talkingNpc, "Make-over Mage", 9847);
			c.nextChat = 199;
		break;
		case 199:
			sendOption2("Yeah, my hair's getting a bit too long.", "Sorry, I'm sexy and I know it.");
			c.dialogueAction = 93;
			c.nextChat = 0;
		break;

		case 40:
			sendNpcChat3("I used to be an adventurer like you,", "then I took an arrow to the knee.", "Any rumors?", c.talkingNpc, "Rasolo", 9760);
			c.nextChat = 41;	
		break;

		case 41:
			sendOption2("What do you have in stock?", "Wait, this isn't Skyrim!");
			c.dialogueAction = 300;
			c.nextChat = 0;	
		break;

		case 42:
			sendNpcChat2("Take a look.", "I may supply more items in the future.", c.talkingNpc, "Rasolo", 9760);
			c.nextChat = 43;
		break;
		
		case 43:
			sendOption5("Pure Accessories", "Weapons and Armour", "Magic and Runes", "Archery", "Supplies");
			c.dialogueAction = 301;
			c.nextChat = 0;
		break;

		case 80:
sendStatement("Should I teleport you?");
c.nextChat = 81;
break;

case 81:
sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
c.dialogueAction = 27;
c.nextChat = 0;
break;
		
case 38:
sendStatement("Congratulations, you open the chest and got a reward!");
c.nextChat = 0;
break;

case 37:
sendStatement("You need at least 2 free inventory spaces and a crystal key.");
c.nextChat = 0;
break;		
		
		

		case 30:
			sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something.", "Would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei", 9847);
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;	
			break;
		case 50:
			sendOption2("Ardougne - Multi",  "Yanille - 1v1");
			c.dialogueAction = 50;
			break;
	case 51:
			sendOption2("Castle Pk (14)",  "UnNamed (34)");
			c.dialogueAction = 51;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20.","Thanks.", c.talkingNpc, "Mosol Rei", 9788);
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 20:
			sendOption4("Information", "Black Jack","Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("","Black Jack", "Five","");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4("The way we play this game is simple. The way you win is", 
					"You need to get a higher number than me and you win the", 
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", 
					c.talkingNpc, "~ Black Jack ~", 9847);
					c.nextChat = 22;
					break;

		case 22:
			sendNpcChat4("", 
					"If i get 22+ I bust and I lose. If you get 21 then you have black", 
					"jack and you win double of what you bet.",
					"", 
					c.talkingNpc, "~ Black Jack ~", 9847);
					c.nextChat = 0;
					break;

		case 23:
			sendNpcChat4("This is my own game which I made. It's pretty simple", 
					"and resembles poker but it's a lot different. The aim of this", 
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.", 
					c.talkingNpc, "~ Five ~", 9847);
					c.nextChat = 24;
					break;
		case 24:
			sendNpcChat4("", 
					"To play this game you need to bet 1,000,000 coins. You", 
					"can win a lot of good items but also lose a lot of cash.",
					"", 
					c.talkingNpc, "~ Five ~", 9847);
					c.nextChat = 0;
					break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena", 9760);
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena", 9847);
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena", 9760);
			c.nextChat = -1;
			break;
		case 2:
			sendOption2("Yes! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel", 9847);
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion", 9847);
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion", 9847);
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel", 9847);
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel", 9847);
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes, I would like an easier task.", "No, I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play!", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
               case 70:
			sendNpcChat2("Hello adventurer, would you like to teleport to a boss?", "Be careful, they are very dangerous!", c.talkingNpc, "Bonafido", 9760);
			c.nextChat = 71;
		break;
		case 94:
sendOption4("Dice (Up to 100)", "Dice (10 sided)", "Dice (6 sided)", "Dice (Black-Jack)");
c.dialogueAction = 16;
c.dialogueId = 94;
break;
		case 190:
			sendOption5("Godwars", "King Black Dragon (Wild)", "Dagganoth Kings", "Corporeal Beast", "More...");
			c.dialogueAction = 120;
			c.dialogueId = 190;
			c.teleAction = -1;
			c.nextChat = 0;
			break;
		case 191:
			sendOption5("Tormented Demons", "Icy Bones", "Balance Elemental", "Sea Troll Queen", "Even more...");
			c.dialogueAction = 121;
			c.dialogueId = 191;
			c.teleAction = -1;
			c.nextChat = 0;
		break;
		case 192:
			sendOption5("Nomad", "Bal'lak the Pummeller", "Giant Mole", "Kalphite Queen", "Even more again...");
			c.dialogueAction = 122;
			c.dialogueId = 192;
			c.teleAction = -1;
			c.nextChat = 0;
		break;
		case 193:
			sendOption5("Phoenix Lair", "Rammernauts", "Arzinian Avatars (Wild)", "@red@Revenants (Wild/Multi)@red@", "@red@Ancient Warriors@red@");
			c.dialogueAction = 123;
			c.dialogueId = 193;
			c.teleAction = -1;
			c.nextChat = 0;
		break;
			
		case 71:
			sendOption2("Giant Mole", "Kalphite Queen");
			c.dialogueAction = 13;
			c.dialogueId = 71;
			c.teleAction = -1;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}

	/*
	 * Editable Player animation chat heads
	 */
	
	private void sendPlayerChat1(String s, int A) {
		c.getPA().sendFrame200(969, A);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1, int A) {
		c.getPA().sendFrame200(974, A);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2, int A) {
		c.getPA().sendFrame200(980, A);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3, int A) {
		c.getPA().sendFrame200(987, A);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
	
	
	/*
	 * Editable NPC animation chat heads
	 */
	
	public void sendNpcChat1(String s, int ChatNpc, String name, int A) {
		c.getPA().sendFrame200(4883, A);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(ChatNpc, 4883);
		c.getPA().sendFrame164(4882);
	}
	
	public void sendNpcChat2(String s, String s1, int ChatNpc, String name, int A) {
		c.getPA().sendFrame200(4888, A);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name, int A) {
		c.getPA().sendFrame200(4894, A);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name, int A) {
		c.getPA().sendFrame200(4901, A);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
}
