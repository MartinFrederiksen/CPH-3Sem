package exceptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExceptionDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
*
* @author Joe
*/
@Provider
public class MissingInputExceptionMapper implements ExceptionMapper<MissingInputException> {
   static Gson gson = new GsonBuilder().setPrettyPrinting().create();
   @Override
   public Response toResponse(MissingInputException ex) {
       Logger.getLogger(MissingInputExceptionMapper.class.getName())
               .log(Level.SEVERE, null, ex);
       ExceptionDTO err = new ExceptionDTO(404, ex.getMessage());
       return Response
               .status(404)
               .entity(gson.toJson(err))
               .type(MediaType.APPLICATION_JSON)
               .build();
   }
}
