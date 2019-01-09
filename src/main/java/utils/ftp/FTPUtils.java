package utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class FTPUtils {
	  /**
     * 
     * @param ftp_ip host
     * @param ftp_port port
     * @param ftp_user
     * @param ftp_pwd
     * @param filename
     * @param ftpFilePath
     * @param b   filename内容
     * @return
     */
    public static boolean ftpUploadFile(String ftp_ip, Integer ftp_port,String ftp_user, String ftp_pwd,
             String ftpFilePath, String filename,String b) {
        if (null == ftp_port) {
            ftp_port = 21;
        }
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(b.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            
            e.printStackTrace();
        }
        boolean b2=ftpUploadFile(ftp_ip, ftp_port, ftp_user, ftp_pwd, ftpFilePath,filename, is);
            
        if(b2){
            System.out.println("上传FTP文件成功！！！上传到FTP文件名" + ftpFilePath+ filename);
            return true;
        }else{
            System.out.println("上传失败！！！上传到FTP文件名" + ftpFilePath+ filename);
            return false;
        }
        
        
    }
    
    /**
     * 
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param path
     * @param filename
     * @param is
     * @return
     */
    public static boolean ftpUploadFile(String ip, int port, String username, String password, 
            String path, String filename, InputStream is) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(ip, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            
            
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1") , is);
//          ftp.enterLocalPassiveMode();
//          ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//          ftp.storeFile(filename, input);
//          ftp.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1") , input);

            is.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                    System.out.println("-----关闭ftp连接-----");
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
    
    
    /**
	 * 
	 * @param ftpurl
	 *            FTP目录
	 * @param ftpport
	 *            不传默认21
	 * @param ftpuser
	 *            FTP用户名
	 * @param ftppwd
	 *            FTP 密码
	 * @param localfilepath
	 *            要上传的本地文件目录
	 * @param filename
	 *            要上传的FTP文件
	 * @param ftpFilePath
	 *            上传到FTP的文件路径
	 * 
	 * @return
	 */
	public static boolean uploadFileToFtpdir(String ftpurl, Integer ftpport,
			String ftpuser, String ftppwd, String localfilepath,
			String filename, String ftpFilePath) {
		if (null == ftpport) {
			ftpport = 21;
		}
		String name="";
		if(filename.indexOf("/")>0){
			name=filename.substring(filename.lastIndexOf("/")+1, filename.length());
		}else{
			name=filename;
		}
		
		String localFile = localfilepath + filename;
		File file = new File(localFile);
		
		if (file.exists()) {
			if (file.isDirectory()) {
				System.out.println("要上传的文件不是一个文件！！！" + localFile);
				return false;
			}

			InputStream input = null;
			try {
				input = new FileInputStream(file);
				
				uploadFile(ftpurl, ftpport, ftpuser, ftppwd, ftpFilePath,
						name, input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("获取文件流失败" + localFile);

			} finally {
				if (null != input) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("文件不存在！！！" + localFile);
			return false;
		}
		System.out.println("上传FTP文件成功！！！本地文件名:" + localFile + "上传到FTP文件名" + ftpFilePath
				+ filename);
		return true;
	}
    
	
	public static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.out.println("Failed to connect to the server");
				return success;
			}else{
				System.out.println("Successed to connect to the server");

			}
			ftp.mkd(path);
			ftp.changeWorkingDirectory(path);
//			ftp.enterLocalPassiveMode();
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//			ftp.storeFile(filename, input);
			ftp.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1") , input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					System.out.println("-----关闭ftp连接-----");
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
    
	
	
	 /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
	
    
    /** 
     * 递归遍历出目录下面所有文件 
     * @param pathName 需要遍历的目录，必须以"/"开始和结束 
     * @throws IOException 
     */  
    public static  List<String> ListFileName(String ftpHost, String ftpUserName,String ftpPassword, int ftpPort,String pathName){  
    	FTPClient ftp = null;
    	List<String> arFiles= new ArrayList<String>();
    	try {
				String directory = pathName;  
				//更换目录到当前目录  
				ftp = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
				ftp.changeWorkingDirectory(directory);  
				FTPFile[] files = ftp.listFiles();
				for(FTPFile file:files){
					//文件夹内是文件夹的情况暂不考虑
					if(file.isFile()){  
				        arFiles.add(file.getName());  
				    }
				}
			
			return arFiles;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					System.out.println("-----关闭ftp连接-----");
				} catch (IOException ioe) {
				}
			}
		}  
    	return null;
    }  
    
	/*
     * 从FTP服务器下载文件
     *
     * @param ftpHost FTP IP地址
     * @param ftpUserName FTP 用户名
     * @param ftpPassword FTP用户名密码
     * @param ftpPort FTP端口
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName 文件名称
     */
    public static void downloadFtpFile(String ftpHost, String ftpUserName,
                                       String ftpPassword, int ftpPort, String ftpPath, String localPath,
                                       String fileName) {

        FTPClient ftpClient = null;
        OutputStream os = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            File localFile = new File(localPath + File.separatorChar + fileName);
			os = new FileOutputStream(localFile);
			ftpClient.retrieveFile(new String(fileName.getBytes("utf-8"),"ISO-8859-1"), os);
           // ftpClient.retrieveFile(fileName, os);
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }finally {
        	if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					System.out.println("-----关闭ftp连接-----");
				} catch (IOException ioe) {
				}
			}
			if(os!=null){
				 try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
    public static void delFtpFile(String ftpHost, String ftpUserName,
            String ftpPassword, int ftpPort, String ftpPath,String filename){
    	 FTPClient ftpClient = null;

         try {
             ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
             ftpClient.setControlEncoding("UTF-8"); // 中文支持
             ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
             ftpClient.enterLocalPassiveMode();
             ftpClient.changeWorkingDirectory(ftpPath);
             ftpClient.deleteFile(filename);
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
        	 System.out.println("----删除的文件路径为:"+ftpPath+"/"+filename);
        	 if (ftpClient.isConnected()) {
 				try {
 					ftpClient.disconnect();
 					System.out.println("-----关闭ftp连接-----");
 				} catch (IOException ioe) {
 				}
 			}
 		}
    }
    
    
    public static boolean uploadFileToFtp(String ftpurl, Integer ftpport,
			String ftpuser, String ftppwd,
			String filename, String ftpFilePath,String b) {
		if (null == ftpport) {
			ftpport = 21;
		}
		b=b.replace("null", "");
		boolean b2=uploadFTPStringTofile(ftpurl, ftpport, ftpuser, ftppwd, ftpFilePath,filename, b);
			
		if(b2){
			System.out.println("上传FTP文件成功！！！上传到FTP文件名" + ftpFilePath+ filename);
			return true;
		}else{
			System.out.println("上传失败！！！上传到FTP文件名" + ftpFilePath+ filename);
			return false;
		}
		
		
	}
	
    
    
    public static boolean uploadFTPStringTofile(String url, int port, String username,
			String password, String path, String filename, String input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			InputStream is= new ByteArrayInputStream(input.getBytes("utf-8"));
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.mkd(path);
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1") , is);
//			ftp.enterLocalPassiveMode();
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//			ftp.storeFile(filename, input);
//			ftp.storeFile(new String(filename.getBytes("utf-8"), "iso-8859-1") , input);

			is.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
    
    public static void main(String[] args) {
		//FTPUtils.downloadFtpFile("192.168.20.125", "web", "123456", 21, "/recive/", "D://cp/receive", "req_00_2_7_20171109173340_1510219823649777.zip");
		//FTPUtils.downloadFtpFile("10.27.1.2", "ftp_cintel", "123456", 21, "/client/", "D://cp/receive", "req_00_3_7_20171109173230_1510219900773350.zip");
		//FTPUtils.downloadFtpFile("10.27.1.2", "ftp_cintel", "123456", 21, "/client/", "D://cp/receive", "req_00_3_7_20171109173230_1510219900773350.zip");
		//FTPUtils.uploadFileToFtpdir("10.27.1.2", 21, "root", "!CHAnct2017", "D://cp/recive//", "a.txt", "data/ld-data/center/30/");
   FTPUtils.getFTPClient("10.27.1.2", "root", "!CHAnct2017", 21);
    	
    	/*FTPClient ftpClient = new FTPClient();
        try {
			//ftpClient.connect("10.27.1.2", 22);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 连接FTP服务器
*/    }
}
