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

public class ProcessPollsController extends HttpServlet {

    private PollService pollService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String header = request.getHeader("requestType");
        String id = request.getParameter("id");

        try {
            JSONObject obj = new JSONObject();
            pollService = new PollService();

            if (header.equals("delete")) {

                boolean isDelete = pollService.deletePoll(id);
                obj.put("deleted", isDelete);

            } else if (header.equals("activate")) {

                boolean isActivated = pollService.updatePollStatus(id, "Active");
                obj.put("isUpdated", isActivated);
            }
            if (header.equals("deactivate")) {

                boolean isDeactivated = pollService.updatePollStatus(id, "Inactive");
                obj.put("isUpdated", isDeactivated);
            }

            response.getWriter().write(obj.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProcessPollsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
