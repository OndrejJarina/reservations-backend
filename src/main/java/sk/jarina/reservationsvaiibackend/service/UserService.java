package sk.jarina.reservationsvaiibackend.service;

import com.google.common.collect.Iterables;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.jarina.reservationsvaiibackend.dao.UserDao;
import sk.jarina.reservationsvaiibackend.model.User;
import sk.jarina.reservationsvaiibackend.model.UserDto;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(@Qualifier("UserDao") UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  return new org.springframework.security.core.userdetails.User("foo", "foo", new ArrayList<>());
        Iterable<User> user = userDao.findByEmail(username);
        if (Iterables.size(user) != 0) {
            User foundUser = Iterables.get(user, 0);
            List<GrantedAuthority> authorities = getUserGrantedAuthority(foundUser.getAccountType().toString());
            return createUserDetailsForAuth(foundUser, authorities);
        } else {
            throw new UsernameNotFoundException("not found: user!");
        }
    }

    private List<GrantedAuthority> getUserGrantedAuthority(String roles) {

        Set<GrantedAuthority> newRoles = new HashSet<>();
        newRoles.add(new SimpleGrantedAuthority(roles));
        return new ArrayList<>(newRoles);
    }

    private UserDetails createUserDetailsForAuth(User user, List<GrantedAuthority> authorityList) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorityList);
    }

    public void createNewUserFromDto(UserDto userDto) {
        String passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
        User user = new User(
                UUID.randomUUID(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getSurname(),
                passwordHash,
                userDto.getBirthDate(),
                userDto.getAccountType()
        );
        userDao.save(user);
    }

    public Iterable<User> findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User validateUser(String email, String password) {
        if (email != null) {
            email = email.toLowerCase();
        }
        Iterable<User> user1 = userDao.findByEmail(email);
        System.out.println("hele");
        User selected = Iterables.get(user1, 0);
        if (!BCrypt.checkpw(password, selected.getPassword())) {
            throw new UsernameNotFoundException("Invalid email/password");
        }
        return selected;

    }
}
