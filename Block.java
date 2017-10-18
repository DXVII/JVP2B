import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Block extends Sprite {
    private Image pic;
	private boolean togRender;
    private Position position;
    private boolean roadBlock;


    public Block(String image_src, Position position) throws SlickException {
		this.position = position;
		this.pic = new Image(image_src);
        this.togRender = true;
    }

    public void update(Input input, int delta) {
    }

	public void render(Graphics g) {
        int x = this.position.getX();
        int x = this.position.getY();
        float xPix = App.TILE_SIZE*(x-0.5*World.lvlWidth)+App.SCREEN_WIDTH/2;
        float yPix = App.TILE_SIZE*(y-0.5*World.lvlHeight)+App.SCREEN_HEIGHT/2;
        pic.draw(this.xPix,this.yPix);
	}

    //unique method, if Block object can move
    public boolean canBlockMove(World world, int direction) {
        Postion nextPos = Postion.nextPosition(this.position, direction);
        ArrayList<Sprite> spritesAtPos = world.getSpritesAt(nextPos);

        for(Sprite currSpr : spritesAtPos){
            //currblock can't move if encounters roadBlokck or another Block
            if(currSpr.getRoadBlock() || currSpr.instanceOf(Block)){
                return false;
            }
        }
        return true;
        
    }




    public Position getPosition(){
        return this.position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public boolean getRoadBlock(){
        return this.roadBlock;
    }

}
