
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Rogue extends Enemy {
    
	private boolean leftward = true;
    
    public Rogue(String image_src, Position position) throws SlickException {
		super(image_src, position);
		this.setDirection(World.LEFT);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g);
    }
    //rogue moves like player, override default enemy movement
    public boolean canEnemyMove(World world, int direction) {
        Position nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if wall/door/cracked or if Block and block not pushable
			if (currSpr.getRoadBlock() ||
			(currSpr instanceof Block && 
			(!((Block) currSpr).canBlockMove(world, direction))) ){
				return false;
			}
		}
		return true;
    }

    public void move(World world){
        // moving left
        if(this.leftward){
            if(this.canEnemyMove(world, World.LEFT)){
                this.setPosition(this.getPosition().nextPosition(World.LEFT));
                this.setDirection(World.LEFT);
            } else {
                //if blocked off, move back
                this.leftward = false;
                this.setDirection(World.RIGHT);
            }
        // moving right
        } else {
            if(this.canEnemyMove(world, World.RIGHT)){
                this.setPosition(this.getPosition().nextPosition(World.RIGHT));
                this.setDirection(World.RIGHT);
            } else {
                this.leftward = true;
                this.setDirection(World.LEFT);
            }
        }

    }
}
