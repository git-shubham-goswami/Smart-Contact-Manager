console.log("Script Loaded");

let currenttheme = getTheme();

// Initial theme setup
changeTheme();

function changeTheme() {
    // Apply the current theme to the HTML document
    document.querySelector('html').classList.add(currenttheme);

    // Select the button to change the theme
    const changeThemebutton = document.querySelector('#theme_change_button');
    
    // Set the initial text of the button based on the current theme
    changeThemebutton.querySelector("span").textContent = currenttheme === "light" ? "Dark" : "Light";

    // Set up an event listener to change the theme when the button is clicked
    changeThemebutton.addEventListener("click", (event) => {
        console.log("Change theme button clicked");

        // Remove the current theme class from the HTML document
        document.querySelector('html').classList.remove(currenttheme);

        // Toggle the theme
        if (currenttheme === "dark") {
            // Change theme to light
            currenttheme = "light";
        } else {
            // Change theme to dark
            currenttheme = "dark";
        }

        // Update the theme in localStorage
        setTheme(currenttheme);

        // Apply the new theme class to the HTML document
        document.querySelector('html').classList.add(currenttheme);

        // Update the button text to reflect the new theme
        changeThemebutton.querySelector("span").textContent = currenttheme === "light" ? "Dark" : "Light";
    });
}

// Set theme in localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}
