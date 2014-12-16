package nl.robertalblas.engine.renderer;

import nl.robertalblas.engine.util.Vector3;

public interface Camera {

	Vector3 getCameraLookAtPosition();

	Vector3 getCameraPosition(float distance);

	Vector3 getUpVector();

}
