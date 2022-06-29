$(document).ready(function() {
    /*
    执行对主页面信息的个性化
    */
    var roletype=$.cookie("role");
    switch(Number(roletype)){
        case 1:{
           $("#AdminIn").text($.cookie("admin1"));
           $("#PassIn").text($.cookie("password1"));
           $(".Num1").text($.cookie("name1"));
           $("#exampleModal6").parent().attr("action","user/updateUser/"+$("#collapseWidthExample").find(".DEF").text());
           $("#Roles").text($.cookie("aRole"));
           $(".TEA").hide();
           $(".STU").hide();
           $(".DEF").show();
           break;
        }
        case 2:{
            $("#AdminIn").text($.cookie("admin2"));
            $("#PassIn").text($.cookie("password2"));
            $("#NameQ,.Num1").text($.cookie("tName"));
            $("#SexQ").text($.cookie("tSex"));
            $("#SubQ").text($.cookie("tSubject"));
            $("#exampleModal6").parent().attr("action","user/updateUser/"+$("#TEANUM").find("span").last().text());
            $("#Roles").text($.cookie("tRole"));
            $(".DEF").hide();
            $(".STU").hide();
            $(".TEA").show();
            break;
        }
        case 3:{
            $("#AdminIn").text($.cookie("admin3"));
            $("#PassIn").text($.cookie("password3"));
            $("#NameQ,.Num1").text($.cookie("name"));
            $("#SexQ").text($.cookie("sex"));
            $("#SubQ").text($.cookie("subject"));
            $("#exampleModal6").parent().attr("action","user/updateUser/"+$("#STUNUM").find("span").last().text());
            $("#Roles").text($.cookie("sRole"));
            $(".TEA").hide();
            $(".DEF").hide();
            $(".STU").show();
            break;
        }
        console.log($("#exampleModal6").parent().attr("action"));
    }
    /*
    引入密码修改信息
    */
    $("#UpPas").click(function(){
        var ad=$("#AdminIn").text();
        $(".PassAdmin").text(ad);
    });
    $("#passUpdate").click(function(){
        console.log("password/"+$("#AdminIn").text()+"/"+$("#addPass").val());
        $.ajax({
            url : "password/"+$("#AdminIn").text()+"/"+$("#addPass").val(),
            type : "PUT",
            success : function(result) {
                alert(result);
                location.reload();
            }
        })
    });
    /*
    引入Questions表
    */
    $("#Ifatab").load("allQuestions #TablePage",function(){
        $("#Ifatab").find(".nono").css("display","none");
    });
    /*
    添加考试表单控制
    */
    $("#addExamB").click(function(){
        $("#Es").val($("#SubQ").text());
        $.ajax({
            url:"exam/2",
            type:"POST",
            data:$("#addExam").serialize(),
            success:function(msg){
                alert(msg);
                location.reload();
            }
        })
    })

/*    $("#exampleModal5").find("a").click(function(){
        $("#Es").val($("#SubQ").text());
        $("#AEform").attr("action","setExam/2");
        console.log($("#AEform").attr("action")+"+"+$("#Es").val());
        console.log($("#AEform").serializeArray());
        $("#AEform").submit();
    });*/

    /*
    表格分页
    */
    $("#TablePage").DataTable({
        "language":{
            "emptyTable":"数据是空的！",
            "lengthMenu":"一页将显示 _MENU_ 项结果",
            "processing":"服务器正在处理火星人...",
            "zeroRecords":"没有匹配到哦",
            "info":"显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "infoEmpty":"显示第 0 至 0 项结果，共 0 项",
            "infoFiltered":"(由 _MAX_ 项结果过滤)",
            "search":"模糊查询:",
            "paginate":{
                "first":"首页",
                "last":"末页",
                "next":"下一页",
                "previous":"上一页"
            },
            "aria":{
                "sortAscending":":以升序排列此列",
                "sortDescending":":以降序排列此列"
            },
            "loadingRecords":"数据正在飞速前往地球...",
        }
    });
});
