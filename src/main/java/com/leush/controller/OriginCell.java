package com.leush.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;

/**
 * Клас який реалізовує логіку комірки.
 * Імплементує інтерфейс Cell.
 */
public class OriginCell implements Cell {
    /**
     * Позиція комірки по горизонталі (в комірках)
     */
    private int x;
    /**
     * Позиція комірки по вертикалі (в комірках)
     */
    private int y;
    /**
     * прапорець стану комірки. true - якщо є бомба, false - якщо немає
     */
    private boolean bomb = false;
    /**
     * прапорець стану комірки. true - якщо гравець перевірив комірку, false - якщо ні
     */
    private boolean check = false;
    /**
     * прапорець стану комірки. true - якщо гравець запідозрив комірку в наявності бомби, false - якщо ні
     */
    private boolean expect = false;
    /**
     * повідомлення , яке виводиться в комірці.
     * '?' - якщо комірка запідозрена,
     * 'number' або ' ' - кількість бомб біля поточної комірки, якщо гравець перевірив комірку і вона виявилася безпечною
     */
    private String MSG = "";

    /*Метод встановлення повідомлення */
    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    /*Конструктор класу*/
    OriginCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public boolean isExpected() {
        return expect;
    }

    @Override
    public boolean isChecked() {
        return check;
    }

    @Override
    public void setOptions(int x, int y, boolean bomb) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;
    }

    @Override
    public void setDanger() {
        bomb = true;
    }

    @Override
    public void drawCell(Graphics graphics, int x, int y) {
        /*встановлюємо товщину ліній в 2 пікселя*/
        ((Graphics2D)graphics).setStroke(new BasicStroke(2));
        /*встановлюємо шрифт*/
        graphics.setFont(new Font("Arial", Font.PLAIN, 18));
        /*Встановлюємо спосіб малювання якщо комірка перевірена*/
        if(isChecked()){
            /*Встановлюємо спосіб малювання , якщо комірка є одночасно і запідозреною*/
            if(isExpected()){
                /*Встановлює колір - оранжевий*/
                graphics.setColor(Color.ORANGE);
                /*Малюємо заповнений прямокутник*/
                graphics.fillRect(x, y, SIDE_LENGTH , SIDE_LENGTH);
                /*Встановлюємо колір для шрифту*/
                graphics.setColor(Color.BLACK);
                /*Малюємо повідомлення в комірку*/
                graphics.drawString(MSG , x+4, y+SIDE_LENGTH-2);
            }
            /*Встановлюємо спосіб малювання , якщо комірка є безпечною*/
            else if(!isBomb()){
                /*Встановлюємо колір - зелений*/
                graphics.setColor(Color.GREEN);
                /*Малюємо заповнений прямокутник*/
                graphics.fillRect(x, y, SIDE_LENGTH , SIDE_LENGTH);
                /*Встановлюємо колір для шрифту*/
                graphics.setColor(Color.BLACK);
                /*Малюємо повідомлення в комірку*/
                graphics.drawString(MSG , x+3, y+SIDE_LENGTH-2);
            }
            /*Встановлюємо спосіб малювання комірки , якщо користувач вибрав її і вона є небезпечною*/
            else if(isBomb()){
                /*Встановлюємо колір - червоний*/
                graphics.setColor(Color.RED);
                /*Малюємо заповнений прямокутник*/
                graphics.fillRect(x, y, SIDE_LENGTH , SIDE_LENGTH);
            }
        }
        /*Встановлюємо спосіб малювання якщо комірка ще не перевірена*/
        else{
            /*Встановлюємо колір - сірий*/
            graphics.setColor(Color.GRAY);
            /*Малюємо заповнений прямокутник*/
            graphics.fillRect(x, y, SIDE_LENGTH , SIDE_LENGTH);
        }
        /*Встановлюємо колір для рамок в комірках - синій*/
        graphics.setColor(Color.BLUE);
        /*Малюємо рамку*/
        graphics.drawRect(x, y, SIDE_LENGTH , SIDE_LENGTH);
    }

    @Override
    public void redrawCell(Graphics graphics, int x, int y) {
        //TODO можна спростити перемальовування комірки використавши цей метод
    }

    @Override
    public Point getPlacing() {
        return new Point(x,y);
    }

    @Override
    public void setChecked() {
        check = true;
    }

    @Override
    public void setExpected() {
        expect = true;
    }

    @Override
    public void removeCheck() {
        check = false;
    }

    @Override
    public void removeExpect() {
        expect = false;
    }
}
