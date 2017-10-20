/**
 * @author David Pham 756598
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
/**
 * Sprites the the key in game objects
 */
public abstract class Sprite {
    //identifing attributes
    private Image pic;
    private Position position;
    private int direction;

    //interactive atttributes
    private boolean toRender;
    private boolean roadBlock;

    // for image generation
    private int worldWidth;
    private int worldHeight;

    /**
     * Sprite
     * @param  image_src     file path
     * @param  position      where it is
     * @throws SlickException if creation fails
     */
    public Sprite(String image_src, Position position) throws SlickException {
		this.position = position;
		this.pic = new Image(App.IMG_TXT + image_src+ App.IMG_TXT_END);
        this.toRender = true;
        this.roadBlock = false;
    }
    /**
     * how sprites get notified of wordly/control changes
     * @param world the world state
     * @param delta changes in time
     */
    public void update(World world,  int delta) {
    }
    /**
     * How and where a sprite appears on screen
     * @param Graphics g [description]
     */
	public void render(Graphics g) {
        int x = this.position.getX();
        int y = this.position.getY();
        // unique mathemtical formula
        float xPix = (float)(App.TILE_SIZE *(x - 0.5*(this.worldWidth)) + App.SCREEN_WIDTH/2);
        float yPix = (float)(App.TILE_SIZE *(y - 0.5*(this.worldHeight))+ App.SCREEN_HEIGHT/2);
        // only drawn if allowed to be
        if(this.toRender){
            pic.drawCentered(xPix,yPix);
        }
	}
    /**
     * Get height and width of a level to be drawn
     * @param World world [description]
     */
	public void getWorldDim(World world) {
		this.worldWidth = world.getLvlWidth();
		this.worldHeight = world.getLvlHeight();
	}
    /**
     * Obtain a sprites position
     * @return its position
     */
    public Position getPosition(){
        return this.position;
    }
    /**
     * change a sprites position
     * @param position new position
     */
    public void setPosition(Position position){
        this.position = position;
    }
    /**
     * get the direction a sprite is facing
     * @return [description]
     */
    public int getDirection(){
        return this.direction;
    }
    /**
     * change the direction a sprite is facing
     * @param direction where it will now face
     */
    public void setDirection(int direction){
        this.direction = direction;
    }
    /**
     * temporarily hide a sprite
     */
    public void suppress() {
        this.roadBlock = false;
        this.toRender = false;
    }
    /**
     * check is a sprite is blocking other sprites
     * @return blocking or no
     */
    public boolean getRoadBlock(){
        return this.roadBlock;
    }
    /**
     * Change whether or not a sprite still blocks stuff
     * @param boolean block or no
     */
    public void setRoadBlock(boolean change){
        this.roadBlock = change;
    }
    /**
     * is an image to be rendered
     * @return render or no
     */
    public boolean getRender(){
        return this.toRender;
    }
    /**
     * Changes if an image is to be renfered
     * @param boolean can still render or no
     */
    public void setRender(boolean change){
        this.toRender = change;
    }
    /**
     * Removes a sprite from existence
     * @param world  ammend in world
     * @param sprite the sprite itself
     */
	public void remove(World world, Sprite sprite) {
		world.removeSprite(this);
	}

}
