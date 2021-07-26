package com.happy.Reptile_Store;

import com.google.firebase.auth.FirebaseAuth;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginTest {
    final Login toTest = new Login();
    final FirebaseAuth mAuthMock = mock(FirebaseAuth.class);

    @BeforeClass
    public void setup() {
        initMocks();
    }

    @Test
    public void loginUser_completesSuccessfully() {
        final String email = "test-email@gmail.com";
        final String password = "testpassword";

        when(mAuthMock).signInWithEmailAndPassword(email, password).thenReturn("testString");

        toTest.loginUser(email, password);

        verify(mAuthMock).signInWithEmailAndPassword(email, password);

        assertEquals();
    }
}