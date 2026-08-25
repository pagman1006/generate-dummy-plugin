package io.github.pagman1006.constants;

/**
 * Log messages and message prefixes used across the generate-dummy-plugin.
 */
public class ConstantsLog {

    /**
     * Log message prefix when generating test classes in a target directory.
     */
    public static final String LOG_GENERATING_CLASSES = "Generating test classes in: ";

    /**
     * Log/exception message prefix when failing to create the test directory.
     */
    public static final String LOG_FAILED_CREATE_TEST_DIRECTORY = "Failed to create test directory: ";

    /**
     * Log/exception message when failing to create Constants.java.
     */
    public static final String LOG_FAILED_CREATE_CONSTANTS = "Failed to create Constants.java";

    /**
     * Log/exception message when failing to create Utils.java.
     */
    public static final String LOG_FAILED_CREATE_UTILS = "Failed to create Utils.java";

    /**
     * Log/exception message when failing to create Factory.java.
     */
    public static final String LOG_FAILED_CREATE_FACTORY = "Failed to create Factory.java";

    /**
     * Log message prefix when Constants.java is created successfully.
     */
    public static final String LOG_CREATED_CONSTANTS = "Constants.java created successfully in ";

    /**
     * Log message prefix when Utils.java is created successfully.
     */
    public static final String LOG_CREATED_UTILS = "Utils.java created successfully in ";

    /**
     * Log message prefix when Factory.java is created successfully.
     */
    public static final String LOG_CREATED_FACTORY = "Factory.java created successfully in ";

    /**
     * Log message prefix when failing to create a directory.
     */
    public static final String LOG_FAILED_CREATE_DIRECTORY = "Failed to create directory: ";

    /**
     * Log message prefix when an error occurs while writing to a file.
     */
    public static final String LOG_ERROR_WRITING_FILE = "Error writing to file: ";

    /**
     * Log message prefix when an error occurs while reading a resource file.
     */
    public static final String LOG_ERROR_READER_FILE = "Error reading resource file: ";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ConstantsLog() {}
}
