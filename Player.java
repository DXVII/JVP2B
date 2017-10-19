
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

//player is an extension of Sprite

public class Player extends Sprite implements Moveable {

	public Player(String image_src, Position postion)
	throws SlickException {
		super(image_src, postion);
	}

    public void move(World world, int direction){
        Postion nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if not wall/door/cracked, if no Block or block is pushable
			if ( !currSpr.getRoadBlock() && (!currSpr.instanceOf(Block)
									||currSpr.canBlockMove(world, direction)) ){
				this.setPosition(nextPos);
				MoveStack.recordMove(this, newPos);
			}

		}

	}

public void render(Graphics g){
	super.render(g)
}


}
