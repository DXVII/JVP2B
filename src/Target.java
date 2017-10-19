
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Target extends Sprite {
    boolean covered = false;

    public Target(String image_src, Position position) throws SlickException {
        super(image_src, position);
    }

	public void render(Graphics g) {
        super.render(g);
	}

    public boolean isCovered(){
        return this.covered;
    }
    public void cover(){
        this.covered = true;
    }
    public void uncover(){
        this.covered = false;
    }
}
