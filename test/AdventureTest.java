//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
///**
// *  This Junit 5 Jupiter test file tests CS 3250 Project 1 code.
// *  It is used to grade functionality of your code.
// *  Expectation is that all unit tests will pass.
// */
//public class AdventureTest {
//  /**
//   *
//   */
//  static int count = 0;
//  static final int total = 44;
//  @BeforeAll
//  static void beforeAll() {
//    //do nothing
//    count = 0;
//  }
//
//  /**
//   * Helper method for testing directions.
//   * @param row
//   * @param column
//   * @param direction
//   * @param limit
//   * @param cango
//   * @param cantgo
//   * @param steps
//   */
//  private void testDirection(int row, int column, String direction, int limit,
//                             String cango, String cantgo, List<Integer> steps) {
//    Adventure game = new Adventure();
//    game.setLocation(row, column);
//    String expected = null;
//    String actual = null;
//
//    String input = String.format("go %s\n", direction);
//    for (int step: steps) {
//      actual = game.eval(input);
//      expected = String.format(
//        cango, direction, step
//      );
//      assertEquals(expected, actual);
//      count++;
//    }
//
//    actual = game.eval(input);
//    expected = String.format(
//      cantgo,
//      direction, steps.get(steps.size()-1));
//    assertEquals(expected, actual);
//  }
//  /**
//   *
//   */
//  @Test
//  public void testGoSouth() {
//    List<Integer> steps = IntStream.range(1,Adventure.MAX_ROW).boxed().collect(Collectors.toList());
//    testDirection(0,0,"south", Adventure.MAX_ROW,
//      "Moving %s...\nYou are at location %s,0\n",
//      "You can't go that far %s.\nYou are at location %s,0\n",
//      steps
//    );
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testGoWest() {
//    int start = 0;
//    int end = 4;
//    List<Integer> steps = IntStream.range(start,end).map(i -> end - i + start - 1).boxed().collect(Collectors.toList());
//    testDirection(1,4,"west", Adventure.MAX_ROW,
//      "Moving %s...\nYou are at location 1,%s\n",
//      "You can't go that far %s.\nYou are at location 1,%s\n",
//      steps
//    );
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testGoNorth() {
//    List<Integer> steps = IntStream.range(1,3).map(i -> 3 - i + 0 - 1).boxed().collect(Collectors.toList());
//    testDirection(2,4,"north", Adventure.MAX_ROW,
//      "Moving %s...\nYou are at location %s,4\n",
//      "You can't go that far %s.\nYou are at location %s,4\n",
//      steps
//    );
//  }
//
//
//  /**
//   *
//   */
//  @Test
//  public void testGoEast() {
//    List<Integer> steps = IntStream.range(1,Adventure.MAX_COL).boxed().collect(Collectors.toList());
//    testDirection(0,0,"east",
//      Adventure.MAX_COL,
//      "Moving %s...\nYou are at location 0,%s\n",
//      "You can't go that far %s.\nYou are at location 0,%s\n",steps
//    );
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testBadDirection() {
//    Adventure game = new Adventure();
//    String expected = null;
//    String actual = null;
//
//    String input = "go east\n";
//    actual = game.eval(input);
//    input = "go south\n";
//    actual = game.eval(input);
//    input = "go fast\n";
//    actual = game.eval(input);
//    expected = "You can't go that way.\nYou are at location 1,1\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testShorthand() {
//    Adventure game = new Adventure();
//
//    String input = "g e\n";
//    String actual = game.eval(input);
//    String expected = "Moving east...\nYou are at location 0,1\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go s\n";
//    actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 1,1\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go n\n";
//    actual = game.eval(input);
//    expected = "Moving north...\nYou are at location 0,1\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go w\n";
//    actual = game.eval(input);
//    expected = "Moving west...\nYou are at location 0,0\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testInventory() {
//    Adventure game = new Adventure();
//    String expected = null;
//
//    String input = "inventory\n";
//    String actual = game.eval(input);
//    expected = "You are carrying:\n"
//      + "brass lantern\n"
//      + "rope\n"
//      + "rations\n"
//      + "staff\n"
//      + "You are at location 0,0\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testInvalidCommand() {
//    Adventure game = new Adventure();
//    String expected = null;
//    String input = "foo\n";
//    String actual = game.eval(input);
//    expected = "Invalid command: foo\nYou are at location 0,0\n";
//    for (int i=0; i < expected.length(); i++)
//    {
//      if (expected.charAt(i) != actual.charAt(i)) {
//        break;
//      }
//    }
//    assertEquals(expected, actual);
//    count++;
//  }
//
//  /**
//   *
//   */
//  @Test
//  public void testCapitals() {
//    Adventure game = new Adventure();
//    String expected = null;
//
//    String input = "GO EAST\n";
//    String actual = game.eval(input);
//    expected = "Moving east...\nYou are at location 0,1\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//  /**
//   *
//   */
//  @Test
//  public void testGoAnyOkWords() {
//    Adventure game = new Adventure();
//    game.setLocation(2,2);
//    String expected = null;
//
//    String input = "grunted sarcasm!\n";
//    String actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 3,2\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//  /**
//   *
//   */
//  @Test
//  public void testScenario() {
//    Adventure game = new Adventure();
//    String expected = null;
//
//    String input = "go south\n";
//    String actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 1,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g south\n";
//    actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 2,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go inside\n";
//    actual = game.eval(input);
//    expected = "You can't go that way.\nYou are at location 2,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go west\n";
//    actual = game.eval(input);
//    expected = "You can't go that far west.\nYou are at location 2,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g s\n";
//    actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 3,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g s\n";
//    actual = game.eval(input);
//    expected = "Moving south...\nYou are at location 4,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g s\n";
//    actual = game.eval(input);
//    expected = "You can't go that far south.\nYou are at location 4,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "inventory\n";
//    actual = game.eval(input);
//    expected = "You are carrying:\n"
//      + "brass lantern\n"
//      + "rope\n"
//      + "rations\n"
//      + "staff\n"
//      + "You are at location 4,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "foo\n";
//    actual = game.eval(input);
//    expected = "Invalid command: foo\nYou are at location 4,0\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "GO EAST\n";
//    actual = game.eval(input);
//    expected = "Moving east...\nYou are at location 4,1\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g e\n";
//    actual = game.eval(input);
//    expected = "Moving east...\nYou are at location 4,2\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g e\n";
//    actual = game.eval(input);
//    expected = "Moving east...\nYou are at location 4,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g e\n";
//    actual = game.eval(input);
//    expected = "Moving east...\nYou are at location 4,4\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g e\n";
//    actual = game.eval(input);
//    expected = "You can't go that far east.\nYou are at location 4,4\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go north\n";
//    actual = game.eval(input);
//    expected = "Moving north...\nYou are at location 3,4\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go west\n";
//    actual = game.eval(input);
//    expected = "Moving west...\nYou are at location 3,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go N\n";
//    actual = game.eval(input);
//    expected = "Moving north...\nYou are at location 2,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "go N\n";
//    actual = game.eval(input);
//    expected = "Moving north...\nYou are at location 1,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "goofy nazgul!\n";
//    actual = game.eval(input);
//    expected = "Moving north...\nYou are at location 0,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "g n\n";
//    actual = game.eval(input);
//    expected = "You can't go that far north.\nYou are at location 0,3\n";
//    assertEquals(expected, actual);
//    count++;
//
//    input = "Q\n";
//    actual = game.eval(input);
//    expected = "Farewell\nYou are at location 0,3\n";
//    assertEquals(expected, actual);
//    count++;
//  }
//
//
//  /**
//   *
//   */
//  @AfterAll
//  static void afterAll() {
//    System.out.printf("%d out of %d tests passed. %.2f%%", count, total, count*100.0/total);
//  }
//}
