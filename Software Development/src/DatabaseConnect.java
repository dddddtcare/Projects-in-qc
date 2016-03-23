/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import javax.swing.*;

public class DatabaseConnect {
        Connection conn = null;
       public static Connection ConnerDb(){
           try{
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/","root","root");  
             
             // JOptionPane.showMessageDialog(null,"Connection Establishes");
              return conn;
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, e);
               return null;
           }
       }
        
}
