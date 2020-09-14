package com.leush.ui;

import com.leush.controller.Board;
import com.leush.controller.Cell;
import com.leush.controller.OriginBoard;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Панель на якій розміщується ігрове поле. Наслідує JPanel.
 */
public class BoardPanel extends JPanel{
    /**
     * Властивість рівня.
     */
    private int level;
    /**
     * Властивість розміру поля.
     */
    private Dimension size;
    /**
     * Дошка з комірками - саме поле гри.
     */
    private Board board;
    /**
     * Прапорець стану поразки.
     */
    private boolean lose = false;
    /**
     *Прапорець стану перемоги.
     */
    private boolean win = false;

    /**
     * Встановлює прапорець поразки в true, якщо гравець зазнав поразки
     * @param lose - прапорець поразки, true - поразка, false - здебільшого не використовується
     */
    void youLose(boolean lose) {
        this.lose = lose;
    }

    /**
     * Встановлює прапорець перемоги в true , якщо гравець переміг
     * @param win - прапорець перемоги, true - перемога, false - не використовується
     */
    void youWin(boolean win){
        this.win = win;
    }

    /**
     * Повертає дошку поля гри для роботи з нею на вищих рівнях
     * @return повертає дошку поля гри
     */
    OriginBoard getBoard() {
        return (OriginBoard)board;
    }

    /**
     * Конструктор
     * @param level - рівень гри
     * @param size - розмір поля гри
     * @param parent - батьківське вікно , в якому розміщується поле
     */
    BoardPanel(int level, Dimension size, MainFrame parent) {
        this.level = level;
        this.size = size;
        /* Ініціалізація властивостей дошки*/
        init();
        /* Надання батьківському класу спроможність прослуховувати поле гри*/
        addMouseListener(parent);
    }

    /**
     * Ініціалізація властивостей дошки
     */
    private void init(){
         /*Надання дошці розміру*/
        setSize(new Dimension(Cell.SIDE_LENGTH*size.width , Cell.SIDE_LENGTH*size.height));
        /*Створення обєкту дошки*/
        board = new OriginBoard(size , level);
    }

    /**
     * Малювання дошки на панелі
     * @param g - графічний контекст панелі
     */
    @Override
    protected void paintComponent(Graphics g) {
        /*Малювання чистої дошки*/
        board.drawBoard(g);
        /*Дії , якщо гравець програв*/
        if(lose){
            /*Малювання повідомлення про поразку*/
            setFontDrawConfig(g , "You lose");
        }
        /*Дії , якщо гравець переміг*/
        /*else if(win){
            setFontDrawConfig(g, "You win");
        }*/
    }

    /**
     * Встановлення властивостей шрифту і розміщення повідомлення про стан гри
     * @param g - графічний контекс , в який малювати
     * @param msg - повідомлення, яке необхідно малювати
     */
    private void setFontDrawConfig(Graphics g, String msg){
        /*Встановлення колььору - BLACK*/
        g.setColor(Color.BLACK);
        /*Встановлення шрифту повідомлення*/
        /*
      Шаблонний розмір шрифту для друкування повідомлення про перемогу/поразку.
      Використовується для формування повідомлення гравцю.
     */
        int PATTERN_FONT_SIZE = 64;
        /*
      Шаблонна ширина панелі.
      Використовується для формування повідомлення гравцю.
     */
        int PATTERN_PANEL_WIDTH = 320;
        g.setFont(new Font("SanSerif" , Font.PLAIN, getWidth()* PATTERN_FONT_SIZE / PATTERN_PANEL_WIDTH));
        /*Створення обєкту класу FontMetrics для знаходження розмірів повідомлення*/
        FontMetrics fm = g.getFontMetrics();
        /*Малювання повідомлення в графічний контекст*/
        g.drawString(msg, (getWidth()-fm.stringWidth(msg))/2,
                (getHeight()-fm.getHeight())/2+fm.getHeight());
    }
}
