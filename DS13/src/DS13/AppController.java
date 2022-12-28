package DS13;

public class AppController {
    // 상수
    private static final int VALID_MAX_SCORE = 100;     // 최대 점수
    private static final int VALID_MIN_SCORE = 0;       // 최소 점수
    private static final int BAN_CAPACITY = 10;         // 반의 크기
    private static final int VALID_MAX_ID = 9;          // 학번의 최대 길이

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

    // 생성자
    public AppController() {

    }

    // 공개함수
    public void run() {
        AppView.outputLine("");
        AppView.outputLine("<<< 성적 처리를 시작합니다 >>>");

        this.setBan(new Ban());
        this.inputAndStoreStudents();
        if (this.ban().isEmpty()) {
            AppView.outputLine("");
            AppView.outputLine("(오류) 학생 정보가 전혀 입력되지 않았습니다.");
        } else {
            this.showStudentList();
            this.showStatistics();
            this.showGradeCounts();
            this.showStudentInfoSortedByScore();
        }
        AppView.outputLine("");
        AppView.outputLine("<<< 성적 처리를 종료합니다 >>>");

    }

    // 비공개함수

    private static boolean idIsValid(String aId) {   // 학번 길이 판단
        return (aId.length() <= AppController.VALID_MAX_ID);
    }

    private static String inputId() {   // 학번 받기
        String studentId = AppView.inputStudentId();
        while (!AppController.idIsValid(studentId)) {
            AppView.outputLine("[오류] 학번의 길이가 너무 깁니다. 최대 " + AppController.VALID_MAX_ID + " 입니다.");
            studentId = AppView.inputStudentId();
        }
        return studentId;
    }

    private static boolean scoreIsValid(int aScore) {    // 유효 점수 범위
        return (aScore >= AppController.VALID_MIN_SCORE &&
                aScore <= AppController.VALID_MAX_SCORE);
    }

    private static Student inputStudent() {     //  학생 점수 받기
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
            String studentId = String.valueOf(AppController.inputId());
            Student student = AppController.inputStudent();
            if (!this.ban().addKeyAndObject(studentId, student)) {
                AppView.outputLine("(경고) 입력에 오류가 있습니다. 학급에 더 이상 학생을 넣을 공간이 없습니다.");
                storingAsStudentWasSuccessful = false;
            }
        }
        AppView.outputLine("! 성적 입력을 마칩니다.");
    }

    private void showStudentList(){
        AppView.outputLine("");
        AppView.outputLine("[학생 목록]");

        Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator();

        DictionaryElement<String, Student> student = null;
        String studentId = null;
        int studentScore;
        while (iterator.hasNext()){
            student = iterator.next();
            studentId = student.key();
            studentScore = student.object().score();
            AppView.outputStudentList(studentId, studentScore);
        }
    }

    private void showStatistics() {
        AppView.outputLine("");
        AppView.outputLine("[학급 성적 통계]");
        AppView.outputTotalNumberOfStudents(this.ban().size());
        AppView.outputAverageScore(this.ban().average());
        AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage());
        AppView.outputHighestScore(this.ban().highest().score());
        AppView.outputLowestSore(this.ban().lowest().score());

    }

    private void showGradeCounts() {
        AppView.outputLine("");
        AppView.outputLine("[학점별 학생 수]");

        this.setGradeCounter(this.ban().countGrades());
        AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA());
        AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB());
        AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC());
        AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD());
        AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF());
    }

    private void showStudentInfoSortedByScore() {
        AppView.outputLine("");
        AppView.outputLine("[학생들의 학번순 목록]");

        this.ban().studentsSortedByScore();

        Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator();

        DictionaryElement<String, Student> student = null;
        String studentId = null;
        int studentScore;
        char studentGrade;

        while (iterator.hasNext()) {
            student = iterator.next();
            studentId = student.key();
            studentScore = student.object().score();
            studentGrade = Ban.scoreToGrade(student.object().score());
            AppView.outputStudentInfo(studentId, studentScore, studentGrade);
        }

    }
}
