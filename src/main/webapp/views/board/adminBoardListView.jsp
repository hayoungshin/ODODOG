<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odd.common.model.vo.PageInfo, com.odd.board.model.vo.Board, com.odd.board.model.vo.Reply" %>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo replyPi = (PageInfo)request.getAttribute("replyPi");
	ArrayList<Reply> replyList = (ArrayList<Reply>)request.getAttribute("replyList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

    /******** 내용 *******/
    #content{/*border:1px solid pink;*/}

    #content p{
        margin-top:10px;
        margin-bottom:3px;
        margin-left:20px;
        font-weight:700;
    }
    #content hr{
        margin:10px 0px;
        border:1px solid rgb(220,220,220);
    }
	
    .list-area{
        width:850px;
        font-size:13px;
        text-align:center;
    }
    .list-area th{
        border-bottom: 2px solid rgb(220, 220, 220);
    }
    .list-area th, .list-area td{
        border-top: 1px solid rgb(220, 220, 220);
        padding:5px 0px; line-height:30px;
    }
    .list-area>tbody>tr:hover{font-weight:700; cursor: pointer;}
    .status-n{
    	width:30px;	height:23px;
    	border:2px solid rgb(220,220,220);
    	border-radius:5px;
    	vertical-align:middle;
    	line-height:15px;
    }
	.status-n:hover{
		background:rgb(220,220,220);
	}
	.status-y{
		width:30px; height:23px;
		border-radius:5px;
		vertical-align:middle;
		line-height:15px;
		background:rgb(220,220,220);
	}
	.status-y:hover{
		border:2px solid rgb(200,200,200);
		background:rgb(200,200,200);
	}
	
    .paging-area button{
        border:none;
        background:none;
        color:rgb(50, 50, 50);
    }
    .paging-area button:hover{
        background:rgb(220,220,220);
        font-weight:700;
    }

</style>

</head>
<body>
    
    <div class="wrap">
        <%@ include file="../common/adminMenubarForInclude.jsp" %>

        <div id="content" align="center">
        
            <br>
            <div align="left">
                <p>일반 게시글 관리</p>
            </div>
            <hr>

            <div align="right" style="margin:15px 0px;">
                <form action="" style="font-size:13px;">
                	<input type="hidden" name="bpage" value="1">
                	<input type="hidden" name="rpage" value="<%= replyPi.getCurrentPage() %>">
                    <select name="bSort" style="height:25px; border:1px solid rgb(220, 220, 220); border-radius:5px;">
                        <option value="title">제목</option>
                        <option value="writer">작성자</option>
                    </select>
                	
                    <input type="text" name="bKeyword"
                        style="width:200px; height:25px; border-right:none; border:1px solid rgb(220, 220, 220); border-radius:5px;">
                
                    <button type="submit" style="height:25px; width:40px; border:1px solid rgb(220,220,220);">
                        <img src="<%= contextPath %>/resources/icons/search.png" width="12px" style="opacity:0.5; margin-bottom:3px;">
                    </button>
                
                </form>
            </div>

            <table class="list-area">
                <thead>
                    <tr>
                        <th width="40">번호</th>
                        <th width="470">제목</th>
                        <th width="80">작성자</th>
                        <th width="80">작성일</th>
                        <th width="70">조회수</th>
                        <th width="60">상태</th>
                        <th width="50"></th>
                    </tr>
                </thead>
               	<% if(list.isEmpty()){ %>
                    <tr>
                        <td colspan="6" style="height:300px;"> 조회된 게시글이 없습니다.</td>
                    </tr>
               	<% } else { %>
               		<% for(Board b : list){ %>
               			<tr>
                        <td><%= b.getBoardNo() %></td>
                        <td><%= b.getBoardTitle() %></td>
                        <td><%= b.getBoardWriter() %></td>
                        <td><%= b.getCreateDate() %></td>
                        <td><%= b.getCount() %></td>
                        <td align="center">
                        	<% if(b.getStatus().equals("Y")){ %>
                        		<button class="status-y">Y</button>
                        	<% } else { %>
                        		<button class="status-n">N</button>
                        	<% } %>
                        </td>
                        <td>
                            <button class="btn-red" style="height:25px; vertical-align:middle; line-height:20px;">삭제</button>
                        </td>
                    </tr>
               		<% } %>
               	<% } %>
            </table>
            <br>
            <div class="paging-area" align="center">
	        	<% if(pi.getCurrentPage() != 1){ %>
	        		<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=pi.getCurrentPage()-1%>&rpage=<%=replyPi.getCurrentPage()%>';">&lt;</button>
	        	<% } %>
	        	<% for(int p = pi.getStartPage(); p <= pi.getEndPage(); p++){ %>
	        		<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=p%>&rpage=<%=replyPi.getCurrentPage()%>';"><%= p %></button>
	        	<% } %>
	        	<!-- 마지막페이지일 때 -->
	        	<% if(pi.getCurrentPage() != pi.getMaxPage()){ %>
	            	<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=pi.getCurrentPage()+1%>&rpage=<%=replyPi.getCurrentPage()%>'">&gt;</button>
	            <% } %>
            </div>

            <br><br>
            <hr style="border:3px solid rgb(220,220,220);">
        
            <br><br>
            <div align="left">
                <p>댓글 관리</p>
            </div>
            <hr>
            
            <div align="right" style="margin:15px 0px;">
                <form action="" style="font-size:13px;">
            		<input type="hidden" name="rpage" value="1">
            		<input type="hidden" name="bpage" value="<%= pi.getCurrentPage() %>">
                    <select name="rSort" style="height:25px; border:1px solid rgb(220, 220, 220); border-radius:5px;">
                        <option value="content">댓글내용</option>
                        <option value="writer">작성자</option>
                    </select>
            
                    <input type="text" name="rKeyword"
                        style="width:200px; height:25px; border-right:none; border:1px solid rgb(220, 220, 220); border-radius:5px;">
                    
                    <button type="submit" style="height:25px; width:40px; border:1px solid rgb(220,220,220);">
                        <img src="<%= contextPath %>/resources/icons/search.png" width="12px"
                            style="opacity:0.5; margin-bottom:3px;">
                    </button>
                </form>
            </div>
            
            <table class="list-area">
                <thead>
                    <tr>
                        <th width="40">번호</th>
                        <th width="500">댓글내용</th>
                        <th width="80">작성자</th>
                        <th width="80">작성일</th>
                        <th width="40">글번호</th>
                        <th width="60">상태</th>
                        <th width="50"></th>
                    </tr>
                </thead>
               	<% if(replyList.isEmpty()){ %>
                    <tr>
                        <td colspan="6" style="height:300px;">조회된 댓글이 없습니다.</td>
                    </tr>
               	<% } else { %>
               		<% for(Reply r : replyList){ %>
	                    <tr>
	                        <td><%= r.getReplyNo() %></td>
	                        <td><%= r.getReplyContent() %></td>
	                        <td><%= r.getReplyWriter() %></td>
	                        <td><%= r.getCreateDate() %></td>
	                        <td><%= r.getBoardNo() %></td>
	                        <td align="center">
	                        	<% if(r.getStatus().equals("Y")){ %>
	                        		<button class="status-y">Y</button>
	                        	<% } else { %>
	                        		<button class="status-n">N</button>
                       			<% } %>
	                        </td>
	                        <td>
	                            <button class="btn-red" style="height:25px; vertical-align:middle; line-height:20px;">삭제</button>
	                        </td>
	                    </tr>
               		<% } %>
               	<% } %>
            </table>
            
            
            <br>
            
            <div class="paging-area" align="center">
	        	<% if(replyPi.getCurrentPage() != 1){ %>
	        		<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=pi.getCurrentPage()%>&rpage=<%=replyPi.getCurrentPage()-1%>';">&lt;</button>
	        	<% } %>
	        	<% for(int p = replyPi.getStartPage(); p <= replyPi.getEndPage(); p++){ %>
	        		<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=pi.getCurrentPage()%>&rpage=<%=p%>';"><%= p %></button>
	        	<% } %>
	        	<!-- 마지막페이지일 때 -->
	        	<% if(replyPi.getCurrentPage() != replyPi.getMaxPage()){ %>
	            	<button onclick="location.href='<%=contextPath%>/list.adBo?bpage=<%=pi.getCurrentPage()%>&rpage=<%=replyPi.getCurrentPage()+1%>'">&gt;</button>
	            <% } %>
            </div>
            <br><br><br>
        
        </div>

    </div>
	
	
	
</body>
</html>