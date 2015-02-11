package game6.client.gui.components;

import game6.client.Controller;
import game6.client.gui.Font;
import game6.core.buildings.CoreBuilding;

import java.awt.Color;

public class GPanelBuilding extends GPanel {

	private Controller controller;
	private GColorfield background;
	private CoreBuilding building;
	private GLabel title;
	private GLabel energy;

	public GPanelBuilding(Controller controller) {

		this.controller = controller;

		background = new GColorfield(new Color(0, 0, 0, 0.5f));
		background.setSize(99999, 99999);
		background.setPos(0, 0);

		title = new GLabel();
		title.setAlignment(Font.CENTER);

		energy = new GLabel("Energie: ");

		add(background);
		add(title);
		add(energy);
	}

	@Override
	public void update() {
		super.update();
		if (building != null) {
			if (building.getFaction() == controller.getFaction()) {
				energy.setText("Energie: " + building.getEnergy() + " / " + building.getMaxEnergy());
			} else {
				energy.setText("owned by " + building.getFaction());
			}
		}
	}

	@Override
	public void render(int offsetX, int offsetY) {
		if (building != null) {
			super.render(offsetX, offsetY);
		}
	}

	public void setBuilding(CoreBuilding building) {
		this.building = building;
		if (building != null) {
			title.setText(building.getName() + " #" + building.getID());
		}
	}

	public void resize() {
		title.setSize(getSizeX(), 40);
		title.setPos(0, getSizeY() - 50);

		energy.setSize(0, 40);
		energy.setPos(20, getSizeY() - 100);
	}
}
