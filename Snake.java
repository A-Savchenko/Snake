public class Snake extends SinglyLinkedList<Coordinate> {

	public Snake(int row, int col) {
		super();
		addFirst(new Coordinate(row, col));
		for (int i = 1; i < 5; ++i) {
			addLast(new Coordinate(row, col + i));
		}
	}

	public Snake(int length, int row, int col) {
		super(); // Call the superclass constructor
		for (int i = 0; i < length; i++) {
			addFirst(new Coordinate(row, col - i));
		}
	}

	public Snake() {
		this(3, 3);
	}
}