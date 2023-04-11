package ua.sulima.mangaservletapp.controller.command.get;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.controller.command.Command;

@Getter
public class LoginUserGet implements Command {
    private static String page = "login.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return page;
    }
}
