package cz.muni.fi.pa165.dndtroops.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jiří Novotný
 */

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource already exists")
public class ResourceAlreadyExistingException extends RuntimeException {}
