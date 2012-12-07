

// hide themes > show add theme
$(function() {
	$("#addStory", $('.menu'))
		.click(function() {
			$('#stories')
				.fadeOut(
					1000,
					function() {
						$('#addStory').fadeIn(100);
					}
				);
		});
});



// submit form add theme > hide add theme > show themes
$(function() {
	$("#btnAddStory").click(function() {
		$.post( "theme",
				$('#formAddStory').serialize(),
				function(data){
					$("#stories").html(data);
					$('#addStory').fadeOut(
							1000,
							function() {
								$('#stories').fadeIn(100);
							}
						);
					},
				'html'

			);
		});
});



$(function() {
	$(".main-content")
	.on('click', 'li > a.addSprint', function(){
		$.post(
				"theme",
				$(this).parents('form').serialize(),
				function(data){
					$("#stories").html(data);
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



