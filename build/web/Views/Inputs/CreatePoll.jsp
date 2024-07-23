<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Poll</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="/PollHub/CreatePoll.js" defer></script>
        <style>
            .custom-width {
                max-width: 500px; /* Adjust as necessary */
            }
            
            body{
                font-family: sans-serif;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6 custom-width">
                    <h1 class="mb-4 text-center">Create a Poll</h1>
                    <form action="/PollHub/CreatePollController" id="poll_form" method="POST">
                        <div class="form-group">
                            <label for="question">Question</label>
                            <p id="question_error"></p>
                            <input type="text" class="form-control" name="question" id="question"/>
                        </div>

                        <div class="form-group">
                            <label for="topic">Topic</label>
                            <select name="topic" class="form-control">
                                <option value="Politics">Politics</option>
                                <option value="Politics">Technology</option>
                                <option value="Automobiles">Automobiles</option>
                                <option value="Sports">Sports</option>
                                <option value="Education">Education</option>
                                <option value="Health">Health</option>
                                <option value="Finance">Finance</option>
                                <option value="Lifestyle">Lifestyle</option>
                                <option value="Fashion & Beauty">Fashion & Beauty</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="option">Option(s)</label>
                            <p id="option_error"></p>
                            <div id="options_container" class="mb-2">
                                <input type="text" class="form-control mb-2" name="option" id="option"/>
                            </div>
                            <button id="add_option" class="btn btn-primary">Add Option</button>
                        </div>

                        <button id="create_poll"  class="btn btn-success">CREATE POLL</button>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
