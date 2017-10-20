/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;

/**
 * Tnt is a block that can explode
 */
public class Tnt extends Block {

    /**
     * TNT
     * @param  image_src     image file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Tnt(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    /**
     * checks if Tnt can move
     * @param  world        checks against whats in the world
     * @param  direction    direction it is facing
     */
    @Override
    public boolean canBlockMove(World world, int direction) {
        //obtain sprite in next position
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
        for(Sprite currSpr : spritesAtPos){
            //change permit to move through Cracked
            if(currSpr instanceof Block ||
            (currSpr.getRoadBlock() && !(currSpr instanceof Cracked)) ){
                return false;
            }
        }
        return true;
    }

    /**
     * Tnt movement
     * @param world     ammend changes to world
     * @param direction where block is moving
     */
    public void move(World world, int direction){
        Position nextPos = this.getPosition().nextPosition(direction);
        if(this.canBlockMove(world, direction)){
            this.setPosition(this.getPosition().nextPosition(direction));

            //when tnt exploded no need to recordMove
            if(this.getRender()){
                MoveStack.recordMove(this, nextPos);
            }
        }
    }

}
