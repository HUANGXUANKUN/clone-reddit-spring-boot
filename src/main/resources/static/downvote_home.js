GET: $(document).ready(
		function() {
			// GET REQUEST
			$("#upvote").click(function(event) {
				var id = this.getAttribute("data-id");
				event.preventDefault();
				ajaxGet(id);
			});

			// DO GET
			function ajaxGet(id) {
				$.ajax({
					type : "GET",
					url : "link/" + id + "/upvote",
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