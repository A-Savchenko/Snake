
public class SnakeBoard
{
	private int widthOfBoard;
	private int heightOfBoard;
	private char[][] board;



	public SnakeBoard( int heightOfBoard,  int widthOfBoard) {
		this.heightOfBoard = heightOfBoard;
		this.widthOfBoard = widthOfBoard;
		board = new char[heightOfBoard + 2][widthOfBoard + 2];
		initializeBoard();
	}

	public void printBoard(Snake snake, Coordinate targetCoordinate) {
		initializeBoard();
		putSnakeOnBoard(snake);

		if (isValidLocation(targetCoordinate) &&
				(snake.get(0).getRow() != targetCoordinate.getRow() ||
						snake.get(0).getCol() != targetCoordinate.getCol())) {
			board[targetCoordinate.getRow()][targetCoordinate.getCol()] = '+';
		}

		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void initializeBoard() {
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				board[i][j] = ' ';
			}
		}
		for (int k = 0; k < board[0].length; ++k) {
			board[0][k] = (board[board.length - 1][k] = '-');
		}
		for (int l = 0; l < board.length; ++l) {
			board[l][0] = (board[l][board[0].length - 1] = '|');
		}
		 char[] array = board[0];
		 int n = 0;
		 char[] array2 = board[0];
		 int n2 = board[0].length - 1;
		 char[] array3 = board[board.length - 1];
		 int n3 = 0;
		 char[] array4 = board[board.length - 1];
		 int n4 = board[0].length - 1;
		 char c = '+';
		array3[n3] = (array4[n4] = c);
		array[n] = (array2[n2] = c);
	}

	public void putSnakeOnBoard(Snake snake) {
		if (snake.size() < 1) {
			return;
		}
		if (isValidLocation(snake.get(0))) {
			board[snake.get(0).getRow()][snake.get(0).getCol()] = '@';
		}
		for (int i = 1; i < snake.size(); ++i) {
			if (isValidLocation(snake.get(i))) {
				board[snake.get(i).getRow()][snake.get(i).getCol()] = '*';
			}
		}
	}

	public boolean isValidLocation(Coordinate coordinate) {
		if (coordinate == null) {
			return false;
		}

		int row = coordinate.getRow();
		int col = coordinate.getCol();
		return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
	}

	public int getWidth() {
		return widthOfBoard;
	}

	public int getHeight() {
		return heightOfBoard;
	}

	public char getChar( Coordinate coordinate) {
		return board[coordinate.getRow()][coordinate.getCol()];
	}
}