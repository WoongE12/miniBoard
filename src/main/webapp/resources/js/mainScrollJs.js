window.addEventListener('scroll', function() {
    const scrollY = window.scrollY || window.pageYOffset || document.documentElement.scrollTop;
    const header = document.querySelector('.include_header');

    if (scrollY >= 330) {
        header.classList.add('show');
        header.style.setProperty("display", "block");
    } else {
        header.classList.remove('show');
        header.style.setProperty("display", "none");
    }
}, { passive: true });
