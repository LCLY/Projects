
import javax.swing.JOptionPane;

public class FAFSAGUI {
    public static void main(String[] args) {
        boolean isAcceptedStudent;
        boolean isSSregistered;
        boolean hasSSN;
        boolean hasValidResidency;
        boolean isDependent;
        boolean repeat = false;
        boolean result = false;
        int age;
        int creditHours;
        double studentIncome;
        double parentIncome;
        String classStanding = null;
        String[] options = {"YES", "NO"};
        String[] selections = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
        do {
            JOptionPane.showMessageDialog(null, "Welcome to the FAFSA!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

            int selectedOption = JOptionPane.showOptionDialog(null, "Have you been accepted into a degree or " +
                            "certificate program?", "Program Acceptance", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);

            if (selectedOption == JOptionPane.YES_OPTION) {
                isAcceptedStudent = true;
            } else {
                isAcceptedStudent = false;
            }
            selectedOption = JOptionPane.showOptionDialog(null, "Are you registered for the selective service?"
                    , "Selective Service", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (selectedOption == JOptionPane.YES_OPTION) {
                isSSregistered = true;
            } else {
                isSSregistered = false;
            }
            selectedOption = JOptionPane.showOptionDialog(null, "Do you have a social security number?", "Social" +
                    "Security Number", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (selectedOption == JOptionPane.YES_OPTION) {
                hasSSN = true;
            } else {
                hasSSN = false;
            }
            selectedOption = JOptionPane.showOptionDialog(null, "Do you have valid residency status?",
                    "Residency Status", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (selectedOption == JOptionPane.YES_OPTION) {
                hasValidResidency = true;
            } else {
                hasValidResidency = false;
            }
            do {
                age = Integer.valueOf(JOptionPane.showInputDialog(null, "How old are you?", "Age",
                        JOptionPane.QUESTION_MESSAGE));
                if (age < 0) {
                    JOptionPane.showMessageDialog(null, "Age cannot be a negative number", "Error: Age",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    result = true;
                }
            } while (!result);
            do {
                creditHours = Integer.valueOf(JOptionPane.showInputDialog(null, "How many credit hours do you " +
                        "plan on taking?", "Credit Hours", JOptionPane.QUESTION_MESSAGE));
                if (creditHours < 1 || creditHours > 24) {
                    result = false;
                    JOptionPane.showMessageDialog(null, "Credit hours must be between 1 and 24, inclusive.",
                            "Error: Credit Hours", JOptionPane.ERROR_MESSAGE);
                } else {
                    result = true;
                }
            } while (!result);

            do {
                studentIncome = Double.valueOf(JOptionPane.showInputDialog(null, "What is your total yearly income?",
                        "Student Income", JOptionPane.QUESTION_MESSAGE));
                if (studentIncome < 0) {
                    JOptionPane.showMessageDialog(null, "Income cannot be a negative number.", "Error: Student Income",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    result = true;
                }
            } while (!result);


            do {
                parentIncome = Double.valueOf(JOptionPane.showInputDialog(null, "What is your parent's total yearly" +
                        " income?", "Error: Parent Income", JOptionPane.QUESTION_MESSAGE));
                if (parentIncome < 0) {
                    JOptionPane.showMessageDialog(null, "Income cannot be a negative number.", "Error: Parent Income",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    result = true;
                }
            } while (!result);

            selectedOption = JOptionPane.showOptionDialog(null, "Are you a dependent?", "Dependency", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (selectedOption == JOptionPane.YES_OPTION) {
                isDependent = true;
            } else {
                isDependent = false;
            }

            String selection = (String) JOptionPane.showInputDialog(null, "What is your current class standing?", "Class " +
                    "Standing", JOptionPane.PLAIN_MESSAGE, null, selections, null);
            if (selection.equals("Freshmen") || selection.equals("Sophomore") || selection.equals("Junior") || selection
                    .equals("Senior")) {
                classStanding = "Undergraduate";
            } else if (selection.equals("Graduate")) {
                classStanding = "Graduate";
            }
            FAFSA student = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN,
                    hasValidResidency, isDependent, age, creditHours,
                    studentIncome, parentIncome, classStanding);

            JOptionPane.showMessageDialog(null, "Loans: $" + student.calcStaffordLoan() + "\nGrants: $" + student
                    .calcFederalGrant() + "\nWorkStudy: $" + student.calcWorkStudy() + "\n----------------\nTotal: $" +
                    student.calcFederalAidAmount(), "FAFSA Results", JOptionPane.INFORMATION_MESSAGE);
            selectedOption = JOptionPane.showOptionDialog(null, "Would you like to complete another Application?", "Continue", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (selectedOption == JOptionPane.YES_OPTION) {
                repeat = true;
            }

        } while (repeat);

    }
}
