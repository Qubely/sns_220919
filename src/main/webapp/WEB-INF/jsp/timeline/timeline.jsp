<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 : 로그인 된 상태에서만 보여짐 --%>
	<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
	</c:if>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<%-- 카드1 --%>
			<c:forEach var="card" items="${cardList}">
				<div class="card border rounded mt-3">
					<%-- 글쓴이, 더보기(삭제) --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold">${card.user.loginId}</span>
						
						<%-- 더보기(내가 쓴 글일 때만 노출) --%>
						<c:if test="${userId eq card.user.id}">
						<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
						</c:if>
					</div>
					
					<%-- 카드 이미지 --%>
					<div class="card-img">
						<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
					</div>
					
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<c:choose>
							<c:when test="${card.filledLike}">
								<a href="#" class="like-btn" data-user-id="${userId}" data-post-id="${card.post.id}">
								<img src="/static/img/heart-icon (1).png" width="18" height="18" alt="empty heart">
									좋아요 ${card.likeCount}개
								</a>
							</c:when>
							<c:otherwise>
								<a href="#" class="like-btn" data-user-id="${userId}" data-post-id="${card.post.id}">
								<img src="/static/img/heart-icon.png" width="18" height="18" alt="empty heart">
									좋아요 ${card.likeCount}개
								</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					<%-- 글 --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">${card.user.loginId}</span>
						<span>${card.post.content}</span>
					</div>
					
					<%-- 댓글 --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
					
					<%-- 댓글 목록 --%>
					<div class="card-comment-list m-2">
						<%-- 댓글 내용 --%>
						<c:forEach var="commentView" items="${card.commentList}">
							<div class="card-comment m-1">
								<span class="font-weight-bold">${commentView.user.loginId}:</span>
								<span>${commentView.comment.content}</span>
								
								<%-- 댓글 삭제 버튼(내가 쓴 댓글일 때만 노출) --%>
								<c:if test="${userId eq commentView.user.id}">
								<a href="#" class="commentDelBtn" data-post-id="${card.post.id}" data-comment-id="${commentView.comment.id}">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
								</a>
								</c:if>
							</div>
						</c:forEach>
						
						<%-- 댓글 쓰기 --%>
						<c:if test="${not empty userId}">
							<div class="comment-write d-flex border-top mt-2">
								<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
								<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
							</div>
						</c:if>
					</div>
					<%--// 댓글 목록 끝 --%>
				</div>
			</c:forEach>
			<%--// 카드1 끝 --%>
		</div>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-sm : 작은 모달 창 --%>
	<%-- modal-dialog-centered : 모달 창을 수직으로 가운데 정렬 --%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
				<a href="#" id="deletePostBtn">삭제하기</a>
			</div>
			<div class="py-3">
				<%-- data-dismiss="modal" : 모달창 닫힘 --%>
				<a href="#" data-dismiss="modal">취소</a>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		
		
		// 파일 업로드 이미지 클릭 => 숨겨져 있는 file을 동작시킴
		$('#fileUploadBtn').on('click', function(e) {
			e.preventDefault();	// a 태그의 페이지 올라가는 현상 방지
			$('#file').click();	// input file을 클릭한 것과 같은 효과
		});
		
		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일 이름 노출
		$('#file').on('change', function(e) {
			//alert("파일 선택");
			let fileName = e.target.files[0].name;	// seagull-7401497_960_720.jpg
			//alert(fileName);
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "jpeg" && ext != "png" && ext != "gif") {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val("");	// 파일 태그의 실제 파일 제거
				$('#fileName').text("");	// 파일 이름 비우기
				return;
			}
			
			// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
			$('#fileName').text(fileName);
		});	// 이미지 유효성 검사 끝
		
		// 글 저장
		$('#writeBtn').on('click', function() {
			let content = $('#writeTextArea').val();
			let file = $('#file').val();
			
			if (content == "") {
				alert("글 내용을 입력해주세요.");
				return;
			}
			
			if (file == "") {
				alert("업로드할 사진을 넣어주세요.");
				return;
			}
			
			// 서버 - AJAX
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				type:"post"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				
				, success:function(data) {
					if (data.code == 1) {
						alert("새 타임라인이 게시되었습니다.");
						location.href = "/timeline/timeline_view"
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("타임라인 게시에 실패했습니다.");
				}
			});	// ajax 끝
		});	// 글쓰기 버튼 끝
		
		// 댓글 쓰기
		$('.comment-btn').on('click', function() {
			// 글번호, 댓글 내용
			let postId = $(this).data('post-id');
			// 지금 클릭된 게시버튼의 형제인 input 태그를 가져온다. siblings
			let comment = $(this).siblings('input').val().trim();
			
			if (comment == "") {
				alert("댓글 내용을 입력해주세요");
				return;
			}
			
			// ajax
			$.ajax({
				type:"post"
				, url:"/comment/create"
				, data:{"postId":postId, "content":comment}
			
				,success:function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
			
		});	// 댓글 쓰기 끝
		
		// 댓글 삭제
		$('.commentDelBtn').on('click', function(e) {
			e.preventDefault();
			let commentId = $(this).data("comment-id");
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"delete"
				, url:"/comment/delete"
				, data:{"postId":postId, "commentId":commentId}
			
				, success:function(data) {
					if (data.code == 1) {
						alert("댓글 삭제완료.");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("삭제 실패");
				}
			});
		});
		
		// 좋아요
		$('.like-btn').on('click', function(e) {
			e.preventDefault();
			
			let userId = $(this).data("user-id");
			if(userId == "") {
				alert("로그인을 해주세요");
				return;
			}
			
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"get"
				, url:"/like/" + postId
				
				, success:function(data) {
					if (data.code == 1) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("좋아요 실패");
				}
			});
			
			
		});	// 좋아요 끝
		
		// 더보기 버튼 클릭 (글 삭제를 위해)
		$('.more-btn').on('click', function(e) {
			e.preventDefault();
			
			let postId = $(this).data("post-id");	// getting
			$('#modal').data('post-id', postId);	// setting 모달 태그에 data-post-id를 심어줌
		});
		
		// 모달 안에 있는 삭제하기 버튼 클릭
		$('#modal #deletePostBtn').on('click', function(e) {
			e.preventDefault();
			let postId = $('#modal').data('post-id');
			
			$.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{"postId":postId}
			
				, success:function(data) {
					if (data.code == 1) {
						alert("삭제 완료.");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("삭제 실패.");
				}
			});
		});
		
	});
</script>