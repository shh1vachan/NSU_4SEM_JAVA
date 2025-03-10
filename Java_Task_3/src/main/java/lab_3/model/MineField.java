package lab_3.model;

import java.util.Random;

public class MineField
{
    private final int width;
    private final int height;
    private final int mines;
    private final int[][] field; // -1 - мина, 0-8 - число мин вокруг
    private final boolean[][] revealed;
    private final boolean[][] flagged;

    public MineField(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.field = new int[height][width];
        this.revealed = new boolean[height][width];
        this.flagged = new boolean[height][width];
        generateMines();
    }

    private void generateMines() {
        Random rand = new Random();
        int placedMines = 0;
        while (placedMines < mines) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (field[y][x] == -1) continue;
            field[y][x] = -1;
            placedMines++;
            updateNumbersAround(x, y);
        }
    }

    private void updateNumbersAround(int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height && field[ny][nx] != -1) {
                    field[ny][nx]++;
                }
            }
        }
    }

    public boolean openCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || revealed[y][x]) return false;
        revealed[y][x] = true;
        if (field[y][x] == -1) return false; // Попали на мину
        if (field[y][x] == 0) openAdjacentCells(x, y);
        return true;
    }

    private void openAdjacentCells(int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height && !revealed[ny][nx]) {
                    openCell(nx, ny);
                }
            }
        }
    }

    public void toggleFlag(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height && !revealed[y][x]) {
            flagged[y][x] = !flagged[y][x];
        }
    }

    public boolean isMine(int x, int y) {
        return field[y][x] == -1;
    }

    public boolean isRevealed(int x, int y) {
        return revealed[y][x];
    }

    public boolean isFlagged(int x, int y) {
        return flagged[y][x];
    }

    public int getCellValue(int x, int y) {
        return field[y][x];
    }

    public boolean isWin() {
        int coveredCells = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!revealed[y][x]) coveredCells++;
            }
        }
        return coveredCells == mines;
    }
}