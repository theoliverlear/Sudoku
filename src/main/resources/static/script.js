let boardNumBoxes = document.getElementsByClassName("num-container");
let boardNumBoxesArray = Array.from(boardNumBoxes);
let hoverSelection = document.getElementById("hover-selection");
boardNumBoxesArray.forEach(boardNum => {
   boardNum.addEventListener("click", selectBoardNumber);
});
let spotChoice;
//---------------------------Track-Mouse-Movements----------------------------
let mouseCords = {x: 0, y: 0};
document.addEventListener("mousemove", monitorMouseCords);
function monitorMouseCords (mouseEvent) {
    mouseCords.x = (mouseEvent.clientX / window.innerWidth) * 100;
    mouseCords.y = (mouseEvent.clientY / window.innerHeight) * 100;
}
//----------------------------Select-Board-Number-----------------------------
function selectBoardNumber() {
    let selectedColor = "rgba(255, 0, 0, 0.5)";
    let defaultColor = "rgba(255, 255, 255, 0.9)";
    spotChoice = this;
    boardNumBoxesArray.forEach(boardNum => {
        if (this !== boardNum) {
            boardNum.style.backgroundColor = defaultColor;
        }
    });
    let hoverSelectionVisible = hoverSelection.style.visibility === "visible";
    if (this.style.backgroundColor === selectedColor) {
        this.style.backgroundColor = defaultColor;
        hoverSelection.style.visibility = "hidden";
        spotChoice = null;
    } else {
        this.style.backgroundColor = selectedColor;
        hoverSelection.style.visibility = "visible";
        hoverSelection.style.left = (mouseCords.x - 15) + "vw";
        hoverSelection.style.top = (mouseCords.y - 1.5) + "vh";
    }
}
let choiceNumBoxes = document.getElementsByClassName("hover-num-container");
let choiceNumBoxesArray = Array.from(choiceNumBoxes);
choiceNumBoxesArray.forEach(choiceNum => {
    choiceNum.addEventListener("click", selectChoiceNumber);
});

let spotNumberChoice;
//-------------------------Select-Choice-Menu-Number--------------------------
function selectChoiceNumber() {
    spotNumberChoice = this.innerText;
    for (let i = 0; i < boardNumBoxesArray.length; i++) {
        if (spotChoice === boardNumBoxesArray[i]) {
            spotChoice.children[0].innerText = spotNumberChoice;
            break;
        }
    }
    spotChoice.style.backgroundColor = "rgba(255, 255, 255, 0.9)";
    hoverSelection.style.visibility = "hidden";
    spotChoice = null;
    spotNumberChoice = null;
}
//--------------------------------Clear-Board---------------------------------
let clearBoardButton = document.getElementById("new-game-div");
clearBoardButton.addEventListener("click", clearBoard);
function clearBoard() {
    boardNumBoxesArray.forEach(boardNum => {
        boardNum.children[0].innerText = "";
    });
}
