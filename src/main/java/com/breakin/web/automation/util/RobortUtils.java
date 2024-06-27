package com.breakin.web.automation.util;

//import org.sikuli.script.*;

public class RobortUtils {

//	public static void uploadPhoto(String photoPath) {
//		Screen screen = new Screen();
//
//        // Wait for the file explorer window to appear
//        try {
//            screen.wait("./src/test/resources/images/Full_window.PNG", 10); // Replace "file_explorer.png" with the image of the file explorer window
//
//            // Navigate to the folder containing the photos
//            screen.click("./src/test/resources/images/Search.PNG"); // Replace "address_bar.png" with the image of the file explorer's address bar
//            screen.type("C:\\Users\\NaradaBenarji\\eclipse-workspace\\BreakInWebsite\\src\\test\\resources\\images"); // Replace "path/to/folder" with the actual path of the folder containing the photos
//            screen.type(Key.ENTER);
//
////            // Wait for the folder content to load
////            screen.wait("folder_content.png", 10); // Replace "folder_content.png" with an image that indicates the folder's content
////
////            // Select all photos at once using Ctrl+A (Command+A for macOS)
////            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
////                screen.type(Key.CMD + "a");
////            } else {
////                screen.type(Key.CTRL + "a");
////            }
////
////            // Wait for the photos to be selected
////            screen.wait("selected_photos.png", 5); // Replace "selected_photos.png" with an image that indicates the selected photos
//
//            // Perform any further actions on the selected photos if needed
//        } catch (FindFailed e) {
//            e.printStackTrace();
//        }
 //   }
    









	public boolean checkAnyOtherStatus(java.util.List<String>obj,String status) {
		if(obj==null||obj.isEmpty()) {
			return false;
		}
		boolean flag=true;
		for(String s:obj) {
			if(!s.equals(status)) {
				flag= false ;
				break;
			}
			
		}
		return flag;
	}
	
	}

