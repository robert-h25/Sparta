package com.sparta.rh.hamcrest;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CollectionMatchers {

    List<String> fruit = List.of(
            "apple", "pear", "banana", "peach", "pomegranate", "pineapple", "lemon");

    @Test
    void givenFruit_SizeIsChecked_SizeEquals7() {
        assertThat(fruit, hasSize(7));
    }

    @Test
    void givenFruit_CheckingItemsInOrder_FruitShouldAppearInCorrectOrder() {
        List<String> expectedItems = List.of("apple", "banana", "pomegranate", "lemon");

        int lastIndex = -1;
        for (int i = 0; i < expectedItems.size(); i++) {
            String expected = expectedItems.get(i);
            int currentIndex = fruit.indexOf(expected);
            assertThat(currentIndex, greaterThan(lastIndex));
            lastIndex = currentIndex;
        }
    }

    @Test
    void givenFruit_CheckingItemsInAnyOrder_ItemsShouldBeInFruit() {
        assertThat(fruit, hasItems("banana", "pear"));
    }

    @Test
    void givenFruit_CheckingExactItems_ItemsShouldBeInFruit() {
        assertThat(fruit, containsInAnyOrder(
                "lemon", "pineapple", "pomegranate", "apple", "peach", "pear", "banana"));
    }

    @Test
    void givenFruit_CheckingForGrape_FruitShouldNotContainGrape() {
        assertThat(fruit, not(hasItem("grape")));
    }

    @Test
    void givenFruit_CheckingForItemEndingWithZ_FruitShouldNotContainItemEndingWithZ() {
        assertThat(fruit, not(hasItem(endsWith("z"))));
    }

}
