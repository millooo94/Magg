document.addEventListener('DOMContentLoaded', function() {
    const mainContainer = document.querySelector('.main-container');
    const rightSidebarButton = document.querySelector('.settings-button');
    const overlay = document.querySelector(".overlay");
    const rightSidebar = document.querySelector(".right-sidebar");
    const rightSidebarNavSection = document.querySelector(".right-sidebar .nav-section");
    const rightSidebarCloseButton = document.querySelector(".right-sidebar .right-sidebar-header > .close");
    const rightSidebarHeader = document.querySelector(".right-sidebar .right-sidebar-header");
    const rightSidebarHeaderTitle = document.querySelector(".right-sidebar .right-sidebar-header-left-block-title");
    const rightSidebarcloseButton = document.querySelector(".close");

    const menuButton = document.querySelector(".menu-button");
    const leftSidebarNavSection = document.querySelector(".left-sidebar .nav-section");
    const logo = document.querySelector(".left-sidebar .logo");

    const rightSidebarElements = [
        mainContainer,
        rightSidebarNavSection,
        rightSidebarCloseButton,
        rightSidebarHeader,
        rightSidebarHeaderTitle,
        rightSidebar,
        overlay,
        rightSidebarButton
    ];
    const leftSidebarElements = [
        mainContainer,
        leftSidebarNavSection,
        logo
    ];

    const toggleRightSidebarClasses = (action) => {
        rightSidebarElements.forEach(element => element.classList[action]('right-sidebar-collapsed'));
    }

    const toggleLeftSidebarClasses = (action) => {
        leftSidebarElements.forEach(element => element.classList[action]('left-sidebar-collapsed'));
    }

    rightSidebarButton.addEventListener('click', function() {
        toggleRightSidebarClasses('add');
    });

    rightSidebarcloseButton.addEventListener('click', function() {
        toggleRightSidebarClasses('remove');
    });

    overlay.addEventListener('click', function() {
        toggleRightSidebarClasses('remove');
    });

    menuButton.addEventListener('click', function() {
        toggleLeftSidebarClasses('toggle');
    });
});
