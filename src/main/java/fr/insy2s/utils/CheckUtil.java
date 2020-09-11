package fr.insy2s.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.security.SecurityUtils;
import fr.insy2s.service.dto.UserDTO;

public class CheckUtil {
    public static String getLoginCurrentUser() {
        return SecurityUtils.getCurrentUserLogin().get();
    }

    public static List<String> getAuthoritiesCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(a -> a.toString()).collect(Collectors.toList());
    }

    public static Boolean checkIdUserHaveRightTochangeAccount(UserDTO userToChange) {
        return (isAdmin() || userToChange.getLogin().equals(getLoginCurrentUser()));
    }

    public static Boolean isAdmin() {
        return verifyAuthorityCurrentUser(AuthoritiesConstants.ADMIN);
    }

    public static Boolean isSociety() {
        return verifyAuthorityCurrentUser(AuthoritiesConstants.SOCIETY);
    }

    public static Boolean isAcountant() {
        return verifyAuthorityCurrentUser(AuthoritiesConstants.ACCOUNTANT);
    }

    public static Boolean verifyIfCurrentUserHaveOneOfTheAuthorites(List<String> roles) {
        for (String role : roles) {
            if (verifyAuthorityCurrentUser(role))
                return true;
        }
        return false;
    }

    private static Boolean verifyAuthorityCurrentUser(String role) {
        return getAuthoritiesCurrentUser().contains(role);
    }

    public static boolean checkIfUserHaveRightToAccessUserAutresInformation(String loginToTest) {
        return (isAdmin());
    }

    public static boolean loginIsLoginOfCurrentUser(String loginToTest) {
        return (loginToTest.equals(getLoginCurrentUser()));
    }

    public static boolean isDateBetween(LocalDate startingDate, LocalDate firstDate, LocalDate secondDate) {

        boolean isEqualsOrAfterFirstDate    = startingDate.isAfter(firstDate)   || startingDate.equals(firstDate);
        boolean isEqualsOrBeforeSecondDate  = startingDate.isBefore(secondDate) || startingDate.equals(secondDate);

        return isEqualsOrAfterFirstDate && isEqualsOrBeforeSecondDate;

    }
}
