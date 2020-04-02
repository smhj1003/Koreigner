$(function() {

  // 희망급여 추가
  $('#form1').on('keyup', 'input.num_only2', function(e){
    if( $(this).val() != null && $(this).val() != '' ) {
      var tmps = parseInt($(this).val().replace(/[^0-9]/g, '')) || 0;
      var tmps2 = tmps.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
      $(this).val(tmps2);
    }

    var msg = $(this).next('span.msg').removeClass('success fail');
    if ( parseInt($('#pay_amount1').val()) > parseInt($('#pay_amount2').val()) ) {
      msg.addClass('fail').html('Desired minimum salary is greater than maximum salary');
    } else {
      msg.removeClass('success fail').html('');
    } // end if--

  });

 // 최종학력 > 해당사항없음 선택 시 '학위', '최종학교' 비활성화
  if ($(":input:radio[name=GRADUATE_SCH]:checked").val() == 'N/A') {
    $(':input:radio[name=DEP]').attr('disabled', true);
    $('#school_entr_year').val('').attr('disabled', true);
    $('#school_entr_month').val('').attr('disabled', true);
    $('#school_grad_year').val('').attr('disabled', true);
    $('#school_grad_month').val('').attr('disabled', true);
    $('#school_area').val('').attr('disabled', true);
    $('#school_name').val('').attr('disabled', true);
    $('#school_major').val('').attr('disabled', true);
  } else if ($(":input:radio[name=GRADUATE_SCH]:checked").val() == '중/고졸') {
    $(':input:radio[name=DEP]').attr('disabled', true);
  } else {
    $(':input:radio[name=DEP]').attr('disabled', false);
    $('#school_entr_year').attr('disabled', false);
    $('#school_entr_month').attr('disabled', false);
    $('#school_grad_year').attr('disabled', false);
    $('#school_grad_month').attr('disabled', false);
    $('#school_area').attr('disabled', false);
    $('#school_name').attr('disabled', false);
    $('#school_major').attr('disabled', false);
  }
  $('input[type=radio][name=GRADUATE_SCH]').change(function() {
    if ($(":input:radio[name=GRADUATE_SCH]:checked").val() == 'N/A') {
      $(':input:radio[name=DEP]').attr('disabled', true);
      $('#school_entr_year').val('').attr('disabled', true);
      $('#school_entr_month').val('').attr('disabled', true);
      $('#school_grad_year').val('').attr('disabled', true);
      $('#school_grad_month').val('').attr('disabled', true);
      $('#school_area').val('').attr('disabled', true);
      $('#school_name').val('').attr('disabled', true);
      $('#school_major').val('').attr('disabled', true);
    } else if ($(":input:radio[name=GRADUATE_SCH]:checked").val() == '중/고졸') {
      $(':input:radio[name=DEP]').attr('disabled', true);
      $('#school_entr_year').attr('disabled', false);
      $('#school_entr_month').attr('disabled', false);
      $('#school_grad_year').attr('disabled', false);
      $('#school_grad_month').attr('disabled', false);
      $('#school_area').attr('disabled', false);
      $('#school_name').attr('disabled', false);
      $('#school_major').attr('disabled', false);
    } else {
      $(':input:radio[name=DEP]').attr('disabled', false);
      $('#school_entr_year').attr('disabled', false);
      $('#school_entr_month').attr('disabled', false);
      $('#school_grad_year').attr('disabled', false);
      $('#school_grad_month').attr('disabled', false);
      $('#school_area').attr('disabled', false);
      $('#school_name').attr('disabled', false);
      $('#school_major').attr('disabled', false);
    }
  });

  // 어학당 이름 비활성화.
  if ($(":input:radio[name=LANG_LEARN]:checked").val() == '해당사항없음') {
    $('#div_lang_school_name').hide();
  } else {
    $('#div_lang_school_name').show();
  }
  $('input[type=radio][name=LANG_LEARN]').change(function() {
    if ($(":input:radio[name=LANG_LEARN]:checked").val() == '해당사항없음') {
      $('#lang_school_name').val('');
      $('#div_lang_school_name').hide();
    } else {
      $('#div_lang_school_name').show();
    }
  });

  //--근무시간 관련 체크
  if( $('#work_time_type3').is(':checked') ) {
    $('#view_span').show();
  } else {
    $('#view_span').hide();
  }
  //--근무시간 관련 이벤트
  $("input[name=WORK_TIME]").change(function() {
    if( $('#work_time_type3').is(':checked') ) {
      $('#view_span').show();
    } else {
      $('#view_span').hide();
    }
  });
  //--선호요일 관련 체크
  if( $('#work_time_isweek1').is(':checked') ) {
    $('#view_div').show();
  } else {
    $('#view_div').hide();
  }
  //--선호요일 관련 이벤트
  $("input[name=WORK_TIME_ISWEEK]").change(function() {
    if( $('#work_time_isweek1').is(':checked') ) {
      $('#view_div').show();
    } else {
      $('#view_div').hide();
    }
  });

  $('#form1').submit(function () {
    //var num_check = /^[0-9]*$/;
    var num_check = /^(\d|-)?(\d|,)*?\d*$/;

    if (this.sido1.value == '') { return alert('Select your preferred working area'), this.sido1.focus(), false; }
    if ($("select#selectBox1").val() == null || $("select#selectBox1").val() == "") {
      alert("Select your specialty"); return false;
    }
   
    // 희망급여 추가
    if (this.pay_type[0].checked == false && this.pay_type[1].checked == false && this.pay_type[2].checked == false && this.pay_type[3].checked == false) return alert('please select salary type'), this.pay_type[0].focus(), false;
    if (this.pay_amount1.value == '') { return alert('Enter your desired salary'), this.pay_amount1.focus(), false; }
    if (!num_check.test(this.pay_amount1.value)) { return alert('Enter your desired salary in numbers'), this.pay_amount1.focus(), false; }
    if (this.pay_amount2.value == '') { return alert('Enter your desired salary'), this.pay_amount2.focus(), false; }
    if (!num_check.test(this.pay_amount2.value)) { return alert('Enter your desired salary in numbers'), this.pay_amount2.focus(), false; }

    //--선호요일 관련
    if( $('#work_time_isweek1').is(':checked') ) {
      if ($("input[name='WORK_TIME_WEEK']:checked").length == 0) { return alert('You should select over than one'), this.work_time_week1.focus(), false; }
    } // 선호요일을 1개 이상 선택해야 합니다

    if ($(":input:radio[name=LANG_LEARN]:checked").val() == '재학중' || $(":input:radio[name=LANG_LEARN]:checked").val() == '졸업') {
      if ($.trim(this.lang_school_name.value) == '') { return alert('Please enter the school name'), this.lang_school_name.focus(), false; }
    }

    return true;
  });

  $('.only-num').keyup(function () {
      this.value = this.value.replace(/[^0-9]/g,'');
  });
  
	
	//카테고리 세션박스 변경 시 하위 카테고리 select ajax
	$("#selectBox1").change(function(){
		console.log("바뀜");
		var selectBox1 = document.getElementById("selectBox1");
		console.log(this.value);
		cate_prnt_en = this.value;
		
		$.ajax("getCateEnJson.do", {
			type: "get",
			dataType : "json",
			data: {"cate_prnt_en" : cate_prnt_en},
			success : function(data){
							
				var dataTag = "";
				$.each(data, function(index, obj){
					dataTag += "<option value=" + this.cate_child +">"
					dataTag += this.cate_child;
					dataTag += "</option>";
				});
	
				$("#cate_child_en").html(dataTag);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Ajax 처리 실패 : \n" +
					  "jqXHR.readyState : " + jqXHR.readyState + "\n" +
					  "textStatus : " + textStatus + "\n" +
					  "errorThrown : " + errorThrown);
			}
		});	
	});
	
	
	$("#addCareer").on("click", function(e){ //경력 추가 버튼
		e.preventDefault();
		fn_addCareer();
	});
	
	$("a[name='deleteCareer']").on("click", function(e){ //커리어삭제 버튼
		e.preventDefault();
		fn_deleteCareer($(this));
	});

	$("a[name='file']").on("click", function(e){ //파일 다운로드
		e.preventDefault(); 
		fn_downloadFile($(this)); 
	});

	$("a[name^='delete']").on("click", function(e){ //파일삭제 버튼
		e.preventDefault();
		fn_deleteFile($(this)); 
	});
	
	$("#addFile").on("click", function(e){ //파일 추가 버튼
		e.preventDefault();
		fn_addFile();
	});
	
	
});



function fn_addCareer(){ //커리어 추가
	var str = "<tr>";
	str += "<th width='15%' class='number'>Career</th>";
	str += "<td width='85%' class='txLeft'><div class='mb-3'>";
	str += "<label for='join/leave'>Joining / Leaving</label>&nbsp; &nbsp;&nbsp; &nbsp;";
	str += "<input type='text' size='6' name='JOIN_YEAR' class='only-num' style='border-radius:.25em; border:1px solid #ced4da; padding:.20rem .75rem' value=''>&nbsp; &nbsp;Year&nbsp; &nbsp;";
	str += "<input type='text' size='4' name='JOIN_MONTH' class='only-num' style='border-radius:.25em; border:1px solid #ced4da; padding:.20rem .75rem' value=''>&nbsp; &nbsp;Month&nbsp; &nbsp;&nbsp; &nbsp; ~ &nbsp; &nbsp;&nbsp; &nbsp;";
	str += "<input type='text' size='6' name='RESIGN_YEAR' class='only-num' style='border-radius:.25em; border:1px solid #ced4da;  padding:.20rem .75rem' value=''>&nbsp; &nbsp;Year&nbsp; &nbsp;";
	str += "<input type='text' size='4' name='RESIGN_MONTH' class='only-num' style='border-radius:.25em; border:1px solid #ced4da; padding:.20rem .75rem' value=''>&nbsp; &nbsp;Month<br></div>";
	str += "<div class='row'>";
	str += "<div class='col-md-3 mb-3'><label for='region'>Region</label>";
	str += "<div class='input-group'><input type='text' class='form-control' name='REGION' value=''></div></div>";
	str += "<div class='col-md-5 mb-3'><label for='company'>Name of company</label>";
	str += "<div class='input-group'><input type='text' class='form-control' name='COMPANY' value=''></div></div>";
	str += "<div class='col-md-4 mb-3'><label for='task'>Assigned task</label>";
	str += "<div class='input-group'><input type='text' class='form-control' name='TASK' value=''></div></div>";
	str += "</div><td><a href='#this' class='btn' id='delete' name='deleteCareer'>Delete</a></td></tr>";
	
	$("#career").append(str);
	
	$("a[name='deleteCareer']").on("click", function(e){ //커리어삭제 버튼
		e.preventDefault();
		fn_deleteCareer($(this));
	});
}


function fn_deleteCareer(obj){ //커리어 삭제
	obj.parent().parent().remove();
}



function fn_downloadFile(obj){ //파일 다운로드
	var idx = obj.parent().find("#IDX").val(); 
	var comSubmit = new ComSubmit(); 
	comSubmit.setUrl("downloadFile.do");
	comSubmit.addParam("IDX", idx); 
	comSubmit.submit(); 
	
}



function fn_deleteFile(obj){ //파일 삭제
	obj.parent().remove(); 
	
}

function insertResume(frm) { //이력서 입력
	frm.action = "insertResume.do";
	frm.submit();
}

function updateResume(frm) { //이력서 수정
	frm.action = "updateResume.do";
	frm.submit();
}

