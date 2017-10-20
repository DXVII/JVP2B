
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

//player is an extension of Sprite

public class Player extends Sprite{

	public Player(String image_src, Position position)
	throws SlickException {
		super(image_src, position);
	}

	private boolean canPlayerMove(World world, int direction){
		Position nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			System.out.println(currSpr.getClass().toString());
			//if wall/door/cracked or if Block and block not pushable
			if (currSpr.getRoadBlock() ||
			(currSpr instanceof Block &&
			!((Block) currSpr).canBlockMove(world, direction)) ){
				return false;
			}
		}
		return true;
	}
	public void move(World world, int direction){
		this.setDirection(direction);
		if(this.canPlayerMove(world, direction)){
			Position nextPos = (this.getPosition()).nextPosition(direction);
			this.setPosition(nextPos);
			MoveStack.recordMove(this, nextPos);
		}

	}

	public void render(Graphics g){
		super.render(g);
	}

	public Position getPosition(){
        return super.getPosition();
    }
    public void setPosition(Position position){
        super.setPosition(position);
    }


	public int getDirection(){
        return super.getDirection();
    }
    public void setDirection(int direction){
        super.setDirection(direction);
    }

}
