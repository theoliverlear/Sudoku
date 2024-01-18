//==================================-Login-===================================

//---------------------------------Login-Popup--------------------------------
let loginButton = document.getElementById('login-popup-button');
let loginPopup = document.getElementById('login-section');
let loginCloseButton = document.getElementById('close-login-img');
function popupLogin() {
    loginPopup.style.display = "block";
    closeSignup();
}
function closeLogin() {
    loginPopup.style.display = "none";
    hideInvalidLoginText();
    clearInputFields();
}
loginButton.addEventListener("click", popupLogin);
loginCloseButton.addEventListener("click", closeLogin);
//----------------------Highlight-Login-Close-Button--------------------------
function makeLoginCloseImgRed() {
    loginCloseButton.src = "../images/close_tab_red_x.svg";
}
function makeLoginCloseImgBlack() {
    loginCloseButton.src = "../images/close_tab_black_x.svg";
}
loginCloseButton.addEventListener("mouseover", makeLoginCloseImgRed);
loginCloseButton.addEventListener("mouseout", makeLoginCloseImgBlack);
//------------------------------Submit-Login----------------------------------
let loginSubmitButton = document.getElementById('login-button-div');
let loginUsername = document.getElementById('login-username-input');
let loginPassword = document.getElementById('login-password-input');
function submitLogin() {
    let usernameText = loginUsername.value;
    let passwordText = loginPassword.value;
    let jsonRequestString = getCredentialRequestJson(usernameText, passwordText);
    let request = new XMLHttpRequest();
    request.open("POST", `/user/login`);
    request.setRequestHeader("Content-Type", "application/json");
    // request.setRequestHeader("Content-Type", "text/xml");
    request.onload = function () {
        let loginSuccess = request.responseText;
        if (loginSuccess === "authenticated") {
            hideInvalidLoginText();
            closeLogin();
            clearInputFields();
            showLoggedInButtons();
            loadBoard();
        } else {
            revealInvalidLoginText();
        }
    }
    request.send(jsonRequestString);
}
loginSubmitButton.addEventListener("click", submitLogin);
//----------------------------Clear-Input-Fields------------------------------
function clearInputFields() {
    loginUsername.value = "";
    loginPassword.value = "";
    
    signupUsername.value = "";
    signupPassword.value = "";
    signupPasswordConfirm.value = "";
}
//----------------------------Invalid-Login-Popup-----------------------------
let invalidLoginPopup = document.getElementById('invalid-login-div');
let loginSection = document.getElementById('login-section');
function revealInvalidLoginText() {
    loginSection.style.height = "25vh"
    invalidLoginPopup.style.display = "flex";
}
function hideInvalidLoginText() {
    loginSection.style.height = "22vh"
    invalidLoginPopup.style.display = "none";
}
//------------------------------Get-Request-XML-------------------------------
function getCredentialRequestXml(username, password) {
    let xmlRequestString = `
    <CredentialRequest>
        <username>${username}</username>
        <password>${password}</password>
    </CredentialRequest>
`;
    return xmlRequestString;
}
//-------------------------------Get-Request-JSON-----------------------------
function getCredentialRequestJson(username, password) {
    let jsonRequestString = `
    {
        "username": "${username}",
        "password": "${password}"
    }
`;
    return jsonRequestString;
}
function getTimeRequestJson(timer) {
    let jsonRequestString = `
    {
        "timer": "${timer}"
    }
`;
    return jsonRequestString;
}
//==================================-Signup-==================================

//--------------------------------Signup-Popup--------------------------------
let signupButton = document.getElementById('signup-popup-button');
let signupPopup = document.getElementById('signup-section');
let signupCloseButton = document.getElementById('close-signup-img');
function popupSignup() {
    signupPopup.style.display = "block";
    closeLogin();
}
function closeSignup() {
    signupPopup.style.display = "none";
    hideInvalidSignupText();
    clearInputFields();
}
signupButton.addEventListener("click", popupSignup);
signupCloseButton.addEventListener("click", closeSignup);
//-----------------------Highlight-Signup-Close-Button------------------------
function makeSignupCloseImgRed() {
    signupCloseButton.src = "../images/close_tab_red_x.svg";
}
function makeSignupCloseImgBlack() {
    signupCloseButton.src = "../images/close_tab_black_x.svg";
}
signupCloseButton.addEventListener("mouseover", makeSignupCloseImgRed);
signupCloseButton.addEventListener("mouseout", makeSignupCloseImgBlack);
//------------------------------Submit-Signup---------------------------------
let signupSuccess = false; // SHINING VARIABLE
let signupSubmitButton = document.getElementById('signup-button-div');
let signupUsername = document.getElementById('signup-username-input');
let signupPassword = document.getElementById('signup-password-input');
let signupPasswordConfirm = document.getElementById('signup-confirm-password-input');

function submitSignup() {
    if (!signupPasswordMatches()) {
        revealPasswordsMatchText();
        return;
    } else {
        hidePasswordsMatchText();
    }
    let usernameText = signupUsername.value;
    let passwordText = signupPassword.value;
    let jsonRequestString = getCredentialRequestJson(usernameText, passwordText);
    let request = new XMLHttpRequest();
    request.open("POST", `/user/signup`);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function () {
        signupSuccess = request.responseText;
        if (signupSuccess === "success") {
            hideInvalidSignupText();
            closeSignup();
            clearInputFields();
            showLoggedInButtons();
        } else {
            revealInvalidSignupText();
        }
    }
    // request.send(xmlRequestString);
    request.send(jsonRequestString);
}
signupSubmitButton.addEventListener("click", submitSignup);
//-------------------------Signup-Password-Matches----------------------------
function signupPasswordMatches() {
    let passwordText = signupPassword.value;
    let passwordConfirmText = signupPasswordConfirm.value;
    return passwordText === passwordConfirmText;
}
//---------------------------Invalid-Signup-Popup-----------------------------
const DEFAULT_INVALID_SIGNUP_TEXT = "Invalid username or password.";
const PASSWORDS_DONT_MATCH_TEXT = "Passwords do not match.";
let invalidSignupPopup = document.getElementById('invalid-signup-div');
let signupSection = document.getElementById('signup-section');
function revealInvalidSignupText() {
    signupSection.style.height = "27vh"
    invalidSignupPopup.style.display = "flex";
}
function hideInvalidSignupText() {
    signupSection.style.height = "24vh"
    invalidSignupPopup.style.display = "none";
}
//--------------------------Passwords-Match-Popup-----------------------------
function revealPasswordsMatchText() {
    invalidSignupPopup.children[0].innerText = PASSWORDS_DONT_MATCH_TEXT;
    signupSection.style.height = "27vh"
    invalidSignupPopup.style.display = "flex";
}
function hidePasswordsMatchText() {
    invalidSignupPopup.children[0].innerText = DEFAULT_INVALID_SIGNUP_TEXT;
    signupSection.style.height = "24vh"
    invalidSignupPopup.style.display = "none";
}
//=============================-Logged-In-Buttons-============================

//---------------------------Swap-Login-Signup-Buttons------------------------
let loginSignupButtons = document.getElementById('login-signup-button-section');
let loggedInButtons = document.getElementById('logged-in-button-section');
function showLoginSignupButtons() {
    loginSignupButtons.style.display = "flex";
    loggedInButtons.style.display = "none";
}
function showLoggedInButtons() {
    loginSignupButtons.style.display = "none";
    loggedInButtons.style.display = "flex";
    loadAccountButtonText();
}
//------------------------------Is-Logged-In----------------------------------
function isLoggedIn() {
    let request = new XMLHttpRequest();
    let isLoggedIn = false;
    request.open("POST", "/user/logged-in");
    request.onload = function () {
        let response = request.responseText;
        if (response === "true") {
            isLoggedIn = true;
        } else {
            isLoggedIn = false;
        }
    }
    request.send();
    return isLoggedIn;
}
//-------------------------------Logout-Button--------------------------------
let logoutButton = document.getElementById('logout-button-div');
function logout() {
    let request = new XMLHttpRequest();
    request.open("POST", "/user/logout");
    showLoginSignupButtons();
}
logoutButton.addEventListener("click", logout);
//-------------------------Load-Account-Button-Text---------------------------
let accountButton = document.getElementById('account-button-div');
function loadAccountButtonText() {
    let request = new XMLHttpRequest();
    request.open("POST", "/user/refresh/account-button");
    request.onload = function () {
        let response = request.responseText;
        accountButton.outerHTML = response;
    }
    request.send();
}
//--------------------------------Save-Board----------------------------------
let saveGameButton = document.getElementById('save-board-button-div');
function saveBoard() {
    submitTime();
    let request = new XMLHttpRequest();
    request.open("POST", "/user/save");
    request.send();
}
saveGameButton.addEventListener("click", saveBoard);
//==================================-Timer-===================================
/**
 * Represents a timer element in the DOM.
 * @type {HTMLElement}
 * @memberof document
 * @property {string} id - The id of the timer element.
 */
let timer = document.getElementById('timer');
/**
 * Represents the status of a task or process being in progress.
 * @type {boolean}
 */
let inProgress = false; // SHINING VARIABLE
/**
 * Represents a time string.
 * @type {string}
 */
let timeString = "00:00";
let timeSeconds = 0;
let timeMinutes = 0;
let secondsString = "";
let minutesString = "";
//------------------------------Load-Time-Values------------------------------
function loadTimeValues(timeToLoad) {
    timeString = timeToLoad;
    timeSeconds = parseInt(timeToLoad.split(":")[1]);
    timeMinutes = parseInt(timeToLoad.split(":")[0]);
}
//-----------------------------Increment-Seconds------------------------------
function incrementSeconds() {
    timeSeconds++;
    if (timeSeconds === 60) {
        timeSeconds = 0;
        incrementMinutes();
    }
    updateTime();
}
//-----------------------------Increment-Minutes------------------------------
function incrementMinutes() {
    timeMinutes++;
    updateTime();
}
//--------------------------------Time-Counter--------------------------------
/**
 * Start counting time in seconds and minutes.
 * @returns {void}
 * @see sleep
 * @see timeString
 * @see timer
 * @see inProgress
 * @async
 */
async function timeCounter() {
    while (true) {
        if (inProgress) {
            incrementSeconds();
        }
        await sleep(1000);
    }
}
//---------------------------Update-Time-String-------------------------------
function updateTime() {
    if (timeSeconds < 10) {
        secondsString = "0" + timeSeconds;
    } else {
        secondsString = timeSeconds;
    }
    if (timeMinutes < 10) {
        minutesString = "0" + timeMinutes;
    } else {
        minutesString = timeMinutes;
    }
    timeString = minutesString + ":" + secondsString;
    timer.innerText = timeString;
}
timeCounter();
//--------------------------------Sleep-Wait----------------------------------
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
//-------------------------------Submit-Time----------------------------------
function submitTime() {
    let request = new XMLHttpRequest();
    request.open("POST", `/time`);
    request.setRequestHeader("Content-Type", "application/json");
    let jsonTimeRequestString = getTimeRequestJson(timeString);
    request.send(jsonTimeRequestString);
}
//--------------------------------Load-Time-----------------------------------
function loadTime() {
    console.log("Loading time.");
    let request = new XMLHttpRequest();
    request.open("POST", "/user/load/timer");
    request.onload = function () {
        let response = request.responseText;
        timeString = response;
        loadTimeValues(timeString);
    }
    request.send();
}
//===============================-Difficulty-=================================
let difficultySection = document.getElementById('difficulty-section');
let difficultyButtons = document.getElementsByClassName('difficulty-option');
let difficultyButtonsArray = Array.from(difficultyButtons);
//--------------------------Popup-Difficulty-Menu-----------------------------
function popupDifficultyMenu() {
    difficultySection.style.display = "block";
}
//--------------------------Close-Difficulty-Menu-----------------------------
function closeDifficultyMenu() {
    difficultySection.style.display = "none";
}
difficultyButtonsArray.forEach(button => {
    button.addEventListener("click", closeDifficultyMenu);
    
    button.addEventListener("click", setDifficulty);
});

let difficultyMenuCloseButton = document.getElementById('close-difficulty-img');
function makeCloseTabImgRed() {
    difficultyMenuCloseButton.src = "../images/close_tab_red_x.svg";
}
function makeCloseTabImgWhite() {
    difficultyMenuCloseButton.src = "../images/close_tab_x.svg";
}
difficultyMenuCloseButton.addEventListener("mouseover", makeCloseTabImgRed);
difficultyMenuCloseButton.addEventListener("mouseout", makeCloseTabImgWhite);

difficultyMenuCloseButton.addEventListener("click", closeDifficultyMenu);
//-----------------------------Set-Difficulty---------------------------------
function setDifficulty() {
    let difficulty = this.children[0].innerText;
    let request = new XMLHttpRequest();
    request.open("POST", `/difficulty/${difficulty}`);
    request.onload = function () {
        attachListeners();
    }
    request.send();
    // Launch a new game after difficulty is chosen.
    newGame();
}
//----------------------------------New-Game----------------------------------
let newGameButton = document.getElementById('new-game-div');
function newGame() {
    timeString = "00:00";
    loadTimeValues(timeString);
    inProgress = true;
    let request = new XMLHttpRequest();
    request.open("POST", "/new-game");
    request.onload = function () {
        let fragResponse = request.responseText;
        let boardDiv = document.getElementById('game-section');
        boardDiv.outerHTML = fragResponse;
        attachListeners();
    }
    request.send();
}
newGameButton.addEventListener("click", popupDifficultyMenu);
//--------------------------------Load-Board----------------------------------
async function loadBoard() {
    let loadedBoardExists = await boardExists();
    if (!loadedBoardExists) {
        console.log("No board to load.")
        return;
    }
    let request = new XMLHttpRequest();
    request.open("POST", "/user/load");
    request.onload = function () {
        let fragResponse = request.responseText;
        let boardDiv = document.getElementById('game-section');
        boardDiv.outerHTML = fragResponse;
        attachListeners();
    }
    request.send();
    inProgress = true;
    console.log("Board timer in progress: " + inProgress);
    loadTime();
}
//-------------------------------Board-Exists---------------------------------
function boardExists() {
    return new Promise((resolve, reject) => {
        let request = new XMLHttpRequest();
        request.open("POST", "/user/board-exists");
        request.onload = function () {
            let response = request.responseText.trim();
            console.log(`boardExists: ${response}`);
            if (response === "true") {
                console.log("Board exists.");
                resolve(true);
            } else {
                console.log("Board does not exist.");
                resolve(false);
            }
        }
        request.onerror = function () {
            reject("Error getting boardExists boolean.");
        }
        request.send();
        return boardExists;
    });
}
//---------------------------Track-Mouse-Movements----------------------------
let mouseCords = {x: 0, y: 0};
document.addEventListener("mousemove", monitorMouseCords);
function monitorMouseCords(mouseEvent) {
    mouseCords.x = (mouseEvent.clientX / window.innerWidth) * 100;
    mouseCords.y = (mouseEvent.clientY / window.innerHeight) * 100;
}
//==============================-Make-Move-===================================

//-------------------------------Select-Spot----------------------------------
let spots = document.getElementsByClassName('num-container');
let spotsArray = Array.from(spots);
let currentSpotChoice = null; // SHINING VARIABLE
function selectSpot() {
    currentSpotChoice = this;
}
spotsArray.forEach(spot => {
    spot.addEventListener("click", selectSpot);
});
//----------------------------Is-Muted-Spot-----------------------------------
function isMutedSpot(spot) {
    let selectedSpotStyle = spot.style.backgroundColor;
    let mutedSpotStyle = "rgb(204, 204, 204)";
    let isMuted = selectedSpotStyle === mutedSpotStyle;
    console.log(`${spot.children[0].id} is muted: ${isMuted}`);
    return isMuted;
}
//--------------------------Reveal-Num-Choice-Menu----------------------------
let hoverSelection = document.getElementById("hover-selection");
function revealNumChoiceMenu() {
    if (isMutedSpot(this)) {
        return;
    }
    const selectedColor = "rgba(255, 0, 0, 0.5)";
    const defaultColor = "rgba(255, 255, 255, 0.9)";
    spotsArray.forEach(spot => {
        if (this !== spot) {
            if (!isMutedSpot(spot)) {
                spot.style.backgroundColor = defaultColor;
            }
        }
    });
    currentSpotChoice = this;
    if (currentSpotChoice.style.backgroundColor === selectedColor) {
        currentSpotChoice.style.backgroundColor = defaultColor;
        hoverSelection.style.visibility = "hidden";
        currentSpotChoice = null;
    } else {
        currentSpotChoice.style.backgroundColor = selectedColor;
        hoverSelection.style.visibility = "visible";
        hoverSelection.style.left = (mouseCords.x - 15) + "vw";
        hoverSelection.style.top = (mouseCords.y - 1.5) + "vh";
    }
}
// Variable from above
spotsArray.forEach(spot => {
    spot.addEventListener("click", revealNumChoiceMenu);
});
//----------------------------Select-Board-Number-----------------------------
let boardNumBoxes = document.getElementsByClassName("hover-num-container");
let boardNumBoxesArray = Array.from(boardNumBoxes);
let currentSpotNumberChoice = null; // SHINING VARIABLE
function selectBoardNumber() {
    let boardNumber = this.children[0].innerText;
    if (boardNumber === " ") {
        boardNumber = "0";
    }
    currentSpotNumberChoice = boardNumber
    hoverSelection.style.visibility = "hidden";
    makeMove(currentSpotChoice.children[0].id, currentSpotNumberChoice);
}
boardNumBoxesArray.forEach(boardNum => {
    boardNum.addEventListener("click", selectBoardNumber);
});
//-------------------------------Update-Board---------------------------------
/**
 * Makes a move in the game by sending a request to the server and updating 
 * the game board.
 * @param {string} spotChoice - The spot choice for the move.
 * @param {number} spotNumberChoice - The spot number value for the move.
 * @return {void}
 * @see selectBoardNumber
 */
function makeMove(spotChoice, spotNumberChoice) {
    let request = new XMLHttpRequest();
    request.open("POST", `/spot/${spotChoice}/${spotNumberChoice}`);
    request.onload = function () {
        let fragResponse = request.responseText;
        let boardDiv = document.getElementById('game-section');
        boardDiv.outerHTML = fragResponse;
        attachListeners();
    }
    request.send();
    submitTime();
}
//==============================-Check-Board-=================================
/**
 * Represents the checkBoardButton element in the DOM.
 *
 * @type {HTMLElement}
 */
let checkBoardButton = document.getElementById('check-game-div');
/**
 * Checks if the given variable 'isWinningBoard' is set to true.
 *
 * @param {boolean} isWinningBoard - The variable to check if it represents 
 * a winning board state.
 * @returns {boolean} - True if the given variable is set to true, 
 * false otherwise.
 */
let isWinningBoard = false; // SHINING VARIABLE
/**
 * Sends a request to the server to check the game board for winning condition.
 * If the game board is a winning board, it sets the inProgress flag to false 
 * and shows "You Win!" alert.
 * @return {void} Returns nothing.
 */
function checkBoard() {
    let request = new XMLHttpRequest();
    request.open("POST", "/check");
    request.onload = function () {
        isWinningBoard = request.responseText;
        console.log(`isWinningBoard: ${isWinningBoard}`);
        if (isWinningBoard === "true") {
            inProgress = false;
            alert("You Win!");
        }
    }
    request.send();
}
checkBoardButton.addEventListener("click", checkBoard);
//============================-Attach-Listeners-==============================

/**
 * Attaches event listeners to various elements in the DOM. When an event
 * changes major elements in the DOM, the event listeners are affected and
 * need to be re-attached.
 *
 * @returns {void}
 */
function attachListeners() {
    const mutedColor = "#cccccc";
    //-----------------------------Set-Difficulty-----------------------------
    difficultySection = document.getElementById('difficulty-section');
    difficultyButtons = document.getElementsByClassName('difficulty-option');
    difficultyButtonsArray = Array.from(difficultyButtons);
    difficultyButtonsArray.forEach(button => {
        button.removeEventListener("click", setDifficulty);
        button.addEventListener("click", setDifficulty);
        
        button.removeEventListener("click", closeDifficultyMenu);
        button.addEventListener("click", closeDifficultyMenu);
    });
    //---------------------------New-Game-Button------------------------------
    newGameButton = document.getElementById('new-game-div');
    newGameButton.removeEventListener("click", popupDifficultyMenu);
    newGameButton.addEventListener("click", popupDifficultyMenu);
    //----------------------------Mouse-Movements-----------------------------
    mouseCords = {x: 0, y: 0};
    document.removeEventListener("mousemove", monitorMouseCords);
    document.addEventListener("mousemove", monitorMouseCords);
    //----------------------------Spot-Selection------------------------------
    spots = document.getElementsByClassName('num-container');
    spotsArray = Array.from(spots);
    spotsArray.forEach(spot => {
        spot.removeEventListener("click", selectSpot);
        if (spot.style.backgroundColor !== mutedColor) {
            spot.addEventListener("click", selectSpot);
        }
    });
    //----------------------------Num-Choice-Menu-----------------------------
    hoverSelection = document.getElementById("hover-selection");
    spotsArray.forEach(spot => {
        spot.removeEventListener("click", revealNumChoiceMenu);
        if (spot.style.backgroundColor !== mutedColor) {
            spot.addEventListener("click", revealNumChoiceMenu);
        }
    });
    //--------------------------Select-Board-Number---------------------------
    boardNumBoxes = document.getElementsByClassName("hover-num-container");
    boardNumBoxesArray = Array.from(boardNumBoxes);
    boardNumBoxesArray.forEach(boardNum => {
        boardNum.removeEventListener("click", selectBoardNumber);
        boardNum.addEventListener("click", selectBoardNumber);
    });
}