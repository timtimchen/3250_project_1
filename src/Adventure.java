import java.util.Scanner;
import java.util.ArrayList;

public class Adventure {

  public static final int MAX_ROW = 5;
  public static final int MAX_COL = 5;

  private int currentRow;
  private int currentCol;
  private boolean quitGame;
  ArrayList<String> inventory = new ArrayList<String>();

  Adventure() {
    inventory.add("brass lantern");
    inventory.add("rope");
    inventory.add("rations");
    inventory.add("staff");
  }

  public void setLocation(int row, int col) {
    currentRow = row;
    currentCol = col;
  }

  public String getLocation() {
    return "You are at location " + currentRow + "," + currentCol;
  }

  public boolean checkQuit() {
    return quitGame;
  }

  public void setQuit() {
    quitGame = true;
  }

  public String getInventory() {
    return String.join("\n", inventory);
  }

  public String eval(String inputStr) {
    if (inputStr != null) {
      String[] arrOfInput = inputStr.trim().split("\\s+", 2);
      if (arrOfInput.length > 0 && arrOfInput[0].length() > 0) {
        if (arrOfInput[0].toLowerCase().charAt(0) == 'g') {
          return "g";
        }
        else if (arrOfInput[0].toLowerCase().charAt(0) == 'i') {
          return getInventory() + "\n" + getLocation();
        }
        else if (arrOfInput[0].toLowerCase().charAt(0) == 'q') {
          setQuit();
          return "Farewell\n" + getLocation();
        }
        else {
          return "Invalid command: " + arrOfInput[0];
        }
      }
      else {
        return "Invalid input";
      }
    }
    // if the input String is null, return null
    return null;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System. in);
    Adventure newAdventure = new Adventure();
    while (! newAdventure.checkQuit()) {
      System.out.print("> ");
      String input = scanner.nextLine();
      System.out.println(newAdventure.eval(input));
    }
  }
}
