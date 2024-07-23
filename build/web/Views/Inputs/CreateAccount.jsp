<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="/PollHub/CreateAccount.js" defer></script>
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
                    <h2 class="mb-4 text-center">Create Account</h2>
                    <form action="/PollHub/CreateAccountController" method="POST" class="mb-4">
                        <div class="form-group">
                            <label for="name">First Name</label>
                            <p id="name_error"></p>
                            <input type="text" class="form-control" name="name" id="name" />
                        </div>

                        <div class="form-group">
                            <label for="surname">Last Name</label>
                            <p id="surname_error"></p>
                            <input type="text" class="form-control" name="surname" id="surname"/>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <p id="email_error"></p>
                            <input type="email" class="form-control" name="email" id="email"/>
                        </div>

                        <div class="form-group">
                            <label for="username">Username</label>
                            <p id="username_error"></p>
                            <input type="text" class="form-control" name="username" id="username" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <p id="password_error"></p>
                            <input type="password" class="form-control" name="password" id="password"/>
                        </div>

                        <div class="form-group">
                            <label for="confirm_password">Confirm Password</label>
                            <input type="password" class="form-control" name="confirm_password" id="confirm_password" />
                        </div>

                        <input type="submit" id="create_account_btn" class="btn btn-success btn-block" value="CREATE ACCOUNT"/>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
