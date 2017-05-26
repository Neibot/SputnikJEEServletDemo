package sputnikjeedemo.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neibot
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    @EJB
    private LibraryManagerSessionBean libraryManager;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        boolean receiveParameter = false;
        boolean sendParameter = false;
        boolean editParameter = false;
        boolean removeParameter = false;
        boolean changeCatalogParameter = false;
        
        Map parameterValues = request.getParameterMap();
        if (parameterValues.containsKey("action") && parameterValues.containsKey("catalog"))
        {
            if (request.getParameter("action").equals("receive") && parameterValues.containsKey("id"))            
                receiveParameter = true; 
            if (request.getParameter("action").equals("send") && parameterValues.containsKey("json"))
                sendParameter = true;
            if (request.getParameter("action").equals("edit") && parameterValues.containsKey("json"))
                editParameter = true;
            if (request.getParameter("action").equals("remove") && parameterValues.containsKey("id"))            
                removeParameter = true;
            if (request.getParameter("action").equals("changecatalog") && parameterValues.containsKey("id"))            
                changeCatalogParameter = true;
        } 
        
        String generated_out = "";
        String [] result = null;
        if (receiveParameter)
        {   
            if (request.getParameter("catalog").equals("private"))                
                result = libraryManager.searchBook(request.getParameter("id"), "private");
            if (request.getParameter("catalog").equals("public"))                
                result = libraryManager.searchBook(request.getParameter("id"), "public");
            generated_out = generated_out.concat("<!DOCTYPE html>\n");        
            generated_out = generated_out.concat("<html>\n");
            generated_out = generated_out.concat("<head>\n");
            generated_out = generated_out.concat("<title>Успех!</title>\n");            
            generated_out = generated_out.concat("</head>\n");
            generated_out = generated_out.concat("<body>\n");  
            generated_out = generated_out.concat("<h3>{</h3>\n");
            generated_out = generated_out.concat("<h3>\"id\":\""+request.getParameter("id")+"\",</h3>\n");
            generated_out = generated_out.concat("<h3>\"name\":\""+result[0]+"\",</h3>\n");
            generated_out = generated_out.concat("<h3>\"author\":\""+result[1]+"\",</h3>\n");
            generated_out = generated_out.concat("<h3>\"publication_date\":\""+result[2]+"\"</h3>\n");
            generated_out = generated_out.concat("<h3>}</h3>\n");
            generated_out = generated_out.concat("<br><a href='index.html'>Вернуться назад</a>\n");
            generated_out = generated_out.concat("</body>\n");
            generated_out = generated_out.concat("</html>\n");
        }
        
        if (sendParameter)
        {               
            result = request.getParameter("json").split("\"");
            String id = null;
            if (request.getParameter("catalog").equals("private"))                
                id = libraryManager.addBook(result[3],result[7],result[11], "private");
            if (request.getParameter("catalog").equals("public"))                
                id = libraryManager.addBook(result[3],result[7],result[11], "public");
            generated_out = generated_out.concat("<!DOCTYPE html>");
            generated_out = generated_out.concat("<html>");
            generated_out = generated_out.concat("<head>");
            generated_out = generated_out.concat("<title>Успех!</title>");            
            generated_out = generated_out.concat("</head>");
            generated_out = generated_out.concat("<body>");
            generated_out = generated_out.concat("<h3>Книга добавлена в каталог "+request.getParameter("catalog")+" с присвоением ID = "+id+"</h3>");
            generated_out = generated_out.concat("<br><a href='index.html'>Вернуться назад</a>");
            generated_out = generated_out.concat("</body>");
            generated_out = generated_out.concat("</html>");
        }
        
        if (editParameter)
        {               
            result = request.getParameter("json").split("\"");            
            if (request.getParameter("catalog").equals("private"))                
                libraryManager.editBook(result[3],result[7],result[11],result[15], "private");
            if (request.getParameter("catalog").equals("public"))                
                libraryManager.editBook(result[3],result[7],result[11],result[15], "public");
            generated_out = generated_out.concat("<!DOCTYPE html>");
            generated_out = generated_out.concat("<html>");
            generated_out = generated_out.concat("<head>");
            generated_out = generated_out.concat("<title>Успех!</title>");            
            generated_out = generated_out.concat("</head>");
            generated_out = generated_out.concat("<body>");
            generated_out = generated_out.concat("<h3>Книга отредактирована!</h3>");
            generated_out = generated_out.concat("<br><a href='index.html'>Вернуться назад</a>");
            generated_out = generated_out.concat("</body>");
            generated_out = generated_out.concat("</html>");
        }
        
        if (removeParameter)
        {   
            if (request.getParameter("catalog").equals("private"))                
                libraryManager.removeBook(request.getParameter("id"), "private");
            if (request.getParameter("catalog").equals("public"))                
                libraryManager.removeBook(request.getParameter("id"), "public");
            generated_out = generated_out.concat("<!DOCTYPE html>");
            generated_out = generated_out.concat("<html>");
            generated_out = generated_out.concat("<head>");
            generated_out = generated_out.concat("<title>Успех!</title>");            
            generated_out = generated_out.concat("</head>");
            generated_out = generated_out.concat("<body>");
            generated_out = generated_out.concat("<h3>Книга удалена!</h3>");
            generated_out = generated_out.concat("<br><a href='index.html'>Вернуться назад</a>");
            generated_out = generated_out.concat("</body>");
            generated_out = generated_out.concat("</html>");
        }
        
        if (changeCatalogParameter)
        {   
            String id = null;
            if (request.getParameter("catalog").equals("private"))                
                id = libraryManager.changeCatalogOfBook(request.getParameter("id"), "private");
            if (request.getParameter("catalog").equals("public"))                
                id = libraryManager.changeCatalogOfBook(request.getParameter("id"), "public");
            generated_out = generated_out.concat("<!DOCTYPE html>");
            generated_out = generated_out.concat("<html>");
            generated_out = generated_out.concat("<head>");
            generated_out = generated_out.concat("<title>Успех!</title>");            
            generated_out = generated_out.concat("</head>");
            generated_out = generated_out.concat("<body>");
            generated_out = generated_out.concat("<h3>Книга перемещена в каталог "+request.getParameter("catalog")+" с присвоением ID = "+id+"</h3>");
            generated_out = generated_out.concat("<br><a href='index.html'>Вернуться назад</a>");
            generated_out = generated_out.concat("</body>");
            generated_out = generated_out.concat("</html>");
        }
        
        try (PrintWriter out = response.getWriter()) 
        {              
            out.println(generated_out);             
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
