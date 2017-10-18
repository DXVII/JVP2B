import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Enemy extends Sprite {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, postion);
        this.roadBlock = false;
    }

    public void update(Input input, int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }

    //unique method, if Block object can move
    public boolean canBlockMove(World world, int direction) {
        Postion nextPos = Postion.nextPosition(this.position, direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);

        for(Sprite currSpr : spritesAtPos){
            //currblock can't move if encounters roadBlokck or another Block
            if(currSpr.getRoadBlock() || currSpr.instanceOf(Block)){
                return false;
            }
        }
        return true;

    }



}
