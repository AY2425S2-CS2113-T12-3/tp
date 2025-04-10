package command.attendancelistcommands;

import attendance.AttendanceFile;
import attendance.AttendanceList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

//@@author wongyihao02
public class CreateNewAttendanceListCommand implements Command<ArrayList<Object>> {

    //parts in tutname,week num
    public void execute(String parts, ArrayList<Object> tutAtten) {
        try {
            TutorialClassList tutlist = (TutorialClassList) tutAtten.get(0);
            AttendanceFile attendanceFile = (AttendanceFile) tutAtten.get(1);

            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidListAttendanceListCommand();
            }
            String[] partsArray = parts.split(",");
            for (int i = 0; i < partsArray.length; i++) {
                partsArray[i] = partsArray[i].trim();
            }
            if (partsArray.length != 2) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            if (Integer.parseInt(partsArray[1].trim()) < 0) {
                throw TASyncException.invalidListAttendanceListCommand();
            }

            TutorialClass tutClass = tutlist.getTutorialByName(partsArray[0]);

            if (tutClass == null) {
                throw TASyncException.invalidListAttendanceListCommand();
            }
            AttendanceList attenlist = attendanceFile.getAttendanceByNameAndWeek(
                    Integer.parseInt(partsArray[1].trim()), tutClass.getTutorialName());

            assert tutClass != null : "Exception should be thrown if cannot find tutorial";

            if (attenlist == null) {
                attenlist = new AttendanceList(tutClass, Integer.parseInt(partsArray[1].trim()));
                for (Student student : attenlist.getTutorialClass().getStudentList().getStudents()) {
                    attenlist.markAbsent(student);
                }

                attendanceFile.addAttendanceList(attenlist);
                System.out.println("Attendance List created");
            } else {
                System.out.println("Attendance list for the week already exists");
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("second parameter has to be numbers only");
        }
    }
}
