package units;

import java.util.ArrayList;

public class UnitRegistry {

    public static ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> list = new ArrayList<>();

        list.add(new Buffer(0, 0));
        list.add(new Fighter(0, 0));
        list.add(new Healer(0, 0));
        list.add(new Mage(0, 0));
        list.add(new Tank(0, 0));

        return list;
    }
}
