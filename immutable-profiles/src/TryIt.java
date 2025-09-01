import com.example.profiles.*;

public class TryIt {
    public static void main(String[] args) {
        ProfileService svc = new ProfileService();
        UserProfile p = svc.createMinimal("u1", "a@b.com");
        System.out.println("Email: " + p.getEmail());
        System.out.println("DisplayName before: " + p.getDisplayName());
        UserProfile p2 = svc.withDisplayName(p, "Alice Wonderland");
        System.out.println("DisplayName after:  " + p2.getDisplayName());
        System.out.println("Original unchanged: " + p.getDisplayName());
    }
}
