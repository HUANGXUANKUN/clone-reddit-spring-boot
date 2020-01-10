GET: $(document).ready(
		function() {
			// GET REQUEST
			$("#downvote").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : window.location.href + "/downvote",
					success : function(result) {
						if (result.status == "success") {
							const newCount = Number($('#votecount').html()) - 1;
							$('#votecount').empty();
							$("#votecount").html(newCount);
							console.log("Success! VoteCount of current link decreases to: ", newCount);
						} else {
							console.log("Fail: ", result.data);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}
		})