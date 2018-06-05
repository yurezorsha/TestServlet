

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = new PrintWriter(response.getWriter());
	    try {
	    	
	    	
	        String name = request.getParameter("function");
	        String x0 = request.getParameter("X0");
	        String x = request.getParameter("X");
	        response.setContentType("text/html");

	        out.println("<!DOCTYPE html PUBLIC " +
	                    "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
	                    "\"http://www.w3.org/TR/html4/loose.dtd\"> ");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<meta charset=\"UTF-8\">");
	        out.println("<title>Graphics</title>");
	        out.println("</head>");
	        out.println("<body>");
	        
	        System.out.println(name);
	        out.println("<form method=POST action=\"Servlet2\">");
	        
	        out.println("Function: <input type=\"text\" name=\"function\" value="+name+">");
	        out.println("<br>");
	        out.println("X0: <input type=\"text\" name=\"X0\" value="+x0+">");
	        out.println("X: <input type=\"text\" name=\"X\" value="+x+">");
	        out.println("<INPUT TYPE=\"submit\" VALUE=\"Submit\">");
	        out.println("</FORM>");
	        out.println("<P>");
	        out.println("<IMG SRC=\"Servlet?function=" +name+"&X0="+x0+"&X="+x 
	                             + "\" BORDER=1 WIDTH=600 HEIGHT=400/>");
	        out.println("</body>");
	        out.println("</html>");

	        out.flush();
	    } catch (Exception e) {
	        System.err.println(e.toString());
	    } finally {
	        out.close();
	    }
	}

}
