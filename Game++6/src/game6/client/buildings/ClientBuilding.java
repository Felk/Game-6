package game6.client.buildings;

import game6.client.effects.Explosion;
import game6.core.buildings.CoreBuilding;
import game6.core.networking.packets.PacketUniqueID;
import game6.core.networking.packets.buildings.*;
import game6.core.world.Map;

public interface ClientBuilding extends CoreBuilding, ClientBuildingBehaviour {

	default public void process(PacketUniqueID packet) {
		if (packet instanceof PacketBuildingUpdate) {
			setEnergy(((PacketBuildingUpdate) packet).energy);
		} else if (packet instanceof PacketBuildingRemove) {
			PacketBuildingRemove packetRemove = (PacketBuildingRemove) packet;
			if (packetRemove.killed) {
				getWorld().getEffectContainer().addEffect(new Explosion(getCenterPosition()));
			}
			kill();
		} else if (packet instanceof PacketBuildingUpdateHealth) {
			setHealth(((PacketBuildingUpdateHealth) packet).health);
		}
	}

	@Override
	default public void kill() {
		if (getWorld().getSelectedBuilding() != null && getID() == getWorld().getSelectedBuilding().getID()) {
			getWorld().selectBuilding(null);
		}
		remove();
	}

	@Override
	default public Map<? extends CoreBuilding> getMap() {
		return getWorld().getMap();
	}

}
