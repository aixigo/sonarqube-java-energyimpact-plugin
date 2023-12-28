# Sonarqube Java Energy Impact Plugin

This repo provide a [SonarQube](https://sonarqube.org/) plugin with the purpose to guide Java developers to write more energy efficient Java applications with the aim to reduce the carbon footprint of Java software.

For example, the plugin makes results of [Improving energy-efficiency by recommending Java collections](https://doi.org/10.1007/s10664-021-09950-y) approachable so that the CI pipeline of Java developers recommends energy efficient code alternatives.

# Install

Download the plugin from [Jitpack.io][jitpack-home] or from the list below and put the JAR file into
[`<sonarqubeHome>/extensions/plugins`][extension-install]] to activate it.

- [sonarqube-java-energyimpact-plugin-v0.1.0.jar](https://jitpack.io/com/github/aixigo/sonarqube-java-energyimpact-plugin/v0.1.0/sonarqube-java-energyimpact-plugin-v0.1.0.jar)

# Development

## SonarQube Plugin

- Build the Java artifact
  ```
  mvn package
  ```
- Start a Docker Image with remote debugging:
  ```
  docker run --rm -it -e SONAR_WEB_JAVAADDITIONALOPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -v "/path/to/project/plugin/target/sonarqube-java-energyimpact-plugin-<version>-SNAPSHOT.jar:/opt/sonarqube/extensions/plugins/energy-impact.jar" -v sq-data:/opt/sonarqube/data -p 9000:9000 -p 5005:5005 --name sq sonarqube:lts
  ```
- Attach to remote debugging port with the IDE of your choice
- Run a Sonarqube analysis on a Maven/Java project
  ```
  mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token created in the settings>
  ```

[jitpack-home]: https://jitpack.io/#aixigo/sonarqube-java-energyimpact-plugin
[extension-install]: https://docs.sonarsource.com/sonarqube/latest/setup-and-upgrade/install-a-plugin/
