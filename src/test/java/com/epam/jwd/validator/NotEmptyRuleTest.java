package com.epam.jwd.validator;

import com.epam.jwd.context.annotation.NotEmpty;
import com.epam.jwd.validator.rule.NotEmptyRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotEmptyRuleTest {
    private NotEmptyRule notEmptyRule;
    private NotEmpty checkNotEmpty;

    @BeforeEach
    public void init() throws NoSuchFieldException {
        notEmptyRule = new NotEmptyRule();
        checkNotEmpty = TestClass.class.getDeclaredField("firstString").getAnnotationsByType(NotEmpty.class)[0];
    }

    @Test
    public void emptyString() {
        checkObjectWithException("");
    }

    @Test
    public void nullObject() {
        checkObjectWithException(null);
    }

    @Test
    public void notEmptyString() {
        checkGoodObject("1");
    }

    @Test
    public void notNullObject() {
        checkGoodObject(new BigDecimal(1));
    }

    private class TestClass {
        @NotEmpty
        private final String firstString;

        private TestClass(String firstString) {
            this.firstString = firstString;
        }
    }

    private void checkObjectWithException(String s) {
        assertThrows(NullPointerException.class,
                () -> notEmptyRule.check(checkNotEmpty, "firstString", s));
    }

    private void checkGoodObject(Object object) {
        notEmptyRule.check(checkNotEmpty, "firstString", object);
    }
}