/**
 * Created by valko on 30-Aug-16.
 */

var localL = {

    shuffledData: shuffle(common.incomeData)


};

function shuffle(a) {
    var j, x, i;
    for (i = a.length; i; i--) {
        j = Math.floor(Math.random() * i);
        x = a[i - 1];
        a[i - 1] = a[j];
        a[j] = x;
    }
    return a;
}

function checkQuestion(questionNumber) {
    var radios = document.getElementsByName("answer_" + questionNumber);
    var userChoice;
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            userChoice = radios[i].value;
        }
        var radiosText = document.getElementById("answer_text_" + questionNumber + "_" + i);
        if (radios[i].value == getCorrectAnswer(questionNumber)) {
            radiosText.setAttribute("style", "color: green;");
        }
        radios[i].disabled = "true";
    }
    if (userChoice != null) {
        return getCorrectAnswer(questionNumber) == userChoice;
    } else {
        return false;
    }
}

function getCorrectAnswer(questionNumber) {
    return localL.shuffledData[questionNumber].answers[0].hashCode();
}