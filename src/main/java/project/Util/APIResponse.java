package project.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse<T> {
    private T data;
    private String Message;
    private boolean Success;


    //Success constructors
    public APIResponse(T data) {
        this.data = data;
        Success = true;
        Message = "Succes Operation";
    }
    public APIResponse() {
        Success = true;
        Message = "Succes Operation";
    }





    //Custom Error constructor
    public APIResponse(String message){
        data=null;
        Success=false;
        Message=message;
    }
}
