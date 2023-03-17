package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> numbers;
    List<Integer> actualsList;
    List<Integer> voidNumbers;
    @BeforeEach
    void initVariable() {
        this.numbers = new ArrayList<>(List.of(1, 2, 4, 5, 7, 10));
        this.actualsList = new ArrayList<>(List.of(1, 2, 4));
        this.voidNumbers = new ArrayList<>();
    }

    @Test
    void testTake_ZeroCount() {
        // BEGIN
        Assertions.assertEquals(App.take(numbers, 0).size(), 0);
        // END
    }

    @Test
    void testTake_ZeroSizeList() {
        // BEGIN
        Assertions.assertEquals(App.take(voidNumbers, 3).size(), 0);
        // END
    }

    @Test
    void testTake_EqualsList() {
        // BEGIN
        Assertions.assertEquals(App.take(numbers, 3), actualsList);
        // END
    }

    @Test
    void testTake_EqualsCount() {
        // BEGIN
        Assertions.assertEquals(App.take(numbers, 3).size(), 3);
        // END
    }
}
