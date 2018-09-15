import java.util.Objects;

public class Coordinate {



    public Coordinate(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    private int posX;
    private int posY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return posX == that.posX &&
                posY == that.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    /**
     *
     * @param posX
     * @param posY
     * @return An instantiation of coordinate we can use.
     */
    public static Coordinate getCoordinate(int posX, int posY)
    {
        return new Coordinate(posX, posY);
    }



}
