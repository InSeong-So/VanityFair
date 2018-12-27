function siteOpen(){
            var Url = "site.html";	//팝업창에 출력될 페이지 URL
            var screenW = screen.availWidth;
            var screenH = screen.availHeight;
            var posL=(screenW-715) / 2;
            var posT=(screenH-730) / 2;
            var Option = "width=715, height=730, top="+posT+", left="+posL+", resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

            window.open(Url,"",Option);
        }

function loginOpen(){
            var Url = "login.html";	//팝업창에 출력될 페이지 URL
            var screenW = screen.availWidth;
            var screenH = screen.availHeight;
            var posL=(screenW-715) / 2;
            var posT=(screenH-730) / 2;
            var Option = "width=615, height=700, top="+posT+", left="+posL+", resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

            window.open(Url,"",Option);
        }
