/**
 * @author David Pham 756598
 */
import java.util.*;
/**
 * A Class that keeps track of player and block history
 */
public class MoveStack{
    // stack is kept alive statically and cleared upon a new level
    private static Stack<MoveStack> moveHistory = new Stack<MoveStack>();
    // unit is either player or a block
    private Sprite unit;
    private Position newPos;
    private Position oldPos;
    /**
     * A movestack keeps track of where a unit is and has been
     * @param  unit          the moving object
     * @param  oldPos        where it was
     * @param  newPos        where it had just moved
     */
    public MoveStack(Sprite unit, Position oldPos, Position newPos){
        this.unit = unit;
        this.setOldPos(oldPos);
        this.setNewPos(newPos);
    }
    /**
     * takes object stored in stack and passes old data back to it
     */
    public static void undoMoves(){
        //check stack underflow
        while(!moveHistory.empty()){
            MoveStack moved = moveHistory.pop();
            //continually restore old pos of to objects ice and stone until
            moved.unit.setPosition(moved.getOldPos());

            // sometimes ice block continues sliding upon undo
            if(moved.unit instanceof Ice) {
                Ice iceBlock = (Ice)moved.unit;
                iceBlock.setSliding(false);
            }

            //you've reached player
            if(moved.unit instanceof Player){
                break;
            }
        }
    }
    /**
     * Takes a unit in world and records new movement
     * @param unit     object whose new move is to be recorded
     * @param nextPos  new move to be recorded
     */
    public static void recordMove(Sprite unit, Position nextPos){
        //create record: unit type, curr -> old position, next -> new position
        MoveStack newMove = new MoveStack(unit, unit.getPosition(), nextPos);
        //append to move record
        MoveStack.moveHistory.push(newMove);
    }

    /**
     * Clear static stack (upon reaching new level)
     */
    public static void clearStack(){
        MoveStack.moveHistory.clear();
    }
    /**
     * Sets a new position in a units move history
     * @param newPos record new positon
     */
	public void setNewPos(Position newPos) {
		this.newPos = newPos;
	}

    /**
     *  Get a units old position
     * @return oldPos oldPosition to be returned
     */
	public Position getOldPos() {
		return oldPos;
	}
    /**
     * set a units old position
     * @param oldPos old positon to be recorded
     */
	public void setOldPos(Position oldPos) {
		this.oldPos = oldPos;
	}
}
