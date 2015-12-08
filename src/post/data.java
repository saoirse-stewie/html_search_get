package post;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by saoirse on 07/12/2015.
 */
public class data extends HttpServlet {


    Connection connection;
    PreparedStatement addressBook;

    public void init(ServletConfig config) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/address?user=root&password=root");
            //addressBook = connection.prepareStatement("insert into person(firstname,lastname,address,phone) values (?, ? , ? , ? )");






        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    //    try {
          //  addressBook = connection.prepareStatement(query2);
           // ResultSet result = addressBook.executeQuery();

            out.println("<html><head><title>Query Successful</title></head><body>");
            out.println("<h2>Thank for your entry</h2");

           // out.println("<p> firstname is: " + request.getParameter("firstname") + "</p>");
          //  out.println("<p> firstname is: " + request.getParameter("lastname") + "</p>");
          //  out.println("<p> firstname is: " + request.getParameter("address") + "</p>");
          //  out.println("<p> firstname is: " + request.getParameter("phone") + "</p>");

        String query2 = "Select * from person where firstname = " + "'" + request.getParameter("firstname") +  "'";
        

        out.println("<p> your query is: " + query2 + "</p>");

        try {
            addressBook = connection.prepareStatement(query2);
            ResultSet result = addressBook.executeQuery();




            int count=0;
            while(result.next())
            {
                out.println("<p>" + result.getString("firstname") + "," +
                result.getString("lastname")+ "," + result.getString("address") + ","
                        + result.getDouble("phone") + "</p>");
                ++count;
//
            }
            out.println("<p>======" + count + "records found ====</p>");


        } catch (SQLException e) {
            e.printStackTrace();
        }




       // int value = Integer.parseInt(request.getParameter("First Name"));
    }

    public void destroy() {
        try {
            addressBook.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
