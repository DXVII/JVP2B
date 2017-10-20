import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Wall extends Sprite {
    public Wall(String image_src, Position position) throws SlickException {
        super(image_src, position);
        this.setRoadBlock(true);
    }
}
