package uz.alif.click_up_clone.enums;

public enum WorkspaceRoleName {
    ROLE_OWNER("owner", "admin"),
    ROLE_ADMIN("admin", "moderator"),
    ROLE_MEMBER("member", "user"),
    ROLE_GUEST("guest", "user");

    public final String name;
    public final String description;

    WorkspaceRoleName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
