import java.util.*;

/**
 * Breadth-First Search (BFS) Algorithm Implementation
 * DAA Assignment 2 — Java Implementation
 *
 * This program implements the BFS graph traversal algorithm using:
 * - Adjacency list representation (HashMap + ArrayList)
 * - LinkedList as a FIFO queue for efficient operations
 * - A HashSet for visited tracking to prevent revisiting vertices
 *
 * Author: [Student Name]
 * Date: October 2026
 */
public class BFS {

    /**
     * Perform Breadth-First Search traversal on a graph.
     *
     * @param graph       Adjacency list representation of the graph.
     * @param start       The starting vertex for BFS traversal.
     * @param numVertices Total number of vertices in the graph.
     * @return The BFS traversal order as a list of vertices.
     *
     * Algorithm:
     *   1. Mark the starting vertex as visited.
     *   2. Insert the starting vertex into the queue.
     *   3. While the queue is not empty:
     *      a. Remove the front vertex from the queue (dequeue).
     *      b. Add it to the traversal output.
     *      c. For each unvisited neighbour of the current vertex:
     *         - Mark the neighbour as visited.
     *         - Add the neighbour to the queue (enqueue).
     *
     * Time Complexity:  O(V + E) where V = vertices, E = edges
     * Space Complexity: O(V) for the visited set and queue
     */
    public static List<Integer> bfs(Map<Integer, List<Integer>> graph, int start, int numVertices) {
        // Validate start vertex
        if (start < 0 || start >= numVertices) {
            throw new IllegalArgumentException(
                "Invalid starting vertex " + start + ". " +
                "Vertex must be between 0 and " + (numVertices - 1) + "."
            );
        }

        // Initialize visited set and queue
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> traversalOrder = new ArrayList<>();

        // Step 1: Mark the starting vertex as visited
        visited.add(start);

        // Step 2: Insert the starting vertex into the queue
        queue.add(start);

        // Step 3: Process the queue until it is empty
        while (!queue.isEmpty()) {
            // Step 3a: Remove the front vertex from the queue (FIFO)
            int vertex = queue.poll();

            // Step 3b: Add it to the traversal output
            traversalOrder.add(vertex);

            // Step 3c: Visit all adjacent vertices
            List<Integer> neighbours = graph.getOrDefault(vertex, Collections.emptyList());
            for (int neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    // Mark neighbour as visited (when enqueued, not dequeued)
                    visited.add(neighbour);
                    // Add neighbour to the queue
                    queue.add(neighbour);
                }
            }
        }

        return traversalOrder;
    }

    /**
     * Build an adjacency list representation of an undirected graph.
     *
     * @param numVertices Number of vertices (labelled 0 to numVertices-1).
     * @param edges       List of edges, where each edge is an int array {u, v}.
     * @return Adjacency list where each key maps to a sorted list of neighbours.
     */
    public static Map<Integer, List<Integer>> buildGraph(int numVertices, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException(
                    "Invalid edge (" + u + ", " + v + "). " +
                    "Vertices must be between 0 and " + (numVertices - 1) + "."
                );
            }
            // Undirected graph: add edge in both directions
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Sort neighbours for consistent traversal order
        for (List<Integer> neighbours : graph.values()) {
            Collections.sort(neighbours);
        }

        return graph;
    }

    /**
     * Display the adjacency list representation of the graph.
     */
    public static void printGraph(Map<Integer, List<Integer>> graph, int numVertices) {
        System.out.println("\nAdjacency List Representation:");
        System.out.println("-".repeat(35));
        for (int vertex = 0; vertex < numVertices; vertex++) {
            List<Integer> neighbours = graph.getOrDefault(vertex, Collections.emptyList());
            System.out.println("  Vertex " + vertex + " -> " + neighbours);
        }
        System.out.println("-".repeat(35));
    }

    /**
     * Run a BFS example with the given parameters and display results.
     */
    public static void runExample(String title, int numVertices, int[][] edges, int start, String description) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  " + title);
        System.out.println("=".repeat(55));

        if (description != null && !description.isEmpty()) {
            System.out.println("  " + description);
        }

        System.out.println("\n  Vertices: " + numVertices + " (labelled 0 to " + (numVertices - 1) + ")");
        System.out.print("  Edges: [");
        for (int i = 0; i < edges.length; i++) {
            System.out.print("(" + edges[i][0] + ", " + edges[i][1] + ")");
            if (i < edges.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("  Starting vertex: " + start);

        Map<Integer, List<Integer>> graph = buildGraph(numVertices, edges);
        printGraph(graph, numVertices);

        List<Integer> traversal = bfs(graph, start, numVertices);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < traversal.size(); i++) {
            sb.append(traversal.get(i));
            if (i < traversal.size() - 1) sb.append(" ");
        }
        System.out.println("\n  BFS Traversal: " + sb);
        System.out.println("  Vertices visited: " + traversal.size() + " out of " + numVertices);

        if (traversal.size() < numVertices) {
            Set<Integer> visited = new HashSet<>(traversal);
            List<Integer> unvisited = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                if (!visited.contains(i)) unvisited.add(i);
            }
            System.out.println("  Note: Vertices " + unvisited + " are not reachable "
                + "from vertex " + start + " (disconnected component).");
        }

        System.out.println();
    }

    /**
     * Run all three required demonstration examples.
     */
    public static void runDemoExamples() {
        System.out.println("\n" + "#".repeat(55));
        System.out.println("#  BFS Algorithm — Demonstration Examples");
        System.out.println("#".repeat(55));

        // Example 1: Default six-vertex graph (tree structure)
        runExample(
            "Example 1: Default Graph (Tree Structure)",
            6,
            new int[][]{{0,1}, {0,2}, {1,3}, {1,4}, {2,5}},
            0,
            "A simple tree with 6 vertices. All vertices are reachable."
        );

        // Example 2: Graph with a cycle
        runExample(
            "Example 2: Graph with a Cycle",
            5,
            new int[][]{{0,1}, {1,2}, {2,3}, {3,4}, {4,0}, {1,3}},
            0,
            "This graph contains cycles (0-1-4-0 and 1-2-3-1).\n"
            + "  BFS handles cycles correctly — visited vertices are never enqueued again."
        );

        // Example 3: Disconnected graph
        runExample(
            "Example 3: Disconnected Graph",
            7,
            new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {5,6}},
            0,
            "This graph has three components: {0,1,2}, {3,4}, and {5,6}.\n"
            + "  BFS from vertex 0 only traverses the component containing vertex 0."
        );
    }

    /**
     * Interactive mode: accept user input to build and traverse a custom graph.
     */
    public static void interactiveMode(Scanner scanner) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  BFS — Interactive Mode");
        System.out.println("=".repeat(55));
        System.out.println("  Build your own graph and run BFS traversal.\n");

        // Get number of vertices
        int numVertices = getValidInteger(scanner, "Enter the number of vertices (>= 1): ", 1, Integer.MAX_VALUE);

        // Get number of edges
        int numEdges = getValidInteger(scanner, "Enter the number of edges (>= 0): ", 0, Integer.MAX_VALUE);

        // Get edges
        int[][] edges = new int[numEdges][2];
        if (numEdges > 0) {
            System.out.println("\nEnter " + numEdges + " edge(s) as pairs of vertices (e.g., '0 1'):");
            for (int i = 0; i < numEdges; i++) {
                System.out.println("  Edge " + (i + 1) + "/" + numEdges + ":");
                while (true) {
                    System.out.print("  Enter edge (u v): ");
                    try {
                        int u = scanner.nextInt();
                        int v = scanner.nextInt();
                        if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                            System.out.println("  Error: Vertices must be between 0 and " + (numVertices - 1) + ".");
                            continue;
                        }
                        edges[i] = new int[]{u, v};
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("  Error: Both values must be integers. Example: 0 1");
                        scanner.nextLine(); // clear bad input
                    }
                }
            }
        }

        // Build graph
        Map<Integer, List<Integer>> graph = buildGraph(numVertices, edges);
        printGraph(graph, numVertices);

        // Get starting vertex
        int start = getValidInteger(scanner, "\nEnter the starting vertex (0 to " + (numVertices - 1) + "): ",
                                    0, numVertices - 1);

        // Run BFS
        List<Integer> traversal = bfs(graph, start, numVertices);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < traversal.size(); i++) {
            sb.append(traversal.get(i));
            if (i < traversal.size() - 1) sb.append(" ");
        }
        System.out.println("\n  BFS Traversal: " + sb);
        System.out.println("  Vertices visited: " + traversal.size() + " out of " + numVertices);

        if (traversal.size() < numVertices) {
            Set<Integer> visitedSet = new HashSet<>(traversal);
            List<Integer> unvisited = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                if (!visitedSet.contains(i)) unvisited.add(i);
            }
            System.out.println("\n  Note: Vertices " + unvisited + " are not reachable "
                + "from vertex " + start + ".\n"
                + "  In a disconnected graph, BFS only traverses the connected\n"
                + "  component that contains the starting vertex.");
        }
    }

    /**
     * Get a valid integer from user input with range validation.
     */
    private static int getValidInteger(Scanner scanner, String prompt, int minVal, int maxVal) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                if (value < minVal) {
                    System.out.println("Error: Value must be at least " + minVal + ". Please try again.");
                    continue;
                }
                if (value > maxVal) {
                    System.out.println("Error: Value must be at most " + maxVal + ". Please try again.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.nextLine(); // clear bad input
            }
        }
    }

    /**
     * Main entry point. Provides menu for demo examples or interactive mode.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "*".repeat(55));
        System.out.println("*  Breadth-First Search (BFS) Algorithm");
        System.out.println("*  DAA Assignment 2 — Java Implementation");
        System.out.println("*".repeat(55));
        System.out.println("\nBFS explores a graph level by level using a FIFO queue.");
        System.out.println("Time Complexity: O(V + E) | Space Complexity: O(V)");

        System.out.println("\nChoose an option:");
        System.out.println("  1. Run demonstration examples (recommended)");
        System.out.println("  2. Enter a custom graph (interactive mode)");
        System.out.println("  3. Run both");

        int choice = getValidInteger(scanner, "\nYour choice (1, 2, or 3): ", 1, 3);

        if (choice == 1) {
            runDemoExamples();
        } else if (choice == 2) {
            interactiveMode(scanner);
        } else if (choice == 3) {
            runDemoExamples();
            interactiveMode(scanner);
        }

        System.out.println("\n" + "*".repeat(55));
        System.out.println("*  BFS traversal complete. Thank you!");
        System.out.println("*".repeat(55) + "\n");

        scanner.close();
    }
}
