package game6.client.gui;

import game6.client.Game;
import game6.client.gui.components.GButton;
import game6.client.gui.components.GColorfield;
import game6.client.gui.components.GInput;
import game6.client.gui.components.GInputNumber;
import game6.client.gui.components.GLabel;

import java.awt.Color;

/**
 * The gui, that is visible on game startup.
 * Currently contains inputs for ip:port to connect to (internal) servers and start the map.
 * @author Felk
 *
 */
public class GuiTitlescreen extends Gui {

	public static GuiTitlescreen instance = new GuiTitlescreen();

	private GColorfield background;
	private GLabel title, label;
	private GInput host;
	private GInputNumber port;
	private GButton connectButton;

	@Override
	public void initComponents() {
		background = new GColorfield(new Color(0, 0, 0));

		title = new GLabel(Game.langFile.getString("gui.main.label.title"));
		title.setAlignment(Font.CENTER);

		label = new GLabel(Game.langFile.getString("gui.main.label.host"));
		label.setAlignment(Font.RIGHT);

		host = new GInput("localhost");
		port = new GInputNumber(4200);

		connectButton = new GButton(Game.langFile.getString("gui.main.button.connect"));
		connectButton.addClickedListener(source -> {
			if (controller.connect(host.getText(), port.getNumber())) {
				Guis.select(Guis.INGAME);
			}
		});

		add(background);
		add(title);
		add(label);
		add(host);
		add(port);
		add(connectButton);
	}

	@Override
	public void onSelect() {
	}

	@Override
	public void onDeselect() {
	}

	@Override
	public void onResize(int screenWidth, int screenHeight) {

		background.setPos(0, 0);
		background.setSize(screenWidth, screenWidth);

		title.setPos(0, screenHeight - 100);
		title.setSize(screenWidth, 80);

		label.setSize(0, 60);
		label.setPos(screenWidth / 2, screenHeight / 2);

		host.setSize(350, 60);
		host.setPos(screenWidth / 2, screenHeight / 2);

		port.setSize(140, 60);
		port.setPos(screenWidth / 2 + 355, screenHeight / 2);

		connectButton.setSize(220, 60);
		connectButton.setPos(screenWidth / 2, screenHeight / 2 - 65);

	}

}
