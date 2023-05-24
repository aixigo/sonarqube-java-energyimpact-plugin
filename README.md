# Sonarqube Java Energy Impact Plugin

This repo provide a [SonarQube](https://sonarqube.org/) plugin with the purpose to guide Java developers to write more energy efficient Java applications with the aim to reduce the carbon footprint of Java software.

For example, the plugin makes results of [Improving energy-efficiency by recommending Java collections](https://doi.org/10.1007/s10664-021-09950-y) approachable so that the CI pipeline of Java developers recommends energy efficient code alternatives.

# Development

- Build the Java artifact
  ```
  mvn package
  ```
- Start a Docker Image with remote debugging:
  ```
  docker run --rm -it -e SONAR_WEB_JAVAADDITIONALOPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -v "/path/to/project/target/sonarqube-java-energyimpact-plugin-<version>-SNAPSHOT.jar:/opt/sonarqube/extensions/plugins/energy-impact.jar" -v sq-data:/opt/sonarqube/data -p 9000:9000 -p 5005:5005 --name sq sonarqube:lts  
  ```
- Attach to remote debugging port with the IDE of your choice
- Run a Sonarqube analysis on a Maven/Java project
  ```
  mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token created in the settings>
  ```
