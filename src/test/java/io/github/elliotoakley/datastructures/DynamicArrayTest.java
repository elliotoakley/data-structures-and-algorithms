package io.github.elliotoakley.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    @Test
    void constructor_withInitialCapacity_createsArray() {
        DynamicArray<Integer> array = new DynamicArray<>(5);

        array.add(10);

        assertEquals(10, array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void constructor_withZeroCapacity_createsArray() {
        DynamicArray<Integer> array = new DynamicArray<>(0);

        array.add(10);

        assertEquals(10, array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void constructor_withNegativeCapacity_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new DynamicArray<Integer>(-1));
    }

    @Test
    void add_whenInitialCapacityExceeded_growsArray() {
        DynamicArray<Integer> array = new DynamicArray<>(2);

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void add_withValue_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();

        array.add(10);
        array.add(20);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void add_withStringType_addsElement() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add("Java");

        assertEquals("Java", array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void add_whenCapacityExceeded_growsArray() {
        DynamicArray<Integer> array = new DynamicArray<>();

        for (int i = 0; i < 12; i++) {
            array.add(i);
        }

        assertEquals(12, array.size());

        for (int i = 0; i < 12; i++) {
            assertEquals(i, array.get(i));
        }
    }

    @Test
    void add_withNull_addsElement() {
        DynamicArray<String> array = new DynamicArray<>();

        array.add(null);

        assertNull(array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void add_withValidIndex_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        array.add(1, 15);

        assertEquals(10, array.get(0));
        assertEquals(15, array.get(1));
        assertEquals(20, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void add_withInvalidIndex_throwsException() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.add(-1, 20));

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.add(2, 20));
    }

    @Test
    void add_atBeginning_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        array.add(0, 5);

        assertEquals(5, array.get(0));
        assertEquals(10, array.get(1));
        assertEquals(20, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void add_atEnd_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        array.add(2, 30);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void add_withIndex_whenCapacityExceeded_growsArray() {
        DynamicArray<Integer> array = new DynamicArray<>();

        for (int i = 0; i < 10; i++) {
            array.add(i);
        }

        array.add(5, 100);

        assertEquals(100, array.get(5));
        assertEquals(5, array.get(6));
        assertEquals(11, array.size());
    }

    @Test
    void add_toEmptyArray_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();

        array.add(0, 10);

        assertEquals(10, array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void set_withValidIndex_replacesElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        Integer oldValue = array.set(1, 30);

        assertEquals(20, oldValue);
        assertEquals(30, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void set_withInvalidIndex_throwsException() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.set(-1, 20));

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.set(1, 20));
    }

    @Test
    void set_withNull_replacesElement() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");

        String oldValue = array.set(0, null);

        assertEquals("Java", oldValue);
        assertNull(array.get(0));
        assertEquals(1, array.size());
    }

    @Test
    void get_withValidIndex_returnsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(20, array.get(1));
    }

    @Test
    void get_withInvalidIndex_throwsException() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.get(-1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.get(1));
    }

    @Test
    void remove_withValidIndex_removesElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        Integer removed = array.remove(1);

        assertEquals(20, removed);
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withInvalidIndex_throwsException() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.remove(-1));

        assertThrows(IndexOutOfBoundsException.class,
                () -> array.remove(1));
    }

    @Test
    void remove_withFirstIndex_removesElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        Integer removed = array.remove(0);

        assertEquals(10, removed);
        assertEquals(20, array.get(0));
        assertEquals(30, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withLastIndex_removesElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        Integer removed = array.remove(2);

        assertEquals(30, removed);
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withIntegerValue_removesElementByValue() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        boolean removed = array.remove(Integer.valueOf(20));

        assertTrue(removed);
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withExistingValue_removesElement() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");
        array.add("Go");

        boolean removed = array.remove("Python");

        assertTrue(removed);
        assertEquals("Java", array.get(0));
        assertEquals("Go", array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withDuplicateValues_removesFirstOccurrence() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");
        array.add("Java");
        array.add("Go");

        boolean removed = array.remove("Java");

        assertTrue(removed);
        assertEquals("Python", array.get(0));
        assertEquals("Java", array.get(1));
        assertEquals("Go", array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void remove_withMissingValue_returnsFalse() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");

        boolean removed = array.remove("Go");

        assertFalse(removed);
        assertEquals("Java", array.get(0));
        assertEquals("Python", array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void remove_withNull_removesElement() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add(null);
        array.add("Go");

        boolean removed = array.remove(null);

        assertTrue(removed);
        assertEquals("Java", array.get(0));
        assertEquals("Go", array.get(1));
        assertEquals(2, array.size());
        assertFalse(array.contains(null));
    }

    @Test
    void contains_withExistingValue_returnsTrue() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");

        assertTrue(array.contains("Python"));
    }

    @Test
    void contains_withMissingValue_returnsFalse() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");

        assertFalse(array.contains("Python"));
    }

    @Test
    void contains_withNull_returnsTrue() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add(null);

        assertTrue(array.contains(null));
    }

    @Test
    void indexOf_withExistingValue_returnsIndex() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");
        array.add("Go");

        assertEquals(1, array.indexOf("Python"));
    }

    @Test
    void indexOf_withDuplicates_returnsFirstIndex() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");
        array.add("Python");
        array.add("Java");
        array.add("Go");

        assertEquals(0, array.indexOf("Java"));
    }

    @Test
    void indexOf_withMissingValue_returnsMinusOne() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("Java");

        assertEquals(-1, array.indexOf("Python"));
    }

    @Test
    void size_withEmptyArray_returnsZero() {
        DynamicArray<Integer> array = new DynamicArray<>();

        assertEquals(0, array.size());
    }

    @Test
    void size_withElements_returnsSize() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        assertEquals(2, array.size());
    }

    @Test
    void isEmpty_withEmptyArray_returnsTrue() {
        DynamicArray<Integer> array = new DynamicArray<>();

        assertTrue(array.isEmpty());
    }

    @Test
    void isEmpty_withElements_returnsFalse() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        assertFalse(array.isEmpty());
    }

    @Test
    void clear_withElements_removesAllElements() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(3, array.size());

        array.clear();

        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertFalse(array.contains(20));
    }

    @Test
    void clear_thenAdd_addsElement() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(10);
        array.add(20);

        assertEquals(2, array.size());

        array.clear();

        array.add(30);

        assertEquals(30, array.get(0));
        assertEquals(1, array.size());
        assertFalse(array.isEmpty());
    }
}
