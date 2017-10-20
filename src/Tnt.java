
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Tnt extends Block {

    public Tnt(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
    	super.render(g);
    }

    //cracked wall edit
    public boolean canBlockMove(World world, int direction) {
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

    public void explode(World world){
        super.remove(world, this);
    }

}
