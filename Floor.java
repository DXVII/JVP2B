import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Floor extends Sprite {


    public Sprite(String image_src, Position position) throws SlickException {
        super(image_src, postion);
        this.roadBlock = false;
    }

	public void render(Graphics g) {
        super.render(g);
	}
}