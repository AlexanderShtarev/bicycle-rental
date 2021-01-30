package com.epam.jwd.validator;

import com.epam.jwd.context.annotation.Length;
import com.epam.jwd.validator.rule.LengthRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckLengthTest {
    private LengthRule lengthRule;
    private Length lengthAnnotation;

    @BeforeEach
    public void init() throws NoSuchFieldException {
        lengthRule = new LengthRule();
        lengthAnnotation = TestClass.class.getDeclaredField("firstString").getAnnotationsByType(Length.class)[0];
    }

    @Test
    public void emptyString() {
        callCheck("");
    }

    @Test
    public void nullString() {
        callCheck(null);
    }

    @Test
    public void shortString() {
        callCheck("1234");
    }

    @Test
    public void longString() {
        callCheck("12345678912345678");
    }

    private void callCheck(String s) {
        assertThrows(IllegalStateException.class,
                () -> lengthRule.check(lengthAnnotation, "firstString", s));
    }

    @Test
    public void normalString() {
        lengthRule.check(lengthAnnotation, "firstString", "12345");
    }

    private class TestClass {
        @Length(min = 5, max = 10)
        private String firstString;

        public TestClass(String firstString) {
            this.firstString = firstString;
        }

    }
}
