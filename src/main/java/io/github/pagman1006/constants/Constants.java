package io.github.pagman1006.constants;

/**
 * Common constants used across the generate-dummy-plugin for generating test utility classes.
 */
public class Constants {

    /**
     * Name of the Mojo execution goal.
     */
    public static final String MOJO_NAME = "generate";

    /**
     * Configuration property name for the base package.
     */
    public static final String PACKAGE_PROPERTY = "packageBase";

    /**
     * Default base package name if none is configured.
     */
    public static final String DEFAULT_PACKAGE = "io.github.pagman1006";

    /**
     * Relative path to the test Java source directory.
     */
    public static final String PATH_TEST_JAVA = "src/test/java/";

    /**
     * Package suffix for generated utility classes.
     */
    public static final String PACKAGE_UTIL = ".util";

    /**
     * Directory path for generated utility classes.
     */
    public static final String DIR_UTIL = "util/";

    /**
     * File name for the generated Constants Java class.
     */
    public static final String CONSTANTS_CLASS = "Constants.java";

    /**
     * File name for the generated Utils Java class.
     */
    public static final String UTILS_CLASS = "Utils.java";

    /**
     * File name for the generated Factory Java class.
     */
    public static final String FACTORY_CLASS = "Factory.java";

    /**
     * Java package declaration keyword prefix.
     */
    public static final String PACKAGE = "package ";

    /**
     * Java static import statement prefix.
     */
    public static final String IMPORT_STATIC = "import static ";

    /**
     * Import pattern suffix for the generated Constants class.
     */
    public static final String IMPORT_CONSTANTS = ".util.Constants.*";

    /**
     * Import pattern suffix for the generated Utils class.
     */
    public static final String IMPORT_UTILS = ".util.Utils.*";

    /**
     * Resource template file name for the Constants class content.
     */
    public static final String CONSTANTS_FILE = "constants.txt";

    /**
     * Resource template file name for the Utils class content.
     */
    public static final String UTILS_FILE = "utils.txt";

    /**
     * Resource template file name for the Factory class content.
     */
    public static final String FACTORY_FILE = "factory.txt";

    /**
     * Newline line delimiter string.
     */
    public static final String DELIMITER = "\n";

    /**
     * Semicolon statement terminator string.
     */
    public static final String SEMICOLON = ";";

    /**
     * Package separator dot character.
     */
    public static final char DOT = '.';

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Constants() {}
}
