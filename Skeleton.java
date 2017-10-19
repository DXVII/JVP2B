
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Skeleton extends Enemy {
    private double time = 0;
    private boolean upward = true;
    private static double SKELE_TIME = 1000;
    public Enemy(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world, int direction, int delta) {
        this.time += delta;
    }

    public void render(Graphics g){
        super.render(g)
    }

    public boolean canEnemyMove(World world, int direction) {
        super.canEnemyMove(world);
    }

    public void move(World world){
        // if more than 1 sec can move
        if(this.time >= SKELE_TIME){
            // moving up
            if(this.upward){
                if(this.canEnemyMove(word)){
                    this.setPosition(this.nextPosition(World.UP);
                    this.time = 0;
                } else {
                    //if blocked off, move back
                    this.upward = !this.upward;
                }
            // moving down
            } else {
                if(this.canEnemyMove(word)){
                    this.setPosition(this.nextPosition(World.DOWN);
                    this.time = 0;
                } else {
                    this.upward = !this.upward;
                }
            }

        }
    }

}
