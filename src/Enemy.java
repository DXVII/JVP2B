/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
/**
 * Composition of an Enemy
 */
public abstract class Enemy extends Sprite {
    /**
     * Enemies can kill players
     * @param  image_src     file path
     * @param  position      where it exits
     * @throws SlickException if creation fails
     */
    public Enemy(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    
    /**
     * Generally most enemies
     * @param  world         world state
     * @param  direction     where enemy intends to move
     */
    public boolean canEnemyMove(World world, int direction){
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
        // checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
            //block and enemy have same behaviour move conditions
			// except rogue (overidden)
            if(currSpr.getRoadBlock() ||
            (currSpr instanceof Block)) {
                return false;
            }
        }
        return true;
    }

    /**
     * all enemies can move
     * @param world ammend changes in the world
     */
    public void move(World world){
    }

}
