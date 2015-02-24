package game6.client.buildings;

import game6.client.ObjectRenderer;
import game6.core.buildings.CoreBuildingReactor;

import java.util.List;

import de.nerogar.engine.UpdateEvent;
import de.nerogar.render.*;

public class BuildingReactor extends CoreBuildingReactor {

	private RenderProperties3f renderProperties;
	private ObjectRenderer renderer;

	public BuildingReactor(long id) {
		super(id);
	}

	@Override
	public void init() {
		renderer = new ObjectRenderer(
				TextureLoader.loadTexture("res/buildings/reactor/color.png"),
				TextureLoader.loadTexture("res/buildings/reactor/light.png"),
				TextureLoader.loadTexture("res/buildings/reactor/faction.png"),
				WavefrontLoader.loadObject("res/buildings/reactor/mesh.obj")
				);

		renderProperties = new RenderProperties3f();
	}

	@Override
	public void render(Shader shader) {
		renderProperties.setXYZ(getPosX(), 0, getPosY());
		renderer.render(shader, renderProperties.getModelMatrix());
	}

	@Override
	public void update(List<UpdateEvent> events) {
	}

}
