package edu.illinois.library.cantaloupe.auth;

import edu.illinois.library.cantaloupe.image.ScaleConstraint;
import edu.illinois.library.cantaloupe.test.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthInfoTest extends BaseTest {

    // N.B.: inner classes are tested in separate test classes

    @Test
    public void testIsAuthorized() {
        AuthInfo info = new AuthInfo.RestrictiveBuilder()
                .withResponseStatus(200)
                .build();
        assertTrue(info.isAuthorized());

        info = new AuthInfo.RestrictiveBuilder()
                .withResponseStatus(300)
                .withRedirectURI("http://example.org/")
                .build();
        assertFalse(info.isAuthorized());
    }

    @Test
    public void testGetScaleConstraint() {
        AuthInfo info = new AuthInfo.RestrictiveBuilder()
                .withResponseStatus(302)
                .withRedirectScaleConstraint(2L, 3L)
                .build();
        assertEquals(new ScaleConstraint(2, 3), info.getScaleConstraint());
    }

}
