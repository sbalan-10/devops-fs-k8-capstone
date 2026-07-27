const loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", login);

}

async function login(event) {

    event.preventDefault();

    const email = document.getElementById("email").value;

    const password = document.getElementById("password").value;

    const request = {

        email: email,

        password: password

    };

    try {

        const response = await fetch(

            AUTH_BASE_URL + "/auth/login",

            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(request)

            }

        );

        if (!response.ok) {

            throw new Error("Invalid Credentials");

        }

        const data = await response.json();

        localStorage.setItem("token", data.token);

        localStorage.setItem("username", data.username);

        window.location.href = "dashboard.html";

    }

    catch (error) {

        document.getElementById("message").innerHTML =

            "Login Failed";

    }

}

function logout() {

    localStorage.removeItem("token");

    localStorage.removeItem("username");

    window.location.href = "login.html";

}

function getToken() {

    return localStorage.getItem("token");

}

function isLoggedIn() {

    return localStorage.getItem("token") != null;

}

function protectPage() {

    if (!isLoggedIn()) {

        window.location.href = "login.html";

    }

}

function authHeaders() {

    return {

        "Content-Type": "application/json",

        "Authorization": "Bearer " + getToken()

    };

}
