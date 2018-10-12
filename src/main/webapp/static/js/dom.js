let dom = {

    allowMatchingPasswords: function () {
        let firstPassword = document.querySelector("#password");
        let secondPassword = document.querySelector("#password_confirmation");
        let passwordConfirmationLabel = document.querySelector("#password-confirmation-label");
        let button = document.querySelector("#submit");

        secondPassword.addEventListener("change", function (evt) {
            console.log(secondPassword.value);
            if (firstPassword.value === secondPassword.value) {
                button.disabled = false;
                passwordConfirmationLabel.innerHTML = "Passwords match.";
                passwordConfirmationLabel.classList.add("color-text-green");
            } else {
                passwordConfirmationLabel.classList.remove("color-text-green");
                passwordConfirmationLabel.innerHTML = "Passwords do not match.";
                passwordConfirmationLabel.classList.add("color-text-red");
                button.disabled = true;
            }
        })
    },

    checkIfEmailRegistered: function () {
        let emailAddressLabel = document.querySelector("#email-address-label");
        // not a good idea. Make an AJAX call (XmlHttpRequest)
        let emailArray = JSON.parse(emailAddressLabel.dataset.emailList);
        let inputFieldEmailAddress = document.querySelector("#email-address");
        let button = document.querySelector("#submit");

        inputFieldEmailAddress.addEventListener(
            "change", function (evt) {

                for (let registeredEmail of emailArray) {
                    if (registeredEmail == inputFieldEmailAddress.value) {
                        emailAddressLabel.classList.remove("color-text-green");
                        emailAddressLabel.innerHTML = "Email address is already registered. Please choose another one:";
                        emailAddressLabel.classList.add("color-text-red");
                        button.disabled = true;
                        break;
                    }
                    emailAddressLabel.innerHTML = "Email address is OK:";
                    emailAddressLabel.classList.remove("color-text-red");
                    emailAddressLabel.classList.add("color-text-green");
                    button.disabled = false;
                }
            }
        );


    },

    search: function () {
        search = document.querySelector("#search");

        search.addEventListener("keyup", function () {
            axios.get('/search', {
                params: {
                    keyWord: search.value
                }
            })
                .then(function (response) {
                    document.querySelector("#products").innerHTML = response.data;
                });
        });
    },

};