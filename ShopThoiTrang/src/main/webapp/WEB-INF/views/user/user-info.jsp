<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Daily Shop | Account Page</title>

<link rel="stylesheet"
	href='<c:url value="/assets/css/font-awesome.css"/>' />
<!-- Bootstrap -->
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.css"/>'>
<!-- SmartMenus jQuery Bootstrap Addon CSS -->
<link rel="stylesheet"
	href='<c:url value="/assets/css/jquery.smartmenus.bootstrap.css"/>'>
<!-- Product view slider -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/assets/css/jquery.simpleLens.css"/>'>
<!-- slick slider -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/assets/css/slick.css"/>'>
<!-- price picker slider -->
<link rel="stylesheet" type="text/css"
	href='<c:url value="/assets/css/nouislider.css"/>'>
<!-- Theme color -->
<link id="switcher" rel="stylesheet"
	href='<c:url value="/assets/css/theme-color/default-theme.css"/>'>
<!-- <link id="switcher" href="css/theme-color/bridge-theme.css" rel="stylesheet"> -->
<!-- Top Slider CSS -->
<link rel="stylesheet" media="all"
	href='<c:url value="/assets/css/sequence-theme.modern-slide-in.css"/>'>

<!-- Main style sheet -->
<link rel="stylesheet" href='<c:url value="/assets/css/style.css"/>'>

<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>


	<!-- SCROLL TOP BUTTON -->
	<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
	<!-- END SCROLL TOP BUTTON -->


	<%@ include file="../include/header.jsp"%>
	<!-- Cart view section -->
	<section id="aa-myaccount">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-myaccount-area">
						<div class="row">
							<div class="col-md-6">
								<div class="aa-myaccount-login">
									<h4>Thông tin cá nhân</h4>

									<f:form class="aa-login-form" action="form/info.htm"
										modelAttribute="user" method="post">
										<label for="">Username : </label>
										<b> <span> ${user.userName} </span></b>
										<br>
										<f:input class="hide" path="maNd" name="maNd" />


										<label for="">Họ Tên :</label>
										<f:input class="value" type="text" path="hoTen" name="hoTen"
											autocomplete="off" />
										<span class="loi" style="color: red;font-size: 13px;">${loiHoTen}</span>
										<br>
										<label for="">Email : </label>
										<b> <span> ${user.email} </span></b>
										<br>

										<label for="">SDT : </label>
										<b> <span> ${user.sdt} </span></b>
										<br>
										<label class="tittle">Giới Tính : </label>
										<label><f:radiobutton path="gioiTinh" value="1" />
											Nam</label>
										<label><f:radiobutton path="gioiTinh" value="0" /> Nữ</label>
										<br>
										<label class="tittle">Ngày Sinh: </label>
										<input class="value" type="date" name="ngaySinh"
											value="${user.ngaySinh}" />
										<span class="loi" style="color: red;font-size: 13px;">${loiNgaySinh}</span>
										<br>
										<label for="">Địa Chỉ : </label>
										<f:input class="value" type="text" path="diaChi" name="diaChi"
											autocomplete="off" />
										<span class="loi"  style="color: red;font-size: 13px;">${loiDiaChi}</span>
										<br>

										<button id="btn-save" name="save" class="aa-browse-btn">Cập
											nhật thông tin</button>
											<br><br><br>
									<c:if test="${not empty successMessage}">
										<div class="alert alert-success">${successMessage}</div>
									</c:if>
									</f:form>
									
								</div>
							</div>
<div class="col-md-6">
                <div class="aa-myaccount-register">                 
                 <h4>Đổi mật khẩu</h4>
                 <form  class="aa-login-form" action="form/changePass.htm" id="info-form" 
				method="post">
                    <label for="">Mật khẩu cũ<span>*</span></label>
                   	<input type="password" name="password" >
                   	<br>
                   	<span class="loi" style="color: red;font-size: 13px;">${loiPassword}</span>	<br>
                    <label for="">Mật khẩu mới (tối thiểu 8 kí tự)<span>*</span></label>
                  	 <input type="password" name="newPassword">
                  	 	<br>
					 <span class="loi" style="color: red;font-size: 13px;">${loiNewPassword}</span>	<br>
                     <label for="">Nhập lại mật khẩu mới<span>*</span></label>
                    <input type="password" name="reNewPassword" >
                    	<br>
					<span class="loi" style="color: red;font-size: 13px;">${loiRePassword}</span>	<br>
				
                   <button id="btn-save" name="save" class="aa-browse-btn">Xác nhận</button>   
                   <br><br><br>    
                   	<c:if test="${not empty thanhCong}">
					    <div class="alert alert-success">${thanhCong}</div>
					</c:if>
                  </form>
                  
		
                </div>
              </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / Cart view section -->


	<%@ include file="../include/footer.jsp"%>

	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- SmartMenus jQuery plugin -->
	<script type="text/javascript" src="assets/js/jquery.smartmenus.js"></script>
	<!-- SmartMenus jQuery Bootstrap Addon -->
	<script type="text/javascript"
		src="assets/js/jquery.smartmenus.bootstrap.js"></script>
	<!-- To Slider JS -->
	<script src="assets/js/sequence.js"></script>
	<script src="assets/js/sequence-theme.modern-slide-in.js"></script>
	<!-- Product view slider -->
	<script type="text/javascript" src="assets/js/jquery.simpleGallery.js"></script>
	<script type="text/javascript" src="assets/js/jquery.simpleLens.js"></script>
	<!-- slick slider -->
	<script type="text/javascript" src="assets/js/slick.js"></script>
	<!-- Price picker slider -->
	<script type="text/javascript" src="assets/js/nouislider.js"></script>
	<!-- Custom js -->
	<script src="assets/js/custom.js"></script>


</body>
</html>