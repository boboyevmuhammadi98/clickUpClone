package uz.alif.click_up_clone.enums;

public enum AccessType {
    PUBLIC("public", "public"),
    PRIVATE("private", "private");

    public final String name;
    public final String description;

    AccessType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
