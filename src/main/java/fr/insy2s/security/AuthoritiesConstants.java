package fr.insy2s.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ACCOUNTANT = "ROLE_ACCOUNTANT";

    public static final String SOCIETY = "ROLE_SOCIETY";

    private AuthoritiesConstants() {
    }
}
