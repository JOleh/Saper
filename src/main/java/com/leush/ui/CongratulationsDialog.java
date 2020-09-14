package com.leush.ui;

import com.leush.ImageFactory;
import com.leush.Images;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Клас діалогового вікна в якому міститься конент привітанняз перемогою.
 * Наслідує JDialog.
 */
class CongratulationsDialog extends JDialog {
    /*Конструктор класу*/
    CongratulationsDialog() {
        /*Стандартний відступ*/
        int ALIGN = 15;
        /* Ширина картинки*/
        int IMAGE_WIDTH = 200;
        /*Висота картинки*/
        int IMAGE_HEIGHT = 270;
        /*Висота кнопки*/
        int BUTTON_HEIGHT = 40;
        /*Ширина кнопки*/
        int BUTTON_WIDTH = 80;
        /*Висота діалогового вікна*/
        int DIALOG_HEIGHT = 340;
        /*Ширина діалогового вікна*/
        int DIALOG_WIDTH = 230;
        /*Встановлення розміру вікна*/
        setSize(new Dimension(DIALOG_WIDTH , DIALOG_HEIGHT));
        /*Встановлення способу розміщення компонентів в вікні*/
        setLayout(null);
        /*Встановлення неможливості змінии розміру вікна*/
        setResizable(false);
        /*Встановлення заголовка вікна*/
        setTitle("Congratulation");
        /*Встановлення фонового кольору вікан*/
        setBackground(Color.GRAY);
        /*Встановлення приховування вікна при натисненні на кно кнопку EXIT*/
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        /*Створення мітки з зображенням*/
        JLabel imageLabel = new JLabel(new ImageFactory().getImageIcon(Images.CONGRATS));
        /*Встановлення розміщення мітки в вікні*/
        imageLabel.setBounds(ALIGN,0 , IMAGE_WIDTH , IMAGE_HEIGHT);
        /*Додавання мітки вікно*/
        add(imageLabel);
        /*Створення мітки-кнопки з зображенням*/
        JLabel buttonLabel = new JLabel(new ImageFactory().getImageIcon(Images.OK));
        /*Встановлення розміщення мітки-кнопки в вікні*/
        buttonLabel.setBounds(ALIGN+(IMAGE_WIDTH-BUTTON_WIDTH)/2,   IMAGE_HEIGHT-ALIGN/2 , BUTTON_WIDTH , BUTTON_HEIGHT);
        /*Додавання кнопки-мітки в вікно*/
        add(buttonLabel);
        /*Встановлення слухавки мітці-кнопці. Буде прослуховуватись локально*/
        buttonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*Встановення невидимості при натисканні на кнопку*/
                setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                /*Встановення невидимості при натисканні на кнопку*/
                setVisible(false);
            }
        });
    }
}
