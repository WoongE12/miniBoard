function toggleButtons(commId) {
	var buttonsDiv = document.getElementById("buttons-" + commId);
	if (buttonsDiv.style.display === "none" || buttonsDiv.style.display === "") {
		buttonsDiv.style.display = "block";
	} else {
		buttonsDiv.style.display = "none";
	}
}

function confirmBoardDelete(bId) {
	if (confirm("삭제하시겠습니까?")) {
		location.href = '/ex/board/delete?b_id=' + bId;
	}
}

function confirmDelete() {
	return confirm("삭제하시겠습니까?");
}