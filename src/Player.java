/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
/**
 * Sprite that game player controls
 */
public class Player extends Sprite{
	/**
     * Player
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
	public Player(String image_src, Position position)
	throws SlickException {
		super(image_src, position);
	}
	
	/**
     * if player can move
     * @param  world         ammend changes to world
     * @param  direction     where rogue is facing
     */
	private boolean canPlayerMove(World world, int direction){
		Position nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if wall/door/cracked or if Block and block not pushable
			if (currSpr.getRoadBlock() ||
			(currSpr instanceof Block &&
			!((Block) currSpr).canBlockMove(world, direction)) ){
				return false;
			}
		}
		return true;
	}

	/**
	 * how player moves
	 * @param world 	ammend changes to world
	 * @param direction where player is to move
	 */
	public void move(World world, int direction){
		this.setDirection(direction);
		// if can move, do it then record move
		if(this.canPlayerMove(world, direction)){
			Position nextPos = (this.getPosition()).nextPosition(direction);
			this.setPosition(nextPos);
			MoveStack.recordMove(this, nextPos);
		}

	}

}
