package nl.robertalblas.engine.renderer;

import javax.media.opengl.GL2;

import nl.robertalblas.engine.util.Vector3;

public interface RenderObject {

	Vector3 getCameraLookAtPosition();

	Vector3 getRotation();

	void draw(GL2 gl);

}
