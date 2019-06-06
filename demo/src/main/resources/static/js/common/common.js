/*******************************************************************************
 * HTML Layout, Menu, Model
 ******************************************************************************/
$(function() {
	// ヘルプボタンクリック
	$(".header-question").on('click', function(e) {
		$(".user-detail").toggle();
	});

	// ヘルプボタン内をクリックしても非表示にしない
	$("#headerQuestion").on('click', function(e) {
		e.stopPropagation();
	});

	// 画面内クリック時ヘルプボタン内が表示されていたら非表示にする
	$(document).on('click', function(e) {
		if (document.getElementById("question").style.display == "block") {
			$(".user-detail").toggle();
		}
	});

	// アコーディオン
	$(".js-accordion-trigger").click(function() {
		$(this).next().slideToggle("fast");
		$(this).toggleClass("accordion-arrow-up");
	});

	// アンカーリンク
	$('a[href^="#"]').click(function() {
		var href = $(this).attr("href");
		var hash = href == "#" || href == "" ? 'html' : href;
		scrollToFixed(hash);
		return false;
	});

	// fixed content
	var fch = $(".js-fixed-content").outerHeight();
	function scrollToFixed(hash) {
		var target = $(hash);
		var position = target.offset().top - (fch);
		$('body,html').stop().animate({
			scrollTop : position
		}, 500);
	}
	var nav = $(".js-fixed-content");
	var navTop = nav.offset().top;
	$(window).on('load scroll', function() {
		if ($(window).scrollTop() >= navTop) {
			nav.addClass("fixed");
			$("body").css("margin-top", fch + 10 + "px");
		} else {
			nav.removeClass("fixed");
			$("body").css("margin-top", "0px");
		}
	});

	// アンカーリンクの現在位置にcurrent付与
	var set = (fch) + 4;
	var contentTop = new Array;
	var current = -1;
	$('.content').each(function(i) {
		contentTop[i] = $(this).offset().top;
	});
	$(window).on('load scroll', function() {
		scrollPosition = $(window).scrollTop();
		for (var i = contentTop.length - 1; i >= 0; i--) {
			if ($(window).scrollTop() > contentTop[i] - set) {
				changeBox(i);
				break;
			}
		}
		;
	});
	function changeBox(secNum) {
		if (secNum != current) {
			current = secNum;
			secNum2 = secNum + 1;
			$('.anchor-link__item').removeClass('current');
			$('.anchor-link__item:nth-child(' + secNum2 + ')').addClass(
					'current');
		}
	}
	;

	// モーダル
	$(document).on('click', '.js-modal-open', function() {
		$("body").append('<div class="js-modal-overlay"></div>');
		$("body").addClass("modal-open");
		$(".js-modal-overlay").fadeIn();
		$(this).next(".js-modal").fadeIn();
	});
	$(document).on('click', '.js-modal-close', function() {
		$("body").removeClass("modal-open");
		$(".js-modal").fadeOut();
		$(".js-modal-overlay").fadeOut(function() {
			$(".js-modal-overlay").remove();
		});
	});
});
/*******************************************************************************
 * Combine JS Loader
 ******************************************************************************/
function scriptLoader(path, callback) {
	var script = document.createElement('script');
	script.type = "text/javascript";
	script.async = true;
	script.src = path;
	script.onload = function() {
		if (typeof (callback) == "function") {
			callback();
		}
	}
	try {
		var scriptOne = document.getElementsByTagName('script')[0];
		scriptOne.parentNode.insertBefore(script, scriptOne);
	} catch (e) {
		document.getElementsByTagName("head")[0].appendChild(script);
	}
}
/*******************************************************************************
 * Get time
 ******************************************************************************/
function getTestTime() {
	var today = new Date();
	var time = today.getHours() + ":" + today.getMinutes() + ":"
			+ today.getSeconds();
	return time;
}
// TODO need to remove - File upload CSS Changes.
$(document).ready(function() {
	$('.custom-file-input input[type="file"]').change(function(e) {
		$(this).siblings('input[type="text"]').val(e.target.files[0].name);
	});
});
/**
 * Enable or Disable Div
 */
function ShowHide(divId) {
	if (document.getElementById(divId).style.display == 'none') {
		document.getElementById(divId).style.display = 'block';
	} else {
		document.getElementById(divId).style.display = 'none';
	}
}

function mail() {
	var dataSet;
	var target = document.getElementById("mail");
	jQuery
			.ajax({
				type : "POST",
				contentType : "application/json",
				url : contextPath + "/mailBody",
				data : dataSet,
				dataType : 'json',
				timeout : 1000000,
				success : function(data) {
					console.log(data);
					target.href = "mailto:FQI_ANALYTICS_helpdesk@mail.nissan.co.jp?subject=問い合わせ確認&amp;body="
							+ data;
				},
				error : function(e) {
					if (e.responseJSON == undefined) {
						window.location.reload();
					} else {
						console.log("aaaa");
					}
				}
			});
	/*
	 * var objOutlook = new ActiveObject('Outlook.Application'); var
	 * objNameSpace = objOutlook.GetNameSpace('MAPI'); var Item =
	 * objOutlook.CreateItem(0); Item.Display(); Item.To =
	 * "FQI_ANALYTICS_helpdesk@mail.nissan.co.jp"; Item.Subject = "testSubject"
	 * var Msg = ""; for(var i = 1; i <=30; i++){ Msg = Msg + i + "testtext"; }
	 * Item.Body = Msg; Item.GetInspector.WindowState = 2;
	 */
}
