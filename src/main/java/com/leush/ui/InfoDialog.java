package com.leush.ui;

import com.leush.BeanProvider;
import com.leush.ResourceFetcher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

class InfoDialog extends JDialog {

    @Autowired
    protected ResourceFetcher resourceFetcher;

    private JTabbedPane panelWithTabs;

    InfoDialog() {
        BeanProvider.autowire(this);
        setSize(new Dimension(300, 400));
        setTitle("Info...");
        setResizable(false);
        createInfoTabs();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void createInfoTabs() {
        panelWithTabs = new JTabbedPane();
        createRulesTab();
        createAuthorTab();

        add(panelWithTabs);
    }

    private void createRulesTab() {
        panelWithTabs.add("Rules", new JPanel() {
            {
                String text = resourceFetcher.readFromFile("text/rules.txt");
                add(new JLabel(text));
            }
        });
    }

    private void createAuthorTab() {
        panelWithTabs.add("Author", new JPanel() {
            {
                String text = resourceFetcher.readFromFile("text/author.txt");
                add(new JLabel(text));
            }

        });
    }
}
