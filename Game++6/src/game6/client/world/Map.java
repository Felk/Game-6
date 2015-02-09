package game6.client.world;

import game6.core.buildings.CoreBuilding;
import game6.core.world.CoreMap;

public class Map extends CoreMap {

	private MapEntity entity;

	public Map(CoreMap core) {
		super(core.getTiles(), core.getHeights());
		this.entity = new MapEntity(this);
	}
	
	@Override
	public void addBuilding(int posX, int posY, CoreBuilding building) {
		super.addBuilding(posX, posY, building);
		entity.reloadMesh();
	}

	public MapEntity getEntity() {
		return entity;
	}
	
	public boolean isGridActivated() {
		return entity.isGridActivated();
	}
	
	public void setGridActivated(boolean is) {
		entity.setGridActivated(is);
	}
	
}
