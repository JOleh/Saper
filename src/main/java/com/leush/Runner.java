package com.leush;

import com.leush.ui.MainFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder(Runner.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(MainFrame.class);
            ex.setVisible(true);
        });

    }
}
