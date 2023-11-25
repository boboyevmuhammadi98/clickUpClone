package uz.alif.click_up_clone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.SystemRoleName;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractUUIDEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLetter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    private boolean isEnabled = false;

    @JsonIgnore
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private SystemRoleName systemRoleName;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getSystemRoleName().name);
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    public User(String fullName, String email, String password, String color, String verificationCode, SystemRoleName systemRoleName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.color = color;
        this.verificationCode = verificationCode;
        this.systemRoleName = systemRoleName;
    }

    @PrePersist
    @PreUpdate
    public void initialLetterInitiator(){
        this.setInitialLetter(this.getFullName().substring(0, 1));
    }
}
