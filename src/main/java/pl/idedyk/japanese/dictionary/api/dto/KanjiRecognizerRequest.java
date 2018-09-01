package pl.idedyk.japanese.dictionary.api.dto;

import java.util.ArrayList;
import java.util.List;

public class KanjiRecognizerRequest {
	
	private int width;
	private int height;
	
	private List<List<Point>> strokes = new ArrayList<List<Point>>();
	
	public void newStroke() {
		strokes.add(new ArrayList<Point>());
	}
	
	public void addPoint(Integer x, Integer y) {
		strokes.get(strokes.size() - 1).add(new Point(x, y));
	}
	
	public List<List<Point>> getStrokes() {
		return strokes;
	}

	public void setStrokes(List<List<Point>> strokes) {
		this.strokes = strokes;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//

	public static class Point {
		
		private Integer x;
		
		private Integer y;
		
		public Point() { }

		public Point(Integer x, Integer y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}		
	}
}
