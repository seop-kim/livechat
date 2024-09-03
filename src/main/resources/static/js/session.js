function checkSession(onSessionExpired) {
    $.ajax({
        url: '/checkSession',
        method: 'GET',
        success: function (response) {
            if (!response.active) {
                onSessionExpired();
            }
        },
        error: function () {
            onSessionExpired(); // 세션 확인 중 오류 발생 시에도 세션 만료 처리
        }
    });
}

function handleSessionExpiry() {
    alert('세션이 만료되었습니다. 다시 로그인해주세요.');
    window.location.href = '/member/login';
}

$(document).ready(function () {
    // 주기적으로 세션 상태 확인
    setInterval(() => {
        checkSession(handleSessionExpiry);
    }, 5000); // 5초마다 세션 상태 확인
});