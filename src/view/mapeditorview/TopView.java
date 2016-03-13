package view.mapeditorview;

import view.BaseWindowView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class TopView extends JPanel {

    public JComboBox widthList;
    public JComboBox heightList;
    public JLabel widthLabel;
    public JLabel heightLabel;
    public JButton saveButton;
    public JButton discardButton;

    public final static String[] widthStrings = {"5","10","15","20","25","30"};
    public final static String[] heightStrings = {"10","15"};

    public TopView(){
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(BaseWindowView.WINDOW_WIDTH, BaseWindowView.WINDOW_HEIGHT / 10));
        initComponents();
    }

    void initComponents(){
        widthList = new JComboBox(widthStrings);
        heightList = new JComboBox(heightStrings);
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");
        saveButton = new JButton("Save");
        discardButton = new JButton("Discard");

        // Flow Layout: Horizontal
        this.add(widthLabel);
        this.add(widthList);
        this.add(heightLabel);
        this.add(heightList);
        this.add(saveButton);
        this.add(discardButton);
    }

}
