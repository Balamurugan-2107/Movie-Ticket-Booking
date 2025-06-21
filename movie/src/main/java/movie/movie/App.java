package movie.movie;

import java.util.Scanner;

import Controller.Admincontrol;
import Controller.Usercontroller;
import Model.Adminmodel;
import Model.Moviemodel;
import Model.Usermodel;
public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc=new Scanner(System.in);
    	Admincontrol cm=new Admincontrol();
		Adminmodel a=new Adminmodel();
		Moviemodel m=new Moviemodel();
		Usermodel u=new Usermodel();
		Usercontroller um=new Usercontroller();
		System.out.println("******************* WELCOME TO MOVIE TICKET BOOKING APP *******************");
		while(true) {
	    	System.out.println("\n1.Admin Sign Up/Sign In\n2.User Sign Up/Sign In\n");
	    	int i=sc.nextInt();
	    	if(i==1) {
	    		System.out.println("******************* ADMIN MENU *******************");
	    		while(true) {
		    		System.out.println("1.Sign Up\n2.Sign In\n3.Back to Main Menu");
		    		int b=sc.nextInt();
		    		if(b==1) {
		    			System.out.println("Enter the Adminname: ");
		        		a.setAdminname(sc.next());
		        		System.out.println("Enter the Password: ");
		        		a.setAdminpassword(sc.next());
		        		System.out.println("*************** SIGN UP SUCCESSFULL ***************");
		        		cm.Admin(a);
		    		}
		    		else if(b==2) {
		    			System.out.println("Enter the Adminname: ");
		        		a.setAdminname(sc.next());
		        		System.out.println("Enter the Password: ");
		        		a.setAdminpassword(sc.next());
		        		
		        		a=cm.check(a);
		        		if(a!=null)
		         	   {
		        			System.out.println("*************** SIGN IN SUCCESSFULL ***************");
		         		   cm.success(m);
		         	   }
		         	   else
		         	   {
		         		   System.out.println("*************** SIGN IN FAILED ***************");
		         	   }
		    		}
		    		else if (b == 3) {
		                break; 
		            }
		    		else {
		                System.out.println("Invalid Choice. Try Again...");
		            }
	    		}
	    	}
	    	else if(i==2) {
	    		System.out.println("******************* USER MENU *******************");
	    		while(true) {
	    			System.out.println("1.Sign Up\n2.Sign In\n3.Back to Main Menu");
		    		int b=sc.nextInt();
		    		if(b==1) {
		    			System.out.println("Enter the User Name: ");
		        		u.setUsername(sc.next());
		        		System.out.println("Enter the Phone Number: ");
		        		u.setPhone(sc.next());
		        		System.out.println("Enter the Password: ");
		        		u.setUserpassword(sc.next());
		        		System.out.println("*************** SIGN UP SUCCESSFULL ***************");
		        		um.register(u);
		    		}
		    		else if(b==2) {
		    			u = new Usermodel();
		    			System.out.println("Enter the User Name or Phone Number: ");
		    			String g=sc.next();
		    			System.out.println("Enter the Password: ");
		    			String h=sc.next();
		        		u.setUsername(g);
		        		u.setUserpassword(h);
		        		u=um.login(u);
		        		if(u!=null) {
		        			System.out.println("*************** SIGN IN SUCCESSFULL ***************");
		        			um.success(u);
		        		}
		        		else {
		        			System.out.println("*************** SIGN IN FAILED ***************");
		        		}
		    		}
		    		else if(b==3) {
		    			break;
		    		}
		    		else {
		    			System.out.println("Invalid option.Please Try again...");
		    		}
	    		}
	    	}
		}
    }
}
