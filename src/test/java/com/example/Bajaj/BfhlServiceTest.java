package com.example.Bajaj;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;
import com.example.Bajaj.service.BfhlService;
import com.example.Bajaj.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlService service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ==================== Example A ====================
    // Input: ["1", "2", "3", "a", "b"]
    @Test
    void testExampleA_oddNumbers() {
        BfhlResponse res = process("1", "2", "3", "a", "b");
        assertEquals(List.of("1", "3"), res.getOddNumbers());
    }

    @Test
    void testExampleA_evenNumbers() {
        BfhlResponse res = process("1", "2", "3", "a", "b");
        assertEquals(List.of("2"), res.getEvenNumbers());
    }

    @Test
    void testExampleA_alphabets() {
        BfhlResponse res = process("1", "2", "3", "a", "b");
        assertEquals(List.of("A", "B"), res.getAlphabets());
    }

    @Test
    void testExampleA_sum() {
        BfhlResponse res = process("1", "2", "3", "a", "b");
        assertEquals("6", res.getSum());
    }

    @Test
    void testExampleA_concatString() {
        // chars: a, b -> reversed: "ba" -> alternating caps: B, a -> "Ba"
        BfhlResponse res = process("1", "2", "3", "a", "b");
        assertEquals("Ba", res.getConcatString());
    }

    // ==================== Example B ====================
    // Input: ["10", "x", "20", "Y"]
    @Test
    void testExampleB_oddNumbers() {
        BfhlResponse res = process("10", "x", "20", "Y");
        assertTrue(res.getOddNumbers().isEmpty());
    }

    @Test
    void testExampleB_evenNumbers() {
        BfhlResponse res = process("10", "x", "20", "Y");
        assertEquals(List.of("10", "20"), res.getEvenNumbers());
    }

    @Test
    void testExampleB_alphabets() {
        BfhlResponse res = process("10", "x", "20", "Y");
        assertEquals(List.of("X", "Y"), res.getAlphabets());
    }

    @Test
    void testExampleB_sum() {
        BfhlResponse res = process("10", "x", "20", "Y");
        assertEquals("30", res.getSum());
    }

    @Test
    void testExampleB_concatString() {
        // chars: x, Y -> reversed: "Yx" -> alternating caps: Y, x -> "Yx"
        BfhlResponse res = process("10", "x", "20", "Y");
        assertEquals("Yx", res.getConcatString());
    }

    // ==================== Example C ====================
    // Input: ["aA", "bC", "dD", "oE"]
    // All alpha chars: a, A, b, C, d, D, o, E
    // Reversed: E, o, D, d, C, b, A, a
    // Alternating caps (0=upper): E, o, D, d, C, b, A, a
    // concat_string = "EoDdCbAa"
    @Test
    void testExampleC_alphabets() {
        BfhlResponse res = process("aA", "bC", "dD", "oE");
        assertEquals(List.of("AA", "BC", "DD", "OE"), res.getAlphabets());
    }

    @Test
    void testExampleC_concatString() {
        BfhlResponse res = process("aA", "bC", "dD", "oE");
        // Collected chars: aAbCdDoE
        // Reversed:        EoDdCbAa
        // Alt caps (0=U):  EoDdCbAa
        assertEquals("EoDdCbAa", res.getConcatString());
    }

    @Test
    void testExampleC_sum() {
        BfhlResponse res = process("aA", "bC", "dD", "oE");
        assertEquals("0", res.getSum());
    }

    // ==================== Edge Cases ====================
    @Test
    void testEmptyArray() {
        BfhlResponse res = process();
        assertTrue(res.getIsSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertTrue(res.getAlphabets().isEmpty());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("", res.getConcatString());
    }

    @Test
    void testAllNumbers() {
        BfhlResponse res = process("1", "2", "3", "4", "5");
        assertEquals(List.of("1", "3", "5"), res.getOddNumbers());
        assertEquals(List.of("2", "4"), res.getEvenNumbers());
        assertTrue(res.getAlphabets().isEmpty());
        assertEquals("15", res.getSum());
        assertEquals("", res.getConcatString());
    }

    @Test
    void testAllAlphabets() {
        BfhlResponse res = process("hello", "World");
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals(List.of("HELLO", "WORLD"), res.getAlphabets());
        assertEquals("0", res.getSum());
    }

    @Test
    void testSpecialCharacters() {
        BfhlResponse res = process("a1", "@#", "1b");
        // "a1" has both alpha and digit -> special
        // "@#" has neither -> special
        // "1b" has both -> special
        assertEquals(List.of("a1", "@#", "1b"), res.getSpecialCharacters());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertTrue(res.getAlphabets().isEmpty());
    }

    @Test
    void testIsSuccess() {
        BfhlResponse res = process("1");
        assertTrue(res.getIsSuccess());
    }

    // ==================== Helper ====================
    private BfhlResponse process(String... elements) {
        BfhlRequest req = new BfhlRequest();
        req.setData(elements.length == 0 ? Collections.emptyList() : Arrays.asList(elements));
        return service.processData(req);
    }
}
