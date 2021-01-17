package sk.jarina.reservationsvaiibackend.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
public class UserController {

    @GetMapping
    public String hello(){
        return "Hello World!";
    }
}
