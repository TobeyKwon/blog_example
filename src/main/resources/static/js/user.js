let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            console.log("버튼클릭");
            this.save();
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
            alert("회원가입이 완료되었습니다.")
            location.href = "/";
        }).fail(function (error) {
            console.log(error)
        })
    }
}
index.init();