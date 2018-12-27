var eventOn = (e) => {
    e.preventDefault();
    $("#login_home").css("display","none");
    $("#myPage").css("display","block");
    $("#userId").text($("#id").val());
    $("#userPw").text($("#pw").val());
}

function memberOpen(){
            var Url = "member.html";	//팝업창에 출력될 페이지 URL
            var screenW = screen.availWidth;
            var screenH = screen.availHeight;
            var posT=(screenH-375) / 2;
            var posL=(screenW-784) / 2;
            var Option = "width=715, height=410, top="+posT+",left="+posL+", resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

            window.open(Url,"",Option);
        }

function returnOpen() {
    location.href = "main.html";
}
