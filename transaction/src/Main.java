
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Statement;

interface Worker
{
	public void work();
}

class Dml
{	
	private static Connection conn;
	private static Statement stmt;	
	private String table;
	private String sql;
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<String> vals = new ArrayList<String>();
	private int rst;
	
	public Dml()
	{
		try 
		{
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://1.234.91.200:3306/TEST","test","1234");
			stmt = (Statement) conn.createStatement();				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public Dml(String table)
	{	
		System.out.println("4");
		this.table = table;
	}
	
	public void add(String key,String val)
	{		
		System.out.println("5");
		this.keys.add(key);
		this.vals.add("'"+val+"'");
	}
	
	public void insert()
	{
		System.out.println("6");
		sql =" INSERT INTO "+ table + "("+ 		Util.implode(",", keys) 	+")"+"VALUES("+		Util.implode(",", vals)		+")";		
		System.out.println(sql);	
	}
	
	public int execute()
	{		
		System.out.println("7");
		try 
		{
			rst = stmt.executeUpdate(sql);
			//stmt.close();
			//conn.close();
			System.out.println("8");
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	
		System.out.println("result="+rst);			
		return rst;
	}
	
	public void executeTransaction(Worker worker)
	{
		System.out.println("2");
		try 
		{			
			conn.setAutoCommit(false);
			worker.work();
			conn.commit();
		} 
		catch (SQLException e) 
		{		
			e.printStackTrace();
		}
	
	}
}
public class Main 
{		
	public Main()  
	{		
		Dml tran = new Dml();
		System.out.println("1");
		tran.executeTransaction(new Worker() 
		{
			@Override
			public void work() 
			{
				System.out.println("3");
				Dml dml = new Dml("member");
				dml.add("user_id","nice");
				dml.add("user_pw","1234");
				dml.insert();
				int rst = dml.execute();
				System.out.println("°á°ú:"+rst);				
			}
		});
	}
	
	public static void main(String[] args)  
	{
		new Main();	
	}
}
