package rancore.path;
import java.util.*;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>AStarSearch</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The AStarSearch class provides an implementation of the A* search algorithm. It uses a priority queue to store the nodes to be evaluated, sets to track the evaluated and visited nodes, and maps to store the previous nodes, g-scores, and f-scores.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>openSet</code>: A PriorityQueue to store the nodes to be evaluated based on their f-scores.</p>
 * <p><code>closedSet</code>: A Set to track the nodes that have already been evaluated.</p>
 * <p><code>cameFrom</code>: A Map to store the previous nodes for each node in the search.</p>
 * <p><code>gScore</code>: A Map to store the g-scores (cost from start) for each node in the search.</p>
 * <p><code>fScore</code>: A Map to store the f-scores (g-score + heuristic) for each node in the search.</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>AStarSearch()</code>: Initializes the AStarSearch object by creating the necessary data structures.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>search(Node startNode, Node goalNode)</code>:</p> Executes the A* search algorithm to find the path from the start node to the goal node. It returns the path as a list of nodes, or an empty list if no path is found.</p>
 * <p><code>calculateHeuristic(Node node1, Node node2)</code>:</p> Calculates the heuristic (estimated cost) between two nodes. In this implementation, it uses the Manhattan distance heuristic.</p>
 * <p><code>getFScore(Node node)</code>:</p> Retrieves the f-score (g-score + heuristic) of a node.</p>
 * <p><code>getNeighbors(Node node)</code>:</p> Retrieves the neighboring nodes of a given node.</p>
 * <p><code>reconstructPath(Node currentNode)</code>:</p> Reconstructs the path from the start node to the current node.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/06/03</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */

class AStarSearch {
    private PriorityQueue<AStarNode> openSet;  // Set of nodes to be evaluated
    private Set<AStarNode> closedSet;  // Set of nodes already evaluated
    private Map<AStarNode, AStarNode> cameFrom;  // Map of nodes and their previous nodes
    private Map<AStarNode, Integer> gScore;  // Map of nodes and their g-scores
    private Map<AStarNode, Integer> fScore;  // Map of nodes and their f-scores

    // Constructor
    public AStarSearch() {
        openSet = new PriorityQueue<>(Comparator.comparingInt(this::getFScore));
        closedSet = new HashSet<>();
        cameFrom = new HashMap<>();
        gScore = new HashMap<>();
        fScore = new HashMap<>();
    }

    /**
     * Executes the A* search algorithm.
     *
     * @param startNode The start node
     * @param goalNode  The goal node
     * @return The path from start to goal, or an empty list if no path found
     */
    public List<AStarNode> search(AStarNode startNode, AStarNode goalNode) {
        gScore.put(startNode, 0);
        fScore.put(startNode, calculateHeuristic(startNode, goalNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            AStarNode currentNode = openSet.poll();

            if (currentNode == goalNode) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            for (AStarNode neighbor : getNeighbors(currentNode)) {
                if (closedSet.contains(neighbor)) {
                    continue;  // Ignore the neighbor already evaluated
                }

                int tentativeGScore = gScore.get(currentNode) + 1;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    continue;  // Not a better path
                }

                cameFrom.put(neighbor, currentNode);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, tentativeGScore + calculateHeuristic(neighbor, goalNode));
            }
        }

        return Collections.emptyList();  // No path found
    }

    /**
     * Calculates the heuristic (estimated cost) between two nodes.
     * In this example, we use the Manhattan distance as the heuristic.
     *
     * @param node1 The first node
     * @param node2 The second node
     * @return The heuristic between the two nodes
     */
    private int calculateHeuristic(AStarNode node1, AStarNode node2) {
        return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
    }

    /**
     * Retrieves the f-score of a node.
     *
     * @param node The node
     * @return The f-score of the node
     */
    private int getFScore(AStarNode node) {
        return fScore.getOrDefault(node, Integer.MAX_VALUE);
    }

    /**
     * Retrieves the neighboring nodes of a given node.
     *
     * @param node The node
     * @return The neighboring nodes
     */
    private List<AStarNode> getNeighbors(AStarNode node) {
        List<AStarNode> neighbors = new ArrayList<>();

        // Assuming 4-connected grid, add neighbors in the four cardinal directions
        neighbors.add(new AStarNode(node.getX() - 1, node.getY(), false));
        neighbors.add(new AStarNode(node.getX() + 1, node.getY(), false));
        neighbors.add(new AStarNode(node.getX(), node.getY() - 1, false));
        neighbors.add(new AStarNode(node.getX(), node.getY() + 1, false));

        // Filter out neighbors that are obstacles or outside the grid boundaries
        neighbors.removeIf(neighbor -> neighbor.isObstacle() || neighbor.getX() < 0 || neighbor.getY() < 0);

        return neighbors;
    }

    /**
     * Reconstructs the path from start to goal.
     *
     * @param currentNode The goal node
     * @return The path from start to goal
     */
    private List<AStarNode> reconstructPath(AStarNode currentNode) {
        List<AStarNode> path = new ArrayList<>();
        path.add(currentNode);

        while (cameFrom.containsKey(currentNode)) {
            currentNode = cameFrom.get(currentNode);
            path.add(0, currentNode);
        }

        return path;
    }
}

