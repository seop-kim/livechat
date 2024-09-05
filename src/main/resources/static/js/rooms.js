$(document).ready(function () {
    const roomName = /*[[${roomName}]]*/ null;
    console.log('room name : ', roomName);

    $("#btn-create").on("click", function (e) {
        e.preventDefault();
        const name = $("input[name='name']").val().trim();

        if (!name) {
            alert("Please write the name.");
        } else {
            // alert('[ ' +name + ` ] 방이 개설되었습니다.`);
            $("form").off('submit').submit(); // 폼 제출 이벤트 리스너 제거 후 제출
        }
    });
});