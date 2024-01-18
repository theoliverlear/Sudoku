const assert = require("assert");
const { describe, it } = require("mocha");

describe("timer", function() {
    let timerString = "00:00";
    it("should have zero seconds", function() {
        let expected = 0;
        let actual = parseInt(timerString.split(":")[1]);
        console.log(actual);
        assert.equal(actual, expected);
    });
    it("should have zero minutes", function() {
        let expected = 0;
        let actual = parseInt(timerString.split(":")[0]);
        console.log(actual);
        assert.equal(actual, expected);
    });
});

describe("timer", function() {
    let timerString = "00:01";
    it("should have one second", function() {
        let expected = 1;
        let actual = parseInt(timerString.split(":")[1]);
        console.log(actual);
        assert.equal(actual, expected);
    });
});
describe("timer", function() {
    let timerString = "00:39";
    it ("should have 39 seconds", function() {
        let expected = 39;
        let actual = parseInt(timerString.split(":")[1]);
        console.log(actual);
        assert.equal(actual, expected);
    });
});
describe("timer", function() {
    let timerString = "01:05";
    it("should have 5 seconds", function() {
        let expected = 5;
        let actual = parseInt(timerString.split(":")[1]);
        console.log(actual);
        assert.equal(actual, expected);
    });
    it("should have 1 minute", function() {
        let expected = 1;
        let actual = parseInt(timerString.split(":")[0]);
        console.log(actual);
        assert.equal(actual, expected);
    });
});

describe("timer array", function() {
   it("should split 2 ways", function() {
       let expected = 2;
       let actual = "00:00".split(":").length;
       console.log(actual);
       assert.equal(actual, expected);
   });
});