console.log("script js running");

let currentTheme = getTheme();
changeTheme();

function changeTheme(){
    document.querySelector("html").classList.add(currentTheme);

    const changeThemeBtn = document.querySelector("#theme_btn");
    changeThemeBtn.addEventListener("click", (event) => {

        document.querySelector("html").classList.remove(currentTheme);


        if(currentTheme == "dark"){
            currentTheme = "light";
        }
        else{
            currentTheme = "dark";
        }

        setTheme(currentTheme);
        document.querySelector("html").classList.add(currentTheme);
    })
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme(){
    let theme = localStorage.getItem("theme");

    return theme ? theme : "light";
}