
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

	//new player direction
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
			App.reset();
		}

	//Undo
		else if(iskeyPressed(input.KEY_Z)) {
			MoveStack.undoMoves();
			if(this.nMoves > 0){
				this.nMoves -= 1;
			}
		}

	// Next Level
		else if(iskeyPressed(input.KEY_N)){
			App.nextLevel();
		}


	//Non-system command
		else {
			// C style for loop (incase objects get removed)//
			for(int i=0; i<spriteArray.size(); i++) {
				Sprite currSpr = spriteArray.get(i);

          ///////////////////////////////////////////////////////////
			// Time ticks & time based movement
				// Skeleton
				if(currSpr.instanceOf(Skeleton)){
					currSpr.update(this.world, this.delta);
					currSpr.move(this);
				}
				// Ice
				else if(currSpr.instanceOf(Ice)){
					/*if slide block is still true*/
					currSpr.update(this, delta);
					/*ice move incorporated in update*/
				}
				// Explosion
				else if(currSpr.instanceOf(Explosion)){
					currSpr.update(this, delta);
					/* expiration in update */
				}

          ///////////////////////////////////////////////////////////
			// Check collision events

				//enemy kill player
				if(currSpr.instanceOf(Enemy) && ((currSpr.getPosition()).equals(this.currPlayPos))) {
					App.reset();
				}

				//new target covered
				if(currSpr.instanceOf(Target)){
					for(Sprite checkSpr : this.spriteArray){

						//update if newly covered
						if(/*share pos, is block && target wasn't covered*/){
							nTargetsCov+=1;
							(Target)currSpr.cover();
							break;
						}
					}
					//update is just uncovered
					if(/*target toggle == True*/){
						nTargetsCov-=1;
						//targetTog = false;
					}
				}

				// check if you've won the game (after blocks move)
				App.checkWin(nTargets, nTargetsCov);


				// block dynamics: ice, tnt, stone
				if(currSpr.instanceOf(Block)) {
					for(Sprite checkSpr : this.spriteArray) {
						if((currSpr.getPosition()).equals(checkSpr)){

							// collision dynamics
							if (checkSpr.instanceOf(Player) ||
							checkSpr.instanceOf(Rogue)) {
								currSpr.move(this, checkSpr.getDirection());
							}
							// switch and door dynamics
							else if(/*doorSwitch*/) {
								for(/*sprite array until door*/) {
									//door.toggle();
								}
							}

						}
					}

				}

			//tnt explosionn dynamics
				if(/*Cracked*/) {
					for(/*sprite array*/) {
						if(/*tnt && same position*/) {
							//cracked.explode();
						}
					}
				}

			}// endloop collision events


			//Player Movement
			this.playerMoved = false;
			if(input.iskeyPressed(Input.KEY_UP)) {
				this.direction = UP;
				this.playerMoved = true;
				this.nMoves += 1;
				player.move(this, direction);

			}
			if(input.iskeyPressed(Input.KEY_DOWN)) {
				this.direction = DOWN;
				this.playerMoved = true;
				this.nMoves += 1;
				player.move(this, direction);
			}
			if(input.iskeyPressed(Input.KEY_LEFT)) {
				this.direction = LEFT;
				this.playerMoved = true;
				this.nMoves += 1;
				player.move(this, direction);
			}
			if(input.iskeyPressed(Input.KEY_RIGHT)) {
				this.direction = RIGHT;
				this.playerMoved = true;
				this.nMoves += 1;
				player.move(this, direction);
			}
			//keep track of player movements
			currPlayPos = player.getPosition();
			currPlayDir = player.getDirection();

			//player observing enemy movement
			if(this.playerMoved){
				for(int index; index < spriteArray.size(); index++) {
					if(currSpr.instanceOf(Rogue) ||
					currSpr.instanceOf(Mage)){
						currSpr.move(this);
					}
				}
			}
		}

	}


///////////////////////////////////////////////////////////////////////////////

	// render floor then player to enure player is always after
	public void render(Graphics g) {
		for (Sprite sprite : spriteArray) {
			spriteTile.render(g);
		}
		//draw move count
	}


///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Sprites> getSpritesAt(Position position){
		ArrayList<Sprite> list = new ArrayList<Sprite>();
		for(Sprite currSpr : this.spriteArray){
			if(currSpr.position.equals(position)){
				list.add(currSpr);
			}
		}
		return list;
	}

	public void addExplosion(Position position){
		this.spriteArray.add(new Explosion(position));
	}

	public Position getPlayerPos(){
		return this.currPlayPos;
	}

	public void setPlayerPos(Position change){
		this.currPlayPos = change;
	}
