package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.PlayerSave;
import server.model.players.Player;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.model.minigames.lottery;
import java.util.Locale;


import java.io.*;

/**
 * Commands
 **/
public class Commands implements PacketType {


    @
    Override
    public void processPacket(Client c, int packetType, int packetSize) {
        String playerCommand = c.getInStream().readString();
        if (!playerCommand.startsWith("/")) {
            c.getPA().writeCommandLog(playerCommand);
        }

        if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
            try {
                BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Reports.txt", true));
                String Report = playerCommand.substring(7);
                try {
                    report.newLine();
                    report.write(c.playerName + ": " + Report);
                    c.sendMessage("You have successfully submitted your report.");
                } finally {
                    report.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





        if (playerCommand.equals("goldmember")) {
            if (c.goldMember == true) {
                c.sendMessage("You are a Gold Member.");
            }
        }








        if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
            if (c.clanId >= 0) {
                System.out.println(playerCommand);
                playerCommand = playerCommand.substring(1).toLowerCase(Locale.ENGLISH);
                Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
            } else {
                if (c.clanId != -1)
                    c.clanId = -1;
                c.sendMessage("You are not in a clan.");
            }
            return;
        }
        if (Config.SERVER_DEBUG)
            Misc.println(c.playerName + " playerCommand: " + playerCommand);

        if (c.playerRights >= 0)
            playerCommands(c, playerCommand);
        if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3)
            moderatorCommands(c, playerCommand);
        if (c.playerRights == 2 || c.playerRights == 3)
            administratorCommands(c, playerCommand);
        if (c.playerRights == 3)
            ownerCommands(c, playerCommand);
        if (c.playerRights == 7 || c.playerRights == 2 || c.playerRights == 3)
            SupportCommands(c, playerCommand);
        if (c.playerRights == 4 || c.playerRights == 2 || c.playerRights == 3)
            DonatorCommands(c, playerCommand);

    }


    public void playerCommands(Client c, String playerCommand) {



        if (playerCommand.equals("vote")) {
            c.getPA().sendFrame126("http://www.rspscoding.org/toplist/vote.php?id=250", 12000);
            c.sendMessage("Please vote every 12 hours!");
        }
        if (playerCommand.equals("forums")) {
            c.getPA().sendFrame126("http://www.conquest-317.com", 12000);
            c.sendMessage("Welcome! Please register!!");
        }
        if (playerCommand.startsWith("settitle") && c.isDonator == 1) {
            String[] args = playerCommand.split(" ");
            if (args.length == 2) {
                int titleId = Integer.parseInt(args[1]);
                c.playerTitle = titleId;
                c.getPA().requestUpdates();
            }
            if (c.playerTitle == 1) {
                c.sendMessage("You have set your title to: <col=12273152>Lord</col>.");
                return;
            } else if (c.playerTitle == 2) {
                c.sendMessage("You have set your title to: <col=12273152>Sir</col>.");
                return;
            } else if (c.playerTitle == 3) {
                c.sendMessage("You have set your title to: <col=12273152>Lionheart</col>.");
                return;
            } else if (c.playerTitle == 4) {
                c.sendMessage("You have set your title to: <col=9044149>Donator</col>.");
                return;
            } else if (c.playerTitle == 5) {
                c.sendMessage("You have set your title to: <col=12273152>Bandito</col>.");
                return;
            } else if (c.playerTitle == 6) {
                c.sendMessage("You have set your title to: <col=12273152>King</col>.");
                return;
            } else if (c.playerTitle == 7) {
                c.sendMessage("You have set your title to: <col=12273152>Big Cheese</col>.");
                return;
            } else if (c.playerTitle == 8) {
                c.sendMessage("You have set your title to: <col=12273152>Wunderkind</col>.");
                return;
            } else if (c.playerTitle == 9) {
                c.sendMessage("You have set your title to: <col=12273152>Crusader</col>.");
                return;
            } else if (c.playerTitle == 10) {
                c.sendMessage("You have set your title to: <col=9044149>Overlord</col>.");
                return;
            } else if (c.playerTitle == 11 && c.playerName.equalsIgnoreCase("Justin")) {
                c.sendMessage("You have set your title to: <col=9044149>PedoBear</col>.");
                return;
            } else if (c.playerTitle == 11 && c.playerName.equalsIgnoreCase("Michael")) {
                c.sendMessage("You have set your title to: <col=9044149>PedoBear</col>.");
                return;
            } else if (c.playerTitle == 12) {
                c.sendMessage("You have set your title to: <col=12320768>Count</col>.");
                return;
            } else if (c.playerTitle == 13 && c.playerLevel[0] == 99 && c.playerLevel[1] == 99 && c.playerLevel[2] == 99 && c.playerLevel[3] == 99 && c.playerLevel[4] == 99 && c.playerLevel[5] == 99 && c.playerLevel[6] == 99 && c.playerLevel[7] == 99 && c.playerLevel[8] == 99 && c.playerLevel[9] == 99 && c.playerLevel[10] == 99 && c.playerLevel[11] == 99 && c.playerLevel[12] == 99 && c.playerLevel[13] == 99 && c.playerLevel[14] == 99 && c.playerLevel[15] == 99 && c.playerLevel[16] == 99 && c.playerLevel[17] == 99 && c.playerLevel[18] == 99 && c.playerLevel[19] == 99 && c.playerLevel[20] == 99 && c.playerLevel[21] == 99 && c.playerLevel[22] == 99) {
                c.sendMessage("You have set your title to: <col=12320768>Legend</col>.");
                return;
            } else if (c.playerTitle == 14) {
                c.sendMessage("You have set your title to: <col=12320768>Hell Raiser</col>.");
                return;
            } else if (c.playerTitle == 15) {
                c.sendMessage("You have set your title to: <col=9044149>Baron</col>.");
                return;
            } else if (c.playerTitle == 16) {
                c.sendMessage("You have set your title to: <col=12273152>Duke</col>.");
                return;
            } else if (c.playerTitle == 17) {
                c.sendMessage("You have set your title to: <col=12273152>Lady</col>.");
                return;
            } else if (c.playerTitle == 18) {
                c.sendMessage("You have set your title to: <col=12273152>The Great</col>.");
                return;
            } else if (c.playerTitle == 19) {
                c.sendMessage("You have set your title to: <col=12273152>Dudette</col>.");
                return;
            } else if (c.playerTitle == 20) {
                c.sendMessage("You have set your title to: <col=12273152>Baroness</col>.");
                return;
            } else if (c.playerTitle == 21) {
                c.sendMessage("You have set your title to: <col=12273152>Countess</col>.");
                return;
            } else if (c.playerTitle == 22) {
                c.sendMessage("You have set your title to: <col=12273152>Overlordess</col>.");
                return;
            } else if (c.playerTitle == 23) {
                c.sendMessage("You have set your title to: <col=12273152>Duchess</col>.");
                return;
            } else if (c.playerTitle == 24) {
                c.sendMessage("You have set your title to: <col=12273152>Queen</col>.");
                return;
            } else if (c.playerTitle == 25) {
                c.sendMessage("You have set your title to: <col=12320768>Private</col>.");
                return;
            } else if (c.playerTitle == 29 && c.playerName.equalsIgnoreCase("Justin")) {
                c.sendMessage("You have set your title to: <col=12273152>Super</col>.");
                return;
            } else if (c.playerTitle == 30) {
                c.sendMessage("You have set your title to: <col=12320768>Royal</col>.");
                return;
            } else if (c.playerTitle == 32 && c.playerName.equalsIgnoreCase("justin")) {
                c.sendMessage("You have set your title to: <col=9044149>Support</col>.");
                return;
            } else if (c.playerTitle == 33) {
                c.sendMessage("You have set your title to: <col=12320768>Donator</col>.");
                return;
            } else if (c.playerTitle == 34 && c.playerName.equalsIgnoreCase("Justin")) {
                c.sendMessage("You have set your title to: <col=12320768>Nerd</col>.");
                return;
            } else if (c.playerTitle == 34 && c.playerName.equalsIgnoreCase("Michael")) {
                c.sendMessage("You have set your title to: <col=12320768>Nerd</col>.");
                return;
            } else if (c.playerTitle == 35) {
                c.sendMessage("You have set your title to: <col=12320768>Master</col>.");
                return;
            } else if (c.playerTitle == 36) {
                c.sendMessage("You have set your title to: <col=12273152>Miss</col>.");
                return;
            } else if (c.playerTitle == 37) {
                c.sendMessage("You have set your title to: <col=12273152>The Awesome</col>.");
                return;
            } else if (c.playerTitle == 38) {
                c.sendMessage("You have set your title to: <col=12273152>The Real</col>.");
                return;
            } else if (c.playerTitle == 39) {
                c.sendMessage("You have set your title to: <col=12273152>The Untouchable</col>.");
                return;
            } else if (c.playerTitle == 40) {
                c.sendMessage("You have set your title to: <col=12273152>Outrageous</col>.");
                return;
            } else if (c.playerTitle == 41 && c.playerLevel[16] == 99) {
                c.sendMessage("You have set your title to: <col=12273152>Athlete</col>.");
                return;
            } else if (c.playerTitle == 42) {
                c.sendMessage("You have set your title to: <col=9044149>The</col>.");
                return;
            } else if (c.playerTitle == 43) {
                c.sendMessage("You have set your title to: <col=12273152>Junior</col>.");
                return;
            } else if (c.playerTitle == 44) {
                c.sendMessage("You have set your title to: <col=12273152>Mr</col>.");
                return;
            } else if (c.playerTitle == 45) {
                c.sendMessage("You have set your title to: <col=12273152>Mrs</col>.");
                return;
            } else if (c.playerTitle == 46) {
                c.sendMessage("You have set your title to: <col=9044149>Esquire</col>.");
                return;
            } else if (c.playerTitle == 47) {
                c.sendMessage("You have set your title to: <col=12273152>Ms</col>.");
                return;
            } else if (c.playerTitle == 48) {
                c.sendMessage("You have set your title to: <col=12273152>Doctor</col>.");
                return;
            } else if (c.playerTitle == 49) {
                c.sendMessage("You have set your title to: <col=12320768>The Adorable</col>.");
                return;
            } else if (c.playerTitle == 50) {
                c.sendMessage("You have set your title to: <col=12273152>Cheerful</col>.");
                return;
            } else if (c.playerTitle == 51) {
                c.sendMessage("You have set your title to: <col=9044149>Grumpy</col>.");
                return;

            } else if (c.playerTitle == 68) {
                c.sendMessage("You have set your title to: <col=12273152>Skiller</col>.");
                return;
            } else if (c.playerTitle == 69 && c.playerLevel[10] == 99) {
                c.sendMessage("You have set your title to: <col=9044149>MasterBaiter</col>.");
                return;
            } else if (c.playerTitle == 70) {
                c.sendMessage("You have set your title to: <col=9044149>Sergeant</col>.");
                return;
            } else if (c.playerTitle == 71) {
                c.sendMessage("You have set your title to: <col=9044149>Lieutenant</col>.");
                return;
            } else if (c.playerTitle == 72) {
                c.sendMessage("You have set your title to: <col=9044149>Commander</col>.");
                return;
            } else if (c.playerTitle == 73) {
                c.sendMessage("You have set your title to: <col=9044149>Private</col>.");
                return;

            } else if (c.playerTitle == 84) {
                c.sendMessage("You have set your title to: <col=12273152>Spartan</col>.");
                return;

            } else if (c.playerTitle == 86) {
                c.sendMessage("You have set your title to: <col=9044149>The Mystical</col>.");
                return;
            } else if (c.playerTitle == 87) {
                c.sendMessage("You have set your title to: <col=12320768>Super Saiyan</col>.");
                return;
            } else if (c.playerTitle == 88) {
                c.sendMessage("You have set your title to: <col=12320768>SSJ</col>.");
                return;
            } else if (c.playerTitle == 89) {
                c.sendMessage("You have set your title to: <col=12320768>SSJ2</col>.");
                return;
            } else if (c.playerTitle == 90) {
                c.sendMessage("You have set your title to: <col=12320768>SSJ3</col>.");
                return;
            } else if (c.playerTitle == 91) {
                c.sendMessage("You have set your title to: <col=12320768>SSJ4</col>.");
                return;

            } else if (c.playerTitle == 98) {
                c.sendMessage("You have set your title to: <col=12273152>Yolo</col>.");
                return;

            } else if (c.playerTitle == 26 && c.playerRights >= 1) {
                c.sendMessage("You have set your title to: <col=9044149>Mod</col>.");
                return;
            } else if (c.playerTitle == 27 && c.playerRights >= 2) {
                c.sendMessage("You have set your title to: <col=9044149>Admin</col>.");
                return;
            } else if (c.playerTitle == 28 && c.playerRights == 3) {
                c.sendMessage("You have set your title to: <col=9044149>Owner</col>.");
                return;

            } else {
                c.sendMessage("You do not have this title unlocked. Your title has been reset.");
                c.playerTitle = 0;
                return;

            }
        }


        if (playerCommand.equalsIgnoreCase("players")) {
            c.sendMessage("There are currently " + PlayerHandler.getPlayerCount() + " players online.");
            c.getPA().sendFrame126(Config.SERVER_NAME + " - Online Players", 8144);
            c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount() + "):", 8145);
            int line = 8147;
            for (int i = 1; i < Config.MAX_PLAYERS; i++) {
                Client p = c.getClient(i);
                if (!c.validClient(i))
                    continue;
                if (p.playerName != null) {
                    String title = "";
                    if (p.playerRights == 1) {
                        title = "@whi@Mod, ";
                    } else if (p.playerRights == 2) {
                        title = "@yel@Admin, ";
                    } else if (p.playerRights == 3) {
                        title = "@blu@Owner, ";
                    } else if (p.playerRights == 4) {
                        title = "@red@Donator, ";
                    }
                    title += "level-" + p.combatLevel;
                    String extra = "";
                    if (c.playerRights > 0) {
                        extra = "(" + p.playerId + ") ";
                    }
                    c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ (" + title + ") @dre@Kills: @dbl@ " + p.KC + ",  @dre@Deaths: @dbl@" + p.DC, line);
                    line++;
                }
            }
            c.getPA().showInterface(8134);
            c.flushOutStream();
        }
        if (playerCommand.startsWith("rules")) {
            c.sendMessage("1.Do not ask staff for items (MUTE).");
            c.sendMessage("2.No rules in pking, (Gbs/stake fights are your own risk)");
            c.sendMessage("3.Do not use offensive Language. (MUTE)");
            c.sendMessage("4.Do not Scam Passwords or Items (IPBAN)");
            c.sendMessage("5.Auto clickers and auto typers are NOT aloud.(BAN)");
        }
        if (playerCommand.startsWith("commands")) {
            c.sendMessage("Conquest-317 Commands:");
            c.sendMessage("::yell, ,::changepassword, ::players, ::forums, ::vote");

        }

        if (playerCommand.startsWith("cleartitle") && c.isDonator == 1) {
            c.sendMessage("<shad=255>You have reset your Player title!</col>");
            c.playerTitle = 0;
        }

        if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
            c.playerPass = playerCommand.substring(15);
            c.sendMessage("Your password is now: " + c.playerPass);
        }

        if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP") || playerCommand.startsWith("eP")) {
            c.sendMessage("EP: " + c.earningPotential + "");
        }
        if (playerCommand.startsWith("yell")) {
            /*
             *This is the sensor for the yell command
             */
            /*String text = playerCommand.substring(5);
            String[] bad = {"chalreq", "duelreq", "tradereq", ". com", "c0m", "com", 
            		"org", "net", "biz", ". net", ". org", ". biz", 
            		". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
            		".com", ".net", ".org", "no-ip", "****", "is gay", "****",
            		"crap", "rubbish", ". com", ". serve", ". no-ip", ". net", ". biz"};
            for(int i = 0; i < bad.length; i++){
            	if(text.indexOf(bad[i]) >= 0){
            		return;
            	}
            }*/
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client) Server.playerHandler.players[j];




                    if (c.playerName.equalsIgnoreCase("justin")) {
                        c2.sendMessage("<col=17407><shad=54783>[Founder]<img=2>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "</col></shad>");
                    } else if (c.isDonator == 4) {
                        c2.sendMessage("<shad=10906535>[Forum Mod]</col><img=1>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.playerRights == 1) {
                        c2.sendMessage("<shad=10906535>[Moderator]</col><img=1>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.playerRights == 2) {
                        c2.sendMessage("<col=255><shad=15695415>[Administrator]</col><img=2>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.playerRights == 3) {
                        c2.sendMessage("<col=255><shad=15695415>[Owner]</col><img=2>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.isDonator == 1) {
                        c2.sendMessage("<col=13238272><shad=16711680>[Donator]<img=0>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "</col></shad>");
                    } else if (c.isDonator == 2) {
                        c2.sendMessage("<shad=65535>[Super Donator]</col><img=0>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.playerName.equalsIgnoreCase("haribo")) {
                        c2.sendMessage("<shad=65535><col=65535><img=0>[Veteran]</shad></col>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.isDonator == 3) {
                        c2.sendMessage("<shad=14981889>[Extreme Donator]</col><img=0>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");
                    } else if (c.playerRights == 0) {
                        c2.sendMessage("<shad=255>[Player]</shad>" + Misc.optimizeText(c.playerName) + ": " + Misc.optimizeText(playerCommand.substring(5)) + "");

                    }
                }
            }
        }
    }

    public void moderatorCommands(Client c, String playerCommand) {
        if (playerCommand.startsWith("jail")) {
            try {
                String playerToBan = playerCommand.substring(5);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.teleportToX = 3102;
                            c2.teleportToY = 9516;
                            c2.Jail = true;
                            c2.sendMessage("You have been jailed by " + c.playerName + "");
                            c.sendMessage("Successfully Jailed " + c2.playerName + ".");
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("emote") && c.playerRights >= 2) {
            String[] args = playerCommand.split(" ");
            c.startAnimation(Integer.parseInt(args[1]));
            c.getPA().requestUpdates();
        }



        if (playerCommand.startsWith("mute")) {
            try {
                String playerToBan = playerCommand.substring(5);
                Connection.addNameToMuteList(playerToBan);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + c.playerName);
                            c.sendMessage("You have muted: " + c2.playerName);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("xteletome") && c.playerRights >= 1) {
            try {
                String playerToTele = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been teleported to " + c.playerName);
                            c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }


        if (playerCommand.startsWith("xteleto") && c.playerRights >= 1) {
            String name = playerCommand.substring(8);
            for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                if (Server.playerHandler.players[i] != null) {
                    if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
                        c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
                    }
                }
            }
        }

        if (playerCommand.startsWith("unmute")) {
            try {
                String playerToBan = playerCommand.substring(7);
                Connection.unMuteUser(playerToBan);
                c.sendMessage("Unmuted.");
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("checkbank")) {
            String[] args = playerCommand.split(" ");
            for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                Client o = (Client) Server.playerHandler.players[i];
                if (Server.playerHandler.players[i] != null) {
                    if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                        c.getPA().otherBank(c, o);
                        break;
                    }
                }
            }
        }
        if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
            try {
                String playerToBan = playerCommand.substring(5);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Server.playerHandler.players[i].disconnected = true;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
            try {
                String playerToBan = playerCommand.substring(4);
                Connection.addNameToBanList(playerToBan);
                Connection.addNameToFile(playerToBan);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Server.playerHandler.players[i].disconnected = true;
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage(" " + c2.playerName + " Got Banned By " + c.playerName + ".");
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("unban")) {
            try {
                String playerToBan = playerCommand.substring(6);
                Connection.removeNameFromBanList(playerToBan);
                c.sendMessage(playerToBan + " has been unbanned.");
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("unjail")) {
            try {
                String playerToBan = playerCommand.substring(7);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.teleportToX = 2844;
                            c2.teleportToY = 10209;
                            c2.monkeyk0ed = 0;
                            c2.Jail = false;
                            c2.sendMessage("You have been unjailed by " + c.playerName + ".");
                            c.sendMessage("Successfully unjailed " + c2.playerName + ".");
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

    }

    public void administratorCommands(Client c, String playerCommand) {
        if (playerCommand.startsWith("alert")) {
            String msg = playerCommand.substring(6);
            for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                if (Server.playerHandler.players[i] != null) {
                    Client c2 = (Client) Server.playerHandler.players[i];
                    c2.sendMessage("Alert##Notification##" + msg + "##By: " + c.playerName);

                }
            }
        }
        if (playerCommand.startsWith("getid")) {

            String a[] = playerCommand.split(" ");

            String name = "";

            int results = 0;

            for (int i = 1; i < a.length; i++)

                name = name + a[i] + " ";

            name = name.substring(0, name.length() - 1);

            c.sendMessage("<shad=255>Searching: " + name + "</shad>");
            for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {

                if (Server.itemHandler.ItemList[j] != null)

                    if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {

                        c.sendMessage("<col=232>"

                            + Server.itemHandler.ItemList[j].itemName.replace("_", " ")

                            + " - "

                            + Server.itemHandler.ItemList[j].itemId);

                        results++;

                    }

            }

            c.sendMessage("<shad=255>" + results + " results found...</shad>");

        }
        if (playerCommand.equalsIgnoreCase("master")) {
            for (int i = 0; i < 27; i++) {
                c.playerLevel[i] = 99;
                c.playerXP[i] = c.getPA().getXPForLevel(100);
                c.getPA().refreshSkill(i);
            }
            c.getPA().requestUpdates();
        }
        if (playerCommand.startsWith("empty")) {
            c.getItems().removeAllItems();
            c.sendMessage("<shad=6081134>You empty your inventory.</col>");
        }
        if (playerCommand.startsWith("ipmute")) {
            try {
                String playerToBan = playerCommand.substring(7);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have IP Muted the user: " + Server.playerHandler.players[i].playerName);
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + c.playerName);
                            c2.sendMessage(" " + c2.playerName + " Got IpMuted By " + c.playerName + ".");
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }



        if (playerCommand.equalsIgnoreCase("mypos")) {
            c.sendMessage("X: " + c.absX + " Y: " + c.absY + " H: " + c.heightLevel);
        }

        if (playerCommand.startsWith("interface")) {
            String[] args = playerCommand.split(" ");
            c.getPA().showInterface(Integer.parseInt(args[1]));
        }

        if (playerCommand.startsWith("gfx")) {
            String[] args = playerCommand.split(" ");
            c.gfx0(Integer.parseInt(args[1]));
        }
        if (playerCommand.startsWith("tele")) {
            String[] arg = playerCommand.split(" ");
            if (arg.length > 3)
                c.getPA().movePlayer(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
            else if (arg.length == 3)
                c.getPA().movePlayer(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), c.heightLevel);
        }
        if (playerCommand.equalsIgnoreCase("bank") && c.playerRights >= 2) {
            c.getPA().openUpBank();
        }
        if (playerCommand.startsWith("unipmute")) {
            try {
                String playerToBan = playerCommand.substring(9);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have Un Ip-Muted the user: " + Server.playerHandler.players[i].playerName);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("@red@Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("bpoints")) {
            c.BossPoints = c.BossPoints + 999999;
            c.sendMessage("Must be nice dude..");
        }
        if (playerCommand.startsWith("slaypts")) {
            c.SlayPts = c.SlayPts + 999999;
            c.sendMessage("Must be nice dude..");
        }

        if (playerCommand.startsWith("item")) {
            try {
                String[] args = playerCommand.split(" ");
                if (args.length == 3) {
                    int newItemID = Integer.parseInt(args[1]);
                    int newItemAmount = Integer.parseInt(args[2]);
                    if ((newItemID <= 20500) && (newItemID >= 0)) {
                        c.getItems().addItem(newItemID, newItemAmount);
                    } else {
                        c.sendMessage("<shad=505>That item ID does not exist.</col>");
                    }
                } else {
                    c.sendMessage("<shad=505>Wrong usage: (Ex:(::item 995 1))</col>");
                }
            } catch (Exception e) {

            } // HERE?
        } // HERE?
        if (playerCommand.startsWith("snpc") && playerCommand.length() > 2) {
            try {
                BufferedWriter npcspawn = new BufferedWriter(new FileWriter(
                    "./Data/cfg/spawn-config.cfg", true));
                String[] args = playerCommand.split(" ");
                String npcid = args[1];
                String face = args[2];
                String name = args[3];
                try {
                    npcspawn.write("spawn =	" + npcid + "	" + c.absX + "	" + c.absY + "	" + c.heightLevel + "	" + face + "	0	0	0" + "	" + name);
                    c.sendMessage("@gre@You have successfully spawned an npc.");
                    npcspawn.newLine();
                } finally {
                    npcspawn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (playerCommand.startsWith("ipban")) {
            try {
                String playerToBan = playerCommand.substring(6);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
                            Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have IP banned the user: " + Server.playerHandler.players[i].playerName + " with the host: " + Server.playerHandler.players[i].connectedFrom);
                            Client c2 = (Client) Server.playerHandler.players[i];
                            Server.playerHandler.players[i].disconnected = true;
                            c2.sendMessage(" " + c2.playerName + " Got IpBanned By " + c.playerName + ".");
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("@red@Player Must Be Offline.");
            }
        }


    }

    public void ownerCommands(Client c, String playerCommand) {

        if (playerCommand.startsWith("update") && c.playerName.equalsIgnoreCase("justin")) {
            String[] args = playerCommand.split(" ");
            int a = Integer.parseInt(args[1]);
            PlayerHandler.updateSeconds = a;
            PlayerHandler.updateAnnounced = false;
            PlayerHandler.updateRunning = true;
            PlayerHandler.updateStartTime = System.currentTimeMillis();
        }

        /*if(playerCommand.startsWith("getid")) {

                             String a[] = playerCommand.split(" ");

                             String name = "";

                             int results = 0;

                             for(int i = 1; i < a.length; i++)

                                     name = name + a[i]+ " ";

                             name = name.substring(0, name.length()-1);

                             c.sendMessage("Searching: " + name);

                             for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {

                                      if (Server.itemHandler.ItemList[j] != null)

                                              if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(name.toLowerCase())) {

                                                      c.sendMessage("<shad=232>"

                                                                      + Server.itemHandler.ItemList[j].itemName.replace("_", " ")

                                                                      + " - "

                                                                      + Server.itemHandler.ItemList[j].itemId);

                                                      results++;

                                               }

                                    }

                              c.sendMessage(results + " results found...");

                             }*/

        if (playerCommand.startsWith("giveitem") && c.playerName.equalsIgnoreCase("Justin")) {

            try {
                String[] args = playerCommand.split(" ");
                int newItemID = Integer.parseInt(args[1]);
                int newItemAmount = Integer.parseInt(args[2]);
                String otherplayer = args[3];
                Client c2 = null;
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
                            c2 = (Client) Server.playerHandler.players[i];
                            break;
                        }
                    }
                }
                if (c2 == null) {
                    c.sendMessage("Player doesn't exist.");
                    return;
                }
                c.sendMessage("You have just given " + server.model.items.Item.getItemName(newItemID) + "X" + newItemAmount + " to " + c2.playerName + ".");
                c2.getItems().addItem(newItemID, newItemAmount);
            } catch (Exception e) {
                c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
            }
        }
        if (playerCommand.startsWith("givedp") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                final String[] args = playerCommand.split(" ");
                final String otherplayer = args[1];
                final int point = Integer.parseInt(args[2]);
                for (final Player player: PlayerHandler.players) {
                    if (player != null) {
                        if (player.playerName.equalsIgnoreCase(otherplayer)) {
                            final Client c2 = (Client) player;
                            c2.donorPoints += point; //CHANGE THIS TO YOUR DONOR POINTS VARIABLE
                            c.sendMessage("<shad=24344>You have given " + otherplayer + ", " + point + " Donor points.");
                            c2.sendMessage("<shad=24344>You have been given " + point + " Donor points by " + c.playerName + ".");
                        }
                    }
                }
            } catch (final Exception e) {
                c.sendMessage("Wrong syntax! ::givedp name amount");
            }
        }
        if (playerCommand.startsWith("givesp") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                final String[] args = playerCommand.split(" ");
                final String otherplayer = args[1];
                final int point = Integer.parseInt(args[2]);
                for (final Player player: PlayerHandler.players) {
                    if (player != null) {
                        if (player.playerName.equalsIgnoreCase(otherplayer)) {
                            final Client c2 = (Client) player;
                            c2.SPoints += point; //CHANGE THIS TO YOUR POINTS VARIABLE
                            c.sendMessage("<shad=24344>You have given " + otherplayer + ", " + point + " Slayer points.");
                            c2.sendMessage("<shad=24344>You have been given " + point + " Slayer points by " + c.playerName + ".");
                        }
                    }
                }
            } catch (final Exception e) {
                c.sendMessage("Wrong syntax! ::givesp name amount");
            }
        }
        if (playerCommand.startsWith("givelvlp") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                final String[] args = playerCommand.split(" ");
                final String otherplayer = args[1];
                final int point = Integer.parseInt(args[2]);
                for (final Player player: PlayerHandler.players) {
                    if (player != null) {
                        if (player.playerName.equalsIgnoreCase(otherplayer)) {
                            final Client c2 = (Client) player;
                            c2.lvlPoints += point;
                            c.sendMessage("<shad=24344>You have given " + otherplayer + ", " + point + " Level Points");
                            c2.sendMessage("<shad=24344>You have been given " + point + " Level Points " + c.playerName + ".");
                        }
                    }
                }
            } catch (final Exception e) {
                c.sendMessage("Wrong syntax! ::givelvlp name amount");
            }
        }

        if (playerCommand.startsWith("givetitle") && c.playerName.equalsIgnoreCase("Justin")) {
            if (c.playerRights != 3)
                return;
            try {
                String[] args = playerCommand.split(" ");
                if (args.length == 3) {
                    int titleId = Integer.parseInt(args[1]);
                    String playerToGiveTitle = args[2];
                    for (int i = 0; i < PlayerHandler.players.length; i++) {
                        if (PlayerHandler.players[i] != null) {
                            if (PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToGiveTitle)) {
                                Client o = (Client) PlayerHandler.players[i];
                                c.sendMessage("You have given " + Misc.optimizeText(o.playerName) + " a title.");
                                o.sendMessage("You have been give a title by " + Misc.optimizeText(c.playerName) + ".");
                                o.playerTitle = titleId;
                                o.getPA().requestUpdates();
                                return;
                            }
                        }
                    }
                } else {
                    c.sendMessage("Wrong usage of this command!");
                }
            } catch (Exception e) {
                c.sendMessage("Player must be offline.");
            }
        }
        if (playerCommand.startsWith("resettitle") && c.playerName.equalsIgnoreCase("Justin")) {
            if (c.playerRights != 3)
                return;
            try {
                String[] args = playerCommand.split(" ");
                if (args.length == 3) {
                    int titleId = Integer.parseInt(args[1]);
                    String playerToGiveTitle = args[2];
                    for (int i = 0; i < PlayerHandler.players.length; i++) {
                        if (PlayerHandler.players[i] != null) {
                            if (PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToGiveTitle)) {
                                Client o = (Client) PlayerHandler.players[i];
                                c.sendMessage("You have reset " + Misc.optimizeText(o.playerName) + "'s title.");
                                o.sendMessage("Your title has been reset by " + Misc.optimizeText(c.playerName) + ".");
                                o.playerTitle = 0;
                                o.getPA().requestUpdates();
                                return;
                            }
                        }
                    }
                } else {
                    c.sendMessage("Wrong usage of this command!");
                }
            } catch (Exception e) {
                c.sendMessage("Player must be offline.");
            }
        }



        if (playerCommand.startsWith("empty")) {
            c.getItems().removeAllItems();
            c.sendMessage("<shad=6081134>You empty your inventory.</col>");
        }


        if (playerCommand.startsWith("npc") && c.playerRights >= 2) {
            try {
                int newNPC = Integer.parseInt(playerCommand.substring(4));
                if (newNPC > 0) {
                    Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
                    c.sendMessage("You spawn a Npc.");
                } else {
                    c.sendMessage("No such NPC.");
                }
            } catch (Exception e) {

            }
        }

        if (playerCommand.equalsIgnoreCase("infspec") && c.playerRights >= 2) {
            c.getItems().updateSpecialBar();
            c.gfx0(2015);
            c.specAmount = 100000000;
        }
        if (playerCommand.equalsIgnoreCase("infhp") && c.playerRights >= 2) {
            c.getPA().requestUpdates();
            c.playerLevel[3] = 99999999;
            c.getPA().refreshSkill(3);
            c.gfx0(754);
        }
        if (playerCommand.equalsIgnoreCase("infpray") && c.playerRights >= 2) {
            c.getPA().requestUpdates();
            c.playerLevel[5] = 99999999;
            c.getPA().refreshSkill(5);
            c.startAnimation(12565);
            c.gfx0(2226);
        }

        if (playerCommand.startsWith("object") && c.playerRights >= 2) {
            String[] args = playerCommand.split(" ");
            c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
        }

        if (playerCommand.startsWith("emote") && c.playerRights >= 2) {
            String[] args = playerCommand.split(" ");
            c.startAnimation(Integer.parseInt(args[1]));
            c.getPA().requestUpdates();
        }

        if (playerCommand.startsWith("spec") && c.playerRights >= 2) {
            c.specAmount = 500.0;
        }
        if (playerCommand.startsWith("giveowner") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToOwner = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given Owner status by " + c.playerName);
                            c2.playerRights = 3;
                            c2.isDonator = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("giveadmin") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToAdmin = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given admin status by " + c.playerName);
                            c2.playerRights = 2;
                            c2.isDonator = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("giveadmin") && c.playerName.equalsIgnoreCase("haribo")) {
            try {
                String playerToAdmin = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given admin status by " + c.playerName);
                            c2.playerRights = 2;
                            c2.isDonator = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("givemod") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToMod = playerCommand.substring(8);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given mod status by " + c.playerName);
                            c2.playerRights = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("givemod") && c.playerName.equalsIgnoreCase("haribo")) {
            try {
                String playerToMod = playerCommand.substring(8);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given mod status by " + c.playerName);
                            c2.playerRights = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("timedmute") && c.playerRights >= 1 && c.playerRights <= 3) {

            try {
                String[] args = playerCommand.split("-");
                if (args.length < 2) {
                    c.sendMessage("Currect usage: ::timedmute-playername-time");
                    return;
                }
                String playerToMute = args[1];
                int muteTimer = Integer.parseInt(args[2]) * 1000;

                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMute)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + c.playerName + " for " + muteTimer / 1000 + " seconds");
                            c2.muteEnd = System.currentTimeMillis() + muteTimer;
                            break;
                        }
                    }
                }


            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("pnpc")) {
            try {
                int newNPC = Integer.parseInt(playerCommand.substring(5));
                if (newNPC <= 200000 && newNPC >= 0) {
                    c.npcId2 = newNPC;
                    c.isNpc = true;
                    c.updateRequired = true;
                    c.setAppearanceUpdateRequired(true);
                } else {
                    c.sendMessage("No such P-NPC.");
                }
            } catch (Exception e) {
                c.sendMessage("Wrong Syntax! Use as ::pnpc #");
            }
        }


        if (playerCommand.startsWith("givedonor") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToDonor = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDonor)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given donator status by " + c.playerName);
                            c2.playerRights = 4;
                            c2.isDonator = 1;
                            c2.logout();

                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("givedonor") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToDonor = playerCommand.substring(10);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDonor)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given donator status by " + c.playerName);
                            c2.playerRights = 4;
                            c2.isDonator = 1;
                            c2.logout();

                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

        if (playerCommand.startsWith("givesupport") && c.playerName.equalsIgnoreCase("justin")) {
            try {
                String playerToSupport = playerCommand.substring(12);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToSupport)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been given [Support] status by " + c.playerName);
                            c2.playerRights = 7;
                            c2.logout();

                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }


        if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase("justin") || c.playerName.equalsIgnoreCase("haribo")) {
            try {
                String playerToDemote = playerCommand.substring(7);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been demoted by " + c.playerName);
                            c2.playerRights = 0;
                            c2.isDonator = 0;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("npcreset")) {
            Server.npcHandler = null;
            Server.npcHandler = new server.model.npcs.NPCHandler();
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client) Server.playerHandler.players[j];
                    c2.sendMessage("<shad=15695415>[" + c.playerName + "] " + " Has Reset NPC's....</col>");
                }
            }

        }
        if (playerCommand.startsWith("w33d") && (c.playerName.equalsIgnoreCase("justin"))) {
            for (Player p: PlayerHandler.players) {
                if (p == null)
                    continue;
                PlayerSave.saveGame((Client) p);
            }
            System.exit(0);
        }
        if (playerCommand.equalsIgnoreCase("switch") && c.playerRights >= 2) {
            for (int i = 0; i < 8; i++) {
                c.getItems().wearItem(c.playerItems[i] - 1, i);
            }
            c.sendMessage("Switching Armor");
        }
        if (playerCommand.equalsIgnoreCase("brid") && c.playerRights >= 2) {
            c.getItems().deleteAllItems();
            int itemsToAdd[] = {
                4151,
                6585,
                10551,
                20072,
                11732,
                11726,
                15220,
                7462,
                2440,
                2436,
                3024
            };
            for (int i = 0; i < itemsToAdd.length; i++) {
                c.getItems().addItem(itemsToAdd[i], 1);
            }
            int[] equip = {
                10828,
                2414,
                18335,
                15486,
                4712,
                6889, -1,
                4714, -1,
                6922, -1,
                6920,
                15018
            };
            for (int i = 0; i < equip.length; i++) {
                c.playerEquipment[i] = equip[i];
                c.playerEquipmentN[i] = 1;
                c.getItems().setEquipment(equip[i], 1, i);
            }
            c.getItems().addItem(555, 1200);
            c.getItems().addItem(560, 800);
            c.getItems().addItem(565, 400);
            c.getItems().addItem(5698, 1);
            c.getItems().addItem(391, 13);
            c.playerMagicBook = 1;
            c.setSidebarInterface(6, 12855);
            c.getItems().resetItems(3214);
            c.getItems().resetBonus();
            c.getItems().getBonus();
            c.getItems().writeBonus();
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
        }
        if (playerCommand.equals("alltome") && c.playerRights >= 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client) Server.playerHandler.players[j];
                    c2.teleportToX = c.absX;
                    c2.teleportToY = c.absY;
                    c2.heightLevel = c.heightLevel;
                    c2.sendMessage("Mass teleport to: " + c.playerName + "");
                }
            }
        }
        if (playerCommand.startsWith("setlevel") && c.playerRights == 3) {
            try {
                String[] args = playerCommand.split("-");
                int skill = Integer.parseInt(args[1]);
                int level = Integer.parseInt(args[2]);
                String otherplayer = args[3];
                Client target = null;
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
                            target = (Client) Server.playerHandler.players[i];
                            break;
                        }
                    }
                }
                if (target == null) {
                    c.sendMessage("Player doesn't exist.");
                    return;
                }
                c.sendMessage("You have just set one of " + Misc.ucFirst(target.playerName) + "'s skills.");
                target.sendMessage("" + Misc.ucFirst(c.playerName) + " has just set one of your skills.");
                target.playerXP[skill] = target.getPA().getXPForLevel(level) + 5;
                target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
                target.getPA().refreshSkill(skill);
            } catch (Exception e) {
                c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
            }
        }
        if (playerCommand.startsWith("heal") && c.playerRights >= 1) {
            if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
                String name = playerCommand.substring(5);
                if (c.validClient(name)) {
                    Client p = c.getClient(name);
                    for (int i = 0; i < 20; i++) {
                        p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
                        p.getPA().refreshSkill(i);
                    }
                    p.sendMessage("You have been healed by " + c.playerName + ".");
                } else {
                    c.sendMessage("Player must be offline.");
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
                    c.getPA().refreshSkill(i);
                }
                c.freezeTimer = -1;
                c.frozenBy = -1;
                c.sendMessage("You have been healed.");
            }
        }
        if (playerCommand.startsWith("shop")) {
            try {
                c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
            } catch (Exception e) {
                c.sendMessage("Invalid input data! try typing ::shop 1");
            }
        }

        if (playerCommand.startsWith("copy")) {
            String name = playerCommand.substring(5);
            if (c.validClient(name)) {
                Client p = c.getClient(name);
                for (int i = 0; i < c.playerEquipment.length; i++)
                    c.playerEquipment[i] = p.playerEquipment[i];
                for (int i = 0; i < c.playerAppearance.length; i++)
                    c.playerAppearance[i] = p.playerAppearance[i];
                c.sendMessage("You have copied " + p.playerName + ".");
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            }
        }
        if (playerCommand.equals("votenow") && c.playerRights == 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client) Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("http://www.rspscoding.org/toplist/vote.php?id=250", 12000);
                    c2.sendMessage("Please vote every 12 hours for more players!");
                }
        }
        if (playerCommand.equals("website") && c.playerRights == 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client) Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("http://www.exilium.org/", 12001);
                    c2.sendMessage("Enjoy Exilium's Site!");
                }
        }
        if (playerCommand.startsWith("xcopy")) {
            String name = playerCommand.substring(6);
            if (c.validClient(name)) {
                Client p = c.getClient(name);
                for (int i = 0; i < c.playerEquipment.length; i++)
                    p.playerEquipment[i] = c.playerEquipment[i];
                for (int i = 0; i < c.playerAppearance.length; i++)
                    p.playerAppearance[i] = c.playerAppearance[i];
                c.sendMessage("You have xcopied " + p.playerName + ".");
                p.sendMessage("You have been xcopied by " + c.playerName + ".");
                p.updateRequired = true;
                p.appearanceUpdateRequired = true;
            }
        }

        if (playerCommand.startsWith("demon") && c.playerRights >= 2) {
            int id = 82 + Misc.random(2);
            c.npcId2 = id;
            c.isNpc = true;
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
            c.playerStandIndex = 66;
            c.playerTurnIndex = 66;
            c.playerWalkIndex = 63;
            c.playerTurn180Index = 66;
            c.playerTurn90CWIndex = 66;
            c.playerTurn90CCWIndex = 63;
            c.playerRunIndex = 63;
        }
        if (playerCommand.startsWith("brute") && c.playerRights >= 2) {
            int id = 6102 + Misc.random(2);
            c.npcId2 = id;
            c.isNpc = true;
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
        }
        if (playerCommand.startsWith("checkinv")) {
            try {
                String[] args = playerCommand.split(" ", 2);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    Client o = (Client) Server.playerHandler.players[i];
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                            c.getPA().otherInv(c, o);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }

    }

    public void SupportCommands(Client c, String playerCommand) {
        if (playerCommand.startsWith("szone")) {
            c.getPA().startTeleport(3087, 3498, 0, "modern");
            c.sendMessage("Just testing at the moment.");
        }
        if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
            try {
                BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Reports.txt", true));
                String Report = playerCommand.substring(7);
                try {
                    report.newLine();
                    report.write("[Support]" + c.playerName + ": " + Report);
                    c.sendMessage("You have successfully submitted your report.");
                } finally {
                    report.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void DonatorCommands(Client c, String playerCommand) {

        if (playerCommand.startsWith("god") && c.playerRights >= 1) {
            if (c.playerStandIndex != 1501) {
                c.startAnimation(1500);
                c.playerStandIndex = 1501;
                c.playerTurnIndex = 1851;
                c.playerWalkIndex = 1851;
                c.playerTurn180Index = 1851;
                c.playerTurn90CWIndex = 1501;
                c.playerTurn90CCWIndex = 1501;
                c.playerRunIndex = 1851;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
                c.sendMessage("You turn God mode on.");
            } else {
                c.playerStandIndex = 0x328;
                c.playerTurnIndex = 0x337;
                c.playerWalkIndex = 0x333;
                c.playerTurn180Index = 0x334;
                c.playerTurn90CWIndex = 0x335;
                c.playerTurn90CCWIndex = 0x336;
                c.playerRunIndex = 0x338;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
                c.sendMessage("Godmode has been diactivated.");
            }
        }
        if (playerCommand.startsWith("emote") && c.playerRights >= 2) {
            String[] args = playerCommand.split(" ");
            c.startAnimation(Integer.parseInt(args[1]));
            c.getPA().requestUpdates();
        }
        if (playerCommand.startsWith("fall") && c.playerRights >= 1) {
            if (c.playerStandIndex != 2048) {
                c.startAnimation(2046);
                c.playerStandIndex = 2048;
                c.playerTurnIndex = 2048;
                c.playerWalkIndex = 2048;
                c.playerTurn180Index = 2048;
                c.playerTurn90CWIndex = 2048;
                c.playerTurn90CCWIndex = 2048;
                c.playerRunIndex = 2048;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            } else {
                c.startAnimation(2047);
                c.playerStandIndex = 0x328;
                c.playerTurnIndex = 0x337;
                c.playerWalkIndex = 0x333;
                c.playerTurn180Index = 0x334;
                c.playerTurn90CWIndex = 0x335;
                c.playerTurn90CCWIndex = 0x336;
                c.playerRunIndex = 0x338;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            }
        }

        if (playerCommand.startsWith("pure")) {
            int i = 0;
            c.getPA().addSkillXP((15000000), 0);
            c.getPA().addSkillXP((15000000), 2);
            c.getPA().addSkillXP((15000000), 3);
            c.getPA().addSkillXP((15000000), 4);
            c.getPA().addSkillXP((15000000), 6);
            c.playerXP[3] = c.getPA().getXPForLevel(99) + 5;
            c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
            c.getPA().refreshSkill(3);
            c.puremaster = 1;
        }
        if (playerCommand.startsWith("pushups") && c.playerRights >= 1) {
            c.startAnimation(2756);
        }
    }
}