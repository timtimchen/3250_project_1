import java.util.Scanner;

/**
 * A class for the Adventure Game.
 */
public class Adventure {

  /** total rows of the adventure map. */
  public static final int MAX_ROW = 5;
  /** total columns of the adventure map. */
  public static final int MAX_COL = 5;

  /** current located in which row. */
  private int currentRow;
  /** current located in which columns. */
  private int currentCol;
  /** game finish sign, default is false. */
  private boolean isGameFinished;

  private GameChar gameChar;

  private Map gameMap;

  /**
   * default constructor.
   */
  Adventure(String mapFile) {
    try {
      gameMap = new Map(mapFile);
    } catch (Exception e) {
      System.out.println("Reading map file failed.");
    }
    gameChar = new GameChar();
  }

  /**
   * location setter.
   * @param row row coordinate of location
   * @param col column coordinate of location
   */
  public void setLocation(final int row, final int col) {
    currentRow = row;
    currentCol = col;
  }

  /**
   * location getter.
   * @return return a message string
   */
  public String getLocation() {
    return "You are at location " + currentRow + "," + currentCol;
  }

  /**
   * check if the game is finished.
   * @return if the game finished, return true
   */
  public boolean checkQuit() {
    return isGameFinished;
  }

  /**
   * set the game finished flag.
   */
  public void quitGame() {
    isGameFinished = true;
  }

  /**
   * evaluate each commands and take responding action.
   * @param inputStr a line of string as a command
   * @return a response string.
   */
  public String eval(final String inputStr) {
    if (inputStr != null) {
      //split the input command line into two arguments
      String[] split = inputStr.trim().split("\\s+", 2);
      //check if first argument is available
      if (split.length > 0 && split[0].length() > 0) {
        // detect 'go' command
        if (split[0].toLowerCase().charAt(0) == 'g') {
          //check if second argument is available
          if (split.length > 1 && split[1].length() > 0) {
            switch (split[1].toLowerCase().charAt(0)) {
              case 'e':  // 'go east'
                // check if this move inside the map boundary
                if (currentCol < MAX_COL - 1) {
                  currentCol++;
                  return "Moving east...\n" + getLocation() + "\n";
                } else {
                  return "You can't go that far east.\n"
                    + getLocation()
                    + "\n";
                }
              case 'w':  // 'go west'
                // check if this move inside the map boundary
                if (currentCol > 0) {
                  currentCol--;
                  return "Moving west...\n"
                    + getLocation() + "\n";
                } else {
                  return "You can't go that far west.\n"
                    + getLocation()
                    + "\n";
                }
              case 's':  // 'go south'
                // check if this move inside the map boundary
                if (currentRow < MAX_ROW - 1) {
                  currentRow++;
                  return "Moving south...\n" + getLocation() + "\n";
                } else {
                  return "You can't go that far south.\n"
                    + getLocation() + "\n";
                }
              case 'n':  // 'go north'
                // check if this move inside the map boundary
                if (currentRow > 0) {
                  currentRow--;
                  return "Moving north...\n"
                    + getLocation() + "\n";
                } else {
                  return "You can't go that far north.\n"
                    + getLocation() + "\n";
                }
              default:  // error direction message
                return "You can't go that way.\n"
                  + getLocation() + "\n";
            }
          } else {
          // second argument is not provided
            return getLocation() + "\n";
          }
        } else if (split[0].toLowerCase().charAt(0) == 'i') {
        // detect 'inventory' command
          return gameChar.getInventory() + "\n" + getLocation() + "\n";
        } else if (split[0].toLowerCase().charAt(0) == 'q') {
        // detect 'quit' command
          quitGame();
          return "Farewell\n" + getLocation() + "\n";
        } else {
        // handle invalid command
          return "Invalid command: "
            + split[0] + "\n"
            + getLocation() + "\n";
        }
      } else {
      // handle void input
        return "Invalid input\n";
      }
    }
    // if the input String is null, return null
    return null;
  }

  /**
   * main function.
   * @param args arguments from the command line
   */
  public static void main(final String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java Adventure mapFileName");
      return;
    }
    Scanner scanner = new Scanner(System.in);
    Adventure newAdventure = new Adventure(args[0]);
    while (!newAdventure.checkQuit()) {
      System.out.print("> ");
      String input = scanner.nextLine();
      System.out.printf(newAdventure.eval(input));
    }
  }
}
