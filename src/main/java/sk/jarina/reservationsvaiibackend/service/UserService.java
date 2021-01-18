package sk.jarina.reservationsvaiibackend.service;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.jarina.reservationsvaiibackend.dao.UserDao;
import sk.jarina.reservationsvaiibackend.dao.UserDaoImpl;
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
        if (Iterables.size(user) != 0){
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
        User user = new User(
                UUID.randomUUID(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPassword(),
                userDto.getBirthDate(),
                userDto.getAccountType()
        );
        userDao.save(user);
    }

    public Iterable<User> findUserByEmail(String email){
        return userDao.findByEmail(email);
    }

}
