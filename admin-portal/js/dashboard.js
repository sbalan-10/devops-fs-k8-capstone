/*const token = localStorage.getItem("token");*/
protectPage();

const token = getToken();

if (!token) {
    window.location.href = "login.html";
}

async function loadDashboard() {

    loadProducts();

    checkBackend();

    checkAuth();

    loadUsers();

}

async function loadProducts() {

    try {

        const response = await fetch(
            API_BASE_URL + "/products",
            {
                headers: {
                    Authorization: "Bearer " + token
                }
            }
        );

        if (!response.ok) {
            throw new Error();
        }

        const products = await response.json();

        document.getElementById("products").innerHTML =
            products.length;

    } catch (e) {

        document.getElementById("products").innerHTML = "N/A";

    }

}

async function loadUsers() {

    // Placeholder until user-management API exists
    document.getElementById("users").innerHTML = "1";

}

async function checkBackend() {

    try {

        const response = await fetch(
            API_BASE_URL + "/health"
        );

        document.getElementById("backendStatus").innerHTML =
            response.ok ? "Running" : "Down";

    } catch (e) {

        document.getElementById("backendStatus").innerHTML =
            "Down";

    }

}

async function checkAuth() {

    try {

        const response = await fetch(
            AUTH_BASE_URL + "/auth/health"
        );

        document.getElementById("authStatus").innerHTML =
            response.ok ? "Running" : "Down";

    } catch (e) {

        document.getElementById("authStatus").innerHTML =
            "Down";

    }

}

function logout() {

    localStorage.removeItem("token");

    window.location.href = "login.html";

}

loadDashboard();
