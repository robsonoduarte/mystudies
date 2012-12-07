

// hide themes > show add theme
$(function() {
	$("#addTheme", $('.menu'))
		.click(function() {
			$('#themes')
				.fadeOut(
					1000,
					function() {
						$('#addTheme').fadeIn(100);
					}
				);
		});
});



// submit form add theme > hide add theme > show themes
$(function() {
	$("#btnAddTheme").click(function() {
		$.post( "backlog",
				$('#formAddTheme').serialize(),
				function(data){
					$("#themes").html(data);
					$('#addTheme').fadeOut(
							1000,
							function() {
								$('#themes').fadeIn(100);
							}
						);
					},
				'html'

			);
		});
});


// submit form view theme
$(function() {
	$("h3 > a").click(function() {
		$(this).parents('form').submit();
	});
});



