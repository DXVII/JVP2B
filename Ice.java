
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Ice extends Block {
    private double time = 0;
    private boolean sliding = false;
    private int slideDir;
    private static final double TIME_LIMIT = 250;

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
        if(this.sliding){
            this.time += delta;
            //slide every 250ms
            if(time >= Ice.TIME_LIMIT){
                //keep sliding in direction was sliding
                this.move(world, this.slideDir);
                //ice can't slide anymore
                if(!this.canBlockMove(world,this.slideDir)){
                    this.sliding = false;
                }
            }
        }
    }

    public void render(Graphics g){
    	super.render(g);
    }

    //unique method, if Block object can move
    public boolean canBlockMove(World world, int direction) {
        super.canBlockMove(world, direction);
    }

    public void move(World world, int direction){
        if(this.canBlockMove()){
            super.move(world, direction);
            //slide direction assigned externally
            // or passed repetitively from update
            this.slideDir = direction;
            //can slide
            this.sliding = true;
            //reset timer
            this.time = 0;

        }
    }

}
