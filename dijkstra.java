public class dijkstra {

	private String Path[];
	private String source, dest;
	private int sourceNode, destNode;

	public String getSource() {
		return source;
	}

	public String getDest() {
		return dest;
	}

	dijkstra(String src, String dst) {
		Matrix m = new Matrix();
		String loc[] = m.getLoc();
		int noOfNodes = m.getNoOfLoction();
		for (int i = 0; i < noOfNodes; i++) {
			if (src.equals(loc[i])) {
				this.sourceNode = i;
			}
			if (dst.equals(loc[i])) {
				this.destNode = i;
			}
		}
		dijkstra_algo();
	}

	dijkstra(int src, int dst) {
		this.sourceNode = src;
		this.destNode = dst;
		dijkstra_algo();
	}

	private void dijkstra_algo() {
		Matrix m = new Matrix();
		int matrix[][] = m.getGraph();
		int noOfNodes = m.getNoOfLoction();
		String loc[] = m.getLoc();
		this.source = loc[sourceNode];
		this.dest = loc[destNode];

		

		int[] distance = new int[noOfNodes];
		int[] visited = new int[noOfNodes];
		int[] prevNode = new int[noOfNodes];
		int min;
		int nextNode = 0;

		distance = matrix[sourceNode]; // finding shortest path for node
		visited[sourceNode] = 1;

		for (int i = 0; i < noOfNodes; i++) {
			prevNode[i] = sourceNode;
		}

		for (int i = 0; i < noOfNodes; i++) {
			min = 999;
			for (int j = 0; j < noOfNodes; j++) {
				if (min > distance[j] && visited[j] != 1) {
					min = distance[j];
					nextNode = j;
				}
			}

			visited[nextNode] = 1;

			for (int c = 0; c < noOfNodes; c++) {
				if (visited[c] != 1) {
					if (min + matrix[nextNode][c] < distance[c]) {
						distance[c] = min + matrix[nextNode][c];
						prevNode[c] = nextNode;

					}
				}
			}
		}

		/*
		 * shows the final result of dijkstra algorithm for (int i = 0; i <
		 * noOfNodes; i++) { System.out.print("|" + distance[i]); }
		 */

		// shows the shortest path
		int j;
		int route[] = new int[15];
		// System.out.println(s);
		j = destNode;
		route[0] = destNode;
		int c = 1;

		do {
			j = prevNode[j];
			route[c] = j;
			c++;
		} while (j != sourceNode);
		//

		String s[] = new String[c];
		for (int i = 0; i < c; i++) {
			s[c - i - 1] = loc[route[i]];
			// System.out.println(route[i]);
		}
		this.Path = s;
	}

	public String[] getPath() {
		// TODO Auto-generated method stub

		return Path;
	}

}
