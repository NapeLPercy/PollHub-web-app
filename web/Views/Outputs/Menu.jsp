<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .navbar-custom {
                background-color: #f8f9fa;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            .navbar-custom .navbar-brand {
                color: #007bff;
                font-weight: bold;
                font-size: 1.5em;
            }
            .navbar-custom .navbar-nav .nav-link {
                color: #343a40;
                margin-right: 1rem;
                padding: 0.5rem 1rem;
                border-radius: 4px;
                transition: background-color 0.3s, color 0.3s;
            }
            .navbar-custom .navbar-nav .nav-link:hover {
                background-color: #007bff;
                color: #fff;
            }
            .navbar-custom .navbar-nav .nav-link.active {
                background-color: #0056b3;
                color: #fff;
            }
            .navbar-custom .navbar-nav .nav-item:last-child .nav-link {
                background-color: #dc3545;
                color: #fff;
                margin-right: 0;
            }
            .navbar-custom .navbar-nav .nav-item:last-child .nav-link:hover {
                background-color: #c82333;
                color: #fff;
            }
            .navbar-toggler-icon {
                background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 30 30' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba%280, 0, 0, 0.5%29' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E");
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <a class="navbar-brand" href="#">Poll<span>Hub</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/PollHub/Views/Inputs/CreatePoll.jsp">Create Poll</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/PollHub/ManagePollsController">Manage Polls</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/PollHub/ViewPollsController">Community Polls</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/PollHub/LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
