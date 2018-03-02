package com.example.weibinwang.myapplication;

import com.example.weibinwang.myapplication.Common.Validator.EmailValidator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Before
    public void setUp() throws Exception {
        System.out.print("start");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        System.out.print("djskaj");
        assertEquals(4, 2 + 2);
    }
    @Test
    public void emailValidator() throws Exception {
        String email = "dhsjahdjksha@test.com";
        assertTrue(EmailValidator.validateEmail(email));
    }
}