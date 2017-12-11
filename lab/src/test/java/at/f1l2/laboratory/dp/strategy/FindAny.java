package at.f1l2.laboratory.dp.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class FindAny {

    @Test
    public void findAnyTest() {

        List<String> stringList = new ArrayList<>();

        stringList.add(null);
        stringList.add("Hallo");
        stringList.add("Halloe");

        stringList.stream().filter(Objects::nonNull).filter(item -> item.startsWith("Ha")).findAny();

        Optional<String> findAny = stringList.stream().filter(Objects::nonNull).filter(item -> item.startsWith("Ha")).findAny();

        Assert.assertEquals(findAny.isPresent(), true);

        String findAny2 = stringList.stream().filter(Objects::nonNull).filter(item -> item.startsWith("123")).findAny().orElse(null);

        Assert.assertEquals(findAny2 != null, false);

    }
}
