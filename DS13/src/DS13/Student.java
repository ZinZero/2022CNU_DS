package DS13;

public class Student implements Comparable<Student> {
    // 상수
    private static final int DEFAULT_SCORE = 0;

    // 비공개 인스턴스
    private int _score;

    // getter, setter
    public int score() {
        return this._score;
    }

    public void setScore(int newScore) {
        this._score = newScore;
    }

    // 생성자
    public Student() {
        this.setScore(DEFAULT_SCORE);
    }

    public Student(int givenScore) {
        this.setScore(givenScore);
    }

    @Override
    public int compareTo(Student other) {
        if (this.score() < other.score()) {
            return -1;
        } else if (this.score() == other.score()) {
            return 0;
        } else {
            return +1;
        }
    }

}
