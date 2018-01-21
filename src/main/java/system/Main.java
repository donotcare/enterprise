package system;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.GetEmployeesJsonServlet;
import servlets.GetOddEmployeesServlet;
import servlets.MarshalXMLServlet;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        DbDataFiller dataFiller = new DbDataFiller();
        dataFiller.parseCsvFile(new FileReader(Main.class.getResource("/data.csv").getFile()));

        Server server = new Server(8090);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceHandler resourceHandler = new ResourceHandler();
        context.addServlet(new ServletHolder(new MarshalXMLServlet()), "/marshal_xml/");
        context.addServlet(new ServletHolder(new GetEmployeesJsonServlet()), "/employees_json/");
        context.addServlet(new ServletHolder(new GetOddEmployeesServlet()), "/odd_employees/");
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();


    }
}
