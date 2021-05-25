# Java Agent Demo

1. Build Agent Jar

```
cd agent
./gradlew jar
```

2. Build App Jar
```
cd app
./gradlew jar
```

3. Run app with agent
```
# pass parameter to agent
java -javaagent:agent/build/libs/agent.jar=someargs -jar app/build/libs/app.jar

# pass no parameter to agent
java -javaagent:agent/build/libs/agent.jar -jar app/build/libs/app.jar
```
