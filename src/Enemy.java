
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class Enemy extends Sprite {

    public Enemy(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    
    public boolean canEnemyMove(World world, int direction){
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
        // checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
            //block and enemy have same behaviour move conditions
			// except rogue
            if(currSpr.getRoadBlock() ||
            (currSpr instanceof Block)) {
                return false;
            }
        }
        return true;
    }

}
