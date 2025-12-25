package utils;

public class FilePathUtils
{
    public static String getWorkingDirectoryPath() {
        return System.getProperty("user.dir");
    }

    public static String getResourceDirectoryPath() {
        return getWorkingDirectoryPath() + "/src/main/resources/";
    }
}
