package com.happy.Reptile_Store;

import com.google.firebase.auth.FirebaseAuth;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DashboardTest {
    final Dashboard toTest = new Dashboard();
    final FirebaseAuth mAuthMock = mock(FirebaseAuth.class);

    @BeforeClass
    public void setup() {
        initMocks();
    }

    @Test
    public void testOne() {

    }
}
