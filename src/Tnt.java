
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
            if(currSpr.isInstance(Block) ||
            (currSpr.getRoadBlock() && !currSpr.isInstance(Cracked)) ){
                return false;
            }
        }
        return true;
    }

    public void move(World world, int direction){
        Position nextPos = this.getPosition().nextPosition(direction);
        if(this.canBlockMove(world, direction)){
            Position nextPos = this.getPosition().nextPosition(direction);
            this.setPosition(nextPos);
            //when tnt exploded no need to recordMove
            if(this.getRender()){
                MoveStack.recordMove(this, nextPos);
            }
        }
    }

    public void explode(){
        this.setRender(false);
        this.setRoadBlock(false);
    }

}
