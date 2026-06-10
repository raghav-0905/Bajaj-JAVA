package com.example.Bajaj.service;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    // ========== HARDCODE YOUR DETAILS HERE ==========
    private static final String USER_ID = "raghavsharma_12092005";
    private static final String EMAIL = "sharmaraghav8512@gmail.com";
    private static final String ROLL_NUMBER = "23070122171";
    // ================================================

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();
        if (data == null) {
            data = Collections.emptyList();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;

        // Collect all alphabetical characters for concat_string
        StringBuilder allAlphaChars = new StringBuilder();

        for (String element : data) {
            if (isNumber(element)) {
                // Pure number string
                int num = Integer.parseInt(element);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
            } else if (isAlpha(element)) {
                // Pure alphabetical string — add element as-is (uppercased) to alphabets list
                alphabets.add(element.toUpperCase());
                // Collect ALL individual characters for concat_string
                allAlphaChars.append(element);
            } else {
                // Special characters / mixed
                specialCharacters.add(element);
            }
        }

        // Build concat_string:
        // 1. Take all collected alpha chars
        // 2. Reverse the whole string
        // 3. Apply alternating caps (index 0 = uppercase, 1 = lowercase, ...)
        String concatString = buildConcatString(allAlphaChars.toString());

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    /**
     * Check if the string is a pure integer (optionally negative).
     */
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if every character in the string is a letter.
     */
    private boolean isAlpha(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Build concat_string:
     * 1. Concatenate all alpha chars into one string
     * 2. Reverse it
     * 3. Apply alternating caps (index 0 = upper, 1 = lower, 2 = upper, ...)
     */
    private String buildConcatString(String allChars) {
        if (allChars.isEmpty()) return "";

        // Reverse
        String reversed = new StringBuilder(allChars).reverse().toString();

        // Alternating caps
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
