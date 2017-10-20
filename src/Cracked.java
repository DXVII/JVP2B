/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Cracked wall disappears upon contact with Tnt
 * and generates an Explosion
 */
public class Cracked extends Sprite {
    /**
     * [Cracked description]
     * @param  image_src     image to be rendered
     * @param  position      position of Cracked Wall
     * @throws SlickException if it fails
     */
    public Cracked(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.setRoadBlock(true);
    }
    /**
     * Cracked Wall explodes upon impact with Tnt
     * @param world remove itself from spriteArray
     * and generate explosion in its place 
     */
    public void explode(World world){
        super.remove(world, this);
        try {
			world.addExplosion(this.getPosition());
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }

}
