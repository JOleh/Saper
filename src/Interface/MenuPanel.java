package Interface;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Клас панелі головного меню програми.
 * Наслідує JMenuBar.
 */
class MenuPanel extends JMenuBar {
    /*Конструктор класу*/
    MenuPanel(MainFrame parent) {
        /*Створення обєкту головного меню*/
        JMenu jMenu = new JMenu("Menu");
        /*Створення обєкту додаткового меню*/
        JMenu optionsMenu = new JMenu("Options");

        /*Створення пункту меню для початку нової гри*/
        JMenuItem newGameItem = new JMenuItem("New Game");
        /*Створення понкту меню для виходу з програми*/
        JMenuItem exitGameItem = new JMenuItem("Exit");
        /*Додавання слухавки для пункту меню. Буде оброблятися батьківським класом до панелі меню.*/
        newGameItem.addActionListener(parent);
        /*Додавання слухавки для пункту меню. Буде оброблятися локально.*/
        exitGameItem.addActionListener(e -> System.exit(0));
        /*Додавання пунктів меню в головне меню*/
        jMenu.add(newGameItem);
        jMenu.add(exitGameItem);
        /*Додавання головно меню в панель меню*/
        add(jMenu);

        /*Створення пункту меню для вибору опцій гри*/
        JMenuItem optionsItem = new JMenuItem("Options");
        /*Створення пункту меню для отримання додаткової інформації про гру/програму*/
        JMenuItem infoItem = new JMenuItem("Info");

        /*Додавання слухавок пунктам меню. Будуть оброблятися батьківським класом до панелі меню.*/
        optionsItem.addActionListener(parent);
        infoItem.addActionListener(parent);

        /*Додавання пунктів меню в додаткове меню*/
        optionsMenu.add(optionsItem);
        optionsMenu.add(infoItem);
        /*Додавання додаткового меню в панель меню*/
        add(optionsMenu);

    }
}
