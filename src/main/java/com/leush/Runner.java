package com.leush;

import com.leush.ui.MainFrame;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class Runner implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Runner.class)
                .headless(false).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(String... args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    /*public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder(Runner.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(MainFrame.class);
            ex.setVisible(true);
        });

    }*/
}
