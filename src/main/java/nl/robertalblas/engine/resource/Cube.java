package nl.robertalblas.engine.resource;

import static javax.media.opengl.GL.GL_TRIANGLES;

import javax.media.opengl.GL2;

public class Cube implements Drawable {
	
	private float r;
	private float g;
	private float b;

	public Cube(float r, float g, float b){
		this.r = r;
		this.b = b;
		this.g = g;
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
	
	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

}
