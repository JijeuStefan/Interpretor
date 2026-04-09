# Toy Language Interpreter (Java)

## 📋 Description

This project is a **toy programming language interpreter** implemented in Java, designed to simulate how programming languages execute programs.

The interpreter follows the **Model–View–Controller (MVC)** architecture and supports execution of custom programs written in a simplified language with features such as variables, expressions, statements, heap memory, file operations, and concurrency.

It provides a step-by-step execution model, allowing visualization of how program state evolves during runtime.

## 🎬 Demo

### 🔹 While Statement Demo
![While Statement Demo](./assets/WhileDemo.gif)

### 🔹 Heap Reading and Writing Demo
![Heap Reading and Writing Demo](./assets/HeapAllocDemo.gif)

## ✨ Features

- **Custom Language Execution** – Executes programs composed of statements and expressions  
- **Strongly Typed System** – Supports:
  - `int`, `bool`, `string`, and `ref` types  
- **Expression Evaluation**:
  - Arithmetic expressions  
  - Logical expressions  
  - Relational expressions  
  - Heap expressions  
  - Variable and constant expressions  
- **Statement Support**:
  - Variable declaration and assignment  
  - Conditional statements (`if`)  
  - Loops (`while`)  
  - Print statements  
  - File operations (`open`, `read`, `close`)  
  - Heap operations (`new`, `rH`, `wH`)  
  - Concurrency (`fork`)  
- **Program State Management**:
  - Execution stack  
  - Symbol table  
  - Output list  
  - File table  
  - Heap memory  
- **Logging System** – Tracks program execution step-by-step  

## 🛠️ Tech Stack

- **Language:** Java  
- **Architecture:** MVC (Model–View–Controller)  
- **Core Concepts:**  
  - Data Structures (Stack, HashMap, List)  
  - Concurrency (thread-like execution with `fork`)  
  - File I/O  
  - Custom Type System  
  - Exception Handling  

## 🧩 Core Architecture

### 📦 Model

The **Model** contains the core logic of the interpreter:

#### ADT (Abstract Data Types)
Custom implementations for:
- **Execution Stack** – controls program execution  
- **Symbol Table** – stores variable values  
- **File Table** – manages opened files  
- **Heap** – dynamic memory allocation  
- **Output List** – stores printed values  

#### Expressions
- Arithmetic (`+`, `-`, `*`, `/`)  
- Logical (`and`, `or`)  
- Relational (`<`, `>`, `==`, etc.)  
- Value expressions  
- Variable expressions  
- Heap expressions (`rH`)  

#### Statements
- Assignment  
- Print  
- Conditional (`if`)  
- Loop (`while`)  
- No-operation (`nop`)  
- Heap operations (`new`, `wH`)  
- File operations (`openRFile`, `readFile`, `closeRFile`)  
- Concurrency (`fork`)  

#### Types
- `IntType`  
- `BoolType`  
- `StringType`  
- `RefType`  

#### Values
- `IntValue`  
- `BoolValue`  
- `StringValue`  
- `RefValue`  

---

### 📦 Repository

- Stores a **list of program states (`PrgState`)**
- Each `PrgState` contains:
  - Execution stack  
  - Symbol table  
  - Output  
  - File table  
  - Heap  

---

### 📦 Controller

- Manages program execution  
- Executes statements step-by-step  
- Handles concurrency (`fork`)  
- Logs execution state  

---

### 📦 View

- Displays program execution  
- Shows step-by-step changes in:
  - Stack  
  - Symbol table  
  - Heap  
  - Output  

## 🚀 Quick Start

### Prerequisites

Make sure you have the following installed:

- **Java JDK 8 or higher**
- **Java IDE** (recommended: IntelliJ IDEA / Eclipse / VS Code)

## Installation

```bash
### Clone the repository
git clone https://github.com/JijeuStefan/Interpreter.git
cd Interpreter

# Open the project in your preferred IDE (e.g., IntelliJ IDEA)
# Make sure the project SDK is set to Java 8 or higher
```

## Usage

```bash
### Run the application
- Locate the `Main.java` file
- Run the program from your IDE
```

## 📁 Project Structure

```text
Interpreter-v2/
├── Model/
│   ├── adt/              # Stack, Heap, Symbol Table, File Table, Output
│   ├── exp/              # Expressions 
│   ├── stmt/             # Statements 
│   ├── type/             # Types 
│   ├── val/              # Values 
|   ├── Exceptions        # User-defined exceptions
├── Repository/           # Program state storage
├── Controller/           # Execution logic
└── View/                 # UI / execution display
    └── Main.java         # Entry point           
```

## 📚 Documentation

### Java
- [Java Documentation](https://docs.oracle.com/en/java/)

### Core Concepts
- [Stacks (Data Structure)](https://en.wikipedia.org/wiki/Stack_(abstract_data_type))
- [Hash Tables](https://en.wikipedia.org/wiki/Hash_table)
- [Heap (Memory Management)](https://en.wikipedia.org/wiki/Memory_management)
- [Interpreter Pattern](https://en.wikipedia.org/wiki/Interpreter_pattern)

### Concurrency
- [Java Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Threads in Java](https://docs.oracle.com/javase/tutorial/essential/concurrency/threads.html)

### File Handling
- [Java File I/O](https://docs.oracle.com/javase/tutorial/essential/io/)

## 📝 Usage

This project was developed for educational purposes as part of studying programming language concepts and interpreter design.

It demonstrates how a program is executed step-by-step using a custom interpreter, including:
- Expression evaluation  
- Statement execution  
- Memory management (stack vs heap)  
- File operations  
- Concurrency through a `fork` mechanism  

The interpreter allows running predefined example programs and observing how the program state evolves during execution.

## 👤 Author

**JijeuStefan**
- GitHub: [@JijeuStefan](https://github.com/JijeuStefan)
