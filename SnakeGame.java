import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SnakeGame
{
	private Snake snake;
	private SnakeBoard board;
	private Coordinate target;
	private int score;
	private  String saveFileName = "snakeGameSave.txt";

	public SnakeGame() {
		snake = new Snake();
		board = new SnakeBoard(20, 25);
		board.putSnakeOnBoard(snake);
		target = generateNewTarget(snake);
		score = 0;
	}

	public static void main( String[] array) {
		SnakeGame sg = new SnakeGame();
		sg.run();
	}

	public void run() {
		printIntroduction();
		int i = 1;
		while (i != 0) {
			board.printBoard(snake, target);
			i = (oneMove() ? 1 : 0);
			if (isGameOver()) {
				i = 0;
				board.printBoard(snake, target);
				System.out.println("\nGame is over\nScore = " + score);
			}
		}
		System.out.println("\nThanks for playing SnakeGame!!");
	}

	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows another * longer.");
		System.out.println("The objective is to grow the longest it can without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}

	public Coordinate generateNewTarget( Snake snake) {
		 ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		for (int i = 0; i < board.getHeight(); ++i) {
			for (int j = 0; j < board.getWidth(); ++j) {
				Coordinate c = new Coordinate(i,j);
				if (board.getChar(c) == ' ') {
					list.add(c);
				}
			}
		}
		return list.get((int)(Math.random() * list.size()));
	}

	public boolean oneMove() {
		char selection = getSelection();
		boolean b = true;
		switch (selection) {
			case 'a': {
				b = moveWest();
				break;
			}
			case 'd': {
				b = moveEast();
				break;
			}
			case 's': {
				b = moveSouth();
				break;
			}
			case 'w': {
				b = moveNorth();
				break;
			}
			case 'q': {
				b = !isQuit();
				break;
			}
			case 'f': {
				b = !saveGame();
				break;
			}
			case 'r': {
				restoreGame();
				break;
			}
			case 'h': {
				helpMenu();
				break;
			}
		}
		if (!b) {
			System.out.println("\nGame is over\nScore = " + score);
		}
		return b;
	}

	public boolean moveWest() {
		return move(new Coordinate(snake.get(0).getRow(), snake.get(0).getCol() - 1));
	}

	public boolean moveEast() {
		return move(new Coordinate(snake.get(0).getRow(), snake.get(0).getCol() + 1));
	}

	public boolean moveSouth() {
		return move(new Coordinate(snake.get(0).getRow() + 1, snake.get(0).getCol()));
	}

	public boolean moveNorth() {
		return move(new Coordinate(snake.get(0).getRow() - 1, snake.get(0).getCol()));
	}

	public boolean move( Coordinate coordinate) {
		if (!board.isValidLocation(coordinate)) {
			return false;
		}
		if (snake.contains(coordinate)) {
			return false;
		}
		snake.add(0, coordinate);
		if (target.equals(coordinate)) {
			++score;
			target = generateNewTarget(snake);
			if (target == null) {
				return false;
			}
		}
		else {
			snake.remove(snake.size() - 1);
		}
		return true;
	}

	public boolean isQuit() {
		return Prompt.getChar("\nDo you really want to quit? (y or n)") == 'y';
	}

	public void helpMenu() {
		System.out.println("\nCommands:\n  w - move north\n  s - move south\n  d - move east\n  a - move west\n  h - help\n  f - save game to file\n  r - restore game from file\n  q - quit");
		Prompt.getString("Press enter to continue");
	}

	public char getSelection() {
		char char1 = ' ';
		int i = 0;
		while (i == 0) {
			char1 = Prompt.getChar("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
			switch (char1) {
				case 97:
				case 100:
				case 102:
				case 104:
				case 113:
				case 114:
				case 115:
				case 119: {
					i = 1;
					continue;
				}
			}
		}
		return char1;
	}

	public boolean isGameOver() {
		int n = 0;
		for (int i = 1; i < board.getHeight(); ++i) {
			for (int j = 1; j < board.getWidth(); ++j) {
				if (board.getChar(new Coordinate(i, j)) == ' ') {
					++n;
				}
			}
		}
		if (n < 5) {
			return true;
		}
		 Coordinate coordinate = snake.get(0);
		 Coordinate coordinate2 = new Coordinate(coordinate.getRow() - 1, coordinate.getCol());
		 Coordinate coordinate3 = new Coordinate(coordinate.getRow() + 1, coordinate.getCol());
		 Coordinate coordinate4 = new Coordinate(coordinate.getRow(), coordinate.getCol() - 1);
		 Coordinate coordinate5 = new Coordinate(coordinate.getRow(), coordinate.getCol() + 1);
		return (!board.isValidLocation(coordinate2) || (board.getChar(coordinate2) != ' ' && board.getChar(coordinate2) != '+')) && (!board.isValidLocation(coordinate3) || (board.getChar(coordinate3) != ' ' && board.getChar(coordinate3) != '+')) && (!board.isValidLocation(coordinate4) || (board.getChar(coordinate4) != ' ' && board.getChar(coordinate4) != '+')) && (!board.isValidLocation(coordinate5) || (board.getChar(coordinate5) != ' ' && board.getChar(coordinate5) != '+'));
	}

	public boolean saveGame() {
		if (Prompt.getChar("\nSave game? (y or n)") != 'y') {
			return false;
		}
		PrintWriter openToWrite = FileUtils.openToWrite("snakeGameSave.txt");
		System.out.println("\nSaving game to file snakeGameSave.txt");
		openToWrite.println("Score " + score);
		openToWrite.println("Target " + target);
		openToWrite.println("Snake " + snake.size());
		for (int i = 0; i < snake.size(); ++i) {
			openToWrite.println(snake.get(i));
		}
		openToWrite.close();
		return true;
	}

	public void restoreGame() {
		 Scanner openToRead = FileUtils.openToRead("snakeGameSave.txt");
		openToRead.next();
		score = openToRead.nextInt();
		openToRead.next();
		target = new Coordinate(openToRead.nextInt(), openToRead.nextInt());
		snake.clear();
		openToRead.next();
		for (int nextInt = openToRead.nextInt(), i = 0; i < nextInt; ++i) {
			snake.add(new Coordinate(openToRead.nextInt(), openToRead.nextInt()));
		}
		openToRead.close();
	}
}