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
							const newCount = Number($('#votecount').html()) + 1;
							$('#votecount').empty();
							$("#votecount").html(newCount);
							console.log("Success! VoteCount of current link increases to: ", newCount);
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