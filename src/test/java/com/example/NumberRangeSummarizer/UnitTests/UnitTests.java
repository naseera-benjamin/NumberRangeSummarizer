package com.example.NumberRangeSummarizer.UnitTests;

import com.example.NumberRangeSummarizer.service.NumberRangeSummarizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UnitTests {

    @Autowired
    private NumberRangeSummarizer numberRangeSummarizer;

    @Test
    public void testCollect() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        Collection<Integer> actual = numberRangeSummarizer.collect(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollection() {
        Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String actual = numberRangeSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizedCollectionMixed(){
        String expected = "1,3-5,8,10-12,";
        String actual = numberRangeSummarizer.summarizeCollection(Arrays.asList(1,3,4,5,8,10,11,12));
        assertEquals(expected, actual);

    }
}
