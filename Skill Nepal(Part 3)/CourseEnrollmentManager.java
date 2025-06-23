public class CourseEnrollmentManager {

    public static void enrollStudent(String studentId, String courseId, EligibilityRule rule) {
        System.out.println("Attempting to enroll " + studentId + " in " + courseId + "...");
        try {
            if (rule.isEligible(studentId, courseId)) {
                System.out.println("Enrollment successful of" + studentId + " in " + courseId + "! Happy learning!");
            } else {
                System.out.println("Enrollment failed for " + studentId + ": not eligible for  course.");
            }
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for " + studentId + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EligibilityRule rule = (studentId, courseId) -> {
            if (studentId.equals("SKILL999")) {
                throw new EnrollmentDeniedException("Student account suspended due to outstanding fees,Aashish!");
            }
            if (courseId.equals("JAVA101")) {
                if (!studentId.startsWith("SKILL")) {
                    throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix,Krishna!");
                }
                return true;
            }
            return false;
        };

        enrollStudent("SKILL123", "JAVA101", rule);      
        enrollStudent("SKILL999", "JAVA101", rule);    
        enrollStudent("USR456", "JAVA101", rule);      
        enrollStudent("SKILL777", "PYTHON202", rule);   
    }
}
