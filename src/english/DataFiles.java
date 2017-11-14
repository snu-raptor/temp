package english;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

public class DataFiles
{

    private File[] folders;
    private File[][] files;

    private final String pathToMain;

    private ArrayList<String> lst;

    private static DataFiles me;

    private String projectPath =  Paths.get(".").toAbsolutePath().normalize().toString() ;

    private DataFiles() {
        pathToMain = projectPath + "/src/english/library/main";
        folders = new File[5];
        files = new File[5][];
        folders[0] = new File(projectPath + "/src/english/library/A1");
        folders[1] = new File(projectPath + "/src/english/library/A2");
        folders[2] = new File(projectPath + "/src/english/library/B1");
        folders[3] = new File(projectPath + "/src/english/library/Book");
        folders[4] = new File(projectPath + "/src/english/library/");

        for (int i = 0; i < folders.length; i++) {
            files[i] = folders[i].listFiles();
            Arrays.sort(files[i]);
        }

        setListOfWords();
    }

    public static DataFiles getReference() {
        if (me != null) {
            return me;
        } else {
            return me = new DataFiles();
        }
    }
    /*
     public static void main(String[] args)
     {
     DataFiles df = new DataFiles();
     df.setListOfWords();
     df.getWord();
        
     Word wr = df.getWord();
     String[] ss = wr.getStrings();

        
     for(String s : ss)
     System.out.println(s + wr.isCorrect());
     }
     */

    public void getFiles(File[][] files) {
        System.arraycopy(this.files, 0, files, 0, this.files.length);
    }

    public ArrayList setListOfWords() {
        try {
            Scanner scanner = new Scanner(new File(pathToMain));

            lst = new ArrayList();

            while (scanner.hasNextLine()) {
                lst.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, this.toString() + '\n' + e);
            System.out.println(this.toString() + '\n' + e);
        }

        return lst;
    }

    void copyFile(JCheckBoxMenuItem[] ckbm, boolean[] f, File[] fls) {
        for (int i = 0; i < ckbm.length; i++) {
            if (f[i]) {
                continue;
            }

            try {
                if (ckbm[i].isSelected()) {
                    Scanner sc = new Scanner(fls[i]);
                    FileWriter writer = null;

                    do {
                        try {
                            writer = new FileWriter(pathToMain, true);
                            writer.write(sc.nextLine() + '\n');
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, this.toString() + '\n' + e);
                            System.out.println(this.toString() + '\n' + e);
                        } finally {
                            if (writer != null) {
                                try {
                                    writer.close();
                                } catch (IOException e) {
                                    JOptionPane.showMessageDialog(null, this.toString() + '\n' + e);
                                    System.out.println(this.toString() + '\n' + e);
                                }
                            }

                            sc.close();
                        }
                    } while (sc.hasNextLine());

                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, this.toString() + '\n' + e);
                System.out.println(this.toString() + '\n' + e);
                System.exit(1);
            }
        }
    }

    public Word getWord() {
        Random r = new Random();
        boolean flag = r.nextBoolean();
        String[] word = new String[3];
        String[] temp = lst.get(0 + r.nextInt(lst.size())).split("@");
        word[0] = temp[0].trim();
        word[2] = temp[1].trim();

        if (flag) {
            word[1] = word[2];
        } else {
            String[] temp2 = lst.get(0 + r.nextInt(lst.size())).split("@");
            word[1] = temp2[1].trim();
        }

        return new Word(word, flag);
    }
}
