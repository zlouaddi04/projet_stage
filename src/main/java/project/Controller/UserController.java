package project.Controller;

import lombok.AllArgsConstructor;
import org.hibernate.metamodel.internal.AbstractPojoInstantiator;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Service.UserService;
import project.Util.APIResponse;
import project.model.User;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
public class UserController {
    final UserService userService;


    //GETMETHODS
    @GetMapping("/getall")
    public List<User> GetUsers(){
        return userService.GetUsers();
    }

    @GetMapping("/Login/{user}/{key}")
    public ResponseEntity<UserService.LoginResponse> GetLoginPermission(
            @PathVariable String user,
            @PathVariable String key
            ){
        return userService.GetLoginPermission(user,key);
    }

    //POTSMETHODS

    @PostMapping("/add")
    public ResponseEntity<APIResponse<User>> AddUser(@RequestBody User user){
        return userService.AddUser(user);
    }


    //PUTMETHODS

    @PutMapping("/resetpassword/{name}/{newpassword}")
    public ResponseEntity<APIResponse<User>> ResetPassword(
            @PathVariable String name,
            @PathVariable String newpassword
            ){
        return userService.ResetPassword(name,newpassword);
    }

    //DELETEMETHODS

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<APIResponse<User>> DeleteUser(@PathVariable String username){
        return  userService.DeleteUser(username);
    }
}

