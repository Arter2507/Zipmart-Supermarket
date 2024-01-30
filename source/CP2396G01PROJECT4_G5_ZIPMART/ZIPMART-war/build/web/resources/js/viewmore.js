$(document).ready(function () {
    // Hide the button initially
    $('.btnview').hide();

// Show the button when DOM is ready and if "nav-about" tab is active
    if ($('#nav-about').hasClass('active')) {
        $('.btnview').show();
    }

// Toggle button visibility on tab clicks
    $('.nav-link').click(function () {
        if ($('#nav-about').hasClass('active')) {
            $('.btnview').show();
        } else {
            $('.btnview').hide();
        }
    });

// Toggle content height and button text on button click
    $('.btnview').click(function () {
        const tabContent = $('#nav-about');
        const buttonText = $('.btnview');
        if (tabContent.height() === 250) {
            tabContent.height('auto');
            buttonText.html('View less <i class="fas fa-caret-up"></i> ');
        } else {
            tabContent.height(250);
            buttonText.html('View more <i class="fas fa-caret-down"></i> ');
            $('#nav-about-tab')[0].scrollIntoView({behavior: 'smooth'});
        }
    });
}
);
