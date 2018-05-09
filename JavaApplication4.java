/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Mary
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sCurrentLine;
           String[] t1 = null;
           String[] t2=null;
           
           String[] keyset = null;
           int x= 0;
           int y=0;
        ArrayList<String> aclRule = new ArrayList<String>();
        ArrayList<String> ipAddress = new ArrayList<String>();
        try
        {
            BufferedReader acl = new BufferedReader(new FileReader("C:\\Users\\Mary\\Desktop\\acl2.txt"));
            BufferedReader ip = new BufferedReader(new FileReader("C:\\Users\\Mary\\Desktop\\ip2.txt"));
            
            
            while ((sCurrentLine = ip.readLine()) != null) {
                ipAddress.add(sCurrentLine);
                
            }
            while ((sCurrentLine = acl.readLine()) != null) {
               // aclStatements.add(sCurrentLine);
                String temp = sCurrentLine;
                if(temp.contains("access-list"))
                {aclRule.add(temp);}   
            }
            //System.out.print(aclStatements);
            ip.close();
            acl.close();
            }
        catch (Exception e)
        {
            System.out.println("Error!");
        }
        for (String ipl:ipAddress)
        {
        t1=ipl.split("\\s");
        
        }
        
        for (String line:aclRule)
        {
        t2=line.split("\\s");
        y=Integer.parseInt(t2[1]);}
        
        
      x=t1.length;
        
        
      if((x==1) && (y<=99))
      {       
        
                      boolean flag=true;
                      int c=0;
                      for(String ipl:ipAddress)
                      {                           
                      String[] ipts=ipl.split("\\.");
                      for(int j=0;j<aclRule.size();j++)
                      {     
                           String[] keysetS=aclRule.get(j).split("\\s");
                           String aclip=keysetS[3];
                           String[] keysplit = aclip.split("\\.");
                           String[] masksplit=keysetS[4].split("\\.");
                            for (int i=0;i<4;i++)
                            { 
                                if(masksplit[i].equals("0"))
                                {                          
                                if(!keysplit[i].contains(ipts[i]))
                                {   
                                 flag=false;
                                 break;
                                }
                                }
                                               
                            }
                          if((flag==true) && (keysetS[2].equals("permit")))
                           {
                              System.out.println(ipl+" Permit packet");
                              c++;
                              break;
                           }
                          else if ((flag==true) && (keysetS[2].equals("deny")))
                           {System.out.println(ipl+" Deny packet");
                           c++;
                           break;
                           }
                       
                         flag=true;                           
                      }
                      if(c==0)
                          System.out.println(ipl+" Deny packet");
                      c=0;
                          
                  }
                
      }
    
               
      else if(x>1 && (y>100))
      {
                  
        
                       boolean flag=true;
                       int c=0;
                       for(String ipl:ipAddress)
                      {String[] ipts=ipl.split("\\s");
                       String[] ipsrc=ipts[0].split("\\.");
                       String[] ipdes=ipts[1].split("\\.");
                      //for(String rule:aclStatements)
                       for(int j=0;j<aclRule.size();j++)
                      {
                          keyset = aclRule.get(j).split("\\s");
                          String[] keysplit = keyset[4].split("\\.");
                          String[] keysplit2 = keyset[6].split("\\.");
                          String[] masksplit1=keyset[5].split("\\.");
                          String[] masksplit2=keyset[7].split("\\.");
                           
                          for (int i=0;i<4;i++)
                          {
                              if(masksplit1[i].equals("0"))
                              {
                                  if(!(keysplit[i].equals(ipsrc[i])))
                                          {
                                              flag=false;
                                              break;
                                          }
                                  
                              }
                              if(masksplit2[i].equals("0"))
                              {
                                  if (!(keysplit2[i].equals(ipdes[i])))
                                      {
                                              flag=false;
                                              break;
                                          }
                                  
                               }
                           
                             switch(ipts[2])
                             {
                                 case ("ftp"):
                                     if(!(keyset[9].equals("20-21")))
                                         flag=false;
                                                 break;
                                 case ("http"):
                                     if(!(keyset[9].equals("80")))
                                         flag=false;
                                                 break;
                                 case ("https"):
                                     if(!(keyset[9].equals("443")))
                                         flag=false;
                                                 break;
                                 case ("telnet"):
                                     if(!(keyset[9].equals("23")))
                                         flag=false;
                                                 break;
                                 case ("ip"):
                                     if(!(keyset[9].equals("")))
                                         flag=false;
                                                 break;
                                 default:
                                     System.out.println("Not a standard rule");
                                     break;
                                 
                             }
                            
                          }
                         
                         
                          if(flag==true && keyset[2].equals("permit"))
                           {
                              System.out.println(ipl+" Permit packet");
                              c++;
                              break;
                           }
                          else if(flag==true && keyset[2].equals("deny"))
                           {System.out.println(ipl+" Deny packet");
                            c++;
                            break;
                           }
                          flag=true;
                      }
                       if(c==0)
                       {
                           System.out.println(ipl+" Deny packet");
                           c=0;
                       }
                           
                                                          
                       
                      }
                      
      
      }
                  
        else
        System.out.println("not a standard acl");
                          
                          
       }
                  
                
  }
    

