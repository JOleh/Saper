package Interface;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

class OptionDialog extends JDialog{

    /**
     * Властивість - комбобокс для вибору розміру таблиці за шаблонами
     */
    private JComboBox<String> sizeComboBox;
    /**
     * Властивість - комбобокс для вибору рівня гри за шаблонами
     */
    private JComboBox<String> levelComboBox;
    /**
     * Властивість - форматоване поле для вказання ширини поля (в комірках)
     */
    private JFormattedTextField width;
    /**
     * Властивість - форматоване поле для вказання висоти поля (в комірках)
     */
    private JFormattedTextField height;
    /**
     * Радіокнопка для керування способом введення
     */
    private JRadioButton sizePattern;

    /* Методи для повернення обєктів заповнення для їх використання на вищих рівнях програми*/
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
        return sizePattern;
    }
    /**
     * Конструктор клас
     * @param owner - власник діалогово вікна
     */
    OptionDialog(MainFrame owner) {
        /*Виклик конструктора суперкласу*/
        super(owner);
        /*
      Властивість - ширина діалогового вікна
     */
        int DIALOG_WIDTH = 300;
        /*
      Властивість - висота діалогового вікна
     */
        int DIALOG_HEIGHT = 200;
         /*
      Властивіть - відступ від правого краю
     */
        int RIGHT_ALIGN = 20;
        /*
      Властивість - ширина кнопки
     */
        int BUTTON_WIDTH = 80;
        /*
      Властивість - висота кнопки
     */
        int BUTTON_HEIGHT = 25;
        /*
      Властивість - відступ зверху
     */
        int TOP = 39;
          /*
      Властивісь - стандартний відступ
     */
        int ALIGN = 10;
        /*
      Властивіть - довжина сторини JRadioButton
     */
        int SIMPLE_SIDE = 25;
        /*
      Властивість - відступ від лівого краю
     */
        int LEFT_ALIGN = 20;


        /*Встановлення заголовку діалогового вікна*/
        setTitle("Options...");
        /*Встановлення розміру діалогового вікна*/
        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        /*Встановлення способу розміщення компонентів в вікні*/
        setLayout(null);
        /*Встановлення можливості зміни розмірів вікна*/
        setResizable(false);

        /*Створення кнопки ОК*/
        JButton jButtonOK = new JButton("OK");
        /*Створення кнопки Cancel*/
        JButton jButtonCancel = new JButton("Cancel");

        /*Надання кнопкам розміщення в вікні*/
        jButtonOK.setBounds(DIALOG_WIDTH -(BUTTON_WIDTH + RIGHT_ALIGN)*2,
                DIALOG_HEIGHT - BUTTON_HEIGHT - TOP, BUTTON_WIDTH, BUTTON_HEIGHT);
        jButtonCancel.setBounds(DIALOG_WIDTH -(BUTTON_WIDTH + RIGHT_ALIGN),
                DIALOG_HEIGHT - BUTTON_HEIGHT - TOP, BUTTON_WIDTH, BUTTON_HEIGHT);
        /*Надання кнопкам слухавок. Події будуть оброблятись в батьківсьокому класі вікна*/
        jButtonOK.addActionListener(owner);
        jButtonCancel.addActionListener(owner);
        /*Додавання кнопок в вікно*/
        add(jButtonOK);
        add(jButtonCancel);

        /*Створення групи кнопок*/
        ButtonGroup sizeGroup = new ButtonGroup();
        /*Створення радіокнопки для шаблонного введення розміру*/
        sizePattern = new JRadioButton();
        /*Створення падіокнопки для самостійного введення*/
        JRadioButton sizeByYourself = new JRadioButton();

        /*Надання властивості радіокнопці шаблоного введення за замовчуванням вибраної*/
        sizePattern.setSelected(true);

        /*Додавання радіокнопок в групу кнопок*/
        sizeGroup.add(sizePattern);
        sizeGroup.add(sizeByYourself);

        /*Додавання кнопок на вікно*/
        add(sizePattern);
        add(sizeByYourself);

        /*Встановлення радіокнопкам розміщення на вікні*/
        sizePattern.setBounds(ALIGN, BUTTON_HEIGHT + ALIGN, SIMPLE_SIDE, SIMPLE_SIDE);
        sizeByYourself.setBounds(ALIGN, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN, SIMPLE_SIDE, SIMPLE_SIDE);

        /*Створення мітки для відображення групи вибору розміру таблиці*/
        JLabel sizeLabel = new JLabel("<html><h2>Size : <h2></html>");
        /*Встановлення розміщення мітки на вікні*/
        sizeLabel.setBounds(LEFT_ALIGN, 0 , BUTTON_WIDTH, BUTTON_HEIGHT);
        /*Додавання мітки в вікно*/
        add(sizeLabel);

        /*Створення комбобоксу для вибору шаблонного розміру*/
        sizeComboBox = new JComboBox<>();
        /*Створення елементів комбобоксу з шаблонними розмірами*/
        sizeComboBox.addItem("large  (24*32)");
        sizeComboBox.addItem("middle (16*18)");
        sizeComboBox.addItem("small  (8*10)");
        /*Встановлення розміщення комбобоксу в вікні*/
        sizeComboBox.setBounds(ALIGN + SIMPLE_SIDE, BUTTON_HEIGHT + ALIGN,120 , SIMPLE_SIDE);
        /*Встановлення в комбобоксі 2 елемента як зазамовчуванням*/
        sizeComboBox.setSelectedIndex(1);
        /*Додавання комбобоксу в вікно*/
        add(sizeComboBox);

        /*Створення мітки для умовного позначення введення ширини таблиці якщо користувач вибрав ручне введення*/
        JLabel w = new JLabel("w:");
        /*Встановлення розміщення мітки на вікні*/
        w.setBounds(ALIGN + SIMPLE_SIDE, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN, 15 , SIMPLE_SIDE);
        /*Додавання мітки в вікно*/
        add(w);
        /*Створення мітки для умовного позначення введення висоти таблиці ,якщо користувач вибрав ручне введення*/
        JLabel h = new JLabel("h:");
        /*Встановлення розміщення мітки на вікні*/
        h.setBounds(ALIGN + SIMPLE_SIDE +60 , BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN, 15 , SIMPLE_SIDE);
        /*Додавання мітки в вікно*/
        add(h);

        /*Створення формату введення даних в текстове поле*/
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        /*Надання форматеру властивості для цілих чисел*/
        formatter.setValueClass(Integer.class);
        /*Встановлення мініму для введення*/
        formatter.setMinimum(1);
        /*Встановлення максимуму для введення у поле ширини*/
        formatter.setMaximum(24);
        /*Встановлення властивості введення лише цілих чисел в заданому діапазоні*/
        formatter.setAllowsInvalid(false);
        /*сам не вшарив за що відповідає дана властивість*/
        formatter.setCommitsOnValidEdit(true);

        /*Створення обєкту форматованого текстового поля для введення ширини*/
        width = new JFormattedTextField(formatter);
        /*Встановлення розміщення поля в вікні*/
        width.setBounds(ALIGN + SIMPLE_SIDE +15, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN, 35 , SIMPLE_SIDE);
        /*Встановлення неможливості використання (за замовчуванням)*/
        width.setEnabled(false);
        /*Додавання поля в вікно*/
        add(width);
        /*Встановлення максимуму для введення у поли висоти*/
        formatter.setMaximum(32);
        /*Створення обєкту форматованого текстового поля для введення висоти*/
        height = new JFormattedTextField(formatter);
        /*Встановлення розміщення поля в вікні*/
        height.setBounds(ALIGN + SIMPLE_SIDE +60+15, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN, 35 , SIMPLE_SIDE);
        /*Встановлення неможливості використання (за замовчуванням)*/
        height.setEnabled(false);
        /*Додавання поля в вікно*/
        add(height);
        /*Створення мітки для того щоб вказати користувачу ліміт введенння ширини*/
        JLabel faqW = new JLabel("<= 24");
        /*Встановлення розміщення мітки в вікні*/
        faqW.setBounds(ALIGN + SIMPLE_SIDE +15, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN +20, 35, SIMPLE_SIDE);
        /*Додавання мітки в вікно*/
        add(faqW);
        /*Створення мітки для того щоб вказати користувачу ліміт введенння висоти*/
        JLabel faqH = new JLabel("<= 32");
        /*Встановлення розміщення мітки в вікні*/
        faqH.setBounds(ALIGN + SIMPLE_SIDE +60+15, BUTTON_HEIGHT + ALIGN + SIMPLE_SIDE + ALIGN +20, 35 , SIMPLE_SIDE);
        /*Додавання мітки в вікно*/
        add(faqH);

        /*Створення мітки для відображення групи вибору рівня гри*/
        JLabel levelLabel = new JLabel("<html><h2>Level : <h2></html>");
        /*Встановлення розміщення в вікні*/
        levelLabel.setBounds(DIALOG_WIDTH /2+ RIGHT_ALIGN, 0 , BUTTON_WIDTH, BUTTON_HEIGHT);
        /*Додавання мітки в вікно*/
        add(levelLabel);

        /*Створення комбобоксу для шаблонного вибору рівня гри*/
        levelComboBox = new JComboBox<>();
        /*Створення елементів комбобоксу*/
        levelComboBox.addItem("1 - Junior");
        levelComboBox.addItem("2");
        levelComboBox.addItem("3 - Middle");
        levelComboBox.addItem("4");
        levelComboBox.addItem("5 - Senior");
        levelComboBox.addItem("6");
        levelComboBox.addItem("7 - To Hard");
        levelComboBox.addItem("8");
        levelComboBox.addItem("9 - Unreal");
        /*Надання 3 елементу властивості бути вибраним за замовчуванням*/
        levelComboBox.setSelectedIndex(2);
        /*Встановлення розміщення комбобоксу в вікні*/
        levelComboBox.setBounds(DIALOG_WIDTH /2+ ALIGN, BUTTON_HEIGHT + ALIGN, 120 , SIMPLE_SIDE);
        /*Додавання комбобоксу в вікно*/
        add(levelComboBox);

        /*Надання властивсі приховувати діалогове вікно при натисканні на Exit*/
        //setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        /*Додавання слухавки діалоговому вікну для слухання подій вікна*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                /*Емулювання нажаття кнопки Cancel при виході з вікна*/
                jButtonCancel.doClick();
            }
        });

        /*Надання радіокнопкам слухавок, які обробляються локально*/
        sizePattern.addActionListener(e -> {
            /*При встановленні радіокнопки вибору шаблонно розміру :
                встановити доступним комбобокс розміру
                встановити недопустимим використання форматованих полів введення висоти і ширини
             */
            sizeComboBox.setEnabled(true);
            width.setEnabled(false);
            height.setEnabled(false);
        });
        sizeByYourself.addActionListener(e -> {
            /*При встановленні радіокнопки самостійного вибору розміру :
                встановити недоступним для вибору комбобокс розміру
                встановити допустимим для введення форматованих полів введення висоти і ширини
             */
            width.setEnabled(true);
            height.setEnabled(true);
            sizeComboBox.setEnabled(false);
        });
    }

    /**
     * Викликається при натисканні ОК і надає головному вікну встановлений користувачем розмір
     * @return повертає вибраний розмір таблиці
     */
    Dimension getSizeInfo(){
        /*тимчасовий обєкт класу Dimension*/
        Dimension temporary = new Dimension();
        /*якщо комбобокс є доступним для вибору , то беремо з нього дані*/
        if(sizeComboBox.isEnabled()){
            /*Перевіряємо який елемн вибраний*/
            switch (sizeComboBox.getSelectedIndex()){
                case 0: temporary = new Dimension(24, 32);
                    break;
                case 1: temporary = new Dimension(16, 18);
                    break;
                case 2: temporary = new Dimension(8, 10);
                    break;
            }
        }
        /*беремо дані з форматованих полів , якщо комбобокс недоступний*/
        else{
            String w = width.getText();
            String h = height.getText();
            temporary = new Dimension(Integer.valueOf(w), Integer.valueOf(h));
        }
        /*повертаємо розмір*/
        return temporary;
    }

    /**
     * Викликається при натисканні ОК і надає головному вікну встановлений користувачем рівень гри
     * @return повертає рівень гри
     */
    int getLevelInfo(){
        /*повертаєм індекс вибраного елемент і додаємо 1 для коригування рівня*/
        return levelComboBox.getSelectedIndex()+1;
    }
}

