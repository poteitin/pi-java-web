function validateForm() {
    var username = document.forms["registerForm"]["username"].value;
    var password = document.forms["registerForm"]["password"].value;
    if (username == "" || password == "") {
        alert("Todos os campos devem ser preenchidos");
        return false;
    }
    return true;
}
