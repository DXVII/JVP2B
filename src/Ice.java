/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
/**
 * Ice block keeps moving in a given direction until it can't move
 */
public class Ice extends Block {
    // timer and time limit details
    private static final float TIME_LIMIT = 250;
    private float time = 0;
    // sliding flag and direction
    private boolean sliding = false;
    private int slideDir;
    /**
     * Ice Block
     * @param  image_src     image file path
     * @param  position      where it exists
     * @throws SlickException if creation fails
     */
    public Ice(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    /**check if was sliding and can slide
     * @param World world [description]
     * @param int   delta [description]
     */
    @Override
    public void update(World world,  int delta) {
        // if was sliding continue sliding
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
        } else {
        	//record moves when not sliding
        	MoveStack.recordMove(this, this.getPosition());
        }
    }

    /**how Ice moves
     * @param world         ammend changes to world
     * @param direction     where it's moving
     */
    @Override
    public void move(World world, int direction){
        if(this.canBlockMove(world, direction)){
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
    /**
     * Set if Ice still slides
     * @param change what to switch sliding to
     */
	public void setSliding(boolean change) {
		this.sliding = change;
		
	}

}
