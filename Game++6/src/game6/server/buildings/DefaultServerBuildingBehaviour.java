package game6.server.buildings;

import game6.server.world.World;

public class DefaultServerBuildingBehaviour implements ServerBuildingBehaviour {

	private World world;

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public void setWorld(World world) {
		this.world = world;
	}

}