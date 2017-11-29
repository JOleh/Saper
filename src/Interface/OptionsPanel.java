package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Dimension;

/**
 * Кдас додаткової панелі з кнопкою рестарту гри
 * Наслідує JPanel.
 */
class OptionsPanel extends JPanel {
   /*Властивіть кнопки , яка являє собою JLabel з іконкою*/
   private JLabel imageLabel;
   /*Висота панелі*/
    static int OPTIONAL_PANEL_HEIGHT = 35;

    /**
     * Конструктор.
     * @param parent - батьківський клас , в якому буде міститись панель
     */
    OptionsPanel(MainFrame parent) {
        init(parent);
    }

    /**
     * Ініціалізація властивостей панелі
     * @param parent - батьківський клас , в якому буде міститись панель
     */
    private void init(MainFrame parent){
        /*Надання панелі способу розміщенння в ній компонентів*/
        setLayout(new FlowLayout());
        /*Надання розміру панелі. -1 - довжина визначається за довжину батьківського контейнера*/
        setSize(new Dimension(-1 , OPTIONAL_PANEL_HEIGHT));

        /*Створення обєкту мітки з іконкою, яка міститься в ресурсах програми*/
        imageLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/smile_play.jpg")));
        /*Надання мітці слухавки. Буде прослуховуватись батьківським до панелі класом*/
        imageLabel.addMouseListener(parent);
        /*Додавання мітки на панель з розміщенням по центрі*/
        add(imageLabel,SwingConstants.CENTER);
    }

    /**
     * Повертає мітку для роботи з нею на вищих рівнях програми
     * @return - повертає мітку
     */
    JLabel getStartButton(){
        return imageLabel;
    }
}
