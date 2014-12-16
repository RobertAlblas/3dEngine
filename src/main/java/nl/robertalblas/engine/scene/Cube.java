package nl.robertalblas.engine.scene;

import static javax.media.opengl.GL.GL_TRIANGLES;

import java.awt.event.KeyEvent;
import java.util.Set;

import javax.media.opengl.GL2;

import nl.robertalblas.engine.renderer.Camera;
import nl.robertalblas.engine.util.Vector3;

public class Cube extends Entity implements Camera{

	private Vector3 lookAt;

	private float r;
	private float g;
	private float b;

	private float speed;
	private float turnspeed;

	public Cube() {
		super();
		lookAt = getCameraPosition(10.0f);

		r = 1.0f;
		g = 0.0f;
		b = 0.0f;

		speed = 0.01f;
		turnspeed = 0.1f;
	}

	@Override
	public void update(Set<Integer> keysPressed, long deltaTime) {
		for (Integer key : keysPressed) {
			switch (key) {
			case KeyEvent.VK_D:
				this.moveRight(speed * deltaTime);
				break;
			case KeyEvent.VK_A:
				this.moveLeft(speed * deltaTime);
				break;
			case KeyEvent.VK_W:
				this.moveForward(speed * deltaTime);
				break;
			case KeyEvent.VK_S:
				this.moveBackward(speed * deltaTime);
				break;
			case KeyEvent.VK_R:
				this.moveUp(speed * deltaTime);
				break;
			case KeyEvent.VK_F:
				this.moveDown(speed * deltaTime);
				break;
			case KeyEvent.VK_RIGHT:
				this.addRotation(0.0f, -turnspeed * deltaTime, 0.0f);
				break;
			case KeyEvent.VK_LEFT:
				this.addRotation(0.0f, turnspeed * deltaTime, 0.0f);
				break;
			case KeyEvent.VK_UP:
				this.addRotation(0.0f, 0.0f, turnspeed * deltaTime);
				break;
			case KeyEvent.VK_DOWN:
				this.addRotation(0.0f, 0.0f, -turnspeed * deltaTime);
				break;
			case KeyEvent.VK_Z:
				this.addRotation(-turnspeed * deltaTime, 0.0f, 0.0f);
				break;
			case KeyEvent.VK_X:
				this.addRotation(turnspeed * deltaTime, 0.0f, 0.0f);
				break;
			}
		}
	}

	public void draw(GL2 gl) {
		gl.glBegin(GL_TRIANGLES);
		gl.glColor3f(r, g, b);

		// Front face
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, -1.0f);

		// Back face
		gl.glVertex3f(1.0f, -1.0f, 1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);
		gl.glVertex3f(1.0f, -1.0f, 1.0f);
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);

		// Top face
		gl.glVertex3f(1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);
		gl.glVertex3f(-1.0f, 1.0f, -1.0f);
		gl.glVertex3f(1.0f, 1.0f, -1.0f);
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);

		// Bottom face
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, -1.0f, 1.0f);
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);

		gl.glColor3f(r, g, b);
		// Left face
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, -1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);
		gl.glVertex3f(-1.0f, 1.0f, 1.0f);

		// Right face
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, 1.0f, -1.0f);
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glVertex3f(1.0f, -1.0f, 1.0f);
		gl.glVertex3f(1.0f, 1.0f, 1.0f);

		gl.glEnd();
	}

	public Vector3 getCameraLookAtPosition() {
		return position;
	}


	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void moveLeft(float speed) {
		position.addX((float) (speed * Math.cos(Math.toRadians((rotation.getY() + 90))) * Math.cos(Math.toRadians(rotation.getX()))));
		position.addZ((float) -(speed * Math.sin(Math.toRadians(rotation.getY() + 90)) * Math.cos(Math.toRadians(rotation.getX()))));
		position.addY((float) (speed * Math.sin(Math.toRadians(rotation.getX()))));
	}

	public void moveRight(float speed) {
		position.addX((float) -(speed * Math.cos(Math.toRadians((rotation.getY() + 90))) * Math.cos(Math.toRadians(rotation.getX()))));
		position.addZ((float) (speed * Math.sin(Math.toRadians(rotation.getY() + 90)) * Math.cos(Math.toRadians(rotation.getX()))));
		position.addY((float) -(speed * Math.sin(Math.toRadians(rotation.getX()))));
	}

	public void moveForward(float speed) {
		position.addX((float) (speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		position.addZ((float) -(speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		position.addY((float) (speed * Math.sin(Math.toRadians(rotation.getZ()))));
	}

	public void moveBackward(float speed) {
		position.addX((float) -(speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		position.addZ((float) (speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		position.addY((float) -(speed * Math.sin(Math.toRadians(rotation.getZ()))));
	}

	public void moveUp(float speed) {
		position.addX((float) (speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		position.addZ((float) -(speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		position.addY((float) (speed * Math.sin(Math.toRadians(rotation.getZ() + 90))));
	}

	public void moveDown(float speed) {
		position.addX((float) -(speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		position.addZ((float) (speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		position.addY((float) -(speed * Math.sin(Math.toRadians(rotation.getZ() + 90))));
	}

	public void addRotation(float x, float y, float z) {
		rotation.addX(x);
		rotation.addY(y);
		rotation.addZ(z);
	}

	public Vector3 getCameraPosition(float distance) {
		lookAt = new Vector3(position.getX(), position.getY(), position.getZ());

		lookAt.addX((float) -(distance * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		lookAt.addZ((float) (distance * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ()))));
		lookAt.addY((float) -(distance * Math.sin(Math.toRadians(rotation.getZ()))));

		return lookAt;
	}
	
	public Vector3 getUpVector(){
		Vector3 up = new Vector3();
		
		up.addX((float) (speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		up.addZ((float) -(speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		up.addY((float) (speed * Math.sin(Math.toRadians(rotation.getZ() + 90))));
		
		return up;
	}
}
