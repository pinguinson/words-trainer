import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.IOException;

public class MyWindow extends JFrame {
    private int total;
    private int right;
    private JLabel successRate;
    private JLabel currentWord;
    private JLabel translation;
    private JButton showAnswer;
    private JButton rightAnswer;
    private JButton wrongAnswer;
    private JButton restart;
    private JButton openFile;
    private JFileChooser fileChooser;
    private JPanel buttonsPanel1;
    Dictionary words;
    String buff;
    int curWordIndex;

    public MyWindow() throws IOException, InterruptedException {
        super("Dictionary trainer");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.total      = 0;
        this.right      = 0;
        fileChooser     = new JFileChooser();
        currentWord     = new JLabel(" Open File");
        translation     = new JLabel(" ");
        showAnswer      = new JButton("Show answer");
        rightAnswer     = new JButton("Right answer");
        restart         = new JButton("Restart");
        wrongAnswer     = new JButton("Wrong answer");
        openFile        = new JButton("Open");
        Font calibri    = new Font("Calibri", Font.PLAIN, 14);
        currentWord.setFont(calibri);
        translation.setFont(calibri);
        this.pack();

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (fileChooser.showOpenDialog(translation.getParent())) {
                    case JFileChooser.APPROVE_OPTION:
                        try {
                            words = new Dictionary(fileChooser.getSelectedFile().getCanonicalPath());
                            updateCurrentWord();
                            showAnswer.setEnabled(true);
                        } catch (Exception x) {
                            x.printStackTrace();
                        }
                        break;
                }
            }
        });

        rightAnswer .setEnabled(false);
        showAnswer  .setEnabled(false);
        wrongAnswer .setEnabled(false);
        restart     .setEnabled(false);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total = 0;
                right = 0;
                showAnswer  .setEnabled(true);
                rightAnswer .setEnabled(true);
                wrongAnswer .setEnabled(true);
                updateWordsCounter();
                updateCurrentWord();
            }
        });

        wrongAnswer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                total++;
                updateWordsCounter();
                showAnswer  .setEnabled(true);
                rightAnswer .setEnabled(false);
                wrongAnswer .setEnabled(false);
                updateCurrentWord();
                translation.setText(" ");
            }
        });

        rightAnswer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                total++;
                right++;
                updateWordsCounter();
                showAnswer  .setEnabled(true);
                rightAnswer .setEnabled(false);
                wrongAnswer .setEnabled(false);
                updateCurrentWord();
                translation.setText(" ");
            }
        });

        showAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTranslation();
                showAnswer  .setEnabled(false);
                rightAnswer .setEnabled(true);
                wrongAnswer .setEnabled(true);
            }
        });

        buttonsPanel1 = new JPanel(new FlowLayout());
        add(currentWord, BorderLayout.NORTH);
        add(translation, BorderLayout.WEST);
        buttonsPanel1.add(showAnswer);
        buttonsPanel1.add(rightAnswer);
        buttonsPanel1.add(wrongAnswer);
        buttonsPanel1.add(openFile);
        buttonsPanel1.add(restart);
        add(buttonsPanel1, BorderLayout.SOUTH);
    }

    private void updateWordsCounter() {
        this.setTitle("Success rate: " + Integer.toString(this.right) + "/" + Integer.toString(this.total));
    }

    private void updateCurrentWord() {
        curWordIndex = words.randomInt();
        if (curWordIndex < 0) {
            this        .setTitle("Your result: " + Integer.toString(this.right) + "/" + Integer.toString(this.total));
            showAnswer  .setEnabled(false);
            rightAnswer .setEnabled(false);
            wrongAnswer .setEnabled(false);
            restart     .setEnabled(true);
            return;
        }
        buff = words.getRussian(curWordIndex);
        currentWord.setText(" " + buff);
    }

    private void showTranslation() {
        translation.setText(" " + words.getEnglish(curWordIndex));
        this.pack();
    }
}
