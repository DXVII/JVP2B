/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 *  win condition of game if all existing targets are covered
 */
public class Target extends Sprite {
    boolean covered = false;
    /**
     * Target
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Target(String image_src, Position position) throws SlickException {
        super(image_src, position);
    }
    /**
     * If target is covered
     * @return if it is covered
     */
    public boolean isCovered(){
        return this.covered;
    }
    /**
     * cover target
     */
    public void cover(){
        this.covered = true;
    }
    /**
     * uncover target
     */
    public void uncover(){
        this.covered = false;
    }
}
