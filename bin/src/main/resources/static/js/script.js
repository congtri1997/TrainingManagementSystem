var flg = true;
$("#custom-logo-hide").hide();
$("#custom-x-navigation-minimize").click(function() {
	if (flg) {
		$("#custom-logo-show").hide();
		$("#custom-logo-hide").show();
		flg = false;
	} else {
		$("#custom-logo-show").show();
		$("#custom-logo-hide").hide();
		flg = true;
	}

});