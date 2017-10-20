
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Sprite {
    private Image pic;
	private boolean toRender;
    private Position position;
    private boolean roadBlock;
    private int direction;
    private int worldWidth;
    private int worldHeight;


    public Sprite(String image_src, Position position) throws SlickException {
		this.position = position;
		this.pic = new Image(App.IMG_TXT + image_src+ App.IMG_TXT_END);
        this.toRender = true;
        this.roadBlock = false;
    }

    public void update(World world,  int delta) {
    }

	public void render(Graphics g) {
        int x = this.position.getX();
        int y = this.position.getY();
        float xPix = (float)(App.TILE_SIZE *(x - 0.5*(this.worldWidth)) + App.SCREEN_WIDTH/2);
        float yPix = (float)(App.TILE_SIZE *(y - 0.5*(this.worldHeight))+ App.SCREEN_HEIGHT/2);
        if(this.toRender){
            pic.drawCentered(xPix,yPix);
        }
	}
	
	public void getWorldDim(World world) {
		this.worldWidth = world.getLvlWidth();
		this.worldHeight = world.getLvlHeight();
	}

    public Position getPosition(){
        return this.position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    
    public int getDirection(){
        return this.direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    public void suppress() {
        this.roadBlock = false;
        this.toRender = false;
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

	public void remove(World world) {
		//get arraylist
		//remove itself from arraylist
	}

}
