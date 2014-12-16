package nl.robertalblas.engine.scene;

import java.awt.event.KeyEvent;
import java.util.Set;

import javax.media.opengl.GL2;

import nl.robertalblas.engine.renderer.Camera;
import nl.robertalblas.engine.resource.Cube;
import nl.robertalblas.engine.resource.Drawable;
import nl.robertalblas.engine.util.Vector3;

public class Thingy extends Entity implements Camera {

	private Vector3 lookAt;

	private float speed;
	private float turnspeed;

	private Drawable drawable;

	public Thingy() {
		super();
		lookAt = getCameraPosition(10.0f);

		speed = 0.01f;
		turnspeed = 0.1f;

		drawable = new Cube(1.0f, 0.0f, 0.0f);
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
		drawable.draw(gl);
	}

	public Vector3 getCameraLookAtPosition() {
		return position;
	}

	public void setColor(float r, float g, float b) {
		((Cube) drawable).setColor(r, g, b);
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

	public Vector3 getUpVector() {
		Vector3 up = new Vector3();

		up.addX((float) (speed * Math.cos(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		up.addZ((float) -(speed * Math.sin(Math.toRadians(rotation.getY())) * Math.cos(Math.toRadians(rotation.getZ() + 90))));
		up.addY((float) (speed * Math.sin(Math.toRadians(rotation.getZ() + 90))));

		return up;
	}
}
