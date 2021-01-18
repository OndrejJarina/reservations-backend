package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.jarina.reservationsvaiibackend.model.User;
import sk.jarina.reservationsvaiibackend.model.UserMapper;

public class UserDaoImpl{

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public UserDaoImpl() {
    }

    public Iterable<User> findByEmail(String userEmail){
        //return (User) jdbcTemplate.queryForObject("select * from users where email = ?", new Object[]{userEmail}, userMapper);
        return jdbcTemplate.query("select * from users where email = ?", userMapper, userEmail);
    }
}

