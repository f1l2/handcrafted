package at.f1l2.laboratory.stream;

import java.util.ArrayList;
import java.util.List;

public class Gegenstand {

    private List<Antritt> antritte = new ArrayList<>();

    public void addAntritt(Antritt antritt) {
        antritte.add(antritt);
    }
    // Getter and setter

    public List<Antritt> getAntritte() {
        return antritte;
    }

    public void setAntritte(List<Antritt> antritte) {
        this.antritte = antritte;
    }

}
