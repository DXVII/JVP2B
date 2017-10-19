
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
	private Position currPlayPos;
	private int currPlayDir;

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
				currSpr = spriteArray.get(i);

			// Time ticks & time based movement
				// Skeleton
				if(currSpr.instanceOf(Skeleton)){
					/*add time*/
					/*skeleton move*/
				}
				// Ice
				else if(currSpr.instanceOf(Ice)){
					/*if slide block is still true*/
					currSpr.update();
					/*ice move*/
				}
				// Explosion
				else if(currSpr.instanceOf(Explosion)){
					/*add time*/
					/* check expire */
				}

		// Check collision events

			//enemy kill player
				if(/*currSpr.instanceOf(Enemy) && (currSpr.getPosition())  players position*/) {
					App.reset();
				}

			//new target covered
				if(currSpr.instanceOf(Target)){
					for(/*sprites*/){
						//update if newly covered
						if(/*share pos, is block && target wasn't covered*/){
							nTargetsCov+=1;
							(Target)currSpr.cover();
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
				if(currSpr.instanceOf(Block)) {
					for(/*over sprite array*/) {
						if(/*shared pos if player or rogue*/) {
							//block.move(currPlayPos,currPlayDir);
						}
						else if(/*doorSwitch*/) {
							for(/*sprite array until door*/) {
								//door.toggle();
							}
						}
					}
				}

			//tnt & cracked wall
				if(/*Cracked*/) {
					for(/*sprite array*/) {
						if(/*tnt && same position*/) {
							//cracked.explode();
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

			currPlayPos = player.getPosition();
			currPlayDir = player.getDirection();

			//player based enemy movement
			if(playerMoved){
				for(int index; index < spriteArray.size(); index++) {
					if(currSpr.instanceOf(Rogue)){
						/*Rogue move*/
					}
					if(currSpr.instanceOf(Mage)){
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

	public ArrayList<Sprites> getSpritesAt(Position position){
		ArrayList<Sprite> list = new ArrayList<Sprite>();
		for(Sprite currSpr : this.spriteArray){
			if(currSpr.position.equals(position)){
				list.add(currSpr);
			}
		}
		return list;
	}
