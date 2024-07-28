document.getElementById("login_btn")
        .addEventListener("click", (event) => {
            event.preventDefault();

            var password = document.getElementById("password");
            var password_error = document.getElementById("password_error");
            const username = document.getElementById("username");
            const username_error = document.getElementById("username_error");
            var invalid_login = document.getElementById("invalid_login");

            if (username.value === "" && password.value === "") {
                updateErrorView(username_error, "Username is required.");
                updateErrorView(password_error, "Password is required.");

            } else if (password.value === "") {
                updateErrorView(password_error, "Password is required.");

            } else if (username.value === "") {
                updateErrorView(username_error, "Username is required.");
            } else
            {
                updateErrorView(password_error, "");
                updateErrorView(username_error, "");
                login(username.value, password.value, invalid_login);
            }

        });


const login = (username, password, element) => {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/PollHub/LoginController?username=" + encodeURIComponent(username) +
            "&password=" + encodeURIComponent(password), true);

    const loginData = {
        username: username,
        password: password
    };

    xhr.onreadystatechange = function () {

        if (this.status === 200 && this.readyState === 4) {
            const data = JSON.parse(xhr.responseText);

            if (data.valid) {
                document.querySelector("form").submit();
            } else {
                updateErrorView(element, "Wrong Username or Password.");
            }
        }
    };

    xhr.send();
};


function updateErrorView(element_name, error_message) {
    element_name.style.color = "red";
    element_name.innerText = error_message;
}

document.querySelectorAll("input")
        .forEach(element => {
            element.addEventListener("focus", (event) => {
                event.target.previousElementSibling.innerText = "";
            });
        });