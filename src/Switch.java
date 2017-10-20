/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Switch toggles door
 */
public class Switch extends Sprite {
    /**
     * Switch
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Switch(String image_src, Position position) throws SlickException {
        super(image_src, position);
    }

}
