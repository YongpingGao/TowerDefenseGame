package view.mapeditorview;


import javax.swing.*;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class PopupMenuView extends JPopupMenu{

    private final String OPTION_ENTRANCE = "Set as an Entrance";
    private final String OPTION_EXIT = "Set as an Exit";

    public JMenuItem menuItem1;
    public JMenuItem menuItem2;

    public PopupMenuView(){
        menuItem1 = new JMenuItem(OPTION_ENTRANCE);
        menuItem2 = new JMenuItem(OPTION_EXIT);
        add(menuItem1);
        add(menuItem2);
    }

}
