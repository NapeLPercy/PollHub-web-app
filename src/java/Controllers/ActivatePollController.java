package Controllers;

import Services.PollService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ActivatePollController extends HttpServlet {

    private PollService pollService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try {
            pollService = new PollService();
            boolean isUpdated = pollService.updatePollStatus(id, "Active");

            JSONObject obj = new JSONObject();
            obj.put("isUpdated", isUpdated);

            response.getWriter().write(obj.toString());
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ActivatePollController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
