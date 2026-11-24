# Mini Kafka

A small Kafka clone built from scratch with Java and Spring Boot, for learning.

- What Kafka is: [01-WHAT-IS-KAFKA.md](01-WHAT-IS-KAFKA.md)
- Sprint plan: [02-SPRINTS-PLAN.md](02-SPRINTS-PLAN.md)

## Requirements

- **JDK 25** (set `JAVA_HOME` to it)
- Maven is not needed; the project includes the Maven wrapper (`mvnw`)

## Modules

| Module | What goes here |
|---|---|
| `common` | Shared code: record model, protocol requests/responses, serialization, error codes |
| `broker` | The server (Spring Boot app): topics, partitions, log storage, groups, replication |
| `client` | Java library: Producer, Consumer, Admin |
| `cli` | Command-line tools: console producer/consumer, topics admin |

## Build and run

```bash
# build everything and run the tests
./mvnw clean verify          # Windows: mvnw.cmd clean verify

# run the broker
./mvnw -pl broker -am spring-boot:run
```

Health check: http://localhost:8080/actuator/health
