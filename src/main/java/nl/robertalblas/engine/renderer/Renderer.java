package nl.robertalblas.engine.renderer;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import nl.robertalblas.engine.util.Vector3;

import com.jogamp.opengl.util.FPSAnimator;

public class Renderer extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = 1L;
	private GLU glu; // for the GL Utility
	private GL2 gl;

	private static final int FPS = 60; // animator's target frames per second
	
	private Camera camera;
	private Set<RenderObject> renderObjects;
	
	private Set<RenderObserver> observers;

	public Renderer() {
		this.addGLEventListener(this);
		renderObjects = new CopyOnWriteArraySet<RenderObject>();
		observers = new HashSet<RenderObserver>();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		if (height == 0)
			height = 1; // prevent divide by zero
		float aspect = (float) width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 0.1, 100.0);
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void init(GLAutoDrawable arg0) {
		gl = arg0.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);

	}

	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
	}

	public void display(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL_MODELVIEW);

		for (RenderObject renderObject : renderObjects) {
			gl.glLoadIdentity();
			Vector3 lookAtVector = camera.getCameraLookAtPosition();
			// Vector3 cameraVector = new Vector3(0.0f, 0.0f, 0.0f);
			Vector3 cameraVector = camera.getCameraPosition(10.0f);
			Vector3 cameraRotation = camera.getUpVector();

			glu.gluLookAt(cameraVector.getX(), cameraVector.getY(), cameraVector.getZ(), lookAtVector.getX(), lookAtVector.getY(), lookAtVector.getZ(), cameraRotation.getX(), cameraRotation.getY(),
					cameraRotation.getZ());
			gl.glTranslatef(renderObject.getCameraLookAtPosition().getX(), renderObject.getCameraLookAtPosition().getY(), renderObject.getCameraLookAtPosition().getZ());
			gl.glRotatef(renderObject.getRotation().getY(), 0.0f, 1.0f, 0.0f);
			gl.glRotatef(renderObject.getRotation().getZ(), 0.0f, 0.0f, 1.0f);
			gl.glRotatef(renderObject.getRotation().getX(), 1.0f, 0.0f, 0.0f);
			renderObject.draw(gl);
		}
	
		for(RenderObserver o: observers){
			o.onFrameRendered();
		}
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void addRenderObject(RenderObject renderObject) {
		this.renderObjects.add(renderObject);
	}

	public void render() {
		final FPSAnimator animator = new FPSAnimator(this, FPS, true);
		animator.start();
	}
	
	public void addObserver(RenderObserver o){
		observers.add(o);
	}
	
	public void removeObserver(RenderObserver o){
		observers.remove(o);
	}

}
