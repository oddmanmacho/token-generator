package com.akirolabs.hometask.constants;

/**
 * The class {@code UrlConstants} will have all the constants used in the hometask application for URL.
 *
 * <p>
 * The constants will be used to avoid the implementation of hard coded strings
 * </p>
 *
 * @author jithin
 */
public class UrlConstants {

    /**
     * Endpoint to get API information.
     */
    public static final String INFO = "/info";

    /**
     * Endpoint to generate token.
     */
    public static final String GENERATE_TOKEN = "/generate-token";

    /**
     * Endpoint to validate token.
     */
    public static final String VALIDATE_TOKEN = "/validate-token";

    /**
     * Private constructor for Constants class.
     */
    private UrlConstants() {}
}
