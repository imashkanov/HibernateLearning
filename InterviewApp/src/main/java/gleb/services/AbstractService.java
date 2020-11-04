package gleb.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class AbstractService {

  protected ResponseEntity getResponseEntityOnServerError(Exception e) {

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
      .body("Server error");
  }

}
