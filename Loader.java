
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import java.io.BufferedReader;


public class Loader {
	//dimension indexing
	public static final int X_POS = 0;
	public static final int Y_POS = 1;

	//sprite indexing
	public static final int TILE_TYPE = 0;
	public static final int X_COORD = 1;
	public static final int Y_COORD = 2;


	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 * @throws SlickException
	 * @throws FileNotFoundException
	 */

	public static ArrayList<Sprite> loadSprites(String filename)
	throws SlickException {

		ArrayList<Sprite> list = new ArrayList<Sprite>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			//read first line to obtain level dimensions
		    String[] txtRow = (br.readLine()).split(",");
			//extract from file convert int
		    world.setLvlWidth(Integer.parseInt((txtRow[X_POS]).trim()));
		    world.setLvlHeight(Integer.parseInt((txtRow[Y_POS]).trim()));

		// Looping through the rest of csv lines
			//indexing
			String tileType;
		    int x;
		    int y;

			String text;
			//Building my array list to be returned to world

		    while ((text = br.readLine()) != null) {
				//reducing horizontal code length
				txtRow = text.split(",");
				tileType = txtRow[TILE_TYPE];
		        x = Integer.parseInt((txtRow[X_COORD]).trim());
		        y = Integer.parseInt((txtRow[Y_COORD]).trim());

				//generating Sprites
				Postion postion = new Postion(x,y);

		        if(tileType.equals("cracked")) {
					list.add(new Cracked(tileType, postion));
				}
				else if(tileType.equals("door")){
					list.add(new Door(tileType, postion));
	            }
				else if(tileType.equals("floor")) {
					list.add(new Floor(tileType, postion));
				}
				else if(tileType.equals("ice")){
					list.add(new Ice(tileType, postion));
	            }
				else if(tileType.equals("mage")){
					list.add(new Mage(tileType, postion));
	            }
				else if(tileType.equals("rogue")){
					list.add(new Rogue(tileType, postion));
	            }
				else if(tileType.equals("skeleton")){
					list.add(new Skeleton(tileType, postion));
	            }
				else if(tileType.equals("stone")){
					list.add(new Stone(tileType, postion));
	            }
				else if(tileType.equals("switch")){
					list.add(new Button(tileType, postion));
	            }
				else if(tileType.equals("target")){
					list.add(new Target(tileType, postion));
					setNTargets(getNTargets+1);
	            }
				else if(tileType.equals("tnt")){
					list.add(new Tnt(tileType, postion));
	            }
				else if(tileType.equals("wall")){
					list.add(new Wall(tileType, postion));
				// be kind, definitely player if not anything else
				} else {
					list.add(new Player(tileType, postion));
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return list;
		//.toArray(new Sprite[list.size()]);
	}
}
