import java.util.Scanner;

class GameOfLife {
  static final int GRIDX = 20;
  static final int GRIDY = 20;

  static final char ALIVE = '◼';
  static final char DEAD = '◻';

  // default 4-8-12 diamond seed
  static boolean[][] defaultSeed() {
    boolean[][] life = new boolean[GRIDY][GRIDX];

    for (int i = 4; i < 16; i++) {
      life[9][i] = true;
    }

    for (int i = 6; i < 14; i++) {
      life[7][i] = true;
      life[11][i] = true;
    }

    for (int i = 8; i < 12; i++) {
      life[5][i] = true;
      life[13][i] = true;
    }

    return life;
  }

  static boolean[][] initLife() {
    Scanner input = new Scanner(System.in);
    String userChoice;

    boolean[][] life = new boolean[GRIDY][GRIDX];

    System.out.println("Type 'default' for default seed");

    do {
      System.out.println("Type coordinate to toggle cell state (eg. b4). Press enter to start\n");
      draw(life);

      userChoice = input.nextLine();

      if (userChoice.equals("default")) {
        life = defaultSeed();
        break;
      }

      try {
        life[Integer.parseInt(userChoice.substring(1))][userChoice.charAt(0) - 'a'] = !life[Integer.parseInt(userChoice.substring(1))][userChoice.charAt(0) - 'a'];
      }
      catch (StringIndexOutOfBoundsException e) {}

      catch (NumberFormatException e) {}
    }
    while (!userChoice.equals(""));

    return life;
  }

  static void draw(boolean[][] life) {
    System.out.print("   ");
    for (int i = 0; i < life[0].length; i++) {
      System.out.print(((char) (i + 'a')) + " ");
    }

    System.out.println();

    for (int i = 0; i < life.length; i++) {
      System.out.print(i);

      if (i < 10) {
        System.out.print("  ");
      }

      else {
        System.out.print(" ");
      }

      for (int j = 0; j < life[i].length; j++) {
        if (life[i][j]) {
          System.out.print(ALIVE + " ");
        }
        else {
          System.out.print(DEAD + " ");
        }
      }

      System.out.println();
    }
    System.out.println();
  }

  static int getNeighbors(boolean[][] life, int x, int y) {
    int neighbors = 0;

    // previous row neighbors
    if (y - 1 >= 0) {
      if (x - 1 >= 0 && life[y - 1][x - 1]) {
        neighbors++;
      }

      if (x + 1 < GRIDX && life[y - 1][x + 1]) {
        neighbors++;
      }

      if (life[y - 1][x]) {
        neighbors++;
      }
    }

    // next row neighbors
    if (y + 1 < GRIDY) {
      if (x - 1 >= 0 && life[y + 1][x - 1]) {
        neighbors++;
      }

      if (x + 1 < GRIDX && life[y + 1][x + 1]) {
        neighbors++;
      }

      if (life[y + 1][x]) {
        neighbors++;
      }
    }

    // current row neighbors
    if (x - 1 >= 0 && life[y][x - 1]) {
      neighbors++;
    }

    if (x + 1 < GRIDX && life[y][x + 1]) {
      neighbors++;
    }

    return neighbors;
  }

  static boolean[][] update(boolean[][] life) {
    boolean[][] newLife = new boolean[GRIDY][GRIDX];

    for (int i = 0; i < life.length; i++) {
      for (int j = 0; j < life[i].length; j++) {
        int neighbors = getNeighbors(life, j, i);

        if (life[i][j]) {

          if (neighbors < 2 || neighbors > 3) {
            newLife[i][j] = false;
          }

          else {
            newLife[i][j] = true;
          }
        }

        else {
          if (neighbors == 3) {
            newLife[i][j] = true;
          }
        }
      }
    }

    return newLife;
  }

  public static void main(String[] args) throws InterruptedException {
    boolean[][] life;
    life = initLife();

    while (true) {
      draw(life);
      life = update(life);
      Thread.sleep(175);
    }
  }
}
