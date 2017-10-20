
public class Position {
    //cell coordinate
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position nextPosition(int direction){
        int newX = this.getX();
        int newY = this.getY();
        if(direction == World.UP){
            newY -= 1;
        }
        else if (direction == World.DOWN){
            newY += 1;
        }
        else if (direction == World.LEFT){
            newX -= 1;
        }
        else if (direction == World.RIGHT){
            newX += 1;
        }
        return new Position(newX,newY);
    }

    public boolean equals(Position position){
        //equating integer x and y coordinates

        return ((this.x == position.getX()) && (this.y == position.getY()));
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}
