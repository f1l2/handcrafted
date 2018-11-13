package at.f1l2.laboratory.stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectorsLab {

    public static void main(String[] args) {

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("PKW", "Seat"));
        cars.add(new Car("PKW", "Skoda"));

        Map<String, Long> result = cars.stream().collect(groupingBy(Car::getType, counting()));

        System.out.println(result.get("PKW"));

    }

}
