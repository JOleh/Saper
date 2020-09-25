package com.leush.ui;

import com.leush.controller.Board;
import com.leush.controller.Cell;
import com.leush.controller.OriginBoard;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int level;
    private Dimension size;
    private Board board;
    private boolean lose = false;
    private boolean win = false;

    void youLose() {
        this.lose = true;
    }

    void youWin(boolean win) {
        this.win = win;
    }

    OriginBoard getBoard() {
        return (OriginBoard) board;
    }

    BoardPanel(int level, Dimension size, MainFrame parent) {
        this.level = level;
        this.size = size;
        init();
        addMouseListener(parent);
    }

    private void init() {
        /*Надання дошці розміру*/
        setSize(new Dimension(Cell.SIDE_LENGTH * size.width, Cell.SIDE_LENGTH * size.height));
        /*Створення обєкту дошки*/
        board = new OriginBoard(size, level);
    }

    @Override
    protected void paintComponent(Graphics g) {
        board.drawBoard(g);
        if (lose) {
            setFontDrawConfig(g, "You lose");
        }
    }

    private void setFontDrawConfig(Graphics g, String msg) {
        g.setColor(Color.BLACK);
        int PATTERN_FONT_SIZE = 64;
        int PATTERN_PANEL_WIDTH = 320;
        g.setFont(new Font("SanSerif", Font.PLAIN, getWidth() * PATTERN_FONT_SIZE / PATTERN_PANEL_WIDTH));
        /*Створення обєкту класу FontMetrics для знаходження розмірів повідомлення*/
        FontMetrics fm = g.getFontMetrics();
        /*Малювання повідомлення в графічний контекст*/
        g.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2,
                (getHeight() - fm.getHeight()) / 2 + fm.getHeight());
    }
}
