package com.qts.service;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qts.dao.DashboardRepository;
import com.qts.model.DiskStatistics;
import com.qts.model.Disks;
import com.qts.model.Heartbeat;
import com.qts.model.NetworkInterfaceDetails;
import com.qts.model.NetworkInterfaceStats;
import com.qts.model.NetworkInterfaces;
import com.qts.model.OperatingSystemDetails;
import com.qts.model.Statistics;
import com.qts.util.JvmStatisticsUtility;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {
 
  private static final Logger logger = LoggerFactory.getLogger(RestController.class);
  public static final String APPLICATION_JSON = "application/json";
  public static final String APPLICATION_XML = "application/xml";
  public static final String APPLICATION_HTML = "text/html";
  private Heartbeat heartbeat = null;
  
  private static final String DOWNLOAD_MCRAWL_FILE_PATH = "C:\\Mcrawl\\download_files\\manta_partial.csv";
  private static final String DOWNLOAD_WIREMDM_FILE_PATH = "/home/guru/Desktop/ML_Dashboard/WireMDM/Classifications.out";
  private static final String DOWNLOAD_CAT_FILE_PATH = "C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\Sample_Data_sets\\Equix_data\\Classified_out.csv";
   
  
 
  
  @Autowired 
  private DashboardRepository dashboardRepository; 

  public RestController() {
	  InetAddress ip;
	  
	  heartbeat = new Heartbeat();
	  try {
          ip = InetAddress.getLocalHost();
          heartbeat.setIpAddress(ip.getHostAddress());
          logger.info("Your current IP address : " + ip.getHostAddress());
      } catch (UnknownHostException e) {
          e.printStackTrace();
      }
	  
	  logger.info("Heartbeat = " + heartbeat);
  }
  
  /**
   * Simply selects the home view to render by returning its name.
 
   */
  @RequestMapping(value = "/status", method = RequestMethod.GET, produces=APPLICATION_HTML)
  public @ResponseBody String status() {
    return "Dashboard Backend Status OK...";
  }

  @RequestMapping(value="/statistics", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody Statistics getStatistics() {
    logger.info("Inside getStatistics() method...");
    
    
    System.out.println("======================================== in side ");
    		try{

   			
   		}catch(Exception e){
        	e.printStackTrace();
        }
    
   	 Statistics stats = dashboardRepository.getStatisticsByID("1");
   		
    return stats;
    //return output;
  }
  
  
  
  @RequestMapping(value = "/createRunClasFile", method = RequestMethod.POST , produces={"text/plain"})	
  public  @ResponseBody String writeFile( @RequestBody String csvString) throws Throwable   {
 	System.out.println(" in create file post method  "+ csvString );
  
 	String csvFileName = "/home/guru/Desktop/ML_Dashboard/WireMDM/test_csv.csv";
 try {
 	         File file=new File(csvFileName);
 	         System.out.println(" in 3   "+ csvString );
 	         FileUtils.writeStringToFile(file, csvString);
 	         
 	        runWiremdm();
 	     } catch (Exception e) {
 	         e.printStackTrace();
 	     }
   	return "file created and Classificatioin run Scuccessfully";
  }
  
  
  @RequestMapping(value = "/trainCatClassifier", method = RequestMethod.POST , produces={"text/plain"})	
  public  @ResponseBody String writeCatFile( @RequestBody String csvString) throws Throwable   {
 	System.out.println(" in create file post method  "+ csvString );
  
 	String csvFileName = "C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\Sample_Data_sets\\CategoryProduct_train.csv";
 try {
 	         File file=new File(csvFileName);
 	         System.out.println(" in 3   "+ csvString );
 	         FileUtils.writeStringToFile(file, csvString);
 	         trainCatClassifier();
 	        
 	     } catch (Exception e) {
 	         e.printStackTrace();
 	     }
   	return "file created and Classificatioin run Scuccessfully";
  }
  
  @RequestMapping(value = "/testCatClassifier", method = RequestMethod.POST , produces={"text/plain"})	
  public  @ResponseBody String testCatClassifier( @RequestBody String csvString) throws Throwable   {
 	System.out.println(" in testCatClassifier method  "+ csvString );
  
 	String csvFileName = "C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\Sample_Data_sets\\Equix_data\\Central_NotMapped _Test_data_1.csv";
 try {
 	         File file=new File(csvFileName);
 	         System.out.println(" in 3   "+ csvString );
 	         FileUtils.writeStringToFile(file, csvString);
 	         
 	        testCatClassifier();
 	     } catch (Exception e) {
 	         e.printStackTrace();
 	     }
   	return "file created and Classificatioin run Scuccessfully";
  }
  
  
  
  @RequestMapping(value = "/downloadCatClasCsv", method = RequestMethod.GET, produces = "text/csv")
  public @ResponseBody Resource downloadCatClasCsv(HttpServletResponse response) throws FileNotFoundException {
  		File file = getFile(DOWNLOAD_CAT_FILE_PATH);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
      
  }
 
  @RequestMapping(value = "/downloadWireMDMCsv", method = RequestMethod.GET, produces = "text/csv")
  public @ResponseBody Resource downloadC(HttpServletResponse response) throws FileNotFoundException {
  		File file = getFile(DOWNLOAD_WIREMDM_FILE_PATH);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
      
  }
  
  @RequestMapping(value = "/updateBankClassifier", method = RequestMethod.POST , produces={"text/plain"})	
  public  @ResponseBody String writeUpdateFile( @RequestBody String csvString) throws Throwable   {
 	System.out.println(" in create file post method  "+ csvString );
  
 	String csvFileName = "/home/guru/Desktop/ML_Dashboard/WireMDM/test_update.csv";
 try {
 	         File file=new File(csvFileName);
 	         System.out.println(" in 3   "+ csvString );
 	         FileUtils.writeStringToFile(file, csvString);
 	         
 	        updateWiremdm();
 	     } catch (Exception e) {
 	         e.printStackTrace();
 	     }
   	return " Classifier Updated Scuccessfully";
  }
  
  
  @RequestMapping(value = "/updateWireMDM", method = RequestMethod.GET, produces = "text/plain")
  public @ResponseBody String updateWireMDM() {
	  
	  boolean flag = false;
	  	try {
				flag = updateWiremdm();
		        
			} catch (Throwable e) {
				e.printStackTrace();
			}
	  	if(flag ==true){
	  		return "true";  
	  	}
	  		return "false";
      
  }
  
  
 
  @RequestMapping(value="/osdetails", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody OperatingSystemDetails getOSDetails() {
	  OperatingSystemDetails osDetails = null;
	  
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
  	osDetails  = jvmstats.getOSDetails();
 	return osDetails; 	
  }
  
  @RequestMapping(value="/getallnetworkinterfaces", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody NetworkInterfaces getAllNetworkInterfaces(
		  @RequestParam(value="is_filtered", defaultValue="true") boolean isActive) {
	 NetworkInterfaces networkInterfaces = null;
	 
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
	networkInterfaces  = jvmstats.getAllNetInterfaces(isActive);

 	return networkInterfaces; 	
  }
  
  @RequestMapping(value="/getnetworkstats", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody NetworkInterfaceStats getNetworkInterfaceStats(@RequestParam("interface") String netInterface) {
	  NetworkInterfaceStats networkInterfaceStats = null;
	  
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
  	try {
		networkInterfaceStats  = jvmstats.getNetworkInterfaceStats(netInterface);
	} catch (SigarException e) {
		e.printStackTrace();
	}

 	return networkInterfaceStats; 	
  }
  
  @RequestMapping(value="/networkdetails", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody NetworkInterfaceDetails getNetworkDetails(@RequestParam("interface") String netInterface) {
	NetworkInterfaceDetails networkDetails = null;
	  
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
  	try {
		networkDetails  = jvmstats.getNetInterfaceDetails(netInterface);
	} catch (SigarException e) {
		e.printStackTrace();
	}
 	return networkDetails; 	
  }

  @RequestMapping(value="/getalldisks", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody Disks getGetAllDisks() {
	DiskStatistics diskDetails = null;
	FileSystem[] fsList = null;
	  
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
  	fsList  = jvmstats.getAllFileSystemList();
  	Disks allDisks = new Disks();
  	for (FileSystem fs: fsList) {
  		String drive = fs.getDirName();
  		double percent = 0;
  		boolean isOnline = false;
  		try {
  	  		diskDetails  = jvmstats.getDiskDetails(drive);
  	  		percent = diskDetails.getUsePercentage();
  	  		isOnline = true;
  		} catch (SigarException e) {
  			isOnline = false;
  		}
	  	allDisks.addDrive(fs.getDirName(), fs.getDevName(), fs.getTypeName(), fs.getSysTypeName(),
	  						fs.getOptions(), fs.getType(), fs.getFlags(), isOnline, percent*100.0);
  	}
  	
 	return allDisks; 	
  }
  
  @RequestMapping(value="/diskdetails", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody DiskStatistics getDiskDetails(@RequestParam("drive") String drive) {
	  DiskStatistics diskDetails = null;
	  
  	JvmStatisticsUtility jvmstats = JvmStatisticsUtility.getInstance();
  	try {
  		diskDetails  = jvmstats.getDiskDetails(drive);
	} catch (SigarException e) {
		e.printStackTrace();
	}
 	return diskDetails; 	
  }
  
  @RequestMapping(value="/heartbeat", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody Heartbeat genHeartbeartAndBroadcast() {
	  Date now = new Date();
	  heartbeat.setCount(heartbeat.getCount()+1);
	  heartbeat.setCurrentDate(now);
	  
	  return heartbeat;
  }
    
  @RequestMapping(value="/resetheartbeat", method=RequestMethod.GET, produces={APPLICATION_JSON,APPLICATION_XML})
  public @ResponseBody Heartbeat resetHeartbeatAndBroadcast() {
	  Date now = new Date();
	  heartbeat.setCount(1);			// Reset back to 1
	  heartbeat.setStartDate(now);
	  heartbeat.setCurrentDate(now);
	  
	  return heartbeat;
  }
  
  ////////////////////////////////
  
  
  private File getFile(String fileName) throws FileNotFoundException {
      File file = new File(fileName);
      if (!file.exists()){
          throw new FileNotFoundException("file with path: " + fileName + " was not found.");
      }
      return file;
  }  
  
  
  
  
public boolean trainCatClassifier() throws Throwable {
	  
	  System.out.println(" in trainCatClassifier restcontroller ");

  	boolean flag = false;
          String[] command = {"python","C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\naive-bayes-classifier-master\\examples\\newsClassifier.py","train"};
          ProcessBuilder probuilder = new ProcessBuilder( command );
          //You can set up your work directory
          probuilder.directory(new File("C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\naive-bayes-classifier-master\\examples"));
          
          Process process = probuilder.start();
          
          //Read out dir output
          InputStream is = process.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(isr);
          String line;
          System.out.printf("Output of running %s is:\n",
                  Arrays.toString(command));
          while ((line = br.readLine()) != null) {
              System.out.println(line);
          }
          
          //Wait to get exit value
          try {
              int exitValue = process.waitFor();
              System.out.println("\n\nExit Value is " + exitValue);
              
              if(exitValue == 0){
              	System.out.println(" Process finished, classifier trained.");
              	return flag = true;
              }
          } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
			return flag;
      }


public boolean testCatClassifier() throws Throwable {
	  
	  System.out.println(" in trainCatClassifier restcontroller ");

	boolean flag = false;
        String[] command = {"python","C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\naive-bayes-classifier-master\\examples\\newsClassifier.py","test"};
        ProcessBuilder probuilder = new ProcessBuilder( command );
        //You can set up your work directory
        probuilder.directory(new File("C:\\Users\\GuruRakesh\\Desktop\\Machine_Learning\\naive-bayes-classifier-master\\examples"));
        
        Process process = probuilder.start();
        
        //Read out dir output
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.printf("Output of running %s is:\n",
                Arrays.toString(command));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        
        //Wait to get exit value
        try {
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
            
            if(exitValue == 0){
            	System.out.println(" Process finished, output file generated.");
            	return flag = true;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
			return flag;
    }
  
  public boolean runWiremdm() throws Throwable {
	  
	  System.out.println(" in runwMDM restcontroller ");

  	boolean flag = false;
          String[] command = {"java", "-jar", "WireMDM.jar" ,"/home/guru/Desktop/ML_Dashboard/WireMDM/test_csv.csv"};
          ProcessBuilder probuilder = new ProcessBuilder( command );
          //You can set up your work directory
          probuilder.directory(new File("/home/guru/Desktop/ML_Dashboard/WireMDM"));
          
          Process process = probuilder.start();
          
          //Read out dir output
          InputStream is = process.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(isr);
          String line;
          System.out.printf("Output of running %s is:\n",
                  Arrays.toString(command));
          while ((line = br.readLine()) != null) {
              System.out.println(line);
          }
          
          //Wait to get exit value
          try {
              int exitValue = process.waitFor();
              System.out.println("\n\nExit Value is " + exitValue);
              
              if(exitValue == 0){
              	System.out.println(" Process finished, OutPut file generated.");
              	return flag = true;
              }
          } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
			return flag;
      }
  
  
  public boolean updateWiremdm() throws Throwable {

  	boolean flag = false;
          String[] command = {"java", "-jar", "WireMDM.jar" ,"/home/guru/Desktop/ML_Dashboard/WireMDM/test_update.csv", "-update"};
          ProcessBuilder probuilder = new ProcessBuilder( command );
          //You can set up your work directory
          probuilder.directory(new File("/home/guru/Desktop/ML_Dashboard/WireMDM"));
        
          try {
          Process process = probuilder.start();
          
          //Read out dir output
          InputStream is = process.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(isr);
          String line;
          System.out.printf("Output of running %s is:\n",
                  Arrays.toString(command));
          while ((line = br.readLine()) != null) {
              System.out.println(line);
          }
          
          //Wait to get exit value
          
              int exitValue = process.waitFor();
              System.out.println("\n\nExit Value is " + exitValue);
              
              if(exitValue == 0){
              	System.out.println(" Process finished, Update successfull.");
              	return flag = true;
              }
              
              if(exitValue != 0){
              	System.out.println(" Error while updating the classifier.");
              	return flag = false;
              }
          } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          
          catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
			return flag;
      }
  
  
  
  boolean SAVtester() throws Throwable {

	   	boolean flag = false;
	           String[] command = {"python","/home/guru/Desktop/ML Dashboard/S-IDGP/S-IDGP-tester.py","-u","-encode","UTF-8"};
	           ProcessBuilder probuilder = new ProcessBuilder( command );
	           //You can set up your work directory
	           probuilder.directory(new File("/home/guru/Desktop/ML Dashboard/S-IDGP/"));
	           
	           Process process = probuilder.start();
	           
	           			           
	           //Read out dir output
	           InputStream is = process.getInputStream();
	           InputStreamReader isr = new InputStreamReader(is);
	           BufferedReader br = new BufferedReader(isr);
	           String line;
	           System.out.printf("Output of running %s is:\n",
	                   Arrays.toString(command));
	           while ((line = br.readLine()) != null) {
	               System.out.println(line);
	               if(line.equalsIgnoreCase("DONE!!!")){
	               	
	               	process.destroy();
	               	System.out.println(" Process finished, OutPut file generated.");
	               	return flag = true;
		               }
	           }
	           
	           //Wait to get exit value
	           try {
	               int exitValue = process.waitFor();
	               System.out.println("\n\nExit Value is " + exitValue);
	               
	               if(exitValue == 0){
	               	System.out.println(" Process finished, OutPut file generated.");
	               	return flag = true;
	               }
	           } catch (InterruptedException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	           }
				return flag;
	       }
 
 static boolean SIDGPtester() throws Throwable {

	   	boolean flag = false;
	           String[] command = {"python","/home/guru/Desktop/ML Dashboard/S-IDGP/S-IDGP-tester.py","-u","-encode","UTF-8"};
	           ProcessBuilder probuilder = new ProcessBuilder( command );
	           //You can set up your work directory
	           probuilder.directory(new File("/home/guru/Desktop/ML Dashboard/S-IDGP/"));
	           
	           Process process = probuilder.start();
	           
	           			           
	           //Read out dir output
	           InputStream is = process.getInputStream();
	           InputStreamReader isr = new InputStreamReader(is);
	           BufferedReader br = new BufferedReader(isr);
	           String line;
	           System.out.printf("Output of running %s is:\n",
	                   Arrays.toString(command));
	           while ((line = br.readLine()) != null) {
	               System.out.println(line);
	               if(line.equalsIgnoreCase("DONE!!!")){
	               	
	               	process.destroy();
	               	System.out.println(" Process finished, OutPut file generated.");
	               	return flag = true;
		               }
	           }
	           
	           //Wait to get exit value
	           try {
	               int exitValue = process.waitFor();
	               System.out.println("\n\nExit Value is " + exitValue);
	               
	               if(exitValue == 0){
	               	System.out.println(" Process finished, OutPut file generated.");
	               	return flag = true;
	               }
	           } catch (InterruptedException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	           }
				return flag;
	       }
  

  
}