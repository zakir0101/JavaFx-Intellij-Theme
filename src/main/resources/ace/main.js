// import ace from "/ace-builds/src-noconflict/ace"

ace.require("ace/ext/language_tools");

var editor = ace.edit("editor");
// editor.getSession().on("change",function (){
//     window.javaConnector.sendTextToJava(editor.getSession.getValue())
// })
// editor.setTheme("ace/theme/dracula");
// editor.session.setMode("ace/mode/python");
editor.setKeyboardHandler("ace/keyboard/vscode");
editor.setOptions({
    enableBasicAutocompletion: true,
    enableSnippets: true,
    enableLiveAutocompletion: false,
    fontSize : "14px"
});
// ace.setFontSize(14)



let jsConnector = {
    sendTextToJs : function setText(text){
        // document.getElementById("editor").innerHTML = text
        editor.setValue(text,-1)
    }
}
function getJsConnector(){
    return jsConnector
}

function setDarkMode(){
    editor.setTheme("ace/theme/dracula");
}

function setLightMode(){
    editor.setTheme("ace/theme/textmate");
}


function setLangJava(){
    editor.session.setMode("ace/mode/java");
}

function setLangKotlin(){
    editor.session.setMode("ace/mode/kotlin");

}

function setLangPython(){
    editor.session.setMode("ace/mode/python");

}

function setLangJavascript(){
    editor.session.setMode("ace/mode/javascript");
}
function setLangHtml(){
    editor.session.setMode("ace/mode/html");

}
function setLangCss(){
    editor.session.setMode("ace/mode/css");

}