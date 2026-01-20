# Opencode Environment Setup

To reproduce the Opencode environment on another computer, follow these steps to install the required plugins and skills.

### 1. Install `oh-my-opencode` Plugin

This plugin provides advanced agent capabilities.

```bash
# Create plugins directory if it doesn't exist
mkdir -p ~/.opencode/plugins

# Clone the repository
git clone https://github.com/code-yeongyu/oh-my-opencode.git ~/.opencode/plugins/oh-my-opencode

# Install dependencies and build
cd ~/.opencode/plugins/oh-my-opencode
# If you have bun installed (recommended)
bun install && bun run build

# Or using npm
npm install && npm run build
```

### 2. Install `anthropics-skills`

These are the standard skills provided by Anthropic.

```bash
# Create skills directory if it doesn't exist
mkdir -p ~/.opencode/skills

# Clone the repository
git clone https://github.com/anthropics/skills.git ~/.opencode/skills/anthropics-skills
```

# State Machine Demo

This is a pure Java implementation of a configurable State Machine pattern.

## Structure

- **State.java**: Enum defining the possible states (PENDING, SEND_TO_MANAGER, etc.).
- **Event.java**: Enum defining events that trigger transitions.
- **WorkflowContext.java**: The domain object (e.g., a Document or Case) that holds the state.
- **Action.java**: Functional interface for side effects (like sending emails).
- **EmailAction.java**: Concrete implementation of an action.
- **StateMachine.java**: The core engine. It allows configuring transitions using a fluent Builder API.
- **Main.java**: Demonstration of how to wire everything together and run a workflow.

## How to Compile and Run

```bash
mkdir -p out
javac -d out src/main/java/my/study/state/machine/*.java
java -cp out my.study.state.machine.Main
```

## Features

- **Configurable Transitions**: Map (State + Event) -> Target State.
- **Side Effects**: Attach one or more actions (like emails) to any transition.
- **Type Safe**: Uses Enums for States and Events.

# Opencode Package

The `my.study.opencode` package demonstrates modern Java features (Record, JPA) and coding patterns (Lombok Builder).

## Key Concepts and How They Work

### 1. Java Records (Immutable Data Carriers)
Records are used throughout the application to model immutable data.
- **Concise Syntax**: Defined using the `record` keyword, automatically generating constructors, accessors, `equals()`, `hashCode()`, and `toString()`.
- **Validation**: Compact constructors (e.g., `public User { ... }`) are used to enforce invariants (like non-null fields or valid email formats) at the moment of creation.
- **Static Factories**: Helper methods like `Point.origin()` provide semantic ways to create instances.

### 2. Builder Pattern for Records
Since Records are immutable and have long constructors, the Builder pattern is often useful.
- **Manual Implementation**: The `RecordBuilderExample` shows how to manually implement a static inner `Builder` class. It accumulates parameters in mutable fields and then constructs the Record in the `build()` method, allowing for default values and pre-build validation.
- **Lombok Integration**: The `LombokRecordBuilderExample` demonstrates how to use the `@Builder` annotation on a Record. Lombok automatically generates the builder boilerplate, making the code significantly cleaner while retaining the benefits of the builder pattern.

### 3. JPA and Projections (Spring Data JPA)
The application uses Spring Data JPA for database interactions, specifically highlighting how to fetch data efficiently using Projections.
- **Entities**: Standard `@Entity` classes (like `Employee`) map to database tables.
- **DTO Projections**: Instead of fetching entire entities, the repositories use Java Records as DTOs (Data Transfer Objects) for projections.
    - **Interface-based Projections**: Using interfaces (like `EmployeeSummary`) to select specific columns.
    - **Class-based Projections (Records)**: Directly mapping query results to Record constructors (e.g., `new my.study.opencode.dto.EmployeeNameRecord(e.firstName, e.lastName)`).
- **Benefits**: This approach reduces memory usage and database load by only retrieving necessary fields, and the immutability of Records makes them perfect for these read-only views.
