public class StudentDashboard {

    public void displayCourseStatus(String studentId, String courseId, EligibilityRule rule) {
        System.out.println("Checking enrollment status for " + studentId + " in " + courseId + "...");
        try {
            if (rule.isEligible(studentId, courseId)) {
                System.out.println(" You are enrolled. Access course materials now."); //try this
            } else {
                System.out.println(" You are not eligible for this course.");
            }
        } catch (EnrollmentDeniedException e) {
            System.out.println(" Enrollment denied: " + e.getMessage() + ". Please contact support."); // if not print exception message
        } finally {
            System.out.println("Status check completed for " + studentId + ".");
        }
    }

    public static void main(String[] args) {
        StudentDashboard dashboard = new StudentDashboard();

        EligibilityRule rule = (studentId, courseId) -> {
            if (studentId.equals("SKILL999")) {
                throw new EnrollmentDeniedException("Student account suspended due to outstanding fees, Aashish!");
            }
            if (courseId.equals("JAVA101")) {
                if (!studentId.startsWith("SKILL")) {
                    throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Krishna!");
                }
                return true;
            }
            return false;
        };

        dashboard.displayCourseStatus("SKILL123", "JAVA101", rule);    
        dashboard.displayCourseStatus("SKILL999", "PYTHON202", rule);  
        dashboard.displayCourseStatus("STUDENT001", "JAVA101", rule);  
    }
}
