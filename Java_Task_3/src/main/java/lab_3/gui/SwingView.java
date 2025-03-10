package lab_3.gui;

import lab_3.model.MineField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingView extends JFrame {

    private final MineField minefield;
    private final JButton[][] buttons;
    private final int width = 9;
    private final int height = 9;
    private final JToggleButton flagButton;

    public SwingView() {
        this.minefield = new MineField(width, height, 10); // Создаем поле 9x9 с 10 минами
        this.buttons = new JButton[height][width];
        this.flagButton = new JToggleButton("Флаг");
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Сапёр");
        setSize(400, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Добавление кнопки для флажков
        flagButton.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = height;
        add(flagButton, gbc);

        // Заполнение игрового поля кнопками
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));

                gbc.gridx = j;
                gbc.gridy = i;
                add(buttons[i][j], gbc);
            }
        }

        setVisible(true);
    }

    // Логика при нажатии на кнопку
    private class ButtonClickListener implements ActionListener {
        private final int x;
        private final int y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flagButton.isSelected()) {
                minefield.toggleFlag(x, y);
                buttons[x][y].setText("F");
                flagButton.setSelected(false);
            } else {
                if (minefield.openCell(x, y)) {
                    updateButton(x, y);
                    if (minefield.isWin()) {
                        JOptionPane.showMessageDialog(SwingView.this, "Поздравляем, вы выиграли!");
                    }
                } else {
                    buttons[x][y].setText("*");
                    JOptionPane.showMessageDialog(SwingView.this, "Вы подорвались! Игра окончена.");
                }
            }
        }
    }

    // Обновление текста на кнопке при открытии клетки
    private void updateButton(int x, int y) {
        if (minefield.isRevealed(x, y)) {
            int value = minefield.getCellValue(x, y);
            buttons[x][y].setText(value == 0 ? "" : String.valueOf(value));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingView());
    }
}