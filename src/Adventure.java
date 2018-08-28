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
    return "You are carrying:\n" + String.join("\n", inventory);
  }

  public String eval(String inputStr) {
    if (inputStr != null) {
      String[] arrOfInput = inputStr.trim().split("\\s+", 2);
      if (arrOfInput.length > 0 && arrOfInput[0].length() > 0) {
        if (arrOfInput[0].toLowerCase().charAt(0) == 'g') {
          if (arrOfInput.length > 1 && arrOfInput[1].length() > 0) {  //check if second argus is available
            switch (arrOfInput[1].toLowerCase().charAt(0)) {
              case 'e':
                if (currentCol < MAX_COL - 1) {
                  currentCol++;
                  return "Moving east...\n" + getLocation() + "\n";
                }
                else {
                  return "You can't go that far east.\n" + getLocation() + "\n";
                }
              case 'w':
                if (currentCol > 0) {
                  currentCol--;
                  return "Moving west...\n" + getLocation() + "\n";
                }
                else {
                  return "You can't go that far west.\n" + getLocation() + "\n";
                }
              case 's':
                if (currentRow < MAX_ROW - 1) {
                  currentRow++;
                  return "Moving south...\n" + getLocation() + "\n";
                }
                else {
                  return "You can't go that far south.\n" + getLocation() + "\n";
                }
              case 'n':
                if (currentRow > 0) {
                  currentRow--;
                  return "Moving north...\n" + getLocation() + "\n";
                }
                else {
                  return "You can't go that far north.\n" + getLocation() + "\n";
                }
              default:
                return "You can't go that way.\n" + getLocation() + "\n";
            }
          }
          else {
            return getLocation() + "\n";
          }
        }
        else if (arrOfInput[0].toLowerCase().charAt(0) == 'i') {
          return getInventory() + "\n" + getLocation() + "\n";
        }
        else if (arrOfInput[0].toLowerCase().charAt(0) == 'q') {
          setQuit();
          return "Farewell\n" + getLocation() + "\n";
        }
        else {
          return "Invalid command: " + arrOfInput[0] + "\n"+ getLocation() + "\n";
        }
      }
      else {
        return "Invalid input\n";
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
      System.out.printf(newAdventure.eval(input));
    }
  }
}
