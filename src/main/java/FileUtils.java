import java.io.File;
public class FileUtils {
    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        final File folder = new File("C:\\Users\\vali7\\IdeaProjects\\SoccerStat\\src\\main\\resources\\teamsCorresp");
        listFilesForFolder(folder);
    }

}
