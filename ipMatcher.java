// Author: Mark Miller 
// Organization: Keene State College

package sp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

 import org.jnetpcap.*;
import org.jnetpcap.packet.*;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.util.PcapPacketArrayList;
import org.jnetpcap.packet.format.FormatUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

public class ipMatcher {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  Statement stmt = null;
			Class.forName("org.gjt.mm.mysql.Driver");
			String url = "jdbc:mysql://localhost/packet"; // Change the name of your Database here. Note: Packet is the name of the database and localhost is the url
			String dbpass = ""; // Database password. Here, there is no password for the database.
			String dbuser = "root"; // Username for the database. 
		  final Connection conn = DriverManager.getConnection(url, dbuser, dbpass); //Connect
		  final ArrayList<String> list1 = new ArrayList<String>(); // A list from String
		  final ArrayList<String> list2 = new ArrayList<String>(); // another list for Strings
		  
		try{
		   	   Scanner scan = new Scanner(System.in); // Scanner to take in the name of the database you want to create. 
			   System.out.println("Input the DB name: ");
			   String tableName2 = scan.next();
			   Userinput.setTableName2(tableName2);
			   stmt = conn.createStatement();
			   String tableName = Userinput.getTableName();
			 
	      //Creates a mySQL table if the name table name specified does not exist
	      String sql = "CREATE TABLE IF NOT EXISTS " + tableName2 + 
	                   " (site VARCHAR(255), " +
	                   " sourceip VARCHAR(255), " + 
	                   " destip VARCHAR(255), " + 
	                   " mac VARCHAR(255))";
	     stmt.executeUpdate(sql);
	     PreparedStatement Statement = null;
	     ResultSet result1 = null;
		 Statement = conn.prepareStatement("Select * FROM known"); // Creates a result set of the IP addresses stored in the known database.
   	     result1 = Statement.executeQuery(); 
   	  
		while (result1.next()) // While loop filles list1 with the current IP address pointed at in the result set. List 2 is filled with the name of the social media website. 
		  {
			  String known = result1.getString("address"); 
			  String name = result1.getString("website");
			  list1.add(known);
			  list2.add(name);
		  }
			
			  conn.close();
		}
		catch (Exception err){
			err.printStackTrace();
		}

	
	 String filename = " "; // Change the number of the packet here, include .pcap
	 StringBuilder error = new StringBuilder();
	 
	
	 final Tcp tcp = new Tcp();  
     final Http http = new Http(); 
	 final Ip4 ip= new Ip4();
	 final Ethernet ether = new Ethernet();
	 Pcap pcap = Pcap.openOffline(filename, error); 
	 
	 if (pcap == null)
	 {
		 throw new Exception(error.toString());
	 }
	
	 PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() 
			 {
				@SuppressWarnings("null")
				int https = 0;
				public void nextPacket(PcapPacket packet, String arg1) 
				{	
					  byte[] sourceIP = new byte[4];  
					  byte[] destinationIP = new byte[4];  
					  int portsource = 0;
					  int portdest = 0;
				      boolean same = false;
				      String sourceIP2 = " ";
				      String name2 = " ";
					  if (packet.hasHeader(ip) == false)
					  {  
					    return; // Not IP packet  
					  }
					  if (packet.hasHeader(tcp))
					  {
						  portsource = tcp.source(); // Sets the source port
		                  portdest = tcp.destination(); // sets the destination port
					  if (portsource == 443 || portdest == 443) // Check to see if the packet uses HTTPS protocl
					  {
						  https++; // Counter for the number of HTTPS packets
					  }
					  		
					      packet.getHeader(tcp);  
						  ip.destinationToByteArray(destinationIP);
						  ip.sourceToByteArray(sourceIP);
		                  String sourceIPFormatted = org.jnetpcap.packet.format.FormatUtils.ip(sourceIP);  // Formats the source IP into a readable ip address such as 192.168.1.1
		                  String destinationIPFormatted = org.jnetpcap.packet.format.FormatUtils.ip(destinationIP); // Formats the destination IP into a readable ip address such as 192.168.1.1 
		                  
		                  
		                  Iterator it =  list1.iterator();// Iterator that is used to iterate over the IP addresses inlist1 (which are from the known DB)
		                  int index= 0; // Loop Counter for below
			                while (it.hasNext()) //While loop that shortens the source IP address to the length of the current known database object
			                {					 //Example: 31.13.71.33 Source IP is being checked against 31.13.71, so the source ip becomes 31.13.71
			                  String known = (String) it.next(); // sets known to the current name IP address stored in list1
			                  String name = list2.get(index); // Sets name to the name of the social media website being pointed
			           		  String sourceIP1 = sourceIPFormatted; // Sets sourceIP1 to the source ip address
			           		  int temp = known.length(); // this temp variable is the length of the ip address from the database
			           		  int temp2 = sourceIPFormatted.length(); // Second temp value is the length of the source ip address
			           		  int diff = temp2 - temp; //Finds the differences on the lengths
			           		  diff = Math.abs(diff); //ensures that the difference will never be a negative, otherwise the substring can go out of bounds. 
			           		  int temp3 = temp2 - diff; // Temp 3 becomes the source IP of the packet minus the difference in the address lengths.
			           		  sourceIP1 = sourceIP1.substring(0, temp3);	// Sets the source IP address to be the same size as the known ip address for comparison
			           		  if (sourceIP1.equals(known)) // If the two ip addresses (the one from the packet and the one from the known database) as the same, a flag is set to true
			           		  {
			           			 same = true; // Boolean flag variable
			           			 name2 = name; // sets a name variable equal to the name of the social media website found
			           		  }
			           		  index++; // loop counter that is used to get the name above
			                }
									if (packet.hasHeader(ether)) // Checks to make sure the packet has an ethernet header.
									{
										String mac = org.jnetpcap.packet.format.FormatUtils.mac(ether.destination()); // makes a readable mac address 
										if (packet.hasHeader(tcp)) // Checks to make sure the packet has a TCP header
										{
											 if (same) // if the source ip of the packet matches an IP from the database, we write information to our database. 
											 {
													  Db.writeToDb(name2, sourceIPFormatted, destinationIPFormatted, mac);    
											  }	  
											
										}	  
									}			   
					  }					
				};
		 
			 };
			  
					 try
					 	 {  
				            pcap.loop(-1, jpacketHandler, "test");  // -1 indicates that every packet from the pcap file will be read. Change to the number of packets you want to analyze. 
				            
				         } 
					 finally
				         {  
				           pcap.close();  // Close the pcap file. 
				         } 
	}
				
}
