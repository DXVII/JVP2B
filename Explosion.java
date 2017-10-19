import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Explosion extends Sprite {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.roadBlock = true;
    }

    public void update(World world, int direction, Input input, int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }


}
