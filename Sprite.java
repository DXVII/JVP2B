
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Sprite {
    private Image pic;
	private boolean toRender;
    private Position position;
    private boolean roadBlock;


    public Sprite(String image_src, Position position) throws SlickException {
		this.position = position;
		this.pic = new Image(image_src);
        this.toRender = true;
    }

    public void update(World world, int direction, Input input, int delta) {
    }

	public void render(Graphics g) {
        int x = this.position.getX();
        int x = this.position.getY();
        float xPix = App.TILE_SIZE*(x-0.5*World.lvlWidth)+App.SCREEN_WIDTH/2;
        float yPix = App.TILE_SIZE*(y-0.5*World.lvlHeight)+App.SCREEN_HEIGHT/2;
        pic.draw(this.xPix,this.yPix);
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
    public void setRoadBlock(boolean change){
        this.roadBlock = change;
    }


    public boolean getRender(){
        return this.toRender;
    }
    public void setRender(boolean change){
        this.toRender = change;
    }

}
