<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entities.Poll"%>
<%@page import="Entities.Option"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Polls</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="/PollHub/ProcessPolls.js" defer></script>
        <style>
            .poll-container {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 15px;
                margin-bottom: 20px;
            }
            
            .option-container {
                margin-bottom: 10px;
            }
            
            meter{
                width: 600px;
                height: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <% if((ArrayList<Poll>)request.getAttribute("polls") != null ) {
                        ArrayList<Poll> polls = (ArrayList<Poll>)request.getAttribute("polls");
                        if(polls.isEmpty()) { %>
                    <h1 class="text-center">No polls to show</h1>
                    <% } else {
                        for(Poll poll : polls) {
                            String pollId = poll.getPollId()+"";
                            String status = poll.getStatus();
                            String buttonValue = "DEACTIVATE POLL";
                            String id = "deactivate-"+pollId;
                    if(status.equals("Inactive")){
                               buttonValue = "ACTIVATE POLL";
                               id = "activate-"+pollId;}
                    %>
                    <div class="poll-container">
                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>Question:</strong> <%= poll.getQuestion() %></p>
                                <p><strong>Topic:</strong> <%= poll.getTopic() %></p>
                            </div>
                            <div id="action_buttons">
                                <input type="submit" id="delete-<%=pollId%>" value="DELETE POLL" />
                                <input type="submit" id="<%=id%>" value="<%=buttonValue%>" />
                            </div>
                        </div>

                        <div class="options">
                            <% ArrayList<Option> options = poll.getPollOptions();
                                    for(Option option : options) { %>
                            <div class="option-container">
                                <label><%= option.getOptionText() %></label><br>
                                <meter id="<%=option.getId()%>-<%=pollId%>" value="0" max="100"></meter>
                                <p id="<%=option.getId()%>" ></p>

                            </div>

                            <% } %>
                        </div>
                    </div>
                    <% }
                    }
                } else { %>
                    <h1 class="text-center">No polls to show</h1>
                    <% } %>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>