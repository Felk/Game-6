package game6.server.entities;

import game6.core.ai.pathfinding.Pathfinder;
import game6.core.entities.CoreEntity;
import game6.core.faction.Faction;
import game6.core.networking.packets.PacketUniqueID;
import game6.core.networking.packets.entities.*;
import game6.server.world.World;

import java.util.List;

import de.nerogar.util.Vector3f;

public interface ServerEntity extends CoreEntity, ServerEntityBehaviour {

	@Override
	default public void setPath(List<Vector3f> newPath) {
		getPath().clear();
		getPath().addAll(newPath);
		Faction.broadcastAll(new PacketEntityUpdatePath(this));
	}

	public World getWorld();

	public void setWorld(World world);

	@Override
	default public void process(PacketUniqueID packet) {
		if (packet instanceof PacketEntityMove) {
			move(((PacketEntityMove) packet).position);
		}
	}

	@Override
	default public Pathfinder getPathfinder() {
		return getWorld().getMap().getPathfinder();
	}

	@Override
	default public void kill() {
		Faction.broadcastAll(new PacketEntityRemove(getID(), true));
		remove();
	}

}