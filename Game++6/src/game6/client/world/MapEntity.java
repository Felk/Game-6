package game6.client.world;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.opengl.GL11;

import game6.core.buildings.CoreBuilding;
import de.nerogar.engine.entity.BaseEntity;
import de.nerogar.physics.BoundingAABB;
import de.nerogar.render.RenderProperties;
import de.nerogar.render.Shader;
import de.nerogar.util.Vector3f;

public class MapEntity extends BaseEntity {

	private Map map;
	private MapMesh mesh;
	private MapGridMesh gridMesh;
	private RenderProperties renderProperties;
	private boolean gridActivated = false;
	private CoreBuilding preview;

	private Shader shader;

	public MapEntity(Map map) {
		super(new BoundingAABB(new Vector3f(-99999), new Vector3f(99999, 0, 99999)), new Vector3f(0));
		this.map = map;
		mesh = new MapMesh(map);
		gridMesh = new MapGridMesh(map);
		renderProperties = new RenderProperties();

		shader = new Shader("shaders/world.vert", "shaders/world.frag");
	}

	public boolean isGridActivated() {
		return gridActivated;
	}

	public void setGridActivated(boolean is) {
		gridActivated = is;
	}

	public void setBuildingPreview(CoreBuilding preview) {
		this.preview = preview;
	}

	public void reloadMesh() {
		mesh.reload();
		gridMesh.reload();
	}

	@Override
	public void update(float timeDelta) {
	}

	@Override
	public void render() {
		mesh.render(renderProperties);
		if (gridActivated) {
			// TODO Don't hardcode OpenGL here. This is to ensure that the grid overlays the terrain.
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			gridMesh.render(renderProperties);
		}

		shader.activate();

		glUniform1i(glGetUniformLocation(shader.shaderHandle, "colorTex"), 0);
		glUniform1i(glGetUniformLocation(shader.shaderHandle, "factionTex"), 1);

		if (preview != null) {
			preview.render(map.getHeight(preview.getPosX(), preview.getPosY()));
		}
		for (CoreBuilding building : map.getBuildings()) {
			building.render(map.getHeight(building.getPosX(), building.getPosY()));
		}

		shader.deactivate();
	}

}
