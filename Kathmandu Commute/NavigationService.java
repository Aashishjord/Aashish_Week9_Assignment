
public interface NavigationService {   //It is causing error might know later. //Problem was due to two classes in the same file
    void navigate(String startPoint, String endPoint, RouteValidator validator)
        throws NavigationFailedException;
}
