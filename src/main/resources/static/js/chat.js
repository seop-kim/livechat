$(document).ready(function () {
    const websocket = new WebSocket("ws://localhost:8080/ws/chat");

    // Event Listeners
    $("#disconn").on("click", disconnect);
    $("#button-send").on("click", send);
    $("#msg").on("keydown", handleKeyDown);

    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;

    function send() {
        const msgInput = document.getElementById("msg");

        if (msgInput.value.trim() === "") {
            return;
        }

        const message = `${msgInput.value}`;
        console.log(message);
        websocket.send(message);
        msgInput.value = '';
    }

    function disconnect() {
        websocket.close();
    }

    function onClose(e) {
        sendSystemMessage("님이 방을 나가셨습니다.");
    }

    function onOpen(e) {
        sendSystemMessage("님이 입장하셨습니다.");
    }

    function sendSystemMessage(action) {
        const message = `${action}`;
        websocket.send(message);
    }

    function onMessage(event) {
        const data = event.data;
        const colonIndex = data.indexOf(':');
        const sessionId = data.substring(0, colonIndex);
        const message = data.substring(colonIndex + 1);

        console.log('Message received: ', data);

        displayMessage(sessionId, message, sessionId === username);
    }

    function handleKeyDown(e) {
        if (e.key === "Enter" && this.value.trim() !== "") {
            e.preventDefault();
            send();
        }
    }

    function displayMessage(sessionId, message, isOwnMessage) {
        const alertClass = isOwnMessage ? 'alert-secondary' : 'alert-warning';
        const messageHtml =
            `
                <div class='col-6'>
                    <div class='alert ${alertClass}'>
                        <b>${sessionId} : ${message}</b>
                    </div>
                </div>
            `;
        $("#msgArea").append(messageHtml);
    }
});