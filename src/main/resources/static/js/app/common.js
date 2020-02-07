$(document).ready(function () {
    // 헤더 메뉴 active 활성화
    var menuVar_01 = window.location.pathname;

// console.log(menuVar_01);
    var temp_menuVar_01 = menuVar_01.substring(1) == "" ? "home" : menuVar_01.substring(1);
// console.log(temp_menuVar_01);
    if (temp_menuVar_01.indexOf("products/01") !== -1)
      temp_menuVar_01 = "products";
    else if (temp_menuVar_01.indexOf("stores/01") !== -1)
      temp_menuVar_01 = "stores";
    else if (temp_menuVar_01.indexOf("articles/01") !== -1)
      temp_menuVar_01 = "articles";
    else if (temp_menuVar_01.indexOf("qna/01") !== -1)
      temp_menuVar_01 = "qna";
    else if (temp_menuVar_01.indexOf("admin/01") !== -1)
      temp_menuVar_01 = "admin";
    else if (temp_menuVar_01.indexOf("myprofile/01") !== -1)
      temp_menuVar_01 = "myprofile";

    $(".headerMenu").each(function (index) {
        var e = $(this);
        var e_name = e.attr("name");

        if (e_name == temp_menuVar_01)
            $(this).addClass("active");
        else
            $(this).removeClass("active");
    });

    // 페이지별 LoadPage 실행 함수
    if (typeof (LoadPage) == "function") {
        var lp = LoadPage();
        $(lp).promise().done(function () {
        });
    }

});

/**
 * class 추가 함수
 *
 * @member global
 * @method plusClass
 * @param {String}
 *          pageUrl
 * @param {String}
 *          pageMethod
 * @return none
 */
function plusClass(className, tagType, addClassName) {
    if ($("." + className).parents(tagType).hasClass(addClassName) != true) {
        console.log($(this));
        // $(this).addClass(addClassName);
    }
}

/**
 * 페이지 이동 함수
 *
 * @member global
 * @method movePage
 * @param {String}
 *          pageUrl
 * @param {char}
 *          pageMethod
 * @param {Array}
 *          optData
 * @return none
 */
function movePage(pageUrl, pageMethod, optData) {
    if (pageMethod == null || pageMethod == '' || pageMethod == undefined) {
        pageMethod = 'get';
    }

    var form = document.createElement("form");
    var param = new Array();
    // var input = new Array();

    form.action = pageUrl;
    form.method = pageMethod;

    // if(headerMenuActiveYn)
    // {
    // headerMenuActive();
    // }

    // param.push([ 'optData', optData ]);
    //
    // for (var i = 0; i < param.length; i++)
    // {
    // input[i] = document.createElement("input");
    // input[i].setAttribute("type", "hidden");
    // input[i].setAttribute("name", param[i][0]);
    // input[i].setAttribute("value", param[i][1]);
    //
    // form.appendChild(input[i]);
    // }

    document.body.appendChild(form);

    form.submit();
}

function fileUpload() {
    var fileTarget = $('.filebox .upload-hidden');
    fileTarget.on('change', function () {
        if (window.FileReader) {
            // 파일명 추출
            var filename = $(this)[0].files[0].name;
        } else {
            // Old IE 파일명 추출
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }
        ;
        $(this).siblings('.upload-name').val(filename);
    });

    // preview image
    var imgTarget = $('.preview-image .upload-hidden');

    imgTarget.on('change', function () {
        var parent = $(this).parent();
        parent.children('.upload-display').remove();

        if (window.FileReader) {
            // image 파일만
            if (!$(this)[0].files[0].type.match(/image\//))
                return;

            var reader = new FileReader();
            reader.onload = function (e) {
                var src = e.target.result;
                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="' + src + '" class="upload-thumb"></div></div>');
            }
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            $(this)[0].select();
            $(this)[0].blur();
            var imgSrc = document.selection.createRange().text;
            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');
            var img = $(this).siblings('.upload-display').find('img');
            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\"" + imgSrc + "\")";
        }
    });
}

// 스마트 에디터 폼
function callSmartEditor(idVal) {
    var editor_object = [];
    // 스마트 에디터 생성 함수
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: idVal, // content 는 스마트에디터가 추가될 텍스트 에어리어
        sSkinURI: "/editor/SmartEditor2Skin.html",
        htParams: {
            bUseToolbar: true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer: true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger: true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bSkipXssFilter: true, // client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
            // aAdditionalFontList : aAdditionalFontSet, // 추가 글꼴 목록
            fOnBeforeUnload: function () {

            }
        },
        fCreator: "createSEditor2"
    });

    $("#editorSubmitBtn").click(function () {
        // newContents 테스트 에어리어의 value로 내용 담아주는 함수
        editor_object.getById[idVal].exec("UPDATE_CONTENTS_FIELD", []);
        $("#editor_form").submit();
    });
}

function returnElapsedTime(time) {
    var min = 60 * 1000;
    var c = new Date()
    var d = new Date(time);
    var minsAgo = Math.floor((c - d) / (min));

    var result = {
        'raw': d.getFullYear() + '-' + (d.getMonth() + 1 > 9 ? '' : '0') + (d.getMonth() + 1) + '-' + (d.getDate() > 9 ? '' : '0') + d.getDate() + ' ' + (d.getHours() > 9 ? '' : '0') + d.getHours() + ':' + (d.getMinutes() > 9 ? '' : '0') + d.getMinutes() + ':' + (d.getSeconds() > 9 ? '' : '0') + d.getSeconds(),
        'formatted': '',
    };

    // 1시간 내
    if (minsAgo < 60)
        result.formatted = minsAgo + '분 전';
    // 하루 내
    else if (minsAgo < 60 * 24)
        result.formatted = Math.floor(minsAgo / 60) + '시간 전';
// 하루 이상
    else
        result.formatted = Math.floor(minsAgo / 60 / 24) + '일 전';

    return result.formatted;
}

// function send(otherParams, onSuccessXS, onComplete, options)
// {
// options = options || {};
// $.ajax({
// url : options.url
// , async : options.async==null?true:options.async
// , type : options.type || "POST"
// , beforeSend : function(xhr){ xhr.setRequestHeader( 'ajax' , 'true' ); }
// , contentType : "application/json"
// , data : options.parameters
// , dataType : 'json'
// , success : options.onSuccess || function (transport) { onSuccess(transport, onSuccessXS, options); }
// , complete : options.onComplete || onComplete
// , error : function(xhr, textStatus, thrownError){
// options.error || onFailure(xhr, textStatus, thrownError);
// }
// })
// .always(function(){
// if(options.Progress == true)
// {
// Progress.stop();
// }
// });
// }
//
// function ajaxRequestXS(dsClass, dsMethod, otherParams, onSuccessXS, onComplete, options){
// options = $.extend( options , { Progress : false });
// send(dsClass, dsMethod, otherParams, onSuccessXS, onComplete, options);
// }
//
// function ajaxRequestXSProg(dsClass, dsMethod, otherParams, onSuccessXS, onComplete, options){
// Progress.start();
// options = $.extend( options , { Progress : true });
// send(dsClass, dsMethod, otherParams, onSuccessXS, function(transport, param){onComplete && onComplete(transport, param); }, options);
// }
//
// function ajaxSyncRequestXS(dsClass, dsMethod, otherParams, onSuccessXS){
// send(dsClass, dsMethod, otherParams, onSuccessXS, null, {async: false, Progress : false});
// }


function datetimeConvert(v) {
    var format = new Date(v);
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    var date = format.getDate();
    var hour = format.getHours();
    var min = format.getMinutes();
    var sec = format.getSeconds();

    if (month < 10)
        month = '0' + month;

    if (date < 10)
        date = '0' + date;

    if (hour < 10)
        hour = '0' + hour;

    if (min < 10)
        min = '0' + min;

    if (sec < 10)
        sec = '0' + sec;

    return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
}
