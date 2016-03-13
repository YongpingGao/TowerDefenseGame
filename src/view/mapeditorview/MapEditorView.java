package view.mapeditorview;


import view.BaseWindowView;
import java.awt.*;

/**
 * Created by yongpinggao on 1/24/16.
 */
public class MapEditorView extends BaseWindowView {

    public TopView topView;
    public MapView mapView;

    public MapEditorView(){
        super("Map Editor");
        topView = new TopView();
        mapView = new MapView();

        Container c = this.getContentPane();
        c.add(topView, BorderLayout.PAGE_START);
        c.add(mapView, BorderLayout.LINE_START);

    }


}

