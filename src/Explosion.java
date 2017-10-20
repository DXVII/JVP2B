/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Explosion appears on Tnt and Cracked Wall collision
 */
public class Explosion extends Sprite {
    //time dictated before it expires
    private float time = 0;
    public static final float EXPIRATION = 400;
    /**
     * Explosion appears at Tnt and Cracked Wall collision
     * @param  image_src     image path to be rendered
     * @param  position      where it is to be created
     * @throws SlickException if it fails
     */
    public Explosion(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    /**
     * Update() checks if the image is to expire yet
     * @param world its place in the world
     * @param delta time elapsed since last update
     */
    public void update(World world,  int delta) {
        this.time += delta;
        if(time >= Explosion.EXPIRATION){
            super.suppress();
        }
    }


}
