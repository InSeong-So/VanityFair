function qna() {
    $("#qa_tap li").off().on("click", function(){
                var i = $(this).index() + 1;
                var title = ["<h3>공지사항</h3>", "<h3>자주묻는 질문</h3>", "<h3>고객 의견</h3>"];
                $("#qa_board table").hide();
                $("#qa_table" + i).show();
                $("#qa_btitle").empty();
                $("#qa_btitle").html(title[i - 1]);
                if (i == 1) {
                    $("#qa_bno").css("display", "inline-block");
                    $("#qa_ta2tap").css("display", "none");
                    
                } else if (i == 2) {
                    $("#qa_bno").css("display", "none");
                    $("#qa_ta2tap").css("display", "inline-block");
                    
                } else if (i == 3) {
                    $("#qa_bno").css("display", "inline-block");
                    $("#qa_ta2tap").css("display", "none");
                    
                }
            });
}

function reaservation() {
    var areavalue;
            $("#rsv_area ul li").off().on("click", function(){
                areavalue = $(this).index();
                areasearch(areavalue);
            });
            $("#rsv_startbutton").off().on("click", function(){
                var tableselect = $("#rsv_menu1 input:checked").val();
                if (tableselect == "bag") {
                    $("#rsv_list_table1").css("display", "table");
                    $("#rsv_list_table2").css("display", "none");
                }else if (tableselect == "watch") {
                    $("#rsv_list_table2").css("display", "table");
                    $("#rsv_list_table1").css("display", "none");
                }
                areavalue = $("#rsv_menu3 select").val();
                if(areavalue != -1){
                    areasearch(areavalue);
                }
            });
            function areasearch(av) {
                var i = av - 1;
                var mapurl = ["https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3313740.168915977!2d125.62462485849026!3d35.7981071861466!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356455ebcb11ba9b%3A0x91249b00ba88db4b!2z64yA7ZWc66-86rWt!5e0!3m2!1sko!2skr!4v1527065548992", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d404811.2671992865!2d126.70936381706494!3d37.56476893071881!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca28b61c565cd%3A0x858aedb4e4ea83eb!2z7ISc7Jq47Yq567OE7Iuc!5e0!3m2!1sko!2skr!4v1527137551193", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d101338.83954294637!2d126.60431045585904!3d37.46452933176048!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35796f2596138247%3A0x7d37fd902cb76142!2z7J247LKc6rSR7Jet7Iuc!5e0!3m2!1sko!2skr!4v1527137642134", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d809272.1685923658!2d126.53549619265904!3d37.59699492544846!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c79e4e57eb68d%3A0x10c1f98d6f6b5c2!2z6rK96riw64-E!5e0!3m2!1sko!2skr!4v1527137671773", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d806346.3823678893!2d127.68734705807618!3d37.86519080782262!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356237ec59968d89%3A0xe150c7d25a07a6e!2z6rCV7JuQ64-E!5e0!3m2!1sko!2skr!4v1527137698198", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d102799.74712085196!2d127.3187604303316!3d36.37307955873017!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356548d7ba4a6601%3A0xd196d69d988ad3b5!2z64yA7KCE6rSR7Jet7Iuc!5e0!3m2!1sko!2skr!4v1527137742293", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d103447.88670142986!2d128.4966604190018!3d35.87972967108084!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3565e3b8b2efadd5%3A0xba92e029a0382e30!2z64yA6rWs6rSR7Jet7Iuc!5e0!3m2!1sko!2skr!4v1527137800046", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d52179.11813465025!2d126.80878064034218!3d35.176694960525616!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3571892301f5a7af%3A0x5f4d2ed0125f548!2z6rSR7KO86rSR7Jet7Iuc!5e0!3m2!1sko!2skr!4v1527137828854", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d104373.97914101335!2d129.0017604028086!3d35.16442984467441!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3568eb6de823cd35%3A0x35d8cb74247108a7!2z67aA7IKw6rSR7Jet7Iuc!5e0!3m2!1sko!2skr!4v1527137856878", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d425479.10172023147!2d126.29284951448652!3d33.57790868955271!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x350ce3544cc84045%3A0x66bc36d2981ebf31!2z7KCc7KO87Yq567OE7J6Q7LmY64-E!5e0!3m2!1sko!2skr!4v1527137892293"];
                var HTML = '<iframe src=' + mapurl[i] + ' width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>';
                $("#rsv_map").empty();
                $("#rsv_map").html(HTML);
            }
}