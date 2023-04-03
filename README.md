# Development

- Build the Java artifact
  ```
  mvn package
  ```
- Start a Docker Image with remote debugging:
  ```
  docker run --rm -it -e SONAR_WEB_JAVAADDITIONALOPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -v "/path/to/project/target/sonarqube-plugin-<version>-SNAPSHOT.jar:/opt/sonarqube/extensions/plugins/green-coding.jar" -v sq-data:/opt/sonarqube/data -p 9000:9000 -p 5005:5005 --name sq sonarqube:lts  
  ```
- Attach to remote debugging port with the IDE of your choice
- Run a Sonarqube analysis on a Maven/Java project
  ```
  mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token created in the settings>
  ```
