# Breadth-First Search (BFS) Algorithm
## DAA Assignment 2 — Java Implementation

---

## Overview

This project implements the **Breadth-First Search (BFS)** graph traversal algorithm in Java. BFS explores all vertices of a graph level by level, starting from a given source vertex, using a **FIFO queue**.

BFS is one of the fundamental graph traversal algorithms used in computer science for shortest path finding in unweighted graphs, connected component detection, and level-order traversal.

---

## Algorithm Definition

Breadth-First Search (BFS) traverses a graph by:
1. Starting at a chosen source vertex.
2. Visiting all neighbours at the current depth before moving to vertices at the next depth level.
3. Using a **queue** (First In, First Out) to maintain the order of exploration.
4. Marking vertices as **visited when enqueued** to prevent revisiting.

### Pseudocode

```
BFS(Graph, start):
    visited = {start}
    queue = [start]

    while queue is not empty:
        vertex = remove_front(queue)
        output(vertex)

        for each neighbour of vertex:
            if neighbour is not visited:
                mark neighbour as visited
                add neighbour to queue
```

### Complexity

| Metric | Value |
|---|---|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |
| Data Structure | Queue (FIFO) |
| Graph Representation | Adjacency List |

Where V = number of vertices, E = number of edges.

---

## Project Structure

```
daa/
├── BFS.java                  # Main BFS implementation (Java)
├── README.md                 # This file
├── algorithm_explanation.md  # Detailed algorithm explanation
├── test_cases.md             # Test cases and expected outputs
├── video_script.md           # 2-minute presentation script
└── visualization/            # Interactive BFS visualizer
    ├── index.html            # Main visualizer page
    ├── learn.html            # Learning mode & complexity page
    ├── style.css             # Ember theme stylesheet
    └── script.js             # JavaScript visualizer logic
```

---

## Setup & Execution

### Prerequisites
- Java 11 or later (JDK)
- No external libraries required (uses only Java standard library)

### Compiling and Running the Java Implementation

```bash
# Compile
javac BFS.java

# Run
java BFS
```

The program provides three modes:
1. **Demo Mode** — Runs three pre-built examples (default graph, cyclic graph, disconnected graph).
2. **Interactive Mode** — Lets you enter a custom graph and starting vertex.
3. **Both** — Runs demo examples followed by interactive mode.

### Opening the Visualization

Open `visualization/index.html` directly in any modern web browser:

```bash
# On Windows
start visualization\index.html

# Or simply double-click the file in File Explorer
```

No web server or installation is required.

---

## Examples

### Example 1: Tree Graph (6 vertices)
```
Vertices: 0, 1, 2, 3, 4, 5
Edges: (0,1), (0,2), (1,3), (1,4), (2,5)
Start: 0
BFS Traversal: 0 1 2 3 4 5
```

### Example 2: Cyclic Graph (5 vertices)
```
Vertices: 0, 1, 2, 3, 4
Edges: (0,1), (1,2), (2,3), (3,4), (4,0), (1,3)
Start: 0
BFS Traversal: 0 1 4 2 3
```

### Example 3: Disconnected Graph (7 vertices)
```
Vertices: 0, 1, 2, 3, 4, 5, 6
Edges: (0,1), (0,2), (1,2), (3,4), (5,6)
Start: 0
BFS Traversal: 0 1 2
Unreachable: [3, 4, 5, 6]
```

---

## Interactive Visualization

The project includes a two-page interactive visualizer built with HTML, CSS, and JavaScript:

- **Visualizer Page** (`index.html`) — Graph canvas, BFS controls, queue animation, and traversal output.
- **Learning Page** (`learn.html`) — Step-by-step explanations, algorithm pseudocode with live highlighting, and complexity analysis.

Both pages share the same warm **Ember** dark theme with orange/rose/amber accents.

---

## Key Java Classes & Methods

| Method | Description |
|---|---|
| `bfs(graph, start, numVertices)` | Performs BFS traversal, returns vertex visit order |
| `buildGraph(numVertices, edges)` | Builds adjacency list from edge array |
| `printGraph(graph, numVertices)` | Prints adjacency list to console |
| `runExample(...)` | Runs and displays a single demo example |
| `runDemoExamples()` | Runs all three required demos |
| `interactiveMode(scanner)` | Accepts user input for custom graph & BFS |

---

## License

Academic project — DAA Assignment 2.
