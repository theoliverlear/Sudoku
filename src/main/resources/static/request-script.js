//----------------------------------New-Game----------------------------------
let newGameButton = document.getElementById('new-game-div');
function newGame() {
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
newGameButton.addEventListener("click", newGame);
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
    currentSpotNumberChoice = this.children[0].innerText;
    hoverSelection.style.visibility = "hidden";
    makeMove(currentSpotChoice.children[0].id, currentSpotNumberChoice);
}
boardNumBoxesArray.forEach(boardNum => {
    boardNum.addEventListener("click", selectBoardNumber);
});
//-------------------------------Update-Board---------------------------------
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

//============================-Attach-Listeners-==============================
function attachListeners() {
    const mutedColor = "#cccccc";
    //---------------------------New-Game-Button------------------------------
    newGameButton = document.getElementById('new-game-div');
    newGameButton.addEventListener("click", newGame);
    newGameButton.addEventListener("click", newGame);
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