import java.io.FileNotFoundException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
	//Direction
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int lvlWidth;
	private int lvlHeight;

	private ArrayList<Sprite> spriteArray;
	private int nTargets = 0;
	private int nTargetsCov = 0;
	private int nMoves = 0;

	//passed to player
	private int direction;

	//passed to enemy units
	private boolean playerMoved = false;
	private Position currPos;
	private int currDirection;

	public World(String lvlAddress) throws FileNotFoundException, SlickException {
		this.spriteArray = Loader.loadSprites(this,lvlAddress);
	}


	public void update(Input input, int delta) {

		//Restart
		if(iskeyPressed(input.KEY_R)) {
			this.loadLvl();
		}

		//Undo
		else if(iskeyPressed(input.KEY_Z)) {
			MoveStack.undoMoves();
		}


		//Non-system command
		else {
			// C style for loop //
			for(int i=0; i<spriteArray.size(); i++) {
				currspr = spriteArray.get(i);

			// Time ticks & time based movement
				// Skeleton
				if(currspr.instanceOf(Skeleton)){
					/*add time*/
					/*skeleton move*/
				}
				// Ice
				else if(currspr.instanceOf(Ice)){
					/*if slide block is still true*/
					/* add time*/
					/*ice move*/
				}
				// Explosion
				else if(currspr.instanceOf(Explosion)){
					/*add time*/
					/* check expire */
				}


		// Check collision events

			//enemy kill player
				if(/*enemy && enemy position == players position*/) {
					World.reload();
				}

			//new target covered
				if(/*target*/){
					for(/*sprites*/){
						//update if newly covered
						if(/*share pos, is block and targetTog == False*/){
							nTargetsCov+=1;
							//targetTog = True
							break;
						}
					}
					//update is just removed
					if(/*target toggle == True*/){
						nTargetsCov-=1;
						//targetTog = false;
					}
				}

			// blocks: ice, tnt, stone
				if(/*block*/) {
					for(/*over sprite array*/) {
						if(/*shared pos if player or rogue*/) {
							block.collide(currPos,currDirection);
						}
						else if(/*doorSwitch*/) {
							for(/*sprite array until door*/) {
								door.toggle();
							}
						}
					}
				}

			//tnt & cracked wall
				if(/*tnt detected*/) {
					for(/*sprite array*/) {
						if(/*cracked wall && same position*/) {
							cracked.collide();
						}
					}
				}

			}


		//Movement
			playerMoved = false;
			if(input.iskeyPressed(Input.KEY_UP)) {
				direction = UP;
				playerMoved = true;
				nMoves += 1;
				player.move(this, direction);

			}
			if(input.iskeyPressed(Input.KEY_DOWN)) {
				direction = DOWN;
				playerMoved = true;
				nMoves += 1;
				player.move(this, direction);
			}
			if(input.iskeyPressed(Input.KEY_LEFT)) {
				direction = LEFT;
				playerMoved = true;
				nMoves += 1;
				player.move(this, direction);
			}
			if(input.iskeyPressed(Input.KEY_RIGHT)) {
				direction = RIGHT;
				playerMoved = true;
				nMoves += 1;
				player.move(this, direction);
			}

			currPos = player.getPosition();
			currDirection = player.getDirection();

			//player based enemy movement
			if(playerMoved){
				for(int index; index < spriteArray.size(); index++) {
					if(currspr.instanceOf(Rogue)){
						/*Rogue move*/
					}
					if(currspr.instanceOf(Mage)){
						/*Mage move*/
					}
				}
			}
		}
		App.checkWin(nTargets, nTargetsCov)
	}

	// render floor then player to enure player is always after
	public void render(Graphics g) {
		for (Sprite sprite : spriteArray) {
			spriteTile.render(g);
		}
		//draw move count

	}

	//method for checking win-condition
	public boolean checkWin(){

	}

	public ArrayList<Sprites> getSpritesAt(Position position){
		ArrayList<Sprite> list = new ArrayList<Sprite>();
		for(Sprite currSpr : this.spriteArray){
			if(currSpr.position.equals(position)){
				list.add(currSpr);
			}
		}
		return list;
	}

///////////////////////////////////////////////////////////////////////////////
//////////////////////////// Getters and Setters //////////////////////////////
///////////////////////////////////////////////////////////////////////////////
