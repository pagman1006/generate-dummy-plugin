package io.github.pagman1006.util;

import io.github.pagman1006.constants.Constants;
import io.github.pagman1006.constants.ConstantsLog;
import org.apache.maven.plugin.logging.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static io.github.pagman1006.constants.Constants.*;
import static io.github.pagman1006.constants.ConstantsLog.*;

/**
 * Utility class providing helper methods to read template resources and generate
 * test utility Java source files (Constants, Utils, Factory).
 */
public class Utils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Utils() {}

    /**
     * Creates the Constants.java class in the target test directory.
     *
     * @param log the Maven plugin logger
     * @param testJavaDir the target test directory
     * @param packageBase the base package name/path
     * @return true if the file was created successfully, false otherwise
     */
    public static boolean createConstantsClass(final Log log, final File testJavaDir, final String packageBase) {
        return createFile(log, testJavaDir, packageBase, CONSTANTS_CLASS, CONSTANTS_FILE, LOG_CREATED_CONSTANTS);
    }

    /**
     * Helper method to create a Java source file from a resource template in the util directory.
     *
     * @param log the Maven plugin logger
     * @param testJavaDir the target test directory
     * @param packageBase the base package name/path
     * @param constantsClass the target Java class file name
     * @param constantsFile the resource template file name
     * @param logCreatedConstants the success log message prefix
     * @return true if the file was created successfully, false otherwise
     */
    private static boolean createFile(Log log, File testJavaDir, String packageBase, String constantsClass,
            String constantsFile, String logCreatedConstants) {
        final String packagePath = packageBase.concat(PACKAGE_UTIL).replace(File.separatorChar, DOT);
        final File baseDir = new File(testJavaDir, DIR_UTIL);
        final File testFile = new File(baseDir, constantsClass);

        if (!baseDir.exists() && !baseDir.mkdirs()) {
            log.error(LOG_FAILED_CREATE_DIRECTORY.concat(baseDir.getAbsolutePath()));
        }

        final StringBuilder content = new StringBuilder();
        content.append(PACKAGE).append(packagePath).append(SEMICOLON).append(DELIMITER).append(DELIMITER)
                .append(readFile(constantsFile));

        return writeFile(testFile, content, log, logCreatedConstants, baseDir);
    }

    /**
     * Reads the content of a resource file from the classpath.
     *
     * @param resource the resource file path in the classpath
     * @return the string content of the resource file, or empty string if not found or on error
     */
    private static String readFile(final String resource) {
        final StringBuilder content = new StringBuilder();
        try (InputStream in = Utils.class.getClassLoader().getResourceAsStream(resource)) {
            if (in != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                    content.append(reader.lines().collect(Collectors.joining(DELIMITER)));
                }
            }
        } catch (Exception e) {
            System.err.println(LOG_ERROR_READER_FILE.concat(e.getMessage()));
        }
        return content.toString();
    }

    /**
     * Writes content to the specified target file and logs the outcome.
     *
     * @param testFile the destination file to write
     * @param content the content to write
     * @param log the Maven plugin logger
     * @param logCreatedFile the success log message prefix
     * @param baseDir the parent directory for error logging purposes
     * @return true if writing succeeded, false on error
     */
    private static boolean writeFile(File testFile, StringBuilder content, Log log, String logCreatedFile,
            File baseDir) {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(content.toString());
            log.info(logCreatedFile.concat(testFile.getAbsolutePath()));
        } catch (Exception e) {
            log.error(
                    LOG_ERROR_WRITING_FILE.concat(baseDir.getAbsolutePath().concat(DELIMITER).concat(e.getMessage())));
            return false;
        }
        return true;
    }

    /**
     * Creates the Utils.java class in the target test directory.
     *
     * @param log the Maven plugin logger
     * @param testJavaDir the target test directory
     * @param packageBase the base package name/path
     * @return true if the file was created successfully, false otherwise
     */
    public static boolean createUtilsClass(final Log log, final File testJavaDir, final String packageBase) {
        return createFile(log, testJavaDir, packageBase, Constants.UTILS_CLASS, Constants.UTILS_FILE,
                ConstantsLog.LOG_CREATED_UTILS);
    }

    /**
     * Creates the Factory.java class in the specified base directory.
     *
     * @param log the Maven plugin logger
     * @param baseDir the target directory where Factory.java will be created
     * @param packagePath the package path for imports and package declaration
     * @return true if the file was created successfully, false otherwise
     */
    public static boolean createFactoryClass(final Log log, final File baseDir, final String packagePath) {
        final String packageName = packagePath.replace(File.separatorChar, DOT);
        final File testFile = new File(baseDir, FACTORY_CLASS);

        if (!baseDir.exists() && !baseDir.mkdirs()) {
            log.error(LOG_FAILED_CREATE_DIRECTORY.concat(baseDir.getAbsolutePath()));
        }

        final StringBuilder content = new StringBuilder();
        content.append(PACKAGE).append(packageName).append(SEMICOLON).append(DELIMITER).append(DELIMITER)
                .append(IMPORT_STATIC).append(packageName).append(IMPORT_CONSTANTS).append(SEMICOLON).append(DELIMITER)
                .append(IMPORT_STATIC).append(packageName).append(IMPORT_UTILS).append(SEMICOLON).append(DELIMITER)
                .append(DELIMITER).append(readFile(FACTORY_FILE));

        return writeFile(testFile, content, log, LOG_CREATED_FACTORY, baseDir);
    }
}
