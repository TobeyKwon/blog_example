let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            console.log("버튼클릭");
            this.save();
        })
        $("#btn-login").on("click", () => {
            console.log("버튼클릭");
            this.login();
        })
        $("#btn-update").on("click", () => {
            console.log("버튼클릭")
            this.update();
        })
    },
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        }
        $.ajax({
            type: "post",
            url: "/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            console.log(response)
            alert("회원가입되었습니다.")
            location.href = "/";
        }).fail(function (error) {
            console.log(error)
        })
    },
    login: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }
        $.ajax({
            type: "post",
            url: "/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            console.log(response);
            if(response !== -1) {
                alert("로그인이 완료되었습니다.");
                location.href = "/";
            } else {
                alert("로그인 실패");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();
        let data = {
            password: $("#password").val(),
            email: $("#email").val(),
        }
        $.ajax({
            type: "put",
            url: `/api/user/${id}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            console.log(response);
            alert("회원정보 수정이 완료되었습니다.");
            history.back();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
index.init();