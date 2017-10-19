import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

// generic class that repesent all worldly objects
/*
All Sprites have:
	- pixel locations (x & y)
	- a picture
*/
public abstract class Sprite {

	public static final String PLAYER_LOC = "src/res/player_left.png";
	public static final String FLOOR_LOC = "src/res/floor.png";
	public static final String STONE_LOC = "src/res/stone.png";
	public static final String WALL_LOC = "src/res/wall.png";
	public static final String TARGET_LOC = "src/res/target.png";

	//x & y represent the pixel locations of floor tiles
	private Image pic;
	private boolean rendered;
	public Sprite(String image_src, Position position) throws SlickException {
		this.x = x;
		this.y = y;
		this.pic = new Image(image_src);
		//
		// if(image_src.equals("floor")){
		// 	pic = new Image(FLOOR_LOC);
		// }
		// else if(image_src.equals("stone")){
		// 	pic = new Image(STONE_LOC);
		// }
		// else if(image_src.e	quals("wall")){
		// 	pic = new Image(WALL_LOC);
		// }
		// else if(image_src.equals("target")){
		// 	pic = new Image(TARGET_LOC);
		// }
		// else if(image_src.equals("player")){
		// 	pic = new Image(PLAYER_LOC);
		// }
	}

	public void update(World world, int direction, int delta) {
	}

	public void render(Graphics g) {
		pic.draw(this.x,this.y);

	}
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////// Getters and Setters //////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

    //xPixel
    public double GetPosition() {
        return this.position;
    }


    //yPixel
    public double getY() {
        return this.y;
    }

    public void setY(double yPixel) {
        this.y = yPixel;
    }
}
