package english;

import javax.swing.JOptionPane;

public final class Word
{

    private String[] word;
    private boolean correctness;

    Word(String[] wrd, boolean flag) {
        try {
            setWord(wrd, flag);
        } catch (NotSituableArray e) {
            JOptionPane.showMessageDialog(null, this.toString() + '\n' + e);
            System.out.println(this.toString() + '\n' + e);
        }
    }

    public void setWord(String[] wrd, boolean flag) throws NotSituableArray {
        if (wrd.length != 3) {
            throw new NotSituableArray("length of passed array is " + wrd.length);
        } else {
            word = wrd;
            correctness = flag;
        }
    }

    public boolean isCorrect() {
        return correctness;
    }

    public String getEngish() {
        return word[0];
    }

    public String getWrongRussian() {
        return word[1];
    }

    public String getRightRussian() {
        return word[2];
    }

    public String[] getStrings() {
        return word;
    }
}
