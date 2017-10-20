//David Pham 756598

/** World
 * holds the game world and all worldly actions and states'
 * most sprites communicate through the world
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
//Directions
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

// Game stuff
	//level dimensions of the current world
	private int lvlWidth;
	private int lvlHeight;
	// compare number of targets covered to number of targets in world to win
	private int nTargets = 0;
	private int nTargetsCov = 0;
	//number of moves a player has made, undoing reduces this
	private int nMoves = 0;

//Sprite stuff
	// where all the sprites live
	private ArrayList<Sprite> spriteArray;
	// door sprite to be easily accessed by switch
	private Sprite door;

//Player stuff
	//rogue and mage be watching
	private boolean playerMoved = false;
	// player is easier to stalk
	private Position currPlayPos;
	// intermediary variable showing all sprites of at a requested location
	private ArrayList<Sprite> samePosSprites;


	public World(String lvlAddress) throws FileNotFoundException, SlickException {
		this.spriteArray = Loader.loadSprites(this,lvlAddress);
		this.findDoor();
	}
/** UPDATE, updates the world state based on class and human keyboard events
 * @param input input from keyboard
 * @param delta time since last update
 */
	public void update(Input input, int delta)
	throws FileNotFoundException, SlickException {

	//Restart Command
		if(input.isKeyPressed(Input.KEY_R)) {
			App.reset();
		}

	//Undo Command
		else if(input.isKeyPressed(Input.KEY_Z)) {
			MoveStack.undoMoves();
			if(this.nMoves > 0){
				this.nMoves -= 1;
			}
		}

	// Next Level Command
		else if(input.isKeyPressed(Input.KEY_N)){
			App.nextLevel();
		}
	//Previous Level Command
		else if(input.isKeyPressed(Input.KEY_B)) {
			App.prevLevel();
		}


	//Non-system related commands
		else {
		//Check win
			if(this.nTargets == this.nTargetsCov){
				App.nextLevel();
			}
		//Looking at all the Sprites
			// C style for loop (incase objects get removed)//
			for(int i=0; i<spriteArray.size(); i++) {
				Sprite currSpr = spriteArray.get(i);


			// Time ticks & time based movement //
				// Skeleton
				if(currSpr instanceof Skeleton){
					//moves if a time limit is hit
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


			// Check collision events //

				//Player Death when Enemy matches player position
				// don't let player die if no move attempt made :)
				if(currPlayPos != null && currSpr instanceof Enemy &&
				((currSpr.getPosition()).equals(this.currPlayPos)) ) {
					// reset level upon death
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

							// and now no blocks detected -> it has been removed
							nTargetsCov-=1;
							((Target) currSpr).uncover();
						}

					}// target loop

				}// target conditions


				// Switch and Door dynamics
				if(currSpr instanceof Switch) {
					//obtain all sprites on switch and check if block
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites){
						//ooh a block, tell door to hide or.supress()!
						if(checkSpr instanceof Block){
							((Door) this.door).doorOpen();
							break;
						}
						//no block, stop hiding door
						((Door) this.door).doorClose();

					}
				}

				// Block movement: Ice, Tnt, Stone
				if(currSpr instanceof Block) {
					//if something has collided into block, they need to move
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites) {
						// collisions only with rogue and player
						// block to move in the same direction they were moving
						if (checkSpr instanceof Player) {
							((Block)currSpr).move(this,checkSpr.getDirection());
						}
						else if (checkSpr instanceof Rogue) {
							((Block)currSpr).move(this,checkSpr.getDirection());
						}
					}
				}

				//Tnt, Cracked (wall) and Explosion dynamics
				if(currSpr instanceof Cracked) {
					this.samePosSprites = getSpritesAt(currSpr.getPosition());
					for(Sprite checkSpr : this.samePosSprites) {
						// TNT meet Cracked (wall)
						if(checkSpr instanceof Tnt) {
							//first you explode
							((Tnt) checkSpr).explode(this);
							// then you explode
							((Cracked) currSpr).explode(this);
						}
					}
				}

				//Player Movement
				// mage and rogue need to know if player has moved since
				this.playerMoved = false;
				if(currSpr instanceof Player) {
					//if moved, update playerMoved, invoke move
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
					//player location easily accessed
					currPlayPos = currSpr.getPosition();
				}


				//Mage an Rogue are stalkers, move when player has
				if(this.playerMoved){
					for(Sprite enemySpr : spriteArray) {
						if(enemySpr instanceof Rogue||enemySpr instanceof Mage){
							((Enemy) enemySpr).move(this);
						}
					}
				}
			}// endloop collision events

		}//system commands

	}//update


	/** Render floor then player to enure player is always after
	 * @param g graphics
	 */


	public void render(Graphics g) {
		for (Sprite sprite : this.spriteArray) {
			sprite.getWorldDim(this);
			sprite.render(g);
		}
		g.drawString("Moves made: "+Integer.toString(nMoves),
				App.TXT_BUFF, App.SCREEN_HEIGHT-App.TXT_BUFF);
	}


////////////////////////// Special Methods /////////////////////////////////////

	/** getSpritesAt: retrieves sprites at a given location
	 * @param position takes a position to check sprites
	 * @return sprites at given location
	*/
	public ArrayList<Sprite> getSpritesAt(Position position){
		//make a new ArrayList of sprites to return
		ArrayList<Sprite> list = new ArrayList<Sprite>();
		for(Sprite currSpr : this.spriteArray){
			if(currSpr.getPosition().equals(position)){
				//appends all sprites at a given location
				list.add(currSpr);
			}
		}
		return list;
	}
	/** addExplosion: makes an explosion appear at a given location
	 * @param position takes a position to put explosion
	*/
	public void addExplosion(Position position) throws SlickException{
		this.spriteArray.add(new Explosion("explosion", position));
	}
/**
 * gets point of door and gives it to world variable 'door'
 */
	public void findDoor(){
		for(Sprite spr : this.spriteArray){
			if(spr instanceof Door){
				this.door = spr;
			}
		}
	}
////////////////////////// Getters and Setters ////////////////////////////////
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

	public void removeSprite(Sprite sprite) {
		this.spriteArray.remove(sprite);

	}


}
