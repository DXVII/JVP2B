
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Mage extends Enemy {

    public Mage(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g);
    }

    public boolean canEnemyMove(World world, int direction) {
        return super.canEnemyMove(world, direction);
    }

    public void move(World world){
        // get mage and player pos
        Position magePos = this.getPosition();
        Position playerPos = world.getPlayerPos();

        // displacement of x and y
        int dx = playerPos.getX() - magePos.getX();
        int dy = playerPos.getY() - magePos.getY();
        // bigger distance covered, mage limited to one move at a time
        if(dx >= dy){
            if(dx > 0){
                if(this.canEnemyMove(world, World.RIGHT)){
                    this.setPosition(this.getPosition().nextPosition(World.RIGHT));
                }
            } else {
                if(this.canEnemyMove(world,World.LEFT)){
                    this.setPosition(this.getPosition().nextPosition(World.LEFT));
                }
            }
        } else {
            if(dy > 0){
                if(this.canEnemyMove(world,World.DOWN)){
                    this.setPosition(this.getPosition().nextPosition(World.DOWN));
                }
            } else {
                if(this.canEnemyMove(world,World.UP)){
                    this.setPosition(this.getPosition().nextPosition(World.UP));
                }
            }
        }


    }

}
