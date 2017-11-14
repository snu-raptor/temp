package english;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame
{

    private JLabel russianLabel;                                    //a russian word
    private JLabel englishLabel;                                    //an english word

    private JPanel p;                                               //main panel
    private JPanel pl;                                              //main panel for info and buttons
    private JPanel info;                                            //panel with points and lastWordLabel
    private JLabel points;                                          //this panel consists points
    private JLabel lastWordLabel;                                   //this panel consists last word

    private JButton falseButton;
    private JButton trueButton;

    private JMenu a1;
    private JMenu a2;
    private JMenu b1;
    private JMenu book;
    private JMenu options;

    private File[][] files;                                         //array consists files to initialise the menu

    private boolean flags[][];                                      //array stores flags which shows that file is not redable.
    private String projectPath =  Paths.get(".").toAbsolutePath().normalize().toString() ;
    private final String pathToMain = projectPath + "/src/english/library/main";
    private String[] question;
    private Word word;
    private JCheckBoxMenuItem[][] items;

   // private final Timer timer;
    private final DataFiles dataFiles;
    private Word lastWord;
    private int counterPoints;
    private int counterQuestions;

    MainWindow() {
        super("English");
        dataFiles = DataFiles.getReference();
        files = new File[5][];
        flags = new boolean[5][];
        dataFiles.getFiles(files);
        dataFiles.setListOfWords();
        items = new JCheckBoxMenuItem[5][];
     /*   timer = new Timer(500, new ActionListener()
                  {
                      @Override
                      public void actionPerformed(ActionEvent ev) {
                          p.setBackground(Color.ORANGE);

                      }
        });
        timer.setRepeats(false);*/

        initGUI();
        initMenu();
    }

    private void setWords() {

        word = dataFiles.getWord();
        setFont();
        englishLabel.setText(word.getRightRussian());
       // russianLabel.setText(word.getEngish());
    }

    private void initGUI() {
        p = new JPanel();
        pl = new JPanel();
        p.setLayout(new GridLayout(3, 1));

        word = dataFiles.getWord();

        falseButton = new JButton("False");
        falseButton.setVisible(false);
        trueButton = new JButton("Next");

        falseButton.setBackground(Color.RED);
        trueButton.setBackground(Color.GREEN);

        englishLabel = new JLabel(word.getEngish());
        lastWordLabel = new JLabel();
        lastWordLabel.setMinimumSize(new Dimension(300, 400));
        lastWordLabel.setSize(new Dimension(300, 400));
        lastWordLabel.setPreferredSize(new Dimension(270, 50));

        russianLabel = new JLabel(word.getWrongRussian());
        russianLabel.setVisible(false);
        //russianLabel.setOpaque(false);
        russianLabel.setHorizontalAlignment(JTextField.CENTER);
        setFont();
        points = new JLabel("0 from 0\n");
        points.setVisible(false);
        info = new JPanel(new GridLayout(2, 1));

        points.setFont(new Font("TimesRoman", Font.BOLD, 9));
        lastWordLabel.setFont(new Font("TimesRoman", Font.BOLD, 9));
        lastWordLabel.setText("<html>Last word: <br> <br> <br> </html>");
        info.setBackground(new Color(143, 255, 71));

        //info.add(points);
        info.add(lastWordLabel);

        englishLabel.setHorizontalAlignment(JLabel.CENTER);
        FlowLayout q = new FlowLayout();
        q.setHgap(20);
        pl.setLayout(q);
        pl.add(falseButton);
        pl.add(trueButton);
        pl.add(points);
        pl.add(info);
        p.add(englishLabel);
        p.add(russianLabel);
        p.add(pl);
        p.setBackground(Color.ORANGE);

       /* falseButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lastWordLabel.setText("<html>Last word:<br>Englsih: " + word.getEngish() + "<br>Version: " + word.getWrongRussian() + "<br>Right: " + word.getRightRussian() + "</html>");
                        //System.out.println(word.isCorrect() + " " + word.getRightRussian().equals(word.getWrongRussian()) + (word.isCorrect() && word.getRightRussian().equals(word.getWrongRussian())));
                        if (!word.isCorrect() || !word.getRightRussian().equals(word.getWrongRussian())) {
                            counterPoints++;
                            info.setBackground(new Color(143, 255, 71));
                            timer.restart();
                            p.setBackground(Color.GREEN);
                        } else {
                            info.setBackground(new Color(255, 110, 110));

                            timer.restart();
                            p.setBackground(Color.RED);
                        }

                        counterQuestions++;
                        setWords();
                        points.setText(counterPoints + " from " + counterQuestions);
                    }
                });*/

        trueButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lastWordLabel.setText("<html>" + /*Last word:<br>*/"Englsih: " + word.getEngish()/* + "<br>Version: " + word.getWrongRussian() */+ "<br>Right: " + word.getRightRussian() + "</html>");
                        //System.out.println(word.isCorrect() + " " + word.getRightRussian().equals(word.getWrongRussian()) + (word.isCorrect() && word.getRightRussian().equals(word.getWrongRussian())));
                    /*    if (word.isCorrect() || word.getRightRussian().equals(word.getWrongRussian())) {
                            counterPoints++;
                            info.setBackground(new Color(143, 255, 71));
                            timer.restart();
                            p.setBackground(Color.GREEN);
                        } else {
                            info.setBackground(new Color(255, 110, 110));

                            timer.restart();
                            p.setBackground(Color.RED);
                        }
*/
                        counterQuestions++;
                        setWords();
                        points.setText(counterPoints + " from " + counterQuestions);
                    }
                });

        add(p);
    }

    private void initMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        fileMenu.add(exit);

        exit.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        System.exit(0);
                    }
                });

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileMenu);

        options = new JMenu("Options");
        options.setMnemonic('O');
        bar.add(options);

        a1 = new JMenu("A1");
        a2 = new JMenu("A2");
        b1 = new JMenu("B1");
        book = new JMenu("Book");

        options.add(a1);
        options.add(a2);
        options.add(b1);
        options.add(book);

        for (int i = 0; i < items.length; i++) {
            items[i] = new JCheckBoxMenuItem[files[i].length];
        }

        for (int i = 0; i < flags.length; i++) {
            flags[i] = new boolean[files[i].length];
        }

        setFlags(a1, files[0], items[0], flags[0], files[0].length);
        setFlags(a2, files[1], items[1], flags[1], files[1].length);
        setFlags(b1, files[2], items[2], flags[2], files[2].length);
        setFlags(book, files[3], items[3], flags[3], files[3].length);
        setFlags(options, files[4], items[4], flags[4], files[4].length);
    }

    private void setFont() {
        if (word.getEngish().length() > 45) {
            russianLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 8));
        } else if (word.getEngish().length() > 32) {
            russianLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 11));
        } else {
            russianLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 14));
        }

        if (word.getWrongRussian().length() > 45) {
            englishLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 8));
        } else if (word.getWrongRussian().length() > 32) {
            englishLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 11));
        } else {
            englishLabel.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 14));
        }
    }

    private void setFlags(JMenu menu, File[] files, JCheckBoxMenuItem[] items, boolean[] flags, int len) {
        StyleHandler handler = new StyleHandler();

        for (int count = 0; count < len; count++) {

            if (files[count].getName().endsWith("~") || files[count].isDirectory() || files[count].getName().equals("main")) {
                flags[count] = true;
            } else {
                items[count] = new JCheckBoxMenuItem(files[count].getName());
                menu.add(items[count]);
                items[count].addItemListener(handler);
            }
        }
    }

    private void clearMain() {
        try {
            FileWriter fstream1 = new FileWriter(pathToMain);
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        } catch (IOException e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

    private boolean areDeselected() {
        boolean deselected = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < items[i].length; j++) {
                if (flags[i][j] == false && items[i][j].isSelected()) {
                    deselected = false;
                }
            }
        }

        return deselected;
    }

    private class StyleHandler implements ItemListener
    {

        public void itemStateChanged(ItemEvent e) {
            if (areDeselected()) {
                return;
            }

            clearMain();
            for (int i = 0; i < items.length; i++) {
                dataFiles.copyFile(items[i], flags[i], files[i]);
            }

            dataFiles.setListOfWords();
            word = dataFiles.getWord();
            setWords();
            counterPoints = 0;
            counterQuestions = 0;
        }
    }
}
