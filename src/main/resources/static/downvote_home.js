GET: $(document).ready(
		function() {
			// GET REQUEST
			$("#downvote").click(function(event) {
				var id = this.getAttribute("data-id");
				event.preventDefault();
				ajaxGet(id);
			});

			// DO GET
			function ajaxGet(id) {
				$.ajax({
					type : "GET",
					url : "link/" + id + "/downvote",
					success : function(result) {
						if (result.status == "success") {
							const newCount = Number($('#votecount').html()) - 1;
							$('#votecount').empty();
							$("#votecount").html(newCount);
							console.log("Success! VoteCount of current link decreases to: ", result.data);
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