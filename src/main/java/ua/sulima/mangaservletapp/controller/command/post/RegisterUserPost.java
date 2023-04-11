package ua.sulima.mangaservletapp.controller.command.post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.controller.command.Command;
import ua.sulima.mangaservletapp.entity.Role;
import ua.sulima.mangaservletapp.entity.User;
import ua.sulima.mangaservletapp.service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RegisterUserPost implements Command {

    private final UserService userService;
    private final String page = "register.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing register post command");
        String[] validationFields = {"register_nickname",
                "register_email",
                "register_password"};

        boolean passValue = Arrays.stream(validationFields)
                .map(request::getParameter)
                .allMatch(this::isEmpty);

        if (!passValue)
            return page;
        String nickname = request.getParameter("register_nickname");
        String email = request.getParameter("register_email");
        String password = request.getParameter("register_password");
        password = BCrypt.hashpw(password, BCrypt.gensalt());

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(Role.builder()
                .id((short)1)
                .name("ROLE_USER")
                .build());
        User userToRegister = User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .image(null)
                .updated(new Timestamp(System.currentTimeMillis()))
                .roles(userRoles)
                .build();
        try {
            Long newUserId = userService.saveUser(userToRegister);
            log.info(String.format("User registration success -> %d, %s", newUserId, email));
        }catch (RuntimeException ex) {
            log.error(String.format("Unable to register user -> %s", ex));
            request.setAttribute("registration_error", ex.getMessage());
            return page;
        }

        return "/redirect:" + GetEndpoint.LOGIN_PAGE;
    }

    private boolean isEmpty(String value) {
        if (value == null) return false;
        else if ("".equals(value)) return false;
        return true;
    }
}
