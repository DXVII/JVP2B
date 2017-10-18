import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

<<<<<<< HEAD
public class Door extends Sprite {
=======
public class Door extends Wall {
>>>>>>> 1535901f29b4fbb063022dd4252b39563f2d6c64

    public Wall(String image_src, Position position) throws SlickException {
		super(image_src, postion);
    }

    public void render(Graphics g){
        super.render(g)
    }

    public void toggle(){
        /* if toggle was off */
            // unlock
        /* if toggle was on */
            // lock
    }

    /*Method - unlock*/
        //roadBlock false
        // super.super(togRendered) false
}
