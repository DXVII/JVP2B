
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Skeleton extends Enemy {
    private boolean leftward = true;
    public Enemy(String image_src, Position position) throws SlickException {
		super(image_src, position);
    }

    public void update(World world,  int delta) {
    }

    public void render(Graphics g){
        super.render(g)
    }
    //rogue moves like player, override default enemy movement
    public boolean canEnemyMove(World world, int direction) {
        Position nextPos = this.getPosition().nextPosition(direction);
		ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);
		// checking all sprites in next position
		for(Sprite currSpr : spritesAtPos){
			//if wall/door/cracked or if Block and block not pushable
			if (currSpr.getRoadBlock() ||
			(currSpr.instanceOf(Block) &&
			!currSpr.canBlockMove(world, direction)) ){
				return false;
			}
			return true;
    }

    public void move(World world){
        // moving left
        if(this.leftward){
            if(this.canEnemyMove(world)){
                this.setPosition(this.nextPosition(World.UP);
                this.time = 0;
            } else {
                //if blocked off, move back
                this.leftward = !this.leftward;
            }
        // moving right
        } else {
            if(this.canEnemyMove(world)){
                this.setPosition(this.nextPosition(World.DOWN);
                this.time = 0;
            } else {
                this.leftward = !this.leftward;
            }
        }

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
