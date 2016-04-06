package model.critter;


import java.util.ArrayList;


/**
 * Created by yongpinggao on 3/13/16.
 */
public class CritterCollection {

    private ArrayList<Critter> critters = new ArrayList<>();

    public void addCritter(Critter critter){
        critters.add(critter);
    }

    public void removeCritter(Critter critter) {
        critters.remove(critter);
    }

    public void clearAllCritters(){
        critters.clear();
    }

    public ArrayList<Critter> getCritters() {
        return critters;
    }



}
