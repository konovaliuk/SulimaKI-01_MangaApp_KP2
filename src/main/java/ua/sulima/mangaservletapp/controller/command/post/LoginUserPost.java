package ua.sulima.mangaservletapp.controller.command.post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.controller.command.Command;
import ua.sulima.mangaservletapp.entity.User;
import ua.sulima.mangaservletapp.service.UserService;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LoginUserPost implements Command {
    private final UserService userService;

    private static final String loginView = "login.jsp";
    private static final String indexView = "login.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        String[] validationFields = {"login_email", "login_password"};
        boolean isValid = Arrays.stream(validationFields)
                .map(request::getParameter)
                .allMatch(this::isEmpty);

        if (!isValid) return loginView;

        String email = request.getParameter("login_email");
        String password = request.getParameter("login_password");

        Optional<User> userOptional = userService.getUserByEmail(email);

        if (!userOptional.isPresent()) {
            request.setAttribute("errorLoginPassMessage",
                    "User with this email is not registered");
            return loginView;
        }

        User loginUser = userOptional.get();
        if (BCrypt.checkpw(password, loginUser.getPassword())) {
            request.getSession().setAttribute("user", loginUser);
            log.info(String.format("Login operation is successful -> %s", loginUser));
            return "/redirect:" + GetEndpoint.INDEX_PAGE;
        }

        request.setAttribute("errorLoginPassMessage", "Wrong credentials");
        return loginView;
    }

    private boolean isEmpty(String value) {
        if (value == null) return false;
        else if ("".equals(value)) return false;
        return true;
    }



}
