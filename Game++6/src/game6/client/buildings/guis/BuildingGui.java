package game6.client.buildings.guis;

import game6.client.buildings.ClientBuilding;
import game6.client.gui.components.GPanel;

public abstract class BuildingGui<T extends ClientBuilding> extends GPanel {

	protected T building;

	public BuildingGui(T building) {
		this.building = building;
		initComponents();
	}

	@Override
	public void update() {
		super.update();
		updateComponents();
	}

	protected abstract void initComponents();

	protected abstract void updateComponents();

}
