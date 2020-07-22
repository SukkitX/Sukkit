package com.sukkit;

public class OSValidator {

    public enum OS {
        WINDOWS("Windows"),
        MAC("Mac"),
        UNIX("Linux"),
		OWIE("");
		
		private final String urlName;
		OS(String urlName) {
			this.urlName = urlName;
		}

		public final String getUrlName() {
			return urlName;
		}
    }

    private static OS OS;

	/**
	 * Find out which OS the user is using
	 */
	private static void determineOS() {
		String longOS = System.getProperty("os.name");
        if(longOS.indexOf("Windows") >= 0){
            OS = OS.WINDOWS;
        }
        if(longOS.indexOf("Mac") >= 0){
            OS = OS.MAC;
        }
        if(longOS.indexOf("Linux") >= 0){
            OS = OS.UNIX;
        }
        if(OS == null){
            OS = OS.OWIE;
        }
	}
	
	/**
	 * Get the operating system that the user is using
	 * 
	 * @return
	 */
    public static OS getOS() {
		if(OS == null){
			determineOS();
        }
        return OS;
    }

	/**
	 * Check if the user is using Windows
	 * 
	 * @return
	 */
	public static boolean isWindows() {
		return getOS() == OS.WINDOWS;
	}

	/**
	 * Check if the user is using Mac
	 * 
	 * @return
	 */
	public static boolean isMac() {
		return getOS() == OS.MAC;
	}

	/**
	 * Check if the user is using a Unix distribution
	 * 
	 * @return
	 */
	public static boolean isUnix() {
		return getOS() == OS.UNIX;
	}

	/**
	 * Check if the OS is supported
	 */
	public static boolean isSupported() {
		return getOS() != OS.OWIE;
	}

}