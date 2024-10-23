package Util;

import java.io.IOException;

public class ApplicationUtils {

    public static void restartApplication() throws IOException {
        String javaBin = System.getProperty("java.home") + "/bin/java";
        String currentJar = new java.io.File(ApplicationUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getPath();

        /* Build command: java -jar application.jar */
        String[] command = new String[]{javaBin, "-jar", currentJar};

        /* Execute the command */
        Runtime.getRuntime().exec(command);

        /* Exit the current application */
        System.exit(0);
    }
}