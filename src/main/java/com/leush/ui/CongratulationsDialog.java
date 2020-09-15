package com.leush.ui;

import com.leush.ImageFactory;
import com.leush.Images;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CongratulationsDialog extends JDialog {

    @Value("${minesweeper.dialog.congrats.title}")
    private String title;

    private final int ALIGN = 15;
    private final int IMAGE_WIDTH = 200;
    private final int IMAGE_HEIGHT = 270;
    private final int BUTTON_HEIGHT = 40;
    private final int BUTTON_WIDTH = 80;
    private final int DIALOG_HEIGHT = 340;
    private final int DIALOG_WIDTH = 230;

    CongratulationsDialog() {
        setupWindowView();
        createCongratsImage();
        createOkButton();
    }

    private void setupWindowView() {
        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setLayout(null);
        setResizable(false);
        setTitle(title);
        setBackground(Color.GRAY);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void createCongratsImage() {
        JLabel imageLabel = new JLabel(new ImageFactory().getImageIcon(Images.CONGRATS));
        imageLabel.setBounds(ALIGN, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        add(imageLabel);
    }

    private void createOkButton() {
        JLabel buttonLabel = new JLabel(new ImageFactory().getImageIcon(Images.OK));
        buttonLabel.setBounds(ALIGN + (IMAGE_WIDTH - BUTTON_WIDTH) / 2, IMAGE_HEIGHT - ALIGN / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(buttonLabel);
        buttonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setVisible(false);
            }
        });
    }
}
