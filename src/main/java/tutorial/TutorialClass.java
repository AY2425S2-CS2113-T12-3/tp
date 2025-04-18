package tutorial;

import students.StudentList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TutorialClass {
    private String tutorialName;
    private StudentList studentList=new StudentList();
    private LocalTime startTime=null;
    //only this is initialised to null deliberately such that:
    // when .getTutorialByName(name) method from TutorialClassList is called,
    //the new tutorial class created has startTime of null. and we can use if(tutorial.getStartTime() == null)
    // to see that there is no existing tutorial with the name
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;

    public TutorialClass(String tutorialName){
        this.tutorialName = tutorialName;
    }


    public TutorialClass(){
    }

    public TutorialClass(String tutorialName, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.tutorialName = tutorialName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studentList = new StudentList(); // Initialize with an empty student list
    }

    public String getTutorialName() {
        return tutorialName;
    }

    public void setTutorialName(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Checks if this tutorial class is happening today.
     *
     * @param today The current date.
     * @return true if the class is on the same day of the week as today, false otherwise.
     */
    public boolean isHappeningToday(LocalDate today) {
        return today.getDayOfWeek() == this.dayOfWeek;
    }

    @Override
    public String toString() {
        return "tutorialName: " + tutorialName +
                ", dayOfWeek: " + dayOfWeek +
                ", from: " + startTime +
                ", to: " + endTime +
                ", studentList: " + studentList;
    }
}
