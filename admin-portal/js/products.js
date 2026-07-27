protectPage();

const token = getToken();

if (!token) {
    window.location.href = "login.html";
}

loadProducts();

async function loadProducts() {

    const response = await fetch(
        API_BASE_URL + "/products",
        {
            headers: {
                Authorization: "Bearer " + token
            }
        });

    const products = await response.json();

    let rows = "";

    products.forEach(product => {

        rows += `

        <tr>

            <td>${product.id}</td>

            <td>${product.name}</td>

            <td>${product.category}</td>

            <td>${product.price}</td>

            <td>${product.quantity}</td>

            <td>

                <button onclick="deleteProduct(${product.id})">

                    Delete

                </button>

            </td>

        </tr>

        `;

    });

    document.getElementById("productTable").innerHTML = rows;

}

document.getElementById("productForm")

.addEventListener("submit", async function (e) {

    e.preventDefault();

    const product = {

        name: document.getElementById("name").value,

        description: document.getElementById("description").value,

        category: document.getElementById("category").value,

        price: Number(document.getElementById("price").value),

        imageUrl: document.getElementById("imageUrl").value,

        quantity: Number(document.getElementById("quantity").value),

        available: true

    };

    const response = await fetch(

        API_BASE_URL + "/products",

        {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                Authorization: "Bearer " + token

            },

            body: JSON.stringify(product)

        });

    if (response.ok) {

        alert("Product Added Successfully");

        document.getElementById("productForm").reset();

        loadProducts();

    } else {

        alert("Unable to save product");

    }

});

async function deleteProduct(id) {

    if (!confirm("Delete Product?")) {

        return;

    }

    await fetch(

        API_BASE_URL + "/products/" + id,

        {

            method: "DELETE",

            headers: {

                Authorization: "Bearer " + token

            }

        });

    loadProducts();

}

function logout() {

    localStorage.removeItem("token");

    window.location.href = "login.html";

}
