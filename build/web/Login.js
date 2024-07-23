const username = document.getElementById("username");
const password = document.getElementById("password");
const login_btn = document.getElementById("login_btn");
const username_error = document.getElementById("username_error");
const password_error = document.getElementById("password_error");
const invalid_login = document.getElementById("invalid_login");


login_btn.addEventListener("click", (event) => {
    event.preventDefault();

    if (username.value === "" && password.value === "") {
        updateErrorView(username_error, "You must enter a username");
        updateErrorView(password_error, "You must enter a password");

    } else if (password.value === "") {
        updateErrorView(password_error, "You must enter a password");

    } else if (username.value === "") {
        updateErrorView(username_error, "You must enter a username");
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
  "&password=" + encodeURIComponent(password),true);

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
                updateErrorView(element, "Wrong username or password");
            }
        }
    };

    xhr.send();
};

function updateErrorView(element_name, error_message) {
    element_name.style.color = "red";
    element_name.innerText = error_message;
}

