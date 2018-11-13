package at.f1l2.lab.stream;

import static java.util.Comparator.comparing;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class Test {

    public static void main(String[] args) {

        Gegenstand gegenstand = new Gegenstand();
        Antritt antritt1 = new Antritt();
        // antritt1.setDatum(LocalDate.now());
        Antritt antritt2 = new Antritt();
        antritt2.setDatum(LocalDate.now());

        gegenstand.addAntritt(antritt1);
        gegenstand.addAntritt(antritt2);

        Optional<Antritt> antritt = gegenstand.getAntritte().stream().sorted(comparing(Antritt::getDatum, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()).findFirst();
        if (!antritt.isPresent()) {
            return;
        }

    }

}
