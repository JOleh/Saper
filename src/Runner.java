import Interface.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Вхідна точка запуску програми.
 */
public class Runner {
    public static void main(String[] args) {
        /*Запуск головного вікна в новому потоці за допомогою SwingUtilities*/
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
