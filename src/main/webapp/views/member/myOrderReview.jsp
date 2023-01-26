<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        /*border:1px solid black;*/
        margin:auto;
        height:100%;
        width:1200px;
    }

    /****** 사이드 ******/
    #side{
        /*border:1px solid blue;*/
        width:20%; 
        height:100%;
        padding:20px;
        color: rgb(50, 50, 50);
        font-weight: 600;
        float:left;
    }
    #side>hr{margin-bottom:30px; border:1px solid rgb(220,220,220);}
    #side>a{
        text-decoration:none;
        color: rgb(50, 50, 50);
    }

    /****** 내용 ******/
    .content{
        width:75%;
        height:100%;
        margin:auto;
        margin-top:190px;
        margin-bottom:200px;
        display: inline-block;
    }
    .myOrderView{
        margin:auto;
        width: 100%;
        height:150px;
        border:1px solid gray;
    }
    .myOrderReview{
        width:800px;
        margin:auto;
    }
    .myOrderView table{
        width:100%;
        height:150px;
    }
    .myOrderView td{
       text-align: center;
       font-weight: 600;
    }
    img{
        width:100%;
        height:100%;
        box-sizing: border-box;
    }
    .review1 td:first-child{
        width:200px;
        height:50px;
        border-right:1px solid gray;
        text-align:center;
        font-size: 14px;
        font-weight: 600;
        color:rgba(20, 18, 18, 0.742);
    }
    .review1 td:last-child{
        width:600px;
        height:50px;
    }
    .review1 td{
        border-collapse: collapse;
        border-spacing: 0;
        border-top:1px solid gray;
    }
    #myStar fieldset{
        display: inline-block;
        border:none;
        direction:ltr;

    }
    #myStar input[type=radio]{
        display:none;
    }
    #myStar label{
        font-size:3em;
        color:transparent;
        text-shadow: 0 0 0 #f0f0f0;
    }
    #myStar label:hover{
        text-shadow:rgba(255, 255, 0, 0.505);
    }
    #myStar label:hover~label{
        text-shadow:rgba(255, 255, 0, 0.505);
    }
    .review2 td{
        border-top:1px solid gray;
        border-bottom:1px solid gray;
    }
    textarea{
        border:none;
        resize: none;
    }
    button{
        border-radius:4px; 
        background:white; 
        width:80px; 
        height:30px; 
        border:1px solid gray;
    }
    .review1, .review2{
        width:100%;
        margin:auto;
    }
    input[type=text]{
        border:none;
    }
    .reviewContent{
        margin:auto;
        width:800px;
    }
    table{
        border-collapse: collapse;
        border-spacing: 0;
    }
</style>
</head>
<body>

	<%@include file="../common/userMenubar.jsp" %>

    <div class="outer">

        <div id="side">
            <br><br><br><br><br>
            <p style="font-size:23px;">MYPAGE</p>
            <hr>
            <a href="<%=contextPath%>/myOrder.me" style="font-size:17px;color:rgb(200, 140, 140);">주문조회</a> <br><br>
            <a href="<%=contextPath%>/myPage.me" style="font-size:17px;">회원정보</a> <br><br>
            <a href="" style="font-size:17px;">관심상품</a> <br><br>
            <a href="<%=contextPath%>/myPoint.me" style="font-size:17px;">적립금</a> <br><br>
            <a href="" style="font-size:17px;">게시글조회</a> <br><br>
            <a href="" style="font-size:17px;">1:1맞춤상담</a>
        </div>

        <script>
             $("#side a").hover(function(){
                $(this).css("color", "rgb(200, 140, 140)");
            }, function(){
                 $(this).css("color", "rgb(50, 50, 50)");
             })
         </script>

        <div class="content">

            <h2 align="center">주문조회 > 리뷰작성</h2>
            <br><br><br><br>

            <div class="myOrderReview">

                <div style="font-size:13px; color:rgba(36, 33, 33, 0.903); font-weight:600;">
                    2023.01.22 20:20 (주문날짜시간들어갈자리)
                </div>

                <br>

                <div class="myOrderView">
                    <form>
                        <table>
                            <tr>
                                <td style="width:150px;"><img src="" ></td>
                                <td style="width:250px;">댕댕이 수제간식</td>
                                <td style="width:200px; padding-right: 20px;"></td>
                                <td style="width:200px; text-align:left;">
                                    &nbsp;상품명 : 댕댕츄르 <br><br>
                                    &nbsp;구매자 : 와그작 <br><br>
                                    &nbsp;총금액 : 15,000원
                                    
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <br>

                <div class="reviewContent">
                    <form>
                        <table class="review1">
                            <tr>
                                <td>상품을 사용해보셨나요?</td>
                                <td>
                                    <div name="myStar" id="myStar">
                                        <fieldset>
                                            <label for="star1">⭐</label><input type="radio" name="star" id="star1" value="1">
                                            <label for="star2">⭐</label><input type="radio" name="star" id="star2" value="2">
                                            <label for="star3">⭐</label><input type="radio" name="star" id="star3" value="3">
                                            <label for="star4">⭐</label><input type="radio" name="star" id="star4" value="4">
                                            <label for="star5">⭐</label><input type="radio" name="star" id="star5" value="5">
                                    
                                        </fieldset>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table class="review2">
                            <tr>
                                <td height="35px" width="20%;">제목 : </td>
                                <td><input type="text" name="title" size="50px;" value="내리뷰제목이들어올자리"></td>
                            </tr>
                            <tr>
                                <td>내용 : </td>
                                <td>
                                    <br>
                                    <textarea name="" id="" cols="100%" rows="30">내리뷰내용이들어올자리</textarea>
                                    <br><br>
                                </td>
                            </tr>
                        </table>
                    </form>
                    
                    <br>

                    <div align="center">
                    <button type="submit">작성완료</button>
                    </div>

                </div>

            </div>

        </div>



    </div>

</body>
</html>