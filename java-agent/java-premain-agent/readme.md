# Java Agent Demo

1. Build Agent Jar

```
cd agent
./gradlew shadowJar
```

2. Build App Jar
```
cd app
./gradlew jar
```

3. Run app with agent
```
# default class transformer, only print out some logs
java -javaagent:agent/build/libs/agent-all.jar=default -jar app/build/libs/app.jar

# replace add method
java -javaagent:agent/build/libs/agent-all.jar=replace -jar app/build/libs/app.jar

# pass no parameter to agent
java -javaagent:agent/build/libs/agent-all.jar -jar app/build/libs/app.jar
```
