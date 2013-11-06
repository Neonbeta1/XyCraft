package soaryn.xycraft.core;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugLogger {

   public static Logger debugLogger = Logger.getLogger("XyCraft Debug");


   public static void initLogger() {
      try {
         File e = new File("XyCraft Debug.txt");
         FileHandler fileHandler = new FileHandler(e.getPath(), 0, 3);
         fileHandler.setLevel(Level.ALL);
         debugLogger.addHandler(fileHandler);
      } catch (Exception var2) {
         ;
      }

   }

   public static boolean isDebugging() {
      return "@DEBUG@".replace("@", "").equals("DEBUG");
   }

}
