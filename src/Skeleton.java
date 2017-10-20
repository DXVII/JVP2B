/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/**
 * Skeleton is an enemy that moves up and down a a rate 1 cell/second
 */
public class Skeleton extends Enemy {
    private float time = 0;
    private boolean upward = true;
    private static float SKELE_TIME = 1000;

    /**
     * [Skeleton description]
     * @param image_src     image to be rendered
     * @param position      where it exists
     * @throws SlickException if creation fails
     */
    public Skeleton(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    /**
     * update if can move then moves
     * @param world notify changes of skeleton to world
     * @param delta time increment
     */
    @Override
    public void update(World world,  int delta) {
        this.time += delta;
        // if more than 1 sec can move
        if(this.time >= Skeleton.SKELE_TIME){
            this.move(world);
        }
    }
    /**
     * how Skeleton moves
     * @param world track changes in world
     */
    @Override
    public void move(World world){
        // moving up
        if(this.upward){
            if(this.canEnemyMove(world, World.UP)){
                this.setPosition((this.getPosition()).nextPosition(World.UP));
                // reset time until, wait until next movement
                this.time = 0;
            } else {
                //if blocked off, move back
                this.upward = false;
            }
        // moving down
        } else {
            if(this.canEnemyMove(world, World.DOWN)){
                this.setPosition((this.getPosition()).nextPosition(World.DOWN));
                this.time = 0;
            } else {
                this.upward = true;
            }
        }

    }

}
