package rancore.path;

import java.util.List;

/**
 * Example usage of the A* search algorithm.
 */
public class AStarExample {
    public static void main(String[] args) {
        // Create a grid with nodes
        AStarNode[][] grid = new AStarNode[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new AStarNode(i, j, false);
            }
        }

        // Set obstacles in the grid
        grid[1][2] = new AStarNode(1, 2, true);
        grid[2][2] = new AStarNode(2, 2, true);
        grid[3][2] = new AStarNode(3, 2, true);

        // Create an instance of the A* search algorithm
        AStarSearch aStarSearch = new AStarSearch();

        // Define the start and goal nodes
        AStarNode startNode = grid[0][0];
        AStarNode goalNode = grid[4][4];

        // Execute the A* search algorithm
        List<AStarNode> path = aStarSearch.search(startNode, goalNode);

        // Print the path
        if (!path.isEmpty()) {
            for (AStarNode node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}

