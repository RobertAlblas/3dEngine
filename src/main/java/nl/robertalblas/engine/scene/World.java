package nl.robertalblas.engine.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nl.robertalblas.engine.renderer.Camera;
import nl.robertalblas.engine.renderer.RenderObject;
import nl.robertalblas.engine.renderer.Renderer;
import nl.robertalblas.engine.util.Vector3;

public class World {
	private Thingy movingCube;
	private Thingy staticCube;
	
	private List<Entity> entities;
	private Camera lookAt;
	private Renderer renderer;
	
	public World(){
		movingCube = new Thingy();
		movingCube.setPosition(new Vector3(0.0f, 2.0f, 10.0f));
		staticCube = new Thingy();
		staticCube.setPosition(new Vector3(0.0f, 0.0f, 10.0f));
		staticCube.setColor(0.0f, 0.0f, 0.0f);

		entities = new ArrayList<Entity>();
		entities.add(staticCube);
		entities.add(movingCube);

		lookAt = movingCube;
	}
	
	public void update(Set<Integer> keysPressed, long deltaTime){
		for(Entity entity : entities){
			if(entity != staticCube){
				entity.update(keysPressed, deltaTime);
			}
		}
	}
	
	public void render(){
		renderer.setCamera(lookAt);
		for(RenderObject entity: entities){
			renderer.addRenderObject(entity);
		}
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
}
