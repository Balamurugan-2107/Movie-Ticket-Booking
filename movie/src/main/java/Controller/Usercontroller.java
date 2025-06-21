package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Model.Usermodel;
import Utility.Dbconnect;

public class Usercontroller {
	Scanner sc=new Scanner(System.in);
	public Usercontroller() {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			Statement yx=conn.createStatement();
			yx.executeUpdate("create table if not exists users(username varchar(20),userpassword varchar(20),phone varchar(15));");
			yx.executeUpdate("create table if not exists bookings(username varchar(20),movieid int,moviename varchar(100),time varchar(10),rate int,seat int);");
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void register(Usermodel u) {
		try {
			Dbconnect y=new Dbconnect();
			Connection conn=y.connect();
			PreparedStatement pst=conn.prepareStatement("insert into users(username,userpassword,phone) values(?,?,?)");
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getUserpassword());
			pst.setString(3, u.getPhone());
			pst.executeUpdate();
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public Usermodel login(Usermodel u) {
	    Usermodel x = null;
	    try {
	        Dbconnect y = new Dbconnect();
	        Connection conn = y.connect();
	        PreparedStatement pst = conn.prepareStatement( "select * from users where (username=? or phone=?) and userpassword=?");
	        pst.setString(1, u.getUsername());  
	        pst.setString(2, u.getUsername());     
	        pst.setString(3, u.getUserpassword()); 

	        ResultSet yx = pst.executeQuery();
	        if (yx.next()) {
	            x = new Usermodel();
	            x.setUsername(yx.getString(1));
	            x.setUserpassword(yx.getString(2));
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return x;
	}
	public void success(Usermodel u) {
		while(true) {
			System.out.println("========= Movie Menu =========");
			System.out.println("1.VIEW MOVIES\n2.BOOK TICKETS\n3.VIEW MY BOOKINGS\n4.LOGOUT");
			int i=sc.nextInt();
			if(i==1) {
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
			else if(i==2) {
				try {
					System.out.print("Enter Movie ID to book: ");
					int movieId = sc.nextInt();

					Dbconnect db = new Dbconnect();
					Connection conn = db.connect();
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM movies WHERE id=?");
					pst.setInt(1, movieId);
					ResultSet rs = pst.executeQuery();

					if(rs.next()) {
					    
					    System.out.println("You selected:");
					    System.out.println("Name: " + rs.getString("moviename"));
					    System.out.println("Time: " + rs.getString("time"));
					    System.out.println("Rate: " + rs.getInt("rate"));

					    
					    System.out.print("Enter how many Seats: ");
					    int seat = sc.nextInt();

					    
					    PreparedStatement pst2 = conn.prepareStatement("INSERT INTO bookings(username, movieid, moviename, time, rate, seat) VALUES (?, ?, ?, ?, ?, ?)");
					    pst2.setString(1, u.getUsername());  
					    pst2.setInt(2, movieId);
					    pst2.setString(3, rs.getString("moviename"));
					    pst2.setString(4, rs.getString("time"));
					    pst2.setInt(5,(rs.getInt("rate"))*seat);;
					    pst2.setInt(6, seat);
					    pst2.executeUpdate();

					    System.out.println("Ticket Booked Successfully!");
					} else {
					    System.out.println("Invalid Movie ID. Please try again.");
					}

					conn.close();	
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
			else if(i==3) {
				try {

					Dbconnect db = new Dbconnect();
					Connection conn = db.connect();
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM bookings where username=?");
					pst.setString(1, u.getUsername());
					ResultSet rs = pst.executeQuery();
					if(rs==null) {
						System.out.println("No bookings found for " + u.getUsername());
					}
					while(rs.next()) {
						System.out.println("Your Bookings:");
					    System.out.println("Movie Name: " + rs.getString(3));
					    System.out.println("Time: " + rs.getString(4));
					    System.out.println("Price: " + rs.getInt(5));
					    System.out.println("Seats Availed: "+ rs.getInt(6));
					}
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
			else if(i==4) {
				break;
			}
			else {
				System.out.println("Invalid option.Please Try again...");
			}
		}
	}

}
