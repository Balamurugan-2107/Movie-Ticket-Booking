package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Model.Adminmodel;
import Model.Moviemodel;
import Utility.Dbconnect;

public class Admincontrol {
	Scanner sc=new Scanner(System.in);
	public Admincontrol() {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			Statement yx=conn.createStatement();
			yx.executeUpdate("create table if not exists admins(adminname varchar(50),adminpassword varchar(20));");
			yx.executeUpdate("create table if not exists movies(id int,moviename varchar(100),time varchar(10),rate int);");
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void Admin(Adminmodel a) {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			PreparedStatement pst=conn.prepareStatement("insert into admins(adminname,adminpassword) values(?,?)");
			pst.setString(1,a.getAdminname());
			pst.setString(2,a.getAdminpassword());
			pst.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public Adminmodel check(Adminmodel c) {
		Adminmodel x=null;
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			PreparedStatement pst=conn.prepareStatement("select * from admins where adminname=? and adminpassword=?");
			pst.setString(1, c.getAdminname());
			pst.setString(2, c.getAdminpassword());
			ResultSet yx=pst.executeQuery();
			if(yx.next()) {
				x=new Adminmodel();
				x.setAdminname(yx.getString(1));
				x.setAdminpassword(yx.getString(2));
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return x;
	}
	public void success(Moviemodel m) {
		while(true) {
			System.out.println("=========================== MENU ===========================\n1.ADD\n2.VIEW\n3.DELETE");
			int i=sc.nextInt();
			if(i==1) {
				System.out.println("Enter the MOVIE ID: ");
				m.setId(sc.nextInt());
				System.out.println("Enter the MOVIE NAME: ");
				m.setMoviename(sc.next());
				System.out.println("Enter the MOVIE TIME: ");
				m.setTime(sc.next());
				System.out.println("Enter the MOVIE RATE: ");
				m.setRate(sc.nextInt());
				add(m);
			}
			else if(i==2) {
				view(m);
			}
			else if(i==3) {
				System.out.println("Enter Movie Id to be delete: ");
				int id=sc.nextInt();
				delete(id);
			}
			else {
				break;
			}
		}
	}
	public void add(Moviemodel m) {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			PreparedStatement pst=conn.prepareStatement("insert into movies(id,moviename,time,rate) values(?,?,?,?)");
			pst.setInt(1,m.getId());
			pst.setString(2,m.getMoviename());
			pst.setString(3,m.getTime());
			pst.setInt(4, m.getRate());
			pst.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void view(Moviemodel m) {
		try {
	        Dbconnect y = new Dbconnect();
	        Connection conn = y.connect();
	        PreparedStatement pst = conn.prepareStatement("SELECT * FROM movies");
	        ResultSet rs = pst.executeQuery();
	        
	        System.out.println("------- Movie List -------");
	        while(rs.next()) {
	            System.out.print("ID: " + rs.getInt("id")+"||");
	            System.out.print("Name: " + rs.getString("moviename")+"||");
	            System.out.print("Time: " + rs.getString("time")+"||");
	            System.out.println("Rate: " + rs.getInt("rate"));
	            System.out.println("-------------------------");
	        }
	        
	        conn.close();
	    }
	    catch(Exception e) {
	        System.out.println(e);
	    }
	}
	public void delete(int id) {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn =y.connect();
			PreparedStatement pst=conn.prepareStatement("delete from movies where id=?");
			pst.setInt(1, id);
			int a=pst.executeUpdate();
			if(a>0) {
				System.out.println("Deleted Successfull.");
			}
			else {
				System.out.println("Movie not found.");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
