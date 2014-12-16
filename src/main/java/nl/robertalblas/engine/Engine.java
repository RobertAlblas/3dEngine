package nl.robertalblas.engine;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import nl.robertalblas.engine.renderer.RenderObserver;
import nl.robertalblas.engine.renderer.Renderer;
import nl.robertalblas.engine.scene.World;

// GL constants

public class Engine implements KeyListener, RenderObserver {

	private Renderer renderer;
	private Set<Integer> keysPressed;

	// Define constants for the top-level container
	private static String TITLE = "Title";
	private static final int CANVAS_WIDTH = 320; // width of the drawable
	private static final int CANVAS_HEIGHT = 240; // height of the drawable

	private JFrame frame;
	private World world;

	private Thread renderThread;

	public Engine() {
		renderer = new Renderer();
		this.keysPressed = new HashSet<Integer>();

		renderer.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		frame = new JFrame();

		frame.getContentPane().add(renderer);

		frame.setTitle(TITLE);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

		world = new World();
		world.setRenderer(renderer);
		world.render();

	}

	public void keyPressed(KeyEvent arg0) {
		keysPressed.add(arg0.getKeyCode());
	}

	public void keyReleased(KeyEvent arg0) {
		keysPressed.remove(arg0.getKeyCode());
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void run() {

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure
				// that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						System.exit(0);
					}
				}.start();
			}
		});

		renderThread = new Thread() {
			@Override
			public void run() {
				renderer.render();
			}
		};
		renderThread.start();

		long time = System.currentTimeMillis();

		while (true) {
			long newTime = System.currentTimeMillis();
			
			long deltaTime = newTime - time;
			time = newTime;
			System.out.println(deltaTime);
			world.update(keysPressed, deltaTime);
		}
	}

	public void onFrameRendered() {
		
	}

}
