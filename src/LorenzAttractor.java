import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

public class LorenzAttractor {

    private double x = 0.1, y = 0.0, z = 0.0; // Initial conditions
    private final double sigma = 10.0;
    private final double beta = 8.0 / 3.0;
    private final double rho = 28.0;
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private float zoomLevel = 60.0f; // Initial zoom level

    public static void main(String[] args) {
        new LorenzAttractor().run();
    }

    public void run() {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        long window = GLFW.glfwCreateWindow(800, 600, "Lorenz Attractor", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        setupProjection();

        glPointSize(10.0f);

        double lastTime = GLFW.glfwGetTime(); // Get the initial time
        double accumulator = 0.0;
        final double fixedTimeStep = 0.01; // Fixed time step for simulation

        while (!GLFW.glfwWindowShouldClose(window)) {
            // Calculate delta time
            double currentTime = GLFW.glfwGetTime();
            double deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            // Accumulate elapsed time
            accumulator += deltaTime;

            // Update simulation with a fixed time step
            while (accumulator >= fixedTimeStep) {
                update(fixedTimeStep);
                accumulator -= fixedTimeStep;
            }

            // Render the scene
            render();

            // Swap buffers and poll events
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void setupProjection() {
        glViewport(0, 0, windowWidth, windowHeight);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-zoomLevel, zoomLevel, -zoomLevel, zoomLevel, -zoomLevel, zoomLevel);
        glMatrixMode(GL_MODELVIEW);
    }

    private void update(double dt) {
        // Lorenz equations with delta time
        double dx = sigma * (y - x) * dt;
        double dy = (x * (rho - z) - y) * dt;
        double dz = (x * y - beta * z) * dt;

        x += dx;
        y += dy;
        z += dz;
    }

    private void render() {
        // glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
        glClear(GL_DEPTH_BUFFER_BIT);

        glBegin(GL_POINTS);

        // Map the x, y, and z position to a color gradient using sine and cosine
        // functions
        float red = (float) (0.5 + 0.5 * Math.sin(x * 0.1 + Math.PI / 3));
        float green = (float) (0.5 + 0.5 * Math.sin(y * 0.1 + Math.PI / 2));
        float blue = (float) (0.5 + 0.5 * Math.sin(z * 0.1));

        glColor3f(red, green, blue);
        glVertex3d(x, y, z);

        glEnd();
    }
}
