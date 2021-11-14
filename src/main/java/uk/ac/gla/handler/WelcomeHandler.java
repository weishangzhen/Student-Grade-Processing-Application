package uk.ac.gla.handler;

import uk.ac.gla.view.MainView;
import uk.ac.gla.view.WelcomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

public class WelcomeHandler extends KeyAdapter implements ActionListener {
    private WelcomeView welcomeView;

    public WelcomeHandler(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonText = jButton.getText();
        if ("Enter".equals(jButtonText)) {
            new MainView();
            welcomeView.dispose();
        } else if ("Exit".equals(jButtonText)) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
            System.exit(0);
        }
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            new MainView();
            welcomeView.dispose();
        }
    }

}
