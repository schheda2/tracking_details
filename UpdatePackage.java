import java.io.*;
import java.sql.*;

public class UpdatePackage extends Thread {
	int packageID = 0;
/*
 *this thread updates the packages
 * 
 */
	
	public void run() {

		while (true) {

			String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "Crazy1993!";

			try {

				Connection con = DriverManager.getConnection(url, username,
						password);
				Statement st = con.createStatement();
				Matrix m = new Matrix();
				int source, destination;
				String s[] = m.getLoc();
					
					do {
						source = (int) (Math.random() * s.length);
						destination = (int) (Math.random() * s.length);
					} while (source == destination);

					// System.out.println(source+","+destination);

					dijkstra d = new dijkstra(source, destination);
					String src = d.getSource();
					String dst = d.getDest();
					

					int weight = (int) (Math.random() * 1000);
					int l = 10 + (int) (Math.random() * 100);
					int b = 10 + (int) (Math.random() * 100);
					int h = 10 + (int) (Math.random() * 100);
					String dimension = (l + " x " + b + " x " + h);

					st.executeUpdate("INSERT INTO PACKAGE_DETAIL VALUES(DEFAULT,'"
							+ src
							+ "',"
							+ "'"
							+ dst
							+ "',"
							+ weight
							+ ",'"
							+ dimension + "')");
					/*
					 * for (int i=0; i<count ; i++){ st.executeUpdate(
					 * "INSERT INTO PACKAGE_DETAIL VALUES(DEFAULT,'" +src+"'," +
					 * "'"+dst+"','"+Path[i]+"',now(),"+i+","+i+")"); }
					 */
					// System.out.println("after pack creation");
				
				con.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println(e.getMessage());
			}

			// selects 20 random packages and update details
			Packages p1[] = new Packages[20];

			try {

				// System.out.println("package array");
				int maximum = 1000;
				int min = 0;

				int randomNum = min + (int) (Math.random() * maximum);
				// System.out.println(randomNum);
				for (int i = 0; i < 20; i++) {
					p1[i] = new Packages(randomNum);
					p1[i].movetonxmode();
					randomNum++;
					// System.out.println("complete updation");
				}
				
				//for (int c = 0; c < 500; c++) {
					Thread.sleep(5000);
					//Thread.sleep(100000);
					//Thread.sleep(100000);
				//}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e);
			}
		}
	}

}
