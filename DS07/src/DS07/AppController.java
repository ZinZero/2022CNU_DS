package DS07;

import java.util.Iterator;

public class AppController {
    // 상수
    private static final int VALID_MAX_SCORE = 100;
    private static final int VALID_MIN_SCORE = 0;
    private static final int BAN_CAPACITY = 10;

    // 비공개 인스턴스 변수
    private Ban _ban;
    private GradeCounter _gradeCounter;

    // getter, setter
    private Ban ban() {
        return this._ban;
    }

    private void setBan(Ban newBan) {
        this._ban = newBan;
    }

    private GradeCounter gradeCounter() {
        return this._gradeCounter;
    }

    private void setGradeCounter(GradeCounter newGradeCounter) {
        this._gradeCounter = newGradeCounter;
    }

    //생성자
    public AppController() {

    }

    // 공개함수
    public void run() {
        AppView.outputLine("");
        AppView.outputLine("<<< 학급 성적 처리를 시작합니다.>>>");

        this.setBan(new Ban((AppController.BAN_CAPACITY)));
        this.inputAndStoreStudents();
        if (this.ban().isEmpty()) {
            AppView.outputLine("");
            AppView.outputLine("(경고) 입력된 성적이 없습니다.");
        } else {
            this.showStatistics();
            this.showGradeCounts();
            this.showStudentsSortedByScore();
        }
        AppView.outputLine("");
        AppView.outputLine("<<< 학급 성적 처리를 종료합니다.>>>");
    }

    // 비공개함수
    // static 함수
    private static boolean scoreIsValid(int aScore) {
        return (aScore >= AppController.VALID_MIN_SCORE &&
                aScore <= AppController.VALID_MAX_SCORE);
    }

    private static Student inputStudent() {
        int score = AppView.inputScore();
        while (!AppController.scoreIsValid(score)) {
            AppView.outputLine("[오류] " +
                    AppController.VALID_MIN_SCORE + " 보다 작거나 " +
                    AppController.VALID_MAX_SCORE + " 보다 커서, 정상적인 점수가 아닙니다.");
            score = AppView.inputScore();
        }
        Student student = new Student();
        student.setScore(score);
        return student;
    }

    private void inputAndStoreStudents() {
        AppView.outputLine("");
        boolean storingAsStudentWasSuccessful = true;
        while (storingAsStudentWasSuccessful && AppView.doesContinueToInputStudent()) {
            Student student = AppController.inputStudent();
            if (!this.ban().add(student)) {
                AppView.outputLine("(경고) 입력에 오류가 있습니다. 학급에 더 이상 학생을 넣을 공간이 없습니다.");
                storingAsStudentWasSuccessful = false;
            }
        }
        AppView.outputLine("! 성적 입력을 마칩니다.");
    }

    private void showStatistics() {
        AppView.outputLine("");
        AppView.outputLine("[학급 성적 통계]");

        AppView.outputNumberOfStudents(this.ban().size());
        AppView.outputHighestScore(this.ban().highest().score());
        AppView.outputLowestScore(this.ban().lowest().score());
        AppView.outputAverageScore(this.ban().average());
        AppView.outputNumberOfStudentAboveAverage(this.ban().numberOfStudentAboveAverage());
    }

    private void showGradeCounts() {
        AppView.outputLine("");
        AppView.outputLine("[학점별 학생 수]");

        this.setGradeCounter(this.ban().counterGrades());
        AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA());
        AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB());
        AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC());
        AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD());
        AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF());
    }

    private void showStudentsSortedByScore() {
        AppView.outputLine("");
        AppView.outputLine("[학생들의 성적순 목록]");

        this.ban().sortByScore();

        Iterator<Student> iterator = this.ban().iterator();
        Student student = null;
        while (iterator.hasNext()) {
            student = iterator.next();
            AppView.outputScore(student.score());
        }
    }

}
