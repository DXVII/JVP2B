import java.util.*;

public class MoveStack{

    private Sprite unit;
    private Position newPos;
    private Position oldPos;

    private static Stack<MoveStack> moveHistory = new Stack<MoveStack>;

    private MoveStack(Sprite unit, Position oldPos, Position newPos, ){
        this.unit = unit;
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

    public static undoMoves(){
        while(!moveHistory.empty()){
            MoveStack moved = moveHistory.pop();
            moved.unit.setPosition(oldPos);
            if(moved.unit.instanceOf(Player)){
                break;
            }
        }
    }

    public static recordMove(Sprite unit, Position nextPos){
        MoveStack newMove = new MoveStack(unit, unit.getPosition(), nextPos);
        MoveStack.moveHistory.push(newMove);
    }

    //clear stack upon new level//
}
