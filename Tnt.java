
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tnt extends Block {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world, int direction, int delta) {
    }

    public void render(Graphics g){
    	super.render(g)
    }

    //cracked wall edit
    public boolean canBlockMove(World world, int direction) {
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);

        for(Sprite currSpr : spritesAtPos){
            //change permit to move through Cracked
            if(currSpr.instanceOf(Block) ||
            (currSpr.getRoadBlock() && !currSpr.instanceOf(Cracked)) ){
                return false;
            }
        }
        return true;
    }

    public void move(World world, int direction){
        super.move(world,direction);
    }

    public void explode(){
        this.setRender(false);
        this.setRoadBlock(false);
    }

}
