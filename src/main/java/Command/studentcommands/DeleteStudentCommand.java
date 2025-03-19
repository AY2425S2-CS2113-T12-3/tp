package Command.studentcommands;

import Command.taskCommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import java.util.logging.Logger;

public class DeleteStudentCommand implements Command<TutorialClassList> {

    /**
     * Executes the "DELETESTUDENT" command by removing a student from the student list of a specific tutorial class.
     * The input string should contain the tutorial class code and the matric number of the student to be deleted.
     * If the input is invalid, the tutorial class is not found, or the student is not found, an appropriate error message is displayed.
     *
     * @param parts            The input string containing the tutorial class code and matric number (format: <TutorialClassCode>,<MatricNumber>).
     * @param tutorialClassList The tutorial class list from which the student will be removed.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidDeleteStudentCommand();
            }

            // Split the input into tutorial class code and matric number
            String[] inputParts = parts.split(",");
            if (inputParts.length != 2) {
                throw TASyncException.invalidDeleteStudentCommand();
            }

            String tutorialClassCode = inputParts[0].trim();
            String matricNumber = inputParts[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getByName(tutorialClassCode);
            if (tutorialClass == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Retrieve the student list from the tutorial class
            StudentList studentList = tutorialClass.getStudentList();

            // Find the student by matric number
            Student studentToRemove = studentList.getStudentByMatricNumber(matricNumber);
            if (studentToRemove != null) {
                // Remove the student
                studentList.removeStudent(studentToRemove);
                System.out.println("Student with matric number " + matricNumber + " has been removed from tutorial class " + tutorialClassCode + ".");
            } else {
                System.out.println("No student found with matric number " + matricNumber + " in tutorial class " + tutorialClassCode + ".");
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}