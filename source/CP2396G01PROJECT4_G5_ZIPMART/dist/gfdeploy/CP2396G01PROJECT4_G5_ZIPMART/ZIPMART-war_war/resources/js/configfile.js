$(document).ready(function() {
  // Sử dụng .on() để gắn trình nghe sự kiện 'change' cho phần tử input file
  $("#formUpdate\\:upload").on("change", function() {
    var reader = new FileReader();

    reader.onload = function(e) {
      $("#formUpdate\\:uploadedAvatar").attr("src", e.target.result);
    };

    reader.readAsDataURL(this.files[0]);
  });

  // Ẩn input file nếu cần
  $("#formUpdate\\:upload").hide();
});
