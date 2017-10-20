
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
	private Sprite door;
	//passed to enemy units
	private boolean playerMoved = false;

	//questionable if needed
	private Position currPlayPos;
	private ArrayList<Sprite> samePosSprites;


	public World(String lvlAddress) throws FileNotFoundException, SlickException {
		this.spriteArray = Loader.loadSprites(this,lvlAddress);
		this.door = findDoor();
	}


	public void update(Input input, int delta) throws FileNotFoundException, SlickException {

	//Restart
		if(input.isKeyPressed(Input.KEY_R)) {
			App.reset();
		}

	//Undo
		else if(input.isKeyPressed(Input.KEY_Z)) {
			MoveStack.undoMoves();
			if(this.nMoves > 0){
				this.nMoves -= 1;
			}
		}

	// Next Level
		else if(input.isKeyPressed(Input.KEY_N)){
			App.nextLevel();
		}


	//Non-system command
		else {
			if(this.nTargets == this.nTargetsCov){
				App.nextLevel();
			}
			// C style for loop (incase objects get removed)//
			for(int i=0; i<spriteArray.size(); i++) {
				Sprite currSpr = spriteArray.get(i);


			// Time ticks & time based movement
				// Skeleton
				if(currSpr instanceof Skeleton){
					((Skeleton) currSpr).update(this, delta);
				}
				// Ice
				else if(currSpr instanceof Ice){
					/*if slide block is still true*/
					((Ice) currSpr).update(this, delta);
					/*ice move incorporated in update*/
				}
				// Explosion
				else if(currSpr instanceof Explosion){
					((Explosion) currSpr).update(this, delta);
					/* expiration in update */
				}


			// Check collision events

				//Player Death
				System.out.println(currSpr);
				if(currSpr instanceof Enemy &&
				((currSpr.getPosition()).equals(this.currPlayPos)) ) {
					App.reset();
				}

				//Target Dynamics
				if(currSpr instanceof Target){
					//Target checks sprites at its position
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites){

						// target +1 if was not covered
						if(!((Target) currSpr).isCovered()){
							//and suddenly sees block, target +1
							if(checkSpr instanceof Block){
								nTargetsCov+=1;
								// check if you've won the game
								//block is now covered
								((Target) currSpr).cover();
								break;
							}
						//if was blocked,
						} else {

							//  and still sees block end loop
							if(checkSpr instanceof Block){
								/*nothing special*/
								break;
							}

							// and now no blocks detected->it has been removed
							nTargetsCov-=1;
							((Target) currSpr).uncover();
						}

					}// target loop
					
					
				}// target conditions
				
				
				// switch and door dynamics
				if(currSpr instanceof Switch) {
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites){
						if(checkSpr instanceof Block){
							((Door) this.door).doorOpen();
							break;
						}
						((Door) this.door).doorClose();

					}
				}

				// block movement: ice, tnt, stone
				if(currSpr instanceof Block) {
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
						
					for(Sprite checkSpr : this.samePosSprites) {
						// collision only with rogue and player
						if (checkSpr instanceof Player) {
							System.out.println(currSpr.getDirection());
							((Block) currSpr).move(this, checkSpr.getDirection());
						}
						else if (checkSpr instanceof Rogue) {
							((Block) currSpr).move(this, checkSpr.getDirection());
						}
					}
				}

				//tnt explosion dynamics
				if(currSpr instanceof Cracked) {
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites) {
						if(checkSpr instanceof Tnt) {
							//((Tnt) checkSpr).explode(this);
							//((Cracked) currSpr).explode(this);
						}
					}
				}

				//Player Movement
				if(currSpr instanceof Player) {
					this.playerMoved = false;
					if(input.isKeyPressed(Input.KEY_UP)) {
						this.playerMoved = true;
						this.nMoves += 1;
						((Player) currSpr).move(this, World.UP);

					}
					if(input.isKeyPressed(Input.KEY_DOWN)) {
						this.playerMoved = true;
						this.nMoves += 1;
						((Player) currSpr).move(this, World.DOWN);
					}
					if(input.isKeyPressed(Input.KEY_LEFT)) {
						this.playerMoved = true;
						this.nMoves += 1;
						((Player) currSpr).move(this, World.LEFT);
					}
					if(input.isKeyPressed(Input.KEY_RIGHT)) {
						this.playerMoved = true;
						this.nMoves += 1;
						((Player) currSpr).move(this, World.RIGHT);
					}
					//player movement easily access
					currPlayPos = currSpr.getPosition();
				}

				//player observing enemy movement
				if(this.playerMoved){
					for(Sprite enemySpr : spriteArray) {
						if(enemySpr instanceof Rogue ||
						enemySpr instanceof Mage){
							((Enemy) enemySpr).move(this);
						}
					}
				}
			}// endloop collision events

		}//system commands

	}//update


///////////////////////////////////////////////////////////////////////////////

	// render floor then player to enure player is always after
	public void render(Graphics g) {
		for (Sprite sprite : this.spriteArray) {
			sprite.getWorldDim(this);
			sprite.render(g);
		}
		//draw move count && fps
	}


///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Sprite> getSpritesAt(Position position){
		ArrayList<Sprite> list = new ArrayList<Sprite>();
		for(Sprite currSpr : this.spriteArray){
			if(currSpr.getPosition().equals(position)){
				list.add(currSpr);
			}
		}
		return list;
	}

	public void addExplosion(Position position) throws SlickException{
		//add path for explosion pic
		this.spriteArray.add(new Explosion("explosion", position));
	}

	public Sprite findDoor(){
		for(Sprite spr : this.spriteArray){
			if(spr instanceof Door){
				return spr;
			}
		}
		//check this again
		return door;
	}
//////////////////////////////////////////////////////////////////////////////
	public Position getPlayerPos(){
		return this.currPlayPos;
	}

	public void setPlayerPos(Position change){
		this.currPlayPos = change;
	}

	public int getLvlWidth(){
		return this.lvlWidth;
	}

	public void setLvlWidth(int width){
		this.lvlWidth = width;
	}

	public int getLvlHeight(){
		return this.lvlHeight;
	}
	public void setLvlHeight(int height){
		this.lvlHeight = height;
	}
	public int getNTargets(){
		return this.nTargets;
	}
	public void setNTargets(int nTargets){
		this.nTargets = nTargets;
	}


}
