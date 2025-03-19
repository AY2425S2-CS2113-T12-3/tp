package Command.studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;
import Command.taskCommands.Command;
import java.util.logging.*;

/**
 * Represents the "FIND_STUDENT" command that searches for students by partial matching of name or matric number
 * within a tutorial class list. The command displays the students that match the keyword.
 */
public class FindStudentCommand implements Command<TutorialClassList> {

    // Logger instance for this class
    private static final Logger logger = Logger.getLogger(FindStudentCommand.class.getName());

    /**
     * Executes the "FIND_STUDENT" command by searching for students that partially match the given keyword across all tutorial classes.
     * It prints the matching students to the user.
     *
     * @param parts The keyword used to search for students (name or matric number).
     * @param tutorialClassList The list of tutorial classes to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {
            // Log the start of the command execution
            logger.log(Level.INFO, "Executing FIND_STUDENT command with keyword: " + parts);

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidFindStudentCommand();
            }

            // Variable to track whether any student is found
            boolean studentFound = false;

            // Iterate through each tutorial class
            for (TutorialClass tutorialClass : tutorialClassList.getTutorialClasses()) {
                // Get the student list from the tutorial class
                StudentList studentList = tutorialClass.getStudentList();

                // Search for students by name or matric number using partial matching
                boolean foundInThisClass = false;
                for (Student student : studentList.getStudents()) {
                    boolean nameMatches = student.getName().toLowerCase().contains(parts.toLowerCase());
                    boolean matricMatches = student.getMatricNumber().toLowerCase().contains(parts.toLowerCase());

                    if (nameMatches || matricMatches) {
                        if (!foundInThisClass) {
                            // If student is found, print the tutorial class name first
                            studentFound = true;
                            System.out.println("In tutorial " + tutorialClass.getTutorialName() + ":");
                            foundInThisClass = true;
                        }
                        // Print the student details
                        System.out.println(student);
                    }
                }
            }

            // If no students are found across all tutorial classes
            if (!studentFound) {
                logger.log(Level.WARNING, "No students found with keyword: " + parts);
                System.out.println("No students found with the keyword: " + parts);
            }
        } catch (TASyncException e) {
            logger.log(Level.SEVERE, "Error executing FIND_STUDENT command: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
