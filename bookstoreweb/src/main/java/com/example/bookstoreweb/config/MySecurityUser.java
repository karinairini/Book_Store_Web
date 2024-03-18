package com.example.bookstoreweb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
public class MySecurityUser extends User {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private LocalDate birthDate;

    public MySecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                          String firstName, String lastName, String emailAddress, LocalDate birthDate) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "MySecurityUser firstName=" + firstName + ", lastName=" + lastName + ", name=" + fullName + ", emailSAddress=" + emailAddress + ", birthDate=" + birthDate
                + "] " + super.toString();
    }
}
