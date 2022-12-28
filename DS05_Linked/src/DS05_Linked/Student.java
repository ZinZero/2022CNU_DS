package DS05_Linked;

public class Student {
    private static final int DEFAULT_SCORE = 0;
    private int _score;

    public int score() {

        return this._score;
    }
    public void setScore(int newScore) {

        this._score = newScore;
    }

    public Student() {

        this.setScore(Student.DEFAULT_SCORE);
    }
    public Student(int givenScore) {
        this.setScore(givenScore);
    }

    @Override
    public boolean equals(Object aStudent) {
        if (aStudent.getClass() != Student.class) {
            return false;
        } else {
            return (this.score() == (((Student) aStudent).score()));
        }
    }

}
