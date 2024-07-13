package org.rancore.path;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>AStarNode</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The AStarNode class represents a node in the search graph. It contains the X and Y coordinates of the node and an indicator if it is an obstacle or not.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>x</code>: An integer representing the X coordinate of the node.</p>
 * <p><code>y</code>: An integer representing the Y coordinate of the node.</p>
 * <p><code>obstacle</code>: A boolean indicating if the node is an obstacle.</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>Node(int x, int y, boolean obstacle)</code>: Initializes a Node object with the specified X and Y coordinates and obstacle indicator.</p>
 *
 * <p><u><b>Getters and Setters:</b></u></p>
 * <p><code>getX()</code>: Returns the X coordinate of the node.</p>
 * <p><code>getY()</code>: Returns the Y coordinate of the node.</p>
 * <p><code>isObstacle()</code>: Returns true if the node is an obstacle, false otherwise.</p>
 */
class AStarNode {
    private int x;  // X coordinate of the node
    private int y;  // Y coordinate of the node
    private boolean obstacle;  // Indicates if the node is an obstacle

    // Constructor
    public AStarNode(int x, int y, boolean obstacle) {
        this.x = x;
        this.y = y;
        this.obstacle = obstacle;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstacle() {
        return obstacle;
    }
}