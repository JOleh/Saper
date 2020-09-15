package com.leush.ui;

import com.leush.ImageFactory;
import com.leush.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Кдас додаткової панелі з кнопкою рестарту гри
 * Наслідує JPanel.
 */
class TopBoardPanel extends JPanel {
    private JLabel imageLabel;
    static int OPTIONAL_PANEL_HEIGHT = 35;


    TopBoardPanel(MainFrame parent) {
        init(parent);
    }

    private void init(MainFrame parent) {
        /*Надання панелі способу розміщенння в ній компонентів*/
        setLayout(new FlowLayout());
        /*Надання розміру панелі. -1 - довжина визначається за довжину батьківського контейнера*/
        setSize(new Dimension(-1, OPTIONAL_PANEL_HEIGHT));

        /*Створення обєкту мітки з іконкою, яка міститься в ресурсах програми*/
        imageLabel = new JLabel(new ImageFactory().getImageIcon(Images.PLAY_SMILE));
        /*Надання мітці слухавки. Буде прослуховуватись батьківським до панелі класом*/
        imageLabel.addMouseListener(parent);
        /*Додавання мітки на панель з розміщенням по центрі*/
        add(imageLabel, SwingConstants.CENTER);
    }

    JLabel getStartButton() {
        return imageLabel;
    }
}
