public class StudentMember extends Member {
    private String collegeName;
    private String rollNumber;

    public StudentMember(String memberId, String name, String collegeName, String rollNumber) {
        super(memberId, name);
        this.collegeName = collegeName;
        this.rollNumber = rollNumber;
    }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    @Override
    public String toString() {
        return super.toString() + " | College: " + collegeName + " | Roll No: " + rollNumber;
    }
}