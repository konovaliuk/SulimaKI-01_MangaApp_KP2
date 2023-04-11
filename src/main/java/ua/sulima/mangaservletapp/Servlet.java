package ua.sulima.mangaservletapp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ua.sulima.mangaservletapp.controller.PostEndpoint;
import ua.sulima.mangaservletapp.controller.command.Command;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.controller.command.get.*;
import ua.sulima.mangaservletapp.controller.command.post.LoginUserPost;
import ua.sulima.mangaservletapp.controller.command.post.RegisterUserPost;
import ua.sulima.mangaservletapp.factories.service.ServiceFactory;


@Slf4j
public class Servlet extends HttpServlet {

    private Map<String, Command> getCommands = new HashMap<>();

    private Map<String, Command> postCommands = new HashMap<>();

    private ServiceFactory serviceFactory = ServiceFactory.getInstanceWithJdbc();

    public void init() {
        initGetCommands();
        initPostCommands();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("handling request in servlet");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        Command command;
        if(request.getMethod().equals("POST")){
            command = postCommands.getOrDefault(path , (r)->GetEndpoint.INDEX_PAGE);
        }else {
            command = getCommands.getOrDefault(path , (r)->GetEndpoint.INDEX_PAGE);
        }
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replaceAll("/redirect:", ""));
            return;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void initGetCommands(){
        getCommands.put(GetEndpoint.LOGIN_PAGE, new LoginUserGet());
        getCommands.put(GetEndpoint.MANGA_CATALOG_PAGE,
                new MangaCatalog(serviceFactory.getMangaService(), serviceFactory.getCreatorService()));
        getCommands.put(GetEndpoint.REGISTER_PAGE, new RegisterUserGet());
        getCommands.put(GetEndpoint.INDEX_PAGE, new IndexGet());
        getCommands.put(GetEndpoint.LOGOUT, new LogoutUserGet());
    }

    private void initPostCommands(){
        postCommands.put(PostEndpoint.LOGIN,
                new LoginUserPost(serviceFactory.getUserService()));
        postCommands.put(PostEndpoint.REGISTER,
                new RegisterUserPost(serviceFactory.getUserService()));

    }

    private boolean isEmpty(String value) {
        if (value == null) return false;
        else if ("".equals(value)) return false;
        return true;
    }
    public void destroy() {
    }
}