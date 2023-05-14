package com.example.NumberRangeSummarizer.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class NumberRangeSummarizerIml implements NumberRangeSummarizer {
    /**
     * Collects input string of comma separated numbers and returns a Collection of Integers.
     * If input string is null or empty, an empty Collection is returned.
     * Any non-integer values are ignored.
     *
     * @param input input string of comma separated numbers
     * @return a Collection of Integers
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        String[] tokens = input.split(",");
        for (String token : tokens) {
            try {
                result.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                // ignore non-integer values
            }
        }
        return result;
    }

    /**
     * Takes a Collection of Integers and returns a summarized string of the numbers,
     * grouping the numbers into a range when they are sequential.
     * If input Collection is null or empty, an empty string is returned.
     *
     * @param input a Collection of Integers
     * @return a summarized string of the numbers
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> sortedInput = new ArrayList<>(input);
        Collections.sort(sortedInput);

        StringBuilder sb = new StringBuilder();
        int start = sortedInput.get(0);
        int end = start;

        for (int i = 1; i < sortedInput.size(); i++) {
            int num = sortedInput.get(i);
            if (num == end + 1) {
                end = num;
            } else {
                sb.append(getRange(start, end)).append(", ");
                start = end = num;
            }
        }

        sb.append(getRange(start, end));
        return sb.toString();
    }

    /**
     * Helper method to format a range of numbers.
     * If start and end are equal, the method returns start as a String.
     * Otherwise, the method returns start-end as a String.
     *
     * @param start the start number of the range
     * @param end the end number of the range
     * @return a formatted range of numbers
     */
    private String getRange(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "-" + end;
        }
    }
}
