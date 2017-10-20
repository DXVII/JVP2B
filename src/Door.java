/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Door is controlled by switch being un/covered
 */
public class Door extends Sprite {
    public Door(String image_src, Position position) throws SlickException {
		super(image_src, position);
        // door begins closed
        this.doorClose();
    }
    /**
     * Closing Door, displays it and blocks movement through it
     */
    public void doorClose(){
        this.setRoadBlock(true);
        this.setRender(true);
    }
    /**
     * Opening door, makes it appear non-existent
     */
    public void doorOpen(){
        super.suppress();
    }
}
