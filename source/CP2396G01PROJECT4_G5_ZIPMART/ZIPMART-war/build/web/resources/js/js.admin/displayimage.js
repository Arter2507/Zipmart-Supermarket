/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function setImagePreview(fileInput) {
    var reader = new FileReader();

    reader.onload = function(e) {
        document.getElementById('uploadedImage').src = e.target.result;
    };

    reader.readAsDataURL(fileInput.files[0]);
}

