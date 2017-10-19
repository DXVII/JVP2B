import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Door extends Sprite {
    public Door(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.setRoadBlock(true);
    }

    public void render(Graphics g){
        super.render(g);
    }

    public void doorOpen(){
        this.setRoadBlock(true);
        this.setRender(true);
    }
    public void doorClose(){
        super.suppress();
    }
}
