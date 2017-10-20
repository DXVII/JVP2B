/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Stone is a simple block
 */
public class Stone extends Block {
    /**
     * Stone
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Stone(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

}
