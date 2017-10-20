/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Mage extends Enemy {
    /**
     * Mage enemy
     * @param  image_src      path to image file
     * @param  position       where exists
     * @throws SlickException if it fails
     */
    public Mage(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }
    /**
     * How the mage moves; note mage level CAN be beaten!
     * @param world so changes can be recorded in the world
     */
    @Override
    public void move(World world){
        // get mage and player pos
        Position magePos = this.getPosition();
        Position playerPos = world.getPlayerPos();

        // displacement of x and y
        int dx = playerPos.getX() - magePos.getX();
        int dy = playerPos.getY() - magePos.getY();
        // bigger distance covered, mage limited to one move at a time
        if(dx >= dy){
            //if player is more to the right than mage is -> move right
            if(dx > 0){
                if(this.canEnemyMove(world, World.RIGHT)){
                    this.setPosition(this.getPosition().nextPosition(World.RIGHT));
                }
            //if player is more to the left than mage is -> move left
            } else {
                if(this.canEnemyMove(world,World.LEFT)){
                    this.setPosition(this.getPosition().nextPosition(World.LEFT));
                }
            }
        } else {
            //if player is more to the up than mage is -> move up
            if(dy > 0){
                if(this.canEnemyMove(world,World.DOWN)){
                    this.setPosition(this.getPosition().nextPosition(World.DOWN));
                }
            //if player is more to the down than mage is -> move down
            } else {
                if(this.canEnemyMove(world,World.UP)){
                    this.setPosition(this.getPosition().nextPosition(World.UP));
                }
            }
        }


    }

}
