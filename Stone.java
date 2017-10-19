
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Stone extends Block {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
    	super.render(g)
    }

    //unique method, if Block object can move
    public boolean canBlockMove(World world, int direction) {
        super.canBlockMove(world, direction);
    }

    public void move(World world, int direction){
        super.move(world,direction);
    }

}
