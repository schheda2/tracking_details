import java.sql.*;

public class Packages {
	int package_id;
	String source, destination;
	String event[];
	String path[];
	int weight;
	String dimension;

	Packages(int package_id) {	// retrive package details from database
		
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";
		this.package_id = package_id;

		try {
			
			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM PACKAGE_DETAIL WHERE PACKAGE_ID="
							+ package_id);
			while (rs.next()) {
				source = rs.getString("source");
				destination = rs.getString("dest");
				weight = rs.getInt("weight");
				dimension =rs.getString("dimension");
			}

			dijkstra d = new dijkstra(source, destination);
			path = d.getPath();			// retrieves the shortest path as calculated by dijkstra algorithm
			
			
		

		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}

	void movetonxmode() {
		
		/*
		 * this function updates the package and moves it to next edge in the path
		 * 
		 */
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";

		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement st = con.createStatement();
			
			//Query finds the current position of the package denoted by status = 1 
			ResultSet rs = st
					.executeQuery("SELECT route_no FROM PACKAGE_STATUS WHERE PACKAGE_ID ="
							+ package_id + " AND status=1");   
			//System.out
				//	.println("SELECT route_no FROM PACKAGE_STATUS WHERE PACKAGE_ID ="
					//		+ package_id + " AND status=1");

			if (rs.next()) {
				//query updates the package to move to next node and changes nodes accordingly
				int routeNo = rs.getInt(1);
				st.executeUpdate("UPDATE PACKAGE_STATUS SET status = 2 WHERE route_no="
						+ routeNo);
				routeNo++;
				st.executeUpdate("UPDATE PACKAGE_STATUS SET package_time=now(), status=1 WHERE route_no = "
						+ routeNo);
			} else {
				/*
				 * this creates a new entry in package_status table for packages which are still not 
				 * dispatched from the source
				 * 
				 * it stores the current position as well as the path of the package in route
				 */
					for (int i = 0; i < path.length; i++) {
						if (i == 0) {
							st.executeUpdate("INSERT INTO PACKAGE_STATUS VALUES("
									+ package_id
									+ ",'"
									+ path[i]
									+ "',now(),"
									+ 1 + "," + i + ")");
						} else {
							st.executeUpdate("INSERT INTO PACKAGE_STATUS VALUES("
									+ package_id
									+ ",'"
									+ path[i]
									+ "','00:00:00'," + 0 + "," + i + ")");
						}
				
				}
			}

			//System.out.println("aftr null");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}
	}

	void getDetails() {
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";
		int s = this.package_id;

		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from PACKAGE_STATUS WHERE PACKAGE_ID="
							+ s);
			ResultSetMetaData ms = rs.getMetaData();
			int e = ms.getColumnCount();
			int flag = 0;
			
			while (rs.next()) {
				 
					System.out.print(" Location : "+rs.getString(2));
					System.out.print(" TIME : "+rs.getString(3));
					int stat = rs.getInt(4);
					if (stat==1){
						flag=1;
						System.out.print(" Package is currently here");
					}else if(stat==2){
						flag=2;
						System.out.print(" Package dispatched");
					}else if (stat == 0){
						flag =1;
						System.out.print(" Package on the way");
					}
					System.out.println();
					
			}
			
			/*
			 * the if statement prints out if he package is delicvered or not
			 */
			if(flag == 2){
				System.out.println(" Package Delivered to location");
			}
			else
				System.out.println("Package not delivered to destination");
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}
	}
}
