package game6.server.buildings;

import game6.core.buildings.CoreConstructionsite;
import game6.core.faction.Faction;
import game6.core.networking.packets.PacketFinishConstruction;
import game6.core.networking.packets.PacketUpdateConstructionsite;
import game6.core.util.Resource;
import game6.core.util.ResourceContainer;
import game6.server.world.World;

/**
 * Don't add this to the building type list. It's a special building representing a construction site.
 * It also does not have the necessary long-only constructor.
 * @author Felk
 */
public class Constructionsite extends CoreConstructionsite<IServerBuilding> implements IServerBuilding {

	private ServerBehaviourDefault defaultBehaviour = new ServerBehaviourDefault();
	
	public Constructionsite(IServerBuilding building, ResourceContainer constructionCost) {
		super(building, constructionCost);
		getCostRemaining().setChangeCallback(this::remainingCostChanged);
	}

	private void remainingCostChanged() {
		getFaction().broadcast(new PacketUpdateConstructionsite(getID(), getCostRemaining()));
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (isFinished()) {
			getWorld().finishConstructionsite(this);
			Faction.broadcastAll(new PacketFinishConstruction(getID()));
		}

		// TODO for debugging. remove later
		if (Math.random() < 0.2f) {
			getCostRemaining().removeResource(Resource.WOOD, 1);
			getCostRemaining().removeResource(Resource.METAL, 1);
		}
	}
	
	@Override
	public World getWorld() {
		return defaultBehaviour.getWorld();
	}

	@Override
	public void setWorld(World world) {
		defaultBehaviour.setWorld(world);
		getBuilding().setWorld(world);
	}
	
}
