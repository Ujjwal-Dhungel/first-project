import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class SystemInfoTool {

    public static void main(String[] args) {
        System.out.println("=== JAVA SYSTEM INFORMATION TOOL ===");
        System.out.println("Generated on: " + new Date());
        System.out.println("------------------------------------");
        
        displayOSInfo();
        displayJavaInfo();
        displayMemoryInfo();
        displayDiskInfo();
        displayUserInfo();
        
        System.out.println("------------------------------------");
        System.out.println("System check completed.");
    }

    private static void displayOSInfo() {
        System.out.println("\n** OPERATING SYSTEM **");
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
    }

    private static void displayJavaInfo() {
        System.out.println("\n** JAVA RUNTIME **");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("JVM Name: " + System.getProperty("java.vm.name"));
        System.out.println("JVM Version: " + System.getProperty("java.vm.version"));
        
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("JVM Uptime: " + formatUptime(runtimeMxBean.getUptime()));
    }

    private static void displayMemoryInfo() {
        System.out.println("\n** MEMORY USAGE **");
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Max Memory: " + format.format(maxMemory / (1024 * 1024)) + " MB");
        System.out.println("Total Memory: " + format.format(totalMemory / (1024 * 1024)) + " MB");
        System.out.println("Used Memory: " + format.format(usedMemory / (1024 * 1024)) + " MB");
        System.out.println("Free Memory: " + format.format(freeMemory / (1024 * 1024)) + " MB");
        
        double usagePercentage = (double) usedMemory / totalMemory * 100;
        System.out.printf("Memory Usage: %.2f%%\n", usagePercentage);
    }

    private static void displayDiskInfo() {
        System.out.println("\n** DISK SPACE **");
        File[] roots = File.listRoots();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        
        for (File root : roots) {
            long totalSpace = root.getTotalSpace();
            long freeSpace = root.getFreeSpace();
            long usedSpace = totalSpace - freeSpace;
            
            if (totalSpace > 0) {
                double usagePercentage = (double) usedSpace / totalSpace * 100;
                System.out.println("Drive: " + root.getAbsolutePath());
                System.out.println("  Total: " + format.format(totalSpace / (1024 * 1024 * 1024)) + " GB");
                System.out.println("  Used: " + format.format(usedSpace / (1024 * 1024 * 1024)) + " GB");
                System.out.println("  Free: " + format.format(freeSpace / (1024 * 1024 * 1024)) + " GB");
                System.out.printf("  Usage: %.2f%%\n", usagePercentage);
            }
        }
    }

    private static void displayUserInfo() {
        System.out.println("\n** USER INFORMATION **");
        System.out.println("User Name: " + System.getProperty("user.name"));
        System.out.println("User Home Directory: " + System.getProperty("user.home"));
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
    }

    private static String formatUptime(long uptimeMillis) {
        long seconds = uptimeMillis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}