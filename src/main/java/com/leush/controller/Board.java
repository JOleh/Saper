package com.leush.controller;

import java.awt.Dimension;
import java.awt.Graphics;

public interface Board {
    /**
     * розмір таблиці з комірками розміру SMALL (одниці розрахунку - комірки)
     */
    Dimension SMALL = new Dimension(8,10);

    /**
     * розмір таблиці з комірками розміру MIDDLE (одниці розрахунку - комірки)
     */
    Dimension MIDDLE = new Dimension(16, 18);

    /**
     * розмір таблиці з комірками розміру LARGE (одниці розрахунку - комірки)
     */
    Dimension LARGE = new Dimension(24 , 32);

    /**
     * ліміт висоти таблиці з комірками (одниці розрахунку - комірки)
     */
    int HEIGHT_LIMIT = 32;

    /**
     * ліміт довжини таблиці з комірками (одниці розрахунку - комірки)
     */
    int WIDTH_LIMIT = 24;

    /**
     * рівень складності для початківця
     */
    int JUNIOR_LEVEL = 1;

    /**
     * рівень складності для середняка
     */
    int MIDDLE_LEVEL = 4;

    /**
     * рівень складності для професійного гравця
     */
    int SENIOR_LEVEL = 7;

    /**
     * максимальний рівень складності
     */
    int BIO_ROBOT_LEVEL = 9;

    /**
     * рівневий ліміт
     */
    int LEVEL_LIMIT = 9;


    /**
     * Встановлює рівень поточної гри
     * @param level рівень гри (від 1 до 9)
     */
    void setLevel(int level);


    /**
     * Генерування таблиці зі скорегованими комірками
     * @param cells пуста таблиця
     * @param level рівень поточної гри
     */
    void generateBoard(Cell[][] cells, Dimension size, int level);

    /**
     * Встановлює розмір таблиці
     * @param size розмір таблиці
     */
    void setSize(Dimension size);


    /**
     * Малює таблицю
     * @param graphics графічний контекст в який малювати
     */
    void drawBoard(Graphics graphics);

    /**
     * Перемальовує таблицю після певних дій
     * @param graphics - графічний контекс в який перемальовувати
     */
    void redrawBoard(Graphics graphics);

    /**
     * Перевіряє скільки бомб знаходиться поряд з поточною
     * щоб потім намалювати в комірці їх кількість якщо поточна комірка була вибрана вірно
     * @param x - координата вибраної точки по горизнталі
     * @param y - координата вибранох точки по вкртикалі
     * @param cells - матриця комірок серед яких шукати
     * @return повертає кількість бомб біля поточної комірки
     */
    int checkAround(int x, int y, Cell[][] cells, Dimension size);

    /**
     * Повертає комірку за її координатами для роботи з неї на вищих рівнях програми
     * @param x - координата комрки по горизонталі
     * @param y - координата комірки по вертикалі
     * @return повертає контекс комірки
     */
    Cell getCellByXY(int x, int y);

    /**
     * Перевіряє гру на наявність виграшу
     * @return повертає true якщо гравець виграв, повертає false - якщо гра ще продовжується
     */
    boolean checkIfWin();
}
