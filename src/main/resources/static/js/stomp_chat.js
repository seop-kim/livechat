$(document).ready(function () {
    console.log("roomName : ", roomName)
    console.log("roomId : ", roomId)
    console.log("username : ", username)

    const sockJs = new SockJS("/stomp/chat");
    const stomp = Stomp.over(sockJs);

    stomp.connect({}, function () {
        stomp.subscribe(`/sub/chat/room/` + roomId, function (chat) {
            const content = JSON.parse(chat.body);
            const writer = content.writer;
            const message = content.message;
            displayMessage(writer, message);
        });
        stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}));
    });

    $("#button-send").on("click", function () {
        sendMessage();
    });

    $("#msg").on("keydown", function (e) {
        if (e.key === "Enter" && this.value.trim() !== "") {
            e.preventDefault();
            sendMessage();
        }
    });

    function sendMessage() {
        const msg = document.getElementById("msg").value.trim();
        if (msg) {
            stomp.send('/pub/chat/message', {}, JSON.stringify({
                roomId: roomId,
                message: msg,
                writer: username
            }));
            document.getElementById("msg").value = '';
        }
    }

    function displayMessage(writer, message) {
        const isOwnMessage = writer === username;
        const alertClass = isOwnMessage ? 'alert-secondary' : 'alert-warning';
        const messageHtml =
            `
                    <div class='col-6'>
                        <div class='alert ${alertClass}'>
                            <b>${writer} : ${message}</b>
                        </div>
                    </div>`;
        $("#msgArea").append(messageHtml);
    }
});