package com.leush.ui;

import com.leush.ImageFactory;
import com.leush.Images;
import com.leush.controller.Cell;
import com.leush.controller.Restart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

@Component
@Slf4j
public class MainFrame extends JFrame implements ActionListener, MouseListener, Restart{

    //@Autowired
    //private ImageFactory imageFactory;

    /** Панель з кнопкою рестарту */
    private OptionsPanel optionsPanel;
    /** Ігрова таблиця*/
    private BoardPanel boardPanel;
    /** Діалог зміни параметрів*/
    private OptionDialog optionDialog;
    /** Діалог додатккової інформації*/
    private InfoDialog infoDialog;
    /**Діалог привітання*/
    private CongratulationsDialog congratulationsDialog;

    /** Властивість - рівень гри*/
    private int level;
    /** Властвість - розмір поля*/
    private Dimension size;
    /** Стандартний відступ*/
    private int ALIGN = 10;
    /** Відступ зверху*/
    private int TOP = 39;
    /** Відступ знизу*/
    private int BOTTOM = 15;

    public MainFrame() {
        init();
    }

    private void init(){
        /* Встановлення заголовку вікна*/
        setTitle("Miner");
        /*Створення іконки для вікна*/

        log.info("Before saper image : ");// + Objects.isNull(imageFactory));
        ImageIcon imageIcon = new ImageFactory().getImageIcon(Images.MINESWEEPER);
        log.info("After saper Image");
        /*Встановлення іконки для вікна*/
        setIconImage(imageIcon.getImage());
        setBackground(Color.GRAY);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setJMenuBar(new MenuPanel(this));
        optionDialog = new OptionDialog(this);
        infoDialog = new InfoDialog();
        congratulationsDialog = new CongratulationsDialog();

        /* Встановлення рівня гри*/
        level = optionDialog.getLevelInfo();
        /* Встановлення розміру поля*/
        size = optionDialog.getSizeInfo();

        /* Встановленння розміру вікна*/
        setSize(new Dimension(Cell.SIDE_LENGTH*size.width + ALIGN*3 ,
                Cell.SIDE_LENGTH*size.height+OptionsPanel.OPTIONAL_PANEL_HEIGHT+ALIGN+TOP+BOTTOM));
        /* Створення обєкту додаткової панелі з кнопкою*/
        optionsPanel = new OptionsPanel(this);
        /* Надання панелі розміщення в вікні*/
        optionsPanel.setBounds(0,0,getWidth() , OptionsPanel.OPTIONAL_PANEL_HEIGHT);
        /* Створення поля гри*/
        boardPanel = new BoardPanel(level , size , this);
        /* Надання полю ри позиції в вікні*/
        boardPanel.setBounds((getWidth()-Cell.SIDE_LENGTH*size.width-8)/2 ,
                OptionsPanel.OPTIONAL_PANEL_HEIGHT ,
                Cell.SIDE_LENGTH*size.width ,
                Cell.SIDE_LENGTH*size.height);
        /* Додавання поля гри в вікно*/
        add(boardPanel);
        /* Додавання додаткової панелі в вікно*/
        add(optionsPanel);
    }

    /** Додаткова змінна для збереження індексу вибраного рівня в діалозі опцій при його відкритті*/
    private int selectedIndexLevel = 0;
    /** Додаткова змінна для збереження індексу вибраного розміру в діалозі опцій при його відкритті*/
    private int selectedIndexSize = 0;
    /** Додаткова змінна для збереження висоти в діалозі опцій при його відкритті*/
    private String previousHeightTF = "";
    /** Додаткова змінна для збереження ширини в діалозі опцій при його відкритті*/
    private String previousWidthTF = "";
    /** Додаткова змінна для збереження активності поля вибору розміру*/
    private boolean selectedRadioButton;

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Дії при виборі пункту меню Options */
        if(e.getActionCommand().equals("Options")){
            /* Збереження властивостей при вході в діалог опцій */
            selectedIndexLevel = optionDialog.getLevelComboBox().getSelectedIndex();
            selectedIndexSize = optionDialog.getSizeComboBox().getSelectedIndex();
            previousHeightTF = optionDialog.getJFTFHeight().getText();
            previousWidthTF = optionDialog.getJFTFWidth().getText();
            selectedRadioButton = optionDialog.getSizePatternRadioButton().isSelected();
            /* Встановлення діалогу опцій видимим*/
            optionDialog.setVisible(true);
        }
        /* Дії при виборі пункту меню Info */
        else if(e.getActionCommand().equals("Info")){
            /* Встановлення діалогу додаткової інформації видимим*/
            infoDialog.setVisible(true);
        }
        /* Дії при нажатті кнопки OK в діалозі опцій */
        else if(e.getActionCommand().equals("OK")){
            /* Змінення розмірів вікна і гри */
            resize();
            /* Встановлення діалогу опцій невидимим*/
            optionDialog.setVisible(false);
        }
        /* Дії при нажатті кнопки Cancel в діалозі опцій */
        else if(e.getActionCommand().equals("Cancel")){
            /* Відновлення всіх попередніх властивостей */
            optionDialog.getLevelComboBox().setSelectedIndex(selectedIndexLevel);
            optionDialog.getSizeComboBox().setSelectedIndex(selectedIndexSize);
            optionDialog.getJFTFHeight().setText(previousHeightTF);
            optionDialog.getJFTFWidth().setText(previousWidthTF);
            optionDialog.getSizePatternRadioButton().setSelected(selectedRadioButton);
            /* Встановлення попередніх властивостей активності компонентів*/
            if(selectedRadioButton){
                optionDialog.getSizeComboBox().setEnabled(true);
                optionDialog.getJFTFWidth().setEnabled(false);
                optionDialog.getJFTFHeight().setEnabled(false);
            }else {
                optionDialog.getSizeComboBox().setEnabled(false);
                optionDialog.getJFTFWidth().setEnabled(true);
                optionDialog.getJFTFHeight().setEnabled(true);
            }
            /* Встановлення діалогу опцій невидимим*/
            optionDialog.setVisible(false);
        }
        /* Дії при виборі пункту меню New Game */
        else if( e.getActionCommand().equals("New Game")){
            /* Рестарт гри з пепередніми властивостями*/
            restartGame();
        }
    }

    /**
     * Зміна розмірів вікна , гри і всіх компонентів при зміні властивостей гри
     */
    @Override
    public void resize(){
        /* Взяття нових властивостей гри*/
        size = optionDialog.getSizeInfo();
        level = optionDialog.getLevelInfo();
        /* Встановлення нового розміру вікна */
        setSize(new Dimension(Cell.SIDE_LENGTH*size.width + ALIGN*3 ,
                Cell.SIDE_LENGTH*size.height+OptionsPanel.OPTIONAL_PANEL_HEIGHT+ALIGN+TOP+BOTTOM));
        /* Видалення додаткової панелі з кнопкою*/
        remove(optionsPanel);
        /* Встановленя властивостей нової панелі з кнопкою*/
        optionsPanel = new OptionsPanel(this);
        optionsPanel.setBounds(0,0,getWidth() , OptionsPanel.OPTIONAL_PANEL_HEIGHT);
        /* Додавання панелі з кнопкою в вікно*/
        add(optionsPanel);
        /* Оновлення інтерфейсу*/
        optionsPanel.updateUI();
        /* рестарт самої гри*/
        restartGame();
    }

    /**
     * Зміна гри при її рестарті
     */
    @Override
    public void restartGame() {

        if(congratulationsDialog.isVisible())  congratulationsDialog.setVisible(false);
        /* Видалення попередньої гри з вікна*/
        remove(boardPanel);
        /* Встановлення властивостей нової панелі з грою */
        boardPanel = new BoardPanel(level , size , this);
        boardPanel.setBounds((getWidth()-Cell.SIDE_LENGTH*size.width-8)/2 ,
                OptionsPanel.OPTIONAL_PANEL_HEIGHT ,
                Cell.SIDE_LENGTH*size.width ,
                Cell.SIDE_LENGTH*size.height);
        add(boardPanel);
        /*Зміна іконки на на кнопці на  додатковій панелі*/
        optionsPanel.getStartButton().setIcon(new ImageFactory().getImageIcon(Images.PLAY_SMILE));
        /* Перемальовування панелі*/
        boardPanel.repaint();
    }

    /**
     * Виконує дії , якщо гравець переміг
     */
    private void winWork(){
        /*Встановлення прапорця перемоги в true*/
        //boardPanel.youWin(true);
        /*Виведенння діалогово вікна для привітання з перемогою*/
        congratulationsDialog.setVisible(true);
        /*Встановлення можливості закрити вікно привітання після 10 секунд , якщо гравець не реагує*/
        /*Отримання поточного часу*/
        long start = System.currentTimeMillis();
        /*Створення нового потоку перевірки зміничасу*/
        new Thread(() -> {
            /*якщо минуло 10с і вікно видиме , то робимо його прихованим*/
            while(true){
                if((System.currentTimeMillis()-start)>10000){
                    if(congratulationsDialog.isVisible()){
                        congratulationsDialog.setVisible(false);
                    }
                    break;
                }
            }
        }).start();
        /*Перемальовування дошки*/
        boardPanel.repaint();
        /*Зміна іконки на додатковій панелі з кнопкою*/
        optionsPanel.getStartButton().setIcon(new ImageFactory().getImageIcon(Images.WIN_SMILE));
        /*Видалення слухавки для миші для панелі з грою для неспроможності продовжувати гри*/
        boardPanel.removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /* Властивість яка зберігає координати комірки по горизонталі над якою була виконана дія*/
        int x = e.getX()/20;
        /* Властивість яка зберігає координати комірки по вертикалі над якою була виконана дія*/
        int y = e.getY()/20;
        /* Властивість , яка зберігає кількість бомб які знаходяться поряд з поточною*/
        int bombAround = boardPanel.getBoard().checkAround(x, y, boardPanel.getBoard().getCells(), size);
        /* Витягнення поточної комірки*/
        Cell temporary = boardPanel.getBoard().getCellByXY(x,y);

        /* Дії при натисканні на панель гри*/
        if(e.getComponent().equals(boardPanel)){
            /* Дії при натисканні лівоїї кнопки миші*/
            if(e.getButton()==MouseEvent.BUTTON1){
                /* Дії якщо вибрана комірка є ще вільною*/
                if(!temporary.isChecked() && !temporary.isExpected()) {
                    /*Дії , якщо в комірці міститься бомба*/
                    if(temporary.isBomb()){
                        bombAround = 8;
                        /*Встановлення прапорця програшу в true*/
                        boardPanel.youLose(true);
                        /*Перемальовування дошки*/
                        boardPanel.repaint();
                        /*Зміна іконки на додатковій панелі з кнопкою*/
                        optionsPanel.getStartButton().setIcon(new ImageFactory().getImageIcon(Images.LOSE_SMILE));
                        /*Видалення слухавки для миші для панелі з грою для неспроможності продовжувати гри*/
                        boardPanel.removeMouseListener(this);
                    }
                    /*Встановлення комірки , як перевіреної*/
                    temporary.setChecked();
                    /*Додання властивості кількості навколишніх бомб поточній комірці*/
                    String msg = String.valueOf(bombAround);
                    if(msg.equals("0")) {
                        msg = "";
                        boardPanel.getBoard().showAround(x,y);
                    }
                    temporary.setMSG(msg);
                    /*Перевірка на наявність перемоги*/
                    if(boardPanel.getBoard().checkIfWin()){
                        /*Якщо гравець переміг*/
                        winWork();
                    }
                }
            /* Дії при натисканні правої кнопки миші*/
            }else if(e.getButton()==MouseEvent.BUTTON3) {
                /*Дії , якщо комірка є ще вільною*/
                if (!temporary.isChecked() && !temporary.isExpected()) {
                    /*Встановлення властивості комірки як очікуваної*/
                    temporary.setExpected();
                    /*Встановлення властивості комірки як перевіреної*/
                    temporary.setChecked();
                    /* Надання властивості повідомлення в комірці про підозру в бомбі*/
                    temporary.setMSG("?");
                }
                /*Дії якщо комірка вже є очікуваною*/
                else if (temporary.isExpected()){
                    /*Встановлення влстивостей очікування і перевірки в false*/
                    temporary.removeExpect();
                    temporary.removeCheck();
                }
                if(boardPanel.getBoard().checkIfWin()){
                    /*Якщо гравець переміг*/
                    winWork();
                }
            }
        /* Перемальовування поля гри */
        boardPanel.repaint();
        }
        /*Дії , якщо була натиснута кнопка на додатковій панелі*/
        else if(e.getComponent().equals(optionsPanel.getStartButton())){
            /*рестарт гри*/
            restartGame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
        if(e.getComponent().equals(optionsPanel.getStartButton())){

            optionsPanel.getStartButton().setIcon(new ImageIcon(getClass().getResource("/resources/smile_play.jpg")));

            restartGame();
        }*/
    }

    /*Нереалізовані віднаслідувані методи обробки подій миші*/
    @Override
    public void mouseReleased(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //TODO
    }

}
