package uz.alif.click_up_clone.enums;

public enum SystemRoleName {
    SYSTEM_ADMIN("admin", "admin"),
    SYSTEM_MODERATOR("moderator", "moderator"),
    SYSTEM_USER("user", "user");

    public final String name;
    public final String description;

    SystemRoleName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
