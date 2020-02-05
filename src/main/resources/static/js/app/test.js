$(function() {

    //Top/Down 스크롤 위치에 따른 버튼 노출 처리
    $(".top_down_position").data("block", true);
    $(window).scroll(function() {
        if ($(document).scrollTop() >= 125) {
            if ($(".top_down_position").data("block") == true) {
                $(".top_down_position").show();
                $(".top_down_position").data("block", false);
            }
        } else {
            $(".top_down_position").hide();
            $(".top_down_position").data("block", true);
        }
    });

    //TOP/DOWN 버튼 클릭 이벤트
    $(".top_down_position .top_down_area .top_down_fixed a").click(function(e) {
        e.preventDefault();
        if ($(this).hasClass("btn_layer_up") == true) {
            $(document).scrollTop(0);
        } else {
            $(document).scrollTop($(document).height() - $(window).height());
        }
    });

    //3depth 부터 노출되는 카테고리 큐레이션 영역의 mouseover, mouseleave
    $(".nav_item").mouseenter(function() {
        //touch기능이 제공되는 device는 mouseover 처리는 하지 않는다.		
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            return false;
        }
        $("ul.spec_nav .spec_show").removeClass("spec_show");
        $(this).addClass("spec_show");

        var sFirstCurationCategoryName = $(this).find(".nav_link .link_txt").attr("data-origin-curation-cate-name");
        var sCurrentCategoryName = $(this).find(".nav_link .link_txt").text();

        $(this).find(".nav_link .link_txt").text(sFirstCurationCategoryName);
        $(this).find(".nav_link .link_txt").data("sCurrentCategoryName", sCurrentCategoryName);

        var sCurrentUrl = $(this).find('a').attr('href');

        //카테고리 설명 조회
        getCategoryExplain(sCurrentUrl);

    }).mouseleave(function() {
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            return false;
        }
        $(this).removeClass("spec_show");

        var sCurrentCategoryName = $(this).find(".nav_link .link_txt").data("sCurrentCategoryName");

        $(this).find(".nav_link .link_txt").text(sCurrentCategoryName);

        setCategoryExplainDefault();
    });

    //3depth 네비게이션 focus 이벤트 - 카테고리 설명
    $(".nav_link").focus(function() {
        setCategoryExplainDefault();
        getCategoryExplain($(this).attr('href'));
    });

    $(".nav_4depth").mouseenter(function() {
        //카테고리 설명 조회
        var sCurrentUrl = $(this).find('a').attr('href');
        getCategoryExplain(sCurrentUrl);
    }).mouseleave(function() {
        setCategoryExplainDefault();
    });

    //3depth 부터 노출되는 카테고리 큐레이션 영역의 click
    $(".nav_item .nav_link").click(function() {
        //touch기능이 제공되는 device에 대한 처리
        //touch 기능이 제공되는 device는 자식 영역이 있고, spec_show클래스가 있을 경우만 링크 이동
        //자식 영역이 없을 경우에는 이동 처리
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            if ($(this).parent().find(".spec_list_wrap").length > 0) {
                if ($(this).parent().hasClass("spec_show") == false) {
                    $("ul.spec_nav .spec_show").removeClass("spec_show");
                    $(this).parent().addClass("spec_show");

                    var sFirstCurationCategoryName = $(this).find(".link_txt").attr("data-origin-curation-cate-name");
                    var sCurrentCategoryName = $(this).find(".link_txt").text();

                    $(this).find(".link_txt").text(sFirstCurationCategoryName);
                    $(this).find(".link_txt").data("sCurrentCategoryName", sCurrentCategoryName);

                    return false;
                } else {
                    $(this).parent().removeClass("spec_show");

                    var sCurrentCategoryName = $(this).find(".link_txt").data("sCurrentCategoryName");

                    $(this).find(".link_txt").text(sCurrentCategoryName);

                    return true;
                }
            } else {
                return true;
            }
        }
    });

    //3depth 부터 노출 되는 카테고리 큐레이션 영역이 아닌 영역에 마우스 포커스가 이동 되었을 경우 하위카테고리 노출 layer 숨김
    $("#danawa_wrap div:not('.nav_item, .spec_list_wrap')").mouseenter(function() {
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            $(".spec_nav_wrap .spec_show").removeClass("spec_show");
        }
    });

    //Show Sub Category : LNB 오버시 하위 카테고리 레이어 노출 (show_sub_cat 클래스 추가)
    $(".category_nav .main_item").hover(function() {
        //현재 선택된 category는 실행 안함, 터치 기능이 있는 Device는 실행 안함.
        if ($(this).hasClass("now_main_cat") || (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1)) {
            return;
        }
        $(this).addClass("show_sub_cat").siblings(".show_sub_cat").removeClass("show_sub_cat");
    }, function() {
        //현재 선택된 category는 실행 안함, 터치 기능이 있는 브라우저는 실행 안함.
        if ($(this).hasClass("now_main_cat") || (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1)) {
            return;
        }
        $(this).removeClass("show_sub_cat");
        setCategoryExplainDefault();
    });

    //lnb focus function
    $(".category_nav .main_item .main_link").focus(function() {
        //현재 선택된 category는 실행 안함, 터치 기능이 있는 Device는 실행 안함.
        var pareObj = $(this).parent();

        if (pareObj.hasClass("now_main_cat") || (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1)) {
            return;
        } else {
            pareObj.addClass("show_sub_cat").siblings(".show_sub_cat").removeClass("show_sub_cat");

            $(this).keydown(function(e) {
                if (e.which == 9) {
                    pareObj.find('li.sub_item').eq(0).focus();
                }
            });

        }
    });

    $('li.dir_item.dir_home a, li.main_item_banner').focus(function(e) {
        $("li.main_item.show_sub_cat").removeClass("show_sub_cat");
    });

    //인기옵션 전체보기 시 option_total_show 클래스 토글
    $(".option_nav .btn_all_opt").click(function() {
        //용어사전 영역 감춤
        $.termDicViewLayerHide();

        // 직접연관 레이어 감춤
        $.relationProductLayerHide();

        //실행 중인 검색 옵션 조회 AJAX 중지
        if (oSearchOptionAjax != null && oSearchOptionAjax.readyState != 4) {
            oSearchOptionAjax.abort();
            //검색 옵션 page cover 숨김
            $(".search_option_cover").hide();
        }

        var sListType = getListType();

        //전체보기 상태별 분기
        if ($(".option_nav").hasClass("option_total_show") == true) { //기본 옵션을 노출
            $(".option_nav").removeClass("option_total_show");
            $(".detail_list_wrap #extendSearchOption" + sListType).hide();
            $(".detail_list_wrap #simpleSearchOption" + sListType).show();

            //상품 리스트 page cover 위치 조정
            productListPageCoverPosition();
        } else { //상세 옵션을 노출
            $(".option_nav").addClass("option_total_show");

            //가격비교 탭에서만 가격대 검색 노출
            if (sListType == "priceCompare") {
                $(".view_opt .price_opt").show();
            }

            //이미 불러온 검색옵션이 없을때만 실행
            if ($(".detail_list_wrap #extendSearchOption" + sListType + " .spec_list").length == 0) {
                getSearchOption();
            } else {
                $(".detail_list_wrap #extendSearchOption" + sListType).show();
                $(".detail_list_wrap #simpleSearchOption" + sListType).hide();
                //상품 리스트 page cover 위치 조정
                productListPageCoverPosition();
            }
        }

        if (sAffiliateId != "") {
            iframeAutoSize();
        }
    });

    //브랜드 로그 더보기 마우스 오버
    $(".brand_list .brand_item").hover(function() {
        //터치 기능이 있는 Device는 실행 안함.
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            return false;
        }
        $(this).addClass("show_sub_brand").siblings(".show_sub_brand").removeClass("show_sub_brand");
    }, function() {
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            return false;
        }
        $(this).removeClass("show_sub_brand");
    });

    //브랜드로그 영역 클릭시 발생 이벤트
    $(".brand_list .brand_item a.brand_link, .brand_list .brand_item .btn_sub_brand").click(function() {
        /**
         * 터치 기능이 있는 Device는 서브레이어가 있을시 클릭시 서브 레이어 표시,
         * 서브 레이어 표시 상태에서 클릭시 이동
         * 서브 레이어가 없으면 아무 처리 없이 이동
         */
        if (
            (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) &&
            $(this).parent().find(".sub_brand_layer").length > 0
        ) {
            if ($(this).parent().hasClass("show_sub_brand") == true) {
                $(this).parent().removeClass("show_sub_brand");
                return true;
            } else {
                $(this).parent().siblings(".show_sub_brand").removeClass("show_sub_brand");
                $(this).parent().addClass("show_sub_brand");
                return false;
            }
        } else {
            return true;
        }
    });

    //서브 브랜드 로그 클릭하여 이동시 서브 브랜드 로그 레이어 숨김 처리
    $(".brand_list .brand_item .sub_brand_list .sub_brand_item a").click(function() {
        $(".brand_list .show_sub_brand").removeClass("show_sub_brand");
        return true;
    });

    //브랜드 로그 영역이 아닌 영역에 마우스 포인터가 이동되면 서브 브랜드 로그 레이어 숨김 처리
    $("#danawa_wrap div:not('.brand_item, .sub_brand_layer')").mouseenter(function() {
        if (Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1) {
            $(".brand_list .show_sub_brand").removeClass("show_sub_brand");
        }
    });

    //가격비교 형태 탭 선택
    $(".tab_list .tab_item .tab_link").click(function(event) {
        event.preventDefault();
        if ($(this).parent().hasClass("selected") == true) {
            return false;
        } else {
            //실행 중인 검색 옵션 조회 AJAX 중지
            if (oSearchOptionAjax != null && oSearchOptionAjax.readyState != 4) {
                oSearchOptionAjax.abort();
                //검색 옵션 page cover 숨김
                $(".search_option_cover").hide();
            }

            var sListType = getListType();
            var sStandardCate = oGlobalSetting.bStandardCategory;

            $(".detail_list_wrap div[id^='simpleSearchOption']").hide();
            $(".detail_list_wrap div[id^='extendSearchOption']").hide();
            $(".option_nav").removeClass("option_total_show"); //옵션검색 전체보기 클래스 변경			
            $.termDicViewLayerHide(); //용어사전 감춤
            // 직접연관 레이어 감춤
            $.relationProductLayerHide();
            //$(document).scrollTop(0); //탭 이동시 상품리스트 스크롤 초기화

            //clearSearchOptionFilter(false); //선택한 검색 옵션 영역 초기화
            $("#filter_" + sListType).hide();
            $("#innerSearchKeyword").val(""); //검색한 리스트내 검색어 초기화
            //상세검색 레이어 초기화
            $("#search_include").val("");
            $("#search_exclude").val("");
            $('#detailSearchTotalTxt').text("");
            oDetailSearchSetting.sInnerDetailSearchKeyword = '';

            $(this).parent().siblings(".selected").find("a.tab_link .list_num").remove();
            $(this).parent().siblings(".selected").removeClass("selected");
            $(this).parent().addClass("selected");

            sListType = getListType();

            //검색 상품, 중고 장터 탭에서 옵션 영역 전체 숨김
            $(".news_movie_wrap").hide();
            if (sListType == "searchProduct") {
                $(".option_nav").hide();
                $(".view_opt .price_opt").show();
                $(".tab_intro").show();
            } else if (sListType == "market") {
                $(".option_nav").hide();
                $(".tab_inro").hide(); //장터 탭에서는 결과내 검색 영역 숨김				
            } else if (sListType == "dpgZone") {
                $(".option_nav").hide();
                $(".tab_inro").hide(); //장터 탭에서는 결과내 검색 영역 숨김
                $.get("./ajax/getNewsMedia.ajax.php", { cate: nUICategoryCode, cate1: oGlobalSetting.sPhysicsCate1, cate2: oGlobalSetting.sPhysicsCate2, sViewType: 'dpg' }, function(standardPCContentsHtml) {
                    if (standardPCContentsHtml != "") {
                        $(".news_movie_wrap").show();
                        $(".news_movie_wrap").html(standardPCContentsHtml);
                    }
                });
            } else {
                if (sStandardCate == 'Y') {
                    $(".option_nav").hide();
                    $(".tab_intro").hide();
                } else {
                    //이미 불러온 검색옵션이 없을때만 실행
                    if ($(".detail_list_wrap #simpleSearchOption" + sListType + " .spec_list").length == 0) {
                        getSimpleSearchOption();
                    } else {
                        $(".detail_list_wrap #simpleSearchOption" + sListType + "").show();
                    }
                    //단종상품 탭에서 가격대 검색 영역 숨김
                    if (sListType == "discontinue") {
                        $(".view_opt .price_opt").hide();
                    } else {
                        $(".view_opt .price_opt").show();

                    }

                    if ($("#filter_" + sListType + " .filter_list li").length > 0) {
                        $("#filter_" + sListType).show();
                    }

                    if ($("#btnAllOptUse").val() == "true") {
                        $(".option_nav .nav_header .head_opt").show();
                    }

                    //상세검색 옵션 있을때만 노출
                    if ($("#searchOptionTotalCount").val() != 0) {
                        $(".option_nav").show();
                    }
                    $(".tab_intro").show();
                }
            }

            getProductList(1, true);
        }
    });

    //검색 제외 클릭
    $("#exceptionBtn").click(function() {
        try {
            _trkEventLog("15상품리스트_검색제외");
        } catch (_e) {}
        $("#expt_layer").toggle();
    });

    //검색 제외 체크박스 클릭
    $("#expt_layer .expt_list li input").click(function() {
        getProductList(1);
    });

    //결과내 검색 포커스
    $("#innerSearchKeyword").on('focus', function(e) {
        e.preventDefault();
        oDetailSearchLayerLogger.focusInnerSearchKeyword();
    });

    //결과내 검색 엔터키 press
    $("#innerSearchKeyword").keypress(function(e) {
        if (e.keyCode == 13) {

            var sDetailTotalKeyword = $('#innerSearchKeyword').val();
            oDetailSearchLayerLogger.search(sDetailTotalKeyword);

            if (sProductListApi == 'search') {
                //결과내 검색 버튼 클릭시 상세검색 레이어 결과 초기화
                oDetailSearchSetting.sInnerDetailSearchKeyword = '';
                oAddKeywordHandler.changeFullTextToSplitText();
            }

            getProductList(1);
        }
    });

    //결과내 검색 버튼 클릭
    $(".submit_search_list").click(function(e) {
        if (sProductListApi == 'search') {
            //결과내 검색 버튼 클릭시 상세검색 레이어 결과 초기화
            oDetailSearchSetting.sInnerDetailSearchKeyword = '';
            oAddKeywordHandler.changeFullTextToSplitText();
        }
        getProductList(1);
    });

    //가격비교 탭 버튼 클릭
    $(".tab_compare").click(function(e) {
        try {
            _trkEventLog("15상품리스트_tab_가격비교");
        } catch (e) {}
        clearSearchOptionFilter(true);
        //		getProductList(1);
    });

    //검색상품 탭 버튼 클릭
    $(".tab_search").click(function(e) {
        try {
            _trkEventLog("15상품리스트_tab_검색상품");
        } catch (e) {}
        getProductList(1);
    });

    //중고장터 탭 버튼 클릭
    $(".tab_used").click(function(e) {
        try {
            _trkEventLog("15상품리스트_tab_중고장터");
        } catch (e) {}
        getProductList(1);
    });

    //단종상품 탭 버튼 클릭
    $(".tab_done").click(function(e) {
        try {
            _trkEventLog("15상품리스트_tab_단종상품");
        } catch (e) {}
        getProductList(1);
    });

    //상세검색 레이어 열기
    $('.btn_detail_srch').click(function(e) {
        e.preventDefault();
        oDetailSearchLayerLogger.open();

        if ($('#detailSearchLayer').is(':visible')) {
            $("#detailSearchLayer").hide();
        } else {
            //			oAddKeywordHandler.changeFullTextToSplitText();
            $("#detailSearchLayer").show();
        }
    });

    //상세검색 > 리스트 내 검색 활성화
    $('#search_include').on('focus', function(e) {
        e.preventDefault();
        oDetailSearchLayerLogger.focusSearchInclude();
    });

    //상세검색 > 키워드 제외 활성화
    $('#search_exclude').on('focus', function(e) {
        e.preventDefault();
        oDetailSearchLayerLogger.focusSearchExclude();
    });

    //상세검색 추가/제외키워드 입력 이벤트
    $('#detailSearchLayer #search_include,#search_exclude').on('keydown keyup', function(e) {
        var nKeyCode = e.keyCode;
        if (nKeyCode == 13) {
            var sDetailTotalKeyword = $('#detailSearchTotalTxt').text();
            $('#innerSearchKeyword').val(sDetailTotalKeyword);
            oAddKeywordHandler.setDetailSearchParameter();
            getProductList(1);

            oDetailSearchLayerLogger.search(sDetailTotalKeyword);

        } else if (nKeyCode != 188) {
            //콤마(,)는 제외
            var sIncludeKeywordResult = oAddKeywordHandler.getIncludeKeywordList(' ');
            var sExcludeKeywordResult = oAddKeywordHandler.getExcludeKeywordList(' -');

            var sReplaceText = sIncludeKeywordResult + sExcludeKeywordResult;
            $('#detailSearchTotalTxt').text(sReplaceText);
        }
    });

    //상세검색 레이어 검색 버튼 클릭
    $('#detailSearchSubmitBtn').click(function(e) {
        e.preventDefault();

        var sDetailTotalKeyword = $('#detailSearchTotalTxt').text();
        $('#innerSearchKeyword').val(sDetailTotalKeyword);
        oAddKeywordHandler.setDetailSearchParameter();
        getProductList(1);

        oDetailSearchLayerLogger.search(sDetailTotalKeyword);
    });

    //상세검색 레이어 초기화 버튼 클릭
    $('#detailSearchLayer .btn_refresh').click(function(e) {
        e.preventDefault();
        $('#search_include').val('');
        $('#search_exclude').val('');
        $('#detailSearchTotalTxt').text('');

        oDetailSearchLayerLogger.clear();
    });

    //상세검색 레이어 닫기
    $('.detailSearchCloseBtn').click(function(e) {
        e.preventDefault();
        $("#detailSearchLayer").hide();

        oDetailSearchLayerLogger.close();
    });

    //검색tooltip 6초후 레이어 닫기
    setTimeout(function() {
        $("#tooltipLayer").hide()
    }, 3000);

    //검색tooltip 마우스 오버
    /*
    $(".btn_detail_srch").hover(function(){
    	//터치 기능이 있는 Device는 실행 안함.
    	if(Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1){
    		return false;
    	}
    	$('.tooltip_layer').addClass("show");
    },function(){
    	if(Modernizr.touch == true && window.navigator.userAgent.indexOf("Windows") == -1){
    		return false;
    	}
    	$('.tooltip_layer').removeClass("show");		
    });
    */

    //검색tooltip 레이어 닫기
    $('.btn_tt_close').click(function(e) {
        e.preventDefault();
        $("#tooltipLayer").hide();
    });

    $(document).click(function(event) {
        //결과내검색 영역 이외 클릭시 상세검색 레이어 닫기
        if (!$(event.target).closest('.tab_intro').length) {
            if ($('#detailSearchLayer').is(':visible')) {
                $('#detailSearchLayer').hide();
            }
        }

        //몰별 최저가 레이어 숨김
        if ($(".more_btn_wrap").hasClass("show")) {
            if (!$('.more_btn_wrap').has(event.target).length && !$(event.target).is('.more_btn_wrap')) {
                $(".more_btn_wrap").removeClass("show");
            }
        }

        //상품이미지 팝업 레이어 숨김
        if (!$('#imgExtensionViewArea').has(event.target).length &&
            !$(event.target).is('.btn_view_zoom') &&
            !$(event.target).is('.badge_vdo') &&
            !$('.thumb_opt').has(event.target).length) {
            closeYoutube();
            $('#imgExtensionArea').hide();
        }

        //관심상품 레이어 숨김		
        if (!$('.inte_folder_layer').has(event.target).length && !$(event.target).is('.inte_folder_layer')) {
            $('.inte_folder_layer').remove();
        }

        //관련상품 레이어 영역외 클릭시 닫힘
        if (!$('#layer_pop3').has(event.target).length && !$(event.target).is('#layer_pop3')) {
            $.relationProductClose();
        }

        //검색tooltip 영역외 클릭시 닫힘
        if ($(".tooltip_layer").hasClass("tooltipShow")) {
            if (!$('.tooltip_layer').has(event.target).length && !$(event.target).is('.tooltip_layer')) {
                $(".tooltip_layer").removeClass("show");
            }
        }
    });

    //상품이미지 레이어 영역 위치 이동
    var oProductImageLayerArea = $("#productImageLayerArea").clone();
    $("#productImageLayerArea").remove();
    $("#danawa_footer").before(oProductImageLayerArea);

    var nUICategoryCode = oGlobalSetting.nListGroup + "" + oGlobalSetting.nListDepth + "" + oGlobalSetting.nListCategoryCode;

    //중분류 물리 카테고리가 하나일 경우 우측 영역 조회 
    var aPhysicsCate2List = oGlobalSetting.sPhysicsCate2.split(",");
    if (aPhysicsCate2List.length == 1 && sAffiliateId == "") {
        $.get("./ajax/getRightMedia.ajax.php", { cate: nUICategoryCode, cate1: oGlobalSetting.sPhysicsCate1, cate2: oGlobalSetting.sPhysicsCate2, cate3: oGlobalSetting.sPhysicsCate3, UICategoryCodeList: oGlobalSetting.UICategoryCodeList }, function(rightContentsHtml) {
            if (rightContentsHtml != "") {
                $(".aside_media").html(rightContentsHtml);

                $oTogetherEventBannerFunction = new TogetherEventBannerFunction();

                if ($(".aside_media img").length > 0) {
                    $(".aside_media img").load(function() {
                        var nAsideHeight = $('.aside_content').height() + 80;
                        $('#danawa_container').css('min-height', nAsideHeight);
                    });
                } else {
                    var nAsideHeight = $('.aside_content').height() + 80;
                    $('#danawa_container').css('min-height', nAsideHeight);
                }

                //쇼핑클립 영역에서 사용되는 스크립트
                $("div.titleBox").mouseenter(function() {
                    $(this).find("div.title_box_btm").show();
                }).mouseleave(function() {
                    $(this).find("div.title_box_btm").hide();
                });



            } else {
                $(".aside_media").hide();
            }
        });
    } else {
        var affiliateFlag = false;

        //slrclub은 우측영역 사용하지 않음
        if (sAffiliateId != "") {
            if (sAffiliateId == 'slrclub') {
                affiliateFlag = true;
            }
        }

        //멀티카테고리 설정되어있어도 우측영역 노출해야 상품영역 중앙정렬 안됨
        $(".aside_media").html('');

        if (affiliateFlag) {
            $(".aside_media").hide();
        } else {
            $(".aside_media").show();
        }
    }

    // 표준PC 뉴스,동영상 조회
    var sStandardCate = oGlobalSetting.bStandardCategory;
    var sDpgZoneCate = oGlobalSetting.bDpgZoneCategory;

    if (sStandardCate == 'Y' || sDpgZoneCate == 'Y') {
        var sViewType = 'standard';
        if (sDpgZoneCate == 'Y') {
            sViewType = 'dpg';
        }

        $.get("./ajax/getNewsMedia.ajax.php", { cate: nUICategoryCode, cate1: oGlobalSetting.sPhysicsCate1, cate2: oGlobalSetting.sPhysicsCate2, sViewType: sViewType }, function(standardPCContentsHtml) {
            if (standardPCContentsHtml != "") {
                $(".news_movie_wrap").show();
                $(".news_movie_wrap").html(standardPCContentsHtml);
            }
        });
    }

    //E 형 광고 링크 클릭시 A태그 이동 안하도록 처리
    $(".ad_wide_slide .ad_slide_nav .slide_nav_list .nav_item a").click(function(event) {
        event.preventDefault();
    });
    //페이지 호출시 첫번째 E형 광고 클릭 처리
    $(".ad_wide_slide .ad_slide_nav .slide_nav_list li:eq(0) a").trigger("click");

    //기본 리스트 타입이 "가격비교" 형태일 경우에만 검색 옵션 기본보기 최초 호출
    if (oExpansionContent.sDefaultListType == "priceCompare") {
        //2017.11.07 URL공유된 URL로 접근시 상세옵션 같이 호출하게끔 변경
        var sUrlPasted = '';
        if ($("#sUrlParameter").length > 0) {
            if ($("#sUrlParameter").val() != '') {
                sUrlPasted = 'Y';
            }
        }
        getSimpleSearchOption(sUrlPasted);

    }

    //상품 리스트 최초 호출
    getProductList(1, true);

    //상품 비교하기 관련 스크립트
    new ProductCompareFunction();

    //UI 카테고리 내부클릭 이벤트
    var sProductListLoggerStr = "";
    if ($(".dir_location .dir_list .dir_item").length > 0) {
        sProductListLoggerStr = "ca";
        $(".dir_location .dir_list .dir_item:not('.dir_home')").each(function(i) {
            if ($.trim($(this).children(".dir_link").text()) != "선택하세요") {
                sProductListLoggerStr += "_" + $.trim($(this).children(".dir_link").text());
            }
        });
    }

    try {
        _trkEventLog(sProductListLoggerStr);
    } catch (_e) {}

    //비교영역에서 마우스 휠 동작
    $("#comparisonProductList .comp_prod").mousewheel(function(event) {
        var currentScrollLeft = $(this).scrollLeft();
        var deltaY = (event.deltaY * -1) * 30;
        var nextScrollLeft = currentScrollLeft + deltaY;

        $(this).scrollLeft(nextScrollLeft);

        return false;
    });

    //이전 카테고리 보기에 데이터 추가 / 노출
    if ($.cookie("cookPrevViewCategory") == null) {
        $.cookie("cookPrevViewCategory", nUICategoryCode, { path: "/list/", domain: sProductListDomain });
    } else {
        var cookieValue = $.cookie("cookPrevViewCategory");
        var cookieValueArray = cookieValue.split("|");
        var insertCookieValue = true;
        var cookieLength = 10;

        for (var i = 0; i < cookieValueArray.length; i++) {
            if (cookieValueArray[i] == nUICategoryCode) {
                var cookieValueArrayTemp = cookieValueArray.slice(i, i + 1);
                cookieValueArray.splice(i, 1);
                cookieValueArray.unshift(cookieValueArrayTemp[0]);
                cookieValue = cookieValueArray.join("|");
                insertCookieValue = false;
            }
        }

        if (insertCookieValue == true) {
            if (cookieValueArray.length >= cookieLength) {
                cookieValueArray.splice(cookieLength - 1, 1);
            }
            cookieValueArray.unshift(nUICategoryCode);
            cookieValue = cookieValueArray.join("|");
        }

        $.cookie("cookPrevViewCategory", cookieValue, { path: "/list/", domain: sProductListDomain });

        if (cookieValueArray.length > 1) {
            $(".dir_location .dir_record").show();

            $.get("./ajax/getPrevViewCategory.ajax.php", { currentUICategory: nUICategoryCode, affiliate: sAffiliateId, loggerChannel: sLoggerChannel }, function(prevViewCategoryHtml) {
                $(".dir_location .dir_record .record_layer .list_area").html(prevViewCategoryHtml);
            });
        }
    }

    //이전 카테고리 보기 클릭시 하위 레이어 호출
    $(".dir_record .link_layer").click(function(event) {
        event.preventDefault();
        $(".dir_location .dir_record").toggleClass("record_layer_show");
    });

    //통합검색 바로가기 레이어 닫기 버튼
    $("#searchViewBtn").click(function(event) {
        event.preventDefault();
        $(".prod_search_view").css('display', 'none');
        $(".prod_search_view").addClass('closed');
    });

    $("#getPriceParameter, #getPriceParameterFilterArea").click(function() {
        var searchCheckedLength = $("#frmProductList .fil_item").length;
        var checkLengthSize = 15; //체크할 옵션 = 15 * 2(대표옵션 포함)

        //		if($(".option_nav").hasClass("option_total_show") == true) {
        //			alert('옵션 전체보기는 지원되지 않습니다.');
        //			return;
        //		}

        if (searchCheckedLength <= checkLengthSize) {
            var sParameterData = $("#frmProductList").serialize();
            var changedParam = '';
            var nowUrl = $("#sProdListUrl").val();
            var aParameter = new Array();

            //제조사
            var oCheckedMaker = $("#frmProductList input[name^='searchMaker[]']:checked");
            var sMakerParam = new Array();

            if (oCheckedMaker.length > 0) {
                oCheckedMaker.each(function() {
                    sMakerParam.push($(this).val());
                });

                var uniqueMaker = sMakerParam.filter(function(itm, i, a) {
                    return i == a.indexOf(itm);
                });

                aParameter.push('searchMaker=' + uniqueMaker.join(','));
            }

            //브랜드
            var oCheckedBrand = $("#frmProductList input[name^='searchBrand[]']:checked");
            var sBrandParam = new Array();

            if (oCheckedBrand.length > 0) {
                oCheckedBrand.each(function() {
                    sBrandParam.push($(this).val());
                });

                var uniqueBrand = sBrandParam.filter(function(itm, i, a) {
                    return i == a.indexOf(itm);
                });

                aParameter.push('searchBrand=' + uniqueBrand.join(','));
            }

            //검색옵션
            var oCheckedSearchOption = $("#frmProductList input[name^='searchAttributeValue[]']:checked");
            var sSearchOptionParam = new Array();
            var aUniqueSearchOption = new Array();

            if (oCheckedSearchOption.length > 0) {

                if ($('#bSearchOptionMultiCategoryYN').val() == 'Y') {
                    oCheckedSearchOption.each(function() {
                        var nCheckedSearchOptionMappingCode = $(this).attr('id').replace('searchAttributeValue', '');
                        sSearchOptionParam.push(nCheckedSearchOptionMappingCode);
                    });
                    $.each(sSearchOptionParam, function(i, e) {
                        if ($.inArray(e, aUniqueSearchOption) == -1) {
                            aUniqueSearchOption.push(e);
                        }
                    });
                } else {
                    oCheckedSearchOption.each(function() {
                        sSearchOptionParam.push($(this).val().split('|')[2]);
                    });

                    $.each(sSearchOptionParam, function(i, e) {
                        if ($.inArray(e, aUniqueSearchOption) == -1) {
                            aUniqueSearchOption.push(e);
                        }
                    });
                }

                aParameter.push('searchAttributeValue=' + aUniqueSearchOption.join(','));
            }

            if (aParameter.length > 0) {
                changedParam += aParameter.join('/');
            }

            if ($("#innerSearchKeyword").length > 0) {
                changedParam += "/innerSearchKeyword=" + encodeURIComponent($("#innerSearchKeyword").val());
            }

            var priceRangeMinPrice = $(".price_opt .min_price_input").val();
            priceRangeMinPrice = priceRangeMinPrice.replace(/,/g, "");
            if (priceRangeMinPrice == "") {
                priceRangeMinPrice = 0;
            }
            var priceRangeMaxPrice = $(".price_opt .max_price_input").val();
            priceRangeMaxPrice = priceRangeMaxPrice.replace(/,/g, "");
            if (priceRangeMaxPrice == "") {
                priceRangeMaxPrice = 0;
            }

            if (priceRangeMinPrice > 0) {
                changedParam += "/priceRangeMinPrice=" + priceRangeMinPrice;
            }

            if (priceRangeMaxPrice > 0) {
                changedParam += "/priceRangeMaxPrice=" + priceRangeMaxPrice;
            }

            changedParam = nowUrl + '&searchOption=' + changedParam;

            copyClip(changedParam);
        } else {
            alert('옵션 선택은 최대 15개까지 가능합니다.');
        }
    });

    //중고상품 개수 카운트 비동기 호출 (중고상품탭 가변 노출)
    if ($('#usedMarketItemTab').length) {
        $.ajax({
            type: "POST",
            url: "./ajax/getUsedMarketCount.ajax.php",
            data: {
                'cate1': oGlobalSetting.sPhysicsCate1,
                'cate2': oGlobalSetting.sPhysicsCate2,
                'cate3': oGlobalSetting.sPhysicsCate3,
                'cate4': oGlobalSetting.sPhysicsCate4
            },
            success: function(data) {
                if (parseInt(data['usedMarketCount']) == 0 || data['usedMarketCount'] === null) {
                    $('#usedMarketItemTab').hide();
                }
            },
            error: function(request, status, error) {
                //		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }

    //상품리스트 우측 이벤트 배너 다음 보기
    $.EventBannerNextPage = function() {
        var nCurrentPage = parseInt($("#bannerCurrentPages_Event").text());
        var nTotalPage = parseInt($("#bannerTotalPages_Event").text());

        nCurrentPage = nCurrentPage + 1;
        if (nCurrentPage == (nTotalPage + 1)) {
            nCurrentPage = 1;
        }

        $.eventShowCurrentPages(nCurrentPage);
    };

    $.rollingEventBanner = function() {
        $.EventBannerNextPage();
    };

    //상품리스트 우측 이벤트 배너 롤링
    $('.aside_media').on({
        mouseenter: function() {
            //	alert('cc');
            clearInterval($(this).data('timer'));
        },
        mouseleave: function() {
            //	alert('dd');
            $(this).data('timer', setInterval(function() {

                //alert('test');
                $.rollingEventBanner();
            }, 5000))
        }
    }).trigger('mouseleave');

    //상품리스트 우측 이벤트 배너 현재 페이지 보이기
    $.eventShowCurrentPages = function(nCurrentPage) {
        if (nCurrentPage == null || typeof(nCurrentPage) == 'undefined') {
            var nCurrentPage = parseInt($("#bannerCurrentPages_Event").text());
        }
        $('div[id^="bannerSections_Event"]').hide();
        $("#bannerSections_Event" + "_" + nCurrentPage).show();
        $("#bannerCurrentPages_Event").html(nCurrentPage);
    }

    //프리미엄배너 로깅
    $('.product_ad_banner .line a').click(function() {
        var nPremiumBannerProdCode = $(this).attr('data-pcode');
        $.ajax({
            type: "POST",
            url: "./ajax/logging/premiumBannerLog.ajax.php",
            data: {
                'nUICateCode': oGlobalSetting.nListCategoryCode,
                'nProdCode': nPremiumBannerProdCode
            }
        });
        _trkEventLog('15상품리스트_프리미엄배너');
    });

    //최근본 카테고리 저장
    setRecentCategory(oGlobalSetting.nGroup.toString() + oGlobalSetting.nDepth.toString() + oGlobalSetting.nCategoryCode.toString(), oGlobalSetting.memberSeq, $.cookie("danawa-loggingApplicationClient"));
});

var ProductCompareFunction = function() {
    // 비교 레이어 노출 열기/닫기
    $(document).on("click", "#comparisonToggleBtn", function() {
        $(this).toggleClass("on");
        $("#comparisonProductList").toggle();

        if ($(this).hasClass("on")) {
            $(this).children(".i_toggle").text("상품비교 접기");
        } else {
            $(this).children(".i_toggle").text("상품비교 펼치기");
        }
    });

    // 비교 레이어 전체 선택/해제	
    $(document).on("click", "#comparisonSelectAllBtn", function() {
        var oTotalProduct = $("#comparisonProductList ul.roll_list li.roll_item");
        var oSelectProduct = $("#comparisonProductList ul.roll_list li.comparison_selectItem");
        var oCheckBox = $("#comparisonProductList ul.roll_list li.roll_item .p_opt .chk_box");

        if (oTotalProduct.length == oSelectProduct.length) {
            oCheckBox.removeClass("on").children(".ico").text("선택").parent().parent().parent().removeClass("comparison_selectItem");
        } else {
            oCheckBox.addClass("on").children(".ico").text("선택해제").parent().parent().parent().addClass("comparison_selectItem");
        }
    });

    //비교 레이어 상품 개별 선택/해제
    $(document).on("click", "#comparisonProductList ul.roll_list li.roll_item .p_opt .chk_box", function() {
        var oCheckBox = $(this);

        if (oCheckBox.hasClass("on")) {
            oCheckBox.removeClass("on").children(".ico").text("선택").parent().parent().parent().removeClass("comparison_selectItem");

        } else {
            oCheckBox.addClass("on").children(".ico").text("선택해제").parent().parent().parent().addClass("comparison_selectItem");
        }
    });

    // 비교 레이어 선택 상품 삭제
    $(document).on("click", "#comparisonDeleteBtn", function() {
        var oSelectProductinfo = $(".comparison_selectItem");

        if (oSelectProductinfo.length > 0) {
            oSelectProductinfo.remove();

            //상품리스트 체크 해제
            $.each(oSelectProductinfo, function(index, oValue) {
                var oProductInfo = $(this).attr("id").split("_");
                clearCompare(this, oProductInfo[1]);
            });

            var oComparisonProduct = $("#comparisonProductList ul.roll_list li.roll_item");
            if (oComparisonProduct.length == 0) {
                $("#comparisonBox").removeClass("show");
            }
            $("#comparisonTotalCount").text(oComparisonProduct.length);
        }
    });

    //비교 레이어 상품 개별 삭제
    $(document).on("click", "#comparisonProductList ul.roll_list li.roll_item .p_opt .btn_s", function() {
        var oCheckBox = $(this);
        oCheckBox.parent().parent().remove();

        var oThisProductInfo = oCheckBox.parent().parent().attr("id").split("_");
        clearCompare(this, oThisProductInfo[1]);

        var oComparisonProduct = $("#comparisonProductList ul.roll_list li.roll_item");
        if (oComparisonProduct.length == 0) {
            $("#comparisonBox").removeClass("show");
        }
        $("#comparisonTotalCount").text(oComparisonProduct.length);
    });


    // 리스트형 비교 레이어 선택 상품 비교
    $(document).on("click", "#comparisonProductBtn", function() {
        var oSelectProduct = $("#comparisonProductList ul.roll_list li.comparison_selectItem");

        if (oSelectProduct.length < 2) {
            alert("선택한 상품이 2개 이상이어야 합니다.");
        } else {
            var productCodes = '';
            $.each(oSelectProduct, function(index, oValue) {
                var oProductInfo = $(this).attr("id").split("_");
                if (index > 0) {
                    productCodes += '|';
                }
                productCodes += oProductInfo[1];
            });

            window.open("/list/popup/compareProduct.php?productCodes=" + encodeURIComponent(productCodes), "_blank");
        }
    });

    // 선택상품담기 클릭
    $(document).on("click", "#saveSelectWishBtn", function() {
        var oSelectProduct = $("#comparisonProductList ul.roll_list li.comparison_selectItem");
        if (oSelectProduct.length < 1) {
            alert("선택한 상품이 1개 이상이어야 합니다.");
        } else {
            var productCodes = '';
            $.each(oSelectProduct, function(index, oValue) {
                var oProductInfo = $(this).attr("id").split("_");
                if (index > 0) {
                    productCodes += '|';
                }
                productCodes += oProductInfo[1];
            });

            $(this).danawaAddProductToWishList(productCodes, 'list_compare', oGlobalSetting.sPhysicsCate1, oGlobalSetting.sPhysicsCate2);
        }
    });
};

//검색 옵션 호출 후 사용 메소드
var SearchOptionFunction = function() {
    var oPriceRangeElement = $(".view_opt .price_opt .prod_price_selector");

    //검색 상품 탭이 아닐땐 기본 설정된 가격범위 셋팅
    var priceRangeMinPrice = oPriceRangeElement.find(".min_price_input").val();
    var priceRangeMaxPrice = oPriceRangeElement.find(".max_price_input").val();

    if (priceRangeMinPrice == 0) {
        priceRangeMinPrice = "";
    }
    if (priceRangeMaxPrice == 0) {
        priceRangeMaxPrice = "";
    }

    oPriceRangeElement.find(".min_price_input").val(priceRangeMinPrice);
    oPriceRangeElement.find(".max_price_input").val(priceRangeMaxPrice);

    //옵션 전체보기 버튼 노출 여부 설정
    if ($("#btnAllOptUse").val() == "true") {
        $(".option_nav .nav_header .head_opt").show();
    }

    //검색 옵션 더보기 클릭
    $(".spec_list_wrap .spec_opt_view .btn_view_more").click(function() {
        $(this).parent().parent().addClass("show_sub_item");

        var parentId = $(this).parent().parent().parent().attr('id');
        if (nMakerBrandAutoWired > 0) {
            $(this).parent().parent().find(".item_list").each(function(index) {
                var ulDisplay = $(this).css('display');

                if (ulDisplay == 'block') {
                    $(this).find('.normal_item').show();
                }
            });
        } else {
            $(this).parent().parent().find(".item_list .normal_item").show();
        }

        $(this).parent().parent().find(".btn_line").show();
        $(this).hide();
        //용어사전 영역 감춤
        $.termDicViewLayerHide();
        $(this).siblings(".btn_view_less").show();

        // 직접연관 레이어 감춤
        $.relationProductLayerHide();

        //상품 리스트 page cover 위치 조정
        productListPageCoverPosition();
        if (sAffiliateId != "") {
            iframeAutoSize();
        }
    });

    //검색 옵션 더보기 닫기
    $(".spec_list_wrap .spec_opt_view .btn_view_less").click(function() {
        $(this).parent().parent().removeClass("show_sub_item");
        var parentId = $(this).parent().parent().parent().attr('id');

        if (nMakerBrandAutoWired > 0) {
            $(this).parent().parent().find(".item_list").each(function(index) {
                var ulDisplay = $(this).css('display');

                if (ulDisplay == 'block') {
                    $(this).find('.normal_item').hide();
                }
            });
        } else {
            $(this).parent().parent().find(".item_list .normal_item").hide();
        }
        $(this).parent().parent().find(".btn_line").hide();
        $(this).hide();
        //용어사전 영역 감춤
        $.termDicViewLayerHide();
        $(this).siblings(".btn_view_more").show();

        // 직접연관 레이어 감춤
        $.relationProductLayerHide();

        //상품 리스트 page cover 위치 조정
        productListPageCoverPosition();
        if (sAffiliateId != "") {
            iframeAutoSize();
        }
    });

    var sListType = getListType();

    //기존에 선택한 내용 선택
    if ($(".spec_list_wrap #filter_" + sListType + " .filter_list .fil_item").length > 0) {
        var oRootElement = $(".spec_list_wrap #filter_" + sListType + " .fil_item");

        var aSelectedSearchOptionList = $(".spec_list_wrap #filter_" + sListType + " .filter_list .fil_item");

        var oRootElement = $(".spec_list_wrap .detail_list_wrap .spec_list");
        $.each(aSelectedSearchOptionList, function(i) {
            var sSelectedSearchOptionId = $(this).attr("id").replace("filterItem_", "");

            var sRepAttributeId = "";
            var sAttributeId = "";
            if (sSelectedSearchOptionId.match(/^searchAttributeValue/g) != null) {
                sRepAttributeId = "searchAttributeValueRep" + sSelectedSearchOptionId.replace("searchAttributeValue", "");
                sAttributeId = "searchAttributeValue" + sSelectedSearchOptionId.replace("searchAttributeValue", "");
            } else if (sSelectedSearchOptionId.match(/^searchMaker/g) != null) {
                sRepAttributeId = "searchMakerRep" + sSelectedSearchOptionId.replace("searchMaker", "");
                sAttributeId = "searchMaker" + sSelectedSearchOptionId.replace("searchMaker", "");
            } else if (sSelectedSearchOptionId.match(/^searchBrand/g) != null) {
                sRepAttributeId = "searchBrandRep" + sSelectedSearchOptionId.replace("searchBrand", "");
                sAttributeId = "searchBrand" + sSelectedSearchOptionId.replace("searchBrand", "");
            }

            oRootElement.find(".sub_item #" + sRepAttributeId).prop("checked", true);
            oRootElement.find(".sub_item #" + sRepAttributeId).parent().parent().addClass("sub_checked");
            oRootElement.find(".sub_item #" + sAttributeId).prop("checked", true);
            oRootElement.find(".sub_item #" + sAttributeId).parent().parent().addClass("sub_checked");
        });
    }

    if (sAffiliateId != "") {
        iframeAutoSize();
    }

    $("select[id^=selectMaker_], select[id^=selectBrand_]").change(function(e) {
        var sListType = getListType();
        var selectedId = e.currentTarget.id;
        var selectedId2 = selectedId + '_' + $('#' + selectedId).val();

        $('ul[id^=' + selectedId + '_' + ']').hide();
        $('#' + selectedId2).show();
        $('#' + selectedId2 + ' .normal_item').show();
    });

    $('.ad_floating_wrap').bind('click', function(e) {
        e.preventDefault();
    });
};

//상품 리스트 호출 후 사용 메소드
var ProductListFunction = function() {

    var sListType = getListType();
    var sSortMethod = getSortMethod();
    var sViewMethod = getViewMethod();

    /*
     * 이베이 파워클릭 관련 
     */
    //애드리더 존재 여부 (이베이 CBP 노출위치 관련)
    if ($("#mainAdReader").text() != '') {
        bPowerClickTopPosition = 'N';
    }

    //이베이 상품 호출 (상품리스트의 첫번째 상품의 물리카테고리 코드 전달 - 상품의 마지막 물리카테고리명 조회 위함)
    var nFistProductPhysicsCategoryCode = $("#fistProductPhysicsCategoryCode").val();
    if (typeof(nFistProductPhysicsCategoryCode) == "undefined") {
        nFistProductPhysicsCategoryCode = 0;
    }
    //애드셀러 노출시 파워클릭 하단 노출
    if ($('#bProductBizAdvertiseSellerYN').val() == 'Y') {
        bPowerClickTopPosition = 'N';
    }
    getPowerClickProductList(nFistProductPhysicsCategoryCode);

    //실행 중인 AJAX 중지
    if (oProductAdditionalAjax != null && oProductAdditionalAjax.readyState != 4) {
        oProductAdditionalAjax.abort();
    }

    if (oProductColorAjax != null && oProductColorAjax.readyState != 4) {
        oProductColorAjax.abort();
    }

    if (oFloatAdAjax != null && oFloatAdAjax.readyState != 4) {
        oFloatAdAjax.abort();
    }

    //상품 부가 정보 비동기 처리
    if (
        sListType == "priceCompare" ||
        sListType == "discontinue" ||
        sListType == "dpgZone"
    ) {
        if ($("#productCodeListForContent").val() != "") {
            var oAdditionalParam = {
                productCodeList: $("#productCodeListForContent").val(),
                sortMethod: sSortMethod,
                viewMethod: sViewMethod,
                physicsCate1: oGlobalSetting.sPhysicsCate1,
                physicsCate2: oGlobalSetting.sPhysicsCate2,
                group: oGlobalSetting.nGroup
            };

            oProductAdditionalAjax = $.ajax({
                url: "./ajax/getProductAdditionalList.ajax.php",
                data: oAdditionalParam,
                type: "POST",
                dataType: "JSON",
                success: function(data) {
                    var useCountHtml = "";
                    var useCountImageHtml = "";
                    var planHtml = "";
                    var newsHtml = "";
                    var useHtml = "";
                    var standardPCHtml = "";
                    var brandLogHtml = "";
                    var productIconHtml = "";

                    if (data != null) {

                        var defaultLoggerText = "15상품리스트";
                        if (sListType == "discontinue") {
                            defaultLoggerText += "_단종";
                        }

                        var uiCategoryCode = oGlobalSetting.nListGroup + "" + oGlobalSetting.nListDepth + "" + oGlobalSetting.nListCategoryCode;

                        $.each(data.productList, function(nProductCode, oAdditionalData) {
                            var productInfoUrl = "/info/?pcode=" + nProductCode + "&cate=" + uiCategoryCode + "#bookmark_cm_opinion";

                            useCountHtml = "";
                            useCountImageHtml = "";
                            if (oAdditionalData.productComment != null) {
                                //리스트형
                                useCountHtml = "<dl class=\"meta_item mt_comment\">";
                                if (oGlobalSetting.emProductReviewGradeYN == 'Y' && oAdditionalData.brandLog == null && oAdditionalData.productComment.starPoint != null) {
                                    useCountHtml += "<dt><span class=\"dt_behind\">상품의견</span></dt>";
                                    useCountHtml += "<dd>";
                                    useCountHtml += "<div class=\"cnt_star\">";
                                    useCountHtml += "<div class=\"point_type_list\">";
                                    useCountHtml += "<div class=\"mask\" style=\"width:" + oAdditionalData.productComment.starPoint + "%\"></div>";
                                    useCountHtml += "</div>";
                                    useCountHtml += "<div class=\"point_num\">" + oAdditionalData.productComment.starPoint + "점</div>";
                                    useCountHtml += "</div>";
                                    useCountHtml += "&nbsp;(<a href=\"" + productInfoUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_상품의견')\">";
                                    useCountHtml += "<strong>" + oAdditionalData.productComment.productCommentCount + "</strong>";
                                    useCountHtml += "</a>건)";
                                    useCountHtml += "</dd>";
                                } else if (oAdditionalData.productComment.productCommentCount != '') {
                                    useCountHtml += "<dt>상품의견</dt>";
                                    useCountHtml += "<dd>";
                                    useCountHtml += "&nbsp;<a href=\"" + productInfoUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_상품의견')\">";
                                    useCountHtml += "<strong>" + oAdditionalData.productComment.productCommentCount + "</strong>";
                                    useCountHtml += "</a>건";
                                    useCountHtml += "</dd>";
                                }
                                useCountHtml += "</dl>";

                                //이미지형 - UI카테고리 설정값에 따라 노출여부 결정
                                if (oGlobalSetting.emProductReviewGradeYN == 'Y' && oAdditionalData.productComment.starPoint != null) {
                                    useCountImageHtml = "<dt><span class=\"dt_behind\">상품의견</span></dt>";
                                    useCountImageHtml += "<dd>";
                                    useCountImageHtml += "<div class=\"cnt_star\">";
                                    useCountImageHtml += "<div class=\"point_type_image\">";
                                    useCountImageHtml += "<div class=\"mask\" style=\"width:" + oAdditionalData.productComment.starPoint + "%\"></div>";
                                    useCountImageHtml += "</div>";
                                    useCountImageHtml += "<div class=\"point_num\"><strong>" + oAdditionalData.productComment.starPoint + "</strong>점</div>";
                                    useCountImageHtml += "</div>";
                                    useCountImageHtml += "<div class=\"cnt_opinion\">";
                                    useCountImageHtml += "&nbsp;(<a href=\"" + productInfoUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_상품의견')\">";
                                    useCountImageHtml += "<strong>" + oAdditionalData.productComment.productCommentImageCount + "</strong>";
                                    useCountImageHtml += "</a>건)";
                                    useCountImageHtml += "</div>";
                                    useCountImageHtml += "</dd>";
                                } else if (oAdditionalData.productComment.productCommentImageCount != '') {
                                    useCountImageHtml = "<dt>상품의견</dt>";
                                    useCountImageHtml += "<dd><a href=\"" + productInfoUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_상품의견')\">";
                                    useCountImageHtml += "<strong>" + oAdditionalData.productComment.productCommentImageCount + "</strong>";
                                    useCountImageHtml += "</a>건</dd>";
                                }
                            }

                            planHtml = "";
                            if (oAdditionalData.plan != null) {
                                planHtml = "<dl class=\"rel_item rel_event\">";
                                planHtml += "<dt>기획전</dt>";
                                planHtml += "<dd>";
                                planHtml += "<a href=\"" + oAdditionalData.plan.linkUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                planHtml += oAdditionalData.plan.title;
                                planHtml += "</a>";
                                planHtml += "</dd>";
                                planHtml += "</dl>";
                            }

                            newsHtml = "";
                            if (oAdditionalData.news != null) {
                                newsHtml = "<dl class=\"rel_item rel_article\">";
                                newsHtml += "<dt>관련기사</dt>";
                                newsHtml += "<dd>";
                                if (oAdditionalData.news.linkUrl.indexOf("http://brand.danawa.com") > -1) {
                                    newsHtml += "<a href=\"" + oAdditionalData.news.linkUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                } else {
                                    newsHtml += "<a href=\"" + oAdditionalData.news.linkUrl + "&cate=" + uiCategoryCode + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                }
                                newsHtml += oAdditionalData.news.title;
                                newsHtml += "</a>";
                                newsHtml += "</dd>";
                                newsHtml += "</dl>";
                            }

                            useHtml = "";
                            if (oAdditionalData.use != null) {
                                useHtml = "<dl class=\"rel_item rel_review\">";
                                useHtml += "<dt>사용기</dt>";
                                useHtml += "<dd>";
                                if (oAdditionalData.use.linkUrl.indexOf("http://brand.danawa.com") > -1) {
                                    useHtml += "<a href=\"" + oAdditionalData.use.linkUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                } else {
                                    useHtml += "<a href=\"" + oAdditionalData.use.linkUrl + "&cate=" + uiCategoryCode + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                }
                                useHtml += oAdditionalData.use.title;
                                useHtml += "</a>";
                                useHtml += "</dd>";
                                useHtml += "</dl>";
                            }

                            standardPCHtml = "";
                            if (oAdditionalData.standardPC != null) {
                                standardPCHtml = "<dl class=\"rel_item rel_pc\">";
                                standardPCHtml += "<dt>조립PC</dt>";
                                standardPCHtml += "<dd>";
                                standardPCHtml += "<a href=\"" + oAdditionalData.standardPC.linkUrl + "\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_관련기사')\">";
                                standardPCHtml += oAdditionalData.standardPC.title;
                                standardPCHtml += "</a>";
                                standardPCHtml += "</dd>";
                                standardPCHtml += "</dl>";
                            }

                            brandLogHtml = "";
                            if (oAdditionalData.brandLog != null) {
                                brandLogHtml = "<dl class=\"meta_item\">";
                                brandLogHtml += "</dt>";
                                brandLogHtml += "<a href=\"http://brand.danawa.com/" + oAdditionalData.brandLog.id + "\" title=\"해당상품 브랜드로그 보러가기\" target=\"_blank\" onmousedown=\"_trkEventLog('" + defaultLoggerText + "_브랜드로그')\">브랜드로그</a>";
                                brandLogHtml += "</dt>";
                                brandLogHtml += "</dl>";
                            }


                            productIconHtml = "";
                            if (oAdditionalData.icon != null) {
                                if (typeof(oAdditionalData.icon.standardPC1) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.standardPC1 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.standardPC2) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.standardPC2 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.standardMonitor) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.standardMonitor + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.standardNotebook) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.standardNotebook + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.event) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.event + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone1) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone1 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone2) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone2 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone3) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone3 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone4) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone4 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone5) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone5 + "&nbsp;";
                                }
                                if (typeof(oAdditionalData.icon.dpgZone6) != "undefined") {
                                    productIconHtml += oAdditionalData.icon.dpgZone6 + "&nbsp;";
                                }
                            }

                            var additionalContentHtml = planHtml + newsHtml + useHtml + standardPCHtml;

                            /*
                             * 애드리더 추가컨텐츠
                             * - 특가, 사용기, 기획전, 관련기사 중 우선순위로 존재하는 컨텐츠 1개만 노출
                             * - 노출 우선순위 : 특가 > 사용기 > 기획전 > 관련기사
                             */
                            var sAdReaderAdditionalContentHtml = "";
                            if ($("#adReaderProductItem" + nProductCode).length > 0) {
                                if ($("#adReaderProductItem" + nProductCode + " .prod_main_info .prod_rel_content dl.rel_special").length == 0) {
                                    if (useHtml != "") {
                                        sAdReaderAdditionalContentHtml = useHtml;
                                    } else if (planHtml != "") {
                                        sAdReaderAdditionalContentHtml = planHtml;
                                    } else {
                                        sAdReaderAdditionalContentHtml = newsHtml;
                                    }
                                }
                            }

                            //리스트형 별점
                            $("#productItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).before(useCountHtml);
                            $("#productItem" + nProductCode).find(".prod_main_info .prod_rel_content").append(additionalContentHtml);
                            $("#productItem" + nProductCode).find(".prod_main_info .prod_rel_content").show();
                            $("#productItem" + nProductCode).find(".prod_main_info .relation_goods_unit").show();
                            $("#productItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).after(brandLogHtml);
                            //이미지형 별점
                            $("#productItem" + nProductCode).find(".prod_meta .mt_comment").append(useCountImageHtml);

                            $("#productItem" + nProductCode).find(".head_ico_wrap").append(productIconHtml);
                            $("#productItem" + nProductCode).find(".head_ico_wrap").show();

                            $("#adReaderProductItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).before(useCountHtml);
                            $("#adReaderProductItem" + nProductCode).find(".prod_main_info .prod_rel_content").append(sAdReaderAdditionalContentHtml);
                            $("#adReaderProductItem" + nProductCode).find(".prod_main_info .prod_rel_content").show();
                            $("#adReaderProductItem" + nProductCode).find(".prod_main_info .relation_goods_unit").show();
                            $("#adReaderProductItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).after(brandLogHtml);
                            $("#adReaderProductItem" + nProductCode).find(".head_ico_wrap").append(productIconHtml);
                            $("#adReaderProductItem" + nProductCode).find(".head_ico_wrap").show();

                            $("#adPointProductItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).before(useCountHtml);
                            $("#adPointProductItem" + nProductCode).find(".prod_main_info .prod_rel_content").append(additionalContentHtml);
                            $("#adPointProductItem" + nProductCode).find(".prod_main_info .prod_rel_content").show();
                            $("#adPointProductItem" + nProductCode).find(".prod_main_info .relation_goods_unit").show();
                            $("#adPointProductItem" + nProductCode).find(".prod_sub_info .prod_sub_meta .meta_item").eq(1).after(brandLogHtml);
                            $("#adPointProductItem" + nProductCode).find(".head_ico_wrap").append(productIconHtml);
                            $("#adPointProductItem" + nProductCode).find(".head_ico_wrap").show();
                        });

                        if (sAffiliateId != "") {
                            iframeAutoSize();
                        }

                        if (data.dpgZoneProductList != null) {
                            if (data.dpgZoneProductList.length > 0 && $('#dpgZoneTab').length > 0) {
                                aDpgZoneProductCodes = new Array();
                                $.each(data.dpgZoneProductList, function(index, nProductSeq) {
                                    aDpgZoneProductCodes.push(nProductSeq); //dpgZone productCode 셋팅
                                });

                                $('#dpgZoneTab').show();
                            }
                        }
                    }
                }
            });

            var productColorHtml = "";
            oProductColorAjax = $.ajax({
                url: "./ajax/getProductListColor.ajax.php",
                data: { productCodeList: $("#productCodeListForContent").val() },
                type: "POST",
                dataType: "JSON",
                success: function(data) {
                    if (data != null) {
                        $.each(data, function(nProductCode, oProductColorList) {
                            if (oProductColorList.length > 0) {
                                productColorHtml = "<div class=\"img_selector\">";
                                $.each(oProductColorList, function(colorSort, aColorList) {
                                    productColorHtml += "<a href=\"#\" class=\"sel_item\" title=\"상품 색상\" onclick=\"return false;\">";
                                    var colorSpanClass = "";
                                    if (aColorList.length > 1) {
                                        colorSpanClass = "sub_color";
                                    }
                                    $.each(aColorList, function(multiColorSort, sColor) {
                                        productColorHtml += "<span class=\"hide_indent " + colorSpanClass + "\" style=\"background-color:" + sColor + ";\">상품 색상</span>";
                                    });
                                    productColorHtml += "</a>";
                                });
                                productColorHtml += "</div>";
                            }
                            if (sViewMethod == "LIST") {
                                $("#productItem" + nProductCode + " .prod_main_info .thumb_image").append(productColorHtml);
                            } else if (sViewMethod == "IMAGE") {
                                $("#productItem" + nProductCode + " .thumb_info").append(productColorHtml);
                            }
                            $("#adReaderProductItem" + nProductCode + " .prod_main_info .thumb_image").append(productColorHtml);
                        });
                    }
                }
            });
        }

        //float 광고 호출
        //empty 이미지가 있는지 없는지에 대해 ajax로 한번 체크 한뒤 empty 이미지가 없을때만 float 광고를 노출한다.
        if ($("#floatAdLayer").length > 0 && sAffiliateId == "") {
            var limitTimeCheck = $.cookie("floatBannerLimit");

            //플로팅배너 그만보기여부 체크
            var cateCode = oGlobalSetting.nCategoryCode; //현재 카테고리
            var watchedCategory = new Array();
            var result = true;

            if (limitTimeCheck) {
                watchedCategory = limitTimeCheck.split(','); //,을 구분자로 배열로 전환

                if (watchedCategory.length > 0) {
                    for (var i in watchedCategory) {
                        if (cateCode == watchedCategory[i]) {
                            result = false;
                        }
                    }
                }
            }


            //현재 카테고리가 그만보기한 쿠키값의 배열에 속하는지 체크
            if (result) {
                var defaultSpace = 10;

                oFloatAdAjax = $.ajax({
                    url: "./ajax/getFloatAdEmptyImageCheck.ajax.php",
                    data: { adUrl: encodeURIComponent($("#floatAdIframe").attr("src")) },
                    type: "GET",
                    dataType: "JSON",
                    success: function(data) {
                        if (data == false) {
                            var nPositionDefaultSpec;
                            var rootPositionY;
                            var sViewMethod = getViewMethod();
                            var randomElement = Math.floor(Math.random() * 4); // 0~4 번째 항목

                            if (sViewMethod == 'IMAGE') {
                                nPositionDefaultSpec = $("div.main_prodlist li.prod_item:first div.prod_price_set");
                                rootPositionY = $("div.main_prodlist li.prod_item").eq(randomElement).find("div.prod_price_set"); //eq인덱스를 위해 0~4로 랜덤으로 생성된 상품항목의 상세설명 오브젝트
                            } else {
                                nPositionDefaultSpec = $("div.main_prodlist li.prod_item:first dl.prod_spec_set");
                                rootPositionY = $("div.main_prodlist li.prod_item").eq(randomElement).find("dl.prod_spec_set"); //eq인덱스를 위해 0~4로 랜덤으로 생성된 상품항목의 상세설명 오브젝트
                            }

                            var nPositionX;
                            var nPositionRandY;
                            if (nPositionDefaultSpec != null) {
                                nPositionX = nPositionDefaultSpec.width() + nPositionDefaultSpec.position().left + defaultSpace;
                            }
                            if (rootPositionY.position() != null) {
                                nPositionRandY = rootPositionY.position().top + rootPositionY.height() + defaultSpace; //상기 오브젝트의 top위치와 높이, 추가여백을 더함								
                            }

                            $("#floatAdLayer").css({
                                "left": nPositionX,
                                "top": nPositionRandY
                            });

                            $("#floatAdLayer").show();
                        }
                    }
                });
            }
        }
    }

    //가격대 검색 버튼 클릭
    $(".price_opt .btn_search").click(function(e) {
        var priceRangeMinPrice = $(".price_opt .min_price_input").val();
        priceRangeMinPrice = priceRangeMinPrice.replace(/,/g, "");
        if (priceRangeMinPrice == "") {
            priceRangeMinPrice = 0;
        }
        var priceRangeMaxPrice = $(".price_opt .max_price_input").val();
        priceRangeMaxPrice = priceRangeMaxPrice.replace(/,/g, "");
        if (priceRangeMaxPrice == "") {
            priceRangeMaxPrice = 0;
        }

        priceRangeMinPrice = parseInt(priceRangeMinPrice);
        priceRangeMaxPrice = parseInt(priceRangeMaxPrice);

        if (priceRangeMinPrice == 0 && priceRangeMaxPrice == 0) {
            $(".price_opt .min_price_input").val(oGlobalSetting.nPriceRangeMinPrice);
            $(".price_opt .max_price_input").val(oGlobalSetting.nPriceRangeMaxPrice);
            getProductList(1);
        } else if (priceRangeMinPrice > priceRangeMaxPrice && priceRangeMaxPrice > 0) {
            alert("가격 범위 최소가격은 가격 범위 최대 가격보다 적어야 합니다.");
            $(".price_opt .max_price_input").focus();
        } else {
            getProductList(1);
        }
    });

    //가격대 검색 input에서 키보드 이벤트
    $(".price_opt .min_price_input, .price_opt .max_price_input").keypress(function(e) {
        if (e.keyCode == 13) {
            e.preventDefault();
            var priceRangeMinPrice = $(".price_opt .min_price_input").val();
            priceRangeMinPrice = priceRangeMinPrice.replace(/,/g, "");
            if (priceRangeMinPrice == "") {
                priceRangeMinPrice = 0;
            }
            var priceRangeMaxPrice = $(".price_opt .max_price_input").val();
            priceRangeMaxPrice = priceRangeMaxPrice.replace(/,/g, "");
            if (priceRangeMaxPrice == "") {
                priceRangeMaxPrice = 0;
            }

            priceRangeMinPrice = parseInt(priceRangeMinPrice);
            priceRangeMaxPrice = parseInt(priceRangeMaxPrice);

            if (priceRangeMinPrice == 0 && priceRangeMaxPrice == 0) {
                $(".price_opt .min_price_input").val(oGlobalSetting.nPriceRangeMinPrice.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                $(".price_opt .max_price_input").val(oGlobalSetting.nPriceRangeMaxPrice.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                getProductList(1);
            } else if (priceRangeMinPrice > priceRangeMaxPrice && priceRangeMaxPrice > 0) {
                alert("가격 범위 최소가격은 가격 범위 최대 가격보다 적어야 합니다.");
                $(".price_opt .min_price_input").focus();
            } else {
                getProductList(1);
            }
        }
    });

    //가격대 검색 input 영역은 숫자만 입력 가능
    $(".price_opt .min_price_input, .price_opt .max_price_input").keyup(function(e) {
        if (e.keyCode != 13) {
            only_number_input($(this)[0]);
        }
    });

    //가격대 검색 focus시 가격 레이블 제거
    $(".price_opt .min_price_input, .price_opt .max_price_input").focus(function(e) {
        $(this).parent().find('.price_label').hide();
    });

    //가격대 검색 input에 focus시 콤마(,) 모두 제거
    $(".price_opt .min_price_input, .price_opt .max_price_input").focus(function() {
        $(this).val($(this).val().replace(/,/g, ""));
    });

    //가격대 검색에서 focusout 되면 천단위 콤마(,) 추가
    $(".price_opt .min_price_input, .price_opt .max_price_input").blur(function() {
        $(this).val($(this).val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });

    //상품 탭에 전체 갯수 대입
    if ($(".prod_list_tab .tab_list li.selected a.tab_link .list_num").length == 0) {
        $(".prod_list_tab .tab_list li.selected a.tab_link").append(" <strong class=\"list_num\">(" + $("#totalProductCount").val() + ")</strong>");
    } else {
        $(".prod_list_tab .tab_list li.selected a.tab_link .list_num").text("(" + $("#totalProductCount").val() + ")");
    }

    //정렬 타입 선택
    $(".prod_list_opts .order_opt .order_list .order_item a").click(function(e) {
        e.preventDefault();
        if ($(this).parent().hasClass("selected") == true) {
            return false;
        } else {
            $(this).parent().siblings(".selected").removeClass("selected");
            $(this).parent().addClass("selected");

            if ($(this).parent().text() != undefined) {
                var sSortType = $(this).parent().text();
                try {
                    _trkEventLog("15상품리스트_정렬_" + sSortType);
                } catch (_e) {}
            }

            getProductList(1);
        }
    });

    //템플릿 타입 선택
    $(".prod_list_opts .view_opt .view_type_list .type_item").click(function(e) {
        if (sListType != undefined) {
            //리스트형 탭 버튼 클릭
            if (sListType == 'LIST') {
                try {
                    _trkEventLog("15상품리스트_보기_리스트형");
                } catch (_e) {}
            }

            //이미지형 탭 버튼 클릭
            if (sListType == 'IMAGE') {
                try {
                    _trkEventLog("15상품리스트_보기_이미지형");
                } catch (_e) {}
            }

            //텍스트형 탭 버튼 클릭
            if (sListType == 'SIMPLE') {
                try {
                    _trkEventLog("15상품리스트_보기_텍스트형");
                } catch (_e) {}
            }
        }

        e.preventDefault();
        if ($(this).hasClass("selected") == true) {
            return false;
        } else {
            if ($(this).attr("data-view-method") == "SIMPLE") {
                window.open("./popup/simpleProduct/?cate=" + oGlobalSetting.nGroup + oGlobalSetting.nDepth + oGlobalSetting.nCategoryCode, "simpleProductList", "width=950,height=700,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=no");
            } else {
                $(this).siblings(".selected").removeClass("selected");
                $(this).addClass("selected");

                var sListType = getListType();

                var nCurrentPage = 1;
                if (sListType == "priceCompare") {
                    if (
                        (oExpansionContent.nPriceCompareListPackageType > 0) ||
                        (oExpansionContent.nPriceCompareListPackageType == 0)
                    ) {
                        nCurrentPage = $(".prod_num_nav .num_nav_wrap .number_wrap .now_on").text();
                    }
                } else {
                    nCurrentPage = $(".prod_num_nav .num_nav_wrap .number_wrap .now_on").text();
                }

                getProductList(nCurrentPage);
            }
        }
    });

    //리스트 갯수 선택
    $(".prod_list_opts .view_opt .view_qnt select").change(function() {
        getProductList(1);
    });

    //장터 리스트에서의 조건 선택
    $(".prod_list_opts .view_opt .view_used_filter select").change(function() {
        getProductList(1);
    });

    //가격비교, 단종 탭 이미지형 상품에 마우스 오버시 레이어 노출
    $(".main_prodlist_image .product_list:not('.other_prod_image') .prod_item").mouseenter(function() {
        $(".main_prodlist_image .product_list .prod_item .thumb_show_opt").removeClass("thumb_show_opt");
        $(this).find(".thumb_info").addClass("thumb_show_opt");
    });

    //가격비교 탭 이미지 형에서 묶음 상품 보기
    $(".main_prodlist_image .product_list:not('.other_prod_image') .main_price.prod_price_set a.opt_item_option").click(function(e) {
        e.preventDefault();
        var nProductCode = $(this).attr("data-product-code");

        $(".main_prodlist_image .product_list .prod_show_option").removeClass("prod_show_option");
        $(".main_prodlist_image .product_list .prod_item#productItem" + nProductCode).addClass("prod_show_option");

        if ($("#bundleProductLayer" + nProductCode).css("display") == "none") {
            $('.main_price.prod_price_set a.opt_item_option').removeClass('on');
            $(this).addClass('on').parents('.prod_item').addClass('prod_show_option');
            $(".main_prodlist_image").find("div[id^='bundleProductLayer']").hide();
            $("#bundleProductLayer" + nProductCode).show();
        } else {
            $(this).removeClass('on').parents('.prod_item').removeClass('prod_show_option');
            $("#bundleProductLayer" + nProductCode).hide();
            $(".main_prodlist_image .product_list .prod_item#productItem" + nProductCode).removeClass("prod_show_option");
        }
    });

    //묶음 상품 이미지 View
    $(".prod_options .option_frame .option_list .option_item .option_name_wrap").mouseenter(function() {
        $(this).addClass("opt_thumb_show");
    }).mouseleave(function() {
        $(this).removeClass("opt_thumb_show");
    });

    //묶음 상품 더보기 클릭
    $(".prod_options .option_frame .group_opt .btn_cover_group").toggle(function() {
        $(this).parents(".option_frame").find(".default_hide").show();
        $(this).text("판매조건 닫기");
    }, function() {
        $(this).parents(".option_frame").find(".default_hide").hide();
        $(this).text("판매조건 더보기");
    });

    //추가금 결제 내용 View
    $(".prod_options .option_frame .option_list .option_item .ico_layer .ico_add_pay").click(function() {
        $(".prod_options .option_frame .option_list .option_item .ico_show_layer").removeClass("ico_show_layer");
        $(this).parent().addClass("ico_show_layer");
    });

    //추가금 결제 내용 Hide
    $(".prod_options .option_frame .option_list .option_item .ico_layer .pop_add_pay .btn_pop_close").click(function() {
        $(this).parent().parent().parent().removeClass("ico_show_layer");
    });

    $("p.more_prod_pricelist a").click(function(e) {
        e.preventDefault();
        var bundleHiddenItem = $(this).parent().children("input[name='bundleHiddenItem']").val();
        var aBundleHiddenItem = bundleHiddenItem.split("|");
        var bundleExtendLength = aBundleHiddenItem.length;

        $(this).parent().parent().parent().parent().toggleClass("prod_item_all");

        if ($(this).hasClass("open")) {
            $(this).removeClass("open");
            $(this).addClass("close");
            $(this).html("다른 구성 <span><strong>닫기</strong></span>");
        } else {
            $(this).addClass("open");
            $(this).removeClass("close");
            $(this).html("다른 구성 <span><strong>" + bundleExtendLength + "</strong>개</span>");
        }

        for (var bundleKey in aBundleHiddenItem) {
            $('#productInfoDetail_' + aBundleHiddenItem[bundleKey]).toggle();
        }
    });

    // 리스트형 비교 레이어 노출
    $("input[id^='prodCompareCheck_'], .compareRecomImage, input[id^='adverProdCompareCheck_']").click(function() {
        var compareCount = $("#comparisonProductList .comp_prod ul.roll_list li.roll_item").length;
        //통합검색 바로가기 레이어 숨김처리
        $(".prod_search_view").css('display', 'none');

        if (compareCount > 9) {
            alert("한번에 선택 비교 할 수 있는 상품은 10개 입니다.");
            $(this).attr("checked", false);
        } else {
            var appendBoolean = false;

            if ($(this).hasClass("compareRecomImage") == true) {
                var sSelfValue = $(this).children("input[type='hidden']").val();
                var bHasCompsel = false; //상품비교탭에 중복상품이 있으면 true
                $("#comparisonProductList .comp_prod ul.roll_list li.roll_item").each(function() {
                    var compProdCode = $(this).attr('id').split('_')[1];
                    if (sSelfValue == compProdCode) {
                        bHasCompsel = true;
                    }
                });
                var rootElement = $(this).parents(".prod_item");
                var productPrice = rootElement.find(".prod_price_set .low_price .num").text().replace("원", "");
                var productPromotionPrice = rootElement.find(".prod_price_set .card_price .num").text();
                var discontinue = rootElement.find(".nosale_price").text();
                var imageSrc = rootElement.find(".thumb_info").children("a").children("img").attr("src");
                var productName = rootElement.find(".prod_name").children("a").text();
                var linkUrl = rootElement.find(".thumb_info").children("a").attr("href");
                var bundleName = '';

                if (bHasCompsel == false) {
                    appendBoolean = true;
                }
            } else {

                var rootParentId = $(this).parent().parent().parent().parent().parent().parent().parent().attr("id");
                var isAdverItem = false;
                var sAdverId = '';

                if (rootParentId != undefined) {
                    if (rootParentId.indexOf("adReaderProductItem") > -1) {
                        isAdverItem = true;
                        sAdverId = 'adReaderProductItem';
                    } else if (rootParentId.indexOf("adPointProductItem") > -1) {
                        isAdverItem = true;
                        sAdverId = 'adPointProductItem';
                    }
                }

                var sRepValue = $(this).parent().parent().children("input[name='compareRepValue']").val(); //대표상품코드
                var sSelfValue = $(this).parent().parent().children("input[name='compareValue']").val(); //번들(단일)상품코드
                var bHasCompsel = false; //상품비교탭에 중복상품이 있으면 true
                $("#comparisonProductList .comp_prod ul.roll_list li.roll_item").each(function() {
                    var compProdCode = $(this).attr('id').split('_')[1];
                    if (sSelfValue == compProdCode) {
                        bHasCompsel = true;
                    }
                });

                //광고상품 분기 추가
                if (isAdverItem) {
                    var rootElement = $(this).parents("#" + sAdverId + sRepValue);
                    var productPrice = rootElement.find(".price_sect").children("a").text().replace("원", "");
                    var discontinue = rootElement.find(".nosale_price").text();
                    var imageSrc = rootElement.find(".prod_main_info .thumb_image").children("a").children("img").attr("src");
                    var productName = rootElement.find(".prod_info .prod_name").children("a").text();
                    var linkUrl = rootElement.find(".prod_info .prod_name").find('a.prod_name').attr("href");
                    var bundleName = '';
                } else {
                    var rootElement = $(this).parents("#productItem" + sRepValue);
                    var productPrice = $('#productInfoDetail_' + sSelfValue).find(".price_sect").children("a").text().replace("원", "");
                    var discontinue = rootElement.find(".nosale_price").text();
                    var imageSrc = rootElement.find(".thumb_image").children("a").children("img").attr("src");
                    var productName = rootElement.find(".prod_info .prod_name a[name='productName']").text();
                    var linkUrl = rootElement.find(".prod_info .prod_name a[name='productName']").attr("href");
                    if ($.trim($('#productInfoDetail_' + sSelfValue).find(".memory_sect").text()) != '') {
                        var bundleName = "(" + $.trim($('#productInfoDetail_' + sSelfValue).find(".memory_sect").text()) + ")";
                    } else {
                        var bundleName = '';
                    }
                    //imageSrc = imageSrc.replace('shrink=130:130', 'shrink=80:80');
                }

                if ($(this).attr("checked") == "checked" && bHasCompsel == false) {
                    appendBoolean = true;
                }
            }

            if (appendBoolean == true) {
                var appendHtml = "";
                appendHtml += "<li class=\"roll_item comparison_selectItem\" id=\"comparisonProduct_" + sSelfValue + "\">";
                appendHtml += "<a href=\"" + linkUrl + "\" class=\"thmb_link\" target=\"_blank\" onmousedown=\"_trkEventLog('15상품리스트_비교_상품클릭')\">";
                appendHtml += "<img width=\"80\" height=\"80\" src=\"" + imageSrc + "\"  alt=\"" + $.trim(productName) + " " + $.trim(bundleName) + "\">";
                appendHtml += "</a>";
                appendHtml += "<div class=\"p_name\">";
                appendHtml += "<a href=\"" + linkUrl + "\" class=\"prod_tit\">" + $.trim(productName) + " " + $.trim(bundleName) + "</a>";
                appendHtml += "</div>";
                appendHtml += "<div class=\"p_price\">";
                appendHtml += "<a href=\"" + linkUrl + "\">";

                if (discontinue == '단종상품') {
                    appendHtml += discontinue;
                } else if ($.trim(productPrice) == '출시예정' || $.trim(productPrice) == '일시품절' || $.trim(productPrice) == '가격비교예정' || $.trim(productPrice) == '가격비교중지') {
                    appendHtml += "<em class=\"num_c\">" + $.trim(productPrice) + "</em>";
                } else if ($.trim(productPrice) == '') {
                    appendHtml += "<em class=\"num_c\">단종상품</em>";
                } else {
                    appendHtml += "<em class=\"num_c\">" + $.trim(productPrice) + "</em>원";
                }
                appendHtml += "</a>";
                appendHtml += "</div>";
                appendHtml += "<div class=\"p_opt\">";
                appendHtml += "<button type=\"button\" class=\"chk_box on\"><span class=\"ico\">선택해제</span></button>";
                appendHtml += "<a href=\"#\" onclick=\"return false;\" class=\"btn_s\">삭제</a>";
                appendHtml += "</div>";
                appendHtml += "</li>";

                $("#comparisonProductList .comp_prod .roll_list").append(appendHtml);
                $("#comparisonProductList .comp_prod .roll_list").css({ width: ((compareCount + 1) * 132) + "px" });

            } else {
                $("#comparisonProduct_" + sSelfValue).remove();
                clearCompare(this, sSelfValue);
            }

            $("#comparisonTotalCount").text($("#comparisonProductList ul.roll_list li.roll_item").length);

            if ($("#comparisonProductList ul.roll_list li.roll_item").length == 0) {
                $("#comparisonBox").removeClass("show");
            } else {
                $("#comparisonBox").addClass("show");
            }

        }
    });

    //기존 선택되어 있는 비교 상품에 대해 비교 버튼을 재 활성화 처리
    if ($("#comparisonProductList .comp_prod ul.roll_list li.roll_item").length > 0) {
        $("#comparisonProductList .comp_prod ul.roll_list li.roll_item").each(function(i) {
            var nProductCode = $(this).attr("id").replace("comparisonProduct_", "");

            $("input[id='prodCompareCheck_" + nProductCode + "']").attr("checked", true);
        });
    }

    //애드리더 영역에서 a태그 클릭시 로그 입력 함수 실행
    $("#mainAdReader a").mousedown(function() {
        var nSaleSeq = $(this).attr("data-sale-seq");
        if (nSaleSeq > 0) {
            fnAdvertisStatistics(nSaleSeq, 2, 2, 0);
        }
    });

    //애드포인트 영역에서 a태그 클릭시 로그 입력 함수 실행
    $("#mainAdPoint a").mousedown(function() {
        var nSaleSeq = $(this).attr("data-sale-seq");
        if (nSaleSeq > 0) {
            fnAdvertisStatistics(nSaleSeq, 2, 14, 0);
        }
    });

    //애드스타 영역에서 a태그 클릭시 로그 입력 함수 실행
    $("#mainAdStar a").mousedown(function() {
        var nSaleSeq = $(this).attr("data-sale-seq");
        if (nSaleSeq > 0) {
            fnAdvertisStatistics(nSaleSeq, 2, 1, 0);
        }
    });

    //애드셀러 영역에서 a태그 클릭시 로그 입력 함수 실행
    $("#mainAdSeller .ads_body a").mousedown(function() {
        var nSaleSeq = $(this).attr("data-sale-seq");
        if (nSaleSeq > 0) {
            fnAdvertisStatistics(nSaleSeq, 2, 16, 0);
        }
    });

    if ($(".ad_caster_list ul li").length > 1) {
        var adCasterCarousel = $(".ad_caster_list").jcarousel({
            auto: 1,
            wrap: "circular"
        }).jcarouselAutoscroll({
            interval: 3000,
            target: '+=1',
            autostart: true
        });

        $(".ad_caster_list").mouseenter(function() {
            $(".ad_caster_list").jcarouselAutoscroll("stop");
        }).mouseleave(function() {
            $(".ad_caster_list").jcarouselAutoscroll("start");
        });

        $(".btn_num_prev#prevAdCaster").on('click', function(e) {
            e.preventDefault();
            adCasterCarousel.jcarousel('scroll', '-=1');
        });
        $(".btn_num_nexy#nextAdCaster").on('click', function(e) {
            e.preventDefault();
            adCasterCarousel.jcarousel('scroll', '+=1');
        });

        //현재 페이지 체크
        adCasterCarousel.on('jcarousel:targetin', 'li', function(event, carousel) {
            var currentAdCasterPage = event.currentTarget.id.split("_")[1];
            $("#adCasterCurrentPage").html(currentAdCasterPage);
        });
    }

    if ($(".ad_focus_list ul li").length > 1) {
        var adFocusCarousel = $(".ad_focus_list").jcarousel({
            auto: 1,
            wrap: "circular"
        }).jcarouselAutoscroll({
            interval: 3000,
            target: '+=1',
            autostart: true
        });

        $(".ad_focus_list").mouseenter(function() {
            $(".ad_focus_list").jcarouselAutoscroll("stop");
        }).mouseleave(function() {
            $(".ad_focus_list").jcarouselAutoscroll("start");
        });

        $(".btn_num_prev#prevAdFocus").on('click', function(e) {
            e.preventDefault();
            adFocusCarousel.jcarousel('scroll', '-=1');
        });
        $(".btn_num_nexy#nextAdFocus").on('click', function(e) {
            e.preventDefault();
            adFocusCarousel.jcarousel('scroll', '+=1');
        });

        //현재 페이지 체크
        adFocusCarousel.on('jcarousel:targetin', 'li', function(event, carousel) {
            var currentAdFocusPage = event.currentTarget.id.split("_")[1];
            $("#adFocusCurrentPage").html(currentAdFocusPage);
        });
    }

    if (sAffiliateId != "") {
        iframeAutoSize();
    }

    //브랜드관 광고
    getAdBrandHall();

    //직접연관상품 간략설명 호출
    $(".relation_goods_unit .unit_goods_list .unit_lists .view_dic").click(function(e) {

        var _this = $(this);
        var offset = $(this).offset();
        var objOffsetTop;
        var objX = this.offsetLeft + 155;
        var objY = offset.top - $('#danawa_header').height() + 20; //고정 높이 값 (20)	

        if (this != undefined) {
            objOffsetTop = $(this).offset().top;
        } else {
            objOffsetTop = objOffsetTop;
        }

        if (typeof(this) == undefined) {
            objOffsetTop = $('.unit_goods_layer').offset().top;
        }

        var objYTemp = objY;
        //Y 좌표가 0보다 작으면 Y 좌표는 이전걸 적용 한다.
        if (objY < 0) {
            objY = objYTemp;
        }

        var nRelationProductCode = parseInt($(this).attr("data-relationcode"));
        $.ajax({
            type: "GET",
            dataType: 'html',
            url: "/list/ajax/getRelationProductDesc.ajax.php",
            data: { "nProductSeq": nRelationProductCode },
            success: function(data) {

                $("#relationProduct_pop").html(data);
                $(".unit_goods_layer").css({ "top": objY, "left": objX });
                $("#relationProduct_pop").show();
            }
        });
    });

    var oMallMinPriceListFunction = new MallMinPriceListFunction();
    var oProductImageLayerFunction = new ProductImageLayerFunction();
};

//몰별 최저가 레이어
var MallMinPriceListFunction = function() {
    $("#productListArea .product_list .prod_pricelist ul li span.more_btn_wrap .i_more")
        .add('#productListArea .main_prodlist_image span.more_btn_wrap .i_more').bind("click", function(e) {
            e.preventDefault();
            var nMinPriceProductCode = 0;
            if ($(this).closest('.main_prodlist_image').length) {
                nMinPriceProductCode = $(this).parent().parent().parent().attr("id").replace('productItem', '');
            } else {
                nMinPriceProductCode = $(this).parent().parent().parent().attr("id").split("_")[1];
            }

            if ($(this).hasClass("adIcon")) {
                var oThisLayer = $("#layer_price_more_ad_" + nMinPriceProductCode);
            } else {
                var oThisLayer = $("#layer_price_more_" + nMinPriceProductCode);
            }

            var sLoadingLayer = '<span class="product_list_cover"><img src="//img.danawa.com/totalMain/ajax-loader.gif" alt="로딩중"></span>';

            if (oThisLayer.parent().hasClass("show")) {
                oThisLayer.parent().removeClass("show");
            } else {
                $(".more_btn_wrap").removeClass("show");
                if (oThisLayer.html() == '') {
                    oThisLayer.html(sLoadingLayer);
                    $.ajax({
                        type: "GET",
                        dataType: 'html',
                        url: "/list/ajax/getMallMinPriceListLayer.ajax.php",
                        data: {
                            "productCode": nMinPriceProductCode,
                            "categoryCode": oGlobalSetting.nCategoryCode,
                            "group": oGlobalSetting.nListGroup,
                            "depth": oGlobalSetting.nListDepth,
                            "cate1": oGlobalSetting.sPhysicsCate1,
                            "cate2": oGlobalSetting.sPhysicsCate2,
                            "cate3": oGlobalSetting.sPhysicsCate3,
                            "cate4": oGlobalSetting.sPhysicsCate4
                        },
                        success: function(data) {
                            oThisLayer.html(data);
                        }
                    });
                }
                oThisLayer.parent().addClass("show");
                if ($(this).closest('.main_prodlist_image').length) {
                    try {
                        _trkEventLog("15상품리스트_이미지형_몰별최저가");
                    } catch (_e) {}
                } else {
                    try {
                        _trkEventLog("15상품리스트_몰별최저가");
                    } catch (_e) {}
                }
            }

        });

    $(document).on("click", '[id^="layer_price_more_"] .lpm_wrap', function() {
        if ($(this).closest('.main_prodlist_image').length) {
            try {
                _trkEventLog("15상품리스트_이미지형_몰별최저가_쇼핑몰 이동");
            } catch (_e) {}
        } else {
            try {
                _trkEventLog("15상품리스트_몰별최저가_쇼핑몰 이동");
            } catch (_e) {}
        }
    });

    $(document).on("click", '[id^="layer_price_more_"] .lpm_more', function() {
        if ($(this).closest('.main_prodlist_image').length) {
            try {
                _trkEventLog("15상품리스트_이미지형_몰별최저가_더보기");
            } catch (_e) {}
        } else {
            try {
                _trkEventLog("15상품리스트_몰별최저가_더보기");
            } catch (_e) {}
        }
    });

}

//상품이미지 레이어 팝업
var ProductImageLayerFunction = function() {
    /**
     * 상품 이미지 팝업레이어 섬네일 클릭
     */
    $('#productListArea ul.product_list li.prod_item .thumb_image a.btn_view_zoom, #productListArea a[id^="imageVideo_"]').click(function(e) {
        e.preventDefault();
        var nUICategoryCode = oGlobalSetting.nListGroup + "" + oGlobalSetting.nListDepth + "" + oGlobalSetting.nListCategoryCode;

        var nImageProductInfo;
        if (typeof($(this).parent().parent().parent().attr("id")) != 'undefined') {
            nImageProductInfo = $(this).parent().parent().parent().attr("id").split("Item");
        } else { //이미지형
            nImageProductInfo = $(this).parent().parent().attr("id").split("Item");
        }
        nImageProductCode = nImageProductInfo[1];
        var isAssemblyGalleryCategory = oGlobalSetting.bAssemblyGalleryCategory;

        //동영상 클릭 유무
        var sSelectType = '';
        if ($(this).attr('id') == 'imageVideo_' + nImageProductCode) {
            sSelectType = 'video';
        }

        if (aProductImageInfoList[nImageProductCode]) {
            $("#productImageLayerArea").html(aProductImageInfoList[nImageProductCode]);
            var oImageLayerEvent = new ImageLayerEvent(sSelectType);
        } else {
            $.ajax({
                type: "GET",
                dataType: 'html',
                url: "/list/ajax/getProductImageLayer.ajax.php",
                data: { "productCode": nImageProductCode, "cate": nUICategoryCode, "assemblyGalleryCategory": isAssemblyGalleryCategory },
                success: function(data) {
                    $("#productImageLayerArea").html(data);
                    aProductImageInfoList[nImageProductCode] = data;
                    var oImageLayerEvent = new ImageLayerEvent(sSelectType);
                }
            });
        }

        try {
            if (sSelectType == 'video') {
                _trkEventLog("15상품리스트_리스트형_동영상보기");
            } else {
                _trkEventLog("15상품리스트_리스트형_이미지보기");
            }
        } catch (_e) {}

    });

    //이미지형 확대보기 클릭
    $('#productListArea .main_prodlist_image .product_list .thumb_info .thumb_opt_list .imageLayerButton').click(function(e) {
        e.preventDefault();
        var nUICategoryCode = oGlobalSetting.nListGroup + "" + oGlobalSetting.nListDepth + "" + oGlobalSetting.nListCategoryCode;
        var nImageProductCode = $(this).attr("data-product-code");
        var isAssemblyGalleryCategory = oGlobalSetting.bAssemblyGalleryCategory;

        if (aProductImageInfoList[nImageProductCode]) {
            $("#productImageLayerArea").html(aProductImageInfoList[nImageProductCode]);
            var oImageLayerEvent = new ImageLayerEvent();
        } else {
            $.ajax({
                type: "GET",
                dataType: 'html',
                url: "/list/ajax/getProductImageLayer.ajax.php",
                data: { "productCode": nImageProductCode, "cate": nUICategoryCode, "assemblyGalleryCategory": isAssemblyGalleryCategory },
                success: function(data) {
                    $("#productImageLayerArea").html(data);
                    aProductImageInfoList[nImageProductCode] = data;
                    var oImageLayerEvent = new ImageLayerEvent();
                }
            });
        }
    });

    var ImageLayerEvent = function(sSelectType) {

        //이미지 변경
        var ImageLayerHandler = {
            changeImage: function(thidObj, type) {
                thidObj.parent().find('li').removeClass('on');
                thidObj.addClass('on');
                $('#expandViewLayer').children().hide();

                if (type == 'video') {
                    $('#expandMovieArea').show();
                    $('#expandViewLayer').removeClass('big_img');
                    $('#expandViewLayer').addClass('big_video');

                } else if (type == 'assembly_video') { //조립갤러리				
                    $('#expandAssemblyMovieArea').show();
                    $('#expandViewLayer').removeClass('big_img');
                    $('#expandViewLayer').addClass('big_video');

                } else if (type == 'high_def') { //고해상도			
                    $('#expandMovieArea').show();

                } else {
                    $('#expandViewLayer').removeClass('big_video');
                    $('#expandViewLayer').addClass('big_img');
                    var replaceImageUrl = thidObj.find('img').attr('src').replace('80:80', '500:500');
                    $('div.big_area img').attr('src', replaceImageUrl);
                    $('#expandImgArea').show();
                }

                var sImageFromText = thidObj.attr('data-attr');
                $('#popLayerimageFrom').text('이미지출처: ' + sImageFromText);

                if (sImageFromText == '') {
                    $('#popLayerimageFrom').hide();
                }
            }
        };

        if (sSelectType == 'video') { //동영상 아이콘 클릭시 동영상 선택된 상태로 노출
            var sSelectObj = $("ul#expandImageSlideArea li.video");
        } else {
            var sSelectObj = $("#expandImageSlideArea li:eq(0)");
        }
        ImageLayerHandler.changeImage(sSelectObj, sSelectType);

        //팝업 레이어 노출
        $("#imgExtensionArea").show();

        //팝업 레이어 숨김
        $('#closeImgExpandLayer').bind("click", function() {
            $('#imgExtensionArea').hide();
            closeYoutube();
        });

        /**
         * 상품 이미지 팝업레이어 섬네일 클릭
         */
        $('ul#expandImageSlideArea li').click(function(e) {
            e.preventDefault();

            //유투브 동영상 정지
            if ($(this).parent().find('li.on').index() != $(this).index()) {
                closeYoutube();
            }

            var sSelectObj = $(this);
            var sThisType = '';

            if (sSelectObj.hasClass('video')) {
                sThisType = 'video';
            } else if (sSelectObj.hasClass('assembly_video')) { //조립갤러리			
                sThisType = 'assembly_video';
            } else if (sSelectObj.hasClass('high_def')) { //고해상도	
                sThisType = 'high_def';
            }

            ImageLayerHandler.changeImage(sSelectObj, sThisType);
        });

        //이미지 팝업레이어 왼쪽보기
        $('#imgExtensionViewArea div.ly_cont button.btn_prev').click(function() {
            var currentIndex = $('ul#expandImageSlideArea li.on').index();
            var maxSlides = $('ul#expandImageSlideArea li').length - 1;
            var nextSlide = (currentIndex == 0) ? maxSlides : currentIndex - 1;

            $('ul#expandImageSlideArea li').eq(nextSlide).trigger('click');
        });

        //이미지 팝업레이어 오른쪽 보기
        $('#imgExtensionViewArea div.ly_cont button.btn_next').click(function() {
            var currentIndex = $('ul#expandImageSlideArea li.on').index();
            var maxSlides = $('ul#expandImageSlideArea li').length - 1;
            var nextSlide = (currentIndex == maxSlides) ? 0 : currentIndex + 1;

            $('ul#expandImageSlideArea li').eq(nextSlide).trigger('click');
        });

        //이미지 팝업버튼 클릭
        $('#imgExtensionAnchorLayer img, #imgMoreBtn, #movieMoreBtn').click(function(e) {
            var currentIndex = $('div.thumb_w ul.thumb_slide li.selected').index();
            var top = Math.max($(window).height() / 2 - $("#imgExtensionViewArea").outerHeight() / 2, 0);
            $('ul#expandImageSlideArea li').eq(currentIndex).trigger('click');
            $("#imgExtensionViewArea").css('position', 'fixed');
            $("#imgExtensionViewArea").css('top', top + "px");
            $('#imgExtensionArea').show();
        });
    };
}

//유튜브 재생 중지
function closeYoutube() {
    if ($('#expandMovieArea').length > 0) {
        $('#expandMovieArea')[0].contentWindow.postMessage('{"event":"command","func":"' + 'stopVideo' + '","args":""}', '*');
    }

    if ($('#expandAssemblyMovieArea').length > 0) {
        $('#expandAssemblyMovieArea')[0].contentWindow.postMessage('{"event":"command","func":"' + 'stopVideo' + '","args":""}', '*');
    }
}

var oSearchOptionAjax = null;
var oSearchOptionFunction = null;
var oCategoryExpainFunction = null;
var clickedSearchOptionId = ''; //검색옵션 클릭 버튼 ID를 담기위한 변수
var clickedSearchOptionAttribute = ''; //검색옵션 종류를 담기위한 변수

/**
 * 검색 옵션 AJAX 호출 [상세]
 * @param oParameterData
 * @return void
 */
function getSearchOption(sUrlPaste) {
    //상품 리스트 page cover 위치 조정
    productListPageCoverPosition();

    if (sUrlPaste != 'Y') {
        //검색 옵션 page cover 위치 조정
        searchOptionPageCoverPosition();
    }

    var sListType = getListType();

    var sAjaxUrl = "./ajax/getSearchOption.ajax.php";
    if ($("#previewType").length > 0) {
        sAjaxUrl = "./ajax/getSearchOptionPreview.ajax.php";
    }

    //실행 중인 검색 옵션 조회 AJAX 중지
    if (oSearchOptionAjax != null && oSearchOptionAjax.readyState != 4) {
        oSearchOptionAjax.abort();
    }

    var sParameterData = $("#frmProductList").serialize();
    if (sParameterData != "") {
        sParameterData += "&";
    }

    sParameterData += "categoryCode=" + oGlobalSetting.nCategoryCode;
    sParameterData += "&categoryMappingCode=" + oGlobalSetting.sCategoryMappingCode;
    sParameterData += "&lastPhysicsCategory=" + oGlobalSetting.sLastPhysicsCategory;
    sParameterData += "&physicsCate1=" + oGlobalSetting.sPhysicsCate1;
    sParameterData += "&physicsCate2=" + oGlobalSetting.sPhysicsCate2;
    sParameterData += "&physicsCate3=" + oGlobalSetting.sPhysicsCate3;
    sParameterData += "&physicsCate4=" + oGlobalSetting.sPhysicsCate4;
    sParameterData += "&makerIndicate=" + oExpansionContent.sMakerIndicate;
    sParameterData += "&brandIndicate=" + oExpansionContent.sBrandIndicate;
    sParameterData += "&makerStandardDisplay=" + oExpansionContent.sMakerStandardDisplayStatus;
    sParameterData += "&brandStandardDisplay=" + oExpansionContent.sBrandStandardDisplayStatus;
    sParameterData += "&listType=" + sListType;

    var nDiscountProductRate = 0;
    if (oExpansionContent.sDiscountProductRate != null) {
        nDiscountProductRate = oExpansionContent.sDiscountProductRate;
        sParameterData += "&nDiscountProductRate=" + nDiscountProductRate;
    }

    oSearchOptionAjax = $.ajax({
        url: sAjaxUrl,
        data: sParameterData,
        type: "POST",
        success: function(sSearchOptionHtml) {
            $(".detail_list_wrap #searchOptionAjaxImage").hide();

            if (sUrlPaste == 'Y') {
                sSearchOptionHtml = "<div id=\"extendSearchOption" + sListType + "\" style=\"display:none;\">" + sSearchOptionHtml + "</div>";
                $(".detail_list_wrap ." + sListType + "_option_wrap").append(sSearchOptionHtml);
            } else {
                //기본 노출 검색 옵션 숨김
                $(".detail_list_wrap #simpleSearchOption" + sListType).hide();

                sSearchOptionHtml = "<div id=\"extendSearchOption" + sListType + "\">" + sSearchOptionHtml + "</div>";
                $(".detail_list_wrap ." + sListType + "_option_wrap").append(sSearchOptionHtml);

                //상품 리스트 page cover 위치 조정
                productListPageCoverPosition();

            }

            //검색 옵션 page cover 숨김
            $(".search_option_cover").hide();

            oSearchOptionFunction = new SearchOptionFunction();

            $("#selectBrand, #selectMaker").change(function(e) {
                var currentId = e.currentTarget.id;
            });

            var searchUrl = $("#sUrlParameter").val();

            if (searchUrl != '') {
                var res = searchUrl.split("&");

                for (var resItem in res) {
                    var firstParameter = res[resItem].split('=');
                    var searchkey = firstParameter[0];
                    var searchValue = firstParameter[1];

                    if (searchValue != undefined) {
                        var extendedSearchValue = searchValue.split(",");

                        for (var searchIdx in extendedSearchValue) {
                            var dataAttrId = '';
                            var dataAttrRep = '';
                            var sAttributeName = '';

                            if (searchkey == 'priceRangeMaxPrice' || searchkey == 'priceRangeMinPrice' || searchkey == 'innerSearchKeyword') { //텍스트 파라미터는 value 셋팅
                                if (searchkey == 'priceRangeMaxPrice' || searchkey == 'priceRangeMinPrice') {
                                    if ($("#" + searchkey + 'Hidden').length > 0) {
                                        $('#' + searchkey + 'Hidden').val(extendedSearchValue[searchIdx]);
                                    }
                                }

                                if ($("#" + searchkey).length > 0) {
                                    $('#' + searchkey).val(extendedSearchValue[searchIdx]);
                                    //URL공유로 들어왔을때만 상세검색 레이어로 키워드 복사
                                    if (searchkey == 'innerSearchKeyword') {
                                        oAddKeywordHandler.changeFullTextToSplitText();
                                    }
                                }
                            } else { //그외 파라미터
                                var searchRepId = searchkey + "Rep" + extendedSearchValue[searchIdx]; //대표옵션
                                sAttributeName = searchkey;

                                //검색옵션 element id가 중복되어있어 상위 element 명시
                                if ($("#simpleSearchOptionpriceCompare #" + searchRepId).length > 0) {
                                    dataAttrRep = 'Rep';
                                    $('#simpleSearchOptionpriceCompare #' + searchRepId).prop("checked", true);
                                }
                                if ($("#extendSearchOptionpriceCompare #" + searchRepId).length > 0) {
                                    dataAttrRep = 'Rep';
                                    $('#extendSearchOptionpriceCompare #' + searchRepId).prop("checked", true);
                                }
                                var searchId = searchkey + extendedSearchValue[searchIdx];

                                if ($("#simpleSearchOptionpriceCompare #" + searchId).length > 0) {
                                    //									dataAttrId = searchId;
                                    $('#simpleSearchOptionpriceCompare #' + searchId).prop("checked", true);
                                }
                                if ($("#extendSearchOptionpriceCompare #" + searchId).length > 0) {
                                    dataAttrId = searchId;
                                    $('#extendSearchOptionpriceCompare #' + searchId).prop("checked", true);
                                }
                            }


                            if (dataAttrId != '') {
                                var sTitle = $("#extendSearchOptionpriceCompare #" + dataAttrId).attr("data-attribute-name");

                                var sFilterHtml = "";
                                sFilterHtml += "<li id=\"filterItem_" + searchId + "\" class=\"fil_item\">";
                                sFilterHtml += "	<span class=\"fil_name\">" + $("#" + dataAttrId).parent().text() + "</span>";
                                sFilterHtml += "	<button type=\"button\" class=\"prodbtn_del_small\" title=\"선택된 검색항목 삭제\" onclick=\"removeOptionFilter(this, '" + sAttributeName + "');\"><span>해당항목 삭제</span></button>";
                                sFilterHtml += "</li>";

                                $("#filter_" + sListType + " .filter_list").append(sFilterHtml);
                                $("#filter_" + sListType).show();
                            }
                        }
                    }
                }

                getProductList(1);
            }
        }
    });
}

/**
 * 검색 옵션 AJAX 호출 [기본]
 * @param oParameterData
 * @return void
 */
function getSimpleSearchOption(sUrlPaste) {
    var sListType = getListType();

    //가격비교 탭에서만 가격대 검색 노출
    if (sListType == "priceCompare") {
        $(".view_opt .price_opt").show();
    } else {
        //검색 옵션 page cover 위치 조정
        searchOptionPageCoverPosition();
    }

    //상품 리스트 page cover 위치 조정
    productListPageCoverPosition();

    var sAjaxUrl = "./ajax/getSearchOption.ajax.php";
    if ($("#previewType").length > 0) {
        sAjaxUrl = "./ajax/getSearchOptionPreview.ajax.php";
    }

    //실행 중인 검색 옵션 조회 AJAX 중지
    if (oSearchOptionAjax != null && oSearchOptionAjax.readyState != 4) {
        oSearchOptionAjax.abort();
    }

    var sParameterData = $("#frmProductList").serialize();
    if (sParameterData != "") {
        sParameterData += "&";
    }

    sParameterData += "mode=simple";
    sParameterData += "&categoryCode=" + oGlobalSetting.nCategoryCode;
    sParameterData += "&group=" + oGlobalSetting.nListGroup;
    sParameterData += "&categoryMappingCode=" + oGlobalSetting.sCategoryMappingCode;
    sParameterData += "&lastPhysicsCategory=" + oGlobalSetting.sLastPhysicsCategory;
    sParameterData += "&physicsCate1=" + oGlobalSetting.sPhysicsCate1;
    sParameterData += "&physicsCate2=" + oGlobalSetting.sPhysicsCate2;
    sParameterData += "&physicsCate3=" + oGlobalSetting.sPhysicsCate3;
    sParameterData += "&physicsCate4=" + oGlobalSetting.sPhysicsCate4;
    sParameterData += "&makerIndicate=" + oExpansionContent.sMakerIndicate;
    sParameterData += "&brandIndicate=" + oExpansionContent.sBrandIndicate;
    sParameterData += "&makerStandardDisplay=" + oExpansionContent.sMakerStandardDisplayStatus;
    sParameterData += "&brandStandardDisplay=" + oExpansionContent.sBrandStandardDisplayStatus;
    sParameterData += "&listType=" + sListType;

    var nDiscountProductRate = 0;
    if (oExpansionContent.sDiscountProductRate != null) {
        nDiscountProductRate = oExpansionContent.sDiscountProductRate;
        sParameterData += "&nDiscountProductRate=" + nDiscountProductRate;
    }

    $.ajax({
        url: sAjaxUrl,
        data: sParameterData,
        type: "POST",
        success: function(sSearchOptionHtml) {
            $(".detail_list_wrap #searchOptionAjaxImage").hide();

            sSearchOptionHtml = "<div id=\"simpleSearchOption" + sListType + "\">" + sSearchOptionHtml + "</div>";
            $(".detail_list_wrap ." + sListType + "_option_wrap").html(sSearchOptionHtml);

            //상품 리스트 page cover 위치 조정
            productListPageCoverPosition();

            if ($("#searchOptionTotalCount").val() > 0) {
                oSearchOptionFunction = new SearchOptionFunction();

                if (sUrlPaste == 'Y') {
                    getSearchOption('Y');
                }

                if ($('#researchGuideTextDisplayYN').val() == 'Y') {
                    $('#research_guideTxt').show();
                }
            } else {
                $(".option_nav").hide();
            }

        }
    });
}

//상세검색클릭시 제조사 브랜드 자동노출 비동기 처리
function getMakerBrand() {
    if (nMakerBrandAutoWired > 0) { //제조사, 브랜드 선택안할때 가능
        var sAjaxUrl = "./ajax/getMakerBrandOnly.ajax.php";
        var sViewType = 'simple';
        var sListType = getListType();
        var sParameterData = '';

        sParameterData += "categoryCode=" + oGlobalSetting.nCategoryCode;
        sParameterData += "&makerIndicate=" + oExpansionContent.sMakerIndicate;
        sParameterData += "&brandIndicate=" + oExpansionContent.sBrandIndicate;
        sParameterData += "&makerStandardDisplay=" + oExpansionContent.sMakerStandardDisplayStatus;
        sParameterData += "&brandStandardDisplay=" + oExpansionContent.sBrandStandardDisplayStatus;

        if ($('#innerSearchKeyword').val() != '') {
            sParameterData += "&innerSearchKeyword=" + $('#innerSearchKeyword').val();
        }

        if ($('#priceRangeMinPrice').val() != '') {
            sParameterData += "&priceRangeMinPrice=" + $('#priceRangeMinPrice').val();
        }

        if ($('#priceRangeMaxPrice').val() != '') {
            sParameterData += "&priceRangeMaxPrice=" + $('#priceRangeMaxPrice').val();
        }

        var nDiscountProductRate = 0;
        if (oExpansionContent.sDiscountProductRate != null) {
            nDiscountProductRate = oExpansionContent.sDiscountProductRate;
            sParameterData += "&nDiscountProductRate=" + nDiscountProductRate;
        }

        if ($(".option_nav").hasClass("option_total_show") == true) {
            sViewType = 'extend';
        }
        sParameterData += "&sViewType=" + sViewType;

        var checkedSearchOption = $('#' + sViewType + 'SearchOption' + sListType + ' input[name^="searchAttributeValue"]:checked');

        if (checkedSearchOption.length > 0) {
            var aCheckedSearchOption = new Array();
            checkedSearchOption.each(function(index) {
                aCheckedSearchOption.push($(this).attr('value'));
            });

            sParameterData += "&searchOption=" + aCheckedSearchOption;
        }

        var selectedMaker = $('input[name^="searchMaker"]:checked:not([name^="searchMakerRep"])');
        if (selectedMaker.length > 0) {
            var selecteMakerArray = new Array();
            selectedMaker.each(function(index) {
                selecteMakerArray.push($(this).attr('value'));
            });
            sParameterData += "&searchMaker=" + selecteMakerArray;
        }

        var selectedBrand = $('input[name^="searchBrand"]:checked:not([name^="searchBrandRep"])');
        if (selectedBrand.length > 0) {
            var selecteBrandArray = new Array();
            selectedBrand.each(function(index) {
                selecteBrandArray.push($(this).attr('value'));
            });
            sParameterData += "&searchBrand=" + selecteBrandArray;
        }

        //더보기 처리
        //		var makerExtend = $('#dlMaker_simple dd div.spec_opt_view .btn_view_more').css('display');
        var makerExtend = $('#dlMaker_' + sViewType + ' dd div.spec_opt_view .btn_view_more');
        var brandExtend = $('#dlBrand_' + sViewType + ' dd div.spec_opt_view .btn_view_more');
        var makerBtnViewMore = 'N';
        var brandBtnViewMore = 'N';

        if (makerExtend.length > 0) {
            if (makerExtend.css('display') == 'none') {
                makerBtnViewMore = 'Y'
            }
        }

        if (brandExtend.length > 0) {
            if (brandExtend.css('display') == 'none') {
                brandBtnViewMore = 'Y'
            }
        }

        oSearchOptionAjax = $.ajax({
            url: sAjaxUrl,
            data: sParameterData,
            type: "POST",
            success: function(sSearchOptionHtml) {
                if (sSearchOptionHtml != '') {
                    var responseHtml = $(sSearchOptionHtml);

                    if (nMakerBrandAutoWired == 1 || nMakerBrandAutoWired == 2) {
                        var responseMakerHtml = responseHtml.filter('#dlMaker').html();
                        var responseMakerCount = responseHtml.filter('#dlMaker').find('.normal_item').length;

                        $('#' + sViewType + 'SearchOption' + sListType + ' #dlMaker_' + sViewType).html(responseMakerHtml);

                        if (responseMakerCount == 0) {
                            $('#dlMaker_' + sViewType).hide();
                        } else {
                            $('#dlMaker_' + sViewType).show();
                        }
                    }

                    if (nMakerBrandAutoWired == 1 || nMakerBrandAutoWired == 3) {
                        var responseBrandHtml = responseHtml.filter('#dlBrand').html();
                        var responseBrandCount = responseHtml.filter('#dlBrand').find('.normal_item').length;
                        $('#' + sViewType + 'SearchOption' + sListType + ' #dlBrand_' + sViewType).html(responseBrandHtml);

                        if (responseBrandCount == 0) {
                            $('#dlBrand_' + sViewType).hide();
                        } else {
                            $('#dlBrand_' + sViewType).show();
                        }
                    }

                    //상품 리스트 page cover 위치 조정
                    productListPageCoverPosition();

                    //검색 옵션 page cover 숨김
                    $(".search_option_cover").hide();

                    //디버깅처리, 리얼 반영시 삭제 필요
                    /*var responseHitDebugHtml = responseHtml.filter('#addresDebugHit').html();
                    if($('#' + sViewType + 'SearchOption' + sListType + ' #addresDebugHit').length > 0 && responseHitDebugHtml != '') {
                    	$('#' + sViewType + 'SearchOption' + sListType + ' #addresDebugHit').html(responseHitDebugHtml);
                    }
                    var responseAscDebugHtml = responseHtml.filter('#addresDebugAsc').html();
                    if($('#' + sViewType + 'SearchOption' + sListType + ' #addresDebugAsc').length > 0 && responseAscDebugHtml != '') {
                    	$('#' + sViewType + 'SearchOption' + sListType + ' #addresDebugAsc').html(responseAscDebugHtml);
                    }*/

                    oSearchOptionFunction = new SearchOptionFunction();

                    //더보기시 더보기 처리
                    if (nMakerBrandAutoWired == 1 || nMakerBrandAutoWired == 2) {
                        if (makerBtnViewMore == 'Y') {
                            $('#dlMaker_' + sViewType + ' dd div.spec_opt_view .btn_view_more').trigger("click");
                        }
                    }

                    if (nMakerBrandAutoWired == 1 || nMakerBrandAutoWired == 3) {
                        if (brandBtnViewMore == 'Y') {
                            $('#dlBrand_' + sViewType + ' dd div.spec_opt_view .btn_view_more').trigger("click");
                        }
                    }
                }
            }
        });
    }
}

//리서치 레이어 노출
function openResearch(optionCode, cateCode1, cateCode2, optionMode, object) {
    var _this = $(object);
    var categoryParam = '';
    var cate1 = oGlobalSetting.sPhysicsCate1;
    var cate2 = oGlobalSetting.sPhysicsCate2;
    var cate3 = oGlobalSetting.sPhysicsCate3;
    var cate4 = oGlobalSetting.sPhysicsCate4;

    if (cate1 > 0) {
        categoryParam += "&cate1=" + cate1;
    }

    if (cate2 > 0) {
        categoryParam += "&cate2=" + cate2;
    }

    if (cate3 > 0) {
        categoryParam += "&cate3=" + cate3;
    }

    if (cate4 > 0) {
        categoryParam += "&cate4=" + cate4;
    }

    var oLayerGraph = _this.siblings(".layer_graph");
    var oIFrame = oLayerGraph.find(".graph_body iframe");

    var researchBaseUrl = 'http://research.danawa.com';
    if (location.host != 'prod.danawa.com') {
        researchBaseUrl = 'http://research-t.danawa.com';
    }

    if (oIFrame.attr("src") == "about:blank") {
        oIFrame.attr("src", researchBaseUrl + "/searchoption/charts/detail?attrseq=" + optionCode + "&searchmode=" + optionMode + categoryParam);
    }

    $(".spec_list_wrap .detail_list_wrap .spec_list .spec_item .graph_show").removeClass("graph_show");
    $(".spec_list_wrap .detail_list_wrap .spec_list .spec_item .item_dt .layer_graph").hide();
    _this.parent().addClass("graph_show");
    oLayerGraph.show();
}

//리서치 레이어 감춤
function closeResearch(object) {
    var _this = $(object);

    var oLayerGraph = _this.parent().parent();

    oLayerGraph.parent().removeClass("graph_show");
    oLayerGraph.hide();
}

//같은 검색 옵션 선택, 선택한 옵션 영역에 데이터 노출
function equalCheckSearchOption(object, sAttributeName, sType) {
    var _this = $(object);
    clickedSearchOptionId = $(object).attr('id');

    var bChecked = _this.is(":checked");

    var sListType = getListType();

    if (bChecked == true && $("#filter_" + sListType + " .filter_list .fil_item").length >= 300) {
        alert("검색옵션은 최대 300개까지 선택 가능 합니다.");
        _this.prop("checked", false);
        return false;
    }

    //해당 검색옵션 호출시의 갯수 처리, test1
    var sOptionValue = _this.attr("id").replace(sAttributeName + sType, "");
    clickedSearchOptionAttribute = sAttributeName + "Rep" + sOptionValue;

    //검색 옵션 호출 여부 체크, 검색 옵션 영역 처리
    if ($(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list").length > 0) {
        var oRootElement = $(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list");
        oRootElement.find(".sub_item #" + sAttributeName + "Rep" + sOptionValue).prop("checked", bChecked);
        oRootElement.find(".sub_item #" + sAttributeName + sOptionValue).prop("checked", bChecked);
        if (bChecked == true) {
            oRootElement.find(".sub_item #" + sAttributeName + "Rep" + sOptionValue).parent().parent().addClass("sub_checked");
            oRootElement.find(".sub_item #" + sAttributeName + sOptionValue).parent().parent().addClass("sub_checked");
        } else {
            oRootElement.find(".sub_item #" + sAttributeName + "Rep" + sOptionValue).parent().parent().removeClass("sub_checked");
            oRootElement.find(".sub_item #" + sAttributeName + sOptionValue).parent().parent().removeClass("sub_checked");
        }
    }

    //선택한 검색 옵션 영역에 데이터 노출
    //선택시 검색옵션 로거 출력
    var loggerNavigationText = '';
    if (bChecked == true) { //체크상태이면 데이터 추가		
        var sTitle = _this.attr("data-attribute-name");

        var sFilterHtml = "";
        sFilterHtml += "<li id=\"filterItem_" + sAttributeName + sOptionValue + "\" class=\"fil_item\">";
        sFilterHtml += "	<span class=\"fil_name\">" + _this.parent().text() + "</span>";
        sFilterHtml += "	<button type=\"button\" class=\"prodbtn_del_small\" title=\"선택된 검색항목 삭제\" onclick=\"removeOptionFilter(this, '" + sAttributeName + "');\"><span>해당항목 삭제</span></button>";
        sFilterHtml += "</li>";

        $("#filter_" + sListType + " .filter_list").append(sFilterHtml);
        //영역이 감추어져 있다면 노출 시킨다.
        if ($("#filter_" + sListType).css("display") == "none") {
            $("#filter_" + sListType).show();
        }

        var navigationLoggerTextObj = new Array();
        for (var navigationDepth in oCurrentNavigation) {
            navigationLoggerTextObj[navigationDepth] = oCurrentNavigation[navigationDepth]['name'];
        }

        loggerNavigationText = 'OptionU_' + oGlobalSetting.sListGroupName + '_' + navigationLoggerTextObj.join('_') + '_' + $.trim(sTitle) + '_' + $.trim(_this.parent().text());

        try {
            _trkEventLog(loggerNavigationText);
        } catch (_e) {}
    } else { //체크 해제 하면 데이터 삭제
        $("#filter_" + sListType + " #filterItem_" + sAttributeName + sOptionValue).remove();
        //데이터가 없으면 영역 감춤
        if ($("#filter_" + sListType + " .filter_list .fil_item").length == 0) {
            $("#filter_" + sListType).hide();
        }
    }
}

/**
 * 선택한 검색 옵션 개별 삭제
 * @param object
 * @param sAttributeName
 */
function removeOptionFilter(object, sAttributeName) {
    var _this = $(object);
    _this.parent().remove();

    var sListType = getListType();

    var sOptionValue = _this.parent().attr("id").replace("filterItem_" + sAttributeName, "");

    //검색 옵션 호출 여부 체크, 검색 옵션 영역 처리
    if ($(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list").length > 0) {
        var oRootElement = $(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list");
        oRootElement.find(".sub_item #" + sAttributeName + "Rep" + sOptionValue).prop("checked", false);
        oRootElement.find(".sub_item #" + sAttributeName + sOptionValue).prop("checked", false);

        oRootElement.find(".sub_item #" + sAttributeName + "Rep" + sOptionValue).parent().parent().removeClass("sub_checked");
        oRootElement.find(".sub_item #" + sAttributeName + sOptionValue).parent().parent().removeClass("sub_checked");
    }

    var sListType = getListType();

    //데이터가 없으면 영역 감춤
    if ($("#filter_" + sListType + " .filter_list .fil_item").length == 0) {
        $("#filter_" + sListType).hide();
    }

    getProductList(1);
}

/**
 * 선택한 검색 옵션 초기화
 * @param bCallProduct:true(상품리스트 정보 호출), false(상품리스트 정보 별도 호출)
 */
function clearSearchOptionFilter(bCallProduct) {
    var sListType = getListType();

    //검색 옵션 호출 여부 체크, 검색 옵션 영역 처리
    if ($(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list").length > 0) {
        var oRootElement = $(".spec_list_wrap .detail_list_wrap ." + sListType + "_option_wrap .spec_list");
        oRootElement.find(".sub_item input[type='checkbox']").prop("checked", false);
        oRootElement.find(".sub_checked").removeClass("sub_checked");
    }

    //가격대 검색도 초기화
    $(".price_opt input").val("");

    //영역의 모든 데이터를 삭제하고 영역 감춤
    $("#filter_" + sListType + " .filter_list").html("");
    $("#filter_" + sListType).hide();

    if (nMakerBrandAutoWired > 0) {
        getSimpleSearchOption(true);
    }

    if (bCallProduct == true) {
        getProductList(1);
    }
}

var oProductAdditionalAjax = null;
var oProductColorAjax = null;
var oFloatAdAjax = null;
var oProductListAjax = null;
var oProductListFunction = null;
var bInitialFirst = true;
/**
 * 상품 리스트 조회
 * @param page
 * @param initial : 정렬, 템플릿, 갯수 등 리스트 호출 조건 초기화 여부
 * @param scroll : 상품리스트 호출뒤 스크롤 이동여부
 * @return void
 */
function getProductList(page, initial, scroll) {
    //용어사전 영역 감춤
    $.termDicViewLayerHide();

    // 직접연관 레이어 감춤
    $.relationProductLayerHide();

    //float 광고 영역 감춤
    $("#floatAdLayer").hide();

    //초기화 처리 여부가 전달되지 않으면 초기화 시키지 않는다.
    if (typeof(initial) == "undefined") {
        initial = false;
    }

    //상품리스트 최초 호출 땐 E형 광고를 이동 하지 않는다.
    //상품리스트 최초 호출 땐 page cover를 사용하지 않는다.(=top position 버그)
    if (bInitialFirst == false) {
        //E형 광고 이동처리
        moveAdTypeE();

        //page cover 위치 조정
        productListPageCoverPosition();
        $(".product_list_cover").show();
    }
    bInitialFirst = false;

    //실행 중인 AJAX 중지
    if (oProductListAjax != null && oProductListAjax.readyState != 4) {
        oProductListAjax.abort();
    }

    var sListType = getListType();

    var nPackageLimit = 5;
    if (oExpansionContent.nPriceCompareListPackageLimit != null) {
        nPackageLimit = oExpansionContent.nPriceCompareListPackageLimit;
    }

    var sInitialPriceDisplay = 'N';
    if (oExpansionContent.nPriceCompareListPackageLimit != null) {
        sInitialPriceDisplay = oExpansionContent.sInitialPriceDisplay;
    }

    var sDiscountProductRate = 0;
    if (oExpansionContent.sDiscountProductRate != null) {
        sDiscountProductRate = oExpansionContent.sDiscountProductRate;
    }

    var sViewMethod = "";
    var sSortMethod = "";
    var nListCount = 0;
    var nListPackageType = 0;

    var sStandardCate = oGlobalSetting.bStandardCategory; //표준시리즈
    var sDpgZoneCate = oGlobalSetting.bDpgZoneCategory; //dpgZone

    //초기화 처리일 경우에만 비교레이어를 숨기고, 상품을 삭제 한다.
    if (initial == true) {
        // 비교레이어 영역 숨김
        $("#comparisonBox").removeClass("show");
        // 비교레이어 영역 숨김
        $("#comparisonProductList .comp_prod ul.roll_list li.roll_item").remove();
    } else { //초기화 처리가 아닐 경우 현재 선택된 템플릿 타입, 정렬 타입, 노출 갯수를 가져 온다.
        sViewMethod = getViewMethod();
        sSortMethod = getSortMethod();
        nListCount = getListCount();
    }

    if (sListType == "searchProduct") {
        if (sViewMethod == "") {
            sViewMethod = oExpansionContent.sSearchProductListType;
        }
        //상품리스트의 초기화
        if (initial == true) {
            nListCount = oExpansionContent.nSearchProductListCount;
        }
    } else if (sListType == "priceCompare") {
        if (sStandardCate == 'Y' || sDpgZoneCate == 'Y') {
            $(".option_nav").hide();
            $(".tab_intro").hide();
            if (sViewMethod == "") {
                sViewMethod = oExpansionContent.sPriceCompareListType;
            }
            //상품리스트의 초기화
            if (initial == true) {
                nListCount = oExpansionContent.nPriceCompareListCount;
            }
        } else {
            if (sViewMethod == "") {
                sViewMethod = oExpansionContent.sPriceCompareListType;
            }
            //상품리스트의 초기화
            if (initial == true) {
                nListCount = oExpansionContent.nPriceCompareListCount;
            }
        }
    } else if (sListType == "discontinue") {
        sSortMethod = sListType;
    }

    var sAjaxUrl = "";
    if (sListType == "searchProduct") {
        sAjaxUrl = "./ajax/getSearchProductList.ajax.php";
    } else if (sListType == "market") {
        sAjaxUrl = "./ajax/getMarketProductList.ajax.php";
    } else {
        sAjaxUrl = "./ajax/getProductList.ajax.php";
    }

    var sParameterData = $(".price_opt input").serialize();
    if ($("#frmProductList .detail_list_wrap #simpleSearchOption" + sListType + " input").serialize() != "") {
        sParameterData += "&" + $("#frmProductList .detail_list_wrap #simpleSearchOption" + sListType + " input").serialize();
    }
    if ($("#frmProductList .detail_list_wrap #extendSearchOption" + sListType + " input").serialize() != "") {
        sParameterData += "&" + $("#frmProductList .detail_list_wrap #extendSearchOption" + sListType + " input").serialize();
    }
    if ($("#frmProductList #previewType").length > 0) {
        sParameterData += "&previewType=Y&categoryUserOptions=" + $("#categoryUserOptions").val() + "&representPhysicsCategoryList=" + $("#representPhysicsCategoryList").val();
    }

    if ($(".price_opt input").serialize() == '') {
        if ($("#priceRangeMinPriceHidden").length > 0) {
            var sHiddenMinPrice = $("#priceRangeMinPriceHidden").val().replace(/,/g, "");

            if (sHiddenMinPrice > 0) {
                sParameterData += "&priceRangeMinPrice=" + sHiddenMinPrice;
            }
        }

        if ($("#priceRangeMaxPriceHidden").length > 0) {
            var sHiddenMaxPrice = $("#priceRangeMaxPriceHidden").val().replace(/,/g, "");

            if (sHiddenMaxPrice > 0) {
                sParameterData += "&priceRangeMaxPrice=" + sHiddenMaxPrice;
            }
        }
    }

    //frmProductList form에 값이 없으면 가격대는 별도로 전달
    if (sParameterData == "") {
        if (oGlobalSetting.nPriceRangeMinPrice > 0) {
            sParameterData += "priceRangeMinPrice=" + oGlobalSetting.nPriceRangeMinPrice;
        }

        if (oGlobalSetting.nPriceRangeMaxPrice > 0) {
            sParameterData += "&priceRangeMaxPrice=" + oGlobalSetting.nPriceRangeMaxPrice;
        }
    }

    //제조사,브랜드,검색옵션 이름 가져오기
    var sCheckedBrandName = "";
    var sCheckedMakerName = "";
    var sCheckedSearchOptionName = "";

    var aCheckedBrandList = $('ul.item_list_all:not([style*="none"]) input[name^="searchBrand"]:checked:not([name^="searchBrandRep"])').parent();
    aCheckedBrandList.each(function(index) {
        if (index == 0) {
            sCheckedBrandName += $.trim($(this).text());
        } else {
            sCheckedBrandName += "," + $.trim($(this).text());
        }
    });

    var aCheckedMakerList = $('ul.item_list_all:not([style*="none"]) input[name^="searchMaker"]:checked:not([name^="searchMakerRep"])').parent();
    aCheckedMakerList.each(function(index) {
        if (index == 0) {
            sCheckedMakerName += $.trim($(this).text());
        } else {
            sCheckedMakerName += "," + $.trim($(this).text());
        }
    });

    var aCheckedSearchOptionList = $('input[name^="searchAttributeValue"]:checked:not([name^="searchAttributeValueRep"])').parent();
    var aCheckedSearchOptionSelector = $('input[name^="searchAttributeValue"]:checked:not([name^="searchAttributeValueRep"])');
    var aCheckedSearchOption = new Array();
    var sCheckedSearchOption = '';
    //단일 물리인지 멀티 물리인지에 따라 분기처리
    var bMultiCategoryYN = $('#bSearchOptionMultiCategoryYN').val();
    if (bMultiCategoryYN == undefined) {
        bMultiCategoryYN = 'N';
    }

    if (bMultiCategoryYN == 'Y') {
        aCheckedSearchOptionSelector.each(function(index) {
            var sChangeCodeToName = $(this).attr('value');
            var sCheckedOptionName = $.trim($(this).parent().text());
            var aChangeCodeToName = sChangeCodeToName.split('^');
            aChangeCodeToName[2] = sCheckedOptionName;
            sChangeCodeToName = aChangeCodeToName.join('|');

            if (index == 0) {
                sCheckedSearchOption += sChangeCodeToName;
            } else {
                sCheckedSearchOption += '**' + sChangeCodeToName;
            }
            aCheckedSearchOption.push(sChangeCodeToName);
        });
    } else {
        aCheckedSearchOptionSelector.each(function(index) {
            var sChangeCodeToName = $(this).attr('value');
            var sCheckedOptionName = $.trim($(this).parent().text());
            var aChangeCodeToName = sChangeCodeToName.split('|');
            aChangeCodeToName[2] = sCheckedOptionName;
            sChangeCodeToName = aChangeCodeToName.join('|');

            if (index == 0) {
                sCheckedSearchOption += sChangeCodeToName;
            } else {
                sCheckedSearchOption += '**' + sChangeCodeToName;
            }
            aCheckedSearchOption.push(sChangeCodeToName);
        });
        aCheckedSearchOptionList.each(function(index) {
            if (index == 0) {
                sCheckedSearchOptionName += $.trim($(this).text());
            } else {
                sCheckedSearchOptionName += "," + $.trim($(this).text());
            }
        });
    }

    sParameterData += "&page=" + page;
    sParameterData += "&listCategoryCode=" + oGlobalSetting.nListCategoryCode;
    sParameterData += "&categoryCode=" + oGlobalSetting.nCategoryCode;
    sParameterData += "&physicsCate1=" + oGlobalSetting.sPhysicsCate1;
    sParameterData += "&physicsCate2=" + oGlobalSetting.sPhysicsCate2;
    sParameterData += "&physicsCate3=" + oGlobalSetting.sPhysicsCate3;
    sParameterData += "&physicsCate4=" + oGlobalSetting.sPhysicsCate4;
    sParameterData += "&viewMethod=" + sViewMethod;
    sParameterData += "&sortMethod=" + sSortMethod;
    sParameterData += "&listCount=" + nListCount;
    sParameterData += "&group=" + oGlobalSetting.nListGroup;
    sParameterData += "&depth=" + oGlobalSetting.nListDepth;
    sParameterData += "&brandName=" + sCheckedBrandName;
    sParameterData += "&makerName=" + sCheckedMakerName;
    sParameterData += "&searchOptionName=" + sCheckedSearchOption;
    sParameterData += "&sDiscountProductRate=" + sDiscountProductRate;
    sParameterData += "&sInitialPriceDisplay=" + sInitialPriceDisplay;
    sParameterData += "&sPowerLinkKeyword=" + oGlobalSetting.sPowerLinkKeyword;
    sParameterData += "&oCurrentCategoryCode=" + oCurrentCategoryCode;
    if ($("#innerSearchKeyword").length > 0) {
        sParameterData += "&innerSearchKeyword=" + encodeURIComponent($("#innerSearchKeyword").val());
    }

    if (oDetailSearchSetting.sInnerDetailSearchKeyword != '') {
        sParameterData += "&innerDetailSearchKeyword=" + encodeURIComponent(oDetailSearchSetting.sInnerDetailSearchKeyword);
    }

    //검색 제외 체크박스 선택	
    var oSearchException = $("#expt_layer .expt_list li input:checked");
    if (oSearchException.length > 0) {
        var sSearchExceptionType = '';
        var aExceptionTypeList = new Array();
        oSearchException.each(function(index) {
            aExceptionTypeList.push($(this).val());
        });
        sSearchExceptionType = aExceptionTypeList.join('|');
        sParameterData += "&searchExceptionType=" + sSearchExceptionType;
    }

    //가격 비교 탭에서 필수 조건 셋팅
    if (sListType == "priceCompare") {
        if (sViewMethod == "IMAGE") {
            sParameterData += "&listPackageType=" + oExpansionContent.nPriceCompareListPackageType;
        } else {
            sParameterData += "&listPackageType=" + oExpansionContent.nPriceCompareListPackageType;
        }
        sParameterData += "&categoryMappingCode=" + oGlobalSetting.sCategoryMappingCode;
        sParameterData += "&priceUnit=" + oExpansionContent.nPriceUnit;
        sParameterData += "&priceUnitValue=" + oExpansionContent.nPriceUnitValue;
        sParameterData += "&priceUnitClass=" + oExpansionContent.sPriceUnitClass;
        sParameterData += "&cmRecommendSort=" + oExpansionContent.sCmRecommendSort;
        sParameterData += "&cmRecommendSortDefault=" + oExpansionContent.sCmRecommendSortDefault;
        sParameterData += "&bundleImagePreview=" + oExpansionContent.sBundleImagePreview;
        sParameterData += "&nPackageLimit=" + nPackageLimit;
        sParameterData += "&nPriceUnit=" + oExpansionContent.nPriceUnit;
        sParameterData += "&bMakerDisplayYN=" + oGlobalSetting.bMakerDisplayYN;
    }
    //dpgZone 탭에서 조건 셋팅
    sParameterData += "&isDpgZoneUICategory=" + oGlobalSetting.bDpgZoneCategory;;
    if (sListType == "dpgZone") {
        sParameterData += "&aDpgZoneProductCodes=" + aDpgZoneProductCodes.join(',');
    }

    //dpgZone 상품 파라미터 설정
    if (sListType == "market") {
        if ($(".prod_list_opts .view_used_filter").length > 0) {
            sParameterData += "&buyWay=" + $(".prod_list_opts .view_used_filter .fil_order_type").val();
            sParameterData += "&status=" + $(".prod_list_opts .view_used_filter .fil_prod_status").val();
            sParameterData += "&userLevel=" + $(".prod_list_opts .view_used_filter .fil_seller_type").val();
        }
    }

    //샵다나와 컨텐츠(유투브채널, 조립갤러리) 노출 대상 카테고리
    sParameterData += "&isAssemblyGalleryCategory=" + oGlobalSetting.bAssemblyGalleryCategory;

    // 검색상품 배송비 포함 정렬
    if (sListType == "searchProduct") {
        sParameterData += "&addDelivery=" + isAddDelivery;
    }

    if (sAffiliateId != "") {
        sParameterData += "&affiliateId=" + sAffiliateId;
        sParameterData += "&loggerChannel=" + sLoggerChannel;
    }
    sParameterData += "&sProductListApi=" + sProductListApi;

    //17.11.16 키보드로 검색옵션 체크시 포커싱을 잃어버리는 접근성 문제 해결 
    var focused = $(':focus');

    //	$("div.option_nav input[type='checkbox']").prop("disabled", "disabled");	//검색결과 조회전 checkbox 비활성화
    //	$("#expt_layer .expt_list li input[type='checkbox']").prop("disabled", "disabled"); //검색 제외 checkbox 비활성화 

    oProductListAjax = $.ajax({
        url: sAjaxUrl,
        data: sParameterData,
        dataType: "HTML",
        type: "POST",
        success: function(sProductListHtml) {

            focused.focus();
            var responseHtml = $(sProductListHtml);
            var dom = $(sProductListHtml);

            $(".product_list_area #productListArea").html(sProductListHtml);
            $(".product_list_cover").hide();
            if (sStandardCate == 'Y') {
                $(".price_opt").hide();
            }

            oProductListFunction = new ProductListFunction();
            $(".tooltip_layer").addClass("tooltipShow");

            //상품리스트 이미지 Lazy Loading
            let placeholderImage;
            if (sViewMethod == "IMAGE") {
                placeholderImage = "//img.danawa.com/new/noData/img/noImg_360.gif";
            } else {
                placeholderImage = "//img.danawa.com/new/noData/img/noImg_130.gif";
            }
            lazyLoadImage(placeholderImage);

            $('.prod_item img').not('.image_lazy').error(function() {
                $(this).attr('src', placeholderImage).off('error');
            });

            // 묶음상품 마우스 오버 시 미리보기 이미지 호출
            $("DIV.over_preview").each(function() {
                $(this)
                    .mouseover(function() {
                        var imgsrc = $("SPAN.preview>IMG", this).attr("data-original");
                        $("SPAN.preview>IMG", this).attr("src", imgsrc);
                        $("SPAN.preview", this).show();
                    })
                    .mouseleave(function() {
                        $("SPAN.preview", this).hide();
                    })
            });

            //검색상품 배송비 포함 정렬
            $("#add_delivery").click(function() {
                if ($(this).prop("checked") == true) {
                    isAddDelivery = 'Y';
                } else {
                    isAddDelivery = 'N';
                }

                //신상품순 정렬시 페이징 유지
                var sSortType = getSortMethod();
                if (sSortType == 'MinPrice' || sSortType == 'MaxPrice') {
                    getProductList(1);
                } else {
                    getProductList(page);
                }
                try {
                    _trkEventLog("15상품리스트_정렬_배송비 포함 ");
                } catch (e) {}

            });

            //애드포인트,애드리더,애드스타 상품블로그 이동 로거
            $("#mainAdReader, #mainAdPoint, #mainAdStar").on("click", ".thumb_image .thumb_link", function(event) {
                try { _trkEventLog("15상품리스트_상품이미지_상품블로그 이동 "); } catch (_e) {}
            });

        },
        complete: function() {
            if (typeof(scroll) != 'undefined') {
                $(document).scrollTop($(".product_list_area").offset().top);
            }
            //일반 결과내검색, 상세검색인지 여부 초기화
            oGlobalSetting.bDetailSearchYN = 'N';
        }
    });
}

//lazyload
function lazyLoadImage(placeholderImage) {
    $('img.image_lazy').lazyload({
        threshold: 500,
        placeholder: placeholderImage,
    });
}

//페이지 이동, 이동하면 스크롤을 상품 리스트 탭 쪽으로 이동
function movePage(page) {
    //	$(window).scrollTop($(".product_list_area").offset().top);
    getProductList(page, false, true);
}

//E형 광고 이동 이벤트
function moveAdTypeE() {
    var adTypeELength = $(".ad_wide_slide .ad_slide_nav .slide_nav_list li").length;

    var oElement = $(".ad_wide_slide .ad_slide_nav .slide_nav_list");
    var adDataIndex = oElement.find("li").index(oElement.find("li.selected"));
    adDataIndex = adDataIndex + 1;
    if (adDataIndex == adTypeELength) {
        adDataIndex = 0;
    }
    oElement.find("li").eq(adDataIndex).find("a").trigger("click");
}

//E형 광고 클릭 이벤트
function changeAdTypeE(adUrl, elementSeq, adType) {
    var oElement = $(".ad_wide_slide .ad_slide_nav .slide_nav_list");
    oElement.find("li.selected").removeClass("selected");
    oElement.find("li").eq(elementSeq).addClass("selected");

    var oATypeEOriginal = $("#adTypeE_Original"); //E형 광고 영역
    var nAdType = parseInt(adType); //광고 타입
    var oAdProductVideo = $("#adType_product_video"); //상품형,동영상형 광고

    if (nAdType > 1) { //2:상품, 3:동영상
        var oAdElement = $("#adType_" + nAdType + "_" + elementSeq);
        var sAdTypeEHtml = oAdElement.html();
        oATypeEOriginal.addClass("top_slide_area"); //상품, 동영상형에만 추가
        oATypeEOriginal.html(sAdTypeEHtml);
        if (sAdTypeEHtml != null) {
            oAdProductVideo.show(); //광고 노출
        }
        //E형광고 동영상레이어 띄우기
        $(".ad_wide_slide .ad_slide_area .video_template .btn_vtop").click(function() {
            popupVideoLayer(oATypeEOriginal);
        });

        //E형광고 동영상정지,레이어 닫기
        $(".ad_wide_slide .ad_slide_area .v_layer_wrap .contents_wrap .btn_vlayer_close").click(function() {
            closeVideoLayer('ifrmVideoSlide_' + elementSeq);
        });

        if (nAdType == 2) {
            var aAdProductCodes = oAdElement.attr("data-adproductcodes").split(",");
            //내부클릭 이벤트 발생
            if (hasETypeTrigger == 'Y') {
                setTimeout("getDisplayEvent(" + aAdProductCodes[0] + ",'Y')", 200);
                setTimeout("getDisplayEvent(" + aAdProductCodes[1] + ",'Y')", 400);
                hasETypeTrigger = 'N';
            }
        } else if (nAdType == 3) {
            var sAdAuthor = oElement.find("li").eq(elementSeq).find(".nav_link").text();
            //내부클릭 이벤트 발생
            try {
                if (hasETypeTrigger == 'Y') {
                    setTimeout('_trkEventLog("상품리스트_E형동영상형_' + sAdAuthor + '_노출")', 200);
                    hasETypeTrigger = 'N';
                }
            } catch (_e) {}
        }

    } else { //0:이미지, 1:SWF		
        hasETypeTrigger = 'N';
        oAdProductVideo.hide(); //광고 노출 숨기기
        oATypeEOriginal.removeClass("top_slide_area");

        //확장자 추출
        var nLength = adUrl.length;
        var nLastIndexCount = adUrl.lastIndexOf(".");

        var sExt = "";
        if (nLastIndexCount == -1) {
            sExt = "html";
        } else {
            sExt = adUrl.substring(nLastIndexCount + 1, nLength); //확장자 추출 (. 포함)
            sExt = $.trim(sExt.toLowerCase());
        }

        var sAdTypeEHtml = "";
        $(".ad_wide_slide .ad_slide_area .banner_list").html("");
        //확장자가 swf로 끝나면 플래쉬 삽입 스크립트 실행
        if (sExt == "swf") {
            var oSwfParam = {
                "wmode": "opaque"
            };
            swfobject.embedSWF(adUrl, "adTypeELayer", "700", "142", "6", "", "", oSwfParam);
            //swf 이외의 확장자는 iframe 입력
        } else {
            sAdTypeEHtml = "<div class=\"banner_list\" id=\"adTypeELayer\"><iframe src=\"" + adUrl + "\" title=\"광고 영역\" width=\"700\" height=\"142\" frameborder=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" id=\"adTypeE\"></iframe></div>";
            oATypeEOriginal.html(sAdTypeEHtml);
            $(".ad_wide_slide .ad_slide_area .banner_list").css("visibility", "visible");
        }
    }

}

function getDisplayEvent(pcode, hasETypeTrigger) {
    try {
        if (hasETypeTrigger == 'Y') { //트리거에 의한 내부클릭이벤트(노출) 여부 
            _trkEventLog("상품리스트_E형상품형_" + pcode + "_" + oGlobalSetting.sPhysicsCate1 + "_노출");
        } else {
            _trkEventLog("상품리스트_E형상품형_" + pcode + "_" + oGlobalSetting.sPhysicsCate1);
        }
    } catch (_e) {}
}

//현재 listType 조회
function getListType() {
    var sListType = "";
    if ($(".tab_list .tab_item.selected .tab_link").hasClass("tab_compare") == true) { //가격비교 탭
        sListType = "priceCompare";
    } else if ($(".tab_list .tab_item.selected .tab_link").hasClass("tab_search") == true) { //검색상품 탭
        sListType = "searchProduct";
    } else if ($(".tab_list .tab_item.selected .tab_link").hasClass("tab_used") == true) { //중고 장터 탭
        sListType = "market";
    } else if ($(".tab_list .tab_item.selected .tab_link").hasClass("tab_done") == true) { //단종 상품 탭
        sListType = "discontinue";
    } else if ($(".tab_list .tab_item.selected .tab_link").hasClass("tab_dpgz") == true) { //dpg zone
        sListType = "dpgZone";
    }

    return sListType;
}

//선택한 정렬 조건 가져 오기
function getSortMethod() {
    var sSortMethod = "";
    if ($(".prod_list_opts .order_opt .order_list").length > 0) {
        sSortMethod = $(".prod_list_opts .order_opt .order_list .selected").attr("data-sort-method");
    }
    return sSortMethod;
}

//선택한 리스트 개수 가져 오기
function getListCount() {

    var nListCount = 0;
    if ($(".prod_list_opts .view_opt .view_qnt").length > 0) {
        nListCount = $(".prod_list_opts .view_opt .view_qnt .qnt_selector").val();
    }
    return nListCount;
}

//선택한 템플릿 가져 오기
function getViewMethod() {
    var sViewMethod = "";
    if ($(".prod_list_opts .view_opt .view_type_list").length > 0) {
        sViewMethod = $(".prod_list_opts .view_opt .view_type_list .selected").attr("data-view-method");
    }

    if ($("#sViewTypeMarket").length > 0) {
        sViewMethod = $("#sViewTypeMarket").val();
    }

    return sViewMethod;
}

//상품리스트 본문 영역 페이지 커버 위치 조정
function productListPageCoverPosition() {
    var nProductListAreaHeight = $(".product_list_area").height();
    if (nProductListAreaHeight < 376) {
        nProductListAreaHeight = 376;
    }

    $(".product_list_cover").css({
        width: $(".product_list_area").width(),
        height: nProductListAreaHeight,
        top: $(".product_list_area").position().top,
        left: $(".product_list_area").position().left
    });
}

//상품리스트 본문 영역 페이지 커버 위치 조정
function searchOptionPageCoverPosition() {
    var nSearchOptionAreaHeight = $(".detail_list_wrap").height();
    if (nSearchOptionAreaHeight == 0) {
        $(".detail_list_wrap #searchOptionAjaxImage").show();
    } else {
        if (nSearchOptionAreaHeight < 35) {
            nSearchOptionAreaHeight = 34;
            $(".search_option_cover div").css({
                top: "0"
            });
        } else {
            $(".search_option_cover div").css({
                top: "40%"
            });
        }

        $(".search_option_cover").css({
            width: $(".detail_list_wrap").width(),
            height: nSearchOptionAreaHeight,
            top: $(".detail_list_wrap").position().top,
            left: $(".detail_list_wrap").position().left
        });

        $(".search_option_cover").show();
    }
}

//하단 영역 브랜드관 광고 호출
function getAdBrandHall() {
    $.ajax({
        url: "./ajax/getAdBrandHall.ajax.php",
        data: { "cate1": oGlobalSetting.sPhysicsCate1, "cate2": oGlobalSetting.sPhysicsCate2 },
        type: "POST",
        cache: false,
        dataType: "Html",
        success: function(data) {
            $("#brandHallArea").html(data);
            oAdBrandHallFunction = new AbBrandHallFucntion();
        },
        error: function(xhr, status, error) {}
    });
}

//브랜드관 광고 스크립트 객체
var AbBrandHallFucntion = function() {
    var placeholderImage = "//img.danawa.com/new/noData/img/noImg_130.gif";
    lazyLoadImage(placeholderImage);

    var oAdBrandHall = $("#brandHallArea");

    //동영상 레이어 띄우기
    oAdBrandHall.find(".video_template .btn_btop").click(function() {
        popupVideoLayer(oAdBrandHall);
    });

    //동영상 레이어 닫기
    oAdBrandHall.find(".v_layer_wrap .contents_wrap .btn_vlayer_close").click(function() {
        closeVideoLayer('ifrmVideoSlide_brandHall');
    });

    //배너 클릭시 스크롤 위치 광고 영역으로 이동
    $("body #adBrandHallBanner").click(function() {
        $(document).scrollTop($(".brand_section_area").offset().top);
    });

    $("#brandHallArea").click(function() {
        try {
            _trkEventLog("상품리스트_브랜드관_" + oGlobalSetting.sPhysicsCate1 + '_' + oGlobalSetting.sPhysicsCate2);
        } catch (_e) {}
    });
}

//동영상 광고 레이어 팝업 띄우기
function popupVideoLayer(oVideoArea) {
    oVideoArea.find(".v_layer_wrap").addClass("show");
    $("#danawa_container").css("z-index", "10"); //GNB 영역 노출 조정
}

//동영상 광고 레이어 닫기, 동영상 정지
function closeVideoLayer(sIfrmId) {
    //동영상 정지
    var oDivVideoSlide = $("#" + sIfrmId);
    var oVideoSlideIframe = oDivVideoSlide.find("iframe")[0].contentWindow;
    oVideoSlideIframe.postMessage('{"event":"command","func":"pauseVideo","args":""}', '*');

    $(".v_layer_wrap").removeClass("show"); //레이어 닫기
    $("#danawa_container").css("z-index", "0"); //GNB 영역 노출 조정
}

// VR 팝업창 띄우기 
function prodImageViewVR(pcode, nCurrentCate1, nCurrentCate2, nCurrentCate3) {
    var url = "http://prod.danawa.com/info/popup/prodImageViewerVR.php?prod_c=" + pcode + "&cate1=" + nCurrentCate1 + "&cate2=" + nCurrentCate2 + "&cate3=" + nCurrentCate3;
    var opt = "width=990, height=750, scrollbars=no";

    window.open(url, "prodImage", opt);
}

// 플러스 상품 / 스페셜 상품 통계 데이터 삽입
function fnAdvertisStatistics(nSaleSeq, actionType, adverServiceSeq, sort) {
    $("#ifrmAdvertisStatistics_" + nSaleSeq).attr("src", "");

    var sAdvertiseStatisticsiframeUrl = oGlobalSetting.sBizAdverRootUrl + "/statistics/advertiseStatisticsActionApi.php?adverServiceSeq=";
    sAdvertiseStatisticsiframeUrl += adverServiceSeq;
    sAdvertiseStatisticsiframeUrl += "&actionType=" + actionType + "&advertiseSeq=" + nSaleSeq + "&sort=" + sort;

    $("#ifrmAdvertisStatistics_" + nSaleSeq).attr("src", sAdvertiseStatisticsiframeUrl);
}

//숫자만 입력 받는 함수
function only_number_input(inputTag) {
    var sRegex = /[^0-9]/g;
    if (sRegex.test(inputTag.value)) {
        inputTag.value = inputTag.value.replace(sRegex, '');
        inputTag.focus();
    }
}

//몰별 최저가 레이어 보기
function showMallMinPriceLayer(nProductCode, mallMinPrice) {

    var mallMinPrices = $(mallMinPrice).parent().siblings('.comp_mall_layer');
    var liCount = $("dd.mall_dd_" + nProductCode).children().length;

    mallMinPrices.parent().addClass('comp_layer_show');
    mallMinPrices.css("display", "block");

    var Param = {
        "productCode": nProductCode
    };

    if (liCount > 0) {
        mallMinPrices.css("display", "block");
    } else {

        $.ajax({
            type: "GET",
            data: Param,
            dataType: "html",
            cache: false,
            url: "./ajax/getMallMinPriceLayer.ajax.php",
            success: function(data) {
                mallMinPrices.html(data);
                bMallMinPriceCallback = true;
            }
        });
    }
}

//몰별 최저가 레이어 닫기
function closeMallMinPriceLayer(elems) {
    var oMallMinPriceLayer = $(elems).parent().siblings('.comp_mall_layer');
    oMallMinPriceLayer.css("display", "none");
    oMallMinPriceLayer.parent().removeClass('comp_layer_show');
}

//비교 레이어 삭제
function clearCompare(object, value) {
    $("input[id='prodCompareCheck_" + value + "']").attr('checked', false);
    $("input[id='adverProdCompareCheck_" + value + "']").attr('checked', false);
    $("#comparisonProduct_" + value).remove();

    $("input[name='prodCompareCheckBox']").each(function() {
        $(this).prop("checked", false);
    });
    //$("#comparisonProductList .comp_prod ul.roll_list li.roll_item").remove();
    if ($("#comparisonProductList ul.roll_list li.roll_item").length == 0) {
        if (!$(".prod_search_view").hasClass('closed')) {
            $(".prod_search_view").css('display', 'block');
        }
    }

    //$("#comparisonTotalCount").text($("#comparisonProductList ul.roll_list li.roll_item").length);	
}

//float 광고 닫기
function floatAdClose() {
    $("#floatAdLayer").hide();
}

//float 광고 닫기
function floatAdStop() {
    $("#floatAdLayer").hide();
    var cateCode = oGlobalSetting.nCategoryCode;
    var categoryList = $.cookie("floatBannerLimit");

    if (!categoryList) {
        setCookie("floatBannerLimit", cateCode, 1);
    } else {
        setCookie("floatBannerLimit", (categoryList + ',' + cateCode), 1);
    }
}

//외부 제휴 상품리스트에서 iframe의 높이 값을 제어하기 위한 함수
function iframeAutoSize() {
    var iframeAutoSizeSrc = sAffiliateIframeResizeUrl;
    iframeAutoSizeSrc += "?nWidth=" + $("#danawa_container").width();
    iframeAutoSizeSrc += "&nHeight=" + $("#danawa_container").height();

    $("#hiddenIFrame").attr("src", iframeAutoSizeSrc);
}

/*
 * 이베이 상품 노출
 * oPowerClickDisplayHtml : 프론트에 노출될 html
 * 최초 1번 호출 이후에 호출하지 않음
 */
function getPowerClickProductList(nFistProductPhysicsCategoryCode) {
    if (oPowerClickDisplayHtml == '') {
        $.ajax({
            type: 'POST',
            dataType: 'html',
            url: "./ajax/getPowerClickProductList.ajax.php",
            data: {
                "UICaregoryCode": oGlobalSetting.nCategoryCode,
                "group": oGlobalSetting.nGroup,
                "depth": oGlobalSetting.nDepth,
                "powerClickKeyword": oGlobalSetting.sPowerClickKeyword,
                "firstProductPhysicsCategoryCode": nFistProductPhysicsCategoryCode,
                "adultCategory": oExpansionContent.sAdultCheck,
                "adult": oGlobalSetting.isAdult
            },
            beforeSend: function() {
                try {
                    _trkEventLog(" PC_파워클릭_상품리스트_호출");
                } catch (_e) {}
            },
            success: function(data) {
                bPowerClickInit = true;
                oPowerClickDisplayHtml = data;


                if (bPowerClickTopPosition == 'Y') {
                    $("#ebayPowerClickTopArea").html(data); //상단노출
                } else {
                    $("#ebayPowerClickBottomArea").html(data); //하단노출
                }
                $oPowerClickFunction = new PowerClickFunction();

                if (data != '') {
                    try {
                        _trkEventLog(" PC_파워클릭_상품리스트_노출");
                    } catch (_e) {}
                }
            },
            error: function() {}
        });

    } else {
        bPowerClickInit = false;

        if (bPowerClickTopPosition == 'Y') { //상단노출
            $("#ebayPowerClickTopArea").html(oPowerClickDisplayHtml);
        } else { //하단노출
            $("#ebayPowerClickBottomArea").html(oPowerClickDisplayHtml);
        }
        $oPowerClickFunction = new PowerClickFunction();
    }
}

/*
 * 이베이 측 통계를 위해 단순 url 호출(이베이측 요청 - 테스트페이지에서는 호출이 일어나면 안됨!)
 */
function powerClickAnalysis(adUrl) {
    if (location.href.indexOf("-local") > -1 || location.href.indexOf("-t") > -1) {} else {
        var imgSrc = new Image();
        imgSrc.src = adUrl;
    }
}

/*
 * 이베이 파워클릭 프론트 노출 후 실행 객체 
 */
var PowerClickFunction = function() {

    //이베이측 노출통계 - 최초 1번만
    if (bPowerClickInit == true) {
        $('[id^="ebayPowerClickList"]').each(function(i) {
            var sAdUrl = $(this).attr("data-display");
            powerClickAnalysis(sAdUrl);
        });
    }

    //내부클릭이벤트 & 이베이측 클릭통계
    $('[id^="ebayPowerClickList"] a').click(function(e) {
        try {
            _trkEventLog("PC_파워클릭_상품리스트_이베이상품클릭");
        } catch (_e) {}

        var sAdUrl = $(this).parent().closest("li").attr("data-click");
        powerClickAnalysis(sAdUrl);
    });

    //이베이 파워클릭이 상단에 노출될 경우 float 배너 위치 조정
    if ($("#ebayPowerClickTopArea").text() != '' && $("#floatAdLayer").length > 0) {
        var nFloatLayerY = $("#floatAdLayer").position().top + 277;
        $("#floatAdLayer").css({
            "top": nFloatLayerY
        });
    }
}

//상품리스트 우측 이벤트 배너
var TogetherEventBannerFunction = function() {

    var oMovingBtn = $("#eventBanner button.btn_page");

    //페이지 이동 버튼 (이전/다음)
    oMovingBtn.click(function() {
        var sThisId = $(this).attr("id");
        var sThisTarget = $.getTarget(sThisId);
        var nCurrentPage = parseInt($("#bannerCurrentPages_" + sThisTarget).text());
        var nTotalPage = parseInt($("#bannerTotalPages_" + sThisTarget).text());

        if (sThisId == ('prevBtns_' + sThisTarget)) { //이전
            nCurrentPage = nCurrentPage - 1;
            if (nCurrentPage == 0) {
                nCurrentPage = nTotalPage;
            }

        } else if (sThisId == ('nextBtns_' + sThisTarget)) { //다음

            nCurrentPage = nCurrentPage + 1;
            if (nCurrentPage == (nTotalPage + 1)) {
                nCurrentPage = 1;
            }

        }

        $.showCurrentPages(sThisTarget, nCurrentPage);
    });

    //현재 페이지 보이기
    $.showCurrentPages = function(sThisTarget, nCurrentPage) {
        if (nCurrentPage == null || typeof(nCurrentPage) == 'undefined') {
            var nCurrentPage = parseInt($("#bannerCurrentPages_" + sThisTarget).text());
        }
        $('div[id^="bannerSections_' + sThisTarget + '"]').hide();
        $("#bannerSections_" + sThisTarget + "_" + nCurrentPage).show();
        $("#bannerCurrentPages_" + sThisTarget).html(nCurrentPage);
    }

    //클릭 영역 타켓값
    $.getTarget = function($sThisId) {
        var aThisIdInfo = $sThisId.split("_");
        var sThisTarget = aThisIdInfo[1];
        var sTargetSeq = aThisIdInfo[2];

        if (sTargetSeq != '' && typeof(sTargetSeq) != 'undefined') {
            sThisTarget += "_" + sTargetSeq;
        }

        return sThisTarget;
    }
}

/**
 * 팝업 오픈 공용 함수
 */
function WindowOpen(url, name, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable) {
    toolbar_str = toolbar ? 'yes' : 'no';
    menubar_str = menubar ? 'yes' : 'no';
    statusbar_str = statusbar ? 'yes' : 'no';
    scrollbar_str = scrollbar ? 'yes' : 'no';
    resizable_str = resizable ? 'yes' : 'no';
    window.open(url, name, 'left=' + left + ',top=' + top + ',width=' + width + ',height=' + height + ',toolbar=' + toolbar_str + ',menubar=' + menubar_str + ',status=' + statusbar_str + ',scrollbars=' + scrollbar_str + ',resizable=' + resizable_str);
}

function rightBannerPopupOpen(url, width, height) {
    WindowOpen(url, 'RightBannerPopup', 100, 100, width, height, false, false, false, true, false);
}

function copyClip(ctext) {
    // IE 일때
    if (window.clipboardData) {
        window.clipboardData.setData("Text", ctext);
        alert("지금 보시는 정보 창의 주소가 복사되었습니다\n다른 곳에 붙여넣기 하여 이용하실 수 있습니다.");
    } else {
        prompt("Ctrl+C를 눌러 클립보드로 복사하세요", ctext);
    }
}

//실행중인 모든 Ajax 중단
function abortAll() {
    //실행 중인 AJAX 중단
    if (oProductAdditionalAjax != null && oProductAdditionalAjax.readyState != 4) {
        oProductAdditionalAjax.abort();
    }

    //실행 중인 AJAX 중단
    if (oProductColorAjax != null && oProductColorAjax.readyState != 4) {
        oProductColorAjax.abort();
    }

    //실행중인 float 광고 호출 중단
    if (oFloatAdAjax != null && oFloatAdAjax.readyState != 4) {
        oFloatAdAjax.abort();
    }

    //실행 중인 검색 옵션 조회 AJAX 중단
    if (oSearchOptionAjax != null && oSearchOptionAjax.readyState != 4) {
        oSearchOptionAjax.abort();
    }

    //실행 중인 AJAX 중단
    if (oProductListAjax != null && oProductListAjax.readyState != 4) {
        oProductListAjax.abort();
    }
}

function setCookie(name, value, expires) {
    $.cookie(name, value, { "expires": expires, "path": '/' });
}

function getCategoryExplain(sUrl) {
    var nCategoryCode = getUrlParam('cate', sUrl);
    $('div.depth_explain_layer').hide();

    oCategoryExpainFunction = $.ajax({
        type: 'GET',
        dataType: 'html',
        url: './ajax/getCategoryExplain.ajax.php',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            'nCategoryCode': nCategoryCode,
        },
        success: function(html) {
            if (html != '') {
                $('div.depth_explain_layer').html(html);
                $('div.depth_explain_layer').show();
            }
        }
    });
}

function setCategoryExplainDefault() {
    if (oCategoryExpainFunction != null) {
        oCategoryExpainFunction.abort();
    }

    $('div.depth_explain_layer').hide();
    $('div.depth_explain_layer').find('strong.title').html('');
    $('div.depth_explain_layer').find('div.cont').html('');
}

function getUrlParam(name, url) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(url);
    if (results == null) {
        return null;
    } else {
        return decodeURI(results[1]) || 0;
    }
}

function setRecentCategory(nCateC, nMemberSeq, sBrowserCode) {
    var baseUrl = '//prod.danawa.com';
    if (location.href.indexOf('local') >= 0 || location.href.indexOf('-t') >= 0) {
        baseUrl = '//prod-t.danawa.com';
    }

    $.ajax({
        url: baseUrl + "/info/recentProduct/saveRecentCategory.ajax.php",
        data: {
            'nCateC': nCateC,
            'nMemberSeq': nMemberSeq,
            'sBrowserCode': sBrowserCode,
            'sDeviceType': 'pc'
        },
        type: "POST",
        success: function(data) {},
        error: function() {}
    });
}

//상세검색
var oAddKeywordHandler = {
    //기존 결과내검색창에서 입력된 텍스트를 상세검색 레이어 추가/제외 키워드로 이동
    changeFullTextToSplitText: function() {
        if (oDetailSearchSetting.sInnerDetailSearchKeyword == '') {
            //이전 검색 결과 초기화 (누적을 원하면 이부분 삭제)
            //초기화하지 않으면 상세검색 버튼 클릭시에는 누적되지 않게 변경처리해야함 (중복 누적)
            oDetailSearchSetting.sExcludeKeyword = '';
            oDetailSearchSetting.sIncludeKeyword = '';

            var sInnerSearchKeyword = $('#innerSearchKeyword').val();
            var aInnerSearchKeyword = sInnerSearchKeyword.split(/(?=\s\-)/g);

            var nExcludeCnt = 0;
            $.each(aInnerSearchKeyword, function(key, val) {
                if (val.substr(0, 2) == ' -') {
                    if (val.substr(2).length > 0 && nExcludeCnt < 10) {
                        if (oDetailSearchSetting.sExcludeKeyword == '') {
                            oDetailSearchSetting.sExcludeKeyword = val.substr(2);
                        } else {
                            oDetailSearchSetting.sExcludeKeyword += '|' + val.substr(2);
                        }
                        nExcludeCnt++;
                    }
                } else {
                    if (key == 0 && val.substr(0, 1) == '-') {
                        if (val.substr(1).length > 0 && nExcludeCnt < 10) {
                            if (oDetailSearchSetting.sExcludeKeyword == '') {
                                oDetailSearchSetting.sExcludeKeyword = val.substr(1);
                            } else {
                                oDetailSearchSetting.sExcludeKeyword += '|' + val.substr(1);
                            }
                            nExcludeCnt++;
                        }
                    } else {
                        //결과내검색 입력창에서 넘어오는건 띄어쓰기로 오기때문에 하나의 키워드로 인식
                        oDetailSearchSetting.sIncludeKeyword = val;
                    }
                }
            });

            if (oDetailSearchSetting.sIncludeKeyword != '') {
                $('#search_include').val(oDetailSearchSetting.sIncludeKeyword + ', ');
            } else {
                $('#search_include').val(oDetailSearchSetting.sIncludeKeyword);
            }
            $('#search_exclude').val(oDetailSearchSetting.sExcludeKeyword.replace(/\|/gi, ', '));

            var sIncludeKeywordResult = this.getIncludeKeywordList(' ');
            var sExcludeKeywordResult = this.getExcludeKeywordList(' -');

            var sReplaceText = $.trim(sIncludeKeywordResult + sExcludeKeywordResult);
            $('#detailSearchTotalTxt').text(sReplaceText);
        } else {

        }
    },
    setDetailSearchParameter: function() {
        var sSearchParameter = '';
        var sIncludeKeyword = $('#search_include').val();
        var aIncludeKeyword = sIncludeKeyword.split(/\,(\s)?/gi);
        $.each(aIncludeKeyword, function(idx, item) {
            if ($.trim(item) != '') {
                if (idx == 0) {
                    sIncludeKeyword = $.trim(item);
                } else {
                    sIncludeKeyword += '|' + $.trim(item);
                }
            }
        });
        //		sIncludeKeyword = sIncludeKeyword.replace(/\,?(\s)/gi, '|');
        var sExcludeKeyword = $('#search_exclude').val();
        var aExcludeKeyword = sExcludeKeyword.split(/\,(\s)?/gi);
        $.each(aExcludeKeyword, function(idx, item) {
            if ($.trim(item) != '') {
                if (idx == 0) {
                    sExcludeKeyword = '!' + $.trim(item);
                } else {
                    sExcludeKeyword += '|!' + $.trim(item);
                }
            }
        });
        //		sExcludeKeyword = sExcludeKeyword.replace(/\,?(\s)/gi, '|');
        oDetailSearchSetting.sInnerDetailSearchKeyword = sIncludeKeyword;
        if (sIncludeKeyword.length > 0) {
            oDetailSearchSetting.sInnerDetailSearchKeyword += '|' + sExcludeKeyword;
        } else {
            oDetailSearchSetting.sInnerDetailSearchKeyword = sExcludeKeyword;
        }
    },
    getDisplayKeyword: function() {
        var sResult = '';
        sResult = $("#detailSearchTotalTxt").text();
        if (typeof(sResult) == 'undefined') {
            sResult = '';
        }
        return sResult;
    },
    getIncludeKeywordList: function(joinText) { //결과내 검색 기능 키워드 가져오기
        var sResult = '';
        var sSearchInclude = '';
        var aKeywordList = new Array();
        var aIncludeKeywordList = new Array();

        sSearchInclude = $("#search_include").val();

        if (sSearchInclude != '' && typeof(sSearchInclude) != "undefined") {
            aIncludeKeywordList = sSearchInclude.split(',');
        }

        if (aIncludeKeywordList.length > 0) {
            $.each(aIncludeKeywordList, function(index, value) {
                var sTrimValue = $.trim(value);
                if (sTrimValue != '') {
                    aKeywordList.push(sTrimValue);
                }
            });

            if (joinText != '') {
                sResult = aKeywordList.join(joinText);
                //				if(joinText != '|') {						
                //					sResult = joinText + sResult;
                //				}
            }
        }

        return $.trim(sResult);
    },
    getExcludeKeywordList: function(joinText) { //키워드 제외 기능 키워드 가져오기
        var sResult = '';
        var sSearchExclude = '';
        var aKeywordList = new Array();
        var aExcludeKeywordList = new Array();

        sSearchExclude = $("#search_exclude").val();

        if (sSearchExclude != '' && typeof(sSearchExclude) != "undefined") {
            aExcludeKeywordList = sSearchExclude.split(',');
        }

        nExlcludeKwdLength = 0;
        if (aExcludeKeywordList.length > 0) {
            $.each(aExcludeKeywordList, function(index, value) {
                var sTrimValue = $.trim(value);
                if (sTrimValue != '') {
                    if (nExlcludeKwdLength < 10) { //제외 키워드 10개 제한
                        aKeywordList.push(sTrimValue);
                    }
                    nExlcludeKwdLength++;
                }
            });

            if (joinText != '') {
                sResult = aKeywordList.join(joinText);
                if (joinText != '|') {
                    sResult = joinText + sResult;
                }
            }
        } else {
            nExlcludeKwdLength = 0;
        }
        return sResult;
    }
};

var oDetailSearchLayerLogger = {
    search: function(searchKeyword) {
        try {
            _trkEventLog("15상품리스트_검색이용_" + oCurrentNavigation[1]['name'] + "_" + oCurrentNavigation[2]['name'] + "_" + searchKeyword);
        } catch (e) {}
    },

    open: function() {
        try {
            _trkEventLog("15상품리스트_상세검색");
        } catch (e) {}
    },

    close: function() {
        try {
            _trkEventLog("15상품리스트_상세검색_검색닫기");
        } catch (e) {}
    },

    clear: function() {
        try {
            _trkEventLog("15상품리스트_상세검색_초기화");
        } catch (e) {}
    },

    focusInnerSearchKeyword: function() {
        try {
            _trkEventLog("15상품리스트_리스트내검색");
        } catch (e) {}
    },

    focusSearchInclude: function() {
        try {
            _trkEventLog("15상품리스트_상세검색_리스트내검색");
        } catch (e) {}
    },

    focusSearchExclude: function() {
        try {
            _trkEventLog("15상품리스트_상세검색_키워드제외");
        } catch (e) {}
    }
}