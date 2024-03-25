package ru.yampolskiy.taskclient.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Класс UserPrincipal представляет принципала пользователя в системе Spring Security.
 */
public class UserPrincipal implements UserDetails {

    private User user;

    /**
     * Конструктор для создания экземпляра UserPrincipal на основе пользователя.
     * @param user Пользователь, для которого создается принципал.
     */
    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Преобразование ролей пользователя в объекты GrantedAuthority
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

    @Override
    public String getPassword() {
        if (user == null) {
            return null;
        }
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Аккаунт не истекает
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Аккаунт не заблокирован
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Учетные данные не истекли
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Аккаунт включен
        return true;
    }
}
