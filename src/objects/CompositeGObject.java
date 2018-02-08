package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		for (GObject g : gObjects) {
			g.move(dX, dY);
		}
		this.x += dX;
		this.y += dY;
	}

	public void recalculateRegion() {
		int xMax = 0, xMin = gObjects.get(0).x, yMax = 0, yMin = gObjects.get(0).y;
		for (GObject g : gObjects) {
			if (g.x + g.width > xMax)
				xMax = g.x + g.width;
			if (g.y + g.height > yMax)
				yMax = g.y + g.height;
			if (g.x < xMin)
				xMin = g.x;
			if (g.y < yMin)
				yMin = g.y;
		}
		this.width = xMax - xMin;
		this.height = yMax - yMin;
		this.x = xMax - width;
		this.y = yMax - height;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject go : gObjects) {
			go.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Composite", x, y);
	}

}
