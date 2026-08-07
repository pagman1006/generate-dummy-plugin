# generate-dummy-plugin

A Maven plugin to automate and standardize the creation of the base structure required for generating test data (dummy data) in Java projects.

## Overview

This plugin generates essential test utility classes (`Constants`, `Utils`, `Factory`) in your `src/test/java` directory. This helps in standardizing dummy data generation for tests across your project.

## Requirements

- Java 17 or higher
- Maven 3.9.16 or higher

## Setup

Add the following to your project's `pom.xml`:

```xml
<plugin>
    <groupId>io.github.pagman1006</groupId>
    <artifactId>generate-dummy-plugin</artifactId>
    <version>1.0.0</version>
    <executions>
      <execution>
        <goals>
          <goal>generate</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <packageBase>com.your.package</packageBase>
    </configuration>
</plugin>
```

## Usage

Run the plugin using the following command:

```bash
mvn generate-dummy-plugin:generate
```

### Configuration

You can configure the base package for the generated classes using the `-DpackageBase` parameter:

```bash
mvn generate-dummy-plugin:generate -DpackageBase=com.your.package
```

The default base package is `io.github.pagman1006`.

## Project Structure

- `src/main/resources/*.txt`: Templates for generated classes.
- `src/main/java/io/github/pagman1006/GenerateMojo.java`: Main plugin entry point.

## Development

- **Build:** `mvn clean install`
- **Tests:** `mvn test`

## TODOs

- [ ] Add more configuration options for templates.
- [ ] Support different test frameworks.
- [ ] Improve error handling and logging.
- [ ] Add example usage documentation.
- [ ] Update `defaultPhase` if necessary.

## License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0). See the [LICENSE](LICENSE) file for details.
