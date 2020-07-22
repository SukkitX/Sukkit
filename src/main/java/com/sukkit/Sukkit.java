package com.sukkit;

import net.lingala.zip4j.ZipFile;

import org.fusesource.jansi.AnsiConsole;

import com.sukkit.OSValidator;
import static com.sukkit.SukkitLogger.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URL;
import java.net.URLConnection;

public class Sukkit 
{
    public static void main( String[] args )
    {
        if(!OSValidator.isSupported()) {
            System.out.println("Your current operating system is not supported.");
            System.exit(0);
        }

        AnsiConsole.systemInstall();
        // Clear the screen and start logging
        Critical("Sukkit has detected a problem with your current installation. The software will attempt to fix this.");
        Notice("Press enter to continue with the automatic fix...");
        
        // Wait for any user input
        Scanner input = new Scanner(System.in);
        input.nextLine();
        input.close();

        Status("Downloading files...");
        downloadFiles(getFiles());

        Status("Unleashing the power...");

        unzip("php.zip", "./");
        //move("php/bin", "bin");
        String binFolder = "";
        if(OSValidator.isWindows()) {
            unzip("Windows/php-7.3.20-vc15-x64.zip", "./");
            binFolder = "Windows";
        }
        if(OSValidator.isUnix()) {
            unzip("Linux/PHP_Linux-x86_64.tar.gz", "./");
            binFolder = "Linux";
        }
        if(OSValidator.isMac()) {
            unzip("Mac/PHP_MacOS-x86_64.tar.gz", "./");
            binFolder = "Mac";
        }
        Status("Cleaning the solar panels...");
        cleanup(new String[] {
            "php.zip",
        });
        if(binFolder != "") {
            deleteDirectory(new File(binFolder));
        }
        if(OSValidator.isWindows()) {
            deleteDirectory(new File("vc_redist.x64.exe"));
        }

        Status("Your installation has been successfully fixed!");
    }

        /**
     * Gets files to download, depending on operating system.
     */
    //private static String[] getFiles() {
    //    return new String[] {
    //        "https://dev.azure.com/pocketmine/a29511ba-1771-4ad2-a606-23c00a4b8b92/_apis/build/builds/233/artifacts?artifactName=" + OSValidator.getOS().getUrlName() + "&api-version=5.1&%24format=zip",
    //        "https://jenkins.pmmp.io/job/PocketMine-MP/lastStableBuild/artifact/PocketMine-MP.phar",
    //        "https://jenkins.pmmp.io/job/PocketMine-MP/lastStableBuild/artifact/start." + "start." + (OSValidator.isWindows() ? "ps1" : "sh"),
    //    };
    //}

    /**
     * Gets files to download, depending on operating system.
     */
    private static Map<String, String> getFiles() {
        Map<String, String> files = new HashMap<>();
        files.put("php.zip", "https://dev.azure.com/pocketmine/a29511ba-1771-4ad2-a606-23c00a4b8b92/_apis/build/builds/233/artifacts?artifactName=" + OSValidator.getOS().getUrlName() + "&api-version=5.1&%24format=zip");
        files.put("PocketMine-MP.phar", "https://github.com/pmmp/PocketMine-MP/releases/download/3.14.2/PocketMine-MP.phar");
        String startName = "start." + (OSValidator.isWindows() ? "cmd" : "sh");
        files.put(startName, "https://github.com/pmmp/PocketMine-MP/releases/download/3.14.2/" + startName);
        return files;
    }

    /**
     * Download multiple files
     * 
     * @param files
     */
    private static void downloadFiles(Map<String, String> files) {
        for (Map.Entry<String, String> entry : files.entrySet()) {
            downloadFile(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Download a single file from a URL
     * 
     * @param file
     */
    private static void downloadFile(String filename, String link) {
        Info("Downloading " + Colors.ANSI_YELLOW_BACKGROUND + filename);
        try{
            URL url = new URL(link);
            URLConnection uc = url.openConnection();
            //uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

            //HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
            //long fileSize = httpConnection.getContentLength();
            //thingie for getting filesize, for future use with progress bars 

            InputStream inputStream = url.openStream();
            Files.copy(inputStream, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception exception){
            // We are required to catch all possible exceptions to build the program
            exception.printStackTrace();
        }
    }

    /**
     * Delete unwanted files when the download is complete
     * 
     * @param files
     */
    private static void cleanup(String[] files) {
        for (String file : files) {
            try{
                deleteDirectory(new File(file));
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }

    /**
     * Delete a directory recursively.
     * 
     * @param directory
     */
    private static void deleteDirectory(File directory) {
        Info("Deleting " + Colors.ANSI_RED_BACKGROUND + directory);
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }

    /**
     * Move a file or folder to a different location
     * 
     * @param source
     * @param destination
     */
    private static void move(String source, String destination) {
        try {
            Info("Moving " + Colors.ANSI_YELLOW + source + Colors.ANSI_RESET + " to " + Colors.ANSI_YELLOW + destination);
            Files.move(new File(source).toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING); //lmao
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // Unarchive a .zip file
    private static void unzip(String source, String destination)
    {
        try {
            Info("Unarchiving " + Colors.ANSI_YELLOW + source + Colors.ANSI_RESET + " at " + Colors.ANSI_YELLOW + destination);
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
