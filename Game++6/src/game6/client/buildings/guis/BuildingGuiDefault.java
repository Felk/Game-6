package game6.client.buildings.guis;

import game6.client.buildings.ClientBuilding;
import game6.client.gui.Font;
import game6.client.gui.components.GColorfield;
import game6.client.gui.components.GLabel;

import java.awt.Color;

public class BuildingGuiDefault extends BuildingGui<ClientBuilding> {

	private GLabel text, health;
	private GColorfield background;

	public BuildingGuiDefault(ClientBuilding building) {
		super(building);
	}

	@Override
	protected void initComponents() {
		setSize(300, 100);

		text = new GLabel(building.getName() + " #" + building.getID());
		text.setPos(0, 10);
		text.setAlignment(Font.CENTER);
		text.setSize(300, 40);

		health = new GLabel();
		health.setPos(0, 50);
		health.setAlignment(Font.CENTER);
		health.setSize(300, 40);

		background = new GColorfield(new Color(0, 0, 0, 0.5f));
		background.setPos(0, 0);
		background.setSize(getSizeX(), getSizeY());

		add(background);
		add(text);
		add(health);
	}

	@Override
	protected void updateComponents() {
		health.setText("Health: " + building.getHealth() + "/" + building.getMaxHealth());
	}

}
