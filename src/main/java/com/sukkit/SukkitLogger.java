package com.sukkit;

import static org.fusesource.jansi.Ansi.*;

public class SukkitLogger {

    /**
     * Erase all the text in the console
     */
    public static void EraseScreen() {
        System.out.println(ansi().eraseScreen());
    }

    /**
     * Log a message with debug level
     * 
     * @param s
     */
    public static void Debug(String s) {
        System.out.println(ansi().reset().a("[").fgBrightYellow().a("DEBUG").reset().a("]").a(s).reset());
    }

    /**
     * Log a message with info level
     * 
     * @param s
     */
    public static void Info(String s) {
        System.out.println(ansi().reset().a("[").a("INFO").a("] ").a(s).reset());
    }

    /**
     * Log a message with notice level
     * 
     * @param s
     */
    public static void Notice(String s) {
        System.out.println(ansi().reset().a("[").fgGreen().a("NOTICE").reset().a("] ").a(s).reset());
    }

    /**
     * Log a message with notice level
     * 
     * @param s
     */
    public static void Status(String s) {
        System.out.println(ansi().reset().a("[").fgBrightCyan().a("STATUS").reset().a("] ").fgBrightCyan().a(s).reset());
    }


    /**
     * Log a message with warning level
     * 
     * @param s
     */
    public static void Warning(String s) {
        System.out.println(ansi().reset().a("[").fgYellow().a("WARNING").reset().a("] ").a(s).reset());
    }

    /**
     * Log a message with critical level
     * 
     * @param s
     */
    public static void Critical(String s) {
        System.out.println(ansi().reset().a("[").fgBrightRed().a("CRITICAL").reset().a("] ").fgBrightRed().a(s).reset());
    }
    
}