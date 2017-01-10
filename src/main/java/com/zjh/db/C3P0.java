package com.zjh.db;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {  
    
    private static ComboPooledDataSource ds =null;  
    //在静态代码块中创建数据库连接池  
    static{  
        try{  
            //通过代码创建C3P0数据库连接池  
            /*ds = new ComboPooledDataSource(); 
           ds.setDriverClass("com.mysql.jdbc.Driver"); 
           ds.setJdbcUrl("jdbc:mysql://localhost:3306/test"); 
            ds.setUser("root"); 
            ds.setPassword("123456"); 
            ds.setInitialPoolSize(10); 
            ds.setMinPoolSize(5); 
            ds.setMaxPoolSize(20);*/  
             
            //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下  
            
        	//ds = newComboPooledDataSource();//使用C3P0的默认配置来创建数据源  
            ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源  
             
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
     
   //从数据源中获取数据库连接  
    public static Connection getConnection(){  
        //从数据源中获取数据库连接  
        Connection conn=null;
        try {
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
        return conn;
    }  
     
    //释放链接  
    public static void closeConnection(Connection conn){  
        
        if(conn!=null){  
            try{  
                //将Connection连接对象还给数据库连接池  
                conn.close();  
            }catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  
