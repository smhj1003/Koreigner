<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>글등록</title>
<%-- 
<!-- include libraries(jQuery, bootstrap) -->
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
	<h1>글등록</h1>
	<p><a href="logout.do">Log-out</a></p>
	<hr>
	
	<form action="insertInfoBoard.do" method="post"
			enctype="multipart/form-data">
	<table>
		<tr>
			<th width="70">제목</th>
			<td>
				<input type="text" name="info_title" size="30">
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="info_ins_user">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
    			<textarea name="info_content" id="editor1" rows="10" cols="80"></textarea>
    	    </td>
		</tr>
		<tr>
			<th>업로드</th>
			<td>
				<input type="file" name="uploadFile">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="center">
				<input type="submit" value="새글 등록">
			</td>
		</tr>
	</table>
	</form>
	<p><a href="getInfoBoardList.do">글 목록 가기</a></p>
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

</body>
</html>











