package ua.sulima.mangaservletapp.controller.command.get;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import ua.sulima.mangaservletapp.controller.command.Command;

@Getter
public class IndexGet implements Command {
    private static String page = "index.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return page;
    }
}
