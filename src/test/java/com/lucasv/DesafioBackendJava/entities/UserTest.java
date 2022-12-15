package com.lucasv.DesafioBackendJava.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Method under test: {@link User#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new User()).canEqual("Other"));
    }

    /**
     * Method under test: {@link User#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        User user = new User();

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertTrue(user.canEqual(user1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link User}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setId(Long)}
     *   <li>{@link User#setPassword(String)}
     *   <li>{@link User#setUserRoles(List)}
     *   <li>{@link User#setUsername(String)}
     *   <li>{@link User#toString()}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getId()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUserRoles()}
     *   <li>{@link User#getUsername()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setId(123L);
        actualUser.setPassword("iloveyou");
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        actualUser.setUserRoles(userRoleList);
        actualUser.setUsername("janedoe");
        String actualToStringResult = actualUser.toString();
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals(123L, actualUser.getId().longValue());
        assertEquals("iloveyou", actualUser.getPassword());
        assertSame(userRoleList, actualUser.getUserRoles());
        assertEquals("janedoe", actualUser.getUsername());
        assertEquals("User(id=123, username=janedoe, email=jane.doe@example.org, password=iloveyou, userRoles=[])",
                actualToStringResult);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        assertNotEquals(user, null);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        assertNotEquals(user, "Different type to User");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        assertEquals(user, user);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals5() {
        User user = new User();
        user.setEmail("janedoe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals6() {
        User user = new User();
        user.setEmail(null);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals7() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals8() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(null);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals9() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("janedoe");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals10() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword(null);
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals11() {
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(UserRole.ROLE_ADMIN);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(userRoleList);
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals12() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("jane.doe@example.org");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals13() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername(null);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertNotEquals(user, user1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals14() {
        User user = new User();
        user.setEmail(null);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail(null);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals15() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(null);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(null);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals16() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword(null);
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword(null);
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals17() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername(null);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername(null);
        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }
}

