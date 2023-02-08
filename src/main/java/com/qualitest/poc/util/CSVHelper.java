package com.qualitest.poc.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.qualitest.poc.model.Items;
import com.qualitest.poc.model.Keys;


//import com.bezkoder.spring.files.csv.model.Tutorial;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "ItemID", "Stem", "Option1", "Option2","Option3","Option4","Option5","Option6",
		  "Option7","Option8" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Items> csvToItems(InputStream is) throws Exception {
	  //try (
	  CSVParser csvParser = null;
	  
			  BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			  
			  csvParser  = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()); 
	 

      List<Items> items = new ArrayList<Items>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      
      

      for (CSVRecord csvRecord : csvRecords) {
    	  
    	  Items item = new Items(
              csvRecord.get("ItemID"),
              csvRecord.get("Stem"),
              csvRecord.get("Option1"),
              csvRecord.get("Option2"),
              csvRecord.get("Option3"),
              csvRecord.get("Option4"),
              csvRecord.get("Option5"),
              csvRecord.get("Option6"),
              csvRecord.get("Option7"),
              csvRecord.get("Option8")
            );

    	  items.add(item);
      }

      csvParser.close();
      return items;
    
  }
  
  
  public static List<Keys> csvToKeys(InputStream is) throws Exception {
	  //try (
	  CSVParser csvParser = null;
	  
			  BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			  
			  csvParser  = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()); 
	 

      List<Keys> keys = new ArrayList<Keys>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      
      

      for (CSVRecord csvRecord : csvRecords) {
    	  
    	  
    	  Keys key = new Keys(
              csvRecord.get("ItemID"),
              csvRecord.get("key"),
              csvRecord.get("KeyText")
                          );

    	  keys.add(key);
      }

      csvParser.close();
      return keys;
    
  }
  

}