package game6.client.gui.components;

import game6.client.gui.BuildingIconTextures;
import game6.client.gui.listener.ClickedListener;
import game6.client.gui.listener.MouseListener;
import game6.core.buildings.BuildingType;

import java.util.ArrayList;
import java.util.List;

/**
 * Gui-Component, that renders a Building-Type as an Image.
 * Offers a listener for clicked-events.
 * @author Felk
 *
 */
public class GBuildingIcon extends GComponent implements MouseListener {

	private List<ClickedListener> clickedListener = new ArrayList<>();

	private GImage image;
	private BuildingType type;

	public GBuildingIcon(BuildingType type) {
		this.type = type;
		image = new GImage(BuildingIconTextures.get(type));
	}

	public BuildingType getBuildingType() {
		return type;
	}

	@Override
	public void init() {
		addMouseListener(this);
	}

	@Override
	public void setSize(int x, int y) {
		super.setSize(x, y);
		image.setSize(x, y);
	}

	@Override
	public void setOffset(int x, int y) {
		super.setOffset(x, y);
		image.setOffset(x, y);
	}

	@Override
	public void setPos(int x, int y) {
		super.setPos(x, y);
		image.setPos(x, y);
	}

	@Override
	public void render() {
		image.render(true);
	}

	public boolean addClickedListener(ClickedListener listener) {
		return clickedListener.add(listener);
	}

	public boolean removeClickedListener(ClickedListener listener) {
		return clickedListener.remove(listener);
	}

	private void notifyClickedListener() {
		for (ClickedListener listener : clickedListener) {
			listener.clicked(this);
		}
	}

	@Override
	public void mouseEntered(GComponent source) {
	}

	@Override
	public void mouseLeft(GComponent source) {
	}

	@Override
	public boolean mouseClicked(GComponent source, int button) {
		if (button == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseReleased(GComponent source, int button) {
		if (button == 0) {
			if (isHovered()) {
				notifyClickedListener();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseWheel(GComponent source, int delta) {
		return false;
	}

	@Override
	public boolean mouseMoved(GComponent source, int dx, int dy) {
		return false;
	}

	@Override
	public void onFocus() {
	}

	@Override
	public void onUnfocus() {
	}

}
