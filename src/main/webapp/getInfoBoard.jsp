
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--(실습) 자바코드를 없애고, 데이터출력은 EL 사용 형태로 변경 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세보기</title>
<!-- include libraries(jQuery, bootstrap) -->
<%-- 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
--%>

<script src="https://cdn.ckeditor.com/4.13.1/standard-all/ckeditor.js"></script>

<style>
	#container {
		width: 700px;
		margin: 0 auto;
	}
	h1, h3, p { text-align: center; }
	table { border-collapse: collapse; }
	table, th, td {
		border: 1px solid black;
		margin: 0 auto;
	}
	th { background-color: orange; }
	.center { text-align: center; }
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>

<div id="container">
	<h1>글 상세</h1>
	
	<hr>
	<form action="updateInfoBoard.do" method="post">
		<input type="hidden" name="info_idx" value="${infoBoard.info_idx }">
	<table id="table">
	<tbody>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="info_title" 
					value="${infoBoard.info_title }">
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${infoBoard.info_ins_user }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			 <textarea name="content" id="editor1" rows="10" cols="80">${infoBoard.info_content }</textarea>
			</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${infoBoard.info_ins_dt }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${infoBoard.info_hit }</td>
		</tr>
		<tr>
			<td colspan="2" class="center">
				<input type="submit" value="글 수정">
			</td>
		</tr>

		</tbody>
		
	
	
<!-- 시티 설치 코드 끝 -->
	</table>
	</form>
	<p>
		<a href="insertInfoBoard.jsp">글등록</a>
		<a href="deleteInfoBoard.do?info_idx=${infoBoard.getInfo_idx() }">글삭제</a>
		<a href="getInfoBoardList.do">글목록</a>
	</p>
</div>

	   <!-- 에디터  -->
   <script type="text/javascript">
       CKEDITOR.replace('editor1', {
         extraPlugins : 'image2',
         filebrowserImageUploadUrl : 'fileupload.do',
         // Upload dropped or pasted images to the CKFinder connector (note that the response type is set to JSON).
         uploadUrl : 'fileupload.do',

         height : 450
      }); 
      
   
   </script>

	
	<div id="disqus_thread">
		<script>
		
		/**
		*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
		*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
		/*
		var disqus_config = function () {
		this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
		this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
		};
		*/
		(function() { // DON'T EDIT BELOW THIS LINE
		var d = document, s = d.createElement('script');
		s.src = 'https://koreigner.disqus.com/embed.js';
		s.setAttribute('data-timestamp', +new Date());
		(d.head || d.body).appendChild(s);
		})();
		</script>
		

</body>
</html>








