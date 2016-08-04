package server.model.minigames;

import server.Server;
import server.model.players.Client;

/**
 * @author Runeika
 */

public class ZombieCaves {
	private final int[][] ZOMBIES = { { 73 }, { 73, 4392 }, { 73, 4392, 4393 },
			{ 4392, 4393 }, { 4393, 4393, 4392 }, { 4393, 4393, 4393, 4393 },
			{ 4392, 4393, 4393, 4392, 4393 }, { 4394 }, { 4394, 1466 },
			{ 4394, 4394, 1466, 1466 }, { 1466, 1466, 1466 },
			{ 1466, 4394, 4393, 4392 }, { 4392, 4393, 1466, 1466, 4394 },
			{ 4392, 4393, 4394, 1466 }, { 4394, 1466, 4393, 4393, 4393 },
			{ 4393, 4393, 1466, 1466, 4392, 4394 },
			{ 4394, 1466, 4394, 1466, 4393 }, { 4393, 4393, 1466, 1466 },
			{ 4393, 4393, 1466, 1466 }, { 4393, 4393, 1466, 1466, 4393 },
			{ 3066 } };
	private int[][] coordinates = { { 2603, 9906 }, { 2604, 9906 },
			{ 2603, 9907 }, { 2603, 9908 }, { 2602, 9906 }, { 2601, 9906 } };

	// 422,423,424 normal zombies 1465,1466 monkey zombies
	public void spawnNextZombies(Client c) {
		if (c != null) {
			if (c.zombiesId >= ZOMBIES.length) {
				c.zombiesId = 0;
				return;
			}
			if (c.zombiesId < 0) {
				return;
			}
			int npcAmount = ZOMBIES[c.zombiesId].length;
			for (int j = 0; j < npcAmount; j++) {
				int npc = ZOMBIES[c.zombiesId][j];
				int X = coordinates[j][0];
				int Y = coordinates[j][1];
				int H = c.heightLevel;
				int hp = getHp(npc);
				int max = getMax(npc);
				int atk = getAtk(npc);
				int def = getDef(npc);
				Server.npcHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk,
						def, true, false);
			}
			c.zombiesToKill = npcAmount;
			c.zombiesKilled = 0;
		}
	}

	public int getHp(int npc) {
		switch (npc) {
		case 73:
			return 150;
		case 4392:
			return 150;
		case 4394:
			return 150;
		case 4393:
			return 200;
		case 1466:
			return 200;
		case 3066:
			return 350;
		}
		return 100;
	}

	public int getMax(int npc) {
		switch (npc) {
		case 73:
			return 30;
		case 4392:
			return 50;
		case 4394:
			return 50;
		case 4393:
			return 75;
		case 1466:
			return 75;
		case 3066:
			return 97;
		}
		return 5;
	}

	public int getAtk(int npc) {
		switch (npc) {
		case 73:
			return 120;
		case 4392:
			return 200;
		case 4394:
			return 200;
		case 4393:
			return 350;
		case 1466:
			return 350;
		case 3066:
			return 650;
		}
		return 100;
	}

	public int getDef(int npc) {
		switch (npc) {
		case 73:
			return 120;
		case 4392:
			return 170;
		case 4394:
			return 170;
		case 4393:
			return 300;
		case 1466:
			return 300;
		case 3066:
			return 500;
		}
		return 100;
	}

}