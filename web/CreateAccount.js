//collect tags
//background-color: #343a40;color: #ffc107;

const username_el = document.getElementById("username");
const name_el = document.getElementById("name");
const surname_el = document.getElementById("surname");
const email_el = document.getElementById("email");
const password_el = document.getElementById("password");
const confirm_password_el = document.getElementById("confirm_password");
const form_el = document.getElementsByTagName("form")[0];
const create_account_btn = document.querySelector("#create_account_btn");
//errror elements
const name_error = document.getElementById("name_error");
const surname_error = document.getElementById("surname_error");
const username_error = document.getElementById("username_error");
const email_error = document.getElementById("email_error");
const password_error = document.getElementById("password_error");

//
let name = false, surname = false, username = false, mail = false;


create_account_btn.addEventListener("click", (event) => {
    event.preventDefault();

//validate passwords
    if (confirm_password_el.value === password_el.value) {
        if (validatePassword(password_el.value) && validatePassword(confirm_password_el.value)) {//password valid
            password = true;
            updateUi(password_error, "");
        } else {
            updateUi(password_error, "Passwords Invalid");
        }

    } else {
        updateUi(password_error, "Password and Confirm Password are not the same");
    }


//validate first name
    if (name_el.value === "") {
        updateUi(name_error, "You must enter your first name");
    } else {
        if (!checkDigitPresence(name_el.value)) {
            name = true;
            updateUi(name_error, "");
        } else {
            updateUi(name_error, "First name cannot contain digits");
        }
    }

//validate last name
    if (surname_el.value === "") {
        updateUi(surname_error, "You must enter your last name");
    } else {
        if (!checkDigitPresence(surname_el.value)) {
            surname = true;
            updateUi(surname_error, "");
        } else {
            updateUi(surname_error, "Last names cannot contains digits");
        }
    }

//validate mail
    if (email_el.value === "") {
        updateUi(email_error, "You must enter your email");
    } else {
        updateUi(email_error, "");
        mail = true;
    }

    // validate username
    if (username_el.value === "") {
        updateUi(username_error, "You must enter last name");
    } else {
        updateUi(username_error, "");
    }

//submits form
    if (name && surname && mail && password) {
        form_el.submit();
    }


});


    function checkDigitPresence(user_details) {
        for (let i = 0; i < user_details.length; i++) {
            let single_char = user_details.charAt(i);
            if (!isNaN(single_char)) { // Check if the character is a digit
                return true; // Digit found, return true
            }
        }
        return false; // No digits found, return false
    }
//update ui
const updateUi = (element, message) => {
    element.innerText = message;
    element.style.color = "red";
};
//validates password
function validatePassword(user_password) {
    let upper = 0, lower = 0, special = 0, digit = 0;
    for (let i = 0; i < user_password.length; i++) {
        let single_char = user_password.charAt(i);
        if (/[A-Z]/.test(single_char)) {
            upper++;
        } else if (/[a-z]/.test(single_char)) {
            lower++;
        } else if (/[0-9]/.test(single_char)) {
            digit++;
        } else if (/[^A-Za-z0-9]/.test(single_char)) {
            special++;
        }
    }

    let characters = user_password.length;
    return (upper >= 1 && lower >= 1 && special >= 2 && digit >= 2 && characters >= 8);
}
;