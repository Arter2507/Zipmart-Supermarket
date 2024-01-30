$(document).ready(function() {
  // Sử dụng .on() để gắn trình nghe sự kiện 'change' cho phần tử input file
  $("#formUpdate\\:upload").on("change", function() {
    if (this.files.length > 0) { // Kiểm tra xem có file được chọn không
      var reader = new FileReader();
      reader.onload = function(e) {
        $("#formUpdate\\:uploadedAvatar").attr("src", e.target.result);
      };
      reader.readAsDataURL(this.files[0]); // Truyền file đầu tiên
    }
  });

  // Ẩn input file nếu cần
  $("#formUpdate\\:upload").hide();
  
  // Sử dụng .on() để gắn trình nghe sự kiện 'change' cho phần tử input file
  $("#formAccountSettings\\:upload").on("change", function() {
    if (this.files.length > 0) { // Kiểm tra xem có file được chọn không
      var reader = new FileReader();
      reader.onload = function(e) {
        $("#formAccountSettings\\:uploadedAvatar").attr("src", e.target.result);
      };
      reader.readAsDataURL(this.files[0]); // Truyền file đầu tiên
    }
  });

  // Ẩn input file nếu cần
  $("#formAccountSettings\\:upload").hide();
});