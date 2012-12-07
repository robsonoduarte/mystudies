
$(function() {
	$("#newSprint", $('.menu'))
		.click(function() {
			$.post( "sprints",
					{ action: "NEWSPRINT"},
					function(data){
						$("#newSprint").html(data);
						$('#stories')
							.fadeOut(
							1000,
							function() {
								$('#newSprint').fadeIn(100);
							});
						},
					'html'
				);
		});
});


$(function() {
	$("#btnCreateSprint").live("click", function() {
		$.post( "sprints",
				$(this).parents('form').serialize(),
				function(data){
					$("#stories").html(data);
					$('#newSprint')
						.fadeOut(
							1000,
							function() {
								$('#stories').fadeIn(100);
							});
				},
				'html'
		);
	});

});