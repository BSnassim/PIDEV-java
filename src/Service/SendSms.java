package Service;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 

public class SendSms {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
     

    public static void send(String num,String msg) {
        String ACCOUNT_SID = "AC54d67a5fec24af57973f22464f4b2d95";
        String AUTH_TOKEN = "03c4543640d46bc0fd4e60dde20de29f";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);// cnx m3a serveur api
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber("+1 276 286 2381"),
                msg)
            .create();

        System.out.println(message.getSid());
    }
}