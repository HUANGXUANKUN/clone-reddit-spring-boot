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
							const newCount = Number($('#votecount').html()) + 1;
							$('#votecount').empty();
							$("#votecount").html(newCount);
							console.log("Success! VoteCount of current link increases to: ", result.data);
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