package burp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BurpExtender implements IBurpExtender, IHttpListener
{
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // your extension code here
    	callbacks.registerHttpListener(this);
    }
    
    int number = 1;
    int number8 = 1;
    int number16 = 1;
    int number32 = 1;
    
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
    	Date date = new Date();
    	SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
    	String dateString = simpledateformat.format(date);
    	String logFiles = "";
    	
    	if(toolFlag == 32)
    	{
    		logFiles = dateString + "_INTRUDER_"+ String.format("%02d", number32) + ".log";
    		File file = new File(logFiles);
    		if(file.exists())
    		{
    			if(file.length() >= 104857600)
    			{
    				number32 = number32 + 1;
    				logFiles = dateString + "_INTRUDER_"+ String.format("%02d", number32) + ".log";
    			}
    		}
    	} else if(toolFlag == 16)
    	{
    		logFiles = dateString + "_SCANNER_"+ String.format("%02d", number16) + ".log";
    		File file = new File(logFiles);
    		if(file.exists())
    		{
    			if(file.length() >= 104857600)
    			{
    				number16 = number16 + 1;
    				logFiles = dateString + "_SCANNER_"+ String.format("%02d", number16) + ".log";
    			}
    		}    		
    	} else if(toolFlag == 8)
    	{
    		logFiles = dateString + "_SPIDER_"+ String.format("%02d", number8) + ".log";
    		File file = new File(logFiles);
    		if(file.exists())
    		{
    			if(file.length() >= 104857600)
    			{
    				number8 = number8 + 1;
    				logFiles = dateString + "_SPIDER_"+ String.format("%02d", number8) + ".log";
    			}
    		}    		
    	} else 
    	{
    		logFiles = dateString + String.format("%02d", number) + ".log";
    		File file = new File(logFiles);
    		if(file.exists())
    		{
    			if(file.length() >= 104857600)
    			{
    				number = number + 1;
    				logFiles = dateString + "_" + String.format("%02d", number) + ".log";
    			}
    		}    		
    	}
    	
    	String request = "";
    	String response = "";
    	SimpleDateFormat simpledateformat1 = new SimpleDateFormat("HH:mm:ss");
    	String dateOutput = simpledateformat1.format(date);
    	
    	try
    	{
    		if(messageInfo.getResponse() != null)
    		{
    			FileWriter filewriter = new FileWriter(logFiles, true);
    			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
    			request = new String(messageInfo.getRequest());
    			response = new String(messageInfo.getResponse());
    		
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(dateOutput);
	    		bufferedwriter.write("  ");
	    		bufferedwriter.write(String.valueOf(messageInfo.getHttpService()));;
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(request);
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(response);
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.close();
    		}
    	}
    	catch(IOException exception)
    	{
    		System.err.println(exception);
    	}
    }
}
