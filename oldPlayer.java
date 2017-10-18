import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

//player is an extension of Sprite

public class Player extends Sprite {

	//note xPos == tile location while x == pixel location
	private static final int STEP = 1;
	private int xPos;
	private int yPos;

	public Player(String image_src, int xPos, int yPos)
	throws SlickException {
		super(image_src,x,y);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public override equals(Object o) {
		// if o the same type as me?
		// 2 if so then x & y same
	}

	//player movement
	public void update(Input input) {
		pos.equals(o2)

		//UDLR movment commands
		if (input.isKeyPressed(Input.KEY_UP)
		&& (!World.isBlocked(xPos,yPos-STEP))) {
			y -= App.TILE_SIZE;
			yPos -= STEP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)
		&& (!World.isBlocked(xPos,yPos+STEP))) {
			y += App.TILE_SIZE;
			yPos += STEP;
		}
		else if (input.isKeyPressed(Input.KEY_LEFT)
		&& (!World.isBlocked(xPos-STEP,yPos))) {
			x -= App.TILE_SIZE;
			xPos -= STEP;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)
		&& (!World.isBlocked(xPos+STEP,yPos))) {
			x += App.TILE_SIZE;
			xPos += STEP;
		}
		//movement reassigned to be redrawn
		super.setX(x);
		super.setY(y);
	}

	public void render(Graphics g) {
		super.render(g);
	}
}
