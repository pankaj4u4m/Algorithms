package fb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Dfs {

	private static Map<Integer, Boolean> isVisited;
	static{
		isVisited = new HashMap<Integer, Boolean>();
	}
	public static void dfs(Map<Integer, List<Integer>> graph){
		if(graph == null || graph.isEmpty()){
			return;
		}
		for(Entry<Integer, List<Integer>> entry : graph.entrySet()){
			Boolean val = isVisited.get(entry.getKey());
			if(val == null || !val){
				isVisited.put(entry.getKey(), true);
				dfsVisit(entry.getKey(), graph);
			}
		}
	}
	private static void dfsVisit(Integer key, Map<Integer, List<Integer>> graph) {
		for(Integer entry : graph.get(key)){
			Boolean val = isVisited.get(entry);
			if(val == null || !val){
				isVisited.put(entry, true);
				dfsVisit(entry, graph);
			}
		}
		
	}
}
