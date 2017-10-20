
/**
 * Project skeleton for SWEN20003: Object Oriented Software Development 2017
 * by Eleanor McMurtry
 */
import java.io.FileNotFoundException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
 	/** Screen dimension values in pixels */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int TILE_SIZE = 32;

    //level data
    public static final String LVL_TXT = "levels/";
	public static final String LVL_TXT_END =".lvl";

    public static final String IMG_TXT = "res/";
	public static final String IMG_TXT_END =".png";

    public static final int MAX_LVL = 5;

    private static World world;
    private static String lvlAddress = "";
    private static int nLvl = 0;

    public App() {
      super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
    	try {
            App.lvlAddress = App.LVL_TXT + Integer.toString(App.nLvl) + App.LVL_TXT_END;
			world = new World(App.lvlAddress);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


///////////////////////////////////////////////////////////////////////////////

    public static void reset() throws FileNotFoundException, SlickException{
		App.lvlAddress = App.LVL_TXT + Integer.toString(App.nLvl) + App.LVL_TXT_END;
        App.world = new World(App.lvlAddress);
        MoveStack.clearStack();
	}

    public static void nextLevel() throws FileNotFoundException, SlickException{
        if(App.nLvl < App.MAX_LVL){
            App.nLvl += 1;
        } else {
            App.nLvl = 0;
        }
        App.reset();
    }





//////////////////////////////////////////////////////////////////////////////

    /** UPDATE the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        try {
			world.update(input, delta);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Escape Command
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            System.exit(0);
        }

    }


    /** RENDER the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
    	world.render(g);
    }


    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }




}
