package krift.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(location = "F:/LP",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ServletWeb extends HttpServlet {

    private String jsp = "";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        String type = request.getParameter("type");

        switch (acao) {
            case "Cadastrar":
                jsp = Cadastrar.execute(request);
                break;
            case "Login":
                jsp = Login.execute(request);
                break;
            case "Logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                jsp = "/login.jsp";
                break;
            case "VisualizarUsuario":
                jsp = VisualizarUsuario.execute(request);
                break;
            case "EditarPerfil":

                Part filePart = request.getPart("fileupload");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                InputStream fileContent = filePart.getInputStream();
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                jsp = EditarPerfil.execute(request);
                break;
            case "CriarReceita":
                jsp = CriarReceita.execute(request);
                break;
            case "BuscarReceita":
                jsp = BuscarReceita.execute(request);
                break;
            case "ListarUsuarios":
                jsp = ListarUsuarios.execute(request);
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
