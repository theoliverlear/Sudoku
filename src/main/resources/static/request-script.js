//==================================-Timer-===================================

//--------------------------------Time-Counter--------------------------------
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
let timeString = "";
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
    let timeSeconds = 0;
    let timeMinutes = 0;
    let secondsString = "";
    let minutesString = "";
    
    while (true) {
        if (inProgress) {
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
            timeSeconds++;
            if (timeSeconds === 60) {
                timeSeconds = 0;
                timeMinutes++;
            }
        }
        await sleep(1000);
    }
}
//--------------------------------Sleep-Wait----------------------------------
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
timeCounter();
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
    
    // newGameButton.addEventListener("click", newGame);
    // newGameButton.addEventListener("click", newGame);
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