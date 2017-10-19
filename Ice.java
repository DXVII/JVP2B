
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Ice extends Block {
    private double time = 0;
    private boolean slide = false;
    private static final double TIME_LIMIT = 250;

    public Block(String image_src, Position position) throws SlickException {
		super(image_src, position);
        this.roadBlock = false;
    }

    public void update(World world, int direction, Input input, int delta) {
        if(this.slide){
            this.time += delta;
            //slide every 250ms
            if(time>TIME_LIMIT){
                this.move(world, direction);
                //ice can't slide anymore
                if(!this.canBlockMove(world,direction)){
                    this.slide = false;
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
        super.move(world, direction);
        //can slide
        this.slide = true;
        //reset timer
        this.time = 0;
    }

}
