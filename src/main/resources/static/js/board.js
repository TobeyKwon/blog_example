let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            console.log("버튼 클릭")
            this.save();
        })
        $("#btn-delete").on("click", () => {
            console.log("버튼 클릭");
            this.deleteById()
        })
        $("#btn-update").on("click", () => {
            console.log("버튼 클릭");
            this.update()
        })
        $("#btn-reply-save").on("click", () => {
            console.log("버튼 클릭");
            this.replySave()
        })
    },
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type: "post",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("글작성 완료!")
            console.log(response)
            location.href = "/"
        }).fail(function (error){
            alert("글작성 실패")
            console.log(error)
        })
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "delete",
            url: `/api/board/${id}`,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("글 삭제 완료")
            console.log(response)
            location.href = "/"
        }).fail(function (error){
            alert("글 삭제 실패")
            console.log(error)
        })
    },
    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").text(),
        }
        console.log(data);
        $.ajax({
            type: "put",
            url: `/api/board/${id}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("글수정 완료!")
            console.log(response)
            location.href = "/"
        }).fail(function (error){
            alert("글수정 실패")
            console.log(error)
        })
    },
    replySave: function () {
        let boardId = $("#boardId").val()
        let data = {
            content: $("#reply-content").val(),
        }
        console.log(data)
        $.ajax({
            type: "post",
            url: `/api/board/${boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("댓글 작성 완료!")
            console.log(response)
            location.href = `/board/${boardId}`
        }).fail(function (error){
            alert("댓글 실패")
            console.log(error)
        })
    },
    replyDelete: function (boardId, replyId) {
        $.ajax({
            type: "delete",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function (response){
            alert("댓글 삭제 완료!")
            console.log(response)
            location.href = `/board/${boardId}`
        }).fail(function (error){
            alert("댓글 삭제 실패")
            console.log(error)
        })
    }
}
index.init();