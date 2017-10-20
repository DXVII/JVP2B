/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
/**
 * Block can move and interact with some special objects
 */
public abstract class Block extends Sprite {
    /**
     * Block
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    /**
     * If block can move
     * @param  world        compare to world state
     * @param  direction    where block intends to move
     */
    public boolean canBlockMove(World world, int direction) {
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);

        for(Sprite currSpr : spritesAtPos){
            //block can't if next pos has a block or a wall
            if(currSpr.getRoadBlock() ||
            (currSpr instanceof Block && !currSpr.getRoadBlock())) {
                return false;
            }
        }
        //no obstacles then move on
        return true;
    }

    /**
     * How block moves
     * @param  world        compare to world state
     * @param  direction    where block intends to move
     */
    public void move(World world, int direction){
    	this.setDirection(direction);
        Position nextPos = this.getPosition().nextPosition(direction);
        //if can move then move
        if(this.canBlockMove(world, direction)){
            MoveStack.recordMove(this, nextPos);
            this.setPosition(nextPos);
        }
    }

}
