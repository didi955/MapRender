package fr.didi955.maprender.helpers;

import java.util.Iterator;

import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class RenderHelper {

	public static MapView resetRenderers(MapView map) {

		for (MapRenderer mapRenderer : map.getRenderers()) {
			map.removeRenderer(mapRenderer);
		}
		
		return map;
	}
	
}
