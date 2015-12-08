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
    PreparedStatement addressBook,find_max,find_min,insert;

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


            out.println("<html><head><title>Query Successful</title></head><body>");
            out.println("<h2>Thank for your entry</h2");

           // out.p rintln("<p> firstname is: " + request.getParameter("firstname") + "</p>");
        //  out.println("<p> firstname is: " + request.getParameter("lastname") + "</p>");
        //  out.println("<p> firstname is: " + request.getParameter("address") + "</p>");
        //  out.println("<p> firstname is: " + request.getParameter("phone") + "</p>");

        String query2 = "Select * from person where firstname = " + "'" + request.getParameter("firstname") +  "'";
        String query = "SELECT MAX(phone) AS phone FROM person";
        String query3 = "SELECT MIN(phone) AS phone FROM person";
        String query4= "insert into person (id, phone) value (9, " + request.getParameter("phone")+ ")";


        out.println("<p> your query is: " + query2 + "</p>");
        out.println("<p> your query is: " + query + "</p>");
        out.println("<p> your query is: " + query3 + "</p>");
        out.println("<p> your query is: " + query4 + "</p>");

        try {
            addressBook = connection.prepareStatement(query2);
            find_max = connection.prepareStatement(query);
            find_min = connection.prepareStatement(query3);
            insert = connection.prepareStatement(query4);
            ResultSet result = addressBook.executeQuery();
            ResultSet result2 = find_max.executeQuery();
            ResultSet result3 = find_min.executeQuery();
            int result4 = insert.executeUpdate();



            int count=0;
            while(result.next())
            {
                out.println("<p>" + result.getString("firstname") + "," +
                result.getString("lastname")+ "," + result.getString("address") + ","
                        + result.getDouble("phone") + "</p>");
                ++count;
            }
            out.println("<p>======" + count + "records found ====</p>");
            while(result2.next())
            {
                out.println("<p>" + result2.getDouble("phone") +"</p");
            }
            while(result3.next())
            {
                out.println("<p>" + result3.getDouble("phone") +"</p");
            }




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
