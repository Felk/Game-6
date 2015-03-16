package game6.core.faction;

import game6.core.buildings.BuildingType;
import game6.core.buildings.CoreBuilding;
import game6.core.networking.packets.PacketEnabledBuildingsList;
import game6.core.networking.packets.PacketPlayerInfo;

import java.util.*;

import de.nerogar.network.packets.Packet;
import de.nerogar.util.Color;

public enum Faction {
	BLUE(1, new Color(0.2f, 0.3f, 1.0f, 1.0f)),
	RED(2, new Color(1.0f, 0.2f, 0.1f, 1.0f)),
	GREEN(3, new Color(0.1f, 1.0f, 0.3f, 1.0f)),
	YELLOW(4, new Color(1.0f, 0.5f, 0.1f, 1.0f));

	public static Faction own;

	private int id;
	private List<Player> players;

	// TODO change to private (later)
	public List<CoreBuilding> ownBuildings;
	public Color color;
	private HashSet<BuildingType> buildableBuildings;

	private boolean updateEnabledBuildings;

	private Faction(int id, Color color) {
		ownBuildings = new ArrayList<CoreBuilding>();
		this.id = id;
		this.players = new ArrayList<>();
		this.color = color;
		this.buildableBuildings = new HashSet<>();
		enableDefaultBuildings();
	}

	// TODO don't define here, which buildings are enabled by default
	private void enableDefaultBuildings() {
		enableBuilding(BuildingType.REACTOR);
		enableBuilding(BuildingType.RESEARCH);
		enableBuilding(BuildingType.FACTORY);
	}

	public void addPlayer(Player player) {
		players.add(player);
		// tell the new player, which faction he is in.
		player.getConnection().send(new PacketPlayerInfo(this));
		// update other stuff too
		updateEnabledBuildings = true;
	}

	public static Faction getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}

	public int getID() {
		return id;
	}

	public static Faction byID(int id) {
		for (Faction faction : values()) {
			if (faction.getID() == id) {
				return faction;
			}
		}
		return null;
	}

	public void broadcast(Packet packet) {
		for (Player player : players) {
			player.getConnection().send(packet);
		}
	}

	public List<Packet> getPackets(int channelID) {
		List<Packet> packets = new ArrayList<>();
		for (Player player : players) {
			packets.addAll(player.getConnection().get(channelID));
		}
		return packets;
	}

	public static void broadcastAll(Packet packet) {
		for (Faction faction : values()) {
			faction.broadcast(packet);
		}
	}

	public static void updateAll() {
		for (Faction faction : values()) {
			faction.update();
		}
	}

	public void update() {
		if (updateEnabledBuildings) {
			updateEnabledBuildings = false;
			broadcast(new PacketEnabledBuildingsList(buildableBuildings));
		}
	}

	public void enableBuilding(BuildingType building) {
		buildableBuildings.add(building);
		updateEnabledBuildings = true;
	}

	public void disableBuilding(BuildingType building) {
		buildableBuildings.remove(building);
		updateEnabledBuildings = true;
	}

	public boolean isBuildingEnabled(BuildingType building) {
		return buildableBuildings.contains(building);
	}

	public HashSet<BuildingType> getBuildableBuildings() {
		return buildableBuildings;
	}

	public void setBuildableBuildings(HashSet<BuildingType> buildings) {
		this.buildableBuildings = buildings;
	}

}
