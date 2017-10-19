import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cracked extends Sprite {

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.setRoadBlock(true);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }

    public void explode(World world){
        this.setRoadBlock(false);
        this.setRender(false);
        world.addExplosion(this.getPosition());
    }

}
