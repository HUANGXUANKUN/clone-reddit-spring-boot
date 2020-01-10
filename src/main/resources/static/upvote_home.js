GET: $(document).ready(
		function() {
			// GET REQUEST
			$("#upvote").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : window.location.href + "/upvote",
					success : function(result) {
						if (result.status == "success") {
							$('#votecount').empty();
							$("#votecount").html(result.data);
							console.log("Success! VoteCount of current link increases to: ", result.data);
						} else {
							$("#votecount").html("<strong>Error</strong>");

							console.log("Fail: ", result.data);
						}
					},
					error : function(e) {
						$("#votecount").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})