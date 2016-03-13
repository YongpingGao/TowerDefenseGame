package view.mainmenuview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class MainMenuView extends JFrame {

    private final static int WINDOW_WIDTH = 400;
    private final static int WINDOW_HEIGHT = 222;
    private final String MENU_TITLE = "Defence of the Tower";
    public JButton editorButton;
    public JButton startGameButton;


    public MainMenuView(){
        initMenuWindow();
    }

    private void initMenuWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(MENU_TITLE);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        editorButton = new JButton("Map Editor");
        startGameButton = new JButton("Start Game");
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        c.add(editorButton, new GridBagConstraints());
        c.add(startGameButton, new GridBagConstraints());
    }

}
