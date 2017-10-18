import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

//player is an extension of Sprite

public class Player extends Sprite implements Moveable {

	private static final int STEP = 1;
    int direction;

	public Player(String image_src, Position postion)
	throws SlickException {
		super(image_src, postion);
        this.direction = World.NORTH;
	}

    public void move(World world, int direction){
        Postion nextPos = Postion.nextPosition(this.position, direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if not wall/door/cracked, if no Block or block is pushable
			if ( !currSpr.getRoadBlock() && (currSpr.instanceOf(Block)
									||currSpr.canBlockMove(world, direction)) ){
				this.position = nextPos;
				MoveStack.recordMove(this, newPos);
			}

		}

	}




}
