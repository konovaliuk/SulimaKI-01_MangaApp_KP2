package ua.sulima.mangaservletapp.controller.command;


import jakarta.servlet.http.HttpServletRequest;



public interface Command {
    String execute(HttpServletRequest request);
}
