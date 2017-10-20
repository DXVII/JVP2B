
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Stone extends Block {

    public Stone(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
    	super.render(g);
    }
}
