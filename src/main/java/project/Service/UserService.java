package project.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.Repository.UserRepository;
import project.Util.APIResponse;
import project.model.Pieces;
import project.model.User;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    final UserRepository userRepository;

    @Getter
    @Setter
    @AllArgsConstructor
    public class LoginResponse{

        public enum Role{
            ADMIN,USER
        }
        private boolean result;
        private Role role;
        private String Message;

    }
    public ResponseEntity<APIResponse<User>> SaveEntity(User u){
        User saveduser;
        try {
            saveduser=userRepository.save(u);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("Echec operation"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>(saveduser));

    }


    //GetMethods

    public List<User> GetUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<LoginResponse> GetLoginPermission(String user,String password){
        Optional<User> optionalUser=userRepository.findByUsername(user);
        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponse(false,null,"User Not Found"));
        User userobj=optionalUser.get();
        if (!password.equals(userobj.getPassword()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new LoginResponse(false,userobj.getIsadmin()? LoginResponse.Role.ADMIN: LoginResponse.Role.USER,"Password incorrect"));
        if (userobj.getIsadmin())
            return ResponseEntity.ok().body(new LoginResponse(true, LoginResponse.Role.ADMIN,"Welcome Admin"));

        return ResponseEntity.ok().body(new LoginResponse(true, LoginResponse.Role.USER,"Welcome User"));
    }


    //POSTMETHODS

    public ResponseEntity<APIResponse<User>> AddUser(User user){
        if (user.getUsername()==null||user.getPassword()==null||user.getIsadmin()==null)
            return ResponseEntity.badRequest().body(new APIResponse<>("Fill all fields"));
        List<User> users=userRepository.findAll();
        if(users.stream().anyMatch(u->u.getUsername().equalsIgnoreCase(user.getUsername())))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse<>("username already taken"));
        return SaveEntity(user);
    }

    //PUTMETHODS

    public ResponseEntity<APIResponse<User>> ResetPassword(String name,String newPassword){
        Optional<User> optionalUser=userRepository.findByUsername(name);
        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("User Not Found"));
        User user=optionalUser.get();
        user.setPassword(newPassword);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("Echec operation"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>(user));

    }


    //DELETEMETHODS
    public ResponseEntity<APIResponse<User>> DeleteUser(String username){
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("User Not Found"));

        try {
            userRepository.delete(optionalUser.get());
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("Echec operation"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>(optionalUser.get()));

    }


}
