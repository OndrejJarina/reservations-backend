package sk.jarina.reservationsvaiibackend.api;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sk.jarina.reservationsvaiibackend.model.User;
import sk.jarina.reservationsvaiibackend.model.UserDto;
import sk.jarina.reservationsvaiibackend.security.AuthenticationRequest;
import sk.jarina.reservationsvaiibackend.security.AuthenticationResponse;
import sk.jarina.reservationsvaiibackend.security.JwtUtil;
import sk.jarina.reservationsvaiibackend.service.UserService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("api/auth")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping(value = "/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        System.out.println(authenticationRequest.getEmail());
        System.out.println(authenticationRequest.getPassword());
        try{
            System.out.println("dva");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail().toLowerCase(), authenticationRequest.getPassword())
            );
            System.out.println("styri");

            final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            System.out.println("pat");
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        catch (BadCredentialsException ex) {
            System.out.println("tri");
            throw new Exception("Incorrect username or password", ex);
        }
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest){
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();
        User user = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "logged in succesfuly!");
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", authenticationRequest.getEmail());
        model.put("token", token);
        //model.put("role", roles);

        return ok(model);


    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        Iterable<User> foundUsers = userService.findUserByEmail(userDto.getEmail());
        if (Iterables.size(foundUsers) != 0) {
            throw new BadCredentialsException("User exists");
        }
        userDto.setToken(UUID.randomUUID().toString());
        userService.createNewUserFromDto(userDto);

        Map<Object, Object> map = new HashMap<>();
        map.put("message", "New user created successfully!");
        return ResponseEntity.ok(map);
    }
}
