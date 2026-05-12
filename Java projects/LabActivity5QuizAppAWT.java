import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener, ItemListener {

    Label topMessageLabel;
    Label botMessageLabel;
    CheckboxGroup choicesGroup;
    Checkbox choiceA, choiceB, choiceC, choiceD;
    Button submitButton;

    String[] questions = {
        "Which component is considered the brain of a computer?",
        "Instructions that a computer follows to perform a task?",
        "Which of these is used for temporary data storage?"
    };
    String[][] options = {
        {"RAM", "CPU", "GPU", "HDD"},
        {"Data", "Hardware", "Program", "Network"},
        {"ROM", "SSD", "RAM", "USB"}
    };
    char[] correctAnswers = {'B', 'C', 'C'};

    int currentQuestionIndex = 0;
    int score = 0;

    public LabActivity5QuizAppAWT() {
        setTitle("AWT Quiz Application");
        setResizable(false);
        setSize(500, 300);
        setLayout(null);

        topMessageLabel = new Label("");
        topMessageLabel.setAlignment(Label.CENTER);
        topMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topMessageLabel.setBounds(20, 40, 460, 25);
        add(topMessageLabel);

        botMessageLabel = new Label("");
        botMessageLabel.setAlignment(Label.CENTER);
        botMessageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        botMessageLabel.setBounds(20, 250, 460, 15);
        add(botMessageLabel);

        choicesGroup = new CheckboxGroup();
        choiceA = new Checkbox("", choicesGroup, false);
        choiceB = new Checkbox("", choicesGroup, false);
        choiceC = new Checkbox("", choicesGroup, false);
        choiceD = new Checkbox("", choicesGroup, false);

        choiceA.addItemListener(this);
        choiceB.addItemListener(this);
        choiceC.addItemListener(this);
        choiceD.addItemListener(this);

        int boxWidth = 80, boxHeight = 35;
        choiceA.setBounds(10, 120, boxWidth, boxHeight);
        choiceB.setBounds(230, 120, boxWidth, boxHeight);
        choiceC.setBounds(10, 170, boxWidth, boxHeight);
        choiceD.setBounds(230, 170, boxWidth, boxHeight);

        add(choiceA);
        add(choiceB);
        add(choiceC);
        add(choiceD);

        submitButton = new Button("Next");
        submitButton.setFont(new Font("Arial", Font.BOLD, 15));
        submitButton.setBounds(5, 270, 490, 25);
        submitButton.addActionListener(this);
        add(submitButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        botMessageLabel.setText(""); // Always clear warning message when loading new question
        if (currentQuestionIndex < questions.length) {
            topMessageLabel.setText(questions[currentQuestionIndex]);
            choiceA.setLabel("A) " + options[currentQuestionIndex][0]);
            choiceB.setLabel("B) " + options[currentQuestionIndex][1]);
            choiceC.setLabel("C) " + options[currentQuestionIndex][2]);
            choiceD.setLabel("D) " + options[currentQuestionIndex][3]);
            choicesGroup.setSelectedCheckbox(null);
            choiceA.setEnabled(true);
            choiceB.setEnabled(true);
            choiceC.setEnabled(true);
            choiceD.setEnabled(true);
            submitButton.setEnabled(true);
        } else {
            topMessageLabel.setText("Quiz completed! Your score is " + score + " out of 3");
            choicesGroup.setSelectedCheckbox(null);
            choiceA.setEnabled(false);
            choiceB.setEnabled(false);
            choiceC.setEnabled(false);
            choiceD.setEnabled(false);
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            Checkbox selected = choicesGroup.getSelectedCheckbox();
            if (selected != null) {
                botMessageLabel.setText(""); // Clear warning when answer is selected 
                char selectedAnswer = selected.getLabel().charAt(0);
                if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                    score++;
                }
                currentQuestionIndex++;
                loadQuestion();
            } else {
                botMessageLabel.setText("Please select an answer.");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        botMessageLabel.setText(""); // Always clear warning when any choice is picked
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}