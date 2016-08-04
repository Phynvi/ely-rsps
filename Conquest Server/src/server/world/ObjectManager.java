package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
//Start of the Objects needed for MY server to work
c.getPA().checkObjectSpawn(2213, 3089, 3933, -1, 10);
c.getPA().checkObjectSpawn(2213, 3089, 3934, -1, 10);


c.getPA().checkObjectSpawn(13289, 2778, 2979,0, 10);
c.getPA().checkObjectSpawn(2470, 2767, 2972,0, 10);
c.getPA().checkObjectSpawn(2728, 2520, 3861, 2, 10);
c.getPA().checkObjectSpawn(2728, 2618, 3893, 2, 10);
c.getPA().checkObjectSpawn(2591, 2621, 9835, 2, 10);
c.getPA().checkObjectSpawn(4965, 2762, 2977, 2, 10);
c.getPA().checkObjectSpawn(5250, 2511, 3854, 2, 10);
c.getPA().checkObjectSpawn(4646, 2548, 3891, 2, 10);
c.getPA().checkObjectSpawn(2091, 2529, 3890, -1, 10);
c.getPA().checkObjectSpawn(2095, 2529, 3892, -1, 10);
c.getPA().checkObjectSpawn(2103, 2528, 3895, -1, 10);
c.getPA().checkObjectSpawn(2105, 2526, 3892, -1, 10);
c.getPA().checkObjectSpawn(2107, 2529, 3894, -1, 10);
c.getPA().checkObjectSpawn(2093, 2526, 3895, -1, 10);
c.getPA().checkObjectSpawn(3044, 2537, 3893, 0, 10);
c.getPA().checkObjectSpawn(2995, 2542, 3898, -1, 10);
c.getPA().checkObjectSpawn(2995, 2561, 3845, -1, 10);
c.getPA().checkObjectSpawn(2213, 2771, 2979, -1, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);



c.getPA().checkObjectSpawn(11005, 3240, 9366, 0, 10);
c.getPA().checkObjectSpawn(11005, 3240, 9365, 0, 10);
c.getPA().checkObjectSpawn(11005, 3240, 9364, 0, 10);
c.getPA().checkObjectSpawn(11005, 3240, 9363, 0, 10);
c.getPA().checkObjectSpawn(11005, 2576, 3880, 0, 10);
c.getPA().checkObjectSpawn(11005, 2576, 3879, 0, 10);

c.getPA().checkObjectSpawn(1308, 2533, 3866, 0, 10);
c.getPA().checkObjectSpawn(1308, 2534, 3870, 0, 10);
c.getPA().checkObjectSpawn(1308, 2532, 3877, 0, 10);
c.getPA().checkObjectSpawn(1308, 2538, 3872, 0, 10);

c.getPA().checkObjectSpawn(1309, 2556, 3885, 0, 10);
c.getPA().checkObjectSpawn(1309, 2555, 3889, 0, 10);
c.getPA().checkObjectSpawn(1309, 2566, 3888, 0, 10);

c.getPA().checkObjectSpawn(1306, 2558, 3862, 0, 10);
c.getPA().checkObjectSpawn(1306, 2561, 3870, 0, 10);

c.getPA().checkObjectSpawn(1306, 2584, 3883, 0, 10);
c.getPA().checkObjectSpawn(1306, 2590, 3888, 0, 10);
c.getPA().checkObjectSpawn(1306, 2589, 3866, 0, 10);

c.getPA().checkObjectSpawn(2213, 2516, 3855, 0, 10);
c.getPA().checkObjectSpawn(635, 2519, 3866, 0, 10);

c.getPA().checkObjectSpawn(541, 3343, 3347, 0, 10);
c.getPA().checkObjectSpawn(541, 3343, 3348, 0, 10);
c.getPA().checkObjectSpawn(541, 3046, 5105, 1, 10);
//END OF OBJECTS NEEDED FOR my SERVER!

//START OF MISC OBJECTS FROM PREVIOUS SOURCE USERS
        c.getPA().checkObjectSpawn(1277, 2048, 3244, 0, 10);
/*Construction*/
c.getPA().checkObjectSpawn(13405, 2843, 10220, 0, 10);//home portal
c.getPA().checkObjectSpawn(13405, 2056, 3263, 0, 10);

        c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2050, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2051, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2052, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2053, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2054, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2055, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2056, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2057, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2058, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2059, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2060, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2061, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2062, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2063, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2064, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2065, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2066, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2067, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2068, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2069, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3244, 0, 10);
 
        c.getPA().checkObjectSpawn(1277, 2048, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2049, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2050, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2051, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2052, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2053, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2054, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2055, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2056, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2057, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2058, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2059, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2060, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2061, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2062, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2063, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2064, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2065, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2066, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2067, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2068, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2069, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2070, 3243, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3243, 0, 10);
 
        c.getPA().checkObjectSpawn(1277, 2071, 3245, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3246, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3247, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3248, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3249, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3250, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3251, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3252, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3253, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3254, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3255, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3256, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3257, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3258, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3259, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3260, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3261, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3262, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2071, 3263, 0, 10);
 
        c.getPA().checkObjectSpawn(1277, 2072, 3244, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3245, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3246, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3247, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3248, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3249, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3250, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3251, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3252, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3253, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3254, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3255, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3256, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3257, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3258, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3259, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3260, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3261, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3262, 0, 10);
        c.getPA().checkObjectSpawn(1277, 2072, 3263, 0, 10);
        //end of trees
          
        
        //empty bulding spaces
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3247, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3247, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3247, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3247, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3247, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3247, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3248, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3248, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3248, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3248, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3248, 0, 10);
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3249, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3249, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3249, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3249, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3249, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3249, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3250, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3250, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3250, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3250, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3250, 0, 10);
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3251, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3251, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3251, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3251, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3251, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3251, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3252, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3252, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3252, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3252, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3252, 0, 10);
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3253, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3253, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3253, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3253, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3253, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3253, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3254, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3254, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3254, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3254, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3254, 0, 10);
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3255, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3255, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3255, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3255, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3255, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3255, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3256, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3256, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3256, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3256, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3256, 0, 10);
        //1
        c.getPA().checkObjectSpawn(11214, 2069, 3257, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2065, 3257, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2061, 3257, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2057, 3257, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2053, 3257, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2049, 3257, 0, 10);
        //2
        c.getPA().checkObjectSpawn(11214, 2067, 3258, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2063, 3258, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2059, 3258, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2055, 3258, 0, 10);
        c.getPA().checkObjectSpawn(11214, 2051, 3258, 0, 10);
	/*ConstructionEnd*/
	/*HomeObjects*/
	c.getPA().checkObjectSpawn(-1, 2838, 10206, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2837, 10206, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2836, 10206, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2835, 10206, 0, 10);
	c.getPA().checkObjectSpawn(2213, 2838, 10206, 0, 10);//bank
	c.getPA().checkObjectSpawn(2213, 2837, 10206, 0, 10);//bank
	c.getPA().checkObjectSpawn(2213, 2836, 10206, 0, 10);//bank
	c.getPA().checkObjectSpawn(2213, 2835, 10206, 0, 10);//bank
	c.getPA().checkObjectSpawn(-1, 2840, 10205, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2841, 10206, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2835, 10212, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2837, 10214, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2840, 10214, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2838, 10215, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2840, 10213, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2841, 10214, 0, 10);
	c.getPA().checkObjectSpawn(-1, 2834, 10213, 0, 10);
	/*HomeObjectsEnd*/
	c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
				c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(12356, 3094, 3487, 1, 10);
				c.getPA().checkObjectSpawn(2403, 3095, 3487, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2759, 2971, -1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.

		/*HomeObjects*/
		c.getPA().checkObjectSpawn(-1, 3096, 3498, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3095, 3498, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3092, 3496, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3090, 3494, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3091, 3495, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3090, 3496, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3098, 3496, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3095, 3499, 0, 10);
		/*HomeObjectsEnd*/ 
	
		c.getPA().checkObjectSpawn(13617, 3052, 4972, 1, 10); //Barrelportal donor
		c.getPA().checkObjectSpawn(13625, 2848, 10206, 1, 10);
		c.getPA().checkObjectSpawn(13621, 3052, 4974, 1, 10);
		c.getPA().checkObjectSpawn(13619, 3060, 4972, 1, 10);
		c.getPA().checkObjectSpawn(13409, 2843, 3492, 4, 10);//Nomad - Dungeon
		c.getPA().checkObjectSpawn(13620, 3060, 4974, 1, 10);	
		c.getPA().checkObjectSpawn(13629, 3078, 3486, 0, 10);		
		c.getPA().checkObjectSpawn(2213, 3042, 4977, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3042, 4978, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2060, 3260, 4, 10);
		c.getPA().checkObjectSpawn(2213, 2060, 3260, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2060, 3260, 8, 10);
		c.getPA().checkObjectSpawn(2213, 2060, 3260, 16, 10);
		c.getPA().checkObjectSpawn(2213, 2311, 9808, 0, 0);
		c.getPA().checkObjectSpawn(2213, 2311, 9807, 0, 0);
		c.getPA().checkObjectSpawn(2213, 2315, 9808, 0, 0);
		c.getPA().checkObjectSpawn(2213, 2315, 9807, 0, 0);
		c.getPA().checkObjectSpawn(2213, 2840, 3438, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2647, 4574, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2656, 4574, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4574, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4573, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4572, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4571, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4570, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4569, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4568, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4567, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4566, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4565, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4564, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4563, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4562, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2670, 4561, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2815, 3468, 0, 10);
		c.getPA().checkObjectSpawn(14859, 2669, 4560, 1, 10);
		c.getPA().checkObjectSpawn(3044, 2666, 4575, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2666, 4564, 1, 10);
		c.getPA().checkObjectSpawn(2732, 2667, 4568, 1, 10);
		c.getPA().checkObjectSpawn(2732, 2667, 4567, 1, 10);
		c.getPA().checkObjectSpawn(2466, 2987, 9641, 0, 10);
		c.getPA().checkObjectSpawn(2467, 2962, 9636, 0, 10);
		
		c.getPA().checkObjectSpawn(13289, 2098, 4430, 0, 10);
		c.getPA().checkObjectSpawn(2465, 2872, 5279, 2, 10);
		        c.getPA().checkObjectSpawn(13292, 2824, 3810, 1, 10);
        c.getPA().checkObjectSpawn(13292, 2824, 3809, 1, 10);
        c.getPA().checkObjectSpawn(13292, 2824, 3808, 1, 10);
        // start of Ice minigame right side...
        c.getPA().checkObjectSpawn(6455, 2850, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2848, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2846, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2844, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2842, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2840, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2838, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2836, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2834, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2832, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2830, 3810, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2828, 3810, 1, 10);
        // start of Ice minigame Left side...
        c.getPA().checkObjectSpawn(6455, 2850, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2848, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2846, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2844, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2842, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2840, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2838, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2836, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2834, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2832, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2830, 3809, 1, 10);
        c.getPA().checkObjectSpawn(6455, 2828, 3809, 1, 10);
        c.getPA().checkObjectSpawn(7272, 2855, 3810, 1, 10);

		c.getPA().checkObjectSpawn(1, 2738, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2739, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2740, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5097, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5096, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5095, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5094, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5093, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5092, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5091, 0, 10);
		c.getPA().checkObjectSpawn(1, 2741, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2740, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2739, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2738, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2737, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2736, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5090, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5091, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5092, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5093, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5094, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5095, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5096, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5097, 0, 10);
		c.getPA().checkObjectSpawn(1, 2735, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2736, 5098, 0, 10);
		c.getPA().checkObjectSpawn(1, 2737, 5098, 0, 10);


		c.getPA().checkObjectSpawn(411, 2849, 10217, -1, 10); // Curse Prayers

		c.getPA().checkObjectSpawn(13615, 2036, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13620, 2041, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13619, 2031, 4518, 0, 10);

		c.getPA().checkObjectSpawn(6163, 3040, 4970, 1, 10);
		c.getPA().checkObjectSpawn(6165, 3040, 4972, 1, 10);
		c.getPA().checkObjectSpawn(6166, 3040, 4974, 1, 10);

		c.getPA().checkObjectSpawn(410, 2847, 10219, 0, 10); 

		c.getPA().checkObjectSpawn(4874, 3084, 3483, 1, 10);
		c.getPA().checkObjectSpawn(4875, 3085, 3483, 1, 10);
		c.getPA().checkObjectSpawn(4876, 3086, 3483, 0, 10);
		c.getPA().checkObjectSpawn(4877, 3087, 3483, 0, 10);
		c.getPA().checkObjectSpawn(4878, 3088, 3483, 0, 10);

		//lumbridge castle removed objects
		c.getPA().checkObjectSpawn(-1, 3208, 3210, 1, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3214, 1, 0);
		c.getPA().checkObjectSpawn(-1, 3207, 3222, 1, 0);
		c.getPA().checkObjectSpawn(-1, 3207, 3227, 1, 0);
		c.getPA().checkObjectSpawn(-1, 3213, 3222, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3215, 3225, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3217, 3219, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3207, 3217, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3211, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3215, 3211, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3219, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3210, 3220, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3210, 3221, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3210, 3222, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3210, 3223, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3224, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3220, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3221, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3222, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3208, 3223, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3205, 3218, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3205, 3220, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3205, 3222, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3205, 3224, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3205, 3226, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3223, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3222, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3221, 0, 0);
		c.getPA().checkObjectSpawn(-1, 3209, 3220, 0, 0);
		//lumbridge castle removed objects end
		//lummy home
		c.getPA().checkObjectSpawn(2213, 3206, 3218, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3219, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3220, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3221, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3222, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3223, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3224, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3225, 0, 0);
		c.getPA().checkObjectSpawn(2213, 3206, 3226, 0, 0); 

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2849, 10212, -1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2849, 10215, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(-1, 3092, 3473, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
				c.getPA().checkObjectSpawn(104, 2845, 10202, 0, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2462, 5287, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2560, 4959, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2559, 4959, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2559, 4960, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2560, 4960, 0, 10);

	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}