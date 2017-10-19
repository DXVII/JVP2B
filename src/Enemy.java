
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Enemy extends Sprite {

    public Enemy(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }

    public boolean canEnemyMove(World world, int direction){
        Position nextPos = this.getPosition().nextPosition(direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
        // checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
            //block and enemy have similar behaviour
            if(currSpr.getRoadBlock() ||
            (currSpr.isInstance(Block) &&
            (currSpr.isInstance(Tnt) && !currSpr.getRoadBlock()))) {
                return False;
            }
        }
        return true;
    }

    public void move(World world){
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
