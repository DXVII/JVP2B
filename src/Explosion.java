import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Explosion extends Sprite {

    private float time = 0;
    public static final float EXPIRATION = 400;

    public Explosion(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
        this.time += delta;
        if(time >= Explosion.EXPIRATION){
            super.suppress();
        }
    }

    public void render(Graphics g){
        super.render(g);
    }


}
