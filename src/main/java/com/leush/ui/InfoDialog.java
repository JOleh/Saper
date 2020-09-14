package com.leush.ui;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;

/**
 * Діалогове вікно для надання інформації про базові правила гри
 * і про автора гри.
 * Наслідує JDialog.
 */

class InfoDialog extends JDialog {
    /**
     * Конструктор для ініціалізації вікна
     */
    InfoDialog() {
        /*Надання розміру вікну*/
        setSize(new Dimension(300 , 400));
        /*Встановлення заголовка*/
        setTitle("Info...");
        /*Встановлення неможливості зміни розміру діалогового вікна*/
        setResizable(false);
        /*Створення JTabbedPane для переходу між панелями правил і авторства*/
        JTabbedPane jTabbedPane = new JTabbedPane();
        /*Додавання панелі правил*/
        jTabbedPane.add("Rules", new RulesPanel());
        /*Додавання панелі авторства*/
        jTabbedPane.add("Author", new AuthorInfoPanel());
        /*Додавання панелі переходів в діалогове вікно*/
        add(jTabbedPane);
        /*Встановлення операції приховування діалогово вікна при натисканні на кнопку виходу*/
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}
/**
 * Клас панелі з інформацією про базові правила ігри.
 * Наслідує JPanel.
 */
class AuthorInfoPanel extends JPanel{
    /*Конструктор*/
    AuthorInfoPanel() {
        /*Додавання тексту повідомлення на панель через створення обєкту класу JLabel*/
        add(new JLabel("<html><h3>Author : Oleh Leush<h3><br>" +
                "Current institution : Lviv National<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;Polytechnic University<br>" +
                "Date &nbsp;: 2017 October<br>" +
                "e-mail : mrnovember@ukr.net</html>"));
    }
}
/**
 * Клас панелі з інформацією про авторство програми.
 * Наслідує JPanel.
 */
class RulesPanel extends JPanel{
    /*Конструктор*/
    RulesPanel() {
        /*Додавання тексту повідомлення на панель через створення обєкту класу JLabel*/
        add(new JLabel("<html>" +
                "<h2>Miner<h2><p align = &quot;justify&quot;>" +
                "You should find all bombs in field. How?<br>" +
                "Left mouse button helps you to detect<br>" +
                "cells where you think are not bombs.<br>" +
                "If you think that in some cells are bombs - <br>" +
                "press right mouse button. In moments<br>" +
                "when you think that you chose wrong cell<br>" +
                "to detect bomb - press right mouse button<br>" +
                "one more time to clear you choice.<br>" +
                "Numbers int green cells show you<br>" +
                "how many cells with bombs a near current cell.<br>" +
                "If you check all non bomb cells - you win,<br>" +
                "else if you find red cell - you lose.</p></html>"));
    }
}
