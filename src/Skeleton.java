import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Skeleton extends Enemy {
    private float time = 0;
    private boolean upward = true;
    private static float SKELE_TIME = 1000;

    public Skeleton(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
        this.time += delta;
        // if more than 1 sec can move
        if(this.time >= Skeleton.SKELE_TIME){
            this.move(world);
        }
    }

    public void render(Graphics g){
        super.render(g);
    }

    public boolean canEnemyMove(World world, int direction) {
        return super.canEnemyMove(world, direction);
    }

    public void move(World world){
        // moving up
        if(this.upward){
            if(this.canEnemyMove(world, World.UP)){
                this.setPosition((this.getPosition()).nextPosition(World.UP));
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
