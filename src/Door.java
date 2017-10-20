import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Door extends Sprite {
    public Door(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.doorClose();
    }

    public void doorClose(){
        this.setRoadBlock(true);
        this.setRender(true);
    }
    public void doorOpen(){
        super.suppress();
    }
}
