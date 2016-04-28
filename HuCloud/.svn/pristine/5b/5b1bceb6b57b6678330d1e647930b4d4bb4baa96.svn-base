var isAbsolute = false;
var isTitle = false;
$(document).keydown(function(e) {
	
	var code = e.keychar;
	if(code == undefined) {
		code = e.which;
	}
	
	// F2 == 113
	if(code == 113) {
		if($(".tooltip").text() != "") {
			
			$(".tooltip").css({
				"opacity" : "1"
			});
			
			isAbsolute = !isAbsolute;
			if(isAbsolute) {
				$(".tooltip").fadeIn();
			}
			else {
				$(".tooltip").fadeOut();
			}
		}
	}
	
	// F8 == 119
	if(code == 119) {
		$(".bottom").toggle();
	}
	
});

$(document).ready(function() {
	$(".tooltip").hide();
	$(".tip").mouseover(function(e) {
		isTitle = false;
		if(isAbsolute) { return; }
		
		$(".tooltip").css({
			"top": parseInt(e.pageY) + "px",
			"left" : parseInt(e.pageX) + "px",
			"opacity" : "0.5"
		});
		
		var tip = $(this).attr("data-tip");
		if(tip == undefined || tip == "") {
			
			tip = $(this).attr("data-tip-str");
			
			if(tip == undefined || tip == "") {
				isTitle = true;
				tip = $(this).attr("title");
				$(".tooltip").css({
					"top": (parseInt(e.pageY) + 5) + "px",
					"left" : (parseInt(e.pageX) + 5) + "px"
				});
			}
			
			$(".tooltip").text(tip);
		}
		else {
			$(".tooltip").html(tip);
		}
		
		$(".tooltip").show();
	});
	
	$(".tip").mousemove(function(e) {
		
		if(isAbsolute) { return; }
		if(isTitle) {
			$(".tooltip").css({
				"top": (parseInt(e.pageY) + 5) + "px",
				"left" : (parseInt(e.pageX) + 5) + "px"
			});
		}
		else {
			$(".tooltip").css({
				"top": parseInt(e.pageY) + "px",
				"left" : parseInt(e.pageX) + "px"
			});
		}
		
		
	});
	
	$(".tip").mouseout(function() {
		
		if(isAbsolute) { return; }
		
		$(".tooltip").hide();
		$(".tooltip").text("");
		$(".tooltip").html("");
	});
	
});
