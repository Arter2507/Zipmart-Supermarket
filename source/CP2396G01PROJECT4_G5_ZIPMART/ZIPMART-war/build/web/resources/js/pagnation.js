let thisPage = 1;
let limit = 6;
let list = document.querySelectorAll('.cart-content .fruite-item');

function loadItem() {
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    list.forEach((item, key) => {
        if (key >= beginGet && key <= endGet) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    })
    listPage();
}
loadItem();
function listPage() {
    let count = Math.ceil(list.length / limit);
    document.querySelector('.pagination').innerHTML = '';

    if (thisPage !== 1) {
        let prev = document.createElement('li');
        let newPageLink = document.createElement('a');
        newPageLink.href = 'javascript:void(0);'; // Set href for link functionality
        newPageLink.innerText = "PREV";
        newPageLink.classList.add('rounded');
        prev.appendChild(newPageLink);
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.pagination').appendChild(prev);
    }

    for (i = 1; i <= count; i++) {
        let newPage = document.createElement('li');
        let newPageLink = document.createElement('a');
        newPageLink.href = 'javascript:void(0);'; // Set href for link functionality
        newPageLink.innerText = i;
        newPageLink.classList.add('rounded');
        newPage.appendChild(newPageLink);
        if (i === thisPage) {
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.pagination').appendChild(newPage);
    }

    if (thisPage !== count) {
        let next = document.createElement('li');
        let newPageLink = document.createElement('a');
        newPageLink.href = 'javascript:void(0);'; // Set href for link functionality
        newPageLink.innerText = "NEXT";
        newPageLink.classList.add('rounded');
        next.appendChild(newPageLink);
        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
        document.querySelector('.pagination').appendChild(next);
    }
}
function changePage(i) {
    thisPage = i;
    loadItem();
}