package io.github.pagman1006;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static io.github.pagman1006.constants.Constants.*;
import static io.github.pagman1006.constants.ConstantsLog.*;
import static io.github.pagman1006.util.Utils.createConstantsClass;
import static io.github.pagman1006.util.Utils.createFactoryClass;
import static io.github.pagman1006.util.Utils.createUtilsClass;

@Mojo(name = MOJO_NAME, defaultPhase = LifecyclePhase.COMPILE)
public class GenerateMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    MavenProject project;

    @Parameter(property = PACKAGE_PROPERTY, defaultValue = DEFAULT_PACKAGE)
    private String packageBase;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        final File baseDir = project.getBasedir();
        final String packagePath = packageBase.replace(DOT, File.separatorChar);
        final File testJavaDir = new File(baseDir, PATH_TEST_JAVA.concat(packagePath));

        getLog().info(LOG_GENERATING_CLASSES.concat(testJavaDir.getAbsolutePath()));
        if (!testJavaDir.exists()) {
            if (!testJavaDir.mkdir()) {
                throw new MojoExecutionException(
                        LOG_FAILED_CREATE_TEST_DIRECTORY.concat(testJavaDir.getAbsolutePath()));
            }
        }

        if (!createConstantsClass(getLog(), testJavaDir, packagePath)) {
            throw new MojoExecutionException(LOG_FAILED_CREATE_CONSTANTS);
        }

        if (!createUtilsClass(getLog(), testJavaDir, packagePath)) {
            throw new MojoExecutionException(LOG_FAILED_CREATE_UTILS);
        }

        if (!createFactoryClass(getLog(), testJavaDir, packagePath)) {
            throw new MojoExecutionException(LOG_FAILED_CREATE_FACTORY);
        }
    }
}
