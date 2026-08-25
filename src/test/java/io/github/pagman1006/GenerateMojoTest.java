package io.github.pagman1006;

import org.apache.maven.api.plugin.testing.InjectMojo;
import org.apache.maven.api.plugin.testing.MojoTest;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedConstruction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link GenerateMojo}.
 */
@MojoTest
class GenerateMojoTest {

    /**
     * Mocked Maven project instance.
     */
    private MavenProject project;

    /**
     * Sets up test fixtures before each test execution.
     */
    @BeforeEach
    void setUp() {
        project = mock(MavenProject.class);
    }

    /**
     * Tests successful execution of {@link GenerateMojo#execute()} when all files are generated properly.
     *
     * @param mojo the injected GenerateMojo instance
     * @param tempDir temporary directory for test execution
     * @throws IllegalAccessException if reflection field injection fails
     */
    @Test
    @InjectMojo(goal = "generate", pom = "src/test/resources/test-pom.xml")
    void executeTest(GenerateMojo mojo, @TempDir Path tempDir) throws IllegalAccessException {
        final File temporalDir = tempDir.toFile();

        when(project.getBasedir()).thenReturn(temporalDir);

        ReflectionUtils.setVariableValueInObject(mojo, "project", project);

        assertDoesNotThrow(mojo::execute);

        assertNotNull(project);
        assertNotNull(mojo);
        temporalDir.deleteOnExit();
    }

    /**
     * Tests that {@link GenerateMojo#execute()} throws an exception when Constants file creation fails.
     *
     * @param mojo the injected GenerateMojo instance
     * @param tempDir temporary directory for test execution
     * @throws IllegalAccessException if reflection field injection fails
     */
    @Test
    @InjectMojo(goal = "generate", pom = "src/test/resources/test-pom.xml")
    void executeConstantsFailedTest(GenerateMojo mojo, @TempDir Path tempDir) throws IllegalAccessException {
        final File temporalDir = tempDir.toFile();
        when(project.getBasedir()).thenReturn(temporalDir);
        ReflectionUtils.setVariableValueInObject(mojo, "project", project);
        try (MockedConstruction<FileWriter> mockedConstruction = mockConstruction(FileWriter.class, (mock, context) -> {
            if (context.getCount() == 1) {
                doThrow(new IOException("Error: disk not empty")).when(mock).write(anyString());
            }
        })) {
            assertNotNull(mojo);
            assertThrows(Exception.class, mojo::execute);
        }
        temporalDir.deleteOnExit();
    }

    /**
     * Tests that {@link GenerateMojo#execute()} throws an exception when Utils file creation fails.
     *
     * @param mojo the injected GenerateMojo instance
     * @param tempDir temporary directory for test execution
     * @throws IllegalAccessException if reflection field injection fails
     */
    @Test
    @InjectMojo(goal = "generate", pom = "src/test/resources/test-pom.xml")
    void executeUtilsFailedTest(GenerateMojo mojo, @TempDir Path tempDir) throws IllegalAccessException {
        final File temporalDir = tempDir.toFile();
        when(project.getBasedir()).thenReturn(temporalDir);
        ReflectionUtils.setVariableValueInObject(mojo, "project", project);
        try (MockedConstruction<FileWriter> mockedConstruction = mockConstruction(FileWriter.class, (mock, context) -> {
            if (context.getCount() == 2) {
                doThrow(new IOException("Error: disk not empty")).when(mock).write(anyString());
            }
        })) {
            assertNotNull(mojo);
            assertThrows(Exception.class, mojo::execute);
        }
        temporalDir.deleteOnExit();
    }

    /**
     * Tests that {@link GenerateMojo#execute()} throws an exception when Factory file creation fails.
     *
     * @param mojo the injected GenerateMojo instance
     * @param tempDir temporary directory for test execution
     * @throws IllegalAccessException if reflection field injection fails
     */
    @Test
    @InjectMojo(goal = "generate", pom = "src/test/resources/test-pom.xml")
    void executeFactoryFailedTest(GenerateMojo mojo, @TempDir Path tempDir) throws IllegalAccessException {
        final File temporalDir = tempDir.toFile();
        when(project.getBasedir()).thenReturn(temporalDir);
        ReflectionUtils.setVariableValueInObject(mojo, "project", project);
        try (MockedConstruction<FileWriter> mockedConstruction = mockConstruction(FileWriter.class, (mock, context) -> {
            if (context.getCount() == 3) {
                doThrow(new IOException("Error: disk not empty")).when(mock).write(anyString());
            }
        })) {
            assertNotNull(mojo);
            assertThrows(Exception.class, mojo::execute);
        }
        temporalDir.deleteOnExit();
    }
}