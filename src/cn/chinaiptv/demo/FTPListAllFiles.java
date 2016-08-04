package cn.chinaiptv.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import java.util.Iterator;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;




public class FTPListAllFiles {
public FTPClient ftp;  
   public ArrayList<String> arFiles;  
 
    public FTPListAllFiles(){  
        ftp = new FTPClient();  
        arFiles = new ArrayList<String>();    
   }  
      
 
    public boolean login(String host,int port,String username,String password) throws IOException{  
        this.ftp.connect(host,port);  
        if(FTPReply.isPositiveCompletion(this.ftp.getReplyCode())){  
            if(this.ftp.login(username, password)){  
                this.ftp.setControlEncoding("GBK");  
                return true;  
            }  
        }  
        if(this.ftp.isConnected()){  
            this.ftp.disconnect();  
        }  
        return false;  
    }  
 
    public void disConnection() throws IOException{  
        if(this.ftp.isConnected()){  
            this.ftp.disconnect();  
        }  
    }  
 
    public void List(String pathName) throws IOException{  
        if(pathName.startsWith("/")&&pathName.endsWith("/")){  
            String directory = pathName;  
            //更换目录到当前目录  
            this.ftp.changeWorkingDirectory(directory);  
            FTPFile[] files = this.ftp.listFiles();  
            for(FTPFile file:files){  
                if(file.isFile()){  
                    arFiles.add(directory+file.getName());  
                }else if(file.isDirectory()){  
                    List(directory+file.getName()+"/");  
                }  
            }  
       }  
    }  
 
    public void List(String pathName,String ext) throws IOException{  
        if(pathName.startsWith("/")&&pathName.endsWith("/")){  
            String directory = pathName;  
            //更换目录到当前目录  
            this.ftp.changeWorkingDirectory(directory);  
            FTPFile[] files = this.ftp.listFiles();  
            for(FTPFile file:files){  
               if(file.isFile()){  
                    if(file.getName().endsWith(ext)){  
                        arFiles.add(directory+file.getName());  
                    }  
               }else if(file.isDirectory()){  
                    List(directory+file.getName()+"/",ext);  
                }  
            }  
        }  
    }  
    public static void main(String[] args) throws IOException {  
        FTPListAllFiles f = new FTPListAllFiles();
        if(f.login(GlobalParams.IPADDRESS, GlobalParams.PORT, GlobalParams.USERNAME, GlobalParams.PWD)){  
            f.List("/tars/");  
        }  
        f.disConnection();  
        Iterator<String> it = f.arFiles.iterator();  
        while(it.hasNext()){  
        	System.out.println(it.next());
        }  
         
   }  
}  

