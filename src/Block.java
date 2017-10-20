import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;

public abstract class Block extends Sprite {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
    	super.render(g);
    }

    //block asks itself if it can move (if next pos has a block or a wall)
    public boolean canBlockMove(World world, int direction) {
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);

        for(Sprite currSpr : spritesAtPos){

            /*Either blocked, there is another block that is not exploded tnt*/
            //consider sprite removal//
            if(currSpr.getRoadBlock() ||
            (currSpr instanceof Block && !currSpr.getRoadBlock())) {
                return false;
            }
        }
        //no obstacles then move on
        return true;
    }

    public void move(World world, int direction){
    	this.setDirection(direction);
        Position nextPos = this.getPosition().nextPosition(direction);
        if(this.canBlockMove(world, direction)){
            MoveStack.recordMove(this, nextPos);
            this.setPosition(nextPos);
        }
    }

}
