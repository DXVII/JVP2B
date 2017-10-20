/**
 * @author David Pham 756598
 */
public class Position {
    //cell coordinate
    private int x;
    private int y;

    /**
     * Creates a position
     * @param  x   x tile coordinate
     * @param  y   y tile coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Takes currrent position and gives next one in a certain direction
     * @param  direction  where to look
     * @return newfound position
     */
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

    /**
     * [equals description]
     * @param  position  other position to be compared with
     * @return whether true or false
     */
    public boolean equals(Position position){
        //equating integer x and y coordinates
        return ((this.x == position.getX()) && (this.y == position.getY()));
    }

    /**
     * returns x tile coordinate
     * @return x tile coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * returns y tile coordinate
     * @return y tile coordinate
     */
    public int getY(){
        return this.y;
    }

}
