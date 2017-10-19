import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cracked extends Sprite {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, postion);
        this.roadBlock = true;
    }

    public void update(Input input, int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }

    public void explode(World world){
        this.setRoadBlock(false);
        this.setRender(false);
        /* add new Explosion to world with same position */
    }

}
