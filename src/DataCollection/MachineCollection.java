package DataCollection;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;

import com.wl.tools.Sqlhelper;


public  class MachineCollection implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("tomcat shutdown2222******");		
	}

	public void contextInitialized(ServletContextEvent sce) {

		new Thread(new QueryListener()).start();		
		System.out.println("tomcat startup22222*******");
	}

	private class QueryListener implements Runnable{

		public void run() {
			System.out.println("服务器主线程启动2222");
			
//			 String host = "192.168.11.190";  
//			 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
//			 String progId = "OPC.SINUMERIK.Machineswitch";  
			 String user = "auduser";  
			 String password = "SUNRISE"; 
			 String host = "192.168.11.190";  
			 String domain = "192.168.11.190"; //SIEMENS-789BE42 SIEMENS-789BE42 SIEMENS-789BE42(this computer)
			 String progId = "OPC.SINUMERIK.Machineswitch";
			 //ServerList serverList = new ServerList(host,user,password,domain);  
			 System.out.println("111111111111111111111111111111");
			 //showAllOPCServer(serverList); 
			 
			 final ConnectionInformation ci = new ConnectionInformation();  
			 ci.setHost(host);  
	    	 ci.setProgId(progId);  //serverList.getClsIdFromProgId (progId)
		    // System.out.println("11111111111"+serverList.getClsIdFromProgId (progId));
	    	 ci.setClsid("75d00afe-dda5-11d1-b944-9e614d000000");
	    	 ci.setDomain(domain);
			 ci.setUser(user);  
			 ci.setPassword(password);
			 
			 ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();  
			 Server server = new Server(ci, exec);  
			 try{
				 server.connect();
				
			     syncWrite(server);}catch(Exception e ){
			 }
	    }
	}
		
	
	public static void syncWrite(Server server) throws Exception{  
	    final String itemId1="/Channel/MachineAxis/aavactB[1]";  
	    final String itemId2="/Channel/MachineAxis/actToolBasePos[u1,1]"; 
	    final String itemId3="/Bag/State/opMode[u1]"; 
	    
	    
	    
	    Group group = server.addGroup("test");  
	    Item item1 = group.addItem(itemId1);
	    Item item2 = group.addItem(itemId2); 
	    Item item3 = group.addItem(itemId3); //get item for writing  
	      
	    //第一次读  
	   while(true){
	    	  ItemState itemState1 = item1.read(true); 
	    	  ItemState itemState2 = item2.read(true); 
	    	  ItemState itemState3 = item3.read(true); 
	    	  
	  	    System.out.println("<<< first read: " + itemState1.getValue()); 
	  	    System.out.println("<<< first read: " + itemState2.getValue()); 
	  	    System.out.println("<<< first read: " + itemState3.getValue()); 
	  	    Thread.sleep(1000);
	  	    int ddd = itemState2.getValue().toString().length();
	  	    String aaa = itemState1.getValue().toString().substring(2,itemState1.getValue().toString().length()-2);
	  	    String aab = itemState2.getValue().toString().substring(2,itemState2.getValue().toString().length()-2);
	  	    //String Sql1="insert into DATACOLLECTION(X_AXIS_FEED_SPEED,X_AXIS_COORDINATES) values('"+aaa+"','"+aab+"')";
	  	    String Sql1="update DATACOLLECTION SET X_AXIS_FEED_SPEED='"+aaa+"',X_AXIS_COORDINATES='"+aab+"'WHERE ID=1";
	  	    try{
	  	    	Sqlhelper.executeUpdate(Sql1, null);
			}catch(Exception e){
				System.out.println("未知错误哈哈哈");
			}
			System.out.println("更新成功");
			  
			
			
			//查询已经插入的条数
			int totalCount = 0;
			String sql2 = "select count(*) from DATACOLLECTION";
			try {
				 totalCount = Sqlhelper.exeQueryCountNum(sql2, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (totalCount >100)
			{
				String sql3 = "";
			}
			
				
			
	  	  
	    }
	}
}
	
//	private class HandleQuery extends Thread  {
//		private Socket socket;
//		public HandleQuery(Socket socket) {
//			super();
//			this.socket = socket;
//		}		
//		@Override
//		public void run() {
//			
//			try {
//				BufferedReader br=null;
//				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
//				StringBuilder sb = new StringBuilder();  
//				String temp;  
//				int index;  
//				while ((temp=br.readLine()) != null) {  
//					System.out.println(temp);  
//					if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
//						sb.append(temp.substring(0, 15));  
//						break;
//					}  
//					
//					sb.append(temp);  
//				}  
//				System.out.println("from client: " + sb); 
//				
//
//				String rfid1=socket.getInetAddress().toString();
//				String barCodeSql="insert into RFIDBADCODE(RFIDCODE,IP) values('"+sb+"','"+rfid1+"')";
//				try{
//					Sqlhelper.executeUpdate(barCodeSql, null);
//				}catch(Exception e){
//					System.out.println("违反唯一约束");
//				}
//				System.out.println("11111"+barCodeSql);
//}