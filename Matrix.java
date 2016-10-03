import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class Matrix {
	
	private int graph[][];

	private int noOfLoction;
	
	private String loc[];
	
	Matrix(){
		String url = "jdbc:mysql://localhost:3306/internetofthings?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Crazy1993!";
		
		
		try{	
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			String sql = "SELECT * FROM distance";
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			int matrix[][] = new int[colNum-1][colNum-2];
			String loc[] = new String[colNum-2];
			int j=0;
			
			//System.out.println(colNum);
			
			
			
			while (rs.next()){
				for (int i=3;i<=colNum;i++){
					loc[j] = rs.getString(2);
					matrix[i-3][j]=rs.getInt(i);
				}
				j++;
			}
			
			this.noOfLoction = j;
			this.graph = matrix;
			this.loc = loc;
			
			
			con.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}
	
	public int getNoOfLoction() {
		
		return noOfLoction;
	}
	
	public String[] getLoc(){
		
		return this.loc;
	}

	public int[][] getGraph(){
		
		return graph;
	}
	
	
}
