import java.util.Scanner;

/**
 * A class for the Adventure Game.
 */
public class Adventure {

  /** game finish sign, default is false. */
  private boolean isGameFinished;

  /** game Character object. */
  private GameChar gameChar;

  /** game Map object. */
  private Map gameMap;

  /**
   * default constructor.
   * @param mapFile a path of map text file
   */
  Adventure(final String mapFile) {
    try {
      gameMap = new Map(mapFile);
    } catch (Exception e) {
      System.out.println("Reading map file failed.");
    }
    gameChar = new GameChar();
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
            return gameChar.go(split[1].toLowerCase().charAt(0), gameMap);
          } else {
          // second argument is not provided
            return gameChar.locationInfo(gameMap) + "\n";
          }
        } else if (split[0].toLowerCase().charAt(0) == 'i') {
        // detect 'inventory' command
          return gameChar.getInventory() + "\n"
            + gameChar.locationInfo(gameMap) + "\n";
        } else if (split[0].toLowerCase().charAt(0) == 'q') {
        // detect 'quit' command
          quitGame();
          return "Farewell\n" + gameChar.locationInfo(gameMap) + "\n";
        } else {
        // handle invalid command
          return "Invalid command: "
            + split[0] + "\n"
            + gameChar.locationInfo(gameMap);
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
