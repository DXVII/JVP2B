import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Cracked extends Sprite {

    public Cracked(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.setRoadBlock(true);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g);
    }

    public void explode(World world){
        super.suppress();
        try {
			world.addExplosion(this.getPosition());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
