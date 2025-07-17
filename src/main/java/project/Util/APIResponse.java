package project.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse<T> {
    private T data;
    private String Message;
    private boolean Success;


    //Success constructor
    public APIResponse(T data) {
        this.data = data;
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
