import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Cracked extends Sprite {

    public Cracked(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.setRoadBlock(true);
    }

    public void explode(World world){
        super.remove(world, this);
        try {
			world.addExplosion(this.getPosition());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
