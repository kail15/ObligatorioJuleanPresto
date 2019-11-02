
package obligatorio.vista.web.utils;

import com.google.gson.Gson;
import obligatorio.vista.web.dto.WsMessageDTO;

//OJP
public class MessageConverter {    
    public static String toMessage(WsMessageDTO msg){
        Gson gson = new Gson();
        String message = gson.toJson(msg);
        return message;
    }    
}
