import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Explosion extends Sprite {

    private double time = 0;
    public static final double EXPIRATION = 400;

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world, int direction, int delta) {
        this.time += delta;
        if(time >= Explosion.EXPIRATION){
            this.setRender(false);
        }
    }

    public void render(Graphics g){
        super.render(g)
    }


}
