$(document).ready(function () {
    // Hide the button initially
    $('.btnview').hide();

// Show the button when DOM is ready and if "nav-about" tab is active
    if ($('#pills-description').hasClass('active')) {
        if ($('#pills-description').height() <= 250) {
            $('.btnview').hide();
        } else {
            $('.btnview').show();
        }
    }

// Toggle button visibility on tab clicks
    $('.nav-link').click(function () {
        if ($('#pills-description').hasClass('active')) {
            $('.btnview').show();
        } else {
            $('.btnview').hide();
        }
    });

// Toggle content height and button text on button click
    $('.btnview').click(function () {
        const tabContent = $('#pills-description');
        const buttonText = $('.btnview');
        if (tabContent.height() === 250) {
            tabContent.height('auto');
            buttonText.html('View less <i class="fas fa-caret-up"></i> ');
        } else {
            tabContent.height(250);
            buttonText.html('View more <i class="fas fa-caret-down"></i> ');
            $('#pills-description-tab')[0].scrollIntoView({behavior: 'smooth'});
        }
    });
}
);
