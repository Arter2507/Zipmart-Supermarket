jQuery(document).ready(function($){

    $('#shoppro .fruite-item').each(function(){
        $(this).attr('data-search-term', $(this).text().toLowerCase());
    });

    $('#shoppro .live-search-box').on('keyup', function(){
        var searchTerm = $(this).val().toLowerCase();

        $('#shoppro .fruite-item').each(function(){
            if ($(this).filter('[data-search-term *= ' + searchTerm + ']').length > 0 || searchTerm.length < 1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });

        // Cập nhật kết quả tìm kiếm khi chuyển đổi trang
        $('.pagination a').on('click', function(e) {
            e.preventDefault();
            // Xử lý việc chuyển trang và cập nhật kết quả tìm kiếm
        });
    });
});
