package lab_3.text;

import lab_3.model.MineField;
import java.util.Scanner;

public class ConsoleView {
    private final MineField minefield;
    private final Scanner scanner;

    public ConsoleView(int width, int height, int mines) {
        this.minefield = new MineField(width, height, mines);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        while (true) {
            printField();
            System.out.print("Введите команду (open x y / flag x y / exit): ");
            String command = scanner.next();
            if (command.equals("exit")) break;
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (command.equals("open")) {
                if (!minefield.openCell(x, y)) {
                    System.out.println("Вы подорвались! Игра окончена.");
                    printField(true);
                    break;
                }
            } else if (command.equals("flag")) {
                minefield.toggleFlag(x, y);
            }

            if (minefield.isWin()) {
                System.out.println("Поздравляем, вы выиграли!");
                printField(true);
                break;
            }
        }
    }

    private void printField() {
        printField(false);
    }

    private void printField(boolean showMines) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (minefield.isRevealed(x, y)) {
                    System.out.print(minefield.getCellValue(x, y) + " ");
                } else if (minefield.isFlagged(x, y)) {
                    System.out.print("F ");
                } else if (showMines && minefield.isMine(x, y)) {
                    System.out.print("* ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new ConsoleView(9, 9, 10).startGame();
    }
}
