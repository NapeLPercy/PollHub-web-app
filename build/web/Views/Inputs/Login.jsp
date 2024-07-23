<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <script src="/PollHub/Login.js" defer></script>
        <style>
            .custom-width {
                max-width: 400px;
            }

            #sign_up{
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6 custom-width">
                    <div class="shadow p-4 bg-white rounded">
                        <h2 class="mb-4 text-center">Login</h2>
                        <form  action="/PollHub/Views/Outputs/Menu.jsp" method="GET" class="mb-4">
                            <div class="form-group">
                                <p id="username_error"></p>
                                <input type="text" id="username" class="form-control" placeholder="Username" name="username"/>
                            </div>

                            <div class="form-group">
                                <p id="password_error"></p>
                                <input type="password" id="password" class="form-control" placeholder="Password" name="password"/>
                                <p id="invalid_login"></p>
                            </div>

                            <p><a href="#">Forgot Password</a></p>
                            <input type="submit" id="login_btn" class="btn btn-success btn-block" value="LOGIN"/>

                            <p id="sign_up">Don't have an account? <a href="/PollHub/Views/Inputs/CreateAccount.jsp">Sign up</a></p>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>



