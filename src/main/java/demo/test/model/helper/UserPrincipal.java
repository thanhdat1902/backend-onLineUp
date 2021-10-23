package demo.test.model.helper;

import demo.test.model.entity.ProfileEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;

    private String email;

    private String password;

    private Set<GrantedAuthority> authorities = new HashSet<>();
    

    public UserPrincipal(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public static UserPrincipal build(ProfileEntity user) {
//        List<GrantedAuthority> listAuthorities = Stream.of(
//                new SimpleGrantedAuthority(user.getRoles())
//        ).collect(Collectors.toList());
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}