
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
	 * @param lvlAddress
	 * @throws SlickException
	 * @throws FileNotFoundException
	 */

	public static ArrayList<Sprite> loadSprites(World world, String lvlAddress)
	throws SlickException {

		ArrayList<Sprite> list = new ArrayList<Sprite>();

		try (BufferedReader br = new BufferedReader(new FileReader(lvlAddress))) {
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
				Position position = new Position(x,y);

		        if(tileType.equals("cracked")) {
					list.add((Sprite) (new Cracked(tileType, position)));
				}
				else if(tileType.equals("door")){
					list.add((Sprite) (new Door(tileType, position)));
	            }
				else if(tileType.equals("floor")) {
					list.add((Sprite) (new Floor(tileType, position)));
				}
				else if(tileType.equals("ice")){
					list.add((Sprite) (new Ice(tileType, position)));
	            }
				else if(tileType.equals("mage")){
					list.add((Sprite) (new Mage(tileType, position)));
	            }
				else if(tileType.equals("rogue")){
					list.add((Sprite) (new Rogue(tileType, position)));
	            }
				else if(tileType.equals("skull")){
					list.add((Sprite) (new Skeleton("skeleton", position)));
	            }
				else if(tileType.equals("stone")){
					list.add((Sprite) (new Stone(tileType, position)));
	            }
				else if(tileType.equals("switch")){
					list.add((Sprite) (new Switch(tileType, position)));
	            }
				else if(tileType.equals("target")){
					list.add((Sprite) (new Target(tileType, position)));
					world.setNTargets(world.getNTargets()+1);
	            }
				else if(tileType.equals("tnt")){
					list.add((Sprite) (new Tnt(tileType, position)));
	            }
				else if(tileType.equals("wall")){
					list.add((Sprite) (new Wall(tileType, position)));
				}
				else if (tileType.equals("player")){
					list.add((Sprite) (new Player(tileType, position)));
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return list;
	}

}
