package com.leush.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

class OptionDialog extends JDialog {

    private JComboBox<String> sizeComboBox;
    private JComboBox<String> levelComboBox;
    private JFormattedTextField width;
    private JFormattedTextField height;
    private JRadioButton predefinedSizesButton;

    private final int DIALOG_WIDTH = 300;
    private final int DIALOG_HEIGHT = 200;
    private final int RIGHT_ALIGN = 20;
    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_HEIGHT = 25;
    private final int TOP = 39;
    private final int ALIGN = 10;
    private final int JRADIO_BUTTON_SIMPLE_SIDE = 25;
    private final int LEFT_ALIGN = 20;

    private final MainFrame owner;

    OptionDialog(MainFrame owner) {
        super(owner);
        this.owner = owner;

        setTitle("Options...");
        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setLayout(null);
        setResizable(false);

        createOkButton();
        createCancelButton();

        predefinedSizesButton = createRadioButtonForPredefinedSizes();
        JRadioButton customSizeButton = createRadioButtonForCustomSize();

        createGroupOfSizeButtons(predefinedSizesButton, customSizeButton);

        createLabelForSizeBlock();

        createComboBoxOfPredefinedSizes();

        createWidthAndHeightLabelsFroCustomSize();

        createWidthFieldForCustomSize();
        createHeightFieldForCustomSize();

        createWidthLimitLabelForCustomSize();
        createHeightLimitLabelForCustomSize();

        createLevelLabel();
        createComboBoxForLevels();
    }

    private void createOkButton() {
        JButton jButtonOK = new JButton("OK");
        jButtonOK.setBounds(DIALOG_WIDTH - (BUTTON_WIDTH + RIGHT_ALIGN) * 2,
                DIALOG_HEIGHT - BUTTON_HEIGHT - TOP, BUTTON_WIDTH, BUTTON_HEIGHT);
        jButtonOK.addActionListener(owner);
        add(jButtonOK);
    }

    private void createCancelButton() {
        JButton jButtonCancel = new JButton("Cancel");
        jButtonCancel.setBounds(DIALOG_WIDTH - (BUTTON_WIDTH + RIGHT_ALIGN),
                DIALOG_HEIGHT - BUTTON_HEIGHT - TOP, BUTTON_WIDTH, BUTTON_HEIGHT);
        jButtonCancel.addActionListener(owner);
        add(jButtonCancel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jButtonCancel.doClick();
            }
        });
    }

    private JRadioButton createRadioButtonForPredefinedSizes() {
        predefinedSizesButton = new JRadioButton();
        predefinedSizesButton.setSelected(true);
        add(predefinedSizesButton);
        predefinedSizesButton.setBounds(ALIGN, BUTTON_HEIGHT + ALIGN, JRADIO_BUTTON_SIMPLE_SIDE, JRADIO_BUTTON_SIMPLE_SIDE);

        predefinedSizesButton.addActionListener(e -> {
            sizeComboBox.setEnabled(true);
            width.setEnabled(false);
            height.setEnabled(false);
        });

        return predefinedSizesButton;
    }

    private JRadioButton createRadioButtonForCustomSize() {
        JRadioButton customSizeButton = new JRadioButton();
        add(customSizeButton);
        customSizeButton.setBounds(ALIGN, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN, JRADIO_BUTTON_SIMPLE_SIDE, JRADIO_BUTTON_SIMPLE_SIDE);

        customSizeButton.addActionListener(e -> {
            width.setEnabled(true);
            height.setEnabled(true);
            sizeComboBox.setEnabled(false);
        });

        return customSizeButton;
    }

    private void createGroupOfSizeButtons(JRadioButton... buttons) {
        ButtonGroup sizeGroup = new ButtonGroup();
        for (JRadioButton jButton : buttons) {
            sizeGroup.add(jButton);
        }
    }

    private void createComboBoxOfPredefinedSizes() {
        sizeComboBox = new JComboBox<>();
        sizeComboBox.addItem("large  (24*32)");
        sizeComboBox.addItem("middle (16*18)");
        sizeComboBox.addItem("small  (8*10)");
        sizeComboBox.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE, BUTTON_HEIGHT + ALIGN, 120, JRADIO_BUTTON_SIMPLE_SIDE);
        sizeComboBox.setSelectedIndex(1);
        add(sizeComboBox);
    }

    private void createLabelForSizeBlock() {
        JLabel sizeLabel = new JLabel("<html><h2>Size : <h2></html>");
        sizeLabel.setBounds(LEFT_ALIGN, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(sizeLabel);
    }

    private void createWidthLabelForCustomSize() {
        JLabel w = new JLabel("w:");
        w.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN, 15, JRADIO_BUTTON_SIMPLE_SIDE);
        add(w);
    }

    private void createHeightLabelForCustomSize() {
        JLabel h = new JLabel("h:");
        h.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + 60, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN, 15, JRADIO_BUTTON_SIMPLE_SIDE);
        add(h);
    }

    private void createWidthAndHeightLabelsFroCustomSize() {
        createWidthLabelForCustomSize();
        createHeightLabelForCustomSize();
    }

    private NumberFormatter createFormatterForNumberTextField() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(24);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //todo setMaximum for heigh
        formatter.setMaximum(32);

        return formatter;
    }

    private void createWidthFieldForCustomSize() {
        NumberFormatter formatter = createFormatterForNumberTextField();
        width = new JFormattedTextField(formatter);
        width.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + 15, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN, 35, JRADIO_BUTTON_SIMPLE_SIDE);
        width.setEnabled(false);
        add(width);
    }

    private void createHeightFieldForCustomSize() {
        NumberFormatter formatter = createFormatterForNumberTextField();
        height = new JFormattedTextField(formatter);
        height.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + 60 + 15, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN, 35, JRADIO_BUTTON_SIMPLE_SIDE);
        height.setEnabled(false);
        add(height);
    }

    private void createWidthLimitLabelForCustomSize() {
        JLabel faqW = new JLabel("<= 24");
        faqW.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + 15, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN + 20, 35, JRADIO_BUTTON_SIMPLE_SIDE);
        add(faqW);
    }

    private void createHeightLimitLabelForCustomSize() {
        JLabel faqH = new JLabel("<= 32");
        faqH.setBounds(ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + 60 + 15, BUTTON_HEIGHT + ALIGN + JRADIO_BUTTON_SIMPLE_SIDE + ALIGN + 20, 35, JRADIO_BUTTON_SIMPLE_SIDE);
        add(faqH);
    }

    private void createLevelLabel() {
        JLabel levelLabel = new JLabel("<html><h2>Level : <h2></html>");
        levelLabel.setBounds(DIALOG_WIDTH / 2 + RIGHT_ALIGN, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(levelLabel);
    }

    private void createComboBoxForLevels() {
        levelComboBox = new JComboBox<>();
        levelComboBox.addItem("1 - Junior");
        levelComboBox.addItem("2");
        levelComboBox.addItem("3 - Middle");
        levelComboBox.addItem("4");
        levelComboBox.addItem("5 - Senior");
        levelComboBox.addItem("6");
        levelComboBox.addItem("7 - To Hard");
        levelComboBox.addItem("8");
        levelComboBox.addItem("9 - Unreal");
        levelComboBox.setSelectedIndex(2);//todo magic number
        levelComboBox.setBounds(DIALOG_WIDTH / 2 + ALIGN, BUTTON_HEIGHT + ALIGN, 120, JRADIO_BUTTON_SIMPLE_SIDE);
        add(levelComboBox);
    }


    /**
     * Викликається при натисканні ОК і надає головному вікну встановлений користувачем розмір
     *
     * @return повертає вибраний розмір таблиці
     */
    Dimension getSizeInfo() {
        /*тимчасовий обєкт класу Dimension*/
        Dimension temporary = new Dimension();
        /*якщо комбобокс є доступним для вибору , то беремо з нього дані*/
        if (sizeComboBox.isEnabled()) {
            /*Перевіряємо який елемн вибраний*/
            switch (sizeComboBox.getSelectedIndex()) {
                case 0 -> temporary = new Dimension(24, 32);
                case 1 -> temporary = new Dimension(16, 18);
                case 2 -> temporary = new Dimension(8, 10);
            }
        }
        /*беремо дані з форматованих полів , якщо комбобокс недоступний*/
        else {
            String w = width.getText();
            String h = height.getText();
            temporary = new Dimension(Integer.parseInt(w), Integer.parseInt(h));
        }
        /*повертаємо розмір*/
        return temporary;
    }

    /**
     * Викликається при натисканні ОК і надає головному вікну встановлений користувачем рівень гри
     *
     * @return повертає рівень гри
     */
    int getLevelInfo() {
        /*повертаєм індекс вибраного елемент і додаємо 1 для коригування рівня*/
        return levelComboBox.getSelectedIndex() + 1;
    }

    JComboBox<String> getSizeComboBox() {
        return sizeComboBox;
    }

    JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }

    JFormattedTextField getJFTFWidth() {
        return width;
    }

    JFormattedTextField getJFTFHeight() {
        return height;
    }

    JRadioButton getSizePatternRadioButton() {
        return predefinedSizesButton;
    }
}

