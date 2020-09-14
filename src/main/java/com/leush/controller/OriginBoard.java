package com.leush.controller;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Клас дошки з комірками.
 * Імплементує Board.
 */
public class OriginBoard implements Board {
    /**
     * матриця комірок.
     */
    private Cell[][] cells;
    /**
     * розміри матриці.
     */
    private Dimension size;
    /**
     * рівень гри (насиченість матриці бомбами).
     */
    private int level;

    /**
     * Конструктор класу
     * @param size - розмір таблиці(дошки/матриці)
     * @param level - рівень гри
     */
    public OriginBoard(Dimension size, int level) {
        this.size = size;
        this.level = level;
        /*Створення обєкту матриці комірок*/
        cells = new Cell[this.size.height][this.size.width];
        /*Генерування комірок*/
        generateBoard(this.cells , this.size , this.level);
    }
    /**
     * Повертає матрицю комірок для обробки її на вищих рівнях програми.
     */
    public Cell[][] getCells() {
        return this.cells;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void generateBoard(Cell[][] cells, Dimension size, int level) {
        /*Генеруємо таблицю за допомогою статичного методу класу Generator і повертаємо її*/
        Generator.generate(cells, size, level);
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public void drawBoard(Graphics graphics) {
        /*Запускаємо два цикли по матриці і малюємо кожну комірку по заданих координатах*/
        for (int iPixel = 0 , iCell=0; iPixel <this.size.height*Cell.SIDE_LENGTH ; iPixel+=Cell.SIDE_LENGTH , iCell++) {
            for (int jPixel = 0 , jCell=0; jPixel <this.size.width*Cell.SIDE_LENGTH ; jPixel+=Cell.SIDE_LENGTH, jCell++) {
                cells[iCell][jCell].drawCell(graphics , jPixel , iPixel);
            }
        }
    }

    @Override
    public void redrawBoard(Graphics graphics) {
        //TODO можна спростити перемальовування таблиці використавши цей метод
    }

    @Override
    public int checkAround(int x, int y, Cell[][] cells , Dimension size) {
        /*Властивість - буде приймати кількість комірок з бомбами поблизу поточної*/
        int count = 0;
        /*Запуск циклу по матриці , враховуючи лише комірки які знаходяться біля поточної,
            і враховуючи розміщення поточної комірки
         */
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1 ; j++) {
                /*Якщо комірка яку потрібно перевірити проходить по необхідним параметрам
                    і лежить в зоні таблиці , то перевіряємо її на наявність бомби
                 */
                if(i>=0 && j>=0 && i<size.width && j<size.height && !(i==x && j==y)){
                    /*Якщо комірка поблизу містить бомбу то збільшуємо count на 1*/
                    if(cells[j][i].isBomb())
                        count++;
                }
            }
        }
        /*повертаємо кількість бомб*/
        return count;
    }

    @Override
    public Cell getCellByXY(int x, int y) {
        return this.cells[y][x];
    }

    @Override
    public boolean checkIfWin() {
        /*Властивість в яку буде записуватись кількість перевірених комірок*/
        int countChecked = 0;
        /*Властивість в яку буде записуватись кількість запідозрених комірок*/
        int countExpected = 0;
        /*прапорець стану перемоги*/
        boolean win = false;
        /*Цикл перевірки всієї таблиці на кількість перевірених комірок*/
        for (int i = 0; i <this.size.height ; i++) {
            for (int j = 0; j <this.size.width ; j++) {
                /*Якщо комірка перевірена і не містить бомби, то збільшуємо countChecked на 1*/
                if(cells[i][j].isChecked() && !cells[i][j].isBomb()){
                    countChecked++;
                }
                /*Якщо комірка запідозрена , то збільшеємо countExpected на 1*/
                if(cells[i][j].isExpected()){
                    countExpected++;
                }
            }
        }
        /*якщо сума перевірених і запідозрених == кількості всіх комірок то
         ставимо прапорець стану перемоги в true.
         */
        if ((countChecked+countExpected) == this.size.width*this.size.height){
            win = true;
        }
        /*повертаємо прапорець стану перемоги*/
        return win;
    }

    public void showAround(int x, int y){
        for (int i = x-1; i <=x+1 ; i++) {
            for (int j = y-1; j <=y+1 ; j++) {
                if(i>=0 && j>=0 && i<size.width && j<size.height && !(i==x && j==y)){
                    //variant 1
                /*    int ch = checkAround(i,j, cells , size);
                    if(ch!=0) cells[j][i].setMSG(String.valueOf(ch));
                    if(ch==0 && !cells[j][i].isChecked()){
                        cells[j][i].setChecked();
                        showAround(i,j);
                    }
                    */
                //variant 2
                    int ch = checkAround(i,j, cells , size);
                    if(ch!=0 && !cells[j][i].isChecked()){
                        cells[j][i].setMSG(String.valueOf(ch));
                        cells[j][i].setChecked();
                    }
                    else if(ch==0 && !cells[j][i].isChecked()){
                        cells[j][i].setChecked();
                        showAround(i,j);
                    }
                }
            }
        }
    }
}
