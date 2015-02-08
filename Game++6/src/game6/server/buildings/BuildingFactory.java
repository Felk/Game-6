package game6.server.buildings;

import game6.core.buildings.CoreBuildingFactory;

public class BuildingFactory extends BaseBuilding {

	public BuildingFactory(int id) {
		super(new CoreBuildingFactory(id));
	}

}