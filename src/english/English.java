package english;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import static java.lang.System.out;

public class English{
    public static void main(String[] args) throws IOException
    {
/*        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(350, 290); // set frame size
        window.setResizable(false);
        window.setVisible(true); // display frame*/
getFile();
    }

    static String projectPath1 =  Paths.get(".").toAbsolutePath().normalize().toString();

    private static void getFile() throws IOException
    {
        File[] folders;
        File[][] files;

        folders = new File[5];
        files = new File[5][];
        folders[0] = new File( projectPath1 + "/src/english/library/A1");
        folders[1] = new File( projectPath1 + "/src/english/library/A2");
        folders[2] = new File( projectPath1 + "/src/english/library/B1");
        folders[3] = new File( projectPath1 + "/src/english/library/Book");
        folders[4] = new File( projectPath1 + "/src/english/library/");

        try (Stream<Path> paths = Files.walk(Paths.get( projectPath1 + "/src/english/library/words from radio'voise of america'"))) {
            paths.filter( Files::isRegularFile ).forEach( path -> {
                try {
                    Properties properties = new Properties();
                    Files.lines( Paths.get( path.toUri() ) ).forEach( e -> {
                         String words[] = e.split( "@" );
                         properties.setProperty( words[0].trim(), words[1].trim() );
                    });
                    properties.storeToXML( new FileOutputStream( projectPath1 + "/src/english/library/" + path.getFileName() + ".xml" ), "just comment" );
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            } );
        } catch (Exception e) {
            out.println( e );
        }



/*        for (int i = 0; i < folders.length; i++) {
            files[i] = folders[i].listFiles();
            Arrays.sort(files[i]);
        }




        Stream.of():
        Scanner scanner = new Scanner(new File(pathToMain));*/
    }
}