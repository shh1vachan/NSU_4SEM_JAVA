package lab_3.model;

import java.util.Random;

public class MineField {
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
            if (field[y][x] == -1) continue; // Если здесь уже есть мина, пробуем снова
            field[y][x] = -1;  // Устанавливаем мину
            placedMines++;
            updateNumbersAround(x, y);
        }
    }

    private void updateNumbersAround(int x, int y) {
        // Обновляем соседние клетки вокруг мины
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height && field[ny][nx] != -1) {
                    field[ny][nx]++;  // Увеличиваем число соседних мин на 1
                }
            }
        }
    }

    public boolean openCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || revealed[y][x]) return false;
        revealed[y][x] = true; // Открываем клетку

        if (field[y][x] == -1) return false; // Если это мина — игра закончена

        if (field[y][x] == 0) openAdjacentCells(x, y); // Если клетка пустая, раскрываем соседей
        return true;
    }

    private void openAdjacentCells(int x, int y) {
        // Открываем соседние клетки, если они пустые
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height && !revealed[ny][nx]) {
                    openCell(nx, ny);
                }
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void toggleFlag(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height && !revealed[y][x]) {
            flagged[y][x] = !flagged[y][x];  // Переключаем флаг на клетке
        }
    }

    public boolean isMine(int x, int y) {
        return field[y][x] == -1;  // Проверка на мину
    }

    public boolean isRevealed(int x, int y) {
        return revealed[y][x];  // Проверка, открыта ли клетка
    }

    public boolean isFlagged(int x, int y) {
        return flagged[y][x];  // Проверка, установлен ли флаг
    }

    public int getCellValue(int x, int y) {
        return field[y][x];  // Возвращаем значение клетки (мина или число соседних мин)
    }

    public boolean gameOver(int x, int y) {
        if (isMine(x, y)) {
            // Игра закончена, если открыта мина
            return true;
        }
        return false;
    }

    public boolean isWin() {
        int flaggedMines = 0;
        int uncoveredCells = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (flagged[y][x] && field[y][x] == -1) {
                    flaggedMines++; // Подсчёт правильно установленных флажков
                }
                if (!revealed[y][x]) {
                    uncoveredCells++;  // Подсчёт закрытых клеток
                }
            }
        }
        // Победа, если количество флажков на минных клетках совпадает с количеством мин,
        // а все клетки без мин открыты
        return flaggedMines == mines && uncoveredCells == mines;
    }

    public int getFlaggedCount() {
        int count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (flagged[y][x]) count++;  // Подсчитываем количество флажков
            }
        }
        return count;
    }
}