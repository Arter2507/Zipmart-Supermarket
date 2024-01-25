function resetAllForms() {
    // Get all form elements
    var forms = document.querySelectorAll("form");
    // Reset all form in page
    for (var i = 0; i < forms.length; i++) {
        forms[i].reset();
    }
    // Add this code to can reset images
    var imgElement = document.getElementById("formAccountSettings:uploadedAvatar");
    imgElement.src = "/ZipMartW-war/faces/javax.faces.resource/images/logo/photo-camera-gray.svg"; // Reset src to default image
}
var btnReset = document.getElementById("btnReset");
btnReset.addEventListener("click", resetAllForms);


