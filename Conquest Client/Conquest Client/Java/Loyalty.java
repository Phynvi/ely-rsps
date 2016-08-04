import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import sign.signlink;

public class Loyalty {

	public static String[] title = new String[30];
	public static final String TITLE_PATH = signlink.findcachedir()+"titles.dat";

	/**
	 * Loads the loyalty titles from the cache
	 */
	public static void loadTitles() {
		DataInputStream dataInputStream;
		try {
			dataInputStream = new DataInputStream(new FileInputStream(
					TITLE_PATH));
			for (int i = 0; i < title.length; i++) {
				title[i] = dataInputStream.readUTF();
			}
			dataInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a title to the cache
	 * 
	 * @param the
	 *            loyalty title added
	 */
	public static void addTitle(String title) {
		DataOutputStream dataOutputStream;
		try {
			dataOutputStream = new DataOutputStream(new FileOutputStream(
					TITLE_PATH));
			dataOutputStream.writeUTF(title);
			dataOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}