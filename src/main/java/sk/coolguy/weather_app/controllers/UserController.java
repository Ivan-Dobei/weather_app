package sk.coolguy.weather_app.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.coolguy.weather_app.dao.UserDAO;
import sk.coolguy.weather_app.entity.Sessions;
import sk.coolguy.weather_app.entity.User;
import sk.coolguy.weather_app.exeptions.ErrorResponse;
import sk.coolguy.weather_app.exeptions.UserAlreadyExistsException;
import sk.coolguy.weather_app.service.SessionService;
import sk.coolguy.weather_app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserService userService, UserDAO userDAO, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        try {
            User user = userDAO.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody User user) {
        try {
            userService.registration(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response) {
        try {
            String userId = userService.login(username, password);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            Sessions newSession = new Sessions();
            String sessionId = newSession.getSessionId();
            newSession.setUserId(Integer.parseInt(userId));
            sessionService.createSession(newSession);

            Cookie cookie = new Cookie("SESSIONID", sessionId);
            cookie.setMaxAge(newSession.getExpireAt() * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            //       .body(new ErrorResponse("Login failed"));
            throw new RuntimeException(e.getMessage());
        }
    }
}
