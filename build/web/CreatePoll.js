//adding another input field and button

const add_option_btn = document.getElementById("add_option");
const options_container = document.getElementById("options_container");

add_option_btn.addEventListener("click", (event) => {
    event.preventDefault();
    createOption();
});

// Event delegation to handle click on dynamically added delete buttons
options_container.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("delete_input")) {
        // Remove the corresponding input and delete button
        const optionWrapper = event.target.closest(".option_wrapper");
        if (optionWrapper) {
            optionWrapper.remove();
        }
    }
});

const createOption = () => {
    // Create a wrapper div for each option input and delete button
    const optionWrapper = document.createElement("div");
    optionWrapper.classList.add("option_wrapper", "mt-2");

    const option_input = document.createElement("input");
    option_input.setAttribute("type", "text");
    option_input.setAttribute("name", "option");
    option_input.placeholder = "Enter another poll option";
    option_input.classList.add("form-control", "mb-2");
    option_input.required = true;

    const delete_input = document.createElement("input");
    delete_input.setAttribute("value", "Remove Option");
    delete_input.setAttribute("type", "button");
    delete_input.classList.add("btn", "btn-danger", "delete_input", "mt-2");

    optionWrapper.appendChild(option_input);
    optionWrapper.appendChild(delete_input);

    options_container.appendChild(optionWrapper);
};

//validating user input
// Validate user input

const question = document.getElementById("question");
const optionsContainer = document.getElementById("options_container");
const form = document.getElementById("poll_form");
const createPollBtn = document.getElementById("create_poll");
const addOptionBtn = document.getElementById("add_option");
const questionError = document.getElementById("question_error");
const optionError = document.getElementById("option_error");

createPollBtn.addEventListener("click", (event) => {
    event.preventDefault();

    let valid = true;

    // Validate question field
    if (question.value === "") {
        updateUi(questionError, "You must enter a poll question");
        valid = false;
    } else {
        updateUi(questionError, "");
    }

    // Validate options fields
    const options = optionsContainer.querySelectorAll("input");
    console.log(options);
    options.forEach(option => {
        if (option.value === "") {
            updateUi(optionError, "All option fields must be entered");
            valid = false;
        } else {
            updateUi(optionError, "");
        }
    });

    // If validation is successful, submit the form
    if (valid) {
        form.submit();
    }
});

//update ui
function updateUi(element, message) {
    element.innerText = message;
    element.style.color = "red";
}
