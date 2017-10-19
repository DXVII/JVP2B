import java.util.*;

public class MoveStack{

    private Sprite unit;
    private Position newPos;
    private Position oldPos;

    private static Stack<MoveStack> moveHistory = new Stack<MoveStack>();

    public MoveStack(Sprite unit, Position oldPos, Position newPos){
        this.unit = unit;
        this.setOldPos(oldPos);
        this.setNewPos(newPos);
    }

    public static void undoMoves(){
        //check stack underflow
        while(!moveHistory.empty()){
            MoveStack moved = moveHistory.pop();
            //continually restore old pos of to objects ice and stone until
            moved.unit.setPosition(moved.getOldPos());
            //you hit player
            if(moved.unit instanceof Player){
                break;
            }
        }
    }
    //pass stone/ice/player
    public static void recordMove(Sprite unit, Position nextPos){
        //create record: unit type, curr -> old position, next -> new position
        MoveStack newMove = new MoveStack(unit, unit.getPosition(), nextPos);
        //append to move record
        MoveStack.moveHistory.push(newMove);
    }

    public static void clearStack(){
        MoveStack.moveHistory.clear();
    }

	public Position getNewPos() {
		return newPos;
	}

	public void setNewPos(Position newPos) {
		this.newPos = newPos;
	}

	public Position getOldPos() {
		return oldPos;
	}

	public void setOldPos(Position oldPos) {
		this.oldPos = oldPos;
	}
}
