package nl.robertalblas.engine.scene;

import java.util.Set;

import nl.robertalblas.engine.renderer.RenderObject;
import nl.robertalblas.engine.util.Vector3;

public abstract class Entity implements RenderObject{

	protected Vector3 position;
	protected Vector3 rotation;
	
	public Entity(){
		position = new Vector3();
		rotation = new Vector3();
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getRotation() {
		return rotation;
	}

	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}

	public void update(Set<Integer> keysPressed, long deltaTime) {
		
	}
}
