
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

//player is an extension of Sprite

public class Player extends Sprite implements Moveable {

	public Player(String image_src, Position position)
	throws SlickException {
		super(image_src, position);
	}

	private boolean canPlayerMove(World world, int direction){
		Position nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if wall/door/cracked or if Block and block not pushable
			if (currSpr.getRoadBlock() ||
			(currSpr.instanceOf(Block) &&
			!currSpr.canBlockMove(world, direction)) ){
				return false;
			}
			return true;
	}

    public void move(World world, int direction){
        if(canPlayerMove(world, direction)){
				this.setPosition(nextPos);
				MoveStack.recordMove(this, newPos);
			}

		}

	}

public void render(Graphics g){
	super.render(g)
}


}
