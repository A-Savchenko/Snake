public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;

    public Coordinate(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof Coordinate && this.row == ((Coordinate)o).row && this.col == ((Coordinate)o).col;
    }

    @Override
    public String toString() {
        return this.row + " " + this.col;
    }

    @Override
    public int compareTo(Coordinate o) {
        return 0;
    }
}