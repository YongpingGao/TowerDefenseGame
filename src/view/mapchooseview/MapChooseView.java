package view.mapchooseview;

import view.BaseWindowView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class MapChooseView extends BaseWindowView {

    public JButton startGameButton;
    public JButton editMapButton;
    public JScrollPane pane;
    public JList list;

    public MapChooseView(DefaultListModel listModel) {
        super(260, 200, "Choose a Map");
        startGameButton = new JButton("Start Game");
        editMapButton = new JButton("Edit the Map");
        list = new JList<>(listModel);
        list.setSelectedIndex(0);
        pane = new JScrollPane(list);

        setLayout(new BorderLayout());
        add(pane, BorderLayout.PAGE_START);
        add(editMapButton, BorderLayout.LINE_START);
        add(startGameButton, BorderLayout.LINE_END);


    }
}
