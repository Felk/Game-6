package game6.server.buildings;

import game6.client.buildings.BuildingGui;
import game6.core.buildings.CoreBuildingRock;
import de.nerogar.render.Shader;

public class BuildingRock extends CoreBuildingRock {

	public BuildingRock() {
		super(getNextID());
	}

	@Override
	public void init() {
	}

	@Override
	public void render(Shader shader) {
	}

	@Override
	public void update() {
	}

	@Override
	public BuildingGui getGui() {
		return null;
	}

}
