// 5초마다 세션 상태 확인
export function checkSession(callback, interval = 5000) {
    setInterval(() => {
        $.ajax({
            url: '/checkSession',
            method: 'GET',
            success: function (response) {
                if (!response.active) {
                    callback();
                }
            },
            error: function () {
                callback();
            }
        });
    }, interval);
}