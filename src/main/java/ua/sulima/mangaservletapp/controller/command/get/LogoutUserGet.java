package ua.sulima.mangaservletapp.controller.command.get;

import jakarta.servlet.http.HttpServletRequest;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.controller.command.Command;

public class LogoutUserGet implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/redirect:" + GetEndpoint.INDEX_PAGE;
    }
}
