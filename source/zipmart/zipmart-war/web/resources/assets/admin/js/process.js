/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var jq = jQuery.noConflict();
(function (jq)
{
    "use strict";
    jq(document).ready(function () {
        jq("#editEmp").click(function (event) {
            event.preventDefault();
            var employeeID = jq(this).data("employee-id");
            jq.ajax({
                url: "#{request.contextPath}/employeeMB/showUpdateEmp",
                type: "POST",
                data: {
                    employeeID: employeeID
                },
                success: function (response) {
                    jq("#formEdit").html(response);
                    jq("#details-modal").modal("show");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error("AJAX error:", textStatus, errorThrown);
                }
            });
        });
    });
})(jQuery);

