package DS13;

public class Ban extends DictionaryByBinarySearchTree<String, Student> {
    // 생상자
    public Ban() {
        super();
    }

    // 공개 함수
    public Student highest() {
        if (this.isEmpty()) {
            return null;
        } else {
            DictionaryElement<String, Student> highestElement = this.highestRecursively(this.root());
            return highestElement.object();
        }
    }

    public Student lowest() {
        if (this.isEmpty()) {
            return null;
        } else {
            DictionaryElement<String, Student> lowestElement = this.lowestRecursively(this.root());
            return lowestElement.object();
        }
    }

    public double average() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return ((double) this.sumOfScores()) / ((double) this.size());
        }
    }

    public int numberOfStudentsAboveAverage() {
        double average = this.average();
        int numberOfStudentAboveAverage = 0;

        Iterator<DictionaryElement<String, Student>> iterator = this.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next().object();
            if (student.score() >= average) {
                numberOfStudentAboveAverage++;
            }
        }
        return numberOfStudentAboveAverage;
    }

    public GradeCounter countGrades() {
        char grade;
        GradeCounter gradeCounter = new GradeCounter();
        Iterator<DictionaryElement<String, Student>> iterator = this.iterator();
        while (iterator.hasNext()) {
            grade = Ban.scoreToGrade(iterator.next().object().score());
            gradeCounter.count(grade);
        }
        return gradeCounter;
    }

    public static char scoreToGrade(int aScore) {
        if (aScore >= 90) {
            return 'A';
        } else if (aScore >= 80) {
            return 'B';
        } else if (aScore >= 70) {
            return 'C';
        } else if (aScore >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public Student[] studentsSortedByScore() {
        @SuppressWarnings("unchecked")
        Student[] students = new Student[this.size()];
        Iterator<DictionaryElement<String, Student>> iterator = this.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            students[i] = iterator.next().object();
        }
        Sort<Student> quicksort = new QuickSort<Student>();
        quicksort.sort(students, this.size());
        return students;
    }

    // 비공개 함수
    private DictionaryElement<String, Student> lowestRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
        DictionaryElement<String, Student> lowest = aRoot.element();
        if (aRoot.left() != null) {
            DictionaryElement<String, Student> lowestOfLeftSubtree = this.lowestRecursively(aRoot.left());
            if (lowestOfLeftSubtree.object().score() < lowest.object().score()) {
                lowest = lowestOfLeftSubtree;
            }
        }
        if (aRoot.right() != null) {
            DictionaryElement<String, Student> lowestOfRightSubtree = this.lowestRecursively(aRoot.right());
            if (lowestOfRightSubtree.object().score() < lowest.object().score()) {
                lowest = lowestOfRightSubtree;
            }
        }
        return lowest;
    }


    private DictionaryElement<String, Student> highestRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
        DictionaryElement<String, Student> highest = aRoot.element();
        if (aRoot.left() != null) {
            DictionaryElement<String, Student> highestOfLeftSubtree = this.highestRecursively(aRoot.left());
            if (highestOfLeftSubtree.object().score() > highest.object().score()) {
                highest = highestOfLeftSubtree;
            }
        }
        if (aRoot.right() != null) {
            DictionaryElement<String, Student> highestOfRightSubtree = this.highestRecursively(aRoot.right());
            if (highestOfRightSubtree.object().score() > highest.object().score()) {
                highest = highestOfRightSubtree;
            }
        }
        return highest;
    }

    private int sumOfScoresRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
        int sum = aRoot.element().object().score();
        int sumElement = aRoot.element().object().score();
        if (aRoot.left() != null) {
            int sumOfLeftSubtree = this.sumOfScoresRecursively(aRoot.left());
            sum += sumOfLeftSubtree;
            sumElement = sumOfLeftSubtree;
        }
        if (aRoot.right() != null) {
            int sumOfRightSubtree = this.sumOfScoresRecursively(aRoot.right());
            sum += sumOfRightSubtree;
            sumElement = sumOfRightSubtree;
        }
        return sum;
    }

    private int sumOfScores() {
        return this.sumOfScoresRecursively(this.root());
    }

}
