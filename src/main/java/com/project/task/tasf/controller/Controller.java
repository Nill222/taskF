package com.project.task.tasf.controller;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.command.CommandType;
import com.project.task.tasf.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AppController", urlPatterns = {"/app"})
public class Controller extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandStr = req.getParameter("command");
        Command command = CommandType.defineCommand(commandStr);
        Router router = command.execute(req);
        String page = router.page();
        if(router.type() == Router.Type.REDIRECT){
            resp.sendRedirect(page);
        }else{
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    public void destroy() {
        ConnectionPool pool = ConnectionPool.INSTANCE;
        pool.destroyPool();

    }
}
