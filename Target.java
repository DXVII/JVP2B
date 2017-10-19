
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Target extends Sprite {
    boolean covered = false;

    public Sprite(String image_src, Position position) throws SlickException {
        super(image_src, position);
        this.setRoadBlock = false;
    }

	public void render(Graphics g) {
        super.render(g);
	}

    public boolean isCovered(){
        return this.cover;
    }
    public void cover(){
        this.covered = true;
    }
    public void uncover(){
        this.covered = false;
    }
}
