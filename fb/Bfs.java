package fb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Bfs {

	public static void bfs(Map<Integer, List<Integer>> graph){
		 Map<Integer, Boolean>  isVisited =  new HashMap<Integer, Boolean>();
		 Queue<Pair<Integer, Integer>> queue = new PriorityQueue<Pair<Integer, Integer>>();
		 
		 
		 queue.add(new Pair<Integer, Integer>(0, 0));
		 while(!queue.isEmpty()){
			 Pair<Integer, Integer> top = queue.poll();
			 Integer v = top.getFirst();
			 Integer d = top.getSecond();
			 for(Integer neighbour : graph.get(v)){
				 Boolean val = isVisited.get(neighbour);
				 if(val == null || !val){
					 isVisited.put(neighbour, true);
					 queue.add(new Pair<Integer, Integer>(neighbour, d + 1));
				 }
			 }
		 }
	}
}
