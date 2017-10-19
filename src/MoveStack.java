import java.util.*;

public class MoveStack{

    private Sprite unit;
    private Position newPos;
    private Position oldPos;

    private static Stack<MoveStack> moveHistory = new Stack<MoveStack>;

    public MoveStack(Sprite unit, Position oldPos, Position newPos, ){
        this.unit = unit;
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

    public static void undoMoves(){
        //check stack underflow
        while(!moveHistory.empty()){
            MoveStack moved = moveHistory.pop();
            //continually restore old pos of to objects ice and stone until
            moved.unit.setPosition(oldPos);
            //you hit player
            if(moved.unit.isInstance(Player)){
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
}
